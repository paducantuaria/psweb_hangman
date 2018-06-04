package psweb.hangman.control;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.stereotype.Component;

import psweb.hangman.model.Player;
import psweb.hangman.model.Tipo;

/**
 * Classe bean para a configuração do jogo
 * 
 * @author Paulo Cantuária e Augusto 
 * @since 1.0
 */
@Component
@ManagedBean
@SessionScoped
public class ConfigBean extends _Bean {

	private Dificuldade dificuldade;
	private List<Player> players;
	private Player player1;
	private Player player2;
	private Tipo tipo; // Um jogador / dois jogadores	
	private int qtdPArtidas;
	
	//
	// Campos do Formulário
	//
	
	
	//
	// Construtor
	//
	public ConfigBean() {
		this.player1 = new Player();
		this.player2 = new Player();
	}
	
	//
	// Operações
	//
	
	public void setOnePlayer() {
		this.tipo = tipo.ONEPLAYER;
	}
	
	public void setTwoPlayers() {
		this.tipo = tipo.TWOPLAYER;
	}
	
	//
	// Métodos de Acesso
//
	
	

	public Dificuldade getDificuldade() {
		return dificuldade;
	}

	public void setDificuldade(Dificuldade dificuldade) {
		this.dificuldade = dificuldade;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public int getQtdPArtidas() {
		return qtdPArtidas;
	}

	public void setQtdPArtidas(int qtdPArtidas) {
		this.qtdPArtidas = qtdPArtidas;
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}
	
	public boolean isTwoPlayers() {
		if("Dois Jogadores".equals(this.tipo.getTipo())) {
			return true;
		}
		return false;
	}
	
	public void setEasyDifficulty() {
		this.dificuldade = dificuldade.FACIL;
	}
	
	public void setNormalDifficulty() {
		this.dificuldade = dificuldade.MEDIO;
	}
	
	public void setHardDifficulty() {
		this.dificuldade = dificuldade.DIFICIL;
}
}
