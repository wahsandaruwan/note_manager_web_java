package com.admin.notemanager.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.admin.notemanager.bean.Note;

public class NoteDao {
	// Properties
	private String jdbcURL = "jdbc:mysql://localhost:3306/note_manager_jsp";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";
	private String jdbcDriver = "com.mysql.jdbc.Driver";
	
	// All db queries
	private static final String INSERT_NOTES_SQL = "INSERT INTO notes (title, category, description, dateCreated) VALUES (?, ?, ?, ?);";
	private static final String SELECT_NOTE_BY_ID = "SELECT id, title, category, description, dateCreated FROM  notes WHERE id = ?;";
	private static final String SELECT_ALL_NOTES = "SELECT * FROM notes;";
	private static final String UPDATE_NOTE_SQL = "UPDATE notes SET title = ?, category = ?, description = ? WHERE id = ?;";
	private static final String DELETE_NOTE_SQL = "DELETE from notes WHERE id = ?;";
	
	// Default constructor
	public NoteDao() {
		
	}
	
	// Create db connection
	protected Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(jdbcDriver);
			conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	// DB operation methods
	// Insert note
	public void insertNote(Note note) throws SQLException{
		System.out.println(INSERT_NOTES_SQL);
		try(Connection conn = getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(INSERT_NOTES_SQL)){
			preparedStatement.setString(1, note.getTitle());
			preparedStatement.setString(2, note.getCategory());
			preparedStatement.setString(3, note.getDescription());
			preparedStatement.setDate(4, java.sql.Date.valueOf(java.time.LocalDate.now()));
			preparedStatement.executeUpdate();
		}catch(SQLException e) {
			printSQLException(e);
		}
	}
	
	// Select note by id
	public Note selectNote(int id) {
		Note note = null;
		try(Connection conn = getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(SELECT_NOTE_BY_ID)){
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String title = rs.getString("title");
				String category = rs.getString("category");
				String description = rs.getString("description");
				String dateCreated = rs.getString("dateCreated");
				note = new Note(id, title, category, description, dateCreated);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return note;
	}
	
	// Select all notes
	public List<Note> selectAllNotes() {
		List<Note> notes = new ArrayList<>();
		try(Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_NOTES);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String category = rs.getString("category");
				String description = rs.getString("description");
				String dateCreated = rs.getString("dateCreated");
				notes.add(new Note(id, title, category, description, dateCreated));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return notes;
	}
	
	// Update note
	public boolean updateNote(Note note) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_NOTE_SQL);) {
			System.out.println("updated Note:"+preparedStatement);
			preparedStatement.setString(1, note.getTitle());
			preparedStatement.setString(2, note.getCategory());
			preparedStatement.setString(3, note.getDescription());
			preparedStatement.setInt(4, note.getId());

			rowUpdated = preparedStatement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	// Delete note
	public boolean deleteNote(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_NOTE_SQL);) {
			preparedStatement.setInt(1, id);
			rowDeleted = preparedStatement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	// Method to print errors
	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if(e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState : " + ((SQLException) e).getSQLState());
				System.err.println("Error Code : " + ((SQLException) e).getErrorCode());
				System.err.println("Message : " + e.getMessage());
				Throwable t = ex.getCause();
				while(t != null) {
					System.err.println("Cause : " + t);
					t = t.getCause();
				}
			}
		}
	}
}


























