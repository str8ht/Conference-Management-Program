import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;


public class ProfilePanel extends JPanel
{
	private static JTextArea notes;
	private JTextField searchKey;
	private JButton searchButton;
	
	public ProfilePanel()
	{
		notes = new JTextArea();
		build(); 
	}
	
	private void build()
	{
		setLayout(new BorderLayout());
		setBackground(Color.decode("#002F6C"));
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		JLabel label = new JLabel("Position Profile");
		Font fnt = new Font(Font.DIALOG, Font.BOLD, 16);
		label.setFont(fnt);
		label.setForeground(Color.WHITE);
		label.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.decode("#002F6c")));
		label.setHorizontalAlignment((int) CENTER_ALIGNMENT);
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.setBackground(Color.decode("#002F6c"));
		
		topPanel.add(label, BorderLayout.NORTH);
		
		JPanel spacerTop = new JPanel();
		spacerTop.setBackground(Color.WHITE);
		//topPanel.add(spacerTop, BorderLayout.SOUTH);
		add(topPanel, BorderLayout.NORTH);
		notes.setBackground(Color.WHITE);
		notes.setBorder(BorderFactory.createCompoundBorder(notes.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		notes.setFont(notes.getFont().deriveFont(16f));
		notes.setForeground(Color.BLACK);
		
		notes.setText("----------------- Select a Position -----------------\n   "
				+ "You can write notes on a position here\n              Please Remember to Save"); 
		
		notes.getCaret().setSelectionVisible(true);
		notes.setLineWrap(true);
		JScrollPane scroller = new JScrollPane(notes);
		add(scroller, BorderLayout.CENTER);

		JPanel sidePanel = new JPanel(new GridLayout(0, 1));
		sidePanel.setBackground(Color.decode("#002F6C"));

		
		JPanel spacer1 = new JPanel();
		spacer1.setBackground(Color.decode("#002F6C"));
		sidePanel.add(spacer1);
		JPanel spacer2 = new JPanel();
		spacer2.setBackground(Color.decode("#002F6C"));
		sidePanel.add(spacer2);
		
		sidePanel.add(new JButton(new UpdateAction()));
		
		JPanel spacer3 = new JPanel();
		spacer3.setBackground(Color.decode("#002F6C"));
		sidePanel.add(spacer3);
		JPanel spacer4 = new JPanel();
		spacer4.setBackground(Color.decode("#002F6C"));
		sidePanel.add(spacer4);
		sidePanel.add(spacer4);


		add(sidePanel, BorderLayout.EAST);
		
		JPanel spacerSide = new JPanel();
		spacerSide.setBackground(Color.WHITE);
		//add(spacerSide, BorderLayout.WEST);
				
		add(createSearchField(), BorderLayout.SOUTH);
		
	}
	
	public static void setProfileText(String text)
	{
		notes.setText(text);
	}

	private JPanel createSearchField()
	{
		JPanel panel = new JPanel();
		panel.setBackground(Color.decode("#002F6C"));
		searchKey = new JTextField(24);
		searchKey.addFocusListener(new FocusListener()
		{
			@Override
			public void focusLost(final FocusEvent pE) {}
			@Override
			public void focusGained(final FocusEvent pE)
			{
				searchKey.selectAll();
			}
		});
		
		searchKey.addKeyListener(new KeyAdapter()
		{
			public void keyReleased(KeyEvent event)
			{
				int evn = event.getKeyCode();
				if (evn == KeyEvent.VK_ENTER)
				{
					new FindAction().actionPerformed(null);
					
				}
			}
		});
		
		panel.setAlignmentX(LEFT_ALIGNMENT);
		searchKey.setText("Search for a Position Here");
		searchKey.requestFocus();
		panel.add(searchKey);
		searchButton = new JButton(new FindAction());
		
		searchButton.setText("Find");
		panel.add(searchButton);
		return panel;
	}
	
	
	
	private class UpdateAction extends AbstractAction
	{
		private UpdateAction()
		{
			super("Save");
		}
		
		public void actionPerformed(ActionEvent arg0)
		{
			String input = notes.getText();
			AttendenceListPanel.updateProfileText(input);
		}
	}
	
	private class FindAction extends AbstractAction
	{
		private FindAction()
		{
			super("Find");
		}
		
		public void actionPerformed(ActionEvent arg0)
		{
			String key = searchKey.getText();			
            for (int row = 0; row <= AttendenceListPanel.attentable.getRowCount() - 1; row++) {

                for (int col = 0; col <= AttendenceListPanel.attentable.getColumnCount() - 1; col++) {

                    if (key.equals(AttendenceListPanel.attentable.getValueAt(row, col))) {

        				AttendenceListPanel.attentable.scrollRectToVisible(AttendenceListPanel.attentable.getCellRect(row, 0, true));
        				AttendenceListPanel.attentable.setRowSelectionInterval(row, row);

                        for (int i = 0; i <= AttendenceListPanel.attentable.getColumnCount() - 1; i++) {

                        	AttendenceListPanel.attentable.getColumnModel().getColumn(i).setCellRenderer(new HighlightRenderer());
                        }
                    }
                }
            }
		}
	}
	
	private class HighlightRenderer extends DefaultTableCellRenderer 
	{
	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) 
	    {
	        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	        return this;
	    }
	}

}
