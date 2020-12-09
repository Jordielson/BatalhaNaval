package modelo;

import java.util.ArrayList;

import exceções.PosicaoInvalidaException;
/**
 * A classe Mapa reprensenta o mapa da partida, com todas as posições disponíveis.
 * O Mapa é representado por um array de inteiros com tamanho de 25. Cada indice desse array representa 
 * uma posição, inicialmente, 0 nas posições vazia e 1 nas posições que contém barcos. 
 * 
 * @author Jordielson Emanuel Caldeira da Silva 
 *
 */

public class Mapa {
	/** Frota do jogador com todas as embarcações*/
	private Frota frota;
	/** Todos os barcos que ainda não afundaram no combate*/
	private ArrayList<Embarcacao> barcosIntactos = new ArrayList<Embarcacao>();
	/** Conta a quantidade de ataques informados*/
	private int contar = 25;
	/**
	 * Representa o mapa
	 * -1 - se o jogador já atacou a posição e errou o ataque
	 * 0 - se a posição estiver vazia, o jogador não atacou essa posição
	 * 1 - se a posição estiver ocupada por um barco, o jogador não atacou essa posição
	 * 2 - se o jogador atacou essa posição e acertou o ataque, mas não destruiu a embarcação
	 * 3 - se o jogador atacou essa posição, acertou o ataque e destruiu a embarcação
	 * 
	 */
	private int[] locais;
	/** Armazena todos os ataques válidos feitos durante a partida*/
	private ArrayList<Integer> ataques = new ArrayList<Integer>();
	/** Quantidade de colunas do mapa*/
	public static final int comprimento = 5;
	/** Quantidade as posições no mapa*/
	public static final int tamanho = 25;
	
	/**
	 * Adiciona a frota ao mapa
	 * @param frota Frota a ser adionada ao mapa
	 * @throws PosicaoInvalidaException
	 */
	public Mapa(Frota frota) throws PosicaoInvalidaException {
		alterarPosicionamentoDaFrota(frota.clone());
	}
	
	/**
	 * Converte as coordenas de uma posição na matriz para a posição equivalente no array  
	 * @param i valor de i na matriz
	 * @param j valor de i na matriz
	 * @return A posição no array referente as coordenadas passadas. Retorna -1 caso a posição não esteja nos limites do mapa. 
	 */
	public static int converter(int i, int j) {
		int valor = i*Mapa.comprimento + j;
		if (valor > -1 && valor < Mapa.tamanho) {
			return valor;
		}
		return -1;
	}
	
	/**
	 * Altera o posicionamento da frota no mapa
	 * @param frota Frota a ser adicionada ao mapa
	 * @throws PosicaoInvalidaException
	 */
	
	public void alterarPosicionamentoDaFrota(Frota frota) throws PosicaoInvalidaException {
		locais = new int[Mapa.tamanho];
		for (int k = 0; k < frota.length(); k++) {
			Embarcacao e = frota.getEmbarcacao(k);
			for (int valor : e.getEmbarcacao()) {
				if(locais[valor] == 1)
					throw new PosicaoInvalidaException();
				locais[valor] = 1;
			}
			barcosIntactos.add(e);
		}
		this.frota = frota;
	}
	
	/**
	 * Representa o ataque do jogador. Altera um posição no mapa, caso seja uma ataque válido.
	 * @param i valor de i na matriz
	 * @param j valor de i na matriz
	 * 
	 * @return -2 - se o valor passado esteja fora dos limetes do mapa ou se a posição já tenha sido atacada
	 *, -1 - se o jogador errou o ataque
	 *, 2 - se o jogador acertou o ataque, mas não destruiu a embarcação
	 *, 3 - se o jogador acertou o ataque e destruiu a embarcação.
	 */
	public int atacar(int i, int j) {
		int valor = converter(i, j);
		return atacar(valor);
	}
	
	/**
	 * 
	 * Representa o ataque do jogador. Altera um posição no mapa, caso seja uma ataque válido.
	 * @param valor posição a ser atacada
	 * @return -2 - se o valor passado esteja fora dos limites do mapa ou se a posição já tenha sido atacada
	 *, -1 - se o jogador errou o ataque
	 *, 2 - se o jogador acertou o ataque, mas não destruiu a embarcação
	 *, 3 - se o jogador acertou o ataque e destruiu a embarcação.
	 *
	 */
	public int atacar(int valor) {
		if(valor > -1 && valor < 25) {
			if(locais[valor] == 0) {
				ataques.add(valor);
				locais[valor] = -1;
				return locais[valor];
			} else if(locais[valor] == 1) {
				ataques.add(valor);
				Embarcacao e = frota.atacar(valor); 
				if(e != null) {
					barcosIntactos.remove(e);
					locais[valor] = 3;
				} else {
					locais[valor] = 2;
				}
				return locais[valor];	
			}
		}
		return -2;
	}
	
	/**
	 * Retorna os barcos que não foram destruídos
	 * @return Todos os barcos que não foram destruídos
	 */
	public ArrayList<Embarcacao> getBarcosIntactos() {
		return barcosIntactos;
	}
	
	/**
	 * Retorna a posição do próximo ataque feito pelo usuário. 
	 * @return -1 - caso os ataques tenha acabado
	 *   
	 */
	
	public int proximoAtaque() {
		if(contar < ataques.size()) {
			return ataques.get(contar++);
		}
		return -1;
	}
	
	/**
	 * Retorna o resultado do ataque em uma posição específica no mapa
	 * @param valor Posição do ataque
	 * @return O resultado do ataque em determinada posição no mapa
	 */
	public int obterResultadoNaPosicao(int valor) {
		return locais[valor];
	}
	
	/**
	 * Retorna a quantidade de embarcações que não foram destruídas
	 * @return A quantidade de embarcações que ainda não foram destruídas
	 */
	public int contarBarcosIntactos() {
		return barcosIntactos.size();
	}
	
	/**
	 * Retorna uma String que representa o mapa atual
	 * @return A representação do mapa 
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(" ---------------------\n");
		int c = 0;
		for (int i = 0; i < Mapa.comprimento; i++) {
			s.append(" | ");
			for (int j = 0; j < Mapa.comprimento; j++) {
				int v = locais[c++]; 
				if(v == 1)
					s.append("B");
				else if(v == 0)
					s.append("A");
				else if(v == -1)
					s.append("X");
				else if(v == 2 || v == 3) 
					s.append("D");
				s.append(" | ");
			}
			s.append("\n ---------------------\n");
		}
		return s.toString();
	}
	
	public void setContar(int contar) {
		this.contar = contar;
	}
	
	public Frota getFrota() {
		return frota;
	}
}
