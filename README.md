# CSE360_Tu37
for CSE360 project
Prototypes from Submission 4:
	Prototype 1: consits of 3 classes. Effort_Entry and Defect_Entry classes have only attributes and no methods. They are used to create objects in the Project class to build the Project log. Project has two methods that instantiate new objects of the Effort_Entry and Defect_Entry class with corresponding data given a certain event happens (i.e. a button is pressed). Those objects are then stored into two arraylists for referencing. One for defects and the other for efforts. 

	Prototype 2: consists of a **Main class, UserInfo class,** and **LoginAuditEntry class** with attributes **failedLoginAttempts,** which is an int representing the count of failed login times,  **lastFailedLoginTime,** which is a long value representing the timestamp of failed attempts, and **user** which is an observable list for handling user data. 

	Prototype 3: consists of a ObjectSaver class which has methods to take in a serializable class object and save it to a new file. and a method to read from said file and return the object. It also has three "dummy" classes, Project, Login, PlanningPoker which are placeholder items used for testing.
*update* the ObjectSaver class now encrypts the object data before saving it to a file so it can't be read as a file.

	Prototype 4: consists of an Authorization and User class which have methods that establish roles and persmissions for a create user. These classes allow and prevent certain users from accessing, editting, deleting, or updating information important to a project. 

Combined_oldPrototype: Combination of the the prototypes from submission 5

PlanningPokerFlow: Java Class containing the basic steps needed to run through one item of planning poker.

EffortLogger_SQL: It is just what was in the SQL.zip file

EffortLogger_current: it is the zip file of the same name that was uploaded to the discord on 11/26 by alma with changes that partially introduce the effort editor window.