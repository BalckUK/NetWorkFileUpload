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
				System.out.println("서버에 접속하였습니다");
			}
			while (true) {
				System.out.println("=== 파일서버프로그램 ===");
				System.out.println("1. 문서파일 업로드(*.txt)");
				System.out.println("2. 파일다운로드");
				System.out.println("3. 음악파일재생");
				System.out.print(">> ");
				String num = sc.nextLine();
				dos.writeUTF(num);
				if (num.equals("1")) {
					System.out.print("업로드 하고 싶은 파일의 위치를 적으세요 : ");
					String filepost = sc.nextLine();
					dos.writeUTF(filepost);
					System.out.print("저장할 파일의 이름을 적으세요 : ");
					String filename = sc.nextLine();
					dos.writeUTF(filename);
					String an = dis.readUTF();
					System.out.println(an);
				} else if (num.equals("2")) {
					long start = System.currentTimeMillis();
					System.out.print("복사할 파일 위치를 적으세요(파일이름은 제외) : ");
					String path = sc.nextLine();
					dos.writeUTF(path);
					System.out.print("파일이름을 적으세요(확장명까지) : ");
					String exe = sc.nextLine();
					dos.writeUTF(exe);
					dos.flush();
					String ans = dis.readUTF();
					System.out.println(ans);
					
					long end = System.currentTimeMillis();
					System.out.println("실행 시간 : " + (end - start) / 1000.0 + "초");
					System.out.println("파일다운로드 완료되었습니다");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
