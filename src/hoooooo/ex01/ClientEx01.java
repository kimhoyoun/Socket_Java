package hoooooo.ex01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientEx01 {
	Socket socket;
	BufferedReader br;
	BufferedWriter bw;
	Scanner sc = new Scanner(System.in);
	public ClientEx01() {
		// 서버 소켓과 연결 - Socket생성 즉시 서버와 연결 됨.
		try {
			socket = new Socket(InetAddress.getLoopbackAddress(),9999);
			System.out.println("클라이언트 >>> 서버와 연결되었다.");
			// 서버와 입/출력 스트림 연결
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			// 서버에 userID 보내기
			bw.write("user01" + "\n");
			bw.flush(); // 버퍼를 비워주는 역할
			// 서버에서 보낸 메시지 받기
			while(true) {
				String line = sc.nextLine();
				bw.write(line+"\n");
				bw.flush();
				
				String serverMessage = br.readLine();
				System.out.println(serverMessage);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new ClientEx01();
	}
}
