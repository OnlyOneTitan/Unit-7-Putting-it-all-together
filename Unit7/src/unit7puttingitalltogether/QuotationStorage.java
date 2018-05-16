package unit7puttingitalltogether;

public class QuotationStorage {
	
	String[] storage;
	
	public QuotationStorage(String[] storage_) {
		storage = storage_;
	}
	
	public String toString() {
		String output = "";
		for(int i = 0; i < storage.length; i++) {
			output += storage[i] + "\n";
		}
		return output;
	}
}
