import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

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
//		dos.writeUTF("Well com my ip ha ha ha ha ha ha ha ha ha");

		while (true) {

			SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH시mm분ss초");
			String str = simple.format(System.currentTimeMillis());

			String msg = dis.readUTF();
			if (msg.contentEquals("time")) {
				dos.writeUTF(str);
				dos.flush();
			} else if (msg.contentEquals("lotto")) {
				Set<Integer> set = new HashSet<>();
				while (set.size() < 6) {
					set.add((int) (Math.random() * 45 + 1));
				}
				dos.writeUTF("오늘의 로또 : " + set);
				dos.flush();

			} else if (msg.contentEquals("wiseword")) {
				int a = (int) (Math.random() * 3 + 1);
				if (a == 1) {
					dos.writeUTF("시간을 지배할 줄 아는 사람은 인생을 지배할 줄 아는 사람이다");
				} else if (a == 2) {
					dos.writeUTF("시간의 걸을걸이에는 세 가지가 있다 미래는 주저하면서 다가오고, 현재는 화살처럼 날아오고, 과거는 영원히 정히하고 있다");
				} else if (a == 3) {
					dos.writeUTF("짧은 인생은 시간낭비에 의해 더욱 짧아진다");
				}

			} else {
				dos.writeUTF("잘못 적으셨습니다");
				continue;
			}

		}

	}

}
