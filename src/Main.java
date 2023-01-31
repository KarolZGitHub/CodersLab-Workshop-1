import org.apache.commons.lang3.ArrayUtils;
import pl.coderslab.ConsoleColors;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean check = false;
       while(!check){ File options = new File("/home/karol/Pulpit/pierwszy/src/tasks.csv");
        Scanner scannerFromFile = null;
        try {                                                                                           //Checking if file exists.
            scannerFromFile = new Scanner(options);
        } catch (FileNotFoundException e) {
            System.out.println("No file.");
        }

        System.out.println(ConsoleColors.BLUE+"Please select an option:"+ConsoleColors.RESET);

        String[] choice = {"add","list","remove","quit"};
        for (String words:
            choice) {                                       // Printing menu.
           System.out.println(words);
       }
           System.out.print("Enter an option: ");
           Scanner scannerConsole= new Scanner(System.in);
           String choose = scannerConsole.nextLine();     // Getting data from user.
      switch (choose) {
         case "list":
             while (scannerFromFile.hasNextLine()){
                 String firstTaskString = scannerFromFile.nextLine();
                 String [] splitedTaskString = firstTaskString.split("\n");
                 String [][] lastSplitedTaskString = new String[splitedTaskString.length][];   // While type "list"
                 for (int i = 0; i < splitedTaskString.length; i++) {                          // Program showing all
                     lastSplitedTaskString[i] = splitedTaskString[i].split("\n");        // tasks
                     for (String word :
                             lastSplitedTaskString[i]) {
                         System.out.println(word);
                     }
                 }
             }
              break;
           case "add":
               try {
                   addiction();
               } catch (FileNotFoundException e) {    // Case when user types "add", adding task.
                   System.out.println("No file.");
               }break;
              case "quit":{
                  System.out.println(ConsoleColors.RED+"Bye bye !:)"+ConsoleColors.RESET); // When user types "quit"
                  check=true;                                                              // closing the program.
             break;
           } case "remove":{
                  removal();   // When user types "remove" program will remove task chosen by user.
           } default:
        }
       }
    }
    public static void addiction() throws FileNotFoundException {   //Method adding a task.

        File options = new File("/home/karol/Pulpit/pierwszy/src/tasks.csv");       //Check if file exists
        Scanner scannerFromConsole = new Scanner(System.in);
        System.out.print("Please enter task name: ");
        String task = scannerFromConsole.nextLine();                                         // Collecting data from user.
        System.out.print("Please enter a year: ");
        while (!scannerFromConsole.hasNextInt()){
            System.out.print("Please enter correct a correct number: ");
            scannerFromConsole.nextLine();
        }
        String year = scannerFromConsole.nextLine();
        System.out.print("Please enter a month: ");                                         // Collecting data from user.
        while (!scannerFromConsole.hasNextInt()){
            System.out.print("Please enter correct a correct number: ");
            scannerFromConsole.nextLine();
        }
        String month = scannerFromConsole.nextLine();
        System.out.print("Please enter a day: ");
        while (!scannerFromConsole.hasNextInt()){
            System.out.print("Please enter correct a correct number: ");                    // Collecting data from user.
            scannerFromConsole.nextLine();
        }
        String day = scannerFromConsole.nextLine();
        System.out.println("Is this task important ? true/false");
        String important ="";
        while (true){
            String second = scannerFromConsole.nextLine();
            if (!second.equalsIgnoreCase("true")&&!second.equalsIgnoreCase("false" )){
                System.out.print("Please enter True or False: ");
            }else if(second.equalsIgnoreCase("true")) {                         // Collecting data from user.
                important = second;
                break;
            }else if(second.equalsIgnoreCase("false")){
                important=second;
                break;
            }
        }

        String view = task+ ","+" "+year+"-"+month+"-"+day+","+" "+important;
        System.out.println("Added "+view);
        try (FileWriter fileWriter = new FileWriter(options, true)) {       //Adding task to file.
            fileWriter.append("\n"+view);
        } catch (IOException e) {
            System.out.println("No file");
        }

    }public static void removal(){                                                  //Removing task from file.
        File options = new File("/home/karol/Pulpit/pierwszy/src/tasks.csv");
        Scanner scannerFromFile = null;                                                     // Checking if file exists.
        try {
            scannerFromFile = new Scanner(options);
        } catch (FileNotFoundException e) {
            System.out.println("No file.");
        }
        StringBuilder sb = new StringBuilder();

        while (scannerFromFile.hasNextLine()){
            String read = scannerFromFile.nextLine();
            sb.append(read).append("\n");

        }
        String tasksFromFile = sb.toString();
        String[] taskArray = tasksFromFile.split("\n");
        System.out.print("Select number of task line to delete: ");       // Collecting data from user.
        Scanner scannerConsole = new Scanner(System.in);
        while (!scannerConsole.hasNextInt()){
            System.out.print("Please enter a correct number: ");
            scannerConsole.nextLine();
        }
        int decision = scannerConsole.nextInt();
        String[]arrayWithoutArgument = ArrayUtils.remove(taskArray, decision-1);   // Deleting task chosen by user.
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(options);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        StringBuilder transfer = new StringBuilder();
        for (String word:
                arrayWithoutArgument) {
            transfer.append(word).append("\n");                                 // Writing edited data to file.
        }
        String toFile=transfer.toString();
        printWriter.write(toFile);
        printWriter.close();
    }

}



