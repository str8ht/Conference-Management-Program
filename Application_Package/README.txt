	
	          C O N F E R E N C E  M A N A G E M E N T  P R O G R A M 

	           Version 1.0.2 | All Rights Reserved | Copyright © 2017	

-----------------------------------------------------------------------------------------------------------------------------------

1. Description & Features
2. Purpose
3. User's Manual
4. Warnings & Limitations

-----------------------------------------------------------------------------------------------------------------------------------

1. Description & Features

The Conference Management Program is designed to package all the necessary tools 
for a director into one easy-to-use application. Utilizing graphical user inference (GUI),
the application has a series of buttons, lists, and timers that can be used to replace
more traditional methods of directing a conferene (i.e. paper/pencil, word processors,
phone timers, etc.) The Conference Management Program is free, and includes a number of
convenient functionalities, such as:

	- A timer for both speaking times and caucus times
	- Indicators that turn on when the timers are near expiration
	- A table that records both those in attendence and the score they are awarded
	- A notes section that allows entries on individual statements.
	- A find functionality to quickly lookup individuals in attendence.
	- A list for recording the order of the speakers (speaker's list).The order can 
	  be changed based on who has and hasn't spoken.
	- Convenient file storage that backs-up all information recorded in the lists/ 
	  notes to a .MUNe file
	- An export-to-excel functionality that saves list information as a comma
	  separated value (csv) file for later reference.
	- A list of motions, following Robert's Rules of order, which can be used to 
	  autopopulate the individual timers.

The conference curriculum application is an organizational tool that can help conference
directors do their job with greater ease.



2. Purpose

The director general of a local conference expressed to me his concern over the disorgan-
-ized nature of directing, and was wondering if a program could be made that packages all 
the necessarily tools for directing into one application. Some of the more specific
concerns included the fact that directors would often resort to writing their speaker's
lists down on a piece of paper or on a chalk board, only to be lost or erased later on. 
Directors were also having to resort to separate phone and computer applications for the
two timers, which was inconvenient and drained battery life on their devices. Moreover,
it was a constant challenge for directors to keep track of motions, in order of
disruptiveness, and be consistant with Robert's Rules of Order. The Conference Management
Program puts all of these tools into one location, allowing for ease of use, ease of
access, and ease of mind.



3. User's Manual

The conference curriculum application can be opened via a runnable .jar file. This .jar
file can be downloaded by the zip folder that this text file is located in. Once
downloaded, the .jar file works like any other standard application (though there may be
a few compatibility issues for Mac and Linux users). As explained, the application has two
timers, one for caucus time and one for speaking time. It also has two lists, one for 
those in attendence (and their current score) as well as one for the speaker's list. The
information recorded in the attendence list is saved to a file called 
"ConferenceData.csv". In the event that the program is exit prematurely, data can be
found in this file. Additionally, upon reopening of the application, you will find that 
the AttendenceList has been populated with the information from the csv file (granted
that the location or contents of the csv file has not be tempered with).

This back-up method ensures that data is not lost in the event that a computer dies or
the application is closed. To prevent premature closure of the application, a pop-up
asks the user if they are sure they want to close the application. This is another added
layer of protection for the data. 

The application is coded such that is can be minimized or the size can be changed. If it
is getting in the way of other applications, it can simply be moved, minimized or
resized. 

To add time to the timers, select one of the buttons with pre-set intervals. The timers
count down to 0:00, and can be stopped at any point while they are running. At preset
intervals (1 minute, 30 seconds, and 1 second) the indicators will flash with warning
colors (yellow, orange, and red respectively). 



4. Warnings & Limitations

There are a few things that this application does not allow. The application do not allow
time of over 59:59 (an hour) due to the way in which the algorithm was written. It is not
necessary for the timers to ever have to go this high because caucus or speaking times 
never need to be this long in Model UN.

Additionally, do not try to press the start button while the timer is currently running.
This will have no affect on the timer, as it will remain running and a pop-up will be 
display reinforcing this point. Likewise, if the timer is at 0:00 or is stopped already,
pressing the stop button will do nothing. There will be no pop-up letting you know this 
(though is should be blatantly obvious that pressing stop on an already stopped timer
doesn't do anything).

Lastly, do not enter a non-numeric number as the score for an attendee. The program is 
written to account for numeric numbers only. So, for example, entering in "three" instead
of 3 will result in an error pop-up that tells the user to enter only numeric numbers.


-----------------------------------------------------------------------------------------------------------------------------------

	          C O N F E R E N C E  M A N A G E M E N T  P R O G R A M 

	           Version 1.0.2 | All Rights Reserved | Copyright © 2017

-----------------------------------------------------------------------------------------------------------------------------------


