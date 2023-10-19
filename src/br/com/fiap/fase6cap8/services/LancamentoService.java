package br.com.fiap.fase6cap8.services;

import java.util.Date;
import java.util.List;

import br.com.fiap.fase6cap8.daos.LancamentoDao;
import br.com.fiap.fase6cap8.models.Conta;
import br.com.fiap.fase6cap8.models.Lancamento;
import br.com.fiap.fase6cap8.models.TipoInvestimento;
import br.com.fiap.fase6cap8.models.TipoLancamento;
import br.com.fiap.fase6cap8.models.Usuario;

public class LancamentoService {
	
	LancamentoDao dao = new LancamentoDao();

	public List<Lancamento> getAll() {
		return dao.getAll();
	}

	public Lancamento getById(Long id) {
		return dao.getById(id);
	}

	public void save(double vlLancamento, Date dtLancamento, String dsLancamento, 
			TipoLancamento tipoLancamento, Usuario usuario, Conta conta, 
			TipoInvestimento tipoInvestimento, Long id) {
		Lancamento lancamento = new Lancamento();
		if(id != null && id > 0) {
			lancamento.setId(id);
		}
		lancamento.setVlLancamento(vlLancamento);
		lancamento.setDtLancamento(dtLancamento);
		lancamento.setDsLancamento(dsLancamento);
		lancamento.setTipoLancamento(tipoLancamento);
		lancamento.setTipoIvestimento(tipoInvestimento);
		lancamento.setConta(conta);
		lancamento.setUsuario(usuario);
		dao.save(lancamento);
	}

	public void delete(Long id) {
		dao.delete(id);		
	}

}
