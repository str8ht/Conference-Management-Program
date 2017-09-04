import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JPanel;

	public class ButtonPanel extends JPanel
	{
		public TimerModel model;
		
		public ButtonPanel(TimerModel model, boolean isSpeakingTime)
		{
			this.model = model;
			build(isSpeakingTime);
			Registry.addModel(model);
		}
		
		private void build(boolean isSpeakingTime)
		{
			GridLayout layout = new GridLayout(2, 5, 0, 0);
			//FlowLayout layout = new FlowLayout();
			setLayout(layout);
			setBackground(Color.decode("#002F6C"));
			
			JPanel spacer1 = new JPanel();
			spacer1.setBackground(Color.decode("#002F6C"));
			JPanel spacer2 = new JPanel();
			spacer2.setBackground(Color.decode("#002F6C"));
			JPanel spacer3 = new JPanel();
			spacer3.setBackground(Color.decode("#002F6C"));
			
			if (!isSpeakingTime)
			{
				
				add(spacer1);
				add(spacer2);
				add(spacer3);
				add(new JButton(new ClearAll()));
				addButton("9 Min.", 540000);
				addButton("5 Min.", 300000);
				addButton("1 Min.", 60000);
				add(new JButton(new ClearAction()));
			}
			
			else
			{
				add(spacer1);
				add(spacer2);
				add(spacer3);
				add(new JButton(new ClearAll()));
				addButton("1 Min.", 60000);
				addButton("45 Sec.", 45000);
				addButton("30 Sec.", 30000);
				add(new JButton(new ClearAction()));
			}
		}
	
	private void addButton(String name, int value)
	{
		JButton button = new JButton(new ReactionAction(name, value));
		add(button);
	}
	
	private class ReactionAction extends AbstractAction
	{
		private final int value;
		private ReactionAction(String name, int value)
		{
			super(name);
			this.value = value;
		}
		
		public void actionPerformed(ActionEvent e)
		{
			model.addTime(value);
		}
	}
	
	private class ClearAction extends AbstractAction
	{
		private ClearAction()
		{
			super("Clear");
		}
		
		public void actionPerformed(ActionEvent e)
		{
			model.clear();
		}
	}
	
	private class ClearAll extends AbstractAction
	{
		private ClearAll()
		{
			super("Clear Both");
		}
		
		public void actionPerformed(ActionEvent e)
		{
			Registry.clearModels();
		}
	}
}
