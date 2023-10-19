package br.com.fiap.fase6cap8.services;

import java.util.List;

import br.com.fiap.fase6cap8.daos.TipoLancamentoDao;
import br.com.fiap.fase6cap8.models.TipoLancamento;

public class TipoLancamentoService {
	
	TipoLancamentoDao dao = new TipoLancamentoDao();

	public List<TipoLancamento> getAll() {
		return dao.getAll();
	}

	public TipoLancamento getById(Long id) {
		return dao.getById(id);
	}

	public void save(String dsTipo, Long id) {
		TipoLancamento tipoLancamento = new TipoLancamento();
		if(id != null && id > 0) {
			tipoLancamento.setId(id);
		}
		tipoLancamento.setDsTipo(dsTipo);
		dao.save(tipoLancamento);
	}

	public void delete(Long id) {
		dao.delete(id);		
	}

}
