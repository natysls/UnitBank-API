
public class Main {

	public static void main(String[] args) {
		ContaBancaria contaBancaria = new ContaBancaria(8765873, 8000.00);
		
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
