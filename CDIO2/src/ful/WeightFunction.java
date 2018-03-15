package ful;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import dal.WeightSim;

public class WeightFunction {


	Socket pingSocket = null;
	PrintWriter out = null;
	BufferedReader in = null;

	public WeightFunction() {
		try {
		this.pingSocket = new Socket("127.0.0.1", 8000);
		this.out = new PrintWriter(pingSocket.getOutputStream(), true);
		this.in = new BufferedReader(new InputStreamReader(pingSocket.getInputStream()));
		} catch(IOException e) {
			System.out.println("fail");
		}
	}

	public static void main(String[] args) {
		WeightFunction test = new WeightFunction();
		try {
			test.WriteTextInDisplay("hejsa");
			test.ReturnToWeightDisplay();
			System.out.println(test.getWeight());
			test.WeightTare();
			System.out.println(test.getWeight());
		} catch (IOException e) {
			System.out.println("failed");
		}
	}


	public int getWeight() throws IOException {
		try {
			out.println("S crlf");
			in.readLine();
			String str = in.readLine().replaceAll("\\D+","");
			int h = Integer.parseInt(str);
			return h;
		} catch(IOException e) {
			System.out.println("Det gik galt");
			return 0;
		}
	}

	public void WeightTare() throws IOException {

		out.println("T crlf");
		System.out.println("Weight has been tared");

	}


	public void ReturnToWeightDisplay() throws IOException {
		out.println("DW crlf");
		in.readLine();
	}

        
	public void WriteTextInDisplay(String text) throws IOException {
		out.println("D ”" + text + "” crlf");


	}


	public void EnterNumber() throws IOException {
		//	        	public static void main(String[] args) throws IOException{
		//	           		
		//	        		Socket pingSocket = null;
		//	    	        PrintWriter out = null;
		//	    	        BufferedReader in = null;

		try {
			pingSocket = new Socket("127.0.0.1", 8000);
			System.out.println("Connected to weight simulator");
			out = new PrintWriter(pingSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(pingSocket.getInputStream()));
		} catch(IOException e) {
			return;		
		}
		out.println("RM20 8 ”INDTAST BATCHNUMBER” ”” ”&3” crlf");
		System.out.println(in.readLine());
		out.println("RM20 B crlf");
		System.out.println(in.readLine());
		out.close();
		in.close();
		pingSocket.close();
	}

	public void EnterID() throws IOException {
		try {
			pingSocket = new Socket("127.0.0.1", 8000);
			System.out.println("Connected to weight simulator");
			out = new PrintWriter(pingSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(pingSocket.getInputStream()));
		} catch(IOException e) {
			return;	
		}
		out.println("RM20 8 ”INDTAST ID” ”” ”&3” crlf");
		System.out.println(in.readLine());
		out.println("RM20 B crlf");
		System.out.println(in.readLine());
		out.close();
		in.close();
		pingSocket.close();
	}

	public void ExitWeight() throws IOException {

		//	        	public static void main(String[] args) throws IOException{
		//       		
		//	        		Socket pingSocket = null;
		//	    	        PrintWriter out = null;
		//	    	        BufferedReader in = null;

		try {
			pingSocket = new Socket("127.0.0.1", 8000);
			System.out.println("Connected to weight simulator");
			out = new PrintWriter(pingSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(pingSocket.getInputStream()));
		} catch(IOException e) {
			return;		
		}
		out.println("Q crlf");
		System.out.println(in.readLine());
		out.close();
		in.close();
		pingSocket.close();
	}
}