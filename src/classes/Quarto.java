package classes;

import java.util.ArrayList;
import java.util.Date;

public class Quarto {
	private int numero;
	private CategoriaDoQuarto categoria;
    private ArrayList<Date> agendamentos = new ArrayList<>();
	
	public Quarto(int numero, CategoriaDoQuarto categoria) {
		super();
		this.numero = numero;
		this.categoria = categoria;
	}
	public int getNumero() {
		return numero;
	}
	public CategoriaDoQuarto getCategoria() {
		return categoria;
	}
	public ArrayList<Date> getAgendamentos() {
		return agendamentos;
	}
	public boolean addData(Date checkin) {
		for(int i = 0; i<this.agendamentos.size(); i++) {
			if(agendamentos.get(i).equals(checkin)) {
				return false;
			}
		}
		this.agendamentos.add(checkin);
		return true;
	}
}
