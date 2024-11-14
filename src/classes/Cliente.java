package classes;

public class Cliente extends Ficheiro{
	private int CPF;
	private String nome;
	public Cliente(int CPF, String nome) {
		super();
		this.CPF = CPF;
		this.nome = nome;
	}
	public String getNome() {
		return nome;
	}
	public int getCPF() {
		return CPF;
	}
}
