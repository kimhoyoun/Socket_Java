package hoooooo.test03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientEx03 extends Thread{
	static Scanner sc = new Scanner(System.in);
	BufferedReader br; // PrintWriter를 이용해서 서버에서 보낸 데이터 읽기

	public ClientEx03(BufferedReader br) {
		this.br = br;
	}

	public static void main(String[] args) {
		try {
			Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
			// 읽고 쓰는 객체 준비
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			// 클라이언트 소켓을 실행 할때 userId를 args에 함께 입력받도록 한다.
			if (args[0].length() == 0) {
				System.out.println("사용자 id가 없습니다. ");
				System.out.println("ex) java -cp . hoooooo.test03.ClientEx03 userId");
				pw.println("no-user");
				pw.flush();
				if (socket != null)
					socket.close();
				return;
			}
			String userId = args[0];
			pw.println(userId);
			pw.flush();
			
			
			
			// 클라이언트 생성 및 br객체 초기화
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			new ClientEx03(br).start(); // 클라이언트 객체 생성 후, 쓰레드 실행.

			// 클라이언트 생성 성공이면 메세지 입력 대기 상태
			while (true) {
				String line = sc.nextLine();
				pw.println(line);
				pw.flush();
			}

		} catch (UnknownHostException e) {
			System.out.println("서버를 찾지 못했다!");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	} // end of main
	
	@Override
	public void run() {
		// 서버에서 보내지는 메세지 출력
		while(true) {
			try {
				String line = br.readLine();
				System.out.println(line);
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}catch (SocketException e) { // 소켓이 끊어졌는지(서버와 연결이 끊어졌는지) 확인
				System.out.println("서버와 연결이 끊어졌다.");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("서버와 데이터 통신 오류!");
				e.printStackTrace();
			}
		}
	}
}
