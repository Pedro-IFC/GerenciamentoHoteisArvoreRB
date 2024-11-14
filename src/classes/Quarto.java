package classes;

public class Quarto {
	private int numero;
	private CategoriaDoQuarto categoria;
	
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
}
