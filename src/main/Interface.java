package main;

public class Interface {
	public static void init() {
		int comando = Comander.index();
		while(true) {
			System.out.println("===========================================================");
			switch(comando) {
				case(0):
					comando = Comander.index();		
				break;
				case(1):
					comando = Comander.gerirQuartos();
				break;
				case(2):
					comando = Comander.gerirReservas();
				break;
				case(3):
					//gerirCancelamentos
				break;
				case(4):
					//gerirClientes
				break;
				case(5):
					comando = Comander.cadastrarQuarto();
				break;
				case(6):
					comando = Comander.deletarQuarto();
				break;
				case(7):
					comando = Comander.consultarQuartos();
				break;
				case(8):
					comando = Comander.consultarDisponibilidadeQuartos();
				break;
				case(9):
					comando = Comander.cadastrarReserva();
				break;
				case(10):
					//Ler reserva por checkin
				break;
				case(11):
					//Ler reserva por cliente
				break;
				case(12):
					//Cancelar reserva
				break;
				default:
					System.out.println("Erro comando não esperado, tente novamente!");
					comando=0;
			}
		}	
	}
}
