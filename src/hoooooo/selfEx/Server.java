package hoooooo.selfEx;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {
	static HashMap hm = new HashMap();
	
	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(9999);
			System.out.println("접속을 기다립니다...");
			while(true) {
				System.out.println(".....");
				Socket socket = server.accept();
				// 접속하면 바로 쓰레드 시작
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
