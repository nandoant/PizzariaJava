
package atv_pazi_pizzaria;

import atv_pazi_pizzaria.Model.Cliente;
import atv_pazi_pizzaria.Model.DiaTrabalho;
import atv_pazi_pizzaria.Model.Pedido;
import atv_pazi_pizzaria.Model.Pizza;
import atv_pazi_pizzaria.Model.Pizzas.PizzaGenerica;
import atv_pazi_pizzaria.service.ClienteService;
import atv_pazi_pizzaria.service.DiaTrabalhoService;
import atv_pazi_pizzaria.service.PedidoService;
import atv_pazi_pizzaria.service.PizzaService;

import java.util.List;
import java.util.Scanner;

public class UI {
    private Scanner scanner;
    private PedidoService pedidoService;
    private PizzaService pizzaService;
    private DiaTrabalhoService diaTrabalhoService;
    private ClienteService clienteService;

    public UI(Scanner scanner, PedidoService pedidoService, PizzaService pizzaService, DiaTrabalhoService diaTrabalhoService, ClienteService clienteService) {
        this.scanner = scanner;
        this.pedidoService = pedidoService;
        this.pizzaService = pizzaService;
        this.diaTrabalhoService = diaTrabalhoService;
        this.clienteService = clienteService;
        DiaTrabalho diaAtual = new DiaTrabalho(0,"20/11/24");
    }

    public void start() {
        boolean executar = true;
        while (executar) {
            mostrarMenuPrincipal();
            int opcao = Integer.parseInt(scanner.nextLine());
            
            switch (opcao) {
                case 1:
                    menuPedidos();
                    break;
                case 2:
                    menuPizzas();
                    break;
                case 3:
                    menuDiaTrabalho();
                    break;
                case 4:
                    menuClientes();
                    break;
                case 0:
                    executar = false;
                    break;
                default:
                    System.out.println("Opcao invalida!");
            }
        }
    }

    private void mostrarMenuPrincipal() {
        System.out.println("\n=== Sistema da Pizzaria ===");
        System.out.println("1. Pedidos");
        System.out.println("2. Pizzas");
        System.out.println("3. Dia de Trabalho");
        System.out.println("4. Clientes");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opcao: ");
    }

    
    
    //PEDIDOS
    private void menuPedidos() {
        System.out.println("\n=== Menu Pedidos ===");
        System.out.println("1. Adicionar Pedido");
        System.out.println("2. Atualizar Pedido");
        System.out.println("3. Remover Pedido");
        System.out.println("4. Buscar Pedido");
        System.out.println("5. Buscar Pedido por Cliente");
        System.out.println("6. Listar Pedidos por Dia de Trabalho");
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

    private void adicionarPedido() {
    System.out.println("\n=== Adicionar Pedido ===");
    
    System.out.print("Data do pedido (DD/MM/YY): ");
    String data = scanner.nextLine();
    DiaTrabalho diaTrabalho = diaTrabalhoService.obterDiaTrabalhoPorId(data);
    if (diaTrabalho == null) {
        System.out.println("Dia de trabalho nao encontrado!");
        return;
    }

    System.out.print("ID do Cliente: ");
    Integer clienteId = Integer.parseInt(scanner.nextLine());
    Cliente cliente = clienteService.obterClientePorId(clienteId);
    if (cliente == null) {
        System.out.println("Cliente nao encontrado!");
        return;
    }

    System.out.print("Observacoes: ");
    String observacoes = scanner.nextLine();
    Pedido pedido = new Pedido(0, cliente, observacoes);

    boolean adicionarPizzas = true;
    while (adicionarPizzas) {
        System.out.println("\nPizzas disponíveis:");
        listarPizza();
        
        System.out.print("\nID da Pizza (0 para finalizar): ");
        Integer pizzaId = Integer.parseInt(scanner.nextLine());
        
        if (pizzaId == 0) {
            adicionarPizzas = false;
        } else {
            Pizza pizza = pizzaService.obterPizzaPorId(pizzaId);
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

    pedidoService.adicionarPedido(pedido);
    diaTrabalho.getPedidos().add(pedido);
    diaTrabalhoService.atualizarDiaTrabalho(diaTrabalho);
    
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

    private void buscarPedidoPorCliente() {
        System.out.println("\n=== Buscar Pedido por Cliente ===");
        System.out.print("ID do Cliente: ");
        Integer clienteId = Integer.parseInt(scanner.nextLine());

        Pedido pedido = pedidoService.obterPedidoPorClienteID(clienteId);
        if (pedido != null) {
            exibirDetalhesPedido(pedido);
        } else {
            System.out.println("Nenhum pedido encontrado para este cliente!");
        }
    }

    private void listarPedidosPorDia() {
        System.out.println("\n=== Listar Pedidos por Dia ===");
        System.out.print("Data (DD/MM/YY): ");
        String data = scanner.nextLine();

        DiaTrabalho dia = diaTrabalhoService.obterDiaTrabalhoPorId(data);
        if (dia != null) {
            for (Pedido pedido : dia.getPedidos()) {
                exibirDetalhesPedido(pedido);
                System.out.println("--------------------");
            }
        } else {
            System.out.println("Dia nao encontrado!");
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

    
    
   //Pizzas
    private void menuPizzas() {
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
                        adicionarPizza();
                        break;
                    case 2:
                        removerPizza();
                        break;
                    case 3:
                        atualizarPizza();
                        break;
                    case 4:
                        buscarPizza();
                        break;
                    case 5:
                        listarPizza();
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

    private void adicionarPizza() {
        System.out.println("\n=== Adicionar Nova Pizza ===");
        System.out.print("Nome da Pizza: ");
        String nome = scanner.nextLine();
        
        System.out.print("Ingredientes: ");
        String ingredientes = scanner.nextLine();
        
        System.out.print("Preco: ");
        Double preco = Double.parseDouble(scanner.nextLine());
        
        Pizza novaPizza = new PizzaGenerica(preco, nome, ingredientes, 0);
        pizzaService.adicionarPizza(novaPizza);
        System.out.println("Pizza adicionada com sucesso!");
    }

    private void removerPizza() {
        System.out.println("\n=== Remover Pizza ===");
        System.out.print("Digite o ID da pizza a ser removida: ");
        Integer id = Integer.parseInt(scanner.nextLine());
        
        pizzaService.removerPizza(id);
        System.out.println("Pizza removida com sucesso!");
    }

    private void atualizarPizza() {
        System.out.println("\n=== Atualizar Pizza ===");
        System.out.print("Digite o ID da pizza a ser atualizada: ");
        Integer id = Integer.parseInt(scanner.nextLine());
        
        Pizza pizzaExistente = pizzaService.obterPizzaPorId(id);
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
        
        pizzaService.atualizarPizza(pizzaExistente);
        System.out.println("Pizza atualizada com sucesso!");
    }

    private void buscarPizza() {
        System.out.println("\n=== Buscar Pizza ===");
        System.out.print("Digite o ID da pizza: ");
        Integer id = Integer.parseInt(scanner.nextLine());
        
        Pizza pizza = pizzaService.obterPizzaPorId(id);
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
    
    private void listarPizza() {
        System.out.println("\n=== Pizzas Disponiveis ===");
        
        for (Pizza pizza: pizzaService.obterTodasPizzas()){
        
            System.out.println("ID: " + pizza.getId());
            System.out.println("Nome: " + pizza.getNome());
            System.out.println("Ingredientes: " + pizza.getIngredientes());
            System.out.println("Preco: R$ " + pizza.getPreco());
            System.out.println("----------------------");
        }
    }

    
    
    //DIA TRABALHO
    private void menuDiaTrabalho() {
        while (true) {
            System.out.println("\n=== Menu Dia de Trabalho ===");
            System.out.println("1. Adicionar Dia de Trabalho");
            System.out.println("2. Remover Dia de Trabalho");
            System.out.println("3. Atualizar Dia de Trabalho");
            System.out.println("4. Buscar Dia de Trabalho");
            System.out.println("5. Listar Todos Dia de Trabalho");
            System.out.println("0. Voltar");
            System.out.print("Opcao: ");

            try {
                int opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1:
                        adicionarDiaTrabalho();
                        break;
                    case 2:
                        removerDiaTrabalho();
                        break;
                    case 3:
                        atualizarDiaTrabalho();
                        break;
                    case 4:
                        buscarDiaTrabalho();
                        break;
                    case 5:
                        listarDiasTrabalho();
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Opcao invalida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um numero valido!");
            }
        }
    }

    private void adicionarDiaTrabalho() {
        System.out.println("\n=== Adicionar Dia de Trabalho ===");
        System.out.println("Digite a data (DD/MM/YY):");
        String data = scanner.nextLine();
        
        DiaTrabalho diaTrabalho = new DiaTrabalho(0, data);
        diaTrabalhoService.adicionarDiaTrabalho(diaTrabalho);
        System.out.println("Dia de trabalho adicionado com sucesso!");
    }

    private void removerDiaTrabalho() {
        System.out.println("\n=== Remover Dia de Trabalho ===");
        System.out.println("Digite a data do dia de trabalho (DD/MM/YY):");
        String data = scanner.nextLine();
        
        diaTrabalhoService.removerDiaTrabalho(data);
        System.out.println("Dia de trabalho removido com sucesso!");
    }

    private void atualizarDiaTrabalho() {
        System.out.println("\n=== Atualizar Dia de Trabalho ===");
        System.out.println("Digite a data atual (DD/MM/YY):");
        String dataAtual = scanner.nextLine();
        
        DiaTrabalho diaTrabalho = diaTrabalhoService.obterDiaTrabalhoPorId(dataAtual);
        if (diaTrabalho != null) {
            System.out.println("Digite o novo ID:");
            Integer novoId = Integer.parseInt(scanner.nextLine());
            System.out.println("Digite a nova data (DD/MM/YYYY):");
            String novaData = scanner.nextLine();
            
            diaTrabalho.setId(novoId);
            diaTrabalho.setData(novaData);
            diaTrabalhoService.atualizarDiaTrabalho(diaTrabalho);
            System.out.println("Dia de trabalho atualizado com sucesso!");
        } else {
            System.out.println("Dia de trabalho nao encontrado!");
        }
    }

    private void buscarDiaTrabalho() {
        System.out.println("\n=== Buscar Dia de Trabalho ===");
        System.out.println("Digite a data (DD/MM/YYYY):");
        String data = scanner.nextLine();
        
        DiaTrabalho diaTrabalho = diaTrabalhoService.obterDiaTrabalhoPorId(data);
        if (diaTrabalho != null) {
            System.out.println("-------------------");
            System.out.println("ID: " + diaTrabalho.getId());
            System.out.println("Data: " + diaTrabalho.getData());
            System.out.println("Numero de pedidos: " + diaTrabalho.getPedidos().size());
        } else {
            System.out.println("Dia de trabalho nao encontrado!");
        }
    }

    private void listarDiasTrabalho() {
        System.out.println("\n=== Lista de Dias de Trabalho ===");
        List<DiaTrabalho> dias = diaTrabalhoService.obterTodosDiasTrabalho();
        if (dias.isEmpty()) {
            System.out.println("Nenhum dia de trabalho cadastrado!");
        } else {
            for (DiaTrabalho dia : dias) {
                System.out.println("ID: " + dia.getId());
                System.out.println("Data: " + dia.getData());
                System.out.println("Numero de pedidos: " + dia.getPedidos().size());
                System.out.println("--------------------");
            }
        }
    }
    
    
    
    //Clientes
    private void menuClientes() {
        while (true) {
            System.out.println("\n=== Menu Clientes ===");
            System.out.println("1. Adicionar Cliente");
            System.out.println("2. Remover Cliente");
            System.out.println("3. Atualizar Cliente");
            System.out.println("4. Buscar Cliente");
            System.out.println("5. Listar Todos os Clientes");
            System.out.println("0. Voltar");
            System.out.print("Opcao: ");

            try {
                int opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 0:
                        return;
                    case 1:
                        adicionarCliente();
                        break;
                    case 2:
                        removerCliente();
                        break;
                    case 3:
                        atualizarCliente();
                        break;
                    case 4:
                        buscarCliente();
                        break;
                    case 5:
                        listarClientes();
                        break;
                    default:
                        System.out.println("Opcao invalida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um numero valido.");
            }
        }
    }

    private void adicionarCliente() {
        System.out.println("\n=== Adicionar Novo Cliente ===");
        System.out.print("Nome do Cliente: ");
        String nome = scanner.nextLine();

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        Cliente novoCliente = new Cliente(0, telefone, nome);
        clienteService.adicionarCliente(novoCliente);
        System.out.println("Cliente adicionado com sucesso!");
    }

    private void removerCliente() {
        System.out.println("\n=== Remover Cliente ===");
        System.out.print("Digite o ID do cliente a ser removido: ");
        Integer id = Integer.parseInt(scanner.nextLine());

        clienteService.removerCliente(id);
        System.out.println("Cliente removido com sucesso!");
    }

    private void atualizarCliente() {
        System.out.println("\n=== Atualizar Cliente ===");
        System.out.print("Digite o ID do cliente a ser atualizado: ");
        Integer id = Integer.parseInt(scanner.nextLine());

        Cliente clienteExistente = clienteService.obterClientePorId(id);
        if (clienteExistente == null) {
            System.out.println("Cliente nao encontrado!");
            return;
        }

        System.out.print("Novo nome (Enter para manter atual: " + clienteExistente.getNome() + "): ");
        String nome = scanner.nextLine();
        if (!nome.trim().isEmpty()) {
            clienteExistente.setNome(nome);
        }

        System.out.print("Novo telefone (Enter para manter atual: " + clienteExistente.getTelefone() + "): ");
        String telefone = scanner.nextLine();
        if (!telefone.trim().isEmpty()) {
            clienteExistente.setTelefone(telefone);
        }

        clienteService.atualizarCliente(clienteExistente);
        System.out.println("Cliente atualizado com sucesso!");
    }

    private void buscarCliente() {
        System.out.println("\n=== Buscar Cliente ===");
        System.out.print("Digite o ID do cliente: ");
        Integer id = Integer.parseInt(scanner.nextLine());

        Cliente cliente = clienteService.obterClientePorId(id);
        if (cliente != null) {
            exibirDetalhesCliente(cliente);
        } else {
            System.out.println("Cliente nao encontrado!");
        }
    }

    private void listarClientes() {
        System.out.println("\n=== Lista de Clientes ===");
        List<Cliente> clientes = clienteService.obterTodosClientes();
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado!");
        } else {
            for (Cliente cliente : clientes) {
                exibirDetalhesCliente(cliente);
                System.out.println("--------------------");
            }
        }
    }

    private void exibirDetalhesCliente(Cliente cliente) {
        System.out.println("ID: " + cliente.getId());
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("Telefone: " + cliente.getTelefone());
    }
}
