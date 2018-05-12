package psweb.hangman;

import java.util.Scanner;

import psweb.hangman.model.Hangman;

public class Game 
{
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args)
	{
		Hangman hangman = new Hangman();
		hangman.reset("sbrubbles");
		
		while (hangman.getChances()>0 && !hangman.isComplete())
		{
			System.out.println("=======================");
			System.out.println("======= HANGMAN =======");
			System.out.println("=======================");
			System.out.println("Word: "+hangman.getWordAsString());
			System.out.println("History: "+hangman.getInputHistory());
			System.out.println("Chances: "+hangman.getChances());
			
			System.out.print("Input >> ");
			
			String str = sc.nextLine();
			
			if (str.length() != 1)
			{
				System.out.println("INVALID INPUT!!! Try from a-z");
				continue;
			}
			
			char chr = str.toCharArray()[0]; 
			
			if (hangman.input(chr))
				System.out.println("Congratulations, you got a letter!");
			else
				System.out.println("Ooops, you are wrong!");
		}
		
		if (hangman.isComplete())
		{
			System.out.println("You guessed the word -- "+hangman.getWordAsString()+" --");
		}
		else
		{
			System.out.println("You are dead, the word was -- "+hangman.getAnswerAsString()+" --");
		}				
	}
}
