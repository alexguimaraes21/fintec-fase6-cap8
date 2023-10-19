package br.com.fiap.fase6cap8.models;

import java.util.Date;

public class Lancamento {
	
	private Long id;
	private double vlLancamento;
	private Date dtLancamento;
	private String dsLancamento;
	private TipoLancamento tipoLancamento;
	private TipoInvestimento tipoIvestimento;
	private Conta conta;
	private Usuario usuario;
	
	public Lancamento(Long id, double vlLancamento, Date dtLancamento, String dsLancamento,
			TipoLancamento tipoLancamento, TipoInvestimento tipoIvestimento, Conta conta, Usuario usuario) {
		this.id = id;
		this.vlLancamento = vlLancamento;
		this.dtLancamento = dtLancamento;
		this.dsLancamento = dsLancamento;
		this.tipoLancamento = tipoLancamento;
		this.tipoIvestimento = tipoIvestimento;
		this.conta = conta;
		this.usuario = usuario;
	}

	public Lancamento() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getVlLancamento() {
		return vlLancamento;
	}

	public void setVlLancamento(double vlLancamento) {
		this.vlLancamento = vlLancamento;
	}

	public Date getDtLancamento() {
		return dtLancamento;
	}

	public void setDtLancamento(Date dtLancamento) {
		this.dtLancamento = dtLancamento;
	}

	public String getDsLancamento() {
		return dsLancamento;
	}

	public void setDsLancamento(String dsLancamento) {
		this.dsLancamento = dsLancamento;
	}

	public TipoLancamento getTipoLancamento() {
		return tipoLancamento;
	}

	public void setTipoLancamento(TipoLancamento tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
	}

	public TipoInvestimento getTipoIvestimento() {
		return tipoIvestimento;
	}

	public void setTipoIvestimento(TipoInvestimento tipoIvestimento) {
		this.tipoIvestimento = tipoIvestimento;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
