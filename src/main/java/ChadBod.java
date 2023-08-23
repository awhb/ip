package main.java;
import java.util.Scanner;
import java.util.ArrayList;


public class ChadBod {
    public static void main(String[] args) {
        System.out.println("Hello! I'm ChadBod.");
        System.out.println("What can I do for you?");
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        boolean shouldExit = false;

        while (!shouldExit) {
            String input = sc.nextLine();
            // may need try catch here
            String[] commandArray = input.split(" ", 2);
            Command command = null;
            try {
                if (commandArray.length > 0) {
                    String commandString = commandArray[0];
                    for (Command cmd : Command.values()) {
                        if (cmd.getValue().equals(commandString)) {
                            command = cmd;
                            break;
                        }
                    }
                }
                if (command == null) {
                    throw new InvalidInputException();
                }
                switch (command) {
                    case BYE:
                        System.out.println("Bye. Hope to see you again soon!");
                        shouldExit = true;
                        break;
                    case LIST:
                        if (tasks.isEmpty()) {
                            System.out.println("There are no tasks in your list!");
                        } else {
                            System.out.println("Here are the tasks in your list:");
                            for (int i = 0; i < tasks.size(); i ++) {
                                System.out.printf("%d.%s\n", i + 1, tasks.get(i));
                            }
                        }
                        break;
                    case MARK:
                        int markTaskNumber = Integer.parseInt(commandArray[1]);
                        if (markTaskNumber < 1 || markTaskNumber > tasks.size()) {
                            throw new TaskIndexOutOfBoundsException();
                        }
                        Task markedTask = tasks.get(markTaskNumber - 1);
                        markedTask.markDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.printf("%s\n", markedTask);
                        break;
                    case UNMARK:
                        int unmarkTaskNumber = Integer.parseInt(commandArray[1]);
                        if (unmarkTaskNumber < 1 || unmarkTaskNumber > tasks.size()) {
                            throw new TaskIndexOutOfBoundsException();
                        }
                        Task unmarkedTask = tasks.get(unmarkTaskNumber - 1);
                        unmarkedTask.markUndone();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.printf("%s\n", unmarkedTask);
                        break;
                    case TODO:
                        if (commandArray.length < 2 || commandArray[1].isEmpty()) {
                            throw new InvalidTaskException("Description of todo cannot be empty.");
                        }
                        Todo newTodo = new Todo(commandArray[1]);
                        tasks.add(newTodo);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(newTodo);
                        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
                        break;
                    case DEADLINE:
                        if (commandArray.length < 2 || commandArray[1].isEmpty()) {
                            throw new InvalidTaskException("Description of deadline cannot be empty.");
                        }
                        String[] deadlineDetails = commandArray[1].split(" /by ", 2);
                        if (deadlineDetails.length < 2 || deadlineDetails[1].isEmpty()) {
                            throw new InvalidTaskException("Deadline due date cannot be empty.");
                        }
                        Deadline newDeadline = new Deadline(deadlineDetails[0], deadlineDetails[1]);
                        tasks.add(newDeadline);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(newDeadline);
                        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
                        break;
                    case EVENT:
                        if (commandArray.length < 2 || commandArray[1].isEmpty()) {
                            throw new InvalidTaskException("Description of event cannot be empty.");
                        }
                        String[] eventDetails = commandArray[1].split(" /from ", 2);
                        if (eventDetails.length < 2 || eventDetails[1].isEmpty()) {
                            throw new InvalidTaskException("Event timings cannot be empty.");
                        }
                        String[] eventTimings = eventDetails[1].split(" /to ", 2);
                        if (eventTimings.length < 2 || eventTimings[1].isEmpty()) {
                            throw new InvalidTaskException("Event from and to timings cannot be empty.");
                        }
                        Event newEvent = new Event(eventDetails[0], eventTimings[0], eventTimings[1]);
                        tasks.add(newEvent);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(newEvent);
                        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
                        break;
                    case DELETE:
                        int taskNumber = Integer.parseInt(commandArray[1]);
                        if (taskNumber < 1 || taskNumber > tasks.size()) {
                            throw new TaskIndexOutOfBoundsException();
                        }
                        Task deletedTask = tasks.remove(taskNumber - 1);
                        System.out.println("Noted. I've removed this task:");
                        System.out.printf("%s\n", deletedTask);
                        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
                        break;
                    default:
                        throw new InvalidInputException();
                }
            } catch (NumberFormatException e) {
            System.out.println("☹ OOPS!!! Invalid task index.");
            } catch (ChadBodException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
