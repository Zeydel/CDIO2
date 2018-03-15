package dal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class WeightSim {

	Socket pingSocket = null;
	PrintWriter out = null;
	BufferedReader in = null;

	public WeightSim() {
		try {
			this.pingSocket = new Socket("127.0.0.1", 8000);
			this.out = new PrintWriter(pingSocket.getOutputStream(), true);
			this.in = new BufferedReader(new InputStreamReader(pingSocket.getInputStream()));
			System.out.println("Connection has been established");

		} catch (IOException e) {
			System.out.println("Connection has not been established");
			return;
		}


	}
	public Socket getPingSocket() {
		return pingSocket;
	}
	public void setPingSocket(Socket pingSocket) {
		this.pingSocket = pingSocket;
	}
	public PrintWriter getOut() {
		return out;
	}
	public void setOut(PrintWriter out) {
		this.out = out;
	}
	public BufferedReader getIn() {
		return in;
	}
	public void setIn(BufferedReader in) {
		this.in = in;
	}
	public static void main(String[] args) throws IOException {

		Socket pingSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;

		try {
			pingSocket = new Socket("127.0.0.1", 8000);
			out = new PrintWriter(pingSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(pingSocket.getInputStream()));
		} catch (IOException e) {
			return;
		}

		out.println("S crlf");
		System.out.println(in.readLine());
		out.close();
		in.close();
		pingSocket.close();
	}
}