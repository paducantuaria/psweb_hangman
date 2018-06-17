package psweb.hangman.api.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.criterion.MatchMode;

import psweb.hangman.api.dao._DaoHibernate;
import psweb.hangman.api.dao.hibernate.DaoLike;

public abstract class _ServiceHibernateAbstract<ENTITY_TYPE> extends _ServiceFullAbstract<ENTITY_TYPE> implements _DaoHibernate<ENTITY_TYPE>   
{
	@Override
	@Transactional
	public void flushSession() 
	{
		((_DaoHibernate<ENTITY_TYPE>)getBaseDao()).flushSession();		
	}

	@Override
	@Transactional
	public List<ENTITY_TYPE> retrieveByLikeInSingleField(String field, String value, MatchMode matchMode) 
	{
		return ((_DaoHibernate<ENTITY_TYPE>)getBaseDao()).retrieveByLikeInSingleField(field, value, matchMode);
	}

	@Override
	@Transactional
	public List<ENTITY_TYPE> retrieveByLikeInManyFields(DaoLike... daoLikes) 
	{
		return ((_DaoHibernate<ENTITY_TYPE>)getBaseDao()).retrieveByLikeInManyFields(daoLikes);
	}

}