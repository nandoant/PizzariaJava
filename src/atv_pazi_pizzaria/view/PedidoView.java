package atv_pazi_pizzaria.view;

import java.util.HashMap;
import java.util.Scanner;

import atv_pazi_pizzaria.Model.Cliente;
import atv_pazi_pizzaria.Model.DiaTrabalho;
import atv_pazi_pizzaria.Model.Pedido;
import atv_pazi_pizzaria.Model.Pizza;
import atv_pazi_pizzaria.dao.PizzaDAOImpl;


public class PedidoView {
    private Scanner scanner = new Scanner(System.in);

    public void menuPedidos(HashMap<String, DiaTrabalho> diasDeTrabalho, DiaTrabalho diaSelecionado , PizzaDAOImpl pizzaDB) {
        System.out.println("\n=== Menu Pedidos ===");
        System.out.println("1. Adicionar Pedido");
        System.out.println("2. Atualizar Pedido");
        System.out.println("3. Remover Pedido");
        System.out.println("4. Buscar Pedido");
        System.out.println("5. Listar Todos Pedidos");
        System.out.println("0. Voltar");
        System.out.println("Opcao: ");
        
        int opcao = Integer.parseInt(scanner.nextLine());
        switch (opcao) {
            case 0:
                return;
            case 1:
                adicionarPedido();
                break;
            case 2:
                atualizarPedido();
                break;
            case 3:
                removerPedido();
                break;
            case 4:
                buscarPedido();
                break;
            case 5:
                buscarPedidoPorCliente();
                break;
            case 6:
                this.listarPedidosPorDia();
                break;
            default:
                System.out.println("Opcao invalida!");
        }
    }

    private void adicionarPedido(DiaTrabalho diaSelecionado, PizzaDAOImpl pizzaDB) {
    System.out.println("\n=== Adicionar Pedido ===");

    System.out.print("Nome do Cliente");
    Cliente cliente = new Cliente(scanner.nextLine());

    System.out.print("Observacoes: ");
    String observacoes = scanner.nextLine();
    Pedido pedido = new Pedido(diaSelecionado.getPedidos().size() + 1, cliente, observacoes);

    boolean adicionarPizzas = true;
    while (adicionarPizzas) {
        System.out.println("\nPizzas disponíveis:");
        pizzaDB.listarPizza();
        
        System.out.print("\nID da Pizza (0 para finalizar): ");
        Integer pizzaId = Integer.parseInt(scanner.nextLine());
        
        if (pizzaId == 0) {
            adicionarPizzas = false;
        } else {
            Pizza pizza = pizzaDB.obterPizzaPorId(pizzaId);
            if (pizza != null) {
                pedido.getPizzas().add(pizza);
                System.out.println("Pizza adicionada ao pedido!");
            } else {
                System.out.println("Pizza nao encontrada!");
            }
        }
    }

    if (pedido.getPizzas().isEmpty()) {
        System.out.println("Pedido deve ter pelo menos uma pizza!");
        return;
    }

    diaSelecionado.getPedidos().add(pedido);
    
    System.out.println("Pedido adicionado com sucesso! Total: R$ " + 
        pedido.calcularValorTotal());
}

    private void atualizarPedido() {
        System.out.println("\n=== Atualizar Pedido ===");
        System.out.print("ID do Pedido: ");
        Integer id = Integer.parseInt(scanner.nextLine());

        Pedido pedido = pedidoService.obterPedidoPorId(id);
        if (pedido == null) {
            System.out.println("Pedido nao encontrado!");
            return;
        }

        System.out.print("Novas observacoes (Enter para manter): ");
        String obs = scanner.nextLine();
        if (!obs.trim().isEmpty()) {
            pedido.setObservacao(obs);
        }

        System.out.println("Deseja atualizar as pizzas? (S/N)");
        if (scanner.nextLine().equalsIgnoreCase("S")) {
            pedido.getPizzas().clear();
            boolean adicionarPizzas = true;
            while (adicionarPizzas) {
                listarPizza();
                System.out.print("\nID da Pizza (0 para finalizar): ");
                Integer pizzaId = Integer.parseInt(scanner.nextLine());

                if (pizzaId == 0) {
                    adicionarPizzas = false;
                } else {
                    Pizza pizza = pizzaService.obterPizzaPorId(pizzaId);
                    if (pizza != null) {
                        pedido.getPizzas().add(pizza);
                        System.out.println("Pizza adicionada!");
                    }
                }
            }
        }

        pedidoService.atualizarPedido(pedido);
        System.out.println("Pedido atualizado! Novo total: R$ " + 
            pedido.calcularValorTotal());
    }

    private void removerPedido() {
        System.out.println("\n=== Remover Pedido ===");
        System.out.print("ID do Pedido: ");
        Integer id = Integer.parseInt(scanner.nextLine());

        Pedido pedido = pedidoService.obterPedidoPorId(id);
        if (pedido == null) {
            System.out.println("Pedido nao encontrado!");
            return;
        }

        pedidoService.removerPedido(id);
        System.out.println("Pedido removido com sucesso!");
    }

    private void buscarPedido() {
        System.out.println("\n=== Buscar Pedido ===");
        System.out.print("ID do Pedido: ");
        Integer id = Integer.parseInt(scanner.nextLine());

        Pedido pedido = pedidoService.obterPedidoPorId(id);
        if (pedido != null) {
            exibirDetalhesPedido(pedido);
        } else {
            System.out.println("Pedido nao encontrado!");
        }
    }

    private void exibirDetalhesPedido(Pedido pedido) {
        System.out.println("\nID: " + pedido.getId());
        System.out.println("Cliente: " + pedido.getCliente().getNome());
        System.out.println("Pizzas:");
        for (Pizza pizza : pedido.getPizzas()) {
            System.out.println("- " + pizza.getNome() + " (R$ " + pizza.getPreco() + ")");
        }
        System.out.println("Observacoes: " + pedido.getObservacao());
        System.out.println("Total: R$ " + pedido.calcularValorTotal());
    }
    
}
