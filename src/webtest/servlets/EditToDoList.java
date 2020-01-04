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
 * Servlet implementation class EditToDoList
 */
@WebServlet("/EditToDoList")
public class EditToDoList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditToDoList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		@SuppressWarnings("unchecked")
		ArrayList<TodoList> todos = (ArrayList<TodoList>) request.getServletContext().getAttribute("todos");
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		int index = TodoList.getIndex(todos, id);
		
		out.println("<html><head><title>Lab 10</title></head><body>");
		
		out.println("<h1>Edit to do List: </h1>");
		out.println("<form action='EditToDoList' method='POST'>"); 
		out.println("<table width='300' style='border-collapse: collapse'>");
		out.println("<tr>"
					+ "<td style='border:1px solid black'> <strong> Name </strong> </td>"
					+ "<td align='center' style='border:1px solid black'>"
					+ "<input type='text' name='name' value='" + todos.get(index).getName() + "'/> </td>");
		
		out.println("<tr>"
				+ "<td style='border:1px solid black'><strong> Complete</strong> </td>"
				+ "<td align='center' style='border:1px solid black'>");
		if (todos.get(index).isDone())
		{
			out.println("<input type='radio' name='complete' value='Done' checked/>Done");
			out.println("<input type='radio' name='complete' value='Not Done'/>Not Done");
		}
		else {
			out.println("<input type='radio' name='complete' value='Done'/>Done");
			out.println("<input type='radio' name='complete' value='Not Done' checked/>Not Done");
		}
		out.println("<input type='hidden' name='id' value='" + todos.get(index).getId() +"'/>");
		out.println("</td></tr>");
		out.println("</table>");
		out.println("<input type='submit' name='submit' value='Save'>");
		out.println("</form>");
		out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		@SuppressWarnings("unchecked")
		ArrayList<TodoList> todos = (ArrayList<TodoList>) request.getServletContext().getAttribute("todos");
		
		if (request.getParameter("name") == null || request.getParameter("name").trim().equals("") 
				|| request.getParameter("id") == null 
				|| Integer.parseInt(request.getParameter("id").trim()) < 0 )
		{
			response.sendRedirect("AddToDoList");
		}
		
		else {
			int id = Integer.parseInt(request.getParameter("id"));
			int index = TodoList.getIndex(todos, id);
			
			todos.get(index).setName(request.getParameter("name"));
			if (request.getParameter("complete").equals("Done"))
				todos.get(index).setDone(true);
			else
				todos.get(index).setDone(false);
			response.sendRedirect("DisplayToDoList");
		}
		
	
	}

}
