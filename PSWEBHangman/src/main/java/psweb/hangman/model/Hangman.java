package psweb.hangman.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe base do Jogo
 * 
 * @author Professor Rafael Castaneda
 * @since 1.0
 */

public class Hangman {
	
	
	private int chances = 6;
	private List<Character> history;
		
	private Word currentWord;

	public Hangman() {
		history = new ArrayList<Character>();
	}
	
	/**
	 * Reinicia o jogo e sorteia uma nova palavra
	 * 
	 * @since 1.0	 * 
	 */
	public void reset() {
		reset("Hello"); // TODO - Buscar de um dicionario acrescentar parâmetro de dificuldade no construtor

	}
	
	
	/**
	 * Reinicia o jogo com uma palavra escolhida
	 * 
	 * @param forcedWord palavra que será utilizada no construtor
	 */
	public void reset(String forcedWord) {
		currentWord = new Word(forcedWord);
		chances = 6;
		history = new ArrayList<Character>();
	}

	
	/**
	 * Faz o input de um caractere
	 * 
	 * @param chr caractere a ser inserido
	 * @return método input de Word em currentWord 
	 */
	public boolean input(Character chr) {
		boolean match = currentWord.input(chr);

		// TODO implementar lógica do contador de vidas (caso uma letra repetida seja inserida, etc)
		// Atualiza o contador de vidas
		if (!match)
			chances--;

		// Atualiza o histórico
		if(!history.contains(Character.toUpperCase(chr))) {
			history.add(Character.toUpperCase(chr));
		}		

		return match;
	}

	/**
	 * Verifica se a palavra está completa
	 * 
	 * @return método isComplete de Word em currentWord
	 */
	public boolean isComplete() {
		return currentWord.isComplete();
	}

	//
	// Métodos de acesso
	//
	public int getChances() {
		return chances;
	}

	public String getWordAsString() {
		return currentWord.getWordAsString();
	}

	public String getAnswerAsString() {
		return currentWord.getAnswerAsString();
	}

	public List<Character> getInputHistory() {
		return history;
	}

	public boolean isGameOver() {
		return chances == 0 || isComplete();
	}
}
