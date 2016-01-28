package main;

import java.util.Scanner;
import utils.Util;

public class Main {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		int cond = 0;
		
		while (cond != 3) {
						
			System.out.println("1 - Jogar");
			System.out.println("2 - Buscar jogo");
			System.out.println("3 - Sair");

			cond = scanner.nextInt();
			
			switch (cond) {
			case 1: Util.jogar();
			break;
			case 2: Util.buscarJogo();				
			break;
			default:
			break;
			}
			
		}

	}

}
