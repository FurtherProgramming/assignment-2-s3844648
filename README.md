#To run the program:
To run the program. right click on Main.java and choose Run.

# Design:

DESIGN DECISIONS:

An MVC design pattern was used in this project. Code that communicates with the database were sorted into the 'model' directory.
files relating to the employee interface such as FXML and CSS files were sorted in the 'ui' directory.
Code that handles employee input from ui and uses it to call methods from the Model were sorted into the 'controller' directory.

Singleton pattern was used to create a class which stores variables so they could be easily accessed across multiple stages.

An abstract class was used for User, this abstract class is used for the two types of users: Employee and Admin who both have some similar methods.

DATABASE:

the database consisted of the following tables:

Employee: holds data on employees login details, secret questions/answers and whether their account is active or deactivated.

Booking: holds the data for bookings i.e employee who booked and which date and desk they booked.

Desk: just hold whether the desk has been locked by an admin.

REFACTORING:

The employee table in the database was refactored to support additional features. For instance, the 'answer' and 'question'
columns were added for the reset password features while the 'activated' column allows admins to activate/deactivate employees.

login.fxml, UserModel and User were also modified to implement features such as registering, resetting passwords and logging in as admin

User was made into an abstract class which the employee and admin class extends.