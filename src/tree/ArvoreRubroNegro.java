package tree;

public class ArvoreRubroNegro<T> {
    private Nodo<T> raiz = null;
    
    private void rotacaoEsquerda(Nodo<T> nodo) {
        Nodo<T> direito = nodo.getDireito();
        nodo.setDireito(direito.getEsquerdo());
        
        if (direito.getEsquerdo() != null) {
            direito.getEsquerdo().setPai(nodo);
        }
        
        direito.setPai(nodo.getPai());
        
        if (nodo.getPai() == null) {
            this.raiz = direito;
        } else if (nodo == nodo.getPai().getEsquerdo()) {
            nodo.getPai().setEsquerdo(direito);
        } else {
            nodo.getPai().setDireito(direito);
        }
        
        direito.setEsquerdo(nodo);
        nodo.setPai(direito);
    }

    private void rotacaoDireita(Nodo<T> nodo) {
        Nodo<T> esquerdo = nodo.getEsquerdo();
        nodo.setEsquerdo(esquerdo.getDireito());
        
        if (esquerdo.getDireito() != null) {
            esquerdo.getDireito().setPai(nodo);
        }
        
        esquerdo.setPai(nodo.getPai());
        
        if (nodo.getPai() == null) {
        	this.raiz = esquerdo;
        } else if (nodo == nodo.getPai().getDireito()) {
            nodo.getPai().setDireito(esquerdo);
        } else {
            nodo.getPai().setEsquerdo(esquerdo);
        }
        
        esquerdo.setDireito(nodo);
        nodo.setPai(esquerdo);
    }

    public void inserir(T dado, int chave) {
        Nodo<T> novoNodo = new Nodo<>(dado, chave);
        raiz = inserirRecursivo(raiz, novoNodo);
        balancearInsercao(novoNodo);
    }

    private Nodo<T> inserirRecursivo(Nodo<T> raiz, Nodo<T> nodo) {
        if (raiz == null) {
            return nodo;
        }
        
        if (nodo.getChave()<raiz.getChave()) {
            raiz.setEsquerdo(inserirRecursivo(raiz.getEsquerdo(), nodo));
            raiz.getEsquerdo().setPai(raiz);
        } else if (nodo.getChave()>raiz.getChave()) {
            raiz.setDireito(inserirRecursivo(raiz.getDireito(), nodo));
            raiz.getDireito().setPai(raiz);
        }
        return raiz;
    }
    private void balancearInsercao(Nodo<T> nodo) {
        Nodo<T> pai, avo;
        
        while (nodo != raiz && nodo.getCor() != Cor.PRETO && nodo.getPai().getCor() == Cor.VERMELHO) {
            pai = nodo.getPai();
            avo = pai.getPai();
            
            if (pai == avo.getEsquerdo()) {
                Nodo<T> tio = avo.getDireito();
                
                if (tio != null && tio.getCor() == Cor.VERMELHO) {
                    avo.setCor(Cor.VERMELHO);
                    pai.setCor(Cor.PRETO);
                    tio.setCor(Cor.PRETO);
                    nodo = avo;
                } else {
                    if (nodo == pai.getDireito()) {
                        rotacaoEsquerda(pai);
                        nodo = pai;
                        pai = nodo.getPai();
                    }
                    
                    rotacaoDireita(avo);
                    Cor tempCor = pai.getCor();
                    pai.setCor(avo.getCor());
                    avo.setCor(tempCor);
                    nodo = pai;
                }
            } else { 
                Nodo<T> tio = avo.getEsquerdo();
                
                if (tio != null && tio.getCor() == Cor.VERMELHO) {
                    avo.setCor(Cor.VERMELHO);
                    pai.setCor(Cor.PRETO);
                    tio.setCor(Cor.PRETO);
                    nodo = avo;
                } else {
                    if (nodo == pai.getEsquerdo()) {
                        rotacaoDireita(pai);
                        nodo = pai;
                        pai = nodo.getPai();
                    }
                    
                    rotacaoEsquerda(avo);
                    Cor tempCor = pai.getCor();
                    pai.setCor(avo.getCor());
                    avo.setCor(tempCor);
                    nodo = pai;
                }
            }
        }
        raiz.setCor(Cor.PRETO);
    }
}