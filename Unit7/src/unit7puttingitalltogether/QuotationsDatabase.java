package unit7puttingitalltogether;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import javax.swing.JOptionPane;

public class QuotationsDatabase {

	public static void main(String[] args) throws IOException {
		
		LocalDate date = LocalDate.now();
		String dateConvert = date.toString().substring(8, 10);
		
		int userDialog = 0;
		int array = 0;
		
		String[] options = {"Quote of the day", "Select a quote", "Exit"};
		
		
		BufferedReader quoteReader = new BufferedReader(new FileReader("C:/Users/Matt McB/git/Unit-7-Putting-it-all-together/Unit7/src/unit7puttingitalltogether/quotes.txt"));
		BufferedReader quoteReader1 = new BufferedReader(new FileReader("C:/Users/Matt McB/git/Unit-7-Putting-it-all-together/Unit7/src/unit7puttingitalltogether/quotes.txt"));
		
		while(userDialog != 2) {
			
			userDialog = JOptionPane.showOptionDialog(null, "Please choose an option", "Quotations Database", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
			
			if(userDialog == 0) {
				
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
				System.out.println(quotes);
				
				
			}
		}
		
	}

}
