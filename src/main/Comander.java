package main;
import java.util.Scanner;

import classes.CategoriaDoQuarto;
import classes.Hotel;
import facade.HotelFacade;

public class Comander {
	private static HotelFacade hotelF = new HotelFacade();
	private static Scanner scanner = new Scanner(System.in);
	public static int  index() {
		System.out.println(
			"Bem vindo ao sistema!\n"
			+ "Você gostaria de:\n"
			+ "[1]Gerenciar Quartos \n"
			+ "[2]Gerenciar Reservas \n"
			+ "[3]Gerenciar Cancelamentos \n"
			+ "[4]Gerenciar Clientes \n"
			+ "..."
		);
		return scanner.nextInt();
	}
	public static int gerirQuartos() {
		System.out.println(
				"Você selecionou gerir Quartos!\n"
				+ "Você gostaria de:\n"
				+ "[1]Cadastrar Quartos \n"
				+ "[2]Deletar Quartos \n"
				+ "[3]Consultar Quartos:\n"
				+ "[4]Consulta de Disponibilidade de Quartos:\n"
				+ "..."
			);
		return scanner.nextInt()+4;
	}
	public static int cadastrarQuarto() {
		int nQuarto = 0;
		do {
			System.out.println(
				"Você selecionou cadastrar quarto!\n"
				+ "Qual o número do quarto?: "
			);
			nQuarto = scanner.nextInt();
		}while(hotelF.hasQuarto(nQuarto));
		System.out.println(
			"Você selecionou cadastrar quarto!\n"
			+ "Confira a lista disponível:"
		);
		int catQuarto, i=0;
		do {
			if(i!=0) {
				System.out.println("Não foi possível identificar essa categoria, tente novamente!");
			}
			i=0;
			for(CategoriaDoQuarto cat : CategoriaDoQuarto.values()) {
				System.out.println("[" + i + "] " + cat);
				i++;
			}
			System.out.println(
				"Qual a categoria do quarto?: "
			);
			catQuarto = scanner.nextInt();
		}while(catQuarto<0||catQuarto>i);
		hotelF.inserirQuarto(nQuarto, CategoriaDoQuarto.values()[catQuarto]);
		return 1;
	}
	public static int consultarQuartos() {
		System.out.println(
				"Você selecionou consultar quartos!\n"
			);
		System.out.println(
			"Nº | Categoria"
		);	
		if(hotelF.getQuartos().size()==0) {
			System.out.println("O Hotel " + hotelF.getName() + "está vazio! ");
		}else {
			for(int i = 0; i<hotelF.getQuartos().size(); i++) {
				System.out.println("" 
					+ (hotelF.getQuartos().get(i).getNumero()<10? "0" : "") + hotelF.getQuartos().get(i).getNumero() 
					+ " | "
					+ hotelF.getQuartos().get(i).getCategoria());
			}
		}
		return 1;
	}
	public static int deletarQuarto() {
		System.out.println(
			"Você selecionou deletar quarto!\n"
		);
		return 1;
	}
}
