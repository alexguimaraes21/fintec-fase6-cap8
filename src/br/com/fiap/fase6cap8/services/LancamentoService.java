package br.com.fiap.fase6cap8.services;

import java.text.SimpleDateFormat;
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

	public void save(double vlLancamento, String dtLancamento, String dsLancamento, 
//			TipoLancamento tipoLancamento, Usuario usuario, Conta conta, 
			TipoLancamento tipoLancamento, Conta conta,
			TipoInvestimento tipoInvestimento, Long id) {
		Lancamento lancamento = new Lancamento();
		if(id != null && id > 0) {
			lancamento.setId(id);
		}
		try {
			Date dataLancamento = new SimpleDateFormat("dd/MM/yyyy").parse(dtLancamento);
			lancamento.setVlLancamento(vlLancamento);
			lancamento.setDtLancamento(dataLancamento);
			lancamento.setDsLancamento(dsLancamento);
			lancamento.setTipoLancamento(tipoLancamento);
			if(tipoLancamento.getId() == 3) { // Investimento
				lancamento.setTipoIvestimento(tipoInvestimento);
			}
			lancamento.setConta(conta);
//			lancamento.setUsuario(usuario);
			dao.save(lancamento);
		} catch (Exception e) {
			System.out.println("Erro na data do Lançamento. Formato de data aceito: dd/mm/yyyy");
			System.out.println(e.getMessage());
		}
	}

	public void delete(Long id) {
		dao.delete(id);		
	}

}
