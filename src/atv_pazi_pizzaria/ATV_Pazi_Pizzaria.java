package atv_pazi_pizzaria;

import atv_pazi_pizzaria.view.UI;
import java.util.Scanner;

//Alunos: Fernando Antonio e Michely Serras
public class ATV_Pazi_Pizzaria {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        
        UI ui = new UI(scanner);
        ui.start();
        
        scanner.close();
    }
    
}
