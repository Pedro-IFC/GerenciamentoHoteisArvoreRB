package classes;

import java.util.Date;

public class Cancelamento extends Reserva{
	public Cancelamento(Quarto quarto, Date checkin, Date checkout)  {
		super(quarto, checkin, checkout);
	}
	public Cancelamento(Reserva reserva)  {
		super(reserva.getQuarto(), reserva.getCheckin(), reserva.getCheckout());
	}
}
