package hoooooo.test03;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Hashtable;

public class ServerEx03 extends Thread {
	// 다중 접속자들의 접속 정보를 저장하는 컬렉션 준비
	// 접속한 아이디의 소켓을 저장 하거나 연결 스트리밍을 저장한다.
	// Hashtable은 클래스에 한개만 생성됨.
	// 한개로 모든 접속자들의 id를 관리하기 위함.
	static Hashtable<String, PrintWriter> map = new Hashtable<>();
	// br과 userId는 객체당 한개씩 생성
	BufferedReader br;
	String userId;

	public ServerEx03(String userId, BufferedReader br) {
		this.userId = userId;
		this.br = br;
	}

	@Override
	public void run() {
		while (true) {
			String line = null;
			try {
				line = br.readLine();
				Enumeration<String> keys = map.keys(); // userId가 key
				while (keys.hasMoreElements()) {
					// 키를 하나씩 받아옴
					String key = keys.nextElement();
					// 그 키로 PrintWriter를 가져옴.(모니터 : 클라이언트의 소켓)
					PrintWriter pw = map.get(key);
					pw.println(userId + " : " + line);
					pw.flush();
				}
			} catch (SocketException e) {
				try {
					if (br != null)
						br.close();
					System.out.println(userId +"클라이언트와 연결이 끊어졌습니다.");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				break;
			} catch (IOException e) {
				System.out.println(userId +"클라이언트와 연결이 끊어졌습니다.");
				break;
			}
		}
	}

	// main 쓰레드에서는 접속자를 기다리는 역할.
	// 접속자가 접속되면 접속자의 출력 정보를 map에 저장하고
	// 접속자의 쓰레드를 실행 시킨다.

	// 쓰레드는 클라이언트가 보낸 데이터를 출력하는 역할.
	// 접속자와 대화하는애는 다른 쓰레드로 만들어 놓는다.
	// main 쓰레드는 여러 소켓을 받을 수 있다.
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		Socket socket = null;
		BufferedReader br = null;
		PrintWriter pw = null;
		String userId = "";
		try {
			serverSocket = new ServerSocket(9999);
			System.out.println("서버에서 클라이언트 접속 대기 중...");
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		while (true) {
			try {
				// 접속이 들어오기 전까지는 반복하지 않고 대기.
				// 접속이 들어오면 socket을 하나 만들고 계속 대기.
				socket = serverSocket.accept();
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				// 클라이언트는 접속하자마자 userId를 입력해줘야함.
				userId = br.readLine();
				if("no-user".equals(userId)) {
					System.out.println("사용자 정보가 없습니다");
					throw new SocketException();
				}
				System.out.println(userId + "님이 접속 하였습니다.");

				// BufferedOutputStream를 활용해서 PrintWriter 생성
				pw = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()));
				// 브로드 케스트 하기위해(모든 접속자에게 메세지를 보냄)
				map.put(userId, pw);
				// 서버 생성 및 쓰레드 실행
				new ServerEx03(userId, br).start();

			} catch (SocketException e) {
				try {
					if (br != null)	br.close();
					if (pw != null)	pw.close();
					
					System.out.println(userId + "클라이언트와 연결이 끊어졌습니다.");
				} catch (IOException e1) {
					System.out.println(userId +"클라이언트 연결 해제 오류!");
				}
			} catch (IOException e) {
				System.out.println(userId +"데이터 입출력 오류!");
//				e.printStackTrace();
			} finally {

			}
		} // end of while
	}
}
