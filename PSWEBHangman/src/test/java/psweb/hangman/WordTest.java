package psweb.hangman;

import org.junit.Test;
import org.junit.Assert;

public class WordTest 
{
	@Test
	public void wordTest()
	{
		Word word = new Word("Hello");
		
		String wordWithMask = word.getWordAsString();
		Assert.assertEquals("_____",wordWithMask);
		Assert.assertFalse(word.isComplete());
				
		boolean match = word.input('l');
		Assert.assertTrue(match);
		wordWithMask = word.getWordAsString();
		Assert.assertEquals("__LL_",wordWithMask);
		
		match = word.input('a');
		Assert.assertFalse(match);
		wordWithMask = word.getWordAsString();
		Assert.assertEquals("__LL_",wordWithMask);
		
		word.input('h');
		word.input('e');
		word.input('o');
		boolean complete = word.isComplete();
		Assert.assertTrue(complete);
		wordWithMask = word.getWordAsString();
		Assert.assertEquals("HELLO",wordWithMask);
	}
}


