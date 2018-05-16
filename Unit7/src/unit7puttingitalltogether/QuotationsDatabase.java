package unit7puttingitalltogether;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;

public class QuotationsDatabase {

	public static void main(String[] args) throws IOException {
		
		String user = "";
		int userDialog = 0;
		int array = 0;
		
		String[] options = {"Display all quotes", "Display random quote", "Display selected quote", "Add a quote", "Remove a quote", "Exit"};
		
		
		
		BufferedReader quoteReader = new BufferedReader(new FileReader("C:/Users/Matt McB/git/Unit-7-Putting-it-all-together/Unit7/src/unit7puttingitalltogether/quotes.txt"));
		BufferedReader quoteReader1 = new BufferedReader(new FileReader("C:/Users/Matt McB/git/Unit-7-Putting-it-all-together/Unit7/src/unit7puttingitalltogether/quotes.txt"));
		
		while(true) {
			
			String line = quoteReader.readLine();
			
			if(line == null) {
				break;
			}
			
			else {
				array++;
			}
		}
		quoteReader.close();
		
		String[] storage = new String[array];					
		for(int i = 0; i < array; i++) {
			String line1 = quoteReader1.readLine();
			storage[i] = line1;
		}
		QuotationStorage quotes = new QuotationStorage(storage);
		
		while(userDialog != 5) {
			
			userDialog = JOptionPane.showOptionDialog(null, "Please choose an option", "Quotations Database", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
			
			// DISPLAY ALL QUOTES
			if(userDialog == 0) {
				
				System.out.println("============================================");
				System.out.println(quotes);
				System.out.println("============================================");
				
			}
			
			// DISPLAY RANDOM QUOTE
			else if(userDialog == 1) {
				int rng = (int) (Math.random() * array);
				System.out.println("============================================");
				System.out.println(quotes.storage[rng]);
				System.out.println("============================================");
				
			}
			
			// DISPLAY SELECTED QUOTE
			else if(userDialog == 2) {
				user = (String) JOptionPane.showInputDialog(null, "Which quote would you like to use?", "Quotations Database", JOptionPane.PLAIN_MESSAGE, null, quotes.storage, quotes.storage[0]);
				System.out.println("============================================");
				System.out.println(user);
				System.out.println("============================================");
			}
			
			// ADD A QUOTE
			else if(userDialog == 3) {
				
			}
			
			// REMOVE A QUOTE
			else if(userDialog == 4) {
				
			}
			
		}
		
	}

}
