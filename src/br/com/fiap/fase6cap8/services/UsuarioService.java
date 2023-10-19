package br.com.fiap.fase6cap8.services;

import java.util.List;

import br.com.fiap.fase6cap8.daos.UsuarioDao;
import br.com.fiap.fase6cap8.models.Usuario;

public class UsuarioService {
	
	UsuarioDao dao = new UsuarioDao();

	public List<Usuario> getAll() {
		return dao.getAll();
	}

	public Usuario getById(long id) {
		return dao.getById(id);
	}

	public void save(String vlCpf, String dsSenha, String nmUsuario, Boolean ckUsuarioAtivo, Long id) {
		Usuario usuario = new Usuario();
		if(id != null && id > 0) {
			usuario.setId(id);
		}
		usuario.setVlCpf(vlCpf);
		usuario.setNmUsuario(dsSenha);
		usuario.setDsSenha(nmUsuario);
		usuario.setCkUsuarioAtivo(ckUsuarioAtivo);
		dao.save(usuario);
	}

	public void delete(long id) {
		dao.delete(id);		
	}

}
