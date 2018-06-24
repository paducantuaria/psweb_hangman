package psweb.hangman.services;

import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import psweb.hangman.model.Player;
import psweb.hangman.model.dao.PlayerDAO;

@Service
public class PlayerServicesCasta 
{
	@Autowired
	PlayerDAO playerDao;
	
	@Transactional
	public List<Player> listAllPlayers() 
	{
		return playerDao.retrieveAll();
	}

	@Transactional
	public void saveUpdate(Player player) 
	{
		playerDao.saveUpdate(player);		
	}

	@Transactional
	public Player retrieveByNome(String name) 
	{
		List<Player> result = playerDao.retrieveByLikeInSingleField("name", name, MatchMode.EXACT);
		
		if (result.isEmpty())
			return null;
		else
			return result.get(0);
	}
}
