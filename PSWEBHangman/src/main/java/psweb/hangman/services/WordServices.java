package psweb.hangman.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.hibernate.criterion.MatchMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import psweb.hangman.model.Word;
import psweb.hangman.model.dao.WordDAO;
import psweb.hangman.utils.enums.Dificuldade;

@Service
public class WordServices {

	@Autowired
	WordDAO wordDao;

	@Transactional
	public Word selectWord() {
		List<Word> words = listAllWords();
		return randomWord(words);
	}
	@Transactional
	public Word selectWord(Dificuldade dificuldade) {
		List<Word> words = listAllWords();
		List <Word> wordsSelected = new ArrayList<Word>();
		for (Word word : words) {
			if(dificuldade.equals(word.getDificuldade())){
				wordsSelected.add(word);
			}
		}
		
		
		return randomWord(wordsSelected);
	}
	@Transactional
	private List<Word> listAllWords() {
		return wordDao.retrieveAll();
	}	
	
	//TODO não está funcionando (Class cast exception String para Enum)
	@Transactional
	private List<Word> listByLevel(Dificuldade dificuldade) {
		String dificuldadeTxt = dificuldade.toString();
		return wordDao.retrieveByLikeInSingleField("dificuldade", dificuldadeTxt, MatchMode.EXACT);
	}
	@Transactional
	private Word randomWord(List<Word> words) {
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
