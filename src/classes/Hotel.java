package classes;
import java.util.ArrayList;
import java.util.List;


public class Hotel extends Ficheiro{
	private String name = "Overlook";
	private Clientes clientes;
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
		return this.getReservas().getReservasByQuarto(idQuarto);
	}
}
