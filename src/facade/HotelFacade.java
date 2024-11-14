package facade;
import classes.CategoriaDoQuarto;
import classes.Cliente;
import classes.Hotel;
import classes.Quarto;
import classes.Reserva;

import java.util.ArrayList;
import java.util.Date;

public class HotelFacade {
    private ArrayList<Hotel> hoteis = new ArrayList<>();
	public int inserirHotel(String name) {
		this.hoteis.add(new Hotel(name));
		return this.hoteis.size()-1;
	}
	public ArrayList<Hotel> getHoteis() {
		return hoteis;
	}
	public Hotel getHotel(int idHotel) {
		return hoteis.get(idHotel);
	}
	public int inserirQuarto(int idHotel, int numero, CategoriaDoQuarto categoria) {
		this.hoteis.get(idHotel).addQuarto(new Quarto(numero, categoria));
		return this.hoteis.get(idHotel).getQuartos().size() - 1;
	}
	public boolean inserirReserva(int idHotel, int idQuarto, int CPF, String nome, Date dataCheckin) {
		Cliente cliente = new Cliente(CPF, nome);
		
		if(this.getHotel(idHotel).addCliente(cliente)) {
			Reserva reserva = new Reserva(this.getHotel(idHotel).getQuarto(idQuarto), dataCheckin);
			if(this.getHotel(idHotel).addReserva(reserva)){
				if(cliente.addReserva(reserva)) {
					return true;
				}
			}
		}
		return false;
	}
	public boolean removerReserva(int idHotel, int idQuarto, int CPF, Date dataCheckin) {
		Cliente cliente = this.getHotel(idHotel).getClientes().get(CPF);
		int idReserva =  Integer.parseInt(
			"" + idQuarto 
				+ Reserva.transformarEmIdNumerico(dataCheckin)
			);
		if(cliente.getReservas().excluir(idReserva)) {
			if(this.getHotel(idHotel).getReservas().excluir(idReserva)){
				return true;
			}
		}
		return false;
	}
}
