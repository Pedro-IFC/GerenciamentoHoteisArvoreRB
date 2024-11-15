package classes;
import tree.ArvoreRubroNegro;
import tree.Nodo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reservas extends ArvoreRubroNegro<Reserva> {
	public List<Reserva> getReservasByQuarto(int idQuarto) {
	    List<Reserva> reservas = new ArrayList<>();
	    buscarNodosQuarto(this.getRaiz(), idQuarto, reservas);
	    return reservas;
	}
	private void buscarNodosQuarto(Nodo<Reserva> raiz, int idQuarto, List<Reserva> reservas) {
	    if (raiz == null) 
	        return;
	    if (raiz.getDado().getQuarto().getNumero() == idQuarto) 
	        reservas.add(raiz.getDado());
	    buscarNodosQuarto(raiz.getEsquerdo(), idQuarto, reservas);
	    buscarNodosQuarto(raiz.getDireito(), idQuarto, reservas);
	}
	public List<Reserva> getReservasByDate(Date data, boolean entre) {
	    List<Reserva> reservas = new ArrayList<>();
	    buscarNodosDate(this.getRaiz(), data, reservas, entre);
	    return reservas;
	}
	private void buscarNodosDate(Nodo<Reserva> raiz, Date data, List<Reserva> reservas, boolean entre) {
	    if (raiz == null) 
	        return;
	    if (raiz.getDado().isDateBetween(data, entre)) 
	        reservas.add(raiz.getDado());
	    buscarNodosDate(raiz.getEsquerdo(), data, reservas, entre);
	    buscarNodosDate(raiz.getDireito(), data, reservas, entre);
	}
	public List<Reserva> getReservasByDateEQuarto(Date data, boolean entre, int idQuarto) {
	    List<Reserva> reservas = new ArrayList<>();
	    buscarNodosDateEQuarto(this.getRaiz(), data, reservas, entre, idQuarto);
	    return reservas;
	}
	private void buscarNodosDateEQuarto(Nodo<Reserva> raiz, Date data, List<Reserva> reservas, boolean entre, int idQuarto) {
	    if (raiz == null) 
	        return;
	    if (raiz.getDado().isDateBetween(data, entre) && raiz.getDado().getQuarto().getNumero() == idQuarto) 
	        reservas.add(raiz.getDado());
	    buscarNodosDateEQuarto(raiz.getEsquerdo(), data, reservas, entre, idQuarto);
	    buscarNodosDateEQuarto(raiz.getDireito(), data, reservas, entre, idQuarto);
	}
}