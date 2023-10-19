package br.com.fiap.fase6cap8.models;

public class Conta {
	
	private Long id;
	private String vlAgencia;
	private String vlConta;
	private String vlBanco;
	private Usuario usuario;
		
	public Conta() {}
	
	public Conta(Long id, String vlAgencia, String vlConta, String vlBanco, Usuario usuario) {
		this.id = id;
		this.vlAgencia = vlAgencia;
		this.vlConta = vlConta;
		this.vlBanco = vlBanco;
		this.usuario = usuario;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getVlAgencia() {
		return vlAgencia;
	}
	
	public void setVlAgencia(String vlAgencia) {
		this.vlAgencia = vlAgencia;
	}
	
	public String getVlConta() {
		return vlConta;
	}
	
	public void setVlConta(String vlConta) {
		this.vlConta = vlConta;
	}
	
	public String getVlBanco() {
		return vlBanco;
	}
	
	public void setVlBanco(String vlBanco) {
		this.vlBanco = vlBanco;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
