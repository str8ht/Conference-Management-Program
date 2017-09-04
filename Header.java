import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Header extends JPanel implements TimeUI
{
	boolean isSpeakingTime;
	JPanel light1;
	JPanel light2;
	
	Color backgroundColor = Color.decode("#E57200");
	
	public Header(boolean isSpeakingTime)
	{
		this.isSpeakingTime = isSpeakingTime;
		build();
	}
	
	private void build()
	{
		setLayout(new BorderLayout());
		setBackground(Color.decode("#002F6C"));
		String text = (isSpeakingTime) ? "Speaking Time" : "Caucus Time";
		JLabel label = new JLabel(text);
		Font fnt = new Font(Font.DIALOG, Font.BOLD, 16);
		label.setFont(fnt);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		add(label, BorderLayout.WEST);
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createRaisedBevelBorder());
		panel.setBackground(backgroundColor);
		add(panel, BorderLayout.EAST);
		light1 = makeLight();
		light2 = makeLight();
		panel.add(light2);
		panel.add(light1);		
	}
	
	private JPanel makeLight() 
	{
		JPanel panel = new JPanel();
		panel.setSize(32,25);
		panel.setBorder(BorderFactory.createLineBorder(Color.white, 2, false)); // color, thickness, roundness
		panel.setBackground(backgroundColor);
		return panel;
	}
	
	public void updateUI(long time)
	{
		if (time < 600)
		{
			light1.setBackground(Color.red);
			light2.setBackground(Color.red);
		}
		
		else if (time < 30000)
		{
			light1.setBackground(Color.orange);
			light2.setBackground(Color.orange);
		}
		
		else if (time < 60000)
		{
			light1.setBackground(Color.yellow);
		}
	}
	
	public void clear()
	{
		light1.setBackground(backgroundColor);
		light2.setBackground(backgroundColor);
	}
}

