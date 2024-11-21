package classes;
import java.util.ArrayList;
import java.util.List;

import tree.ArvoreRubroNegro;
import tree.Nodo;

public class Cancelamentos extends ArvoreRubroNegro<Cancelamento> {
	public List<Cancelamento> getByQuarto(int idQuarto) {
	    List<Cancelamento> reservas = new ArrayList<>();
	    buscarNodosQuarto(this.getRaiz(), idQuarto, reservas);
	    return reservas;
	}
	private void buscarNodosQuarto(Nodo<Cancelamento> raiz, int idQuarto, List<Cancelamento> reservas) {
	    if (raiz == null) 
	        return;
	    if (raiz.getDado().getQuarto().getNumero() == idQuarto) 
	        reservas.add(raiz.getDado());
	    buscarNodosQuarto(raiz.getEsquerdo(), idQuarto, reservas);
	    buscarNodosQuarto(raiz.getDireito(), idQuarto, reservas);
	}
}
