
package unit7puttingitalltogether;

import java.io.*;
import java.util.*;
import javax.swing.*;

public class QuotationsDatabase {

    public static void main(String[] args) throws IOException {

    	// Initializing variables
        boolean exit;
        String user = "";
        String authName = "";
        String quoteInput = "";
        String removeInput;
        ArrayList<String> authors = new ArrayList();
        ArrayList<String> storage = new ArrayList();
        int userDialog = 0;
        
        // Used to find the location of the text file. This allows the program to read the text file from any computer.
        String location = System.getProperty("user.dir") + "\\src\\unit7puttingitalltogether\\quotes2.txt";
        // For the option dialog
        String[] options = {"Display all quotes", "Display random quote", "Display selected quote", "Search by Author","Add a quote", "Remove a quote", "Exit"};
        
        // Initializes the readers/writers
        BufferedReader quoteReader1 = null;
        PrintWriter fileWriter1 = null;
        //error check to make sure file in given directory exists
        try{
            quoteReader1 = new BufferedReader(new FileReader(location));
        }
        catch(FileNotFoundException e)
        {
        	
            JOptionPane.showMessageDialog(null, "File not found");
            System.exit(0);
        }	
        //Fills the storage and authors array list with respective info
        while(true)
        {
            //line reader
            String line1 = quoteReader1.readLine();
            //if line is null (end of file), exits loop
            if (line1 == null)
            {
                break;
            }
            else{
                //temp variable to dettermine first letter in a line
                String charAt = Character.toString(line1.charAt(0));
                //if the first letter is - (representing an author, adds said 
                //line to authors arraylist
                if (charAt.equalsIgnoreCase("-")){
                    authors.add(line1.substring(2, line1.length()));
                }
                //else, the line must be a quote so it adds it to storage arraylist
                else{
                    storage.add(line1);
                }
            }
        }
        //closes reader
        quoteReader1.close();
        //intitalized constructor to modify the quotations array (add, remove, etc)
        QuotationStorage quotes = new QuotationStorage(storage, authors);
        //if user clicks exit, user dialog will output 6, therefore the program exits
        while(userDialog != 6) {
            //main menu
            userDialog = JOptionPane.showOptionDialog(null, "Please choose an option", "Quotations Database", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);

            // DISPLAY ALL QUOTES
            if(userDialog == 0) {

                    JOptionPane.showMessageDialog(null, quotes, "Quotations Database", JOptionPane.PLAIN_MESSAGE);
                    
            }

            // DISPLAY RANDOM QUOTE
            else if(userDialog == 1) {
                //if file is empty, storage.size = 0, therefore this option wont work
                //error guard to prevent user from using this function
                if (quotes.storage.size() <= 0)
                {
                    JOptionPane.showMessageDialog(null, "Error!\nThere are no quotes to randomly display!");
                }
                else
                {
                    //rng variable stores random index value to choose a random quote
                    int rng = (int) (Math.random() * quotes.storage.size());
                    JOptionPane.showMessageDialog(null, quotes.storage.get(rng), "Quotations Database", JOptionPane.PLAIN_MESSAGE);
                }

            }

            // DISPLAY SELECTED QUOTE
            else if(userDialog == 2) {
                if (quotes.storage.size() <= 0)
                {
                    JOptionPane.showMessageDialog(null, "Error!\nThere are no quotes to select!", "Quotations Database", JOptionPane.PLAIN_MESSAGE);
                }
                else
                {
                    user = (String) JOptionPane.showInputDialog(null, "Which quote would you like to use?\nClick \"cancel\" to return to the main menu.", "Quotations Database", JOptionPane.PLAIN_MESSAGE, null, quotes.storage.toArray(), quotes.storage.get(0));
                    if (user != null){
                        JOptionPane.showMessageDialog(null, user, "Quotations Database", JOptionPane.PLAIN_MESSAGE);

                    }
                }
            }
            //SEARCH BY AUTHOR
            else if (userDialog == 3)
            {
            	// Checks the storage to see if there are any quotes. If there is, it goes to the else statement, where a drop down menu interface is displayed. If there is nothing inside he storage, all you would get is a message saying that there is nothing inside.
                if (quotes.storage.size() <= 0)
                {
                    JOptionPane.showMessageDialog(null, "Error!\nThere are no authors to select!", "Quotations Database", JOptionPane.PLAIN_MESSAGE);
                }
                else
                    {
                    //generates list of authors for users to select from
                    quotes.authorIndex();
                    //user input
                    authName = (String) JOptionPane.showInputDialog(null, "Please select the author of the quotes you are looking for.\nClick \"cancel\" to return to the main menu.", "Quotations Database", JOptionPane.PLAIN_MESSAGE, null, quotes.authIndex.toArray(), quotes.authIndex.get(0));
                    //gets list of quotes by the specified author
                    if (authName != null)
                    {
                    quotes.search(authName);
                    }
                }

            }
            // ADD A QUOTE
            else if(userDialog == 4) {
            	// Gives exit a boolean of false. Used in the while loop.
                exit = false;
                // Loop: Asks the user for input of a quote. The inputs cannot equal nothing. If the user clicks cancel, it will return the user to the main menu. Otherwise it would break and enter the next loop.
                while (exit == false)
                {
                    quoteInput = JOptionPane.showInputDialog("Please input the quote you would like to add.\nClick \"cancel\" to return to the main menu.");
                    if ( quoteInput == null)
                    {
                        exit = true;
                    }
                    else if (quoteInput.equalsIgnoreCase(""))
                    {
                        JOptionPane.showMessageDialog(null,"Error! this field cannot be BLANK!");
                    }
                    else
                    {
                        break;
                    }
                }
                // Loop: Asks the user for input of an author. The inputs cannot equal nothing. If the user clicks cancel, it will return the user to the main menu. Otherwise it would break and go to the next part where it adds the quotes to the text file..
                while (exit == false)
                {
                   authName = JOptionPane.showInputDialog("Please input the author of the quote you would like to add.\nClick \"cancel\" to return to the main menu.");
                   if (authName == null)
                    {
                        exit = true;
                    }
                    else if (authName.equalsIgnoreCase(""))
                    {
                        JOptionPane.showMessageDialog(null,"Error! this field cannot be BLANK!");
                    }
                    else
                    {
                        break;
                    }
                }
                if (exit == false)
                {
                    quotes.addQuote(authName, quoteInput);
                }
            }

            // REMOVE A QUOTE
            else if(userDialog == 5) {
            	// Checks if there is 0 quotes inside, if there is, the user does not have to remove anything. Otherwise the program will give the user a drop down menu of the quote. If the user selects ok on that quote, then the program will remove the quote.
                if (quotes.storage.size() <= 0)
                {
                    JOptionPane.showMessageDialog(null, "Error!\nThere are no quotes to remove!");
                }
                else
                {
                    removeInput =(String)JOptionPane.showInputDialog(null, "Which quote would you like to use?\nClick \"cancel\" to return to the main menu.", "Quotations Database", JOptionPane.PLAIN_MESSAGE, null, quotes.storage.toArray(), quotes.storage.get(0));
                    if (removeInput != null){
                        quotes.removeQuote(removeInput);
                    }
                }
            }
            else if(userDialog == 6 || userDialog == -1) {
            	break;
            }
        }
        // Creates a new writer for the text file.
        fileWriter1 = new PrintWriter(new FileWriter(location));
        //counter for quotes, stops loop when there are no more quotes to add
        for (int j = 0; j < quotes.storage.size(); j++)
        {
            //adds a quote from quote arraylist
            fileWriter1.println(quotes.storage.get(j));
            //then adds its author from the author arraylist
            fileWriter1.println("- "+quotes.authorList.get(j));
        }
        //closes filewriter
        fileWriter1.close();
        //exits program
        System.exit(0);
    }
}