import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class AttendeeTableModel extends AbstractTableModel
{
	AttendenceListModel attendees;
	
	public AttendeeTableModel(AttendenceListModel attendees)
	{
		this.attendees = attendees;
	}
	
	@Override
	public int getColumnCount()
	{
		return 2;
	}
	
	public int getRowCount()
	{
		return attendees.getAttendeeList().size();
	}
	
	public String getColumnName(int column)
	{
		return (column == 0) ? "Position" : "Total Score";
	}
	
	public Object getValueAt(int rowIndex, int columnIndex)
	{
		ArrayList<Attendee> list = attendees.getAttendeeList();
		Attendee attendee = list.get(rowIndex);
		return (columnIndex == 0) ? attendee.getName() : attendee.getValue();
	}
}
