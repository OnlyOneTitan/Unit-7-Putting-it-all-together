package unit7puttingitalltogether;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

import javax.swing.JOptionPane;

public class QuotationsDatabase {

	public static void main(String[] args) throws IOException {
		LocalDate date = LocalDate.now();
		
		int userDialog = 0;
		String[] options = {"Quote of the day", "Select a quote", "Exit"};
		String[] storage = new String[2];
		
		String dateConvert = date.toString().substring(8, 10);
		
		BufferedReader quoteReader = new BufferedReader(new FileReader("C:/Users/Matt McB/git/Unit-7-Putting-it-all-together/Unit7/src/unit7puttingitalltogether/quotes.txt"));
		
		while(userDialog != 2) {
			userDialog = JOptionPane.showOptionDialog(null, "Please choose an option", "Quotations Database", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
			if(userDialog == 0) {
				while(true) {
					String line = quoteReader.readLine();
					if(line == null) {
						break;
					}
					else {
						System.out.println(line);
					}
				}
			}
		}
		
		quoteReader.close();
	}

}
