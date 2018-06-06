package psweb.hangman.api.dao.hibernate;

import org.hibernate.criterion.MatchMode;

public class DaoLike {
	private String field;
	private String value;
	private MatchMode matchMode;

	public DaoLike(String field, String value, MatchMode matchMode) {
		super();
		this.field = field;
		this.value = value;
		this.matchMode = matchMode;
	}

	public String getField() {
		return field;
	}

	public String getValue() {
		return value;
	}

	public MatchMode getMatchMode() {
		return matchMode;
	}
}
