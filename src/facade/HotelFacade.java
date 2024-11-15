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
	public boolean inserirReserva(int idQuarto, int CPF, String nome, Date dataCheckin) {
		Cliente cliente = new Cliente(CPF, nome);
		
		if(this.hotel.addCliente(cliente)) {
			Reserva reserva = new Reserva(this.hotel.getQuarto(idQuarto), dataCheckin);
			if(this.hotel.addReserva(reserva, cliente.getCPF())){
				if(cliente.addReserva(reserva, cliente.getCPF())) {
					return true;
				}
			}
		}
		return false;
	}
	public boolean removerReserva(int idQuarto, int CPF, Date dataCheckin) {
		Cliente cliente = this.hotel.getClientes().get(CPF);
		int idReserva =  Integer.parseInt(
			"" + idQuarto 
				+ Reserva.transformarEmIdNumerico(dataCheckin)
			);
		if(cliente.getReservas().excluir(idReserva)) {
			if(this.hotel.getReservas().excluir(idReserva)){
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
}
