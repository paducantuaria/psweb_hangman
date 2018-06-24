package psweb.hangman.model.dao;

import java.util.List;

import javax.persistence.EntityManager;

import psweb.hangman.model.Word;
import psweb.hangman.utils.enums.Dificuldade;

public class WordDAOStatic extends GenericDAO<Word>{

	EntityManager em = PersistenceManager.getEntityManager();

	public WordDAOStatic() {
		super(Word.class, PersistenceManager.getEntityManager());		
	}
	
	@SuppressWarnings("unchecked")
	public List<Word> listByLevel(Dificuldade dificuldade){		
		List<Word> words = em.createQuery(
			    "SELECT w FROM Word w WHERE w.dificuldade LIKE :dificuldade")
			    .setParameter("dificuldade", dificuldade)
			    .getResultList();		
		return words;
	}

}
	
	


