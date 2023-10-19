package br.com.fiap.fase6cap8.services;

import java.util.List;

import br.com.fiap.fase6cap8.daos.ContaDao;
import br.com.fiap.fase6cap8.models.Conta;
import br.com.fiap.fase6cap8.models.Usuario;

public class ContaService {
	
	ContaDao dao = new ContaDao();

	public List<Conta> getAll() {
		return dao.getAll();
	}

	public Conta getById(Long id) {
		return dao.getById(id);
	}

	public void save(String vlAgencia, String vlConta, String vlBanco, Usuario usuario, Long id) {
		Conta conta = new Conta();
		if(id != null && id > 0) {
			conta.setId(id);
		}
		conta.setUsuario(usuario);
		conta.setVlAgencia(vlAgencia);
		conta.setVlBanco(vlBanco);
		conta.setVlConta(vlConta);
		dao.save(conta);
	}

	public void delete(Long id) {
		dao.delete(id);		
	}

}
