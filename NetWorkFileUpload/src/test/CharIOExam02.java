package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class CharIOExam02 {

	public static void main(String[] args) {

		BufferedReader br = null;
		PrintWriter pw = null;
		try {
			br = new BufferedReader(new FileReader("C:\\Users\\haga5\\Desktop\\»õ Æú´õ\\dd.txt"));
			pw = new PrintWriter(new FileWriter("te.txt"));
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
	}

}
