package GetData;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Classes.Path;

public class GetRelevance {
	
	private Map<String, List> relevanceMap;
	
	public GetRelevance(String path) throws IOException {
		relevanceMap = new HashMap<>();
		BufferedReader reader = new BufferedReader(new FileReader(path));
		String line = "";
		while ((line = reader.readLine()) != null) {
			String[] semiSplit = line.split(":");
			String docno = semiSplit[0];
			String[] commaSplit = semiSplit[1].split(", ");
			List<String> docList = new ArrayList<String>();
			
			for (String doc : commaSplit) {
				if (docList.size() >= 5) break;
				docList.add(doc);
			}
			relevanceMap.put(docno, docList);
		}
		reader.close();
	}
	
	public List<String> getRelevance(String docno){
		return relevanceMap.get(docno);
	}
}
