package psweb.hangman.model;

public enum Tipo {
	ONEPLAYER("Um Jogador"), TWOPLAYER("Dois Jogadores");

	private final String tipo;

	Tipo(String tipo) {
		this.tipo = tipo;
	}

	public static Tipo[] asList() {
		return Tipo.values();
	}

	public String getTipo() {
		return tipo;
	}
}
