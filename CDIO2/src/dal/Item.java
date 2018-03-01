package dal;

public class Item {
	
	private String name;
	private int batchNo;
	private double weight;
	private int user;
	
	public Item(String name, int batchNo, double weight, int user) {
		this.name = name;
		this.batchNo = batchNo;
		this.weight = weight;
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(int batchNo) {
		this.batchNo = batchNo;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

}
