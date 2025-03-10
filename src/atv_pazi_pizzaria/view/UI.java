
package atv_pazi_pizzaria.view;

import java.util.HashMap;
import java.util.Scanner;

import atv_pazi_pizzaria.Model.DiaTrabalho;
import atv_pazi_pizzaria.dao.PizzaDAOImpl;

public class UI {
    private Scanner scanner;
    private DiaTrabalhoView diaTrabalho = new DiaTrabalhoView();
    private PizzaView pizza = new PizzaView();
    private PedidoView pedido = new PedidoView();
    private RelatorioView relatorio = new RelatorioView();
    private PizzaDAOImpl pizzaDB = new PizzaDAOImpl();

    public UI(Scanner scanner) {
        this.scanner = scanner;
    }

    public void start() {
        boolean executar = true;
        HashMap<String, DiaTrabalho> diasDeTrabalho = new HashMap<>();
        DiaTrabalho diaSelecionado = new DiaTrabalho("00/00/00");

        while (executar) {
            mostrarMenuPrincipal(diaSelecionado);

            try{
                int opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1:
                        pedido.menuPedidos(diaSelecionado, pizzaDB);
                        break;
                    case 2:
                        pizza.menuPizzas(pizzaDB);
                        break;
                    case 3:
                        diaTrabalho.menuDiaTrabalho(diasDeTrabalho, diaSelecionado);
                        break;
                    case 4:
                        relatorio.menuRelatorios(diasDeTrabalho, diaSelecionado);
                        break;  
                    case 5:
                        diaSelecionado = alterarDiaSelecionado(diasDeTrabalho, diaSelecionado);
                        break;
                    case 0:
                        executar = false;
                        break;
                    default:
                        System.out.println("Opcao invalida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um numero valido!");
            } 
            
        }
    }

    private void mostrarMenuPrincipal(DiaTrabalho diaSelecionado) {
        System.out.println("\n--------------------------");
        System.out.println("Dia Selecionado: " + diaSelecionado.getData());
        System.out.println("--------------------------");
        System.out.println("=== Sistema da Pizzaria ===");
        System.out.println("1. Pedidos");
        System.out.println("2. Pizzas");
        System.out.println("3. Dia de Trabalho");
        System.out.println("4. Relatorios");
        System.out.println("5. Alterar Dia Selecionado");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opcao: ");
    }

    private DiaTrabalho alterarDiaSelecionado(HashMap<String, DiaTrabalho> diasDeTrabalho, DiaTrabalho diaSelecionado) {
        diasDeTrabalho.put(diaSelecionado.getData(), diaSelecionado);
        System.out.println("\n=== Alterar Dia Selecionado ===");
        System.out.println("Digite a data que deseja selecionar (DD/MM/YY):");
        String data = scanner.nextLine();
        
        DiaTrabalho diaTrabalho = diasDeTrabalho.get(data);
        if (diaTrabalho != null) {
            System.out.println("Dia de trabalho selecionado alterado com sucesso!");
            return diaTrabalho;
        } else {
            System.out.println("Dia de trabalho nao encontrado!");
            return diaSelecionado;
        }

    }
}