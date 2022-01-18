package hoooooo.selfEx;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

class ClientThread extends Thread{
	BufferedReader br;
	ClientThread(BufferedReader br){
		this.br = br;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				String line = br.readLine();
				System.out.println(line);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
public class Client extends Thread{
	// 입력
	Socket socket;
	BufferedReader br = null;
	BufferedWriter bw = null;
	Scanner sc = new Scanner(System.in);
	public Client() {
		try {
			socket = new Socket(InetAddress.getLocalHost(),9999);
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			System.out.print("userID 입력 : ");
			String userID = sc.nextLine();
			bw.write(userID + "\n");
			bw.flush();
			
			ClientThread cth = new ClientThread(br);
			cth.start();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void run() {
		while(true) {
			System.out.print(">>>> ");
			String line = sc.nextLine();
			try {
				bw.write(line + "\n");
				bw.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		new Client().start();
	}
}
