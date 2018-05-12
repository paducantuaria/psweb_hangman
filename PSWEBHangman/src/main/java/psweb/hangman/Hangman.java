package psweb.hangman;

import java.util.ArrayList;
import java.util.List;

public class Hangman 
{
	private int chances = 6;
	private Word currentWord;
	private List<Character> history;
	
	public Hangman()
	{
		history = new ArrayList<Character>();
	}
	
	// Sorteia uma nova palavra
	public void reset()
	{
		reset("Hello"); // TODO - Buscar de um dicionario		
	}
	
	public void reset(String forcedWord)
	{
		currentWord = new Word(forcedWord);
		chances = 6;
		history = new ArrayList<Character>();
	}
	
	// Faz input de um caractere
	public boolean input(char chr)
	{
		boolean match = currentWord.input(chr);				
		
		// Atualiza o contador de vidas
		if (!match)
			chances--;
		
		// Atualiza o histórico
		history.add(Character.toUpperCase(chr));
		
		return match;
	}
	
	public boolean isComplete()
	{
		return currentWord.isComplete();
	}	
	
	public boolean isGameOver()
	{
		return chances==0 || isComplete(); 
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
}
