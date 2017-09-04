import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.util.Hashtable;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class MotionsPanel extends JPanel
{
	private JCheckBox motionsHolder[];
	private Hashtable<JCheckBox, ClockValue> lookUp;
	
	public MotionsPanel()
	{
		motionsHolder = new JCheckBox[25];
		lookUp = new Hashtable<JCheckBox, ClockValue>();
		build();
	}
	
	private void build()
	{
		setBackground(Color.WHITE);
		setLayout(new BorderLayout());

		JPanel topPanel = new JPanel();
		topPanel.setBackground(Color.WHITE);
		
		JLabel label = new JLabel("Motions in order of Disruptiveness");
		Font fnt = new Font(Font.DIALOG, Font.BOLD, 16);
		label.setFont(fnt);
		label.setForeground(Color.BLACK);
		label.setHorizontalAlignment((int) CENTER_ALIGNMENT);
		topPanel.add(label);
		
		add(topPanel, BorderLayout.NORTH);
		
		ClockValue Input4 = new ClockValue(900000,0);
		ClockValue Input5 = new ClockValue(600000,0);
		ClockValue Input6 = new ClockValue(300000,0);
		ClockValue Input8 = new ClockValue(720000,60000);
		ClockValue Input9 = new ClockValue(720000,45000);
		ClockValue Input10 = new ClockValue(720000,30000);
		ClockValue Input11 = new ClockValue(600000,60000);
		ClockValue Input12 = new ClockValue(600000,45000);
		ClockValue Input13 = new ClockValue(540000,60000);
		ClockValue Input14 = new ClockValue(540000,45000);
		ClockValue Input15 = new ClockValue(540000,30000);
		ClockValue Input16 = new ClockValue(360000,60000);
		ClockValue Input17 = new ClockValue(360000,45000);
		ClockValue Input18 = new ClockValue(360000,30000);
		ClockValue Input19 = new ClockValue(300000,60000);
		ClockValue Input20 = new ClockValue(300000,30000);
				
		motionsHolder[0] = new JCheckBox("Voting Procedure: 2-For / 2-Against");
		motionsHolder[1] = new JCheckBox("Voting Procedure: 1-For / 1-Against");
		motionsHolder[2] = new JCheckBox("Voting Procedure");		
		motionsHolder[3] = new JCheckBox("Extension By: ");
		
		motionsHolder[3].setBackground(Color.WHITE);
		motionsHolder[3].setAlignmentX(LEFT_ALIGNMENT);
		
		JPanel extension = new JPanel((LayoutManager) new FlowLayout(FlowLayout.LEFT));
		extension.setBackground(Color.WHITE);
		extension.add(motionsHolder[3], BorderLayout.WEST);
		extension.add(new JTextField(5), BorderLayout.EAST);
				
		motionsHolder[4] = new JCheckBox("15 Min (UNMODERATED)");
		motionsHolder[5] = new JCheckBox("10 Min (UNMODERATED)");
		motionsHolder[6] = new JCheckBox("5 Min. (UNMODERATED)");
		motionsHolder[7] = new JCheckBox("Other (UNMODERATED): ");
		
		motionsHolder[7].setBackground(Color.WHITE);
		motionsHolder[7].setAlignmentX(LEFT_ALIGNMENT);
		
		JPanel unmod = new JPanel((LayoutManager) new FlowLayout(FlowLayout.LEFT));
		unmod.setBackground(Color.WHITE);
		unmod.add(motionsHolder[7], BorderLayout.WEST);
		unmod.add(new JTextField(5), BorderLayout.EAST);
		
		motionsHolder[8] = new JCheckBox("12 Min | 1 Min (MODERATED)");
		motionsHolder[9] = new JCheckBox("12 Min | 45 Sec (MODERATED)");
		motionsHolder[10] = new JCheckBox("12 Min | 30 Sec (MODERATED)");
		motionsHolder[11] = new JCheckBox("10 Min | 1 Min (MODERATED)");
		motionsHolder[12] = new JCheckBox("10 Min | 30 Sec (MODERATED)");
		motionsHolder[13] = new JCheckBox("9 Min | 1 Min (MODERATED)");
		motionsHolder[14] = new JCheckBox("9 Min | 45 Sec (MODERATED)");
		motionsHolder[15] = new JCheckBox("9 Min | 30 sec (MODERATED)");
		motionsHolder[16] = new JCheckBox("6 Min | 1 min (MODERATED)");
		motionsHolder[17] = new JCheckBox("6 Min | 45 Sec (MODERATED)");
		motionsHolder[18] = new JCheckBox("6 Min | 30 sec (MODERATED)");
		motionsHolder[19] = new JCheckBox("5 Min | 1 min (MODERATED)");
		motionsHolder[20] = new JCheckBox("5 Min | 30 sec (MODERATED)");
		motionsHolder[21] = new JCheckBox("Other (MODERATED): ");
		
		motionsHolder[21].setBackground(Color.WHITE);
		motionsHolder[21].setAlignmentX(LEFT_ALIGNMENT);
		
		JPanel mod = new JPanel((LayoutManager) new FlowLayout(FlowLayout.LEFT));
		mod.setBackground(Color.WHITE);
		mod.add(motionsHolder[21], BorderLayout.WEST);
		mod.add(new JTextField(5), BorderLayout.EAST);
		
		motionsHolder[22] = new JCheckBox("Motion to Introduce");
		motionsHolder[23] = new JCheckBox("Other: ");

		motionsHolder[23].setBackground(Color.WHITE);
		motionsHolder[23].setAlignmentX(LEFT_ALIGNMENT);
		
		JPanel other = new JPanel((LayoutManager) new FlowLayout(FlowLayout.LEFT));
		other.setBackground(Color.WHITE);
		other.add(motionsHolder[23], BorderLayout.WEST);
		other.add(new JTextField(13), BorderLayout.EAST);
		
		
		JPanel leftCol = new JPanel(new GridLayout(0,1));
		JPanel rightCol = new JPanel(new GridLayout(0,1));
		
		leftCol.setBorder(BorderFactory.createCompoundBorder(leftCol.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		rightCol.setBorder(BorderFactory.createCompoundBorder(rightCol.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		leftCol.setBackground(Color.WHITE);
		rightCol.setBackground(Color.WHITE);

		
		for (int i=0; i<3; i++)
		{
			motionsHolder[i].setBackground(Color.WHITE);
			leftCol.add(motionsHolder[i]);
		}
		leftCol.add(extension);
		for (int i=4; i<7; i++)
		{
			motionsHolder[i].setBackground(Color.WHITE);
			leftCol.add(motionsHolder[i]);
		}
		leftCol.add(unmod);
		for (int i=8; i<12; i++)
		{
			motionsHolder[i].setBackground(Color.WHITE);
			leftCol.add(motionsHolder[i]);
		}
		
		for (int i=12; i<21; i++)
		{
			motionsHolder[i].setBackground(Color.WHITE);
			rightCol.add(motionsHolder[i]);
		}
		rightCol.add(mod);
		for (int i=22; i<23; i++)
		{
			motionsHolder[i].setBackground(Color.WHITE);
			rightCol.add(motionsHolder[i]);
		}
		rightCol.add(other);
		
		add(leftCol, BorderLayout.WEST);
		add(rightCol, BorderLayout.EAST);
		
		lookUp.put(motionsHolder[4],Input4);
		lookUp.put(motionsHolder[5],Input5);
		lookUp.put(motionsHolder[6],Input6);
		lookUp.put(motionsHolder[8],Input8);
		lookUp.put(motionsHolder[9],Input9);
		lookUp.put(motionsHolder[10],Input10);
		lookUp.put(motionsHolder[11],Input11);
		lookUp.put(motionsHolder[12],Input12);
		lookUp.put(motionsHolder[13],Input13);
		lookUp.put(motionsHolder[14],Input14);
		lookUp.put(motionsHolder[15],Input15);
		lookUp.put(motionsHolder[16],Input16);
		lookUp.put(motionsHolder[17],Input17);
		lookUp.put(motionsHolder[18],Input18);
		lookUp.put(motionsHolder[19],Input19);
		lookUp.put(motionsHolder[20],Input20);
		
		
		JPanel botPan = new JPanel();
		botPan.setLayout(new GridLayout(1,0));
		
		JPanel spacer1 = new JPanel();
		spacer1.setBackground(Color.WHITE);
		JPanel spacer2 = new JPanel();
		spacer2.setBackground(Color.WHITE);
		JPanel spacer3 = new JPanel();
		spacer3.setBackground(Color.WHITE);
		
		botPan.add(spacer1, BorderLayout.WEST);
		botPan.add(new JButton(new AutofillAction()), BorderLayout.CENTER);
		botPan.add(spacer2, BorderLayout.EAST);
		
		add(botPan, BorderLayout.SOUTH);
	}
	
	/*
	 *  Use Gridlayout (2 cols) put it BorderLayout.CENTER
	 *   _______________
	 *  | Apply to Time | (Button) "This will Populate the timers"
	 *  
	 *  Look Up a List of all motions (Put in Order of Disruptiveness)
	 */
	
	private class AutofillAction extends AbstractAction
	{
		private AutofillAction()
		{
			super("Autofill Timers");
		}
		
		public void actionPerformed(ActionEvent arg0)
		{
			JCheckBox temp = null;
			boolean switcher;
			ClockValue val;
			int counter = 0;
			for (int i=0; i<24; i++)
			{
				if (motionsHolder[i].isSelected())
				{
					temp = motionsHolder[i];
					counter++;
				}
			}
			
			switcher = (temp != motionsHolder[0]) && (temp != motionsHolder[1]) && (temp != motionsHolder[2])
					&& (temp != motionsHolder[3]) && (temp != motionsHolder[7]) && (temp != motionsHolder[21])
					&& (temp != motionsHolder[22]) && (temp != motionsHolder[23]);
			
				if (counter > 1)
				{
					JOptionPane.showMessageDialog(null, "Please select only 1 option.", "Info", JOptionPane.INFORMATION_MESSAGE);
				}
			
				else if (switcher && (counter == 1))
				{
					val = lookUp.get(temp);
					ConferenceCirriculumFrame.autofillTime(val);
				}
			counter = 0;
		}
	}
}
