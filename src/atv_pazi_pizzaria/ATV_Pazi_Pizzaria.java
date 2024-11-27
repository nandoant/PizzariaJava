package atv_pazi_pizzaria;

import atv_pazi_pizzaria.dao.ClienteDAO;
import atv_pazi_pizzaria.dao.ClienteDAOImpl;
import atv_pazi_pizzaria.dao.DiaTrabalhoDAO;
import atv_pazi_pizzaria.dao.DiaTrabalhoDAOImpl;
import atv_pazi_pizzaria.dao.PedidoDAO;
import atv_pazi_pizzaria.dao.PedidoDAOImpl;
import atv_pazi_pizzaria.dao.PizzaDAO;
import atv_pazi_pizzaria.dao.PizzaDAOImpl;
import atv_pazi_pizzaria.service.ClienteService;
import atv_pazi_pizzaria.service.PedidoService;
import atv_pazi_pizzaria.service.PizzaService;
import atv_pazi_pizzaria.service.DiaTrabalhoService;
import java.util.*;

//Alunos: Fernando Antonio e Michely Serras
public class ATV_Pazi_Pizzaria {

    public static void main(String[] args) {
        ClienteDAO clienteDAO = new ClienteDAOImpl();
        PedidoDAO pedidoDAO = new PedidoDAOImpl();
        PizzaDAO pizzaDAO = new PizzaDAOImpl();
        DiaTrabalhoDAO diaTrabalhoDAO = new DiaTrabalhoDAOImpl();

        ClienteService clienteService = new ClienteService(clienteDAO);
        PedidoService pedidoService = new PedidoService(pedidoDAO);
        PizzaService pizzaService = new PizzaService(pizzaDAO);
        DiaTrabalhoService diaTrabalhoService = new DiaTrabalhoService(diaTrabalhoDAO);

        Scanner scanner = new Scanner(System.in);
        
        UI ui = new UI(scanner, pedidoService, pizzaService, diaTrabalhoService, clienteService);
        ui.start();
        
        scanner.close();
    }
    
}
