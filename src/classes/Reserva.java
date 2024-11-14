package classes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Reserva {
	private Quarto quarto;
	private Date checkin;
	private Date checkout;
	public Reserva(Quarto quarto, Date checkin) {
		super();
		this.quarto = quarto;
		this.checkin = checkin;
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
    public int transformarEmIdNumerico() {
        SimpleDateFormat formatoId = new SimpleDateFormat("yyyyMMdd");
        return Integer.parseInt(formatoId.format(checkin));
    }
	@Override
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
