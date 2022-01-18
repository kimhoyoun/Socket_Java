package hoooooo.ex01_01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientEx {
	Socket socket = null;
	BufferedReader br = null;
	BufferedWriter bw = null;
	Scanner sc = new Scanner(System.in);
	public ClientEx() {
		try {
			Socket socket = new Socket(InetAddress.getLocalHost(),9999);
			System.out.println("서버 접속 성공");
			// 입출력 스트림 생성
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			// 데이터 보내주기
			bw.write("user01" + "\n");
			bw.flush();
			
			// ID 보내주고 계속 입력
			
			while(true) {
				String line = sc.nextLine();
				bw.write(line+"\n");
				bw.flush();
				
				String echo = br.readLine();
				System.out.println("Server >>> "+echo);
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new ClientEx();
	}
}
