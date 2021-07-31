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
		System.out.println("������ �����Ǿ����ϴ�");
		System.out.println("Ŭ���̾�Ʈ ������ ������Դϴ�...ing");
		Socket socket = server.accept();
		System.out.println(socket.getInetAddress() + "���� �����Ͽ����ϴ�");

		OutputStream out = socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(out);

		InputStream in = socket.getInputStream();
		DataInputStream dis = new DataInputStream(in);
//		dos.writeUTF("Well com my ip ha ha ha ha ha ha ha ha ha");

		while (true) {

			SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH��mm��ss��");
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
				dos.writeUTF("������ �ζ� : " + set);
				dos.flush();

			} else if (msg.contentEquals("wiseword")) {
				int a = (int) (Math.random() * 3 + 1);
				if (a == 1) {
					dos.writeUTF("�ð��� ������ �� �ƴ� ����� �λ��� ������ �� �ƴ� ����̴�");
				} else if (a == 2) {
					dos.writeUTF("�ð��� �������̿��� �� ������ �ִ� �̷��� �����ϸ鼭 �ٰ�����, ����� ȭ��ó�� ���ƿ���, ���Ŵ� ������ �����ϰ� �ִ�");
				} else if (a == 3) {
					dos.writeUTF("ª�� �λ��� �ð����� ���� ���� ª������");
				}

			} else {
				dos.writeUTF("�߸� �����̽��ϴ�");
				continue;
			}

		}

	}

}
