/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemabanco;

import java.util.Scanner;

/**
 *
 * @author lucasGitirana
 */
public class SistemaBanco {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Dao dao = new Dao();
        DaoBanco daoBanco = new DaoBanco();
        DaoConta daoConta = new DaoConta();
        DaoCliente daoCliente = new DaoCliente();
        int opcao = 1;
        while(opcao != 0){
            System.out.println("Informe a operação desejada:"
                    + "\n1 - Cadastrar cliente"
                    + "\n2 - Cadastrar agência"
                    + "\n3 - Cadastrar conta"
                    + "\n4 - Efetuar saque ou depósito"
                    + "\n5 - Listar agências e todas as suas contas");
            opcao = s.nextInt();
            
            switch(opcao){
                //CADASTRAR CLIENTE
                case 1:{
                    System.out.println("===============CADASTRAR CLIENTE===============");
                    System.out.println("Nome: ");
                    String nome = s.next();
                    System.out.println("CPF: ");
                    String cpf = s.next();
                    System.out.println("E-mail: ");
                    String email = s.next();
                    System.out.println("Telefone: ");
                    String telefone = s.next();
                    
                    Cliente c = new Cliente(cpf,nome,email,telefone);
                    
                    if(daoCliente.inserir(c)){
                        System.out.println("O cliente foi cadastrado com sucesso!");
                    }else{
                        System.out.println("Não foi possível cadastrar cliente.");
                    }
                    
                    break;
                }
                // CADASTRAR AGÊNCIA E CONTA
                case 2:{
                    System.out.println("===============CADASTRAR AGÊNCIA===============");
                    System.out.println("Número: ");
                    int numeroAgencia = s.nextInt();
                    System.out.println("Endereço: ");
                    String endereco = s.next();
                    
                    Banco b = new Banco(numeroAgencia, endereco);    
                    
                    if(daoBanco.inserir(b)){
                        System.out.println("A agência foi cadastrada com sucesso!");
                    }else{
                        System.out.println("Não foi possível cadastrar agência.");
                    }
                    
                    System.out.println("Deseja cadastrar conta nessa agência? (digite S ou N)");
                    char cadastrarConta = s.next().toUpperCase().charAt(0);
                    while(cadastrarConta == 'S'){
                        System.out.println("CPF do cliente: ");
                        String cpfCliente = s.next();
                        if(daoCliente.selecionar(cpfCliente) != null){
                            System.out.println("Número da conta: ");
                            int numeroConta = s.nextInt();
                            System.out.println("Saldo: ");
                            double saldo = s.nextDouble();
                            System.out.println("Tipo da conta: "
                                    + "\n1. Corrente"
                                    + "\n2. Poupança");
                            int tipoConta = s.nextInt();
                            
                            if (tipoConta == 1){
                                System.out.println("Limite: ");
                                double limite = s.nextDouble();
                                b.adicionarContaCorrente(limite, numeroConta, saldo, b, daoCliente.selecionar(cpfCliente));
                            }else{
                                System.out.println("Variação: ");
                                int variacao = s.nextInt();
                                b.adicionarContaPoupanca(variacao, numeroConta, saldo, b, daoCliente.selecionar(cpfCliente));
                            }
                            
                            if(daoBanco.editar(b)){
                                System.out.println("A conta foi cadastrada com sucesso!");
                            }else{
                                System.out.println("Não foi possível cadastrar conta.");
                            }
                        }else{
                            System.out.println("Cliente não encontrado.");
                        }
                        System.out.println("Deseja continuar? (digite S ou N)");
                        cadastrarConta = s.next().toUpperCase().charAt(0);
                    }
                    break;
                }
                
                case 3:{
                    System.out.println("===============CADASTRAR CONTA===============");
                    System.out.println("Número da agência: ");
                    int agencia = s.nextInt();
                    System.out.println("CPF do cliente: ");
                    String cpfCliente = s.next();
                    
                    if(daoBanco.selecionar(agencia) != null){
                        if(daoCliente.selecionar(cpfCliente) != null){
                            System.out.println("Número da conta: ");
                            int numero = s.nextInt();
                            System.out.println("Saldo: ");
                            double saldo = s.nextDouble();
                            
                            System.out.println("Tipo da conta: \n1. Corrente \n2. Poupança");
                            int tipoConta = s.nextInt();
                            
                            if(tipoConta == 1){
                                System.out.println("Limite: ");
                                double limite = s.nextInt();
                                daoBanco.selecionar(agencia).adicionarContaCorrente(limite, numero, saldo, daoBanco.selecionar(agencia), daoCliente.selecionar(cpfCliente));
                            }else if(tipoConta == 2){
                                System.out.println("Variação: ");
                                int variacao = s.nextInt();
                                daoBanco.selecionar(agencia).adicionarContaPoupanca(variacao, numero, saldo, daoBanco.selecionar(agencia), daoCliente.selecionar(cpfCliente));
                            }
                        }else{
                            System.out.println("Cliente não encontrado");
                        }
                    }else{
                        System.out.println("Agência não encontrada");
                    }
                    break;
                }
                
                // SACAR OU DEPOSITAR
                case 4:{
                    System.out.println("===============SACAR OU DEPOSITAR===============");
                    System.out.println("Número da agência: ");
                    int agencia = s.nextInt();
                    System.out.println("Número da conta: ");
                    int conta = s.nextInt();
                    
                   
                       if (daoBanco.selecionar(agencia) != null){
                           for(Conta c : daoBanco.selecionar(agencia).getContas()){
                               if(c.getNumero() == conta){
                                   System.out.println("=================="+ c.toString()+"==================");
                                   System.out.println("1 - Sacar \n2 - Depositar");
                                   int operacao = s.nextInt();
                                   if(operacao == 1){
                                       System.out.println("Valor do saque: ");
                                       double valor = s.nextDouble();
                                       if(valor > c.getSaldo()){
                                           System.out.println("Você não pode sacar esse valor.");
                                       }else{
                                           c.sacar(valor);
                                           if (daoBanco.editar(daoBanco.selecionar(agencia))) {
                                           System.out.println("Saque realizado.\n" + c.toString());
                                           System.out.println("=================="+ c.toString()+"==================");
                                       }
                                           break;
                                   }
                                   }else if(operacao == 2){
                                       System.out.println("Valor do depósito: ");
                                       double valor = s.nextDouble();
                                       
                                       if((c.getSaldo()+ valor) > c.getLimite() && c.getLimite() != 0){
                                           System.out.println("O limite da conta não permite esse depósito.");
                                       }else{
                                           c.depositar(valor);
                                           if (daoBanco.editar(daoBanco.selecionar(agencia))) {
                                           System.out.println("Depósito realizado");
                                           System.out.println("=================="+ c.toString()+"==================");
                                   }
                                       }
                                       break;
                                   }
                               }
                        }
                       }
                    
                   break;
                }

                //LISTAGEM DAS AGÊNCIAS E SUAS CONTAS
                case 5:{
                    System.out.println("===============LISTAR===============");
                    for(Banco b : daoBanco.listar()){
                        System.out.println("===== " +b.toString() + " =====");
                        for(Conta c : b.getContas()){
                            System.out.println(c.toString());
                            System.out.println("Valor disponível: R$ "+c.getSaldoDisponivel());
                        }
                        
                    }
                    break;
                }
            }
            System.out.println("Deseja continuar? (1. SIM ou 0.NÃO)");
            opcao = s.nextInt();
        }
        dao.fechar();
    }
    
}
