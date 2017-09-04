import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class TimerPanel extends JPanel implements TimeUI
{
	private TimerModel model;
	private boolean isSpeakingTime;
	private TimeDisplay tmd;
	private Header header;
	public  ButtonPanel panel;

	public TimerPanel(boolean isSpeakingTime)
	{
		model = new TimerModel();
		this.isSpeakingTime = isSpeakingTime;
		build();
		model.setTimerPanel(this);
		Registry.add(this);
	}
	
	private void build()
	{
		setLayout(new BorderLayout());
		setBackground(Color.decode("#002F6C"));
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		panel = new ButtonPanel(model, isSpeakingTime);
		add(panel, BorderLayout.SOUTH);
		tmd = new TimeDisplay();
		add(tmd, BorderLayout.CENTER);
		add(new StartStopPanel(model), BorderLayout.EAST);
		header = new Header(isSpeakingTime);
		add(header, BorderLayout.NORTH);
	}
	
	public void updateUI(long time)
	{
		tmd.updateUI(time);
		header.clear();
		header.updateUI(time);
	}
	
	public TimeDisplay getTimeDisplay()
	{
		return tmd;
	}
	
	public void clear()
	{
		tmd.clear();
		header.clear();
	}
	
	public void clearAll()
	{
		Registry.clearAll();
	}
}
