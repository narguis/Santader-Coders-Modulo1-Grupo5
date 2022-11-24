import java.io.IOException;
import java.util.*;


public class Projeto1 {
    static Scanner scanner = new Scanner(System.in);
    static List<Produtos> produtos = new ArrayList<>();
    public static void main(String[] args) {
        Menu();
        Produtos p = new Produtos("café", 8, 5.5f);
        System.out.println(p.tostr());
    }

        public static void Menu() {
            System.out.println("""
                    0 - CRIAR PRODUTO
                    1 - EDITAR PRODUTO
                    2 - EXCLUIR PRODUTO
                    3 - PESQUISAR PRODUTOS
                    4 - COMPRA DE PRODUTOS
                    5 - SAIR DO PROGRAMA""");
            String escolha = "";

            while (!escolha.equals("5")){
                System.out.print("O que você deseja fazer? ");
                escolha = scanner.next();

            switch (escolha) {
                case "0" -> criarProduto();
                case "1" -> editarProduto();
                case "2" -> excluirProduto();
                case "3" -> pesquisarProduto();
                case "4" -> comprarProdutos();
                case "5" -> {}
                default -> System.out.println("Opção Inválida!");

            }
            }
        }

    private static void comprarProdutos() {

    }

    private static void pesquisarProduto() {
    }

    private static void excluirProduto() {
    }

    private static void editarProduto() {
    }

    private static void criarProduto() {
        System.out.print("Nome do produto: ");
        String nm = scanner.next();

        System.out.print("Quantidade em estoque: ");
        int qtd = scanner.nextInt();

        System.out.print("Preço do produto: R$");
        float prc = scanner.nextFloat();

        Produtos prod = new Produtos(nm, qtd, prc);

        produtos.add(prod);
    }
}