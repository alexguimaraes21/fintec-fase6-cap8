package br.com.fiap.fase6cap8.models;

public class TipoInvestimento {
	
	private Long id;
	private String dsTipoInvestimento;
	private double vlRendimentoMensal;
	private int prazoMinimoInvestimento;
	
	public TipoInvestimento(Long id, String dsTipoInvestimento, double vlRendimentoMensal, int prazoMinimoInvestimento) {
		this.id = id;
		this.dsTipoInvestimento = dsTipoInvestimento;
		this.vlRendimentoMensal = vlRendimentoMensal;
		this.prazoMinimoInvestimento = prazoMinimoInvestimento;
	}

	public TipoInvestimento() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDsTipoInvestimento() {
		return dsTipoInvestimento;
	}

	public void setDsTipoInvestimento(String dsTipoInvestimento) {
		this.dsTipoInvestimento = dsTipoInvestimento;
	}

	public double getVlRendimentoMensal() {
		return vlRendimentoMensal;
	}

	public void setVlRendimentoMensal(double vlRendimentoMensal) {
		this.vlRendimentoMensal = vlRendimentoMensal;
	}

	public int getPrazoMinimoInvestimento() {
		return prazoMinimoInvestimento;
	}

	public void setPrazoMinimoInvestimento(int prazoMinimoInvestimento) {
		this.prazoMinimoInvestimento = prazoMinimoInvestimento;
	}
}
