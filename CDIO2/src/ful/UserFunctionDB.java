package ful;

import java.util.ArrayList;
import java.util.List;

import dal.DatabaseStorage;
import dal.IUserDAO;
import dal.IUserDAO.DALException;
import dal.PersistentStorage;
import dto.UserDTO;

public class UserFunctionDB {

	DatabaseStorage DBStorage = new DatabaseStorage();

	public UserFunctionDB(DatabaseStorage DBStorage) throws Exception {
		try {
			DBStorage.readDataBase();
		} catch (DALException e) {
		}
		this.DBStorage = DBStorage;

	}

	public void addUser(int userId, String userName, List<String> roles, String password, String cpr) throws DALException {

		DBStorage.createUser(new UserDTO(userId, userName, roles, password, cpr));
		DBStorage.
	}

	public void editName(int userId, String newName) throws DALException {
		for(int i = 0; i < DBStorage.getUserList().size(); i++) {
			if (DBStorage.getUserList().get(i).getUserId() == userId) {
				DBStorage.getUserList().get(i).setUserName(newName);
				DBStorage.updateUser(DBStorage.getUserList().get(i));
				DBStorage.saveTofile();
			}
		} throw new DALException("User with ID " + userId + " not found.");
	}

	public void editPassword(int userId, String newPassword) throws DALException {
		for(int i = 0; i < DBStorage.getUserList().size(); i++) {
			if (DBStorage.getUserList().get(i).getUserId() == userId) {
				DBStorage.getUserList().get(i).setPassword(newPassword);
				DBStorage.updateUser(DBStorage.getUserList().get(i));
				DBStorage.saveTofile();
			}
		}  throw new DALException("User with ID " + userId + " not found.");
	}

	public void editRoles(int userId, ArrayList<String> newRoles) throws DALException {
		UserDTO currentUser = null;
		for(int i = 0; i < DBStorage.getUserList().size()-1; i++) {
			if (DBStorage.getUserList().get(i).getUserId() == userId) {
				currentUser = DBStorage.getUserList().get(i);
				currentUser.setRoles(newRoles);
			}
		}
		if (currentUser != null) {
			DBStorage.updateUser(currentUser);
			DBStorage.saveTofile();
		}  else throw new DALException("User with ID " + userId + " not found.");

	}

	public UserDTO findUser(int userId) {
		try {
			return DBStorage.getUser(userId);
		} catch (DALException e) {
			return null;
		}
	}

	public List<UserDTO> getUserList() throws DALException{
		return DBStorage.getUserList();
	}

	public void deleteUser(int userId) throws DALException {
		DBStorage.deleteUser(userId);
	}

	public boolean asserIfIdExists(int userId) {
		try {
			for(int i = 0; i < DBStorage.getUserList().size(); i++) {
				if(DBStorage.getUserList().get(i).getUserId() == userId) {
					return true;
				}
			} 
		} catch (DALException e) {
			return false;
		} return false;
	}

	public boolean assertPasswordQuality(String password) {
		if(password.length() < 6) {
			return false;
		}
		int smallLetterCount = 0;
		for(int i = 0; i < password.length(); i++) {
			if(Character.isLowerCase(password.charAt(i))) {
				smallLetterCount++;
			}
		} int bigLetterCount = 0;
		for(int i = 0; i < password.length(); i++) {
			if(Character.isUpperCase(password.charAt(i))) {
				bigLetterCount++;
			}
		}  int numberCount = 0;
		for(int i = 0; i < password.length(); i++) {
			if(Character.isDigit(password.charAt(i))){
				numberCount++;
			}
		} int specialCount = 0;
		for(int i = 0; i < password.length(); i++) {
			switch(password.charAt(i)) {
			case '.' : specialCount++;
			break;
			case '-' : specialCount++;
			break;
			case '_' : specialCount++;
			break;
			case '+' : specialCount++;
			break;
			case '!' : specialCount++;
			break;
			case '?' : specialCount++;
			break;
			case '=' : specialCount++;
			break;
			}
		}
		int count = 0;
		if(smallLetterCount != 0) {
			count++;
		} if(bigLetterCount != 0) {
			count++;
		} if(numberCount != 0) {
			count++;
		} if(specialCount != 0) {
			count ++;
		}

		if(count < 3) {
			return false;
		}
		if(password.length() != smallLetterCount + bigLetterCount + numberCount + specialCount) {
			return false;
		}

		return true;
	}
}