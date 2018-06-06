package psweb.hangman.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import psweb.hangman.model.Player;
import psweb.hangman.model.dao.GenericDAO;
import psweb.hangman.model.dao.PersistenceManager;

@Service
public class PlayerServices {

	@Transactional
	public static List<Player> listAllPlayers() {
		GenericDAO<Player> playerDao = PersistenceManager.createGenericDAO(Player.class);
		return playerDao.findAll();
	}

}
