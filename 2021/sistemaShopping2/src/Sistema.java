
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author lucasgitirana
 */
public class Sistema {

    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);
        Dao dao = new Dao();
        DaoSegmento daoSegmento = new DaoSegmento();
        DaoLoja daoLoja = new DaoLoja();
        DaoShopping daoShopping = new DaoShopping();
        int opcao = 0;
        do {
            System.out.println("Informe o número da operação desejada: ");
            System.out.println("1. Cadastro de segmento"
                    + "\n2. Cadastro de shopping"
                    + "\n3. Cadastro de loja"
                    + "\n4. Edição de loja "
                    + "\n5. Exclusão de loja"
                    + "\n6. Listagem de todos os shoppings com todas as suas lojas"
                    + "\n7. Exibição das informações de um determinado shopping com suas lojas"
                    + "\n8. Listagem das lojas de um segmento em um determinado shopping");
            opcao = s.nextInt();

            switch (opcao) {
                case 1: {
                    System.out.println("Nome: ");
                    String nome = s.next();
                    Segmento segmento = new Segmento(nome);
                    if (daoSegmento.inserir(segmento)) {
                        System.out.println("Cadastro realizado com sucesso!");
                    } else {
                        System.out.println("Não foi possível realizar o cadastro.");
                    }
                    break;
                }
                case 2: {
                    System.out.println("Nome: ");
                    String nomeShopping = s.next();
                    System.out.println("Endereço: ");
                    String endereco = s.next();
                    Shopping shopping = new Shopping(nomeShopping, endereco);

                    char continuar;
                    System.out.println("Deseja cadastrar loja no shopping? (digite S ou N)");
                    continuar = s.next().charAt(0);
                    while (Character.toUpperCase(continuar) == 'S') {
                        System.out.println("Nome: ");
                        String nomeLoja = s.next();
                        System.out.println("Site: ");
                        String site = s.next();
                        System.out.println("Descrição: ");
                        String descricao = s.next();
                        System.out.println("Número da sala");
                        int numeroSala = s.nextInt();
                        String nomeSegmento = "";
                        do {
                            System.out.println("Segmento: ");
                            nomeSegmento = s.next();
                        } while (daoSegmento.selecionar(nomeSegmento) == null);
                        shopping.addLoja(nomeLoja, site, descricao, numeroSala, daoSegmento.selecionar(nomeSegmento));
                        System.out.println("Continuar? (digite S ou N)");
                        continuar = s.next().charAt(0);
                    }
                    if (daoShopping.inserir(shopping)) {
                        System.out.println("Cadastro realizado com sucesso!");
                    } else {
                        System.out.println("Não foi possível realizar o cadastro.");
                    }
                    break;
                }
                case 3: {
                    String nomeShopping = "";
                    do {
                        System.out.println("Digite o nome do shopping: ");
                        nomeShopping = s.next();
                        if (daoShopping.selecionar(nomeShopping) == null) {
                            System.out.println("Shopping não encontrado. Digite novamente.");
                        }
                    } while (daoShopping.selecionar(nomeShopping) == null);

                    System.out.println("Nome: ");
                    String nome = s.next();
                    System.out.println("Site: ");
                    String site = s.next();
                    System.out.println("Descrição: ");
                    String descricao = s.next();
                    System.out.println("Número da sala");
                    int numeroSala = s.nextInt();
                    String nomeSegmento = "";
                    do {
                        System.out.println("Segmento: ");
                        nomeSegmento = s.next();
                    } while (daoSegmento.selecionar(nomeSegmento) == null);
                    daoShopping.selecionar(nomeShopping).addLoja(nome, site, descricao, numeroSala, daoSegmento.selecionar(nomeSegmento));                    
                    if (daoShopping.editar(daoShopping.selecionar(nomeShopping))) {
                        System.out.println("Loja adicionada ao shopping " + daoShopping.selecionar(nomeShopping).getNome());
                    }
                    break;
                }
                case 4: {
                    String nomeShopping = "";
                    do {
                        System.out.println("Digite o nome do shopping: ");
                        nomeShopping = s.next();
                        if (daoShopping.selecionar(nomeShopping) == null) {
                            System.out.println("Shopping não encontrado. Digite novamente.");
                        }
                    } while (daoShopping.selecionar(nomeShopping) == null);                   
                    
                        System.out.println("Nome da loja: ");
                        String nomeLoja = s.next();
                        
                    for (Loja l : daoShopping.selecionar(nomeShopping).getLojas()) {
                        if (l.getNome().equals(nomeLoja)) {
                            System.out.println("Nome: ");
                            String nome = s.next();
                            System.out.println("Site: ");
                            String site = s.next();
                            System.out.println("Descrição: ");
                            String descricao = s.next();
                            System.out.println("Número da sala");
                            int numeroSala = s.nextInt();
                            l.setNome(nome);
                            l.setSite(site);
                            l.setDescricao(descricao);
                            l.setNumeroSala(numeroSala);
                            String nomeSegmento = "";
                            do {
                                System.out.println("Segmento: ");
                                nomeSegmento = s.next();
                            } while (daoSegmento.selecionar(nomeSegmento) == null);
                            if (daoLoja.editar(l)) {
                                System.out.println("A loja foi editada!");
                            } else {
                                System.out.println("Não foi possível realizar a edição.");
                            }
                        }
                    }
                    break;
                }
                case 5: {
                    String nomeShopping = "";
                    do {
                        System.out.println("Digite o nome do shopping: ");
                        nomeShopping = s.next();
                        if (daoShopping.selecionar(nomeShopping) == null) {
                            System.out.println("Shopping não encontrado. Digite novamente.");
                        }
                    } while (daoShopping.selecionar(nomeShopping) == null);
                    
                    System.out.println("Nome da loja: ");
                    String nomeLoja = s.next();

                    for (Loja l : daoShopping.selecionar(nomeShopping).getLojas()) {
                        if (l.getNome().equals(nomeLoja)) {
                            if (daoLoja.excluir(l)) {
                                System.out.println("Exclusão realizada com sucesso!");
                            } else {
                                System.out.println("Não foi possível realizar a exclusão.");
                            }
                        }
                    }

                    break;
                }

                case 6: {
                    for (Shopping shopping : daoShopping.listar()) {
                        System.out.println(shopping.toString());
                        for (Loja l : shopping.getLojas()) {
                            System.out.println(l.toString());
                        }
                    }
                    break;
                }
                case 7: {
                    System.out.println("Nome do shopping: ");
                    String nomeShopping = s.next();
                    if (daoShopping.selecionar(nomeShopping) != null) {
                        System.out.println(daoShopping.selecionar(nomeShopping).toString());
                        for (Loja l : daoShopping.selecionar(nomeShopping).getLojas()) {
                            System.out.println(l.toString());
                        }
                    } else {
                        System.out.println("Shopping não econtrado");
                    }
                    break;
                }
                case 8: {
                    String nomeShopping = "";
                    do {
                        System.out.println("Digite o nome do shopping: ");
                        nomeShopping = s.next();
                        if (daoShopping.selecionar(nomeShopping) == null) {
                            System.out.println("Shopping não encontrado. Digite novamente.");
                        }
                    } while (daoShopping.selecionar(nomeShopping) == null);
                    String nomeSegmento = "";
                    do {
                        System.out.println("Digite o segmento da loja: ");
                        nomeSegmento = s.next();
                        if (daoSegmento.selecionar(nomeSegmento) == null) {
                            System.out.println("Segmento não encontrado. Digite novamente.");
                        }
                    } while (daoSegmento.selecionar(nomeSegmento) == null);
                    for (Loja l : daoShopping.selecionar(nomeShopping).getLojas()) {
                        if (l.getSegmento().getNome().equals(nomeSegmento)) {
                            System.out.println(l.toString());
                        }
                    }
                    break;
                }
            }
            System.out.println("Deseja sair? (0.Não / 1.Sim)");
            opcao = s.nextInt();
        } while (opcao == 0);  
        daoSegmento.fechar();
        daoLoja.fechar();
        daoShopping.fechar();
    }
}
