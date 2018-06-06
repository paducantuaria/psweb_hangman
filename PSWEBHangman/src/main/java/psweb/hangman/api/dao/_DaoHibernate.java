package psweb.hangman.api.dao;
import java.util.List;

import org.hibernate.criterion.MatchMode;

import psweb.hangman.api.dao.hibernate.DaoLike;

public interface _DaoHibernate<ENTITY_TYPE> extends _DaoFull<ENTITY_TYPE>
{
	/**
	 * Força a atualização das modificações pendentes na transação atual
	 * 
	 * @return the list<type>
	 */
	public void flushSession();

	/**
	 * Busca por like em um único campo
	 * 
	 * @return the list<type>
	 */
	public List<ENTITY_TYPE> retrieveByLikeInSingleField(String field, String value, MatchMode matchMode);

	/**
	 * Busca por like em um varios campos
	 * 
	 * @return the list<type>
	 */
	public List<ENTITY_TYPE> retrieveByLikeInManyFields(DaoLike... daoLikes);
}
