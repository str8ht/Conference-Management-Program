import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JPanel;

public class StartStopPanel extends JPanel
{
	TimerModel model;
	
	public StartStopPanel(TimerModel model)
	{
		this.model = model;
		build();
	}
	
	private void build()
	{
		GridLayout layout = new GridLayout(0, 1);
		setLayout(layout);
		setBackground(Color.decode("#002F6C"));
		JPanel spacerTop = new JPanel();
		spacerTop.setBackground(Color.decode("#002F6C"));
		add(spacerTop);
		add(new JButton(new StartAction()));
		add(new JButton(new StartBoth()));
		//add(new JButton(new StopAction()));
		add(new JButton(new StopBoth()));;
		JPanel spacerBot = new JPanel();
		spacerBot.setBackground(Color.decode("#002F6C"));
		add(spacerBot);

	}
	
	private class StartAction extends AbstractAction
	{
		private StartAction()
		{
			super("Start");
		}
		
		public void actionPerformed(ActionEvent e) 
		{
			model.start();
		}
	}
	
	private class StartBoth extends AbstractAction
	{
		private StartBoth()
		{
			super("Start Both");
		}
		
		public void actionPerformed(ActionEvent e)
		{
			Registry.startModels();
		}
	}
	
	private class StopAction extends AbstractAction
	{
		private StopAction()
		{
			super("Stop");
		}
		
		public void actionPerformed(ActionEvent e)
		{
			model.stop();
		}
	}
	
	private class StopBoth extends AbstractAction
	{
		private StopBoth()
		{
			super("Stop Both");
		}
		
		public void actionPerformed(ActionEvent e)
		{
			Registry.stopModels();
		}
	}
}
