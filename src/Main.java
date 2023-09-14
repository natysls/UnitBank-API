public class Main {

	public static void main(String[] args) {
		Usuario usuario = new Usuario("Natalia", "61865489379", "4040");
		ContaBancaria contaBancaria = new ContaBancaria(usuario, 428922, 8000.00);

		System.out.println("Usuário: ");
		contaBancaria.exibirUsuario();

		System.out.println("Antes de tudo: ");
		contaBancaria.imprimirSaldo();
		
		System.out.println("Depósito: ");
		double saldoAtual = contaBancaria.depositarValor(350.00);
		System.out.println("Saldo atual: " + saldoAtual + "\n");
		
		System.out.println("Saque: ");
		saldoAtual = contaBancaria.sacarValor(600.70);
		System.out.println("Saldo atual: " + saldoAtual + "\n");
		
		System.out.println("Saque com erro: ");
		saldoAtual = contaBancaria.sacarValor(9000.000);
		System.out.println("Saldo atual: " + saldoAtual + "\n");
		
		System.out.println("Depois de tudo: ");
		contaBancaria.imprimirSaldo();
	}

}
