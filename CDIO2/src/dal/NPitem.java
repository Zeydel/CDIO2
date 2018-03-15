package dal;

import java.util.ArrayList;
import java.util.Arrays;

import dal.IUserDAO.DALException;
import dto.ItemDTO;
import dto.UserDTO;

public class NPitem {
	
	ArrayList<ItemDTO> items = new ArrayList<ItemDTO>();
	public NPitem() {
				items.add(new ItemDTO(1234, "Salt", 4, 12));
	}
	
	public ArrayList<ItemDTO> getItems(){
		return items;
	}
	
	public void addItem(ItemDTO item) {
		items.add(item);
	}

	public void updateItem(ItemDTO item) throws DALException {
		for (int i = 0; i< items.size();i++) {
			if (items.get(i).getBatchNr() == item.getBatchNr()) {
				items.set(i, item);
				return;
			}
		}	throw new DALException("Item with id " + item.getBatchNr() + " not found.");
	}

	public void deleteItem(int BatchNr) throws DALException {
		for (int i = 0; i<=items.size();i++) {
			if (items.get(i).getBatchNr() == BatchNr) {
				items.remove(i);
				return;
			}
		} 	throw new DALException("Item with id " + BatchNr + " not found.");
	}
}
