
public class Attendee implements Comparable<Attendee>
{
	private final double initialValue;
	private final String name;
	private double value;
	private String profile;
	
	public Attendee(String name, double value)
	{
		this.name = (name == null) ? "__" : name;
		this.value = value;
		this.initialValue = value;
		profile = getDefaultProfile();
	}
	
	public Attendee(String name, double value, String profile)
	{
		this.name = (name == null) ? "__" : name;
		this.value = value;
		this.initialValue = value;
		this.profile = profile;
	}
	
	public String getName()
	{
		return name;
	}
	
	public double getValue()
	{
		return value;
	}
	
	public String getProfile()
	{
		return profile;
	}
	
	public void saveProfileText(String text)
	{
		this.profile = text;
	}
	
	public void addToValue(double aggre)
	{
		value += aggre;
	}
	
	public void reset()
	{
		value = initialValue;
	}
	
	public String toString()
	{
		return name;
	}
	
	public boolean startsWith(String prefix)
	{
		return name.startsWith(prefix);
	}
	
	public int compareTo(Attendee obj)
	{
		return name.compareTo(obj.name);
	}
	
	public String toCsv()
	{
		return name + "," + value;
	}
	
	public String to$()
	{
		return name + "/0" + value + "/0" + profile + "[]";
	}
	
	public String getDefaultProfile()
	{
		String result = "Position: " + name;
		if (name.length() <= 35)
		{
			for (int i=name.length(); i<55; i++)
			{
				result = result + " ";
			}
		}
		return result + "----------------- Notes -----------------";
	}
}
