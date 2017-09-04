import javax.swing.JTable;

public class AttendenceListModel extends AbstractConferenceModel
{
	private JTable table;
	
	public void setTable(JTable table)
	{
		this.table = table;
	}
	
	@Override
	protected void update()
	{
		if (table != null)
		{
			table.setModel(new AttendeeTableModel(this));
		}
	}
}
