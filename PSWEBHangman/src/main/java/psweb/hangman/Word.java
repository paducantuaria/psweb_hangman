package psweb.hangman;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Classe da palavra que será usada no jogo
 * 
 * @author Professor Rafael Castaneda
 * @since 1.0
 *
 */
@Entity
public class Word {
	@Id
	@GeneratedValue
	private Long id;

	private char[] wordChars;
	private char[] wordMask;
	
	/**
	 * Construtor sem argumentos para ser usado pela JPA
	 * @deprecated para indicar que não será usado na aplicação
	 */
	@Deprecated
	public Word() {
	}

	public Word(String word) {
		this.wordChars = word.toUpperCase().toCharArray();
		this.wordMask = new char[wordChars.length];

		for (int i = 0; i < wordMask.length; i++)
			wordMask[i] = '_';
	}

	/**
	 * Faz o input de um caractere na palavra
	 * 
	 * @param chr
	 *            char passado e transformado em maiúsculo
	 * @return true se a letra existe na palavra ou false caso contrário
	 * @since 1.0
	 */
	public boolean input(char chr) {
		chr = Character.toUpperCase(chr);

		boolean match = false;

		for (int i = 0; i < wordChars.length; i++) {
			if (wordChars[i] == chr && wordMask[i] == '_') {
				match = true;
				wordMask[i] = chr;
			}
		}

		return match;
	}

	/**
	 * Verifica se a palavra está completa
	 * 
	 * @return true se palavra está completa ou false caso contrário
	 * @since 1.0
	 */
	public boolean isComplete() {
		for (int i = 0; i < wordChars.length; i++) {
			if (wordMask[i] == '_') {
				return false;
			}
		}

		return true;
	}

	/**
	 * Transforma a máscara da palavra em uma string
	 * 
	 * @return wordMask convertida em String
	 * @since 1.0
	 */
	public String getWordAsString() {
		return new String(wordMask);
	}

	/**
	 * Transforma a resposta em uma String
	 * 
	 * @return wordChars convertida em String
	 * @since 1.0
	 */
	public String getAnswerAsString() {
		return new String(wordChars);
	}
}