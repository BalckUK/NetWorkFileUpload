import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Server {

	public static void main(String[] args) throws IOException {

		ServerSocket server = new ServerSocket(10000);
		System.out.println("서버가 구동되었습니다");
		System.out.println("클라이언트 연결을 대기중입니다...ing");
		Socket socket = server.accept();
		System.out.println(socket.getInetAddress() + "에서 연결하였습니다");

		OutputStream out = socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(out);

		InputStream in = socket.getInputStream();
		DataInputStream dis = new DataInputStream(in);

		Map<String, String> map = new HashMap<>();
		map.put("admin", "1234");

		Map<String, String> mapchecked = new HashMap<>();

		String reall = "true";
		String reall1 = "false";

		String id = dis.readUTF();
		String pw = dis.readUTF();
		mapchecked.put(id, pw);

		if (map.entrySet().equals(mapchecked.entrySet())) {
			dos.writeUTF(reall);
			socket.close();
		} else {
			dos.writeUTF(reall1);
			socket.close();
		}

	}

}
