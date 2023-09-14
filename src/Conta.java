abstract class Conta {
	private Usuario usuario;
	private int numeroDaConta;
	private double saldoInicial;
	private double saldoAtual;
	
	protected Conta(Usuario usuario, int numeroDaConta, double saldoInicial) {
		super();
		this.usuario = usuario;
		this.numeroDaConta = numeroDaConta;
		this.saldoInicial = saldoInicial;
		this.saldoAtual = saldoInicial;
	}

	protected void exibirUsuario(){
		System.out.println("Nome: " + usuario.getNome());
		System.out.println("CPF: " + usuario.getCpf());
		System.out.println("Senha: " + usuario.getSenha() + "\n");
	}

	protected double depositarValor(double deposito) {
		saldoAtual = saldoAtual + deposito;
		return saldoAtual;
	}
	
	protected double sacarValor(double saque) {
		if(saque > saldoInicial && saque > saldoAtual) {
			System.out.println("Saldo atual eh inferior ao saque.");
			return saldoAtual; 
		}
		saldoAtual = saldoAtual - saque;
		return saldoAtual;
	}
	
	protected void imprimirSaldo() {
		System.out.println("O nomero da conta eh: " + numeroDaConta);
		System.out.println("O saldo atual eh: " + saldoAtual + "\n");
	}
		
}
