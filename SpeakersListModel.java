import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.UIManager;

public class SpeakersListModel extends AbstractConferenceModel
{
	private JList<Attendee> list;
	
	public SpeakersListModel()
	{
		list = new JList<Attendee>();
	}
	
	public void sendToBottom()
	{
		if (attendeeList.isEmpty()) 
		{
			return;
		}
		Attendee temp = attendeeList.remove(0);
		attendeeList.add(temp);
		update();
	}
	
	public JList getList()
	{
		return list;
	}
	
	@Override
	protected void update()
	{
		if (list != null)
		{
			Attendee[] attendees = attendeeList.toArray(new Attendee[0]);
			list.setListData(attendees);
		}
	}
}
