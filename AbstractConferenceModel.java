import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class AbstractConferenceModel {
	protected ArrayList<Attendee> attendeeList;

	protected abstract void update();

	public AbstractConferenceModel() {
		attendeeList = new ArrayList<Attendee>();
	}

	public void readFromFile(File fileName) throws FileNotFoundException {
		Scanner scr = new Scanner(fileName);
		scr.useDelimiter("[]\\n");

		attendeeList = new ArrayList<Attendee>();
		while (scr.hasNext()) {
			String line = scr.next();
			System.out.println(line);
			attendeeList.add(parseAttendees(line));
		}
		scr.close();
		update();
	}

	public void writeToFile(File fileName) throws IOException {
		FileWriter wtr = new FileWriter(fileName);
		PrintWriter print = new PrintWriter(wtr, true);

		for (Attendee attendee : attendeeList) {
			print.println(attendee.to$());
		}
		wtr.close();
		print.close();
	}
	
	public void exportToFile(File fileName) throws IOException {
		FileWriter wtr = new FileWriter(fileName);
		PrintWriter print = new PrintWriter(wtr, true);

		for (Attendee attendee : attendeeList) {
			print.println(attendee.toCsv()); /* change this */
		}
		print.close();
	}

	private Attendee parseAttendees(String line) {
		String[] fields = line.split("/0");
		
		for (int i=0; i<fields.length; i++)
		{
			System.out.println(fields[i]);
		}
		
		double value = (fields.length > 1) ? Double.parseDouble(fields[1]) : 0;
		
		return new Attendee(fields[0], value, fields[2]);
	}

	public ArrayList<Attendee> getAttendeeList() {
		return attendeeList;
	}

	public Attendee getAttendee(int index) {
		return attendeeList.get(index);
	}

	public void addCountry(Attendee att) {
		attendeeList.add(att);
		update();
	}

	public void deleteCountry(Attendee att) {
		attendeeList.remove(att);
		update();
	}
}
