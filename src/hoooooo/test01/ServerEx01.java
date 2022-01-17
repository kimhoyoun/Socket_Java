package hoooooo.test01;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ServerEx01 {

	public static void main(String[] args) {
		// 서버에서 클라이언트 요청 대기
		byte[] by = new byte[65535];
		DatagramPacket packet = null;
		DatagramSocket socket =  null;
		InetAddress ia = null;
		
		
		try {
			// 컴퓨터 이름과 ip를 가져올 수 있음.
			ia = InetAddress.getLocalHost();
//			System.out.println("Location >>> "+ia.toString());
			socket = new DatagramSocket(7777, ia);
			packet = new DatagramPacket(by, by.length);
			System.out.println("서버 준비....");
			// 소켓에 패킷을 넣어줌
			socket.receive(packet);
			packet.setData(by);
			System.out.println(new String(by).trim());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
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
