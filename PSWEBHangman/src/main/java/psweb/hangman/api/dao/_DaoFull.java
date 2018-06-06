package psweb.hangman.api.dao;
public interface _DaoFull<ENTITY_TYPE> extends _DaoReadOnly<ENTITY_TYPE>
{
	/**
     * Insere ou Atualiza um Objeto.
     * 
     * @param object the object
     */	    
    public void saveUpdate(ENTITY_TYPE object);     	    	    
	
    /**
     * Remove um Objeto.
     * 
     * @param object the object
     */		    
	public void delete(ENTITY_TYPE object);
}