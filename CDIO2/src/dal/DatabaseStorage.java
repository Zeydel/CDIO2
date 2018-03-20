package dal;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dal.IUserDAO.DALException;
import dto.UserDTO;

public class DatabaseStorage implements IUserDAO {
	    private Connection connect = null;
	    private Statement statement = null;
	    private PreparedStatement preparedStatement = null;
	    private ResultSet resultSet = null;
	    final String url = "jdbc:mysql://localhost:3306/database?useSSL=true";
	    final String database = "database.user"; //Write in format DATABASE.TABLE, Change only here
	    final String username = "database_user"; //database user username 
	    final String password = "Climate2020";
	    
	    //simple database connection test - deprecated in released version
	    public void testConnection() {
		    System.out.println("Connecting database...");
            // Setup the connection with the DB
		    try (Connection connection = DriverManager.getConnection(url, username, password)) {
		    		connect = connection;
		        System.out.println("Database connected!");
		    } catch (SQLException e) {
		        throw new IllegalStateException("Cannot connect the database!", e);
		    }
	    }
	    
	    // Read from database
	    public void readDataBase() throws Exception {
		    	try (Connection connection = DriverManager.getConnection(url, username, password)) {
		    		connect = connection;
			        System.out.println("Database connected!");
	            // Statements allow to issue SQL queries to the database
		    		statement = connect.createStatement();
		    		// Result set get the result of the SQL query
		    		//prints table before write
	            resultSet = statement.executeQuery("select * from " + database);
	            writeResultSet(resultSet);
	            System.out.println("_____________________");
	            
//	            writeToTable();
	            
	            //Prints table after write 
//	            preparedStatement = connect.prepareStatement("SELECT name, initials, role, password, cpr from " + database);
//	            resultSet = preparedStatement.executeQuery();
//	            writeResultSet(resultSet);
		    	} catch (SQLException e) {
			        throw new IllegalStateException("Cannot connect the database!", e);   
		    } catch (Exception e) {
	            throw e;
	        } //finally {
	        //    close();
	       // }
	    }
	    
	    private void writeToTable(String name, String initials, String role, String password, int cpr) throws Exception {
	        // PreparedStatements can use variables and are more efficient
	    		// "?" can be used as placeholder for values
            preparedStatement = connect.prepareStatement("insert into " + database + " (id ,name, initials, role, password, cpr) values (?, ?, ?, ?, ?, ?)");
            // "name, initials, role, password, cpr from database.user");
            // Parameters start with 1
            preparedStatement.setString(1, "nameTest");
            preparedStatement.setString(2, "initialsTest");
            preparedStatement.setString(3, "roleTest");
            preparedStatement.setString(4, "passwordTest");
            preparedStatement.setInt(5, 12345678);
            preparedStatement.executeUpdate();

	    }

	    private void writeResultSet(ResultSet resultSet) throws SQLException {
	        // ResultSet is initially before the first data set
	        while (resultSet.next()) {
	            // It is possible to get the columns via name
	            // also possible to get the columns via the column number
	            // which starts at 1
	            // e.g. resultSet.getString(2);
	        		int id = resultSet.getInt("id");
	            String name = resultSet.getString("name");
	            String initials = resultSet.getString("initials");
	            String role = resultSet.getString("role");
	            String password = resultSet.getString("password");
	            int cpr = resultSet.getInt("cpr");
	            System.out.println("Id: " + id);
	            System.out.println("Name: " + name);
	            System.out.println("Initials: " + initials);
	            System.out.println("Role: " + role);
	            System.out.println("Password: " + password);
	            System.out.println("CPR: " + cpr);

	        }
	    }
	    
	 // You need to close the resultSet
	    private void close() {
	        try {
	            if (resultSet != null) {
	                resultSet.close();
	            }

	            if (statement != null) {
	                statement.close();
	            }

	            if (connect != null) {
	                connect.close();
	            }
	        } catch (Exception e) {

	        }
	    }
	    
	    /////////////////////
	    @Override
		public UserDTO getUser(int userId) throws DALException {
	        UserDTO u = null;
	        try(Connection connection = DriverManager.getConnection(url, username, password)) {
	        		connect = connection;
	        		System.out.println("Database connected!");
	        		statement = connect.createStatement();
				resultSet = statement.executeQuery("select * from " + database);
				
				if(!resultSet.equals(null)) {
					System.out.println("resultSet not empty");
					try {
						while (resultSet.next()) {
							if (resultSet.getInt("id") == userId) {
								int id = resultSet.getInt("id");
						        String name = resultSet.getString("name");
						        String initials = resultSet.getString("initials");
						        String role = resultSet.getString("role");
						        String password = resultSet.getString("password");
						        String cpr = resultSet.getString("cpr");
						        List<String> roles = Arrays.asList(role.split(","));
						        UserDTO user = new UserDTO(id, name, initials ,roles, password, cpr);
						        u = user;
							}
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	        } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			return u; 
		}

		@Override
		public List<UserDTO> getUserList() throws DALException {
			System.out.println("printing users..");
			List<UserDTO> users = new ArrayList<UserDTO>();
	        try(Connection connection = DriverManager.getConnection(url, username, password)) {
	        		connect = connection;
	        		System.out.println("Database connected!");
	        		statement = connect.createStatement();
				resultSet = statement.executeQuery("select * from " + database);
				
				if(!resultSet.equals(null)) {
					System.out.println("resultSet not empty");
					try {
						while (resultSet.next()) {
							int id = resultSet.getInt("id");
					        String name = resultSet.getString("name");
					        String initials = resultSet.getString("initials");
					        String role = resultSet.getString("role");
					        String password = resultSet.getString("password");
					        String cpr = resultSet.getString("cpr");
					        List<String> roles = Arrays.asList(role.split(","));
					        UserDTO user = new UserDTO(id, name, initials ,roles, password, cpr);
					        users.add(user);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	        } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			return users; 
		}

		@Override
		public void createUser(UserDTO u) throws DALException {
			try(Connection connection = DriverManager.getConnection(url, username, password)) {
        		connect = connection;
        		System.out.println("Database connected!");
        		statement = connect.createStatement();
        		String rolesCommaSeparated = String.join(",", u.getRoles());
        							
			System.out.println("trying to save to database");
			PreparedStatement createUser = connect.prepareStatement("insert into " + database + " (id, name, initials, role, password, cpr) values (?, ?, ?, ?, ?, ?)");
			createUser.setInt(1, u.getUserId() );
			createUser.setString(2, u.getUserName() );
			createUser.setString(3, u.getIni());
            createUser.setString(4, rolesCommaSeparated);
            createUser.setString(5, u.getPassword());
            createUser.setString(6, u.getCpr());
			createUser.execute();
				
	        } catch (SQLException e) {
				// TODO Auto-generated catch block
	        		System.out.println("Could not connect to database");
				e.printStackTrace();
			} finally {
				close();
			}
				
		}

		@Override
		public void updateUser(UserDTO user) throws DALException {
	        try(Connection connection = DriverManager.getConnection(url, username, password)) {
	        		connect = connection;
	        		System.out.println("Database connected!");
	        		statement = connect.createStatement();
				resultSet = statement.executeQuery("select * from " + database);
				
				if(!resultSet.equals(null)) {
					System.out.println("resultSet not empty");
					try {
						while (resultSet.next()) {
							if (resultSet.getInt("id") == user.getUserId()) {
								String rolesCommaSeparated = String.join(",", user.getRoles());
    							
								String updateTableSQL = "UPDATE " + database + " SET name = ?, initials = ?, role = ?, password = ?, cpr = ? WHERE id = " + user.getUserId();
								PreparedStatement updateUser = connect.prepareStatement(updateTableSQL);
								updateUser.setString(1, user.getUserName() );
								updateUser.setString(2, user.getIni());
								updateUser.setString(3, rolesCommaSeparated);
								updateUser.setString(4, user.getPassword());
								updateUser.setString(5, user.getCpr());
								updateUser.executeUpdate();
							}
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	        } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}

		@Override
		public void deleteUser(int userId) throws DALException {
			try(Connection connection = DriverManager.getConnection(url, username, password)) {
	        		connect = connection;
	        		System.out.println("Database connected!");
	        		statement = connect.createStatement();
				resultSet = statement.executeQuery("select * from " + database);
				
				if(!resultSet.equals(null)) {
					System.out.println("resultSet not empty");
					try {
						while (resultSet.next()) {
							if (resultSet.getInt("id") == userId) {
								
								String deleteTableSQL = "DELETE FROM " + database + " WHERE id = " + userId;
								PreparedStatement deleteUser = connect.prepareStatement(deleteTableSQL);
								
								deleteUser.executeUpdate();
							}
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	        } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
}