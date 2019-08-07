package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Classes.Document;
import Classes.Query;
import Classes.Stemmer;
import Classes.StopWordRemover;
import GetData.GetTitle;
import IndexingLucene.MyIndexReader;
import SearchLucene.QueryRetrievalModel;

/**
 * Servlet implementation class GetQuery
 */
@WebServlet("/GetQuery")
public class GetQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StopWordRemover stopwordRemover;
	private GetTitle getTitle;
       
    /**
     * @throws IOException 
     * @see HttpServlet#HttpServlet()
     */
    public GetQuery() throws IOException {
        super();
//        ServletContext context = getServletContext();
//        String stopwordPath = getServletContext().getRealPath("/data/stopword.txt");
//        // TODO Auto-generated constructor stub
//        stopwordRemover = new StopWordRemover(stopwordPath);
//        getTitle = new GetTitle();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		ServletContext context = getServletContext();
		
		String stopwordPath = context.getRealPath("/data/stopword.txt");
		String titleIndexPath = context.getRealPath("/data/title_index.txt");
		
		stopwordRemover = new StopWordRemover(stopwordPath);
        getTitle = new GetTitle(titleIndexPath);
		
		String query = request.getParameter("query").trim().toLowerCase();
		
		StringTokenizer st = new StringTokenizer(query);
		String processed_query = "";
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			if (!stopwordRemover.isStopword(token)) {
				Stemmer s = new Stemmer();
				char[] word= token.toCharArray();
				s.add(word, word.length);
				s.stem();
				processed_query = processed_query + s.toString() + " ";
			}
			
		}
		String indexPath = context.getRealPath("/data/indextext/");
		MyIndexReader ixreader = new MyIndexReader(indexPath);
		// Initialize the MyRetrievalModel
		SearchLucene.QueryRetrievalModel model = new QueryRetrievalModel(ixreader, indexPath);
		// Extract the queries
		
		Query aQuery = new Query();
		aQuery.SetQueryContent(processed_query);
		
		List<Document> results = new ArrayList<>();;
		try {
			results = model.retrieveQuery(aQuery, 30);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String text = "";
		getTitle.open(context.getRealPath("/data/title.txt"));
		if (results != null) {
			for (Document result : results) {
				text = text + getTitle.getTitle(result.docno()) + "#" + result.docno() +";";
			}
		}
		getTitle.close();
		ixreader.close();
		response.setContentType("text/plain");
		response.getWriter().write(text);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
