package br.com.fiap.fase6cap8;

import java.util.List;

import br.com.fiap.fase6cap8.models.Conta;
import br.com.fiap.fase6cap8.models.Usuario;
import br.com.fiap.fase6cap8.services.ContaService;
import br.com.fiap.fase6cap8.services.UsuarioService;

public class Teste {

	public static void main(String[] args) {
		ContaService contaService = new ContaService();
		UsuarioService usuarioService = new UsuarioService();
		
		/*
		 * Cadastrando um novo Usuario
		 */
		usuarioService.save("37877120036", "123mudar", "admin", true, null);
		usuarioService.save("12345678901", "789mudou", "usuario-teste", true, null);
		
		/*
		 * Buscando o usuario com ID => 1
		 */
		Usuario usuarioSelecionado = usuarioService.getById(1);
		System.out.println("## Buscando usuário com ID = 1 ##");
		if(usuarioSelecionado != null) {
			System.out.println("  Usuário ID: [ " + usuarioSelecionado.getId() + " ]");
			System.out.println("    Nome do usuário: " + usuarioSelecionado.getNmUsuario());
			System.out.println("    CPF do usuário: " + usuarioSelecionado.getVlCpf());
			System.out.println("    Usuário Ativo? " + (usuarioSelecionado.isCkUsuarioAtivo() ? "SIM" : "NÃO"));
		}
		
		/*
		 * Cadastrando uma conta
		 */
		contaService.save("4975-2", "00087372-2", "146", usuarioSelecionado, null);
		contaService.save("0089-2", "00005712-9", "033", usuarioSelecionado, null);
		
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
		Conta contaSelecionada = contaService.getById(1L);
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
		
		
		/*
		 * Cadastrando Carteiras de Investimento
		 */
		
		
		/*
		 * Cadastrando Lancamento
		 */
	}
}
