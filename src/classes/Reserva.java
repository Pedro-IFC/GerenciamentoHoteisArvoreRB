package classes;

import java.util.Date;

public class Reserva {
	private Quarto quarto;
	private Date checkin;
	private Date checkout;
	public boolean isDateBetween(Date periodo, boolean entre) {
	    if (entre) {
	        return !periodo.before(checkin) && !periodo.after(checkout);
	    }
	    return periodo.before(checkin) || periodo.after(checkout);
	}
	public boolean isDateBetween(Date initial, Date finalDate) {
	    return !finalDate.before(checkin) && !initial.after(checkout);
	}

	public Reserva(Quarto quarto, Date checkin, Date checkout) {
		super();
		this.quarto = quarto;
		this.checkin = checkin;
		this.checkout = checkout;
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
