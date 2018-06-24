package psweb.hangman.model.dao;

import org.springframework.stereotype.Repository;

import psweb.hangman.api.dao.hibernate._DaoHibernateAbstract;
import psweb.hangman.model.Player;

@Repository
public class PlayerDAOImpl extends _DaoHibernateAbstract<Player> implements PlayerDAO {
	@Override
	public Class<Player> getEntityClass() {
		return Player.class;
	}
}
