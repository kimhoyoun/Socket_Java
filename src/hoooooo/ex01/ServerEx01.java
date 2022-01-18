package hoooooo.ex01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerEx01 {
	// 서버쪽에서 포트를 열어서 대기하는 주체
	ServerSocket listener = null;
	public ServerEx01() {
		Socket socket = null;
		BufferedReader br = null;
		BufferedWriter bw = null;
		Scanner sc = new Scanner(System.in);
		try {
			// SeverSocket을 생성하고 클라이언트 접속 대기
			// 생성자에 넣어주는 ip가 서버 ip, 포트만 넣어주면 기본 ip
			// 일회용
			listener = new ServerSocket(9999);
			System.out.println("서버 대기중....");
			// 클라이언트 접속 대기 - 접속이 되면 Socket을 반환한다.
			socket = listener.accept();
			System.out.println("클라이언트와 접속이 되었습니다.");
			// 소켓을 이용해서 br이나 bw와 연결(클라이언트와 입출력 스트림을 연결)
			// BufferedReader와 BufferedWriter를 생성
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			// 클라이언트의 userID 읽어오기
			String userID = br.readLine();
			System.out.println("Server >> "+userID + "님이 접속하셨습니다.");
			// 클라이언트로 에코를 전달한다.
			while(true) {
				// 반복해서 읽어들인다.
				String line = br.readLine();
				System.out.println("Client >>> "+line);
				
				// 클라이언트로 다시 전송(에코)
				bw.write("server >>> "+line+ "\n");
				bw.flush();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// Socket Server
		new ServerEx01();
	}
	
}
