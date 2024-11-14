package classes;

public class Ficheiro {
	private Reservas reservas;
	private Cancelamentos cancelamentos;
	
	public boolean addReserva(Reserva reserva) {
		return this.reservas.inserir(reserva,
				Integer.parseInt(
					"" + Reserva.transformarEmIdNumerico(reserva.getCheckin())
						+ reserva.getQuarto().getNumero() 
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
					"" + Cancelamento.transformarEmIdNumerico(cancelamento.getCheckin())
						+ cancelamento.getQuarto().getNumero() 
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
