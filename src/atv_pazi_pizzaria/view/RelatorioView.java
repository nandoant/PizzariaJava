package atv_pazi_pizzaria.view;

import java.util.HashMap;
import java.util.Scanner;

import atv_pazi_pizzaria.Model.DiaTrabalho;
import atv_pazi_pizzaria.Model.Pedido;
import atv_pazi_pizzaria.Model.Pizza;

public class RelatorioView {
    private Scanner scanner = new Scanner(System.in);

    public void menuRelatorios(HashMap<String, DiaTrabalho> diasDeTrabalho, DiaTrabalho diaSelecionado) {
        diasDeTrabalho.put(diaSelecionado.getData(), diaSelecionado);

        while (true) {
            System.out.println("\n=== Menu Relatorios ===");
            System.out.println("1. Pedidos por Cliente");
            System.out.println("2. Pedidos por Dia de Trabalho");
            System.out.println("0. Voltar");
            System.out.println("Opcao: ");

            try{
                int opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 0:
                        return;
                    case 1:
                        pedidosPorCliente(diasDeTrabalho);
                        break;
                    case 2:
                        pedidosPorDiaTrabalho(diasDeTrabalho);
                        break;
                    default:
                        System.out.println("Opcao invalida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um numero valido!");
            }
            
        }
    }

    private void pedidosPorCliente(HashMap<String, DiaTrabalho> diasDeTrabalho) {
        int count = 0;
        double total = 0.0;
        System.out.println("\n=== Pedidos por Cliente ===");
        System.out.print("Nome do Cliente: ");
        String nome = scanner.nextLine();

        for (DiaTrabalho dia : diasDeTrabalho.values()) {
            for (Pedido pedido : dia.getPedidos()) {
                if (pedido.getCliente().getNome().equals(nome)) {
                    System.out.println("Data: "+dia.getData());
                    exibirDetalhesPedido(pedido);
                    count++;
                    total += pedido.calcularValorTotal();
                }
            }
        }
        if (count == 0) {
            System.out.println("Nenhum pedido encontrado para o cliente " + nome);
        }else{
            System.out.println("Total de pedidos: " + count);
            System.out.println("Total: R$ " + total);
            System.out.println("----------------------");
        }
    }

    private void pedidosPorDiaTrabalho(HashMap<String, DiaTrabalho> diasDeTrabalho) {
        double total = 0.0;
        int count = 0;

        System.out.println("\n=== Pedidos por Dia de Trabalho ===");
        System.out.print("Dia de Trabalho: ");
        String data = scanner.nextLine();
        DiaTrabalho dia = diasDeTrabalho.get(data);
        if (dia == null) {
            System.out.println("Nenhum pedido encontrado para o dia " + data);
        } else {
            for (Pedido pedido : dia.getPedidos()) {
                exibirDetalhesPedido(pedido);;
                count++;
                total += pedido.calcularValorTotal();
            }
        }
        System.out.println("Total de pedidos: " + count);
        System.out.println("Total do dia: " + total);
        System.out.println("----------------------");
    }

    private void exibirDetalhesPedido(Pedido pedido) {
        System.out.println("ID: " + pedido.getId());
        System.out.println("Cliente: " + pedido.getCliente().getNome());
        System.out.println("Pizzas:");
        for (Pizza pizza : pedido.getPizzas()) {
            System.out.println("- " + pizza.getNome() + " (R$ " + pizza.getPreco() + ")");
        }
        System.out.println("Observacoes: " + pedido.getObservacao());
        System.out.println("Total: R$ " + pedido.calcularValorTotal());
        System.out.println("----------------------");
    }
}
