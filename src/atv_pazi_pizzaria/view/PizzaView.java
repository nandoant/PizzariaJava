package atv_pazi_pizzaria.view;

import java.util.Scanner;

import atv_pazi_pizzaria.Model.Pizza;
import atv_pazi_pizzaria.Model.PizzaSabores.PizzaGenerica;
import atv_pazi_pizzaria.dao.PizzaDAOImpl;

public class PizzaView {
    private Scanner scanner = new Scanner(System.in);

    public void menuPizzas(PizzaDAOImpl pizzaDB) {
        while (true) {
            System.out.println("\n=== Menu Pizzas ===");
            System.out.println("1. Adicionar Pizza");
            System.out.println("2. Remover Pizza");
            System.out.println("3. Atualizar Pizza");
            System.out.println("4. Buscar Pizza");
            System.out.println("5. Listar Todas as Pizzas");
            System.out.println("0. Voltar");
            System.out.print("Opcao: ");
            
            try {
                int opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 0:
                        return;
                    case 1:
                        adicionarPizza(pizzaDB);
                        break;
                    case 2:
                        removerPizza(pizzaDB);
                        break;
                    case 3:
                        atualizarPizza(pizzaDB);
                        break;
                    case 4:
                        buscarPizza(pizzaDB);
                        break;
                    case 5:
                        listarPizza(pizzaDB);
                        break;
                    default:
                        System.out.println("Opcao invalida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um numero valido.");
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private void adicionarPizza(PizzaDAOImpl pizzaDB) {
        System.out.println("\n=== Adicionar Nova Pizza ===");
        System.out.print("Nome da Pizza: ");
        String nome = scanner.nextLine();
        
        System.out.print("Ingredientes: ");
        String ingredientes = scanner.nextLine();
        
        System.out.print("Preco: ");
        Double preco = Double.parseDouble(scanner.nextLine());
        
        Pizza novaPizza = new PizzaGenerica(preco, nome, ingredientes, 0);
        pizzaDB.adicionarPizza(novaPizza);
        System.out.println("Pizza adicionada com sucesso!");
    }

    private void removerPizza(PizzaDAOImpl pizzaDB) {
        System.out.println("\n=== Remover Pizza ===");
        System.out.print("Digite o ID da pizza a ser removida: ");
        Integer id = Integer.parseInt(scanner.nextLine());
        
        pizzaDB.removerPizza(id);
        System.out.println("Pizza removida com sucesso!");
    }

    private void atualizarPizza(PizzaDAOImpl pizzaDB) {
        System.out.println("\n=== Atualizar Pizza ===");
        System.out.print("Digite o ID da pizza a ser atualizada: ");
        Integer id = Integer.parseInt(scanner.nextLine());
        
        Pizza pizzaExistente = pizzaDB.obterPizzaPorId(id);
        if (pizzaExistente == null) {
            System.out.println("Pizza nao encontrada!");
            return;
        }
        
        System.out.print("Novo nome (Enter para manter atual: " + pizzaExistente.getNome() + "): ");
        String nome = scanner.nextLine();
        if (!nome.trim().isEmpty()) {
            pizzaExistente.setNome(nome);
        }
        
        System.out.print("Novos ingredientes (Enter para manter atual: " + pizzaExistente.getIngredientes() + "): ");
        String ingredientes = scanner.nextLine();
        if (!ingredientes.trim().isEmpty()) {
            pizzaExistente.setIngredientes(ingredientes);
        }
        
        System.out.print("Novo preco (Enter para manter atual: " + pizzaExistente.getPreco() + "): ");
        String precoStr = scanner.nextLine();
        if (!precoStr.trim().isEmpty()) {
            pizzaExistente.setPreco(Double.parseDouble(precoStr));
        }
        
        pizzaDB.atualizarPizza(pizzaExistente);
        System.out.println("Pizza atualizada com sucesso!");
    }

    private void buscarPizza(PizzaDAOImpl pizzaDB) {
        System.out.println("\n=== Buscar Pizza ===");
        System.out.print("Digite o ID da pizza: ");
        Integer id = Integer.parseInt(scanner.nextLine());
        
        Pizza pizza = pizzaDB.obterPizzaPorId(id);
        if (pizza != null) {
            System.out.println("\nPizza encontrada:");
            System.out.println("ID: " + pizza.getId());
            System.out.println("Nome: " + pizza.getNome());
            System.out.println("Ingredientes: " + pizza.getIngredientes());
            System.out.println("Preco: R$ " + pizza.getPreco());
        } else {
            System.out.println("Pizza nao encontrada!");
        }
    }
    
    private void listarPizza(PizzaDAOImpl pizzaDB) {
        System.out.println("\n=== Pizzas Disponiveis ===");
        
        for (Pizza pizza: pizzaDB.obterTodasPizzas()){
        
            System.out.println("ID: " + pizza.getId());
            System.out.println("Nome: " + pizza.getNome());
            System.out.println("Ingredientes: " + pizza.getIngredientes());
            System.out.println("Preco: R$ " + pizza.getPreco());
            System.out.println("----------------------");
        }
    }
}
