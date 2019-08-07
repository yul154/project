package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import GetData.GetNews;
import GetData.GetRelevance;
import GetData.GetTitle;

/**
 * Servlet implementation class GetDoc
 */
@WebServlet("/GetDoc")
public class GetDoc extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BufferedReader pageReader;
	private GetTitle getTitle;
	private GetNews getNews;
	private GetRelevance getRelevance;
       
    /**
     * @throws FileNotFoundException 
     * @see HttpServlet#HttpServlet()
     */
    public GetDoc() throws IOException {
        super();
        // TODO Auto-generated constructor stub
//        getTitle = new GetTitle();
//        getNews = new GetNews();
//        getRelevance = new GetRelevance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String docno = request.getParameter("docno").trim();
		ServletContext context = getServletContext();
		String titleIndexPath = context.getRealPath("/data/title_index.txt");
		getTitle = new GetTitle(titleIndexPath);
		getTitle.open(context.getRealPath("/data/title.txt"));
		
		getNews = new GetNews(context.getRealPath("/data/text_index.txt"));
		String title = getTitle.getTitle(docno);
		
		getNews.open(context.getRealPath("/data/result_text.txt"));
		String news = getNews.getNews(docno);
		getNews.close();
		
		getRelevance = new GetRelevance(context.getRealPath("/data/relevance.txt"));
		List<String> relevanceList = getRelevance.getRelevance(docno);
		
		pageReader = new BufferedReader(new FileReader(context.getRealPath("/news.html")));
		String line;
		StringBuilder content = new StringBuilder();
		while((line = pageReader.readLine()) != null) {
			content.append(line);
		}
		String newsPage = content.toString();
		
		for (int i = 0; i < relevanceList.size(); i++) {
			String relevance = relevanceList.get(i);
			String docnoPattern = "\\{docno" + String.valueOf(i) + "\\}";
			String titlePattern = "{title" + String.valueOf(i) + "}";
			newsPage = newsPage.replaceAll(docnoPattern, relevance);
			newsPage = newsPage.replace(titlePattern, getTitle.getTitle(relevance));
		}
		
//		System.out.println(content);
		response.setContentType("text/html");
		response.getWriter().write(newsPage.replace("{title}", title).replace("{content}", news));
		getTitle.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
