# CSE360_Tu37
for CSE360 project

Prototype 1 consits of 3 classes. Effort_Entry and Defect_Entry classes have only attributes and no methods. They are used to create objects in the Project class to build the Project log. Project has two methods that instantiate new objects of the Effort_Entry and Defect_Entry class with corresponding data given a certain event happens (i.e. a button is pressed). Those objects are then stored into two arraylists for referencing. One for defects and the other for efforts. 

Prototype 3 consists of a ObjectSaver class which has methods to take in a serializable class object and save it to a new file. and a method to read from said file and return the object. It also has three "dummy" classes, Project, Login, PlanningPoker which are placeholder items used for testing.
