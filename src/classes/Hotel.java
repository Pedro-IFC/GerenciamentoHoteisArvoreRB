package classes;

public class Hotel {
	private String name;
	private Reservas reservas;
	private Cancelamentos cancelamentos;
	private Quarto[] quartos;
	
	
	public Quarto[] getQuartos() {
		return quartos;
	}
	public void setQuartos(Quarto[] quartos) {
		this.quartos = quartos;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Reservas getReservas() {
		return reservas;
	}
	public void setReservas(Reservas reservas) {
		this.reservas = reservas;
	}
	public Cancelamentos getCancelamentos() {
		return cancelamentos;
	}
	public void setCancelamentos(Cancelamentos cancelamentos) {
		this.cancelamentos = cancelamentos;
	}
	
}
