package hoooooo.test01;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientEx01 {

	public static void main(String[] args) {
		// 서버에 데이터를 전송하는 기능
		// 보낼 데이터 준비
		String str = null;
		// 데이터 키로 입력
		Scanner sc = new Scanner(System.in);
		System.out.print("내용 입력 >> ");
		str = sc.nextLine();
		
		InetAddress ia = null;
		DatagramPacket packet = null;
		DatagramSocket socket = null;
		
		// 입력받은 내용을 서버에 보내주기
		try {
			ia = InetAddress.getLocalHost();
			// 패킷에 IP를 넣고 만들었기 때문에 (우편물에 주소를 직접 입력했다고 생각하면 됨)
			packet = new DatagramPacket(str.getBytes(), str.getBytes().length, ia, 7777);
			// 소켓에서 IP를 넣어줄 필요가 없다.
			socket = new DatagramSocket();
			socket.send(packet);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
