package psweb.hangman.control;

import java.io.IOException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import psweb.hangman.model.Hangman;
import psweb.hangman.model.Player;
import psweb.hangman.services.PlayerServicesCasta;
import psweb.hangman.services.WordServices;
import psweb.hangman.utils.enums.Dificuldade;

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
	private Player currentEnemy;
	private String currentHint;
	private boolean isSoundPlaying;
	private boolean inGame = false;
	private String wordFromPlayer;

	@Autowired
	public ConfigBean config;
	@Autowired
	private PlayerServicesCasta playerServices;
	@Autowired
	private WordServices wordServices;

	//
	// Campos do Formulário
	//
	private String letter = "";
	private String hint = "";

	//
	// Construtor
	//
	public HangmanBean() {
		this.hangman = new Hangman();
	}

	public ConfigBean getConfigBean() {
		return config;
	}

	/**
	 * Configura os players para o jogo atual
	 */
	public void configPlayers() {
		// seta o player1
		currentPlayer = config.getPlayer1();
		Player p1PreExist = null;

		if (currentPlayer.getName().trim().isEmpty() || currentPlayer.getName() == null) {
			currentPlayer.setName("Player 1");
		}
		p1PreExist = playerServices.retrieveByNome(currentPlayer.getName());
		if (p1PreExist != null) {
			currentPlayer.setId(p1PreExist.getId());
		}
		currentPlayer.setCurrentScore(0);

		// seta o player2 se for o caso e zera o scores
		if (config.isTwoPlaying()) {
			currentEnemy = config.getPlayer2();
			currentEnemy.setCurrentScore(0);
			if(config.getPlayer2().getName().trim().isEmpty() || config.getPlayer2().getName() == null) {
				config.getPlayer2().setName("Player 2");
			}
		}
	}

	/**
	 * Configura a dificuldade conforme a opção selecioanda pelo usuário
	 * 
	 * @param event Evento que captura o parâmetro passado pelo usuário
	 */
	public void setDifficulty(ActionEvent event) {
		config.setDifficulty(event);
		firstReset();
	}

	/**
	 * Método para configuração do jogo na primeira inicialização
	 * 
	 */
	public void firstReset() {
		this.setSoundPlaying(true);
		this.setInGame(true);

		// prepara o jogo para iniciar
		reset();
	}

	/**
	 * Prepara a tela para início do jogo
	 */
	public void reset() {
		letter = "";
		hint = "";
		currentHint = "";

		if (!config.isTwoPlaying())
			hangman.reset(config.getDificuldade());
		else
			hangman.reset(wordFromPlayer);
	}

	/**
	 * Finaliza o jogo e redireciona para a tela final
	 */
	private void finishGame() {
		// calcula o score obtido pelo jogador
		int score = computeScore();

		// atualiza o score do current player
		if (score != 0) {
			saveScoreToCurrentPlayer(score);
		}

		// se dois jogadores, alterna current player e enemy
		if (config.isTwoPlaying())
			togglePlayers();

		// redireciona para página de fim de jogo
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("endgame.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}

		// abre modal de fim de jogo
		// Map<String,Object> options = new HashMap<String, Object>();
		// options.put("modal", true);
		// PrimeFaces.current().dialog.openDynamic("endGame", options, null);
		// RequestContext.getCurrentInstance().openDialog("dlgEndGame", options, null);

	}

	/**
	 * Computa o score obtido na rodada
	 * 
	 * @return score (int) score calculado.
	 */
	private int computeScore() {
		// se perdeu, retorna 0;
		if (isGameLose())
			return 0;

		// se não perdeu e jogo está no modo TWOPLAYERS
		if (config.isTwoPlaying())
			return isGameWin() ? 1 : 0;

		// se não perdeu e jogo está no modo ONEPLAYER
		return computeScoreFromDificulty();
	}

	/**
	 * Computa o score baseado na dificuldade escolhida e uso da dica
	 * 
	 * @return score (int) score calculado.
	 */
	private int computeScoreFromDificulty() {
		int score = 0;

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

		return score;
	}

	/**
	 * Atualiza o score do currentPlayer e persiste no banco se for modo ONEPLAYER
	 * 
	 * @param score (int) score obtido na rodada.
	 */
	private void saveScoreToCurrentPlayer(int score) {
		// atualiza o score atual
		currentPlayer.setCurrentScore(currentPlayer.getCurrentScore() + score);

		// se for modo ONEPLAYER, atualiza o score acumulado no banco
		if (!config.isTwoPlaying()) {
			currentPlayer.setScore(currentPlayer.getScore() + score);
			playerServices.saveUpdate(currentPlayer);
		}
	}

	/**
	 * Alterna os players no modo TWOPLAYERS
	 * 
	 */
	private void togglePlayers() {
		// TODO apagar sysos
		System.out.print("Player 1: ");
		System.out.println(config.getPlayer1().getName());
		System.out.print("Player 2: ");
		System.out.println(config.getPlayer2().getName());
		System.out.print("Jogador corrente: ");
		System.out.println(currentPlayer.getName());

		if (currentPlayer.equals(config.getPlayer1())) {
			System.out.println("Jogador corrente == Player1");
			currentPlayer = config.getPlayer2();
			currentEnemy = config.getPlayer1();
		} else {
			System.out.println("Jogador corrente != Player1");
			currentPlayer = config.getPlayer1();
			currentEnemy = config.getPlayer2();
		}
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
		if (isGameOver())
			finishGame();
	}

	public void showHint() {
		currentHint = hangman.getHint();
		hint = currentHint == hangman.getTrueHint() ? "Hint: " + currentHint : "No Hint: " + currentHint;
	}

	public void throwHome() {
		this.setSoundPlaying(false);
		this.setInGame(false);
	}

	public boolean toggleSound() {
		this.setSoundPlaying(this.isSoundPlaying ? false : true);
		return isSoundPlaying;
	}

	//
	// Métodos de Acesso
	//
	public String getWord() {
		return hangman.getWordAsString();
	}

	public String getAnswer() {
		return hangman.getAnswerAsString();
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

	public Player getCurrentEnemy() {
		return currentEnemy;
	}

	public void setCurrentEnemy(Player currentEnemy) {
		this.currentEnemy = currentEnemy;
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

	public void setSoundPlaying(boolean soundPlaying) {
		this.isSoundPlaying = soundPlaying;
	}

	public boolean isInGame() {
		return inGame;
	}

	public void setInGame(boolean inGame) {
		this.inGame = inGame;
	}

	public String getWordFromPlayer() {
		return wordFromPlayer;
	}

	public void setWordFromPlayer(String wordFromPlayer) {
		this.wordFromPlayer = wordFromPlayer;
	}


}

