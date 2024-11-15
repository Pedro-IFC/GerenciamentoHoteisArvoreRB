package main;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import classes.CategoriaDoQuarto;
import facade.HotelFacade;

public class Comander {
	private static HotelFacade hotelF = new HotelFacade();
	private static Scanner scanner = new Scanner(System.in);
	public static int  index() {
		System.out.println(
			"Bem vindo ao sistema!\n"
			+ "Você gostaria de:\n"
			+ "[1] Gerenciar Quartos \n"
			+ "[2] Gerenciar Reservas \n"
			+ "[3] Gerenciar Cancelamentos \n"
			+ "[4] Gerenciar Clientes \n"
			+ "..."
		);
		return scanner.nextInt();
	}
	public static int gerirQuartos() {
		System.out.println(
				"Você selecionou gerir Quartos!\n"
				+ "Você gostaria de:\n"
				+ "[1] Cadastrar Quartos \n"
				+ "[2] Deletar Quartos \n"
				+ "[3] Consultar Quartos:\n"
				+ "[4] Consulta de Disponibilidade de Quartos:\n"
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
		System.out.println(
			"ID | Nº | Categoria"
		);	
		if(hotelF.getQuartos().size()==0) {
			System.out.println("O Hotel " + hotelF.getName() + "está vazio! ");
		}else {
			int i;
			for(i = 0; i<hotelF.getQuartos().size(); i++) {
				System.out.println("" 
					+ "[" + i + "] | "
					+ (hotelF.getQuartos().get(i).getNumero()<10? "0" : "") + hotelF.getQuartos().get(i).getNumero() 
					+ hotelF.getQuartos().get(i).getCategoria());
			}
			int idQuarto=0;
			do {
				if(idQuarto<0 || idQuarto>i) {
					System.out.println("Não foi possível identificar esse quarto, tente novamente!");
				}
				System.out.println(
					"Qual o ID do quarto?: "
				);
				idQuarto = scanner.nextInt();
			}while(idQuarto<0 || idQuarto>i);
			hotelF.removeQuarto(i);
			System.out.println("Quarto excluído com sucesso");
		}
		return 1;
	}
	public static int consultarDisponibilidadeQuartos() {
		System.out.println(
			"Você selecionou verificar disponibilidade do quarto!\n"
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
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        System.out.print("Digite uma data (dd/MM/yyyy): ");
        scanner.nextLine();
        String dataEntrada = scanner.nextLine(); 
        Date dataFormatada = null;
        try {
            dataFormatada = formatoData.parse(dataEntrada);
            if(hotelF.disponibilidadeQuarto(catQuarto, dataFormatada).size()<=0) {
            	System.out.println("Quarto já reservado neste periodo");
            }else {
            	System.out.println("Quarto não está reservado neste periodo");
            }
        } catch (ParseException e) {
            System.out.println("Erro: data inválida! Certifique-se de usar o formato dd/MM/yyyy.");
        }
		return 1;
	}
}
