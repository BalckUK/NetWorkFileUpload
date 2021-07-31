import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.crypto.Data;

public class Time {

	public static void main(String[] args) {

		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String str = simple.format(System.currentTimeMillis());
		System.out.println(str);

		Date time = new Date();
		String time1 = simple.format(time);

		System.out.println(time1);
	}

}
