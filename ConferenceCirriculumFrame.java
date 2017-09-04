import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;

public class ConferenceCirriculumFrame extends JFrame
{
	TimerModel model;
	private AttendenceListModel alm;
	private SpeakersListModel slm;
	private AttendenceListPanel alp;
	private ProfilePanel pp;
	public static TimerPanel caucusTime;
	public static TimerPanel speakerTime;
	
	public ConferenceCirriculumFrame()
	{
		alm = new AttendenceListModel();
		slm = new SpeakersListModel();
		alp = new AttendenceListPanel(alm);
		build();
	}
	
	private void build()
	{
		setFrameProperties();
		getContentPane().setBackground(Color.WHITE);
		JMenuBar menuBar = new JMenuBar();
		menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		menuBar.setBackground(Color.decode("#002F6C"));
		setJMenuBar(menuBar);
		JMenu menu = new JMenu("File");
		menu.setBackground(Color.WHITE);
		menu.setOpaque(true);
		menuBar.add(menu);
		
		JMenuItem item = new JMenuItem(new SaveAction(this));
		item.setMnemonic(KeyEvent.VK_S);
		menu.add(item);
		item = new JMenuItem(new OpenAction(this));
		item.setMnemonic(KeyEvent.VK_O);
		menu.add(item);
		item = new JMenuItem(new ExportAction(this));
		item.setMnemonic(KeyEvent.VK_E);
		menu.add(item);		
		
		item = new JMenuItem(new PullSpeakersList());
		item.setBackground(Color.WHITE);
		item.setOpaque(true);
		item.setMnemonic(KeyEvent.VK_S);
		menuBar.add(item);
		item = new JMenuItem(new PullMotionsList());
		item.setBackground(Color.WHITE);
		item.setOpaque(true);
		item.setMnemonic(KeyEvent.VK_L);
		menuBar.add(item);
		item = new JMenuItem(new HelpAction(this));
		item.setBackground(Color.WHITE);
		item.setOpaque(true);
		item.setMnemonic(KeyEvent.VK_H);
		menuBar.add(item);
		
		GridLayout layout = new GridLayout(2, 2, 2, 2);
		setLayout(layout);
		caucusTime = new TimerPanel(false);
		speakerTime = new TimerPanel(true);
		SpeakersListPanel slp = new SpeakersListPanel(slm);
		pp = new ProfilePanel();
		getContentPane().add(caucusTime);
		add(speakerTime);
		add(alp);
		//add(slp);
		add(pp);
		pack();
		setSize(800, 600);
		setVisible(true);
	}
	
	private void setFrameProperties()
	{
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		try
		{
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());		
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		WindowAdapter windowAdapter = new WindowAdapter()
		{
			public void windowClosing(WindowEvent we)
			{
				int decision = JOptionPane.showConfirmDialog(null,  "Save and Exit?\nNo means to exit without saving.", "Exit Confirmation", JOptionPane.YES_NO_CANCEL_OPTION);
				if (decision == JOptionPane.YES_OPTION)
				{
					new SaveAction(null).actionPerformed(null);
					System.exit(0);
				}
				
				if (decision == JOptionPane.NO_OPTION)
				{
					System.exit(0);
				}
			}
		};
		addWindowListener(windowAdapter);
		setTitle("VAMUN XXXVII");
		setLocation(600,200);
	}
	
	public static void autofillTime(ClockValue val)
	{
		caucusTime.panel.model.clear();
		speakerTime.panel.model.clear();
		caucusTime.panel.model.addTime(val.container[0]);
		speakerTime.panel.model.addTime(val.container[1]);
	}
	
	private class OpenAction extends AbstractAction
	{
		private JFrame frame;
		private OpenAction(JFrame frame)
		{
			super("Open File...");
			this.frame = frame;
		}
		
		public void actionPerformed(ActionEvent arg0)
		{
			JFileChooser fc = new JFileChooser();
			fc.setFileFilter(new MuneFilter());
			int returnVal = fc.showOpenDialog(frame);
			if (returnVal == JFileChooser.APPROVE_OPTION)
			{
				File file = fc.getSelectedFile();
				try
				{
					alm.readFromFile(file);
				}
				
				catch (FileNotFoundException e)
				{
					JOptionPane.showMessageDialog(frame,  "File not found.", "Warning!", JOptionPane.WARNING_MESSAGE);
				}
			}
		}
	}
	
	private class SaveAction extends AbstractAction
	{
		private JFrame frame;
		private SaveAction(JFrame frame)
		{
			super("Save As...");
			this.frame = frame;
		}
		
		public void actionPerformed(ActionEvent arg0)
		{
			JFileChooser fc = new JFileChooser();
			fc.setFileFilter(new MuneFilter());
			int returnVal = fc.showSaveDialog(frame);
			if (returnVal == JFileChooser.APPROVE_OPTION)
			{
				File file = fc.getSelectedFile();
				try
				{
					if (!file.getName().toLowerCase().endsWith(".MUNe"))
					{
						File parent = file.getParentFile();
						String name = file.getName() + ".MUNe";
						File output = new File(parent, name);
						file.delete();
						file = output;
					}
					alm.writeToFile(file);
				}
				
				catch (IOException e)
				{
					JOptionPane.showMessageDialog(frame,  "Unable to save file", "Warning!", JOptionPane.WARNING_MESSAGE);
				}
			}
		}
	}
	
	private class CsvFilter extends FileFilter
	{
		@Override
		public boolean accept(File arg0)
		{
			return arg0.getName().endsWith(".csv") || arg0.isDirectory();
		}
		
		@Override
		public String getDescription()
		{
			return ".csv";
		}
	}
	
	private class MuneFilter extends FileFilter
	{
		@Override
		public boolean accept(File arg0)
		{
			return arg0.getName().endsWith(".MUNe") || arg0.isDirectory();
		}
		
		@Override
		public String getDescription()
		{
			return ".MUNe";
		}
	}
	
	private class HelpAction extends AbstractAction
	{
		private JFrame frame;
		private HelpAction(JFrame frame)
		{
			super("Help");
			this.frame = frame;
		}
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			File file = new File("README.txt");
			JTextArea helpText = null;
			try
			{
				helpText = new JTextArea(readFromFile(file), 40, 50);
				helpText.setAutoscrolls(true);
				helpText.setEditable(false);
				JScrollPane scroller = new JScrollPane(helpText);
				JOptionPane.showMessageDialog(null, scroller, "Info", JOptionPane.PLAIN_MESSAGE);
			}
			
			catch (FileNotFoundException e2)
			{
				JOptionPane.showMessageDialog(frame,  "File not found.", "Warning!", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	
	private class PullSpeakersList extends AbstractAction
	{
		private PullSpeakersList()
		{
			super("Speaker's List");
		}
		
		public void actionPerformed(ActionEvent arg0)
		{
			createSpeakersFrame();
		}
		
		private void createSpeakersFrame()
		{
			JFrame frame = new JFrame();
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setVisible(true);
			frame.setTitle("Speaker's List");
			frame.setLocation(200,200);
			frame.setSize(400, 400);
			try
			{
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			}
			
			catch (Exception e)
			{
				e.printStackTrace();
			}
			SpeakersListPanel slp = new SpeakersListPanel(slm);
			slp.setOpaque(true);
			frame.add(slp);
			
		}
	}
	
	private class PullMotionsList extends AbstractAction
	{
		public PullMotionsList()
		{
			super("List of Motions");
		}
		
		public void actionPerformed(ActionEvent arg0)
		{
			createMotionsFrame();
		}
		
		private void createMotionsFrame()
		{
			JFrame frame = new JFrame();
			MotionsPanel mp = new MotionsPanel();
			frame.add(mp);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setVisible(true);
			frame.setTitle("List of Motions");
			frame.setLocation(1400, 200);
			frame.setSize(500, 600);
			// TODO: create class "MotionsPanel"
		}
	}
	
	private class ExportAction extends AbstractAction
	{
		private JFrame frame;
		private ExportAction(JFrame frame)
		{
			super("Export to Excel...");
			this.frame = frame;
		}
		
		public void actionPerformed(ActionEvent arg0)
		{
			JFileChooser fc = new JFileChooser();
			fc.setFileFilter(new CsvFilter());
			int returnVal = fc.showSaveDialog(frame);
			if (returnVal == JFileChooser.APPROVE_OPTION)
			{
				File file = fc.getSelectedFile();
				try
				{
					if (!file.getName().toLowerCase().endsWith(".csv"))
					{
						File parent = file.getParentFile();
						String name = file.getName() + ".csv";
						File output = new File(parent, name);
						file.delete();
						file = output;
					}
					alm.exportToFile(file);
				}
				
				catch (IOException e)
				{
					JOptionPane.showMessageDialog(frame,  "Unable to save file", "Warning!", JOptionPane.WARNING_MESSAGE);
				}
			}
		}
	}
	
	private String readFromFile(File fileName) throws FileNotFoundException
	{
		Scanner scr = new Scanner(fileName);
		String entireText = "";
		while(scr.hasNext())
		{
			String line = scr.nextLine();
			entireText += "\n    " + line;
		}
		scr.close();
		return entireText;
	}
}
