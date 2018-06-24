package psweb.hangman.model.dao;

import java.util.List;

import javax.persistence.EntityManager;

/**
 * Implementação do padrão DAO
 * 
 * @author Paulo Cantuária
 * @since 1.0
 *
 * @param <T>
 */
public class GenericDAO<T> {

	private EntityManager manager;
	private Class<T> t;

	GenericDAO(Class<T> t, EntityManager manager) {
		this.t = t;
		this.manager = manager;
	}

	public List<T> findAll() {
		@SuppressWarnings("unchecked")
		List<T> lista = manager.createQuery("from " + t.getName()).getResultList();

		return lista;
	}

	public T find(Long id) {
		return manager.find(t, id);
	}

	public void insert(T entidade) {
		manager.persist(entidade);
	}

	public void delete(T entidade) {
		manager.remove(entidade);
	}

	public void update(T entidade) {
		manager.merge(entidade);
	}

}