package psweb.hangman;

public class Word 
{
	private char[] wordChars;
	private char[] wordMask;
	
	public Word(String word)
	{
		this.wordChars = word.toUpperCase().toCharArray();
		this.wordMask  = new char[wordChars.length];
		
		for (int i=0;i<wordMask.length;i++)
			wordMask[i] = '_';		
	}
	
	public boolean input(char chr)
	{
		chr = Character.toUpperCase(chr);
		
		boolean match = false;
		
		for (int i=0;i<wordChars.length;i++)
		{
			if (wordChars[i] == chr && wordMask[i] == '_')
			{
				match = true;
				wordMask[i] = chr;
			}									
		}
		
		return match;
	}

	public boolean isComplete()
	{
		for (int i=0;i<wordChars.length;i++)
		{
			if (wordMask[i] == '_')
			{
				return false;
			}									
		}
		
		return true;
	}
	
	public String getWordAsString()
	{
		return new String(wordMask);
	}	
	
	public String getAnswerAsString()
	{
		return new String(wordChars);
	}	
}
