package GetData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

import Classes.Path;

public class GetNews {
	private Map<String, String> news_index = new HashMap<>();
	private RandomAccessFile raf;
	public GetNews(String path) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(path));
		String line;
		while((line = reader.readLine()) != null) {
			String[] index = line.split(":");
			news_index.put(index[0], index[1]);
		}
		reader.close();
	}
	
	public void open(String path) throws IOException {
		raf = new RandomAccessFile(path, "r");
	}
	
	public String getNews(String docno) throws IOException{
		raf.seek(Integer.parseInt(news_index.get(docno)));
		return raf.readLine();
	}
	
	public void close() throws IOException {
		raf.close();
	}
}
