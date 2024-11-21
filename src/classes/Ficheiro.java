package classes;

import java.text.SimpleDateFormat;

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
	public boolean cancelarReserva(int id) {
		Reserva reserva = this.getReservas().listar().get(id);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	    String dataFormatada = sdf.format(reserva.getCheckin());
	    String chaveString = dataFormatada + reserva.getQuarto().getNumero();
        this.getReservas().excluir(Integer.parseInt(chaveString));
		return this.getCancelamentos().inserir(new Cancelamento(reserva), Integer.parseInt(chaveString));
	}
}
