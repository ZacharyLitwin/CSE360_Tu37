# CSE360_Tu37
for CSE360 project

EffortLogger_Alma: it is the zip file of the same name that Alma uploaded to the discord on 11/26

EffortLogger_Zachary: Partially adds the Effort Editor screen. The starting screen was changed to be the Effort editor screen for testing. Finished javaFX with Scene Builder. I changed how the entries are defined in SQL.
They now have data DATE, startTime TIME, stopTime TIME, lifeCycles, Categories, and details. This is shown in the entriesSQL.png. There are now functions that allow for the insertion and deletions of entries in mySQL.
Updated the effort log screen to more closely match the one in efforlogger v1, You can now create effort logs in the effort log screen.
TODO- The life cycle steps are still not project dependent and there is no login info added to the entries or the entry editor

EffortLogger_Karryl: Please explain your additions to effortlogger here

EffortLogger_Trevor: Please explain your additions to effortlogger here

EffortLogger_Ishan: Screen starts from login screen. Instructions on how to create the defects table: create table defects (defectsID int, projectName varchar(50), defectsName varchar(100), defectsDef varchar(250), defectsInjected varchar(50), defectsRemoved varchar(50), defectCategory varchar(50), fix varchar(50), status varchar(10));
allows to create, update, delete, open, reopen defect entrys. 
Table to be created in "definitions" database. 
Please update your password in the database.java file. 


Prototypes from Submission 4:

Prototype 1: consits of 3 classes. Effort_Entry and Defect_Entry classes have only attributes and no methods. They are used to create objects in the Project class to build the Project log. Project has two methods that instantiate new objects of the Effort_Entry and Defect_Entry class with corresponding data given a certain event happens (i.e. a button is pressed). Those objects are then stored into two arraylists for referencing. One for defects and the other for efforts. 

Prototype 2: consists of a **Main class, UserInfo class,** and **LoginAuditEntry class** with attributes **failedLoginAttempts,** which is an int representing the count of failed login times,  **lastFailedLoginTime,** which is a long value representing the timestamp of failed attempts, and **user** which is an observable list for handling user data. 

Prototype 3: consists of a ObjectSaver class which has methods to take in a serializable class object and save it to a new file. and a method to read from said file and return the object. It also has three "dummy" classes, Project, Login, PlanningPoker which are placeholder items used for testing. update* the ObjectSaver class now encrypts the object data before saving it to a file so it can't be read as a file.

Prototype 4: consists of an Authorization and User class which have methods that establish roles and persmissions for a create user. These classes allow and prevent certain users from accessing, editting, deleting, or updating information important to a project. 

Combined_oldPrototype: Combination of the the prototypes from submission 5

PlanningPokerFlow: Java Class containing the basic steps needed to run through one item of planning poker.

EffortLogger_SQL: It is just what was in the SQL.zip file. not sure what is was
