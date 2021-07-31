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
			System.out.println("서버가 구동되었습니다");
			System.out.println("클라이언트 연결을 대기중입니다...ing");
			Socket socket = server.accept();
			System.out.println(socket.getInetAddress() + "에서 연결하였습니다");

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
					dos.writeUTF("파일이 저장되었습니다");
				} else if (num.equals("2")) {
					String path = dis.readUTF();
					System.out.println(path);
					String exe = dis.readUTF();
					System.out.println(exe);

					// 원본 파일경로
					String oriFilePath = path + "\\" + exe;
					// 복사될 파일경로
					String copyFilePath = "C:\\Users\\haga5\\Desktop\\mm\\" + exe;

					// 파일객체생성
					File oriFile = new File(oriFilePath);
					// 복사파일객체생성
					File copyFile = new File(copyFilePath);
					long L = 0;
					try {

						FileInputStream fis = new FileInputStream(oriFile); // 읽을파일
						FileOutputStream fos = new FileOutputStream(copyFile); // 복사할파일
						int fileByte = 0;
						// fis.read()가 -1 이면 파일을 다 읽은것
						File oFile = new File(path + "/" + exe);
						if (oFile.exists()) {
							L = oFile.length();
							System.out.println(L + " bytes : " + oFile.getAbsoluteFile());
						} else
							System.err.println("파일이 없음...");
						while ((fileByte = fis.read()) != -1) {
							fos.write(fileByte);
						}
						// 자원사용종료
						fis.close();
						fos.close();
						dos.writeUTF("파일 복사가 되었습니다");
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}

		} catch (Exception e) {
			System.out.println("서버와 접속이 끊어졌습니다");
			// e.printStackTrace();
		}
	}
}
