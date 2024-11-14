package classes;

public class Ficheiro {
	private Reservas reservas;
	private Cancelamentos cancelamentos;
	
	public boolean addReserva(Reserva reserva) {
		return this.reservas.inserir(reserva,
				Integer.parseInt(
					"" + reserva.getQuarto().getNumero() 
					+ reserva.transformarEmIdNumerico()
					)
				);
	}
	public Ficheiro() {
		super();
		this.reservas = new Reservas();
		this.cancelamentos = new Cancelamentos();
	}
	public boolean addCancelamento(Cancelamento cancelamento) {
		return this.cancelamentos.inserir(cancelamento,
				Integer.parseInt(
					"" + cancelamento.getQuarto().getNumero() 
					+ cancelamento.transformarEmIdNumerico()
					)
				);
	}
	public Reservas getReservas() {
		return reservas;
	}
	public Cancelamentos getCancelamentos() {
		return cancelamentos;
	}
}
