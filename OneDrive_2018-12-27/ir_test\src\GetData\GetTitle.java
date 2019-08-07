package GetData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import Classes.Path;

public class GetTitle {
	private Map<String, String> title_index = new HashMap<>();
	private RandomAccessFile raf;
	public GetTitle(String path) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(path));
		String line;
		while((line = reader.readLine()) != null) {
			String[] index = line.split(":");
			title_index.put(index[0], index[1]);
		}
		reader.close();
	}
	
	public void open(String path) throws IOException {
		raf = new RandomAccessFile(path, "r");
	}
	
	public String getTitle(String docno) throws IOException{
		raf.seek(Integer.parseInt(title_index.get(docno)));
		return raf.readLine();
	}
	
	public void close() throws IOException {
		raf.close();
	}
}
