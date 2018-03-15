package dto;

public class ItemDTO {
	
	int BatchNr;
	int weight;
	int userID;
	String name;
	
	public ItemDTO(int BatchNr, String name, int weight, int userID) {
		this.BatchNr = BatchNr;
		this.weight = weight;
		this.userID = userID;
		this.name = name;
	}
	
	public String toString() {
		return String.format("BatchID:%-4d Name:%-20s Weight:%-6d UserID:%-2d", BatchNr, name, weight, userID);
	}

	public int getBatchNr() {
		return BatchNr;
	}

	public void setBatchNr(int batchNr) {
		BatchNr = batchNr;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	

}
