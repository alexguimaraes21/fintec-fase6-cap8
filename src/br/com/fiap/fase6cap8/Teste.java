package br.com.fiap.fase6cap8;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import br.com.fiap.fase6cap8.models.Conta;
import br.com.fiap.fase6cap8.models.Lancamento;
import br.com.fiap.fase6cap8.models.TipoInvestimento;
import br.com.fiap.fase6cap8.models.TipoLancamento;
import br.com.fiap.fase6cap8.models.Usuario;
import br.com.fiap.fase6cap8.services.ContaService;
import br.com.fiap.fase6cap8.services.LancamentoService;
import br.com.fiap.fase6cap8.services.TipoInvestimentoService;
import br.com.fiap.fase6cap8.services.TipoLancamentoService;
import br.com.fiap.fase6cap8.services.UsuarioService;

public class Teste {

	public static void main(String[] args) {
		ContaService contaService = new ContaService();
		TipoLancamentoService tipoLancamentoService = new TipoLancamentoService();
		TipoInvestimentoService tipoInvestimentoService = new TipoInvestimentoService();
		LancamentoService lancamentoService = new LancamentoService();
		
		/*
		 * Cadastrando contas
		 */
		contaService.save("4975-2", "00087372-2", "146", null);
		contaService.save("0089-2", "00005712-9", "033", null);
		
		/*
		 * Listando todas as contas
		 */
		List<Conta> contas = contaService.getAll();
		System.out.println("## Listando todas as contas cadastradas ##");
		if(!contas.isEmpty() ) {
			contas.forEach(cnt -> {
				System.out.println(" Conta ID: [ " + cnt.getId() + " ]");
				System.out.println("   Agencia: " + cnt.getVlAgencia());
				System.out.println("   Conta: " + cnt.getVlConta());
				System.out.println("   Banco: " + cnt.getVlBanco());
			});
		} else {
			System.out.println("Não foram encontradas contas cadastradas");
		}
		
		/*
		 * Listando conta com ID => 1
		 */
		System.out.println("## Selecionando conta com ID = 1 ##");
		Conta contaSelecionada = contaService.getById(1);
		if(contaSelecionada != null) {
			System.out.println(" Conta ID: [ " + contaSelecionada.getId() + " ]");
			System.out.println("   Agencia: " + contaSelecionada.getVlAgencia());
			System.out.println("   Conta: " + contaSelecionada.getVlConta());
			System.out.println("   Banco: " + contaSelecionada.getVlBanco());
		} else {
			System.out.println("Conta não encontrada");
		}
		
		/*
		 * Cadastrando Tipo de Lancamento
		 */
		System.out.println("## Cadastrando os Tipos Lançamento: Despesa, Receita e Investimento com IDs = 1, 2 e 3 ##");
		tipoLancamentoService.save("Despesa", null);
		tipoLancamentoService.save("Receita", null);
		tipoLancamentoService.save("Investimento", null);
		
		System.out.println("## Listando todos os Tipos Lancamento ##");
		tipoLancamentoService.getAll().forEach(tpl -> {
			System.out.println("  Tipo Lancaçamento ID [ " + tpl.getId() + " ]");
			System.out.println("    Tipo Lançamento: " + tpl.getDsTipo());
		});
		
		TipoLancamento despesa = tipoLancamentoService.getById(1);
		TipoLancamento receita = tipoLancamentoService.getById(2);
		TipoLancamento investimento = tipoLancamentoService.getById(3);
		
		/*
		 * Cadastrando Carteiras de Investimento
		 */
		System.out.println("## Cadastrando os Tipos Investimento ##");
		tipoInvestimentoService.save("CDB 104% do CDI - Rendimento anual", 9.29, 12, null);
		tipoInvestimentoService.save("Tesouro Direto - Juros Semestrais 2029", 1.81, 72, null);
		tipoInvestimentoService.save("Tesouro Selic (SELIC + 0,047%", 8.24, 12, null);
		
		System.out.println("## Listando todos Tipos Investimento registrados ##");
		tipoInvestimentoService.getAll().forEach(tpi -> {
			System.out.println("  Tipo Investimento ID [ " + tpi.getId() + " ]");
			System.out.println("    Carteira de Investimento: " + tpi.getDsTipoInvestimento());
			System.out.println("    Tempo mínimo do investimento: " + tpi.getPrazoMinimoInvestimento());
			System.out.println("    Rentabilidade do investimento: " + tpi.getVlRendimentoMensal() + "%");
		});
		TipoInvestimento cdb = tipoInvestimentoService.getById(1);
		TipoInvestimento tesouroDireto = tipoInvestimentoService.getById(2);
		
	}
}
