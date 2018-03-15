package tui;
import java.util.Scanner;

import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Random;

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
			int id = function.EnterNumber("Enter UserID, press OK to continue");
			while(!uFunction.asserIfIdExists(id)) {
				id = function.EnterNumber("No user with this ID, try again");	
			}
			String name = uFunction.findUser(id).getUserName();
			
			function.ReturnToWeightDisplay();
			
			function.writeALotOfText("Welcome " + name + ", press [-> to continue");
			
			int batchNr = function.EnterNumber("Enter BatchNr");
			System.out.println(function.isValidBatchNumber(batchNr));
			while(!function.isValidBatchNumber(batchNr)) {
				batchNr = function.EnterNumber("Batchnumber cant be used, try again");
			}
			function.ReturnToWeightDisplay();
			function.writeALotOfText("Fjern venligst alt fra vægten");
			
			function.WeightTare();
			
			function.ReturnToWeightDisplay();
			
			function.writeALotOfText("Placer tom beholder på vægt");
			
			int tareWeight = function.getWeight();
			
			function.WeightTare();
			
			
			function.writeALotOfText("Placer indhold i beholder");
			
			int netto = function.getWeight();
			
			function.WeightTare();
			
			function.writeALotOfText("Fjern alt fra vægten");
			
			int brutto = function.getWeight();
			
			function.WeightTare();
			
			if(brutto - tareWeight == netto) {
				String itemName = function.getStringFromDisplay("Hvad har du vejet?");
				function.ReturnToWeightDisplay();
				function.addItem(batchNr, itemName, netto, id);
				
			} else function.writeALotOfText("Fejl i bruttokontrol. Batch kasseret.");
			
		}catch(IOException e) {
			System.out.println("lol rip");
		}

	}

	public void showBatches() {

	}

	public void deleteBatch() {

	}

}