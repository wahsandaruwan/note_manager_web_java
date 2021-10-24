package com.admin.notemanager.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.notemanager.bean.Note;
import com.admin.notemanager.dao.NoteDao;

/**
 * Servlet implementation class NoteServlet
 */
@WebServlet("/")
public class NoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// Note dao instance
	private NoteDao noteDao =  new NoteDao();

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);	
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		// Handle endpoints
		String action = request.getServletPath();
		switch(action) {
			case "/new":
				try {
					showNewForm(request, response);
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case "/insert":
				try {
					insertNote(request, response);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case "/delete":
				try {
					deleteNote(request, response);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case "/edit":
				try {
					showEditForm(request, response);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case "/update":
				try {
					updateNote(request, response);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			default:
				try {
					listNotes(request, response);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
		}
	}
	
	// Default action
	private void listNotes(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		List<Note> listNotes = noteDao.selectAllNotes();
		request.setAttribute("listNotes", listNotes);
		RequestDispatcher dispatcher = request.getRequestDispatcher("note-list.jsp");
		dispatcher.forward(request, response);
	}
	
	// Show new form
	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("note-form.jsp");
		dispatcher.forward(request, response);
	}
	
	// Show edit form
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Note existingUser = noteDao.selectNote(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("note-form.jsp");
		request.setAttribute("note", existingUser);
		dispatcher.forward(request, response);
	}
	
	// Insert new note
	private void insertNote(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String title = request.getParameter("title");
		String category = request.getParameter("category");
		String description = request.getParameter("description");
		Note newNote = new Note(title, category, description);
		noteDao.insertNote(newNote);
		response.sendRedirect("list");
	}
	
	// Update note
	private void updateNote(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String category = request.getParameter("category");
		String description = request.getParameter("description");

		Note note = new Note(id, title, category, description);
		noteDao.updateNote(note);
		response.sendRedirect("list");
	}
	
	// Delete note
	private void deleteNote(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		noteDao.deleteNote(id);
		response.sendRedirect("list");
	}

}







