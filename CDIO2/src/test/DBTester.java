package test;
import dal.NonPersistentStorage;
import dal.PersistentStorage;
import ful.UserFunction;
import tui.TUI;

public class DBTester {
	NonPersistentStorage storage = new NonPersistentStorage();
	UserFunction function = new UserFunction(storage);
	TUI ui = new TUI(function);
	public static void main(String[] args) {
		DBTester test = new DBTester();
		test.run();
	}
	
	//Kør TUI som administrator.
	public void run() {
		while(true) {
			ui.showMenu();
		}

	}
}
