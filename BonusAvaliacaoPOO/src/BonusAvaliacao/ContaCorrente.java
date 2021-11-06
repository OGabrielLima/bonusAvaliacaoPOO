package BonusAvaliacao;

public class ContaCorrente {

    private int     numero;
    private double  saldo;
    private Cliente cliente;

    //Construtor
    public ContaCorrente(int numero) {
        this.numero = numero;
        this.saldo = 0;
    }

    public int getNumero(){
        return this.numero;
    }

    public void setCliente(Cliente cliente) {
        //associa Cliente com ContaCorrente
        this.cliente = cliente;
    }

    public boolean verificarSaldo(String tipo, double value) {
    	double valor;
    	valor = saldo;
    	if(tipo == "deposito") {
    		valor += value;
    	}
    	if(tipo == "saque") {
    		valor -= value;
    	}
    	if(valor < 0) {
    		return false;
    	}
        return true;
    }
    
    public double verificaSaldo() {
        //ASSERT saldo negativo
        assert this.saldo > 0 : "Erro: Saldo negativo.";
        return this.saldo;
    }

    public void depositaValor(double deposito) {
        if(verificarSaldo("deposito", deposito)) {
        	this.saldo += deposito;
        } else {
        	System.out.println("* Impossível continuar, o valor em conta não pode ser menor que 0 *");
        }
        //verificaSaldo();
        // Verifica saldo negativo com ASSERT
    }
    public void retiraValor(double saque) {
        if(verificarSaldo("saque", saque)) {
        	this.saldo -= saque;
        } else {
        	System.out.println("* Impossível continuar, o valor em conta não pode ser menor que 0 *");
        }
        //v
        //verificaSaldo();
        // Verifica saldo negativo com ASSERT
    }

    public void imprimeConta() { //ContaCorrente acessa dados de Cliente
        String dadosConta = "";
        dadosConta += "Numero Conta: " + this.numero + "\n" ;
        dadosConta += "Cliente: "      + this.cliente.getNome() + "\n";
        dadosConta += "Saldo: R$ "     + this.verificaSaldo();

        System.out.println(dadosConta+"\n");
    }
}

