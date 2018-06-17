package psweb.hangman.api.service;

import javax.transaction.Transactional;

import psweb.hangman.api.dao._DaoFull;

public abstract class _ServiceFullAbstract<ENTITY_TYPE> extends _ServiceReadOnlyAbstract<ENTITY_TYPE> implements _DaoFull<ENTITY_TYPE>   
{		
	@Override
	@Transactional
	public void saveUpdate(ENTITY_TYPE object) 
	{
		((_DaoFull<ENTITY_TYPE>)getBaseDao()).saveUpdate(object);		
	}

	@Override
	@Transactional
	public void delete(ENTITY_TYPE object) 
	{
		((_DaoFull<ENTITY_TYPE>)getBaseDao()).delete(object);		
	}

	
}