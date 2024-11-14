package classes;
import java.util.ArrayList;


public class Hotel extends Ficheiro{
	private String name;
	private Clientes clientes;
    private ArrayList<Quarto> quartos = new ArrayList<>();
	public Hotel(String name) {
		super();
		this.name = name;
	}
	
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
}
