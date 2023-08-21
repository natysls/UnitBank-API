
abstract class Conta {
	private int numeroDaConta;
	private double saldoInicial;
	private double saldoAtual;
	
	protected Conta(int numeroDaConta, double saldoInicial) {
		super();
		this.numeroDaConta = numeroDaConta;
		this.saldoInicial = saldoInicial;
		this.saldoAtual = saldoInicial;
	}

	//N�o ser� necess�rio pois inicializo a instancia usando o construtor do Java
//	protected void criarContaBancaria(int numeroDaConta, double saldoInicial) {
//		this.numeroDaConta = numeroDaConta;
//		this.saldoInicial = saldoInicial;
//		this.saldoAtual = saldoInicial;
//	}

	protected double depositarValor(double deposito) {
		saldoAtual = saldoAtual + deposito;
		return saldoAtual;
	}
	
	protected double sacarValor(double saque) {
		if(saque > saldoInicial && saque > saldoAtual) {
			System.out.println("Saldo atual � inferior ao saque.");
			return saldoAtual; 
		}
		saldoAtual = saldoAtual - saque;
		return saldoAtual;
	}
	
	protected void imprimirSaldo() {
		System.out.println("O n�mero da conta �: " + numeroDaConta);
		System.out.println("O saldo atual �: " + saldoAtual + "\n");
	}
		
}
