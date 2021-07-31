import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

public class Client {

	public static void main(String[] args) throws UnknownHostException, IOException {

		Socket sock = new Socket("127.0.0.1", 10000);
		InputStream in = sock.getInputStream();
		DataInputStream dis = new DataInputStream(in);

		OutputStream out = sock.getOutputStream();
		DataOutputStream dos = new DataOutputStream(out);

		while (true) {
			SimpleDateFormat simple = new SimpleDateFormat("  yyyy-MM-dd HHΩ√mm∫–ss√ ");
			String str = simple.format(System.currentTimeMillis());

			dos.writeUTF(JOptionPane.showInputDialog("time/lotto/wiseword"));
			dos.flush();

			String msg = dis.readUTF();
			System.out.println(msg);
		}

	}

}
