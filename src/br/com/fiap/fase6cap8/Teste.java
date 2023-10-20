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
//		UsuarioService usuarioService = new UsuarioService();
		TipoLancamentoService tipoLancamentoService = new TipoLancamentoService();
		TipoInvestimentoService tipoInvestimentoService = new TipoInvestimentoService();
		LancamentoService lancamentoService = new LancamentoService();
		
		/*
		 * Cadastrando um novo Usuario
		 */
//		usuarioService.save("37877120036", "123mudar", "admin", true, null);
//		usuarioService.save("12345678901", "789mudou", "usuario-teste", true, null);
		
		/*
		 * Buscando o usuario com ID => 1
		 */
//		Usuario usuarioSelecionado = usuarioService.getById(1);
//		System.out.println("## Buscando usuário com ID = 1 ##");
//		if(usuarioSelecionado != null) {
//			System.out.println("  Usuário ID: [ " + usuarioSelecionado.getId() + " ]");
//			System.out.println("    Nome do usuário: " + usuarioSelecionado.getNmUsuario());
//			System.out.println("    CPF do usuário: " + usuarioSelecionado.getVlCpf());
//			System.out.println("    Usuário Ativo? " + (usuarioSelecionado.isCkUsuarioAtivo() ? "SIM" : "NÃO"));
//		}
		
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
		
		/*
		 * Cadastrando Lancamento
		 */
		System.out.println("## Cadastrando os Lançamentos ##");
		lancamentoService.save(10000.00, "08/10/2023", "Pagamento - Cliente ABC", receita, contaSelecionada, null, null);
		lancamentoService.save(5000.00, "09/10/2023", "Pagamento - Cliente DEF", receita, contaSelecionada, null, null);
		lancamentoService.save(7349.18, "09/10/2023", "Pagamento - Cliente GEH", receita, contaSelecionada, null, null);
		lancamentoService.save(500.00, "10/10/2023", "Investimento para Aposentadoria", investimento, contaSelecionada, tesouroDireto, null);
		lancamentoService.save(250.00, "10/10/2023", "Investimento para Aposentadoria", investimento, contaSelecionada, cdb, null);
		lancamentoService.save(231.13, "15/10/2023", "Conta de luz", despesa, contaSelecionada, null, null);
		lancamentoService.save(1250.00, "20/10/2023", "Condomínio", despesa, contaSelecionada, null, null);
		
		System.out.println("## Listando todos os Lançamentos e os totais ##");
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		double totalReceita = 0.00;
		double totalDespesa = 0.00;
		double totalInvestimento = 0.00;
		double saldoAtual = 0.00;
		for (Lancamento lcto : lancamentoService.getAll()) {
			System.out.println("  Lançamento ID [ " + lcto.getId() + " ]");
			System.out.println("    Descrição: R$ " + lcto.getDsLancamento());
			System.out.println("    Valor: R$ " + lcto.getVlLancamento());
			System.out.println("    Data do Lançamento: " + dateFormat.format(lcto.getDtLancamento()));
			System.out.println("    Tipo do Lançamento: " + lcto.getTipoLancamento().getDsTipo());
			if (lcto.getTipoLancamento().getId() == 3) {
				totalInvestimento += lcto.getVlLancamento();
				System.out.println("    Carteira de Investimento: " + lcto.getTipoIvestimento().getDsTipoInvestimento());
			} else if (lcto.getTipoLancamento().getId() == 2) {
				totalReceita += lcto.getVlLancamento();
			} else if (lcto.getTipoLancamento().getId() == 1) {
				totalDespesa += lcto.getVlLancamento();
			}
		}
		System.out.println("Total Investido: R$ " + totalInvestimento);
		System.out.println("Total de Despesas: R$ " + totalDespesa);
		System.out.println("Total de Receitas: R$ " + totalReceita);
		saldoAtual = totalReceita;
		saldoAtual -= totalDespesa;
		saldoAtual -= totalInvestimento;
		System.out.println("Saldo Atual R$ " + saldoAtual);
	}
}
