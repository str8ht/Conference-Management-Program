import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class TimeDisplay extends JLabel implements TimeUI
{
	private TimeDisplay tmd;
	public TimeDisplay()
	{
		Font fnt = new Font(Font.DIALOG, Font.BOLD, 110);
		setFont(fnt);
		setForeground(Color.WHITE);
		setHorizontalAlignment(SwingConstants.CENTER);
		updateUI(0);
	}
	
	public void updateUI(long time)
	{
		setText(calcStringTime(time));
	}
	
	public String calcStringTime(long time)
	{
		if (time < 0)
		{
			return 0 + ":" + 00;
		}
		if (time > 3599400)
		{
			JOptionPane.showMessageDialog(null, "Please Don't input more than an hour.","Hey!",JOptionPane.INFORMATION_MESSAGE);
			time -= 1000;
			return "59:59";
		}
		
		time += 999;
		int timeSec = (int)(time/1000);
		int timeMin = timeSec/60;
		int remainder = timeSec%60;
		
		String secondsString = (remainder > 9) ? "" + remainder : "0" + remainder;
		
		String display = timeMin + ":" + secondsString;
		return display;
	}
	
	public void clear()
	{
		updateUI(0);
	}
}
