package facade;
import classes.CategoriaDoQuarto;
import classes.Cliente;
import classes.Hotel;
import classes.Quarto;
import classes.Reserva;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HotelFacade {
    private Hotel hotel = new Hotel();
	public int inserirQuarto(int numero, CategoriaDoQuarto categoria) {
		this.hotel.addQuarto(new Quarto(numero, categoria));
		return this.hotel.getQuartos().size() - 1;
	}
	public String getName() {
		return this.hotel.getName();
	}
	public ArrayList<Quarto> getQuartos(){
		return this.hotel.getQuartos();
	}
	public boolean hasQuarto(int num) {
		for(int i = 0; i<this.hotel.getQuartos().size(); i++) {
			if(this.hotel.getQuarto(i).getNumero()==num) {
				return true;
			}
		}
		return false;
	}
	public void removeQuarto(int idQuarto) {
		this.hotel.getQuartos().remove(idQuarto);
	}
	public List<Integer> disponibilidadeQuarto(int idCat, Date periodo) {
		List<Integer> ids = new ArrayList<Integer>();
		for(int i = 0; i<this.hotel.getQuartos().size(); i++) {
			if(this.hotel.getQuartos().get(i).getCategoria() == CategoriaDoQuarto.values()[idCat]) {
				List<Reserva> reservas = this.hotel.getReservasByQuarto(this.hotel.getQuartos().get(i).getNumero());
				if(reservas.size()<=0) {
					ids.add(i);
				}
			}
		}
		return ids;
	}
	public boolean inserirReserva(Cliente cliente, Quarto quarto, Date checkin, Date checkout) {
		return this.hotel.addReserva(new Reserva(quarto, checkin, checkout, cliente), cliente.getCPF());
	}
	public boolean hasCliente(int cpf) {
		return this.hotel.getClientes().has(cpf);
	}
	public void inserirCliente(int cpf, String nome) {
		this.hotel.addCliente(new Cliente(cpf, nome));
	}
	public Cliente getCliente(int cpf) {
		return this.hotel.getClientes().get(cpf);
	}
}
