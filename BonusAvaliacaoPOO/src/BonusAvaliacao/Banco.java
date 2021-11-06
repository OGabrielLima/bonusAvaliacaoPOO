package BonusAvaliacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Banco {

    private String nome;
    //Coleção de objetos
    private ArrayList<Cliente> clientes;
    private static Cliente selectedCliente;
    
    //Metodos da classe
    public Banco(String nome) {
        this.nome = nome;
        // Coleção de objetos - inicializa ArrayList
        this.clientes   = new ArrayList<Cliente> ();
    }
    public void addCliente(Cliente c) {
        //Coleção de objetos - adiciona obj no ArrayList
        clientes.add(c);
    }
    public List<Cliente> getClientes() {
		return clientes;
	}
	public static Cliente getSelectedCliente() {
		return selectedCliente;
	}
	public static void setSelectedCliente(Cliente selectedCliente) {
		Banco.selectedCliente = selectedCliente;
	}
	
	//Programa Principal
	public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        String opcao = "0";
        Banco banco = new Banco("Banco Gabs SA.");

        while(true){
        	opcao = menuPrincipal();
        	switch (opcao){
                case "1" : {
            		try {
	                	System.out.println("--- CADASTRAR CLIENTE ---");
	                	String nome;
	                    int numeroConta;
	                    double valor = 0;
	                    boolean permiteCad =  true;
	                    System.out.print("Informe o nome: ");
	                    nome = leitor.nextLine();
	                    System.out.print("Informe o número conta: ");
	                    numeroConta = Integer.parseInt(leitor.nextLine());
	                    List<Cliente> listaClientes = banco.getClientes();
            			for (Cliente cliente : listaClientes) { // cliente é um iterador
							if(cliente.getConta().getNumero() == numeroConta) {
								System.out.println("Já existe um cliente cadastrado com este número de conta");
								permiteCad = false;
							}
						} 
            			if(permiteCad) {
							Cliente cliente = new Cliente(nome, numeroConta);
							banco.addCliente(cliente);
							setSelectedCliente(cliente);
							System.out.println("Para finalizar o cadastro de sua conta você precisa realizar um deposito em conta: ");
							System.out.print("Digite o valor do deposito: R$ ");
							valor = leitor.nextDouble();
							getSelectedCliente().getConta().depositaValor(valor);
							System.out.print(" * CADASTRADO COM SUCESSO * \n");
							getSelectedCliente().getConta().imprimeConta();
							opcao = "3";
						}
            		} catch (Exception e) {
						System.out.println("Entrada invalida, tente novamente. ");
					}
                    break;
                }
                case "2" : {
                	try {
                	System.out.println("--- CONSULTAR CLIENTE ---");
                		System.out.print("Informe a conta Corrente: ");
                		int conta = Integer.parseInt(leitor.nextLine());
                		Cliente clienteAchado = null;
                		for(Cliente clienteBusca : banco.clientes){
                			if(clienteBusca.getConta().getNumero() == conta){
                				clienteAchado = clienteBusca;
                				break;
                			}
                		}
	                    if(clienteAchado!=null){
	                    	setSelectedCliente(clienteAchado);
	                    	clienteAchado.getConta().imprimeConta();
	                    }else{
	                        System.out.println("Conta Corrente não encontrada para " + conta);
	                    }
                	} catch (Exception e) {
                		System.out.println("Entrada invalida, tente novamente. ");
					}
                	break;
                }
                case "3" : {
                    System.out.println("--- LISTAR CLIENTES ---");
            		try {
            			List<Cliente> listaClientes = banco.getClientes();
						if(listaClientes.size() == 0) {
							System.out.println("Não há clientes cadastrados. ");
						}
            			for (Cliente cliente : listaClientes) { // cliente é um iterador
							cliente.getConta().imprimeConta();
						}
					} catch (Exception e) {
						System.out.println("Não há clientes cadastrados. ");
					}
                    break;
                }
                case "4" : {
                    System.out.println("--- SACAR --");
                    try {
                    	if(getSelectedCliente() == null) {
                    		System.out.println("Para executar esta opção é necessário selecionar um cliente (Opção 2)");
                    	} else {
                    		double valor = 0;
                    		System.out.print("Digite o valor do saque: R$ ");
                    		valor = leitor.nextDouble();
                    		if(valor < 0) {
	                			System.out.println("O valor do saque não pode ser negativo");
	                		} else {
	                			getSelectedCliente().getConta().retiraValor(valor);
	                			getSelectedCliente().getConta().imprimeConta();
	                		}
                    	}
                    } catch (Exception e) {
						System.out.println("Conta inválida. ");
					}
                    break;
                }
                case "5" : {
                    System.out.println("-- DEPOSITAR ---");
                    try {
                    	if(getSelectedCliente() == null) {
                    		System.out.println("Para executar esta opção é necessário selecionar um cliente (Opção 2)");
                    	} else {
	                		double valor = 0;
	                		System.out.print("Digite o valor do deposito: R$ ");
	                		valor = leitor.nextDouble();
	                		if(valor < 0) {
	                			System.out.println("O valor do deposito não pode ser negativo");
	                		} else {
	                			getSelectedCliente().getConta().depositaValor(valor);
	                			getSelectedCliente().getConta().imprimeConta();
	                		}
                    	}
                    } catch (Exception e) {
						System.out.println("Entrada inválida. ");
					}
                    break;
                }
                case "6" : {
                    System.out.println("Programa Encerrado");
                    System.exit(0);
                }
                default : {  
                	System.out.println("Opção invalida");
	            	break;
                }
            }
        }
    }

    public static String menuPrincipal(){
    	Scanner leitor = new Scanner(System.in);
    	String option = "0";
    	System.out.println("--------------------------");
        System.out.println(" *** Menu de Opções : ***");
        System.out.println("1 : Cadastrar Cliente");
        System.out.println("2 : Consultar / Selecionar Cliente");
        System.out.println("3 : Listar Clientes");
        System.out.println("4 : Sacar ");
        System.out.println("5 : Depositar ");
        System.out.println("6 : Sair ");
        System.out.print("Opção desejada: ");
        option = leitor.nextLine();
		System.out.println("--------------------------");
        return option;
    }


}
