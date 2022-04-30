
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author letic
 */
public class Sistema {
    public static void main(String[] args){
        Scanner s = new Scanner (System.in);
        DaoEncomenda dao = new DaoEncomenda();
        int r = 1;
        
        while (r != 0){
            do{
                System.out.println("Informe a operação desejada:"
                + "\n1 - Cadastrar encomenda"
                + "\n2 - Editar status da encomenda"
                + "\n3 - Listar todas as encomendas"
                + "\n4 - Listar encomendas em entrega"
                + "\n5 - Listar encomendas com frete superior a um valor informado"
                + "\n0 - Sair do programa");
                 r = s.nextInt();
                 
                if (r < 0 || r > 5){
                    System.out.println("Opção inválida.");
                }
                
            }while (r < 0 || r > 5);
        
        switch(r){
            
            case 0:{
                break;
            }
            case 1:{
                System.out.println("Cliente: ");
                String cliente = s.next();
                
                int status = 0;
                do{
                    System.out.println("Status: "
                        + "\n 1 - Nota fiscal emitida"
                        + "\n 2 - Em separação"
                        + "\n 3 - Em entrega"
                        + "\n 4 - Entregue");
                    System.out.println("\nOpção: ");
                    
                        status = s.nextInt(); 
                        
                        if (status < 1 || status > 4){
                            System.out.println("Status inválido. Informe um valor"
                                    + " entre 1 e 4");
                            
                        }
                        
                }while(status < 1 || status > 4);
                
                System.out.println("Peso (em gramas): ");
                double peso = s.nextDouble() / 1000;
                System.out.println("Volume (em cm³): ");
                double volume = s.nextDouble() / 1000000;
                System.out.println("Valor: ");
                double valor = s.nextDouble();
                
                Encomenda e = new Encomenda(cliente, status, peso, volume, valor);
              
                dao.inserir(e);
                
                if (dao.inserir(e)){
                    System.out.println("Sucesso! O código da encomenda cadastrada é: " +e.getId()+
                            " e o frete da compra é de R$ "+e.calcularValorFrete());
                }else{
                    System.out.println("Não foi possível cadastrar esta encomenda.");
                }
                
                break; 
            }
            case 2:{
                    
                Encomenda e;
                
                    do{
                        System.out.println("Informe o id da encomenda: ");
                        int id = s.nextInt();
                        e = dao.selecionar(id);
                        
                        if (e != null){
                        
                        int status = 0;
                        do{
                            System.out.println("O status atual da encomenda é " +e.getStatus()
                                + "\n"
                                + "\n 1 - Nota fiscal emitida"
                                + "\n 2 - Em separação"
                                + "\n 3 - Em entrega"
                                + "\n 4 - Entregue"
                                + "\n "
                                + "\n Informe o novo status: ");
                        
                        status = s.nextInt();

                        if (status >= 1 && status <= 4){
                            e.setStatus(status);
                            if (dao.editar(e)){
                            System.out.println("Sucesso! O status da encomenda"
                                    + " foi alterado para "+e.getStatus());
                            } else{
                                System.out.println("Ocorreu um erro.");
                            }
                                          
                        }else{
                            System.out.println("Valor inválido para status. "
                                    + "Digite um número entre 1 e 4.");
                        }
                        }while (status < 1 || status > 4);
                    }else{
                        System.out.println("Id não encontrado.");
                    }
                        
                    }while(e == null); 
                    
                    
                    break;
            }
            case 3:{
                List<Encomenda> encomendas = dao.listar();
                
                if (!encomendas.isEmpty()){
                        for (Encomenda e : encomendas){
                            System.out.println(e.toString());
                    }
                }else{
                    System.out.println("Nenhuma encomenda cadastrada.");
                }
                
                break;
            }   
            
            case 4:{
                List<Encomenda> encomendas = dao.listar();
                
                if (!encomendas.isEmpty()){
                    for (Encomenda e : encomendas){
                        if (e.getStatus() == 3){
                            System.out.println(e.toString());
                        }
                    }
                    
                }else{
                    System.out.println("Nenhuma encomenda encontrada.");
                }
                
                break;
            }
            
            case 5:{
                List<Encomenda> encomendas = dao.listar();
                
                System.out.println("Informe um valor para o frete: ");
                double frete = s.nextDouble();
                
                if (!encomendas.isEmpty()){
                    for (Encomenda e : encomendas){
                        if (e.calcularValorFrete() > frete){
                            System.out.println(e.toString());
                        }
                    }
                }else{
                    System.out.println("Nenhuma encomenda cadastrada.");
                }
                
                break;
            }
            
            default:{
                System.out.println("Opção inválida.");
                break;
           }
    }
    }
        dao.fechar();
        
        
    }
}
