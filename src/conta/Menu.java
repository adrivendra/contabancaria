package conta;

import conta.util.*;
import java.util.Scanner;

import conta.controller.ContaController;
import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;

public class Menu {
	public static void main(String[] args) {

		ContaController contas = new ContaController();

		Scanner scan = new Scanner(System.in);

		int opcao, numero, agencia, tipo, aniversario;
		String titular;
		float saldo, limite;

		System.out.println("\n Criar Contas");

		ContaCorrente cc1 = new ContaCorrente(contas.gerarNumero(), 123, 1, "João da Silva", 1000f, 100.0f);
		contas.cadastrar(cc1);

		ContaCorrente cc2 = new ContaCorrente(contas.gerarNumero(), 124, 1, "Maria da Silva", 2000f, 100.0f);
		contas.cadastrar(cc2);

		ContaPoupanca cp1 = new ContaPoupanca(contas.gerarNumero(), 125, 2, "Mariana dos Santos", 4000f, 12);
		contas.cadastrar(cp1);

		ContaPoupanca cp2 = new ContaPoupanca(contas.gerarNumero(), 125, 2, "Juliana Ramos", 8000f, 15);
		contas.cadastrar(cp2);

		while (true) {
			System.out.println(Cores.TEXT_PURPLE_BOLD_BRIGHT + Cores.ANSI_BLACK_BACKGROUND);

			System.out.println("*****************************************************");
			System.out.println("                                                     ");
			System.out.println("              ⚒ BANCO DO ADRIANO ☭                   ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("                                                     ");
			System.out.println("            1 - Criar Conta                          ");
			System.out.println("            2 - Listar todas as Contas               ");
			System.out.println("            3 - Buscar Conta por Numero              ");
			System.out.println("            4 - Atualizar Dados da Conta             ");
			System.out.println("            5 - Apagar Conta                         ");
			System.out.println("            6 - Sacar                                ");
			System.out.println("            7 - Depositar                            ");
			System.out.println("            8 - Transferir valores entre Contas      ");
			System.out.println("            9 - Sair                                 ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("Entre com a opção desejada:                          ");
			System.out.println("                                                     " + Cores.TEXT_RESET);

			opcao = scan.nextInt();

			if (opcao == 9) {
				System.out.println(Cores.TEXT_WHITE_BOLD + "\nBanco do Adriano - O banco mais geracional do Brasil! ☭");
				sobre();
				scan.close();
				System.exit(0);
			}

			switch (opcao) {
			case 1:
				System.out.println("Criar Conta\n\n");

				System.out.println("Digite o numero da Agência: ");
				agencia = scan.nextInt();
				System.out.println("Digite o nome do Titular: ");
				scan.skip("\\R?");
				titular = scan.nextLine();

				do {
					System.out.println("Digite o tipo da Conta (1 - CC ou 2 - CP): ");
					tipo = scan.nextInt();

				} while (tipo < 1 && tipo > 2);
				System.out.println("Digite o Saldo da Conta (R$): ");
				saldo = scan.nextFloat();

				switch (tipo) {
				case 1 -> {
					System.out.println("Digite o Limite de Crédito (R$): ");
					limite = scan.nextFloat();
					contas.cadastrar(new ContaCorrente(contas.gerarNumero(), agencia, tipo, titular, saldo, limite));
				}
				case 2 -> {
					System.out.println("Digite o dia do Aniversario da Conta: ");
					aniversario = scan.nextInt();
					contas.cadastrar(
							new ContaPoupanca(contas.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
				}
				}

				break;
			case 2:
				System.out.println("Listar todas as Contas\n\n");
				contas.listarTodas();
				break;
			case 3:
				System.out.println("Consultar dados da Conta - por número\n\n");

				System.out.println("Digite o número da conta: ");
				numero = scan.nextInt();

				contas.procurarPorNumero(numero);

				break;
			case 4:
				System.out.println("Atualizar dados da Conta\n\n");

				System.out.println("Digite o número da conta: ");
				numero = scan.nextInt();

				var buscaConta = contas.buscarNaCollection(numero);

				if (buscaConta != null) {
					tipo = buscaConta.getTipo();

					System.out.println("Digite o número da Agência: ");
					agencia = scan.nextInt();
					System.out.println("Digite o Nome do Titular: ");
					scan.skip("\\R?");
					titular = scan.nextLine();

					System.out.println("Digite o Saldo da Conta (R$): ");
					saldo = scan.nextFloat();

					switch (tipo) {
					case 1 -> {
						System.out.println("Digite o limite de Crédito: ");
						limite = scan.nextFloat();

						contas.atualizar(new ContaCorrente(numero, agencia, tipo, titular, saldo, limite));
					}
					case 2 -> {
						System.out.println("Digite o dia de Aniversario da Conta: ");
						aniversario = scan.nextInt();

						contas.atualizar(new ContaPoupanca(numero, agencia, tipo, titular, saldo, aniversario));
					}
					default -> {
						System.out.println("Tipo de conta inválido!!");
					}

					}

				} else {
					System.out.println("A conta não foi encontrada!");
				}

				break;
			case 5:
				System.out.println("Apagar a Conta\n\n");
				System.out.println("Digite o número da conta: ");
				numero = scan.nextInt();
				
				contas.deletar(numero);
				break;
			case 6:
				System.out.println("Saque\n\n");

				break;
			case 7:
				System.out.println("Depósito\n\n");

				break;
			case 8:
				System.out.println("Transferência entre Contas\n\n" + Cores.TEXT_RESET);

				break;
			default:
				System.out.println(Cores.TEXT_RED_BOLD + "\nOpção Inválida!\n" + Cores.TEXT_RESET);
				break;
			}
		}
	}

	public static void sobre() {
		System.out.println("\n*********************************************************");
		System.out.println("Projeto Desenvolvido por: ");
		System.out.println("Adriano Vendramini - adrianovendramini@hotmail.com");
		System.out.println("github.com/adrivendra/contabancaria");
		System.out.println("*********************************************************");
	}
}