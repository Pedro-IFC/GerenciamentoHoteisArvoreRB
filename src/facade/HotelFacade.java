package facade;
import classes.Cancelamento;
import classes.CategoriaDoQuarto;
import classes.Cliente;
import classes.Hotel;
import classes.Quarto;
import classes.Reserva;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
	}public boolean verificarDisponibilidadeQuarto(int idParam, Date checkin, Date checkout) {
        List<Reserva> reservas = this.hotel.getReservasByQuarto(idParam);
        for (Reserva reserva : reservas) {
            if (checkin.before(reserva.getCheckout()) && checkout.after(reserva.getCheckin())) {
                return false;
            }
        }
        return true;
	}
	public boolean inserirReserva(Cliente cliente, Quarto quarto, Date checkin, Date checkout) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	    String dataFormatada = sdf.format(checkin);
	    String chaveString = dataFormatada + quarto.getNumero();
	    int chaveInteira;
	    try {
	        chaveInteira = Integer.parseInt(chaveString);
	    } catch (NumberFormatException e) {
	        System.out.println("Erro ao converter para inteiro: " + e.getMessage());
	        return false;
	    }
	    return this.getCliente(cliente.getCPF()).addReserva(new Reserva(quarto, checkin, checkout), chaveInteira);
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
	public List<Cliente> getClientes() {
		return this.hotel.getClientes().listar();
	}
	public Double getTaxaOCupacao(Date initial) {
        int totalQuartos = hotel.getQuartos().size();
        if (totalQuartos == 0) {
            return 0.0; 
        }
        int quartosOcupados = 0;
        for (Quarto quarto : hotel.getQuartos()) {
            List<Reserva> reservas = hotel.getReservasByQuarto(quarto.getNumero());
            for (Reserva reserva : reservas) {
                if (reserva.isDateBetween(initial, true)) {
                    quartosOcupados++;
                }
            }
        }
        return (double) quartosOcupados / totalQuartos * 100;
	}
	public Double getTaxaOCupacao(Date initial, Date finalDate) {
		int totalQuartos = hotel.getQuartos().size();
	    if (totalQuartos == 0) {
	        return 0.0;
	    }

	    long totalDiasPeriodo = (finalDate.getTime() - initial.getTime()) / (1000 * 60 * 60 * 24) + 1; // Dias no período
	    if (totalDiasPeriodo <= 0) {
	        return 0.0;
	    }

	    double somaOcupacaoPorDias = 0.0;

	    for (Quarto quarto : hotel.getQuartos()) {
	        List<Reserva> reservas = hotel.getReservasByQuarto(quarto.getNumero());
	        long diasOcupados = 0;

	        for (Reserva reserva : reservas) {
	            if (reserva.isDateBetween(initial, finalDate)) {
	                Date inicioReserva = reserva.getCheckin().after(initial) ? reserva.getCheckin() : initial;
	                Date fimReserva = reserva.getCheckout().before(finalDate) ? reserva.getCheckout() : finalDate;

	                diasOcupados += (fimReserva.getTime() - inicioReserva.getTime()) / (1000 * 60 * 60 * 24) + 1;
	            }
	        }

	        somaOcupacaoPorDias += (double) diasOcupados / totalDiasPeriodo;
	    }

	    return (somaOcupacaoPorDias / totalQuartos) * 100;
	}
	public Double getTaxaCancelamento(Date initial, Date finalDate) {
	    int totalQuartos = hotel.getQuartos().size();
	    if (totalQuartos == 0) {
	        return 0.0;
	    }

	    long totalDiasPeriodo = (finalDate.getTime() - initial.getTime()) / (1000 * 60 * 60 * 24) + 1; // Dias no período
	    if (totalDiasPeriodo <= 0) {
	        return 0.0; 
	    }

	    double somaOcupacaoPorDias = 0.0;

	    for (Quarto quarto : hotel.getQuartos()) {
	        List<Cancelamento> reservas = hotel.getCancelamentosByQuarto(quarto.getNumero());
	        long diasOcupados = 0;

	        for (Cancelamento reserva : reservas) {
	            if (reserva.isDateBetween(initial, finalDate)) {
	                Date inicioReserva = reserva.getCheckin().after(initial) ? reserva.getCheckin() : initial;
	                Date fimReserva = reserva.getCheckout().before(finalDate) ? reserva.getCheckout() : finalDate;

	                diasOcupados += (fimReserva.getTime() - inicioReserva.getTime()) / (1000 * 60 * 60 * 24) + 1;
	            }
	        }

	        somaOcupacaoPorDias += (double) diasOcupados / totalDiasPeriodo;
	    }

	    return (somaOcupacaoPorDias / totalQuartos) * 100;
	}
	public Map<Integer, Integer> getQuartosMaisReservados() {
		return hotel.getQuartosMaisReservados();
	}
	public Map<Integer, Integer> getQuartosMenosReservados() {
		return hotel.getQuartosMenosReservados();
	}
}
