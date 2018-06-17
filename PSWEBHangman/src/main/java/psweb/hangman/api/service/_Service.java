package psweb.hangman.api.service;

import psweb.hangman.api.dao._Dao;

public interface _Service<ENTITY_TYPE> extends _Dao<ENTITY_TYPE>
{
	public abstract _Dao<ENTITY_TYPE> getBaseDao();
}