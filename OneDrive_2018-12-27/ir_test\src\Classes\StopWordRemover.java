package Classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class StopWordRemover {
	private HashSet<String> stopwords_list = new HashSet<String>();

	public StopWordRemover(String path) throws IOException {
		// load and store the stop words from the fileinputstream with appropriate data structure
		// that you believe is suitable for matching stop words.
		// address of stopword.txt should be Path.StopwordDir
		
		// read the stopword.text file from the fileinputstream
		BufferedReader br = new BufferedReader(new FileReader(path));
		String line;
		
		// read the file, if the line is not null, add it to Stopword set; 
		// if the line is null, stop reading and close the file  
		while ((line = br.readLine()) != null) {
			stopwords_list.add(line);
		}
		br.close();
	}
	
	// YOU MUST IMPLEMENT THIS METHOD
	public boolean isStopword( String word ) {
		// return true if the input word is a stopword, or false if not
		// if Stop set contains this word return true;
		// if not, return false
		if(stopwords_list.contains(word)){
			return true;
		}
		else{
			return false;
		}
	}
}
