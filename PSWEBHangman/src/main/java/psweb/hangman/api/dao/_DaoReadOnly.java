package psweb.hangman.api.dao;
import java.util.List;

public interface _DaoReadOnly<ENTITY_TYPE> extends _Dao<ENTITY_TYPE> 
{       
	/**
	 * Busca um Objeto por Id.
	 * 
	 * @param id the id
	 * 
	 * @return the TYPE
	 */ 
	public ENTITY_TYPE retrieveById(Integer id);
	
	/**
	 * Busca um Objeto por Exemplos.
	 * 
	 * @return the list< typ e>
	 */        
	public List<ENTITY_TYPE> retrieveAll();
}
