package tui;
import java.util.Scanner;

import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

import java.io.IOException;
import java.util.InputMismatchException;
import dal.NPitem;
import dal.NonPersistentStorage;
import ful.UserFunction;
import ful.WeightFunction;


public class LaborantTUI {
	
	WeightFunction function;
	UserFunction uFunction;
	Scanner in = new Scanner(System.in);
	
	
	
	public static void main(String []args) {
		NPitem item = new NPitem();
		WeightFunction wf = new WeightFunction(item);
		NonPersistentStorage NPS = new NonPersistentStorage();
		UserFunction tobias = new UserFunction(NPS);
		
		LaborantTUI TUI = new LaborantTUI(wf, tobias);
		TUI.createBatcht();
		
	}
	
	public LaborantTUI (WeightFunction function, UserFunction uFunction) {
		this.function = function;
		this.uFunction = uFunction;
	}
	public void showMenu() {

		System.out.println(
				"--Menu--\n" + 
						"1. Foretag afvejning\n"+ 
						"2. Vis afvejninger\n" + 
						"3. Slet afvejning\n" +
				"3. Afslut Program\n");

		System.out.println("Tryk venligst et tal mellem 1 og 3");
		int choice = 0;
		try {
			choice = in.nextInt();
		}catch (InputMismatchException e) {
		}

		switch(choice) {
		case 1: createBatcht();
		break;
		case 2: showBatches();
		break;
		case 3: deleteBatch();
		case 5: System.exit(0);
		break;
		default:
			System.out.println("FEJL: Tryk venligst et tal mellem 1 og 5");
			choice = 0;
			break;
		}
	}
	
	public void createBatcht() {
		try {
		int id = function.EnterNumber("Enter UserID");
		while(!uFunction.asserIfIdExists(id)) {
			id = function.EnterNumber("No user with this ID");	
		}		
		String name = uFunction.findUser(id).getUserName();
		//function.WriteTextInDisplay("Welcome, "+ name + "to the weight");
		String str = ("Welcome, "+ name + " to the weight");
		function.writeALotOfText(str);
		while(true) {
			
		}
		}
		catch(IOException e) {
			
		}

	}
	
	public void showBatches() {
		
	}
	
	public void deleteBatch() {
		
	}

}