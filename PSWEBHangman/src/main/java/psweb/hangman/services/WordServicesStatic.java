package psweb.hangman.services;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import psweb.hangman.model.Word;
import psweb.hangman.model.dao.GenericDAO;
import psweb.hangman.model.dao.PersistenceManager;
import psweb.hangman.model.dao.WordDAOStatic;
import psweb.hangman.utils.enums.Dificuldade;

@Service
public class WordServicesStatic {


	public static Word selectWord() {
		List<Word> words = WordServicesStatic.listAllWords();
		return randomWord(words);
	}

	public static Word selectWord(Dificuldade dificuldade) {
		List<Word> words = WordServicesStatic.listByLevel(dificuldade);
		return randomWord(words);
	}

	private static List<Word> listAllWords() {
		GenericDAO<Word> wordDao = PersistenceManager.createGenericDAO(Word.class);
		return wordDao.findAll();
	}

	private static List<Word> listByLevel(Dificuldade dificuldade) {
		WordDAOStatic wordDao = new WordDAOStatic();
		return wordDao.listByLevel(dificuldade);
	}

	private static Word randomWord(List<Word> words) {
		Random random = new Random();
		if (!(words.size() == 0)) {
			int i = random.nextInt(words.size());
			Word word = words.get(i);
			return new Word(word.getWordTxt(), word.getWordHint());
		} else {
			return new Word("hello", "Greeting");
		}
	}

}
