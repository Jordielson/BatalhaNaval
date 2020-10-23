package listaantiga;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String nomeDoArquivo = "central.xml";
		
		Persistencia p = new Persistencia();
		CentralDeInformacoes CI = p.recuperarCentral(nomeDoArquivo);
		
		String op = "";
		while(!op.equals("S")) {
			Main.exibir();
			op = input.nextLine();
			
			if(op.equals("1")) {
				System.out.print("Apelido:");
				String apelido = input.nextLine();
				System.out.print("E-mail:");
				String email = input.nextLine();
				System.out.print("Senha:");
				String senha = input.nextLine();
				System.out.print("Quantidade de Vitórias:");
				int vitorias = Integer.parseInt(input.nextLine());
				if (CI.adicionarJogador(new Jogador(apelido, email, senha, vitorias)))
					p.salvarCentral(CI, nomeDoArquivo);
				else
					System.out.println("Dados inválidos");
			} else if(op.equals("2")) {
				for (Jogador j : CI.getTodosOsJogadores())
					System.out.println(j);	
			} else if(op.equals("3") || op.equals("4")) {
				System.out.print("E-mail: ");
				String email = input.nextLine();
				System.out.println("Senha: ");
				String senha = input.nextLine();
				
				if(op.equals("3")) {
					
				}	
			}
		}
		
		input.close();
	}
	
	public static void exibir() {
		System.out.print("1-novo jogador \n"
				+ "2-listar jogadores\n"
				+ "3 - cadastrar um novo mapa para um jogador\n" 
				+ "4 - visualizar o mapa de um jogador\n"
				+ "S-sair\n"
				+ "Escolha uma opção:");
		
	}
}
