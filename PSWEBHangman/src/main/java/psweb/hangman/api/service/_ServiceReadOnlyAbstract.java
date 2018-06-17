package psweb.hangman.api.service;

import java.util.List;

import javax.transaction.Transactional;

import psweb.hangman.api.dao._DaoReadOnly;

public abstract class _ServiceReadOnlyAbstract<ENTITY_TYPE> implements _DaoReadOnly<ENTITY_TYPE>,_Service<ENTITY_TYPE>
{
	@Transactional
	public ENTITY_TYPE retrieveById(Integer id) 
	{
		return ((_DaoReadOnly<ENTITY_TYPE>)getBaseDao()).retrieveById(id);
	}

	@Transactional
	public List<ENTITY_TYPE> retrieveAll() 
	{
		return ((_DaoReadOnly<ENTITY_TYPE>)getBaseDao()).retrieveAll();
	}	
}