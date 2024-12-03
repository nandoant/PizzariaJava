package atv_pazi_pizzaria.view;

import java.util.HashMap;
import java.util.Scanner;

import atv_pazi_pizzaria.Model.DiaTrabalho;

public class DiaTrabalhoView {

    private Scanner scanner = new Scanner(System.in);

    public void menuDiaTrabalho(HashMap<String, DiaTrabalho> diasDeTrabalho, DiaTrabalho diaSelecionado) {
        diasDeTrabalho.put(diaSelecionado.getData(), diaSelecionado);

        while (true) {
            System.out.println("\n--------------------------");
            System.out.println("Dia Selecionado: " + diaSelecionado.getData());
            System.out.println("--------------------------");
            System.out.println("=== Menu Dia de Trabalho ===");
            System.out.println("1. Adicionar um Dia de Trabalho");
            System.out.println("2. Remover um Dia de Trabalho");
            System.out.println("3. Atualizar um Dia Selecionado");
            System.out.println("4. Buscar um Dia de Trabalho");
            System.out.println("5. Listar Todos os Dias de Trabalho");
            System.out.println("0. Voltar");
            System.out.print("Opcao: ");

            try {
                int opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1:
                        adicionarDiaTrabalho(diasDeTrabalho);
                        break;
                    case 2:
                        removerDiaTrabalho(diasDeTrabalho, diaSelecionado);
                        break;
                    case 3:
                        atualizarDiaTrabalho(diasDeTrabalho);
                        break;
                    case 4:
                        buscarDiaTrabalho(diasDeTrabalho);
                        break;
                    case 5:
                        listarDiasTrabalho(diasDeTrabalho);
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

    private void adicionarDiaTrabalho(HashMap<String, DiaTrabalho> diasDeTrabalho) {
        System.out.println("\n=== Adicionar Dia de Trabalho ===");
        System.out.println("Digite a data (DD/MM/YY):");
        String data = scanner.nextLine();
        
        DiaTrabalho diaTrabalho = new DiaTrabalho(data);
        diasDeTrabalho.put(data, diaTrabalho);

        System.out.println("Dia de trabalho adicionado com sucesso!");
    }

    private void removerDiaTrabalho(HashMap<String, DiaTrabalho> diasDeTrabalho, DiaTrabalho diaSelecionado) {
        System.out.println("\n=== Remover Dia de Trabalho ===");
        System.out.println("Digite a data do dia de trabalho (DD/MM/YY):");
        String data = scanner.nextLine();
        
        if(data == diaSelecionado.getData()) {
            System.out.println("Nao e possivel remover o dia de trabalho selecionado!");
            return;
        }
        diasDeTrabalho.remove(data);
        
        System.out.println("Dia de trabalho removido com sucesso!");
    }

    private void atualizarDiaTrabalho(HashMap<String, DiaTrabalho> diasDeTrabalho) {
        System.out.println("\n=== Atualizar Dia de Trabalho ===");
        System.out.println("Digite a data (DD/MM/YY):");
        String dataAtual = scanner.nextLine();
        
        DiaTrabalho diaTrabalho = diasDeTrabalho.get(dataAtual);
        if (diaTrabalho != null) {
            System.out.println("Digite a nova data (DD/MM/YYYY):");
            String novaData = scanner.nextLine();
            
            diaTrabalho.setData(novaData);
            diasDeTrabalho.put(novaData, diaTrabalho);
            System.out.println("Dia de trabalho atualizado com sucesso!");
        } else {
            System.out.println("Dia de trabalho nao encontrado!");
        }
    }

    private void buscarDiaTrabalho(HashMap<String, DiaTrabalho> diasDeTrabalho) {
        System.out.println("\n=== Buscar Dia de Trabalho ===");
        System.out.println("Digite a data (DD/MM/YYYY):");
        String data = scanner.nextLine();
        
        DiaTrabalho diaTrabalho = diasDeTrabalho.get(data);
        if (diaTrabalho != null) {
            System.out.println("-------------------");
            System.out.println("Data: " + diaTrabalho.getData());
            System.out.println("Numero de pedidos: " + diaTrabalho.getPedidos().size());
        } else {
            System.out.println("Dia de trabalho nao encontrado!");
        }
    }

    private void listarDiasTrabalho(HashMap<String, DiaTrabalho> diasDeTrabalho) {
        System.out.println("\n=== Lista de Dias de Trabalho ===");
        if (diasDeTrabalho.isEmpty()) {
            System.out.println("Nenhum dia de trabalho cadastrado!");
        } else {
            for (DiaTrabalho dia : diasDeTrabalho.values()) {
                System.out.println("Data: " + dia.getData());
                System.out.println("Numero de pedidos: " + dia.getPedidos().size());
                System.out.println("Total de vendas: " + dia.getTotalVendas());
                System.out.println("--------------------");
            }
        }
    }


    
}
