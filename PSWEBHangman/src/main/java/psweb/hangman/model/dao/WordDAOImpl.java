package psweb.hangman.model.dao;

import org.springframework.stereotype.Repository;

import psweb.hangman.api.dao.hibernate._DaoHibernateAbstract;
import psweb.hangman.model.Word;

@Repository
public class WordDAOImpl extends _DaoHibernateAbstract<Word> implements WordDAO
{
	@Override
	public Class<Word> getEntityClass() 
	{
		return Word.class;
	}	
	
}