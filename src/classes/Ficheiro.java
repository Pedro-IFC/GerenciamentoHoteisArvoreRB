package classes;

public class Ficheiro {
	private Reservas reservas;
	private Cancelamentos cancelamentos;
	public boolean addReserva(Reserva reserva, int cpf) {
		return this.reservas.inserir(reserva, cpf);
	}
	public Ficheiro() {
		super();
		this.reservas = new Reservas();
		this.cancelamentos = new Cancelamentos();
	}
	public boolean addCancelamento(Cancelamento cancelamento, int cpf) {
		return this.cancelamentos.inserir(cancelamento, cpf);
	}
	public Reservas getReservas() {
		return reservas;
	}
	public Cancelamentos getCancelamentos() {
		return cancelamentos;
	}
}
