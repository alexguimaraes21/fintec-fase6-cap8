package br.com.fiap.fase6cap8.models;

public class TipoLancamento {
	
	private Long id;
	private String dsTipo;
	
	public TipoLancamento(Long id, String dsTipo) {
		this.id = id;
		this.dsTipo = dsTipo;
	}

	public TipoLancamento() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDsTipo() {
		return dsTipo;
	}

	public void setDsTipo(String dsTipo) {
		this.dsTipo = dsTipo;
	}
}
