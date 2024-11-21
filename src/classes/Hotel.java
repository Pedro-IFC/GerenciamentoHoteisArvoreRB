package classes;
import java.util.ArrayList;
import java.util.List;


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
}
