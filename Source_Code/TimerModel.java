import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class TimerModel
{
	private int timerVal;
	private long startTime;
	private long endTime;
	private Timer timer;
	private TimeDisplay timeDisplay;
	private TimerPanel timerPanel;
	
	public TimerModel()
	{
		timerVal = 0;
	}
	
	public void setTimerPanel(TimerPanel timerPanel)
	{
		this.timerPanel = timerPanel;
		this.timeDisplay = timerPanel.getTimeDisplay();
	}
	
	public void addTime(int time)
	{
		timerVal += time;
		if (timer != null && timer.isRunning())
		{
			endTime += (time);
		}
		else
		{
			timeDisplay.updateUI(timerVal);
		}

	}
	
	public void start()
	{
		JFrame frame = new JFrame();
		if (timer != null && timer.isRunning()) 
		{
			//JOptionPane.showMessageDialog(frame, "The timer is already running.", "Hey!", JOptionPane.WARNING_MESSAGE);
		}
		else
		{
			ActionListener actList = new Reloader();
			timer = new Timer(500, actList);
			startTime = System.currentTimeMillis();
			endTime = startTime + timerVal;
			timer.start();
		}
	}
	
	public void stop()
	{
		if (timer != null && timer.isRunning()) 
		{
			timer.stop();
			long stopTime = endTime - System.currentTimeMillis();
			timerVal = (int) (stopTime);
			timeDisplay.updateUI(timerVal);
		}
	}
	
	public void clear()
	{
		timerVal = 0;
		timerPanel.clear();
		if (timer != null)
		{
			timer.stop();
		}
	}
	
	public void clearAll()
	{
		timerVal = 0;
		timerPanel.clearAll();
		if (timer != null)
		{
			timer.stop();
		}
	}
	
	private void updateUI()
	{
		long currentTime = System.currentTimeMillis();
		if (currentTime > endTime)
		{
			timer.stop();
			timerVal = 0;
			timeDisplay.updateUI(0);
		}
		else
		{
			timerPanel.updateUI((endTime - currentTime));
		}
	}
	
	public class Reloader implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			updateUI();
		}
	}
}
