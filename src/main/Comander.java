package main;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import classes.CategoriaDoQuarto;
import classes.Cliente;
import facade.HotelFacade;

public class Comander {
	private static HotelFacade hotelF = new HotelFacade();
	private static Scanner scanner = new Scanner(System.in);
	public static void printOccupancyRate() {
		System.out.println("Taxa de ocupação: " + hotelF.getTaxaOCupacao(new Date(), new Date()) + "%");
	}
	public static int  index() {
		System.out.println(
			"Bem vindo ao sistema!\n"
			+ "Você gostaria de:\n"
			+ "[1] Gerenciar Quartos \n"
			+ "[2] Gerenciar Reservas \n"
			+ "[3] Gerenciar Cancelamentos \n"
			+ "[4] Ver relatórios \n"
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
				+ "[0] Voltar \n"
				+ "..."
			);
		int input = scanner.nextInt();
		return input<=0? 0 : input+4;
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
					+ (hotelF.getQuartos().get(i).getNumero()<10? "0" : "") + hotelF.getQuartos().get(i).getNumero() + " | "
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
	public static int gerirReservas() {
		System.out.println(
				"Você selecionou gerir Reservas!\n"
				+ "Você gostaria de:\n"
				+ "[1] Cadastrar reserva \n"
				+ "[2] Listagem de Reservas por Check-in \n"
				+ "[3] Listagem de Reservas por Cliente:\n"
				+ "[4] Ver todas as reservas:\n"
				+ "[5] Cancelar uma reserva:\n"
				+ "[0] Voltar \n"
				+ "..."
			);
		int input = scanner.nextInt();
		return input<=0? 0 : input+8;
	}
	public static int cadastrarReserva() {
		int cpf =0;
		do {
			if(cpf<0) {
				System.out.println("Não foi possível identificar esse CPF, tente novamente!");
			}
			System.out.println(
				"Qual o CPF do cliente?: "
			);
			cpf = scanner.nextInt();
		}while(cpf<0);
		if(!hotelF.hasCliente(cpf)) {
			System.out.println("Não foi possível identificar um cliente com este CPF, criado um novo registro de cliente!");
	        System.out.print("Digite um nome: ");
	        scanner.nextLine();
	        String nome = scanner.nextLine(); 
			hotelF.inserirCliente(cpf, nome);
		}
		Cliente c = hotelF.getCliente(cpf);
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
			SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
	        System.out.print("Digite a data de check-in (dd/MM/yyyy): ");
	        scanner.nextLine();
	        String dataEntrada = scanner.nextLine(); 
	        try {
	        	Date dataFormatada1 = formatoData.parse(dataEntrada);
	            System.out.print("Digite a data de check-out (dd/MM/yyyy): ");
	            String dataEntrada2 = scanner.nextLine(); 
	        	Date dataFormatada2 = formatoData.parse(dataEntrada2);
	        	if(hotelF.verificarDisponibilidadeQuarto(hotelF.getQuartos().get(idQuarto).getNumero(), dataFormatada1, dataFormatada2)) {
		    		if(hotelF.inserirReserva(c, hotelF.getQuartos().get(idQuarto), dataFormatada1, dataFormatada2)) {
		    			System.out.println("Reserva cadastrada com sucesso!");
		    		}else {
		    			System.out.println("Reserva não pode ser efetuada! Procure por outro periodo");
		    		}
	        	}else {
	    			System.out.println("Reserva não pode ser efetuada! Procure por outro periodo");
	    		}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return 2;
	}
	public static int verTodasReservas() {
		System.out.println(
			"Cliente | Nº Quarto | Categoria | Check-in | Check-ou"
		);	
		for(int i = 0; i<hotelF.getClientes().size();i++) {
			for(int j = 0; j<hotelF.getClientes().get(i).getReservas().size();j++) {
				System.out.println(
					hotelF.getClientes().get(i).getNome() + " | " +
					hotelF.getClientes().get(i).getReservas().listar().get(j).getQuarto().getNumero() + " | " +
					hotelF.getClientes().get(i).getReservas().listar().get(j).getQuarto().getCategoria() + " | " +
					hotelF.getClientes().get(i).getReservas().listar().get(j).getCheckin() + " | " +
					hotelF.getClientes().get(i).getReservas().listar().get(j).getCheckout() + " | " 
				);
			}
		}
		return 2;
	}
	public static int verReservasPorCliente() {
		int cpf =0;
		do {
			if(cpf<0) {
				System.out.println("Não foi possível identificar esse CPF, tente novamente!");
			}
			System.out.println(
				"Qual o CPF do cliente?: "
			);
			cpf = scanner.nextInt();
		}while(cpf<0);
		if(hotelF.getCliente(cpf)==null) {
			System.out.println("Nenhum cliente cadastrado com este CPF");
			return 2;
		}
		System.out.println(
			"Cliente | Nº Quarto | Categoria | Check-in | Check-ou"
		);	
		for(int j = 0; j<hotelF.getCliente(cpf).getReservas().size();j++) {
			System.out.println(
				hotelF.getCliente(cpf).getNome() + " | " +
				hotelF.getCliente(cpf).getReservas().listar().get(j).getQuarto().getNumero() + " | " +
				hotelF.getCliente(cpf).getReservas().listar().get(j).getQuarto().getCategoria() + " | " +
				hotelF.getCliente(cpf).getReservas().listar().get(j).getCheckin() + " | " +
				hotelF.getCliente(cpf).getReservas().listar().get(j).getCheckout() + " | " 
			);
		}
		return 2;
	}
	public static int verReservasPorCheckin() {
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        System.out.print("Digite a data de check-in (dd/MM/yyyy): ");
        scanner.nextLine();
        String dataEntrada = scanner.nextLine(); 
        try {
        	Date dataFormatada1 = formatoData.parse(dataEntrada);
    		System.out.println(
				"Cliente | Nº Quarto | Categoria | Check-in | Check-ou"
			);	
    		for(int i = 0; i<hotelF.getClientes().size();i++) {
    			for(int j = 0; j<hotelF.getClientes().get(i).getReservas().size();j++) {
    				if(hotelF.getClientes().get(i).getReservas().listar().get(j).getCheckin().equals(dataFormatada1)) {
    					System.out.println(
	    					hotelF.getClientes().get(i).getNome() + " | " +
	    					hotelF.getClientes().get(i).getReservas().listar().get(j).getQuarto().getNumero() + " | " +
	    					hotelF.getClientes().get(i).getReservas().listar().get(j).getQuarto().getCategoria() + " | " +
	    					hotelF.getClientes().get(i).getReservas().listar().get(j).getCheckin() + " | " +
	    					hotelF.getClientes().get(i).getReservas().listar().get(j).getCheckout() + " | " 
	    				);
    				}
    			}
    		}
        } catch (ParseException e) {
			e.printStackTrace();
		}
		return 2;
	}
	public static int cancelarReserva() {
		int cpf =0;
		do {
			if(cpf<0) {
				System.out.println("Não foi possível identificar esse CPF, tente novamente!");
			}
			System.out.println(
				"Qual o CPF do cliente?: "
			);
			cpf = scanner.nextInt();
		}while(cpf<0);
		if(hotelF.getCliente(cpf)==null) {
			System.out.println("Nenhum cliente cadastrado com este CPF");
			return 2;
		}
		System.out.println(
			"ID | Cliente | Nº Quarto | Categoria | Check-in | Check-ou"
		);	
		int j = 0;
		for(; j<hotelF.getCliente(cpf).getReservas().size();j++) {
			System.out.println(
				j + " | " +
				hotelF.getCliente(cpf).getNome() + " | " +
				hotelF.getCliente(cpf).getReservas().listar().get(j).getQuarto().getNumero() + " | " +
				hotelF.getCliente(cpf).getReservas().listar().get(j).getQuarto().getCategoria() + " | " +
				hotelF.getCliente(cpf).getReservas().listar().get(j).getCheckin() + " | " +
				hotelF.getCliente(cpf).getReservas().listar().get(j).getCheckout() + " | " 
			);
		}
		int id=0;
		do {
			if(id<0 || id>j) {
				System.out.println("Não foi possível identificar essa reserva, tente novamente!");
			}
			System.out.println(
				"Qual o ID do quarto?: "
			);
			id = scanner.nextInt();
		}while(id<0 || id>j);
		hotelF.getCliente(cpf).cancelarReserva(id);
		return 2;
	}
	public static int gerirCancelamentos() {
		System.out.println(
				"Você selecionou gerir Reservas!\n"
				+ "Você gostaria de:\n"
				+ "[1] Listagem de Cancelamentos por Check-in \n"
				+ "[2] Listagem de Cancelamentos por Cliente:\n"
				+ "[3] Ver todas as Cancelamentos:\n"
				+ "[0] Voltar \n"
				+ "..."
			);
		int input = scanner.nextInt();
		return input<=0? 0 : input+12;
	}
	public static int verTodosCancelamentos() {
		System.out.println(
			"Cliente | Nº Quarto | Categoria | Check-in | Check-ou"
		);	
		for(int i = 0; i<hotelF.getClientes().size();i++) {
			for(int j = 0; j<hotelF.getClientes().get(i).getCancelamentos().size();j++) {
				System.out.println(
					hotelF.getClientes().get(i).getNome() + " | " +
					hotelF.getClientes().get(i).getReservas().listar().get(j).getQuarto().getNumero() + " | " +
					hotelF.getClientes().get(i).getReservas().listar().get(j).getQuarto().getCategoria() + " | " +
					hotelF.getClientes().get(i).getReservas().listar().get(j).getCheckin() + " | " +
					hotelF.getClientes().get(i).getReservas().listar().get(j).getCheckout() + " | " 
				);
			}
		}
		return 3;
	}
	public static int verCancelamentosPorCliente() {
		int cpf =0;
		do {
			if(cpf<0) {
				System.out.println("Não foi possível identificar esse CPF, tente novamente!");
			}
			System.out.println(
				"Qual o CPF do cliente?: "
			);
			cpf = scanner.nextInt();
		}while(cpf<0);
		if(hotelF.getCliente(cpf)==null) {
			System.out.println("Nenhum cliente cadastrado com este CPF");
			return 2;
		}
		System.out.println(
			"Cliente | Nº Quarto | Categoria | Check-in | Check-ou"
		);	
		for(int j = 0; j<hotelF.getCliente(cpf).getCancelamentos().size();j++) {
			System.out.println(
				hotelF.getCliente(cpf).getNome() + " | " +
				hotelF.getCliente(cpf).getCancelamentos().listar().get(j).getQuarto().getNumero() + " | " +
				hotelF.getCliente(cpf).getCancelamentos().listar().get(j).getQuarto().getCategoria() + " | " +
				hotelF.getCliente(cpf).getCancelamentos().listar().get(j).getCheckin() + " | " +
				hotelF.getCliente(cpf).getCancelamentos().listar().get(j).getCheckout() + " | " 
			);
		}
		return 3;
	}
	public static int verCancelamentosPorCheckin() {
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        System.out.print("Digite a data de check-in (dd/MM/yyyy): ");
        scanner.nextLine();
        String dataEntrada = scanner.nextLine(); 
        try {
        	Date dataFormatada1 = formatoData.parse(dataEntrada);
    		System.out.println(
				"Cliente | Nº Quarto | Categoria | Check-in | Check-ou"
			);	
    		for(int i = 0; i<hotelF.getClientes().size();i++) {
    			for(int j = 0; j<hotelF.getClientes().get(i).getCancelamentos().size();j++) {
    				if(hotelF.getClientes().get(i).getCancelamentos().listar().get(j).getCheckin().equals(dataFormatada1)) {
    					System.out.println(
	    					hotelF.getClientes().get(i).getNome() + " | " +
	    					hotelF.getClientes().get(i).getCancelamentos().listar().get(j).getQuarto().getNumero() + " | " +
	    					hotelF.getClientes().get(i).getCancelamentos().listar().get(j).getQuarto().getCategoria() + " | " +
	    					hotelF.getClientes().get(i).getCancelamentos().listar().get(j).getCheckin() + " | " +
	    					hotelF.getClientes().get(i).getCancelamentos().listar().get(j).getCheckout() + " | " 
	    				);
    				}
    			}
    		}
        } catch (ParseException e) {
			e.printStackTrace();
		}
		return 3;
	}
	public static int gerirRelatorios() {
		System.out.println(
				"Você selecionou gerir Relatórios!\n"
				+ "Você gostaria de:\n"
				+ "[1] Taxa de ocupação por período \n"
				+ "[2] Quartos mais reservados:\n"
				+ "[3] Quartos menos reservados:\n"
				+ "[4] Taxa de cancelamento por período \n"
				+ "[0] Voltar \n"
				+ "..."
			);
		int input = scanner.nextInt();
		return input<=0? 0 : input+15;
	}
	public static int taxaOcupacaoPorPeriodo() {
		System.out.println("Você escolheu ocupação por período");
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        System.out.print("Digite a data inicial (dd/MM/yyyy): ");
        scanner.nextLine();
        String dataEntrada = scanner.nextLine(); 
        try {
        	Date dataFormatada1 = formatoData.parse(dataEntrada);
            System.out.print("Digite a data final (dd/MM/yyyy): ");
            String dataEntrada2 = scanner.nextLine(); 
        	Date dataFormatada2 = formatoData.parse(dataEntrada2);
        	System.out.println("A taxa de ocupação nesse período foi de: " + hotelF.getTaxaOCupacao(dataFormatada1, dataFormatada2) + "%");
        } catch (ParseException e) {
            System.out.println("Erro: data inválida! Certifique-se de usar o formato dd/MM/yyyy.");
        }
		return 4;
	}
	public static int quartosMaisReservados() {
		return 4;
	}
	public static int quartosMenosReservados() {
		return 4;
	}
	public static int taxaCancelamentoPorPeriodo() {
		System.out.println("Você escolheu ocupação por período");
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        System.out.print("Digite a data inicial (dd/MM/yyyy): ");
        scanner.nextLine();
        String dataEntrada = scanner.nextLine(); 
        try {
        	Date dataFormatada1 = formatoData.parse(dataEntrada);
            System.out.print("Digite a data final (dd/MM/yyyy): ");
            String dataEntrada2 = scanner.nextLine(); 
        	Date dataFormatada2 = formatoData.parse(dataEntrada2);
        	System.out.println("A taxa de cancelamento nesse período foi de: " + hotelF.getTaxaCancelamento(dataFormatada1, dataFormatada2) + "%");
        } catch (ParseException e) {
            System.out.println("Erro: data inválida! Certifique-se de usar o formato dd/MM/yyyy.");
        }
		return 4;
	}
}
