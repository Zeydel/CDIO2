package ful;
	import java.io.BufferedReader;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.io.PrintWriter;
	import java.net.Socket;

public class WeightFunction {

//
	        Socket pingSocket = null;
	        PrintWriter out = null;
	        BufferedReader in = null;
//
//	        try {
//	            pingSocket = new Socket("127.0.0.1", 8000);
//	            System.out.println("Connected to weight sim");
//	            out = new PrintWriter(pingSocket.getOutputStream(), true);
//	            in = new BufferedReader(new InputStreamReader(pingSocket.getInputStream()));
//	        } catch (IOException e) {
//	            return;
//	        }
//	        System.out.println("reading command...");
//	        out.println("S crlf");
//	        System.out.println(in.readLine());
//	        out.close();
//	        in.close();
//	        pingSocket.close();
//	    }
//	}
	        public void getWeight() throws IOException {
	        	try {
	        		pingSocket = new Socket("127.0.0.1", 8000);
	        		System.out.println("Connected to weight simulator");
	        		out = new PrintWriter(pingSocket.getOutputStream(), true);
	        		in = new BufferedReader(new InputStreamReader(pingSocket.getInputStream()));	
	        	} catch(IOException e){
	        		return;
	        	}
	        	System.out.println("reading command...");
	        	out.println("S crlf");
	        	System.out.println(in.readLine());
	        	out.close();
	        	in.close();
	        	pingSocket.close();
	        }
	        
	        public int setWeightToZero(double weight) throws IOException {
	        	try {
	        		pingSocket = new Socket("127.0.0.1", 8000);
	        		System.out.println("Connected to weight simulator");
	        		out = new PrintWriter(pingSocket.getOutputStream(), true);
	        		in = new BufferedReader(new InputStreamReader(pingSocket.getInputStream()));
	        	} catch(IOException e) {
	        		return;
	        	}
	        	out.println(x);
	        }
}
