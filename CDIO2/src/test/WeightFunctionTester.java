package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import ful.WeightFunction;

public class WeightFunctionTester {
	
	Socket pingSocket = null;
	PrintWriter out = null;
	BufferedReader in = null;

	
	public static void main(String[] args) throws IOException {

		Socket pingSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;
		WeightFunction weight = new WeightFunction(null);


//		Test af EnterNumber metoden:
//		try {
//			pingSocket = new Socket("127.0.0.1", 8000);
//			out = new PrintWriter(pingSocket.getOutputStream(), true);
//			in = new BufferedReader(new InputStreamReader(pingSocket.getInputStream()));
//		} catch (IOException e) {
//			return;
//		}
//
//		out.println(weight.EnterNumber("12355"));
//		System.out.println(in.readLine());
//		out.close();
//		in.close();
//		pingSocket.close();
//		out.println();
		
		
//		Test af WriteTextInDisplay
		try {
			pingSocket = new Socket("127.0.0.1", 8000);
			out = new PrintWriter(pingSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(pingSocket.getInputStream()));
		} catch (IOException e) {
			return;
		}

		out.println(weight.WriteTextInDisplay("HALLO"));
		System.out.println(in.readLine());
		out.close();
		in.close();
		pingSocket.close();
		out.println();
		
		
	}
	
		
	
		
}
