package psweb.hangman.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import psweb.hangman.model.Player;
import psweb.hangman.model.dao.GenericDAO;
import psweb.hangman.model.dao.PersistenceManager;

@Service
public class PlayerServices 
{
	/*
	@Transactional
	public List<Player> listAllPlayers() 
	{
		GenericDAO<Player> playerDao = PersistenceManager.createGenericDAO(Player.class);
		return playerDao.findAll();
	}

	@Transactional
	public void insert(Player player) 
	{
		GenericDAO<Player> playerDao = PersistenceManager.createGenericDAO(Player.class);
		PersistenceManager.getTransaction().begin();
		try {	
			//TODO Ver lógica de inserir player duplicado
		playerDao.insert(player);
		PersistenceManager.getTransaction().commit();
		}catch(Exception e ) {
			PersistenceManager.getTransaction().rollback();

		}
	}

	public Player retrieveByNome(String name) 
	{
		return null;
	}
	*/
}