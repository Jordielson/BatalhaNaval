package listaderecuperacao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Util {	
	public static String input(String prompt) {
		String inputLine = null;
		System.out.print(prompt + " ");
		try {
			BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
			inputLine = is.readLine();
			if (inputLine.length() == 0) 
				return null;
		}catch (IOException e) {
			e.printStackTrace();
		}
		return inputLine;
	}
}
