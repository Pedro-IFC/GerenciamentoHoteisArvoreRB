package classes;

import java.util.Date;

public class Reserva {
	private Quarto quarto;
	private Date checkin;
	private Date checkout;
    public boolean isDateBetween(Date periodo, boolean entre) {
    	if(entre) {
            return (periodo.after(checkin) || periodo.equals(checkin)) 
                && (periodo.before(checkout) || periodo.equals(checkout));
    	}
        return !((periodo.after(checkin) || periodo.equals(checkin)) 
                && (periodo.before(checkout) || periodo.equals(checkout)));
    }
    public boolean isDateBetween(Date initial, Date finalDate) {
        return (initial.after(checkin) || initial.equals(checkin)) 
            && (finalDate.before(checkout) || finalDate.equals(checkout));
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
