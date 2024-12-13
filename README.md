# Balmes_Final_Project_OOP_JavAskWhatToDo
Welcome to Balmes_Final_Project in OOP entitled "JavAskWhatToDO?" crafted by Katrine Angeleen Balmes. This repository is a guidance which involves README file that stands ready to narrate the project's overview and its align SDG's. 
## Overview
JavAsk What To-Do?  is a console-based task management application built in Java. It allows users to add personal and work-related tasks, view tasks by date, mark tasks as completed, and remove tasks. The application implements basic Object-Oriented Programming principles and is designed to help users stay organized. The program uses fundamental Object-Oriented Programming (OOP) principles, including Encapsulation, Abstraction, Inheritance, and Polymorphism, to ensure scalability, maintainability, and ease of use.
## OOP Principles Applied
## 1. Encapsulation
Task details are encapsulated within the `Task` class, and access is provided via getter and setter methods. The Class Task is in private accessed and modified through public methods to ensure controlled access.
## 2. Abstraction
In the console based system it provides a blueprint for task behavior, while subclasses implement specific functionality.The `Task` class provides an abstract structure, while `PersonalTask` and `WorkTask` implement specific behaviors.
## 3. Inheritance
PersonalTask and WorkTask inherit common fields and methods from Task, reducing code duplication.
## 4. Polymorphism
In showTaskDetails() is overridden in subclasses, allowing the program to call the appropriate method based on the task type at runtime.
## Sustainable Development Goals 
# SDG 4: Quality Education
"JavAsk What To-Do?" aligns with the principles of SDG 4 by promoting effective organizational skills crucial for educational success. This Java-based application helps users manage their tasks efficiently, thereby enhancing productivity and learning outcomes. By integrating Object-Oriented Programming principles, it serves as a practical educational tool for those learning Java. Its user-friendly design makes it accessible for students and educators alike, facilitating better educational experiences.
## SDG 8: Decent Work and Economic Growth
"JavAsk What To-Do?" supports the aims of SDG 8 by improving individual task management, which is essential for professional productivity. The application enables users to efficiently handle work-related tasks, contributing to a more organized and productive workforce. This increased productivity can lead to economic growth by improving job performance and satisfaction. Moreover, by assisting in balancing personal and professional tasks, the app supports healthier work-life balance, promoting sustainable and decent work environments.
## Instruction in Running the Program
*Before running the program make sure the JDK version 8 or higher is installed on your device. 
## Step-by-Step Functionality:
## 1. Program Initialization:
   Welcome Page which says "Welcome to JavAskWhaToDO?" together with the choices between 1 to 7
## 2. User Interaction:
   Presented with options (Add Task, View Tasks, Mark as Complete, Remove Task, etc.).
   User selects an action by entering a number. (Between 1 - 7)
## Function for Choices
## Choice 1: Adding Personal Task 
- The user will add description of their task then also date with following the format YYYY-MM-DD
## Choice 2: Adding Work Task 
- This function enables users to add a new work-related task to their to-do list. Upon selection,       it prompts the user to input the specifics of the work task also they will choose if it is High/Medium/Low Priority. Then they will also put date on which they preffered to do the task.
## Choice 3: View Tasks for a Specific Day
- This option allows users to view all tasks scheduled for a specific day. When chosen, it asks the user to specify the date they want to review, providing a focused view of tasks due on that particular day.
## Choice 4: Mark Task as Completed 
- This function lets users mark a task as completed. After selecting this option, users can choose which task from their list they have finished, updating the task's status to completed.
## Choice 5: Remove Task 
- This option enables users to remove a task from their to-do list. Upon selection, it presents the user the date which the use should input the date also the description of task they want to remov.
## Choice 6 
-  View All Tasks: This function allows users to view all the tasks in their to-do list. Selecting this option provides a comprehensive overview of all tasks, both pending and completed, facilitating overall task management.
## Choice 7 
-Exit: This option allows users to exit the application. When selected, it closes the application, ensuring that users can easily terminate their session when they have finished managing their tasks.
## End of the Program
