import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class AttendenceListPanel extends JPanel
{
	public static AttendenceListModel almodel;
	public static JTable attentable;
	private JTextField country;
	private JTextField value;
	
	
	public AttendenceListPanel(AttendenceListModel model)
	{
		this.almodel = model;
		build();
	}
	
	private void build()
	{
		setLayout(new BorderLayout());
		setBackground(Color.decode("#002F6C"));
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		attentable = new JTable();
		almodel.setTable(attentable);
		JScrollPane scroller = new JScrollPane(attentable);
		attentable.setFillsViewportHeight(true);
		add(scroller, BorderLayout.CENTER);
		almodel.update();		
		
		JLabel label = new JLabel("Attendence List");
		Font fnt = new Font(Font.DIALOG, Font.BOLD, 16);
		label.setFont(fnt);
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment((int) CENTER_ALIGNMENT);
		add(label, BorderLayout.NORTH);
		JPanel panel = createButtons();
		add(panel, BorderLayout.EAST);
		add(createTextFields(), BorderLayout.SOUTH);
		
		ListSelectionModel cellSelectionModel = attentable.getSelectionModel();
	    cellSelectionModel.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

	    cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
	      public void valueChanged(ListSelectionEvent e) 
	      {
	    	  	int[] selections = attentable.getSelectedRows();
				if (selections.length == 0)
				{
					return;
				}
				Attendee att = almodel.getAttendee(selections[0]);
				ProfilePanel.setProfileText(att.getProfile());
	      }

	    });
		
		country.addKeyListener(new KeyAdapter()
		{
			public void keyReleased(KeyEvent event)
			{
				int evn = event.getKeyCode();
				if (evn == KeyEvent.VK_ENTER)
				{
					new AddAction().actionPerformed(null);
					
				}
			}
		});
		
		value.addKeyListener(new KeyAdapter()
		{
			public void keyReleased(KeyEvent event)
			{
				int evn = event.getKeyCode();
				if (evn == KeyEvent.VK_ENTER)
				{
					new AddAction().actionPerformed(null);
					
				}
			}
		});
	}
	
	private JPanel createButtons()
	{
		JPanel panel = new JPanel();
		panel.setBackground(Color.decode("#002F6C"));
		GridLayout layout = new GridLayout(0, 1);
		panel.setLayout(layout);
		JPanel spacer1 = new JPanel();
		spacer1.setBackground(Color.decode("#002F6C"));
		JPanel spacer2 = new JPanel();
		spacer2.setBackground(Color.decode("#002F6C"));
		panel.add(spacer1);
		panel.add(new JButton(new DeleteAction()));
		panel.add(new JButton(new EditAction()));
		panel.add(new JButton(new AddAction()));
		panel.add(spacer2);
		return panel;
	}
	
	private JPanel createTextFields()
	{
		JPanel panel = new JPanel();
		panel.setBackground(Color.decode("#002F6C"));
		country = new JTextField(24);
		country.addFocusListener(new FocusListener()
		{
			@Override
			public void focusLost(final FocusEvent pE) {}
			@Override
			public void focusGained(final FocusEvent pE)
			{
				country.selectAll();
			}
		});
		
		panel.setAlignmentX(LEFT_ALIGNMENT);
		country.setText("Add a Position Here");
		country.requestFocus();
		panel.add(country);
		value = new JTextField(5);
		
		value.addFocusListener(new FocusListener()
		{
			@Override
			public void focusLost(final FocusEvent pE) {}
			@Override
			public void focusGained(final FocusEvent pE)
			{
				value.selectAll();
			}
		});
		
		value.setText("Score");
		panel.add(value);
		return panel;
	}
	
	public static void updateProfileText(String text)
	{
	  	int[] selections = attentable.getSelectedRows();
	  	if (selections.length == 0)
		{
			return;
		}
		Attendee att = almodel.getAttendee(selections[0]);
		att.saveProfileText(text);
	}
	
	private class DeleteAction extends AbstractAction
	{
		private DeleteAction()
		{
			super("Delete");
		}
		
		public void actionPerformed(ActionEvent e)
		{
			int[] selections = attentable.getSelectedRows();
			if (selections.length == 0)
			{
				JOptionPane.showMessageDialog(null, "Nothing has been slected.", "INFO", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			Attendee[] removal = new Attendee[selections.length];
			
			for(int i=0; i<selections.length; i++)
			{
				removal[i] = almodel.getAttendee(selections[i]);
			}
			
			for(int i=0; i<selections.length; i++)
			{
				almodel.deleteCountry(removal[i]);;
			}
			
			ProfilePanel.setProfileText("----------------- Select a Position -----------------\n   "
					+ "You can write notes on a position here\n              Please Remember to Save");
		}
	}
	
	private class EditAction extends AbstractAction
	{
		private EditAction()
		{
			super("Edit");
		}
		
		public void actionPerformed(ActionEvent e)
		{
			int[] selections = attentable.getSelectedRows();
			if (selections.length == 0)
			{
				JOptionPane.showMessageDialog(null,  "Nothing has been selected.", "INFO", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			
			Attendee att = almodel.getAttendee(selections[0]);
			country.setText(att.getName());
			value.setText("" + att.getValue());
			almodel.deleteCountry(att);
		}
	}
	
	private class AddAction extends AbstractAction
	{
		private AddAction()
		{
			super("Add");
		}
		
		public void actionPerformed(ActionEvent e)
		{
			double v = 0;
			String val = value.getText();
			
			try
			{
				v = Double.parseDouble(val);
			}
			catch (NumberFormatException we)
			{
				// do nothing
			}
			
			Attendee attendee = new Attendee(country.getText(), v);
			almodel.addCountry(attendee);
			country.setText("");
			value.setText("");
		}
	}
}
