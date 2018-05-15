package psweb.hangman.control;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import psweb.hangman.model.Hangman;
import psweb.hangman.model.Player;
import psweb.hangman.model.Tipo;

/**
 * Classe Bean
 * 
 * @author Professor Rafael Castaneda
 * @since 1.0
 *
 */
@Component
@ManagedBean
@SessionScoped
public class HangmanBean extends _Bean {
	//
	// Atributos
	//
	private Hangman hangman;
	private Player currentPlayer;

	@Autowired
	private ConfigBean config; // TODO ver com Castaneda se precisa de Get/Set

	//
	// Campos do Formulário
	//
	private String letter = "";

	//
	// Construtor
	//
	public HangmanBean() {
		if (config.getTipo().equals(Tipo.ONEPLAYER)) {
			this.hangman = new Hangman();
			this.hangman.reset();
		}
	}

	// TODO Criar método para instanciar hangman com a palavra passada pelo jogador
	// no modo vs

	// TODO Método que inicia uma nova partida de acordo com as configurações setadas e o estado atual do jogo
	
	
	//
	// Operações
	//
	public void guess() {
		char chr = letter.toCharArray()[0];
		hangman.input(chr);
		letter = "";
	}

	public void reset() {
		hangman.reset();
		letter = "";
		// TODO Setar novo jogador corrente, persistir score do jogador
	}

	//
	// Métodos de Acesso
	//
	public String getWord() {
		return hangman.getWordAsString();
	}

	public Integer getChances() {
		return hangman.getChances();
	}

	public String getAttempts() {
		List<Character> history = hangman.getInputHistory();
		history.sort(null);
		return history.toString();
	}

	public boolean isGameOver() {
		return hangman.isGameOver();
	}

	public boolean isGameWin() {
		return hangman.isComplete();
	}

	public boolean isGameLose() {
		return hangman.getChances() == 0;
	}

	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

	public ConfigBean getConfig() {
		return config;
	}

	public void setConfig(ConfigBean config) {
		this.config = config;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
}
