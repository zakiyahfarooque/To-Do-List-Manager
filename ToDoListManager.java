import java.util.*;
import java.io.*;

public class TodoListManager {
    public static final boolean EXTENSION_FLAG = true;

    public static void main(String[] args) throws FileNotFoundException {
       
        Scanner console = new Scanner(System.in);

        List<String> todo = new ArrayList<>();

        String choice = "";

        System.out.println("Welcome to your TODO List Manager!");

        while(!choice.equalsIgnoreCase("Q")) {
            System.out.println("What would you like to do?");
            System.out.print("(A)dd TODO, (AM)Add Multiple Items, (M)ark TODO as done," + 
                "(L)oad TODOs, (S)ave TODOs, (Q)uit? ");

            choice = console.nextLine();
        
            if (choice.equalsIgnoreCase("A")) {
                addItem(console, todo);
            } else if (choice.equalsIgnoreCase("AM")) {
                addMultipleItems(console, todo);
            } else if (choice.equalsIgnoreCase("M")) {
                markItemAsDone(console, todo);
            } else if (choice.equalsIgnoreCase("L")) {
                loadItems(console, todo);

            } else if (choice.equalsIgnoreCase("S")) {
                saveItems(console, todo);

            } else if (choice.equalsIgnoreCase("Q")) {
                
            } else {
                System.out.println("Unknown input: " + choice);
                printTodos(todo);
                
            }

        }

    }

    // Pre: A list of TODO items.
    // Post: Outputs the list of TODOs to the console. If the list is empty, indicates there 
    //       are no TODO items.
    public static void printTodos(List<String> todos) {
        System.out.println("Today's TODOs:");

        if (todos.size() == 0) {
            System.out.println("  You have nothing to do yet today! Relax!");
        } else {
            for (int i = 0; i < todos.size(); i++) {
                System.out.println("  " + (i + 1) + ": " + todos.get(i));
            }
        }
    }

    // Pre: Scanner for user input and a list of TODO items.
    // Post: Prompts the user for a TODO item to add and adds it to the list. Can add to a
    //      specified position or at the end.
    public static void addItem(Scanner console, List<String> todos) {
        // TODO: Your Code Here
        System.out.print("What would you like to add? ");
        String add = console.nextLine();
        if(todos.isEmpty()) {
            todos.add(add);
            printTodos(todos);
        } else {
            System.out.print("Where in the list should it be (1-" + 
                            (todos.size() + 1) + ")? (Enter for end): ");
            String addInput = console.nextLine();
            if (addInput.isEmpty()) {
                todos.add(add);
                printTodos(todos);
            } else {
                int positionInput = Integer.parseInt(addInput);
                todos.add(positionInput - 1, add);
                printTodos(todos);
            }
            
        }
        
    }

    // Pre: Scanner for user input and a list of TODO items.
    // Post: Allows the user to add multiple TODO items until they type "done". 
    //      Outputs the updated TODO list.
    public static void addMultipleItems(Scanner console, List<String> todos) {
        System.out.println("Add multiple items to your To-Do List! Type 'done' " +
                        "when you are finished adding items."); 

        String userInput = console.nextLine();

        while (!userInput.equalsIgnoreCase("done")) {
            if (!userInput.isEmpty()) {
                todos.add(userInput);
            }

            userInput = console.nextLine();

        }

        System.out.println("Added multiple to-do items to your list.");
        printTodos(todos);
    }

    // Pre: Scanner for user input and a list of TODO items.
    // Post: Asks the user which item they have completed, removes it from the list, and 
        //outputs the updated list. Checks if the list is empty.
    public static void markItemAsDone(Scanner console, List<String> todos) {
        
        if (todos.size() == 0) {
            System.out.println("All done! Nothing left to mark as done!");
            printTodos(todos);
        } else {
            System.out.print("Which item did you complete (1-" + todos.size() + ")? ");
            String completed = console.nextLine();
            int completedNum = Integer.parseInt(completed);
            todos.remove(completedNum - 1);
            printTodos(todos);

        }

    }

     // Pre: Scanner for user input, a list of TODO items, and a valid filename.
    // Post: Saves the current list of TODO items to a specified file. 
    // Throws `FileNotFoundException` if the file cannot be created or written.
    public static void saveItems(Scanner console, List<String> todos)
                            throws FileNotFoundException {
        
        System.out.println("File name? ");
        String fileName = console.nextLine();
        File outFile = new File(fileName);
        PrintStream out = new PrintStream(outFile);

        for (int i = 0; i < todos.size(); i++) {
            out.println(todos.get(i));
        }
        printTodos(todos);
    }

    // Pre: Scanner for user input and a valid filename for loading TODO items.
    // Post: Clears the existing list, loads TODO items from a specified file, and 
    //      outputs the updated TODO list. Throws `FileNotFoundException` if the file is not found.
    public static void loadItems(Scanner console, List<String> todos)
                                throws FileNotFoundException {

        System.out.println("File name?");
        String fileName = console.nextLine();
        File outFile = new File(fileName);
        Scanner fileScan = new Scanner(outFile);

        todos.clear();

        while(fileScan.hasNextLine()) {
            String item = fileScan.nextLine();
            todos.add(item);
        }

        printTodos(todos);

    }

}
