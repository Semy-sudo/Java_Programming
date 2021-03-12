package chatting;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatSever {

	public static void main(String[] args) {
		// 서버 - ServerSocket -> accept -> Socket
		try {
			ServerSocket serverSocket = new ServerSocket(9999);
			System.out.println("server: 클라이언트의 연결 대기 중...");
			while(true) {
				Socket socket = serverSocket.accept();
				System.out.println("클라이언트 연결 됨.");
			}
		} catch (IOException e) {
			System.out.println("채팅 중 오류 발생");
		}
		
		

	}

}
