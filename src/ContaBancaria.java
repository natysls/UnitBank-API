
public class ContaBancaria extends Conta {
	
	protected ContaBancaria(Usuario usuario, int numeroDaConta, double saldoInicial) {
		super(usuario, numeroDaConta, saldoInicial);
	}

	@Override
	protected void exibirUsuario() {
		super.exibirUsuario();
	}
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
