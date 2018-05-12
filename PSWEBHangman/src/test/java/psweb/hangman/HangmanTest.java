package psweb.hangman;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class HangmanTest 
{
	@Test
	public void hangmanTest()
	{
		// Inicia um novo jogo
		Hangman hangman = new Hangman();
		hangman.reset("hello");
		
		// Testa a palavra inicial
		String wordWithMask = hangman.getWordAsString();
		Assert.assertEquals("_____",wordWithMask);
		
		// Testa um input errado
		Assert.assertEquals(6, hangman.getChances());
		Assert.assertFalse(hangman.input('a'));
		Assert.assertEquals(5, hangman.getChances());
		
		// Testa um input correto
		Assert.assertTrue(hangman.input('h'));
		Assert.assertEquals(5, hangman.getChances());
		Assert.assertEquals("H____",hangman.getWordAsString());
		
		// Testa o controle de histórico
		List<Character> history = hangman.getInputHistory();
		Assert.assertEquals(2, history.size());
		Assert.assertEquals(new Character('A'), history.get(0));
		Assert.assertEquals(new Character('H'), history.get(1));
	}

}
