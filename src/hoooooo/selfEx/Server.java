package hoooooo.selfEx;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

class ServerThread extends Thread {
	Socket socket;
	HashMap hm;
	BufferedReader br = null;
	BufferedWriter bw = null;
	String userID;

	public ServerThread(Socket socket, HashMap hm) {
		this.socket = socket;
		this.hm = hm;

		try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			userID = br.readLine();
			System.out.println(userID+"님 접속");
			hm.put(userID, bw);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				String line = null;
				line = userID+" : "+br.readLine() +"\n";
				System.out.print(line);
				Collection collection = hm.values();
				Iterator it = collection.iterator();
				while(it.hasNext()) {
					BufferedWriter print = (BufferedWriter)it.next();
					print.write(line);
					print.flush();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
			}
		}

	}
}

public class Server {
	static HashMap hm = new HashMap();

	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(9999);
			System.out.println("접속을 기다립니다...");
			while(true) {
			Socket socket = server.accept();
//			ServerThread sth = new ServerThread(socket, hm);

//				System.out.println(".....");
				// 접속하면 바로 쓰레드 시작
				ServerThread sth = new ServerThread(socket, hm);
				sth.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
	}
}
