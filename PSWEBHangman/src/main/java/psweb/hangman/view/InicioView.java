package psweb.hangman.view;

import javax.faces.bean.ManagedBean;

import psweb.hangman.model.Tipo;

@ManagedBean
public class InicioView {
	private Tipo tipo;

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
}
