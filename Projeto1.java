import java.util.*;

public class Projeto1 {
    static Scanner scanner = new Scanner(System.in);
    static List<Produtos> produtos = new ArrayList<>();
    static List<Carrinho> carrinho = new ArrayList<>();

    public static void main(String[] args) {
        Menu();
        System.out.println();
    }

    public static void Menu() {
        String escolha = "";
        while (!escolha.equals("0")) {
            System.out.println("""
                    1 - CRIAR PRODUTO
                    2 - EDITAR PRODUTO
                    3 - EXCLUIR PRODUTO
                    4 - PESQUISAR PRODUTOS
                    5 - VENDER DE PRODUTOS
                    0 - SAIR DO PROGRAMA""");
            System.out.print("O que você deseja fazer? ");
            escolha = scanner.next();

            switch (escolha) {
                case "1":
                    boolean continuarCriando = true;
                    do {
                        criarProduto();
                        continuarCriando = desejaContinuar();
                    } while (continuarCriando);
                    break;

                case "2": {
                    if (produtos.size() != 0) {
                        boolean continuarEditando = true;
                        do {
                            editarProduto();
                            continuarEditando = desejaContinuar();
                        } while (continuarEditando);
                        listagemProdutos();
                    } else {
                        System.out.println("Não há produtos cadastrados.");
                        System.out.println();
                    }
                    break;
                }
                case "3": {
                    if (produtos.size() != 0) {
                        boolean continuarExcluindo = true;
                        do {
                            excluirProduto();
                            continuarExcluindo = desejaContinuar();
                        } while (continuarExcluindo);
                    } else {
                        System.out.println("Não há produtos cadastrados.");
                        System.out.println();
                    }
                    break;
                }
                case "4": {
                    if (produtos.size()!=0){
                        boolean continuarPesquisando = true;
                        do {
                            pesquisarProduto();
                            continuarPesquisando = desejaContinuar();
                        } while (continuarPesquisando);
                    } else {
                        System.out.println("Não há produtos cadastrados.");
                        System.out.println();
                    }
                    break;
                }
                case "5": {
                    boolean continuarVendendo = true;
                    System.out.println("=============MENU VENDAS=============");
                    listagemProdutos();
                    do {
                        venderProdutos();
                        continuarVendendo = desejaContinuar();

                    } while (continuarVendendo);
                    seuCarrinho(carrinho);
                    break;
                }
                case "0":
                    System.out.println("Finalizando programa...");
                    return;
                default: {
                    System.out.println("Opção Inválida!");
                    break;
                }
            }
        }
    }

    private static void venderProdutos() {
        float valorProduto = 0.0F;
            System.out.println("Digite o codigo do produto:");
            int codigo = scanner.nextInt();
            codigo -=1;
            if(codigo >= 0 && codigo < produtos.size()){
                    System.out.println("Digite a quantidade do produto:");
                    int quantidade = scanner.nextInt();
                    if (quantidade <= produtos.get(codigo).getQuantidade()) {
                        valorProduto = produtos.get(codigo).getPreco() * quantidade;
                        Carrinho car = new Carrinho(produtos.get(codigo).getNome(), quantidade, valorProduto);
                        carrinho.add(car);
                    } else {
                        System.out.println("Quantidade não encontrada em estoque.");
                    }
            }else {
                System.out.println("Codigo nao encontrado a lista.");
            }
    }

    private static void seuCarrinho(List<Carrinho> carrinho) {
        float valorTotal = 0.0f;
        System.out.println("Seu Carrinho:");
        for (int i = 0; i < carrinho.size(); i++) {
            valorTotal += carrinho.get(i).getValor();
            System.out.print((i + 1) + " - Nome: " + carrinho.get(i).getNome() + "\t - Quantidade: "
                    + carrinho.get(i).getQuantidade() + "\t - R$: " + carrinho.get(i).getValor() + "\n");
            System.out.println();
        }
        System.out.println("Valor total: " + valorTotal);
        for (int i = 0; i < produtos.size(); i++) {
            for (int j = 0; j < carrinho.size(); j++) {
                if (produtos.get(i).getNome().equalsIgnoreCase(carrinho.get(j).getNome())) {
                    produtos.get(i).setQuantidade(produtos.get(i).getQuantidade() - carrinho.get(j).getQuantidade());
                }
        }
    }
    }

    private static void listagemProdutos() {
        System.out.println("Lista de produtos:");
        System.out.println("Produto\t / Quantidade\t / Preço");
        for (int i = 0; i < produtos.size(); i++) {
            System.out.print((i + 1) + " - Nome: " + produtos.get(i).getNome() + "\t - Quantidade: "
                    + produtos.get(i).getQuantidade() + "\t - R$: " + produtos.get(i).getPreco() + "\n");
        }//acessar o produto utilizando o numero do produto -1;
    }

    private static void pesquisarProduto() {
        System.out.println("Digite o nome do produto:");
        String produtoPesquisado = scanner.next();
        int contador=0;
        for (int i = 0; i < produtos.size(); i++) {
            String produto = produtos.get(i).getNome();
            if (produtoPesquisado.equalsIgnoreCase(produto)){
                System.out.print((i + 1) + " - Nome: " + produtos.get(i).getNome() + "\t - Quantidade: "
                        + produtos.get(i).getQuantidade() + "\t - R$: " + produtos.get(i).getPreco() + "\n");
                System.out.println();
            } else {
                contador++;
            }
        }
        if (contador== produtos.size()){
            System.out.println("Produto não encontrado!");
            System.out.println();
        }
    }

    private static void excluirProduto() {
        listagemProdutos();
        System.out.println("Escolha um item para excluir:");
        int indice = scanner.nextInt();
        if (indice <= produtos.size() && indice > 0) {
            produtos.remove(indice - 1);
            listagemProdutos();
        }else{
            System.out.println("Item inválido");
        }
    }

    private static void editarProduto() {
        listagemProdutos();
        System.out.println("Digite o indice do produto:");
        int indice = scanner.nextInt();
        if (indice > 0 && indice <= produtos.size()) {
            System.out.println("""
                    1 - EDITAR NOME
                    2 - EDITAR QUANTIDADE
                    3 - EDITAR PREÇO""");
            System.out.print("O que você deseja fazer? ");
            int tipoEdicao = scanner.nextInt();
            switch (tipoEdicao) {
                case 1: {
                    System.out.println("Digite o novo NOME do produto:");
                    String novoNome = scanner.next();
                    produtos.get(indice - 1).setNome(novoNome);
                    break;
                }
                case 2: {
                    System.out.println("Digite a nova QUANTIDADE do produto:");
                    int novaQuantidade = scanner.nextInt();
                    produtos.get(indice - 1).setQuantidade(novaQuantidade);
                    break;
                }
                case 3: {
                    System.out.println("Digite o novo preço do produto:");
                    float novoPreco = scanner.nextFloat();
                    produtos.get(indice - 1).setPreco(novoPreco);
                    break;
                }
                default: {
                    System.out.println("Opção inválida...");
                    break;
                }
            }
        } else{
            System.out.println("Indice inválido");
        }
    }

    private static void criarProduto () {
        System.out.print("Nome do produto: ");
        String nm = scanner.next();

        System.out.print("Quantidade em estoque: ");
        int qtd = scanner.nextInt();

        System.out.print("Preço do produto: R$");
        float prc = scanner.nextFloat();

        Produtos prod = new Produtos(nm, qtd, prc);

        produtos.add(prod);
    }

    public static boolean desejaContinuar () {
        boolean continuar_Lista = true;
        int mais_Produtos;
        boolean adicionarNovoProduto = true;
        do {
            try {
                System.out.println("Deseja continuar?\n1 - Sim\n2 - Não");
                mais_Produtos = scanner.nextInt();
                if (mais_Produtos == 1) {
                    continuar_Lista = false;
                    adicionarNovoProduto = true;
                } else if (mais_Produtos == 2) {
                    continuar_Lista = false;
                    adicionarNovoProduto = false;
                    System.out.println("Finalizad0.");
                } else {
                    System.out.println("Não é uma opção válida...");
                    continuar_Lista = true;
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Entrada inválida. Digite 1 para 'sim' ou 2 para 'não'");
                continuar_Lista = true;
            }
        } while (continuar_Lista);
        return adicionarNovoProduto;
    }

}
