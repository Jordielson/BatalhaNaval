package modelo;

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
	
	public static String gerarSenhaAleatoria() {
		int qtdeMaximaCaracteres = 8;
	    String[] caracteres = { "0", "1", "b", "2", "4", "5", "6", "7", "8",
	                "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
	                "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
	                "x", "y", "z"};
	    
		StringBuilder senha = new StringBuilder();

        for (int i = 0; i < qtdeMaximaCaracteres; i++) {
            int posicao = (int) (Math.random() * caracteres.length);
            senha.append(caracteres[posicao]);
        }
        return senha.toString();
	}
}
