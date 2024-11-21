package classes;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;


public class Hotel{
	private String name = "Overlook";
	private Clientes clientes = new Clientes();
    private ArrayList<Quarto> quartos = new ArrayList<>();
	public ArrayList<Quarto> getQuartos() {
		return quartos;
	}
	public String getName() {
		return name;
	}
	public Clientes getClientes() {
		return clientes;
	}
	public boolean addCliente(Cliente cliente) {
		return this.clientes.inserir(cliente, cliente.getCPF());
	}
	public void addQuarto(Quarto quarto) {
		this.quartos.add(quarto);
	}
	public Quarto getQuarto(int id) {
		return this.quartos.get(id);
	}
	public List<Reserva> getReservasByQuarto(int idQuarto) {
		List<Reserva> reservas = new ArrayList<Reserva>();
		for (int i = 0; i < this.getClientes().listar().size(); i++) {
			if(this.getClientes().listar().get(i)!=null) {
			    reservas.addAll(this.getClientes().listar().get(i).getReservasByQuarto(idQuarto));
			}
		}
		return reservas;
	}
	public List<Cancelamento> getCancelamentosByQuarto(int idQuarto) {
		List<Cancelamento> cancelamentos = new ArrayList<Cancelamento>();
		for (int i = 0; i < this.getClientes().listar().size(); i++) {
			if(this.getClientes().listar().get(i)!=null) {
				cancelamentos.addAll(this.getClientes().listar().get(i).getCancelamentosByQuarto(idQuarto));
			}
		}
		return cancelamentos;
	}
	public Map<Integer, Integer> getQuartosMaisReservados() {
	    Map<Integer, Integer> reservasPorQuarto = new HashMap<>();
	    for (Quarto quarto : this.getQuartos()) {
	        int numeroQuarto = quarto.getNumero();
	        List<Reserva> reservas = this.getReservasByQuarto(numeroQuarto);
	        reservasPorQuarto.put(numeroQuarto, reservas.size());
	    }
	    return reservasPorQuarto;
	}
	public Map<Integer, Integer> getQuartosMenosReservados() {
	    Map<Integer, Integer> reservasPorQuarto = getQuartosMaisReservados();
	    int menorNumeroReservas = Integer.MAX_VALUE;
    	for (int count : reservasPorQuarto.values()) {
	        if (count < menorNumeroReservas) {
	            menorNumeroReservas = count;
	        }
	    }
	    Map<Integer, Integer> quartosMenosReservados = new HashMap<>();
	    for (Map.Entry<Integer, Integer> entry : reservasPorQuarto.entrySet()) {
	        if (entry.getValue() == menorNumeroReservas) {
	            quartosMenosReservados.put(entry.getKey(), entry.getValue());
	        }
	    }
	    return quartosMenosReservados;
	}
}
