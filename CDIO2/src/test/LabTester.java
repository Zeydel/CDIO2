package test;

import dal.NPitem;
import dal.NonPersistentStorage;
import ful.UserFunction;
import ful.WeightFunction;
import tui.LaborantTUI;

public class LabTester {

	NonPersistentStorage US = new NonPersistentStorage();
	NPitem IS = new NPitem();
	UserFunction UF = new UserFunction(US);
	WeightFunction WF = new WeightFunction(IS);
	LaborantTUI TUI = new LaborantTUI(WF, UF);

	//Kør TUI som laborant
	//Åben vægtsimulator før du kører denne
	public static void main(String[] args) {
		LabTester LT = new LabTester();
		LT.run();

	}

	public void run() {
		while(true) {
			TUI.showMenu();
		}
	}

}
