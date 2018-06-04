package psweb.hangman.model;

import java.util.List;
import java.util.Random;

import psweb.hangman.model.dao.GenericDAO;
import psweb.hangman.model.dao.PersistenceManager;

public class WordServices {

	public static Word selectWord() {
		Random random = new Random();
		List<Word> words = WordServices.listAllWords();
		if(!(words.size() == 0)) {
		int i = random.nextInt(words.size());		
		Word word = words.get(i);
		return new Word(word.getWordTxt(), word.getWordHint());
		}else {
			return new Word("hello","Greeting");
		}
	}

	public static List<Word> listAllWords() {
		GenericDAO<Word> wordDao = PersistenceManager.createGenericDAO(Word.class);
		return wordDao.findAll();
	}

}
