package test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class testclient {

	public static void main(String[] args) {

		try {
			Socket socket = new Socket("127.0.0.1", 10000);

			InputStream in = socket.getInputStream();
			DataInputStream dis = new DataInputStream(in);

			OutputStream out = socket.getOutputStream();
			DataOutputStream dos = new DataOutputStream(out);

			Scanner sc = new Scanner(System.in);

			String are = dis.readUTF();
			if (are.equals("ok")) {
				System.out.println("������ �����Ͽ����ϴ�");
			}
			while (true) {
				System.out.println("=== ���ϼ������α׷� ===");
				System.out.println("1. �������� ���ε�(*.txt)");
				System.out.println("2. ���ϴٿ�ε�");
				System.out.println("3. �����������");
				System.out.print(">> ");
				String num = sc.nextLine();
				dos.writeUTF(num);
				if (num.equals("1")) {
					System.out.print("���ε� �ϰ� ���� ������ ��ġ�� �������� : ");
					String filepost = sc.nextLine();
					dos.writeUTF(filepost);
					System.out.print("������ ������ �̸��� �������� : ");
					String filename = sc.nextLine();
					dos.writeUTF(filename);
					String an = dis.readUTF();
					System.out.println(an);
				} else if (num.equals("2")) {
					long start = System.currentTimeMillis();
					System.out.print("������ ���� ��ġ�� ��������(�����̸��� ����) : ");
					String path = sc.nextLine();
					dos.writeUTF(path);
					System.out.print("�����̸��� ��������(Ȯ������) : ");
					String exe = sc.nextLine();
					dos.writeUTF(exe);
					dos.flush();
					String ans = dis.readUTF();
					System.out.println(ans);
					
					long end = System.currentTimeMillis();
					System.out.println("���� �ð� : " + (end - start) / 1000.0 + "��");
					System.out.println("���ϴٿ�ε� �Ϸ�Ǿ����ϴ�");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
