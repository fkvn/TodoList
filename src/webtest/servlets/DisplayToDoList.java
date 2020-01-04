package webtest.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webtest.models.TodoList;

/**
 * Servlet implementation class homepage
 */
@WebServlet(urlPatterns="/DisplayToDoList", loadOnStartup = 1)
public class DisplayToDoList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayToDoList() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		
		int id = 1;
		
		ArrayList<TodoList> todos = new ArrayList<>();
		todos.add(new TodoList("kevin", id++, false));
		todos.add(new TodoList("ngo", id++, true));
		
		getServletContext().setAttribute("todos", todos);
		getServletContext().setAttribute("id", id);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		@SuppressWarnings("unchecked")
		ArrayList<TodoList> todos = (ArrayList<TodoList>) request.getServletContext().getAttribute("todos");
		
		out.println("<html><head><title>Lab 10</title></head><body>");
		
		out.println("<h1>To do List: </h1>");
		out.println("<table width='400' style='border-collapse: collapse'>");
		out.println("<tr> <th style='border:1px solid black; width:10px'> # </th>"
					+ "<th style='border:1px solid black'> Name </th>"
					+ "<th style='border:1px solid black'> Complete </th>"
					+ "<th style='border:1px solid black'> Operation </th>");
		for (int i = 0; i < todos.size(); i++) {
			out.println("<tr>"
						+ "<td align='center' style='border:1px solid black'>"
						+ i + "</td>"
						+ "<td align='center' style='border:1px solid black'>" 
						+ todos.get(i).getName() + "</td>");
			out.println("<td align='center' style='border:1px solid black'>"); 
			if (todos.get(i).isDone())
			{
				out.println("<input type='radio' name='complete" + todos.get(i).getId() + "' value='Done' checked/>Done");
				out.println("<input type='radio' name='complete" + todos.get(i).getId() + "' value='Not Done' disabled/>Not Done ");
			}
			else
			{
				out.println("<input type='radio' name='complete" + todos.get(i).getId() + "' value='Done' disabled />Done");
				out.println("<input type='radio' name='complete" + todos.get(i).getId() + "' value='Not Done' checked/>Not Done");
			}
			out.println("</td> <td align='center' style='border:1px solid black'>"
					+ "<a href='EditToDoList?id=" + todos.get(i).getId() + "' style='text-decoration:none'> Edit </a> | "
					+ "<a href='DeleteToDoList?id=" + todos.get(i).getId() + "' style='text-decoration:none'> Delete </a>");
			out.println("</td></tr>");
		}
		out.println("</table>");
		
		out.println("<hr><div> The number of <em> incompleted </em> todos: " 
				+ (todos.size() - TodoList.correctCounts(todos))
				+ "</div><br>");
		
		out.println("<div> The number of <em> completed </em> todos: " 
				+ "<em>" + TodoList.correctCounts(todos) + "</em>"
				+ "</div><br>");
		
		out.println("<div> The total number of <em>all</em> todos: " 
				+ todos.size()
				+ "</div><hr>");
		
		out.println("<button><a href='AddToDoList' style='text-decoration:none'> Add a Todo </a></button>");
		out.println("<button><a href='DeleteAllCompleted' style='text-decoration:none'>Delete All completed todos </a></button>");
		
		out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
