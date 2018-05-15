package psweb.hangman.control;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.stereotype.Component;

import psweb.hangman.model.Player;
import psweb.hangman.model.Tipo;

/**
 * Classe bean para a configuração do jogo
 * @author Paulo Cantuária e Augusto
 * @since 1.0
 */
@Component
@ManagedBean
@SessionScoped
public class ConfigBean {
	
	private Dificuldade dificuldade;	
	private List<Player> players;	
	private Tipo tipo; // Um jogador / dois jogadores
	private int qtdPArtidas;
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
	
	

}
