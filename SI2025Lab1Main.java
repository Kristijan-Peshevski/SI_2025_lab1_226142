import java.util.*;

enum Priority {
    LOW, MEDIUM, HIGH
}

class Task {
    private String name;
    private boolean isCompleted;
    private Priority priority;
    private String category;

    public Task(String name, Priority priority, String category) {
        this.name = name;
        this.priority = priority;
        this.category = category;
        this.isCompleted = false;
    }

    public void complete() {
        this.isCompleted = true;
    }

    public String getName() {
        return name;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public Priority getPriority() {
        return priority;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return name + " [" + category + "] - Priority: " + priority + (isCompleted ? " [Completed]" : " [Pending]");
    }
}

class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(String name, Priority priority, String category) {
        tasks.add(new Task(name, priority, category));
    }

    public void printTasks() {
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    // 7. Count tasks per category
    public Map<String, Integer> countTasksPerCategory() {
        Map<String, Integer> categoryCount = new HashMap<>();
        for (Task task : tasks) {
            categoryCount.put(task.getCategory(), categoryCount.getOrDefault(task.getCategory(), 0) + 1);
        }
        return categoryCount;
    }

    // 8. Mark a task as completed by name
    public void markTaskCompleted(String name) {
        for (Task task : tasks) {
            if (task.getName().equalsIgnoreCase(name)) {
                task.complete();
                System.out.println("Task '" + name + "' marked as completed.");
                return;
            }
        }
        System.out.println("Task '" + name + "' not found.");
    }

    // 9. Mark all tasks in a category as completed
    public void markCategoryCompleted(String category) {
        boolean found = false;
        for (Task task : tasks) {
            if (task.getCategory().equalsIgnoreCase(category)) {
                task.complete();
                found = true;
            }
        }
        if (found) {
            System.out.println("All tasks in category '" + category + "' marked as completed.");
        } else {
            System.out.println("No tasks found in category '" + category + "'.");
        }
    }
}

public class SI2025Lab1Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        manager.addTask("Write report", Priority.HIGH, "Work");
        manager.addTask("Submit assignment", Priority.MEDIUM, "School");
        manager.addTask("Buy groceries", Priority.LOW, "Personal");
        manager.addTask("Finish project", Priority.HIGH, "Work");
        manager.addTask("Exercise", Priority.MEDIUM, "Personal");

        // Print all tasks
        manager.printTasks();

        // Count and display tasks per category
        System.out.println("\nTask count per category:");
        Map<String, Integer> categoryCounts = manager.countTasksPerCategory();
        for (Map.Entry<String, Integer> entry : categoryCounts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Mark a task as completed
        System.out.println("\nMarking 'Submit assignment' as completed...");
        manager.markTaskCompleted("Submit assignment");

        // Mark all tasks in a category as completed
        System.out.println("\nMarking all 'Work' tasks as completed...");
        manager.markCategoryCompleted("Work");

        // Print tasks again to verify changes
        System.out.println("\nUpdated Task List:");
        manager.printTasks();
    }
}
