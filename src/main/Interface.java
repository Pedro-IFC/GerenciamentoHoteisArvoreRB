package main;

public class Interface {
	public static void init() {
		Comander.printOccupancyRate();
		int comando = Comander.index();
		while(true) {
			System.out.println("===========================================================");
			Comander.printOccupancyRate();
			switch(comando) {
				case(-1):
					comando = Comander.index();		
				break;
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
					comando = Comander.gerirCancelamentos();
				break;
				case(4):
					comando = Comander.gerirRelatorios();
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
					comando = Comander.verReservasPorCheckin();
				break;
				case(11):
					comando = Comander.verReservasPorCliente();
				break;
				case(12):
					comando = Comander.verTodasReservas();
				break;
				case(13):
					comando = Comander.cancelarReserva();
				break;
				case(14):
					comando = Comander.verCancelamentosPorCheckin();
				break;
				case(15):
					comando = Comander.verCancelamentosPorCliente();
				break;
				case(16):
					comando = Comander.verTodosCancelamentos();
				break;
				default:
					System.out.println("Erro comando não esperado, tente novamente!");
					comando=0;
			}
		}	
	}
}
