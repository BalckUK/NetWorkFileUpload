package test;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class testserver {

	public static void main(String[] args) {
		try {
			BufferedReader br = null;
			PrintWriter pw = null;
			Scanner sc = new Scanner(System.in);
			ServerSocket server = new ServerSocket(10000);
			System.out.println("������ �����Ǿ����ϴ�");
			System.out.println("Ŭ���̾�Ʈ ������ ������Դϴ�...ing");
			Socket socket = server.accept();
			System.out.println(socket.getInetAddress() + "���� �����Ͽ����ϴ�");

			InputStream in = socket.getInputStream();
			DataInputStream dis = new DataInputStream(in);

			OutputStream out = socket.getOutputStream();
			DataOutputStream dos = new DataOutputStream(out);

			if (!socket.equals(null)) {
				System.out.println(socket);
				dos.writeUTF("ok");
			}
			while (true) {
				String num = dis.readUTF();
				if (num.equals("1")) {
					try {
						String filepost = dis.readUTF();
						br = new BufferedReader(new FileReader(filepost));
						String filename = dis.readUTF();
						pw = new PrintWriter(new FileWriter(filename));
						String line = null;
						while ((line = br.readLine()) != null) {
							pw.println(line);
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						pw.close();
						try {
							br.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					dos.writeUTF("������ ����Ǿ����ϴ�");
				} else if (num.equals("2")) {
					String path = dis.readUTF();
					System.out.println(path);
					String exe = dis.readUTF();
					System.out.println(exe);

					// ���� ���ϰ��
					String oriFilePath = path + "\\" + exe;
					// ����� ���ϰ��
					String copyFilePath = "C:\\Users\\haga5\\Desktop\\mm\\" + exe;

					// ���ϰ�ü����
					File oriFile = new File(oriFilePath);
					// �������ϰ�ü����
					File copyFile = new File(copyFilePath);
					long L = 0;
					try {

						FileInputStream fis = new FileInputStream(oriFile); // ��������
						FileOutputStream fos = new FileOutputStream(copyFile); // ����������
						int fileByte = 0;
						// fis.read()�� -1 �̸� ������ �� ������
						File oFile = new File(path + "/" + exe);
						if (oFile.exists()) {
							L = oFile.length();
							System.out.println(L + " bytes : " + oFile.getAbsoluteFile());
						} else
							System.err.println("������ ����...");
						while ((fileByte = fis.read()) != -1) {
							fos.write(fileByte);
						}
						// �ڿ��������
						fis.close();
						fos.close();
						dos.writeUTF("���� ���簡 �Ǿ����ϴ�");
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}

		} catch (Exception e) {
			System.out.println("������ ������ ���������ϴ�");
			// e.printStackTrace();
		}
	}
}
