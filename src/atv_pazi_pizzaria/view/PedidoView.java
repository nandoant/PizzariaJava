package atv_pazi_pizzaria.view;

import java.util.Scanner;

import atv_pazi_pizzaria.Model.Cliente;
import atv_pazi_pizzaria.Model.DiaTrabalho;
import atv_pazi_pizzaria.Model.Pedido;
import atv_pazi_pizzaria.Model.Pizza;
import atv_pazi_pizzaria.dao.PizzaDAOImpl;


public class PedidoView {
    private Scanner scanner = new Scanner(System.in);

    public void menuPedidos(DiaTrabalho diaSelecionado , PizzaDAOImpl pizzaDB) {
        while (true) {
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
                    adicionarPedido(diaSelecionado, pizzaDB);
                    break;
                case 2:
                    atualizarPedido(diaSelecionado, pizzaDB);
                    break;
                case 3:
                    removerPedido(diaSelecionado);
                    break;
                case 4:
                    buscarPedido(diaSelecionado);
                    break;
                case 5:
                    listarTodos(diaSelecionado);
                    break;
                default:
                    System.out.println("Opcao invalida!");
            }
        }
    }

    private void adicionarPedido(DiaTrabalho diaSelecionado, PizzaDAOImpl pizzaDB) {
    System.out.println("\n=== Adicionar Pedido ===");

    System.out.print("Nome do Cliente: ");
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

    private void atualizarPedido(DiaTrabalho diaSelecionado, PizzaDAOImpl pizzaDB) {
        System.out.println("\n=== Atualizar Pedido ===");
        System.out.print("ID do Pedido: ");
        Integer id = Integer.parseInt(scanner.nextLine());

        Pedido pedido = diaSelecionado.getPedidos().stream()
            .filter(p -> p.getId().equals(id))
            .findFirst()
            .orElse(null);

        if (pedido == null) {
            System.out.println("Pedido nao encontrado!");
            return;
        }

        System.out.print("Nome Atual: " + pedido.getCliente().getNome() + " (Enter para manter): ");
        String nomeCliente = scanner.nextLine();
        if (!nomeCliente.trim().isEmpty()) {
            pedido.getCliente().setNome(nomeCliente);
        }

        System.out.print("Observacoes Atual: "+pedido.getObservacao()+" (Enter para manter): ");
        String obs = scanner.nextLine();
        if (!obs.trim().isEmpty()) {
            pedido.setObservacao(obs);
        }

        System.out.println("Deseja atualizar as pizzas? (S/N)");
        if (scanner.nextLine().equalsIgnoreCase("S")) {
            pedido.getPizzas().clear();
            boolean adicionarPizzas = true;
            while (adicionarPizzas) {
                pizzaDB.listarPizza();
                System.out.print("\nID da Pizza (0 para finalizar): ");
                Integer pizzaId = Integer.parseInt(scanner.nextLine());

                if (pizzaId == 0) {
                    adicionarPizzas = false;
                } else {
                    Pizza pizza = pizzaDB.obterPizzaPorId(pizzaId);
                    if (pizza != null) {
                        pedido.getPizzas().add(pizza);
                        System.out.println("Pizza adicionada!");
                    }
                }
            }
        }

        diaSelecionado.getPedidos().add(pedido);
        System.out.println("Pedido atualizado! Novo total: R$ " + 
            pedido.calcularValorTotal());
    }

    private void removerPedido(DiaTrabalho diaSelecionado) {
        System.out.println("\n=== Remover Pedido ===");
        System.out.print("ID do Pedido: ");
        Integer id = Integer.parseInt(scanner.nextLine());

        Pedido pedido = diaSelecionado.getPedidos().stream()
            .filter(p -> p.getId().equals(id))
            .findFirst()
            .orElse(null);

        if (pedido == null) {
            System.out.println("Pedido nao encontrado!");
            return;
        }

        diaSelecionado.getPedidos().remove(pedido);
        System.out.println("Pedido removido com sucesso!");
    }

    private void buscarPedido(DiaTrabalho diaSelecionado) {
        System.out.println("\n=== Buscar Pedido ===");
        System.out.print("ID do Pedido: ");
        Integer id = Integer.parseInt(scanner.nextLine());

        Pedido pedido = diaSelecionado.getPedidos().stream()
            .filter(p -> p.getId().equals(id))
            .findFirst()
            .orElse(null);

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

    private void listarTodos(DiaTrabalho diaSelecionado) {
        System.out.println("\n=== Todos Pedidos ===");
        if(diaSelecionado.getPedidos().isEmpty()){
            System.out.println("Ainda nao foram feitos pedidos nesse dia!");
            return;
        }   

        for (Pedido pedido : diaSelecionado.getPedidos()) {
            exibirDetalhesPedido(pedido);
            System.out.println("----------------------");
        }
    }
    
}
