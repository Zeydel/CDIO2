package test;
import java.util.ArrayList;
import java.util.List;

import dal.DatabaseStorage;
import dal.IUserDAO;
import dal.IUserDAO.DALException;
import dal.PersistentStorage;
import dto.UserDTO;
import ful.UserFunction;
import tui.TUI;

public class DBTester {
	DatabaseStorage dbStorage = new DatabaseStorage();
	//PersistentStorage storage = new PersistentStorage("savedData");
	public static void main(String[] args) {
		DBTester test = new DBTester();
		test.run();
	}

	public void run() {
		DBTester test = new DBTester();
		
		List<String> list = new ArrayList<String>();
		list.add("update test role");
		UserDTO user = new UserDTO(14, "sam", "ss", list, "Climate2121", "0911964321");
		try {

			System.out.println("Updating user");
			test.dbStorage.createUser(user);
			
			System.out.println("Getting ready to print users...");
			for ( UserDTO u : test.dbStorage.getUserList()) {
			System.out.println(u);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
	
	private static void printUsers(IUserDAO iDAO) {
		try {
			System.out.println("Printing users...");
			List<UserDTO> userList = iDAO.getUserList();
			for (UserDTO userDTO : userList) {
				System.out.println(userDTO);
			}

		} catch (DALException e) {
			e.printStackTrace();
		}
	}
	
}
