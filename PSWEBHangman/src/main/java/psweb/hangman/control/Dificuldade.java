package psweb.hangman.control;

public enum Dificuldade {
	FACIL("Fácil"), MEDIO("Médio"), DIFICIL("Difícil");

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

