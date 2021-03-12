package chatting;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ChatClient {
	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		// 클라이언트 - Socket
		
		try {
			Socket socket = new Socket("127.0.0.1",9999);
			System.out.println("client: 채팅 서버에 연결 되었습니다.");
			
			while(true) {
				String line = scan.nextLine();
				System.out.println(line);
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
