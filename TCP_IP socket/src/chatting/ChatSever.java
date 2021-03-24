package chatting;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Hashtable;

public class ChatSever extends Thread {
	static Hashtable<String, PrintWriter> map = new Hashtable<String, PrintWriter>();
	BufferedReader br;
	String userid;
	
	public ChatSever(String userid, BufferedReader br){
		this.userid = userid;
		this.br = br;
		
		sendMessage(userid+"님이 입장하셨습니다.");
	}
	
	void sendMessage(String line) {
		//전달 받은 메세지를 모든 소켓에 뿌린다.
		Enumeration<String> keys = map.keys();
		while(keys.hasMoreElements()) {
			String idKey = keys.nextElement();
			PrintWriter pw = map.get(idKey);
			pw.println(line);
			pw.flush();
		}
	}
	
	//쓰레드
	@Override
	public void run() {
		while(true) {
			try {
				String line = br.readLine();
				sendMessage(line);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		// 서버 - ServerSocket -> accept -> Socket
		BufferedReader br = null;
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(9999);
			System.out.println("server: 클라이언트의 연결 대기 중...");
			while(true) {
				Socket socket = serverSocket.accept();
				System.out.println("클라이언트 연결 됨.");
				//클라이언트로 부터 메세지 받기
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String userid = br.readLine();
				System.out.println(userid+"님이 접속됨");
				PrintWriter pw = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()));
				pw.println(userid + "님이 입장하셨습니다.");
				pw.flush();
				
				map.put(userid, pw);//들어올때 마다 map에 쌓아줌
				
				//브로드 케스트 준비
				ChatSever server = new ChatSever(userid,br);
				server.start();
			}
		} catch (IOException e) {
			System.out.println("채팅 중 오류 발생");
		}
		
		

	}

}
