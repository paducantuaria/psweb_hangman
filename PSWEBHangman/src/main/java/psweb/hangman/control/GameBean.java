package psweb.hangman.control;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.stereotype.Component;

import psweb.hangman.model.Hangman;
import psweb.hangman.model.Player;
import psweb.hangman.model.Tipo;

/**
 * Classe bean para a tela do Jogo
 * @author Paulo Cantuária
 * @since 1.0
 */
@Component
@ManagedBean
@SessionScoped
public class GameBean {
	
	//
	// Atributos
	//
	private Hangman hangman;
	private List<Player> players;	
	private Tipo tipo;
	
	private Player currentPlayer;

	private ConfigBean config;

	//
	// Campos do Formulário
	//
	
		
	
	
}
