package psweb.hangman.utils.enums;

public enum Dificuldade {
	EASY("Easy"), NORMAL("Normal"), HARD("Hard");

	private final String dificuldade;

	Dificuldade(String dificuldade) {
		this.dificuldade = dificuldade;
	}

	public static Dificuldade[] asList() {
		return Dificuldade.values();
	}

	public String getDificuldade() {
		return dificuldade;
	}
}

