package webtest.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webtest.models.TodoList;


/**
 * Servlet implementation class AddToDoList
 */
@WebServlet("/AddToDoList")
public class AddToDoList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToDoList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		out.println("<html><head><title>Lab 10</title></head><body>");
		
		out.println("<h1>Add to do List: </h1>");
		out.println("<form action='AddToDoList' method='POSt'>"); 
		out.println("<table width='300' style='border-collapse: collapse'>");
		out.println("<tr>"
					+ "<td style='border:1px solid black'> <strong> Name </strong> </td>"
					+ "<td align='center' style='border:1px solid black'>"
					+ "<input type='text' name='name'/> </td>");
		
		out.println("<tr>"
				+ "<td style='border:1px solid black'><strong> Complete</strong> </td>"
				+ "<td align='center' style='border:1px solid black'>"
				+ "<input type='radio' name='complete' value='Done'/>Done"
				+ "<input type='radio' name='complete' value='Not Done' checked/>Not Done"
				+ "</td></tr>");
		out.println("</table>");
		out.println("<input type='submit' name='submit' value='Add'>");
		out.println("</form>");
		out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		@SuppressWarnings("unchecked")
		ArrayList<TodoList> todos = (ArrayList<TodoList>) request.getServletContext().getAttribute("todos");
		
		int id = (int) request.getServletContext().getAttribute("id");
		
		if (request.getParameter("name") == null || request.getParameter("name").trim().equals(""))
		{
			response.sendRedirect("AddToDoList");
		}
		else
		{
			
			if (request.getParameter("complete").equals("Done"))
				todos.add(new TodoList(request.getParameter("name"), id++, true));
			else
				todos.add(new TodoList(request.getParameter("name"), id++, false));
			
			getServletContext().setAttribute("id", id);
			
			response.sendRedirect("DisplayToDoList");
		}
		
	}

}
