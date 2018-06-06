package psweb.hangman.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import psweb.hangman.utils.enums.Dificuldade;

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

	@Transient
	private char[] wordChars;
	@Transient
	private char[] wordMask;

	private String wordHint;
	private String wordTxt;
	
	@Enumerated(EnumType.STRING)
	private Dificuldade dificuldade;

	/**
	 * Construtor sem argumentos para ser usado pela JPA
	 * 
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

	public Word(String word, String hint) {
		this.wordChars = word.toUpperCase().toCharArray();
		this.wordMask = new char[wordChars.length];

		for (int i = 0; i < wordMask.length; i++)
			wordMask[i] = '_';

		this.wordHint = hint;
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

	public String getWordHint() {
		return wordHint;
	}

	public void setWordHint(String wordHint) {
		this.wordHint = wordHint;
	}	

	public String getWordTxt() {
		return wordTxt;
	}

	public void setWordTxt(String wordTxt) {
		this.wordTxt = wordTxt;
	}
  
	public Dificuldade getDificuldade() {
		return dificuldade;
	}

	public void setDificuldade(Dificuldade dificuldade) {
		this.dificuldade = dificuldade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Word other = (Word) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

