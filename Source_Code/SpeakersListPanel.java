import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class SpeakersListPanel extends JPanel
{
	private SpeakersListModel model;
	private JTextField country;
	
	public SpeakersListPanel(SpeakersListModel model)
	{
		this.model = model;
		build();
	}
	
	private void build()
	{
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createLoweredBevelBorder());
		JList list = model.getList();
		list.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		JScrollPane scroller = new JScrollPane(list);
		add(scroller, BorderLayout.CENTER);
		JLabel label = new JLabel("Speaker's List");
		Font fnt = new Font(Font.SERIF, Font.BOLD, 16);
		label.setFont(fnt);
		label.setHorizontalAlignment((int) CENTER_ALIGNMENT);
		add(label, BorderLayout.NORTH);
		country = new JTextField(24);
		country.setText("Add a speaker here");
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
		add(country, BorderLayout.SOUTH);
		add(createButtons(), BorderLayout.EAST);
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
	}
	
	private JPanel createButtons()
	{
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		//BorderLayout layout = new BorderLayout(0,70);
		GridLayout layout = new GridLayout(0,1);
		panel.setLayout(layout);
		
		JPanel spacer1 = new JPanel();
		spacer1.setBackground(Color.WHITE);
		JPanel spacer2 = new JPanel();
		spacer2.setBackground(Color.WHITE);
		JPanel spacer3 = new JPanel();
		spacer3.setBackground(Color.WHITE);
		JPanel spacer4 = new JPanel();
		spacer4.setBackground(Color.WHITE);
		
		panel.add(spacer1);
		panel.add(spacer2);
		panel.add(new JButton(new MoveAction()));
		panel.add(new JButton(new DeleteAction()));
		panel.add(new JButton(new AddAction()));
		panel.add(spacer3);
		panel.add(spacer4);
		return panel;
	}
	
	private class DeleteAction extends AbstractAction
	{
		private DeleteAction()
		{
			super("Delete");
		}
		
		public void actionPerformed(ActionEvent e)
		{
			int[] selections = model.getList().getSelectedIndices();
			if (selections.length == 0)
			{
				JOptionPane.showMessageDialog(null, "Nothing has been selected.", "Hey!", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			Attendee[] removal = new Attendee[selections.length];
			
			for(int i=0; i<selections.length; i++)
			{
				removal[i]= model.getAttendee(selections[i]);
			}
			
			for(int i=0; i<selections.length; i++)
			{
				model.deleteCountry(removal[i]);
			}
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
			Attendee attendee = new Attendee(country.getText(), 0);
			model.addCountry(attendee);;
			country.setText("");
		}
	}
	
	private class MoveAction extends AbstractAction
	{
		private MoveAction()
		{
			super("Move Up");
		}
		
		public void actionPerformed(ActionEvent e)
		{
			model.sendToBottom();
		}
	}
}
