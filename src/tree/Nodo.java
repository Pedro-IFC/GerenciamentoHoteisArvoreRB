package tree;

public class Nodo<T> {
    private T dado;
    private Cor cor;
    private  Nodo<T> esquerdo, direito, pai;
    private int chave;
    
    public Nodo(T dado, int chave) {
        this.dado = dado;
        this.chave=chave;
        this.cor = Cor.VERMELHO;
        this.esquerdo = this.direito = this.pai = null;
    }
    public T getDado() {
        return dado;
    }
    public void setDado(T dado) {
        this.dado = dado;
    }
	public Cor getCor() {
		return cor;
	}
	public void setCor(Cor cor) {
		this.cor = cor;
	}
	public Nodo<T> getEsquerdo() {
		return esquerdo;
	}
	public void setEsquerdo(Nodo<T> esquerdo) {
		this.esquerdo = esquerdo;
	}
	public Nodo<T> getDireito() {
		return direito;
	}
	public void setDireito(Nodo<T> direito) {
		this.direito = direito;
	}
	public Nodo<T> getPai() {
		return pai;
	}
	public void setPai(Nodo<T> pai) {
		this.pai = pai;
	}
	public int getChave() {
		return chave;
	}
	public void setChave(int chave) {
		this.chave = chave;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Nodo [dado=");
		builder.append(dado);
		builder.append(", cor=");
		builder.append(cor);
		builder.append(", esquerdo=");
		builder.append(esquerdo);
		builder.append(", direito=");
		builder.append(direito);
		builder.append(", pai=");
		builder.append(pai);
		builder.append("]");
		return builder.toString();
	}
    
}