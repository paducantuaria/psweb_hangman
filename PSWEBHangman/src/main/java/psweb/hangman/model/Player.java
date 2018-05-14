package psweb.hangman.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Classe que representa o jogador
 * 
 * @author Paulo Cantuária
 * @since 1.0
 */
@Entity
public class Player {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
}
