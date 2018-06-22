package psweb.hangman.control;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import psweb.hangman.model.Hangman;
import psweb.hangman.model.Player;
import psweb.hangman.services.PlayerServices;
import psweb.hangman.utils.enums.Dificuldade;
import psweb.hangman.utils.enums.Tipo;

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
	private String currentHint;
	private boolean isSoundPlaying;

	@Autowired
	private ConfigBean config;

	//
	// Campos do Formulário
	//
	private String letter = "";
	private String hint = "";
	private String wordFromPlayer = "";

	//
	// Construtor
	//
	public HangmanBean() {
		this.hangman = new Hangman();
	}

	public ConfigBean getConfigBean() {
		return config;
	}

	// TODO Criar método para instanciar hangman com a palavra passada pelo jogador
	// no modo vs
	
	
	public void setDifficulty(ActionEvent event) {
		config.setDifficulty(event);
		start();
	}
	

	/**
	 * Método para iniciar uma nova partida, conforme as configurações setadas e
	 * estado atual do jogo
	 * 
	 * @author Paulo Cantuária e Augusto José
	 */
	public void start() {
		System.out.println(config); // TODO remover SYSO
		this.isSoundPlaying = true;
		currentPlayer = config.getPlayer1();
		reset();
	}

	//
	// Operações
	//
	public void guess() {
		char chr = letter.toCharArray()[0];
		hangman.input(chr);
		letter = "";
		if (currentHint != hangman.getTrueHint()) {
			hint = "";
		}
	}

	public void reset() {
		System.out.println(config); // TODO remover SYSO
		int score = 0;
		letter = "";
		hint = "";
		currentHint = "";

		Player p1 = config.getPlayer1();
		
		//TODO Ver onde fica melhor essa lógica de pontuação
		if (Tipo.ONEPLAYER.equals(config.getTipo())) {
			//if (isGameWin()) { // TODO isGameWin precisa de um currentWord que, na primeira vez, não existe ainda neste ponto
			if (false) {
				Dificuldade dificulty = config.getDificuldade();
				switch (dificulty) {
				case EASY:
					score = 2;
					break;
				case NORMAL:
					score = 3;
					break;
				case HARD:
					score = 4;
					break;
				}

				if (isHintDisplayed()) {
					score--;
				}
				p1.setCurrentScore(score);
			}
			p1.setScore(p1.getScore() + p1.getCurrentScore());
			PlayerServices.insert(p1);
			hangman.reset(config.getDificuldade());

		} else if (Tipo.TWOPLAYER.equals(config.getTipo())) {
			Player p2 = config.getPlayer2();
			if (currentPlayer.equals(p1)) {
				currentPlayer = p2;
			} else {
				currentPlayer = p1;
			}
			hangman.reset(wordFromPlayer);
		} else {
			System.out.println("Deu Errado"); // TODO remover SYSO
			hangman.reset();
		}

		// TODO Setar novo jogador corrente, persistir score do jogador

	}

	public void showHint() {
		currentHint = hangman.getHint();
		hint = currentHint == hangman.getTrueHint() ? "Hint: " + currentHint : "No Hint: " + currentHint;
	}

	public void throwHome() {
		this.isSoundPlaying = true;
	}

	public boolean toggleSound() {
		isSoundPlaying = this.isSoundPlaying ? false : true;
		return isSoundPlaying;
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

	public String getHint() {
		return this.hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public boolean isHintDisplayed() {
		return this.currentHint.equals(hangman.getTrueHint()) ? true : false;
	}

	public boolean isSoundPlaying() {
		return isSoundPlaying;
	}
}
