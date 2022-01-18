package hoooooo.ex01_01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerEx {
	ServerSocket listener = null;
	Socket socket = null;
	BufferedReader br = null;
	BufferedWriter bw = null;
	
	public ServerEx() {
		// 서버 생성
		try {
			listener = new ServerSocket(9999);
			System.out.println("서버 대기중...");
			socket = listener.accept();
			System.out.println("클라이언트와 접속완료");
			
			// 입출력 스트림 만들기
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			// 데이터 받기
			String userId = br.readLine();
			System.out.println(userId + "님 접속!");
			
			// ID받고 계속 입력 받고 에코 넘기기
			while(true) {
				String line = br.readLine();
				System.out.println("Client >>> "+line);
				
				bw.write(line + "\n");
				bw.flush();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new ServerEx();
	}
}
