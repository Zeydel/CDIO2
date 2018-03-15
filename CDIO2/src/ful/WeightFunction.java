package ful;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.omg.DynamicAny.DynAnyFactoryPackage.InconsistentTypeCodeHelper;

import dal.NPitem;
import dal.WeightSim;
import dto.ItemDTO;

public class WeightFunction {


	Socket pingSocket = null;
	PrintWriter out = null;
	BufferedReader in = null;
	static NPitem storage = new NPitem();

	public WeightFunction(NPitem storage) {
		try {
			this.pingSocket = new Socket("127.0.0.1", 8000);
			this.out = new PrintWriter(pingSocket.getOutputStream(), true);
			this.in = new BufferedReader(new InputStreamReader(pingSocket.getInputStream()));
		} catch(IOException e) {
			System.out.println("fail");
		}

		this.storage = storage;
	}

	public static void main(String[] args) {
		WeightFunction test = new WeightFunction(storage);
		try {
			//System.out.println(test.EnterNumber("test"));
			test.writeALotOfText("TEXT");
		} catch (IOException e) {
			System.out.println("failed");
		}
	}


	//Reads weight from weightsim and returns it as an int
	public int getWeight() throws IOException {
		try {
			out.println("S ctrl");
			String str = in.readLine().replaceAll("\\D+","");
			int h = Integer.parseInt(str);
			return h;
		} catch(IOException e) {
			System.out.println("Det gik galt");
			return 0;
		}
	}

	//Tares the weightsim
	public void WeightTare() throws IOException {

		out.println("T \r\n");
		System.out.println("Weight has been tared");
		in.readLine();
		in.readLine();

	}
	


	//Used to return to weight display after a String has been displayed
	public void ReturnToWeightDisplay() throws IOException {
		out.println("DW crlf");
		in.readLine();
	}

	//Writes a message in the display 
	public void WriteTextInDisplay(String text) throws IOException {
		
		out.println("D \"" + text + "\" \r\n");
		
		resetInputStream();
		
		
		while(!in.ready()) {
		}

	} 

	public void writeALotOfText(String msg) throws IOException {
		out.println("P111 \"" + msg + "\" \r\n");
		in.readLine();
		in.readLine();
		
		while(!in.ready()) {

		}
		in.readLine();

	}

	//Writes a message in the display and returns a user number input
	public int EnterNumber(String msg) throws IOException {
		out.println("RM20 8 \""+ msg + "\" \"\" \"&3\" crlf");
		in.readLine();  
		in.skip(4);
		String str = in.readLine().replaceAll("\\D+","");
		int output = Integer.parseInt(str);

		return output;
	}
	
	public String getStringFromDisplay(String msg) throws IOException {
		out.println("RM20 8 \""+ msg + "\" \"\" \"&3\" crlf");
		in.readLine();
		in.skip(6);
		String str = in.readLine();
		
		return str;
	}

	//Exits the Weightsim
	public void ExitWeight() throws IOException {

		out.println("Q \r\n");
		out.close();
		in.close();  
		pingSocket.close();
	}


	public List<String> getItemsAsStrings() {
		ArrayList<String> itemsAsStrings = new ArrayList<String>();
		for(int i = 0; i<storage.getItems().size(); i++) {
			itemsAsStrings.add(storage.getItems().get(i).toString());
		}
		return itemsAsStrings;
	}
	
	public void addItem(int batchNr, String Name, int weight, int user) {
		ItemDTO newItem = new ItemDTO(batchNr, Name, weight, user);
		storage.addItem(newItem);
	}

	public boolean isValidBatchNumber(int BN) {
		if(BN < 1000 || BN > 9999) {
			return false;
		}

		for(int i = 0; i < storage.getItems().size(); i++) {
			if(storage.getItems().get(i).getBatchNr() == BN) {
				return false;
			}
		}

		return true;
	}

	public void resetInputStream() {
		String str = null;
		try {
			while((str = in.readLine()) != null) {
				in.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}