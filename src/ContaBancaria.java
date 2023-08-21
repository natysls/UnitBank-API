
public class ContaBancaria extends Conta {
	
	protected ContaBancaria(int numeroDaConta, double saldoInicial) {
		super(numeroDaConta, saldoInicial);
	}

//	@Override
//	protected void criarContaBancaria(int numeroDaConta, double saldoInicial) {
//		super.criarContaBancaria(numeroDaConta, saldoInicial);
//	}

	@Override
	protected double depositarValor(double deposito) {
		return super.depositarValor(deposito);
	}
	
	@Override
	protected double sacarValor(double saque) {
		return super.sacarValor(saque);
	}
	
	@Override
	protected void imprimirSaldo() {
		super.imprimirSaldo();
	}
}
