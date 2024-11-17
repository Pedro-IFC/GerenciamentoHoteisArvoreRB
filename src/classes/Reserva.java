package classes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Reserva {
	private Quarto quarto;
	private Date checkin;
	private Date checkout;
	private Cliente cliente;
    public boolean isDateBetween(Date periodo, boolean entre) {
    	if(entre) {
            return (periodo.after(checkin) || periodo.equals(checkin)) 
                && (periodo.before(checkout) || periodo.equals(checkout));
    	}
        return !((periodo.after(checkin) || periodo.equals(checkin)) 
                && (periodo.before(checkout) || periodo.equals(checkout)));
    }
    
	public Reserva(Quarto quarto, Date checkin, Date checkout, Cliente cliente) {
		super();
		this.quarto = quarto;
		this.checkin = checkin;
		this.checkout = checkout;
		this.cliente = cliente;
	}

	public Quarto getQuarto() {
		return quarto;
	}
	public Date getCheckin() {
		return checkin;
	}
	public Date getCheckout() {
		return checkout;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Reserva [quarto=");
		builder.append(quarto);
		builder.append(", checkin=");
		builder.append(checkin);
		builder.append(", checkout=");
		builder.append(checkout);
		builder.append("]");
		return builder.toString();
	}
}
