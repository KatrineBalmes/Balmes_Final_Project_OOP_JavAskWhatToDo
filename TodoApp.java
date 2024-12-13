import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

abstract class Task {
    //Encapsulation
    private final String description;
    private boolean isCompleted;


    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public String getDescription() {
        return description;
    }
    public boolean isCompleted() {
        return isCompleted;
    }

    public void markComplete() {
        isCompleted = true;
    }

    public void markIncomplete() {
        isCompleted = false;
    }
//Abstraction
    public abstract void showTaskDetails();
}
//Inheritance
class PersonalTask extends Task {
    private final String dueDate;

    // Constructor
    public PersonalTask(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    // Display task details
    @Override
    public void showTaskDetails() {
        System.out.println("\033[1;34m******************************************************************************************");
        System.out.println("Personal Task: " + getDescription() + " | Due Date: " + dueDate + " | Status: " + (isCompleted() ? "Completed" : "Pending"));
        System.out.println("*******************************************************************************************\033[0m");
    }
}

class WorkTask extends Task {
    private final String priority;
    private final String date; // Add the date property

    public WorkTask(String description, String priority, String date) {
        super(description);
        this.priority = priority;
        this.date = date;
    }

    @Override
    public void showTaskDetails() {
        System.out.println("\033[1;34m********************************************************************************************");
        System.out.println("Work Task: " + getDescription() + " | Priority: " + priority + " | Date: " + date + " | Status: " + (isCompleted() ? "Completed" : "Pending"));
        System.out.println("*********************************************************************************************\033[0m");
    }


    public String getDate() {
        return date;
    }
}

class TaskManager {
    private final Map<String, List<Task>> tasks;

    public TaskManager() {
        tasks = new HashMap<>();
    }

    public void addTask(String date, Task task) {
        tasks.putIfAbsent(date, new ArrayList<>());
        tasks.get(date).add(task);
    }


    public void removeTask(String date, Task task) {
        List<Task> taskList = tasks.get(date);
        if (taskList != null) {
            taskList.remove(task);
        }
    }

    public void displayTasks(String date) {
        List<Task> taskList = tasks.get(date);
        if (taskList != null && !taskList.isEmpty()) {
            System.out.println("\033[1;35m********************************************");
            System.out.println("Tasks for " + date + ":");
            for (Task task : taskList) {
                System.out.println("\033[1;34m********************************************");
                task.showTaskDetails();
                System.out.println("********************************************\033[0m");
            }
        } else {
            System.out.println("\033[1;35mNo tasks for " + date + "\033[0m");
        }
    }

    public void markTaskComplete(String date, Task task) {
        List<Task> taskList = tasks.get(date);
        if (taskList != null && taskList.contains(task)) {
            task.markComplete();
            System.out.println("\033[1;34mTask marked as completed!\033[0m");
        } else {
            System.out.println("\033[1;35mTask not found for the specified date.\033[0m");
        }
    }

    public List<Task> getTasksForDate(String date) {
        return tasks.getOrDefault(date, new ArrayList<>());
    }


    public void displayAllTasks() {
        boolean hasTasks = false;
        System.out.println("\033[1;35m********************************************");
        System.out.println("All Tasks:");
        for (Map.Entry<String, List<Task>> entry : tasks.entrySet()) {
            for (Task task : entry.getValue()) {
                task.showTaskDetails();  // Each task's details include the date
                hasTasks = true;
            }
        }
        if (!hasTasks) {
            System.out.println("\033[1;35mNo tasks entered.\033[0m");
        }
        System.out.println("********************************************\033[0m");
    }

    public static boolean isValidDate(String date) {
        String regex = "^\\d{4}-\\d{2}-\\d{2}$";
        if (!date.matches(regex)) {
            return false;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}

public class TodoApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final TaskManager taskManager = new TaskManager();

    public static void main(String[] args) {
        displayWelcomePage();

        while (true) {
            showMainMenu();
            int choice = getValidChoice();
            
            System.out.println("\nYou selected option: " + choice);

            switch (choice) {
                case 1 -> addPersonalTask();
                case 2 -> addWorkTask();
                case 3 -> viewTasksForDay();
                case 4 -> markTaskAsCompleted();
                case 5 -> removeTask();
                case 6 -> viewAllTasks(); // New option to view all tasks
                case 7 -> {
                    System.out.println("\033[1;34mThank you for using this system, Don't forget to JavAsk What To-Do?\033[0m");
                    System.exit(0);
                }
                default -> System.out.println("\033[1;35mInvalid choice. Please try again.\033[0m");
            }

            System.out.print("\033[1;34mPress Enter to return to the main menu... \033[0m");
            scanner.nextLine();  // Wait for the user to press Enter before continuing
        }
    }

    private static void showMainMenu() {
        System.out.println("\n\033[1;34m********************************************");
        System.out.println("        --- JavAsk What To-Do? ---");
        System.out.println("********************************************");
        System.out.println("\033[1;35m1. Add Personal Task");
        System.out.println("2. Add Work Task");
        System.out.println("3. View Tasks for a Specific Day");
        System.out.println("4. Mark Task as Completed");
        System.out.println("5. Remove Task");
        System.out.println("6. View All Tasks"); 
        System.out.println("7. Exit\033[0m");
        System.out.print("\033[1;34m********************************************\nChoose an option: \033[0m");
    }

    private static int getValidChoice() {
        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice < 1 || choice > 7) {
                    System.out.println("\033[1;35mPlease enter a valid option between 1 and 7.\033[0m");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("\033[1;35mInvalid input. Please enter a number between 1 and 7.\033[0m");
            }
        }
        return choice;
    }

    private static void addPersonalTask() {
        String description;
        String dueDate;
        clearScreen();
        printPopupHeader("\t\tAdd Personal Task");
        while (true) {
            System.out.print("\033[1;34mEnter task description: \033[0m");
            description = scanner.nextLine();
            System.out.print("\033[1;34mEnter due date (YYYY-MM-DD): \033[0m");
            dueDate = scanner.nextLine();
            
            if (TaskManager.isValidDate(dueDate)) {
                break; 
            } else {
                System.out.println("\033[1;35mInvalid date format. Please use YYYY-MM-DD.\033[0m");
            }
        }

        PersonalTask personalTask = new PersonalTask(description, dueDate);
        System.out.println("\033[1;34mPersonal Task added!\033[0m");
        System.out.print("\033[1;34mEnter the date to add the task (YYYY-MM-DD): \033[0m");
        String taskDate = scanner.nextLine();
        taskManager.addTask(taskDate, personalTask);
    }

    private static void addWorkTask() {
        String description;
        String priority;
        String taskDate; 
        clearScreen();
        printPopupHeader("\t\tAdd Work Task");
    
        System.out.print("\033[1;34mEnter task description: \033[0m");
        description = scanner.nextLine();
        System.out.print("\033[1;34mEnter priority (High/Medium/Low): \033[0m");
        priority = scanner.nextLine();
    
        while (true) {
            System.out.print("\033[1;34mEnter the date (YYYY-MM-DD): \033[0m");
            taskDate = scanner.nextLine();
            if (TaskManager.isValidDate(taskDate)) {
                break; // Exit loop if date is valid
            } else {
                System.out.println("\033[1;35mInvalid date format. Please use YYYY-MM-DD.\033[0m");
            }
        }
    
        WorkTask workTask = new WorkTask(description, priority, taskDate);
        System.out.println("\033[1;34mWork Task added!\033[0m");
        taskManager.addTask(taskDate, workTask);
    }
    private static void viewTasksForDay() {
        System.out.print("\033[1;34mEnter the date (YYYY-MM-DD) to view tasks: \033[0m");
        String date = scanner.nextLine();
        taskManager.displayTasks(date);
    }

    private static void markTaskAsCompleted() {
        System.out.print("\033[1;34mEnter the date (YYYY-MM-DD) of the task to mark as completed: \033[0m");
        String date = scanner.nextLine();
        List<Task> taskList = taskManager.getTasksForDate(date);
        if (taskList.isEmpty()) {
            System.out.println("\033[1;35mNo tasks found for " + date + "\033[0m");
            return;
        }
        taskManager.displayTasks(date);

        System.out.print("\033[1;34mEnter task description to mark as completed: \033[0m");
        String taskDescription = scanner.nextLine();
        taskList.stream()
                .filter(task -> task.getDescription().equalsIgnoreCase(taskDescription))
                .findFirst()
                .ifPresent(task -> {
                    taskManager.markTaskComplete(date, task);
                    System.out.println("\033[1;34mTask marked as completed!\033[0m");
                });
    }

    private static void removeTask() {
        System.out.print("\033[1;34mEnter the date (YYYY-MM-DD) of the task to remove: \033[0m");
        String date = scanner.nextLine();
        List<Task> taskList = taskManager.getTasksForDate(date);
        if (taskList.isEmpty()) {
            System.out.println("\033[1;35mNo tasks found for " + date + "\033[0m");
            return;
        }
        taskManager.displayTasks(date);

        System.out.print("\033[1;34mEnter task description to remove: \033[0m");
        String taskDescription = scanner.nextLine();
        taskList.stream()
                .filter(task -> task.getDescription().equalsIgnoreCase(taskDescription))
                .findFirst()
                .ifPresent(task -> {
                    taskManager.removeTask(date, task);
                    System.out.println("\033[1;34mTask removed successfully!\033[0m");
                });
    }

    private static void viewAllTasks() {
        taskManager.displayAllTasks();
    }

    private static void displayWelcomePage() {
        System.out.println("\n\033[1;33m********************************************");
        System.out.println("        Welcome to JavAsk What To-Do?");
        System.out.println("********************************************\033[0m");
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void printPopupHeader(String header) {
        System.out.println("\033[1;33m********************************************");
        System.out.println(header);
        System.out.println("********************************************\033[0m");
    }
}