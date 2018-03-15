package ful;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import dal.NPitem;
import dal.WeightSim;

public class WeightFunction {


	Socket pingSocket = null;
	PrintWriter out = null;
	BufferedReader in = null;
	NPitem storage = new NPitem();

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
			System.out.println(test.EnterNumber("test"));
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


	public int EnterNumber(String msg) throws IOException {
		out.println("RM20 8 ”"+ msg +"” ”” crlf");
		in.readLine();
		String str = in.readLine().replaceAll("\\D+","");
		int output =Integer.parseInt(str);
		return (output-20000);
	}


	public void ExitWeight() throws IOException {

		out.println("Q crlf");
		out.close();
		in.close();
		pingSocket.close();
	}
}