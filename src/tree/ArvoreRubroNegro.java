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

    public boolean inserir(T dado, int chave) {
        Nodo<T> novoNodo = new Nodo<>(dado, chave);
        boolean inserido = inserirRecursivo(raiz, novoNodo);
        if (inserido) {
            balancearInsercao(novoNodo);
        }
        return inserido;
    }

    private boolean inserirRecursivo(Nodo<T> raiz, Nodo<T> nodo) {
        if (raiz == null) {
            this.raiz = nodo; // Se a raiz está vazia, definimos o novo nodo como raiz
            return true;
        }

        if (nodo.getChave() < raiz.getChave()) {
            if (raiz.getEsquerdo() == null) {
                raiz.setEsquerdo(nodo);
                nodo.setPai(raiz);
                return true;
            } else {
                return inserirRecursivo(raiz.getEsquerdo(), nodo);
            }
        } else if (nodo.getChave() > raiz.getChave()) {
            if (raiz.getDireito() == null) {
                raiz.setDireito(nodo);
                nodo.setPai(raiz);
                return true;
            } else {
                return inserirRecursivo(raiz.getDireito(), nodo);
            }
        } else {
            return false;
        }
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
    public int size() {
        return contarNodos(raiz);
    }
    private int contarNodos(Nodo<T> nodo) {
        if (nodo == null) {
            return 0;
        }
        int esquerdo = contarNodos(nodo.getEsquerdo());
        int direito = contarNodos(nodo.getDireito());
        return 1 + esquerdo + direito;
    }
    public boolean excluir(int chave) {
        Nodo<T> nodo = buscarNodo(raiz, chave); // Localiza o nodo a ser excluído
        if (nodo == null) {
            return false; // Elemento não encontrado
        }
        
        Nodo<T> substituto, fixNode;
        Cor corOriginal = nodo.getCor();
        
        // Caso o nodo tenha apenas um filho ou nenhum
        if (nodo.getEsquerdo() == null) {
            fixNode = nodo.getDireito();
            substituirNodo(nodo, nodo.getDireito());
        } else if (nodo.getDireito() == null) {
            fixNode = nodo.getEsquerdo();
            substituirNodo(nodo, nodo.getEsquerdo());
        } else {
            // Se o nodo tem dois filhos, encontra o sucessor
            substituto = menorNodo(nodo.getDireito());
            corOriginal = substituto.getCor();
            fixNode = substituto.getDireito();
            
            if (substituto.getPai() == nodo) {
                if (fixNode != null) fixNode.setPai(substituto);
            } else {
                substituirNodo(substituto, substituto.getDireito());
                substituto.setDireito(nodo.getDireito());
                substituto.getDireito().setPai(substituto);
            }
            
            substituirNodo(nodo, substituto);
            substituto.setEsquerdo(nodo.getEsquerdo());
            substituto.getEsquerdo().setPai(substituto);
            substituto.setCor(nodo.getCor());
        }
        
        if (corOriginal == Cor.PRETO) {
            balancearExclusao(fixNode);
        }
        
        return true;
    }
    public boolean has(int chave) {
    	return this.buscarNodo(this.raiz, chave)!=null;
    }
    private Nodo<T> buscarNodo(Nodo<T> raiz, int chave) {
        if (raiz == null || raiz.getChave() == chave) return raiz;
        if (chave < raiz.getChave()) return buscarNodo(raiz.getEsquerdo(), chave);
        return buscarNodo(raiz.getDireito(), chave);
    }
    public T get(int chave) {
        Nodo<T> nodo = buscarNodo(raiz, chave);
        return (nodo != null) ? nodo.getDado() : null;
    }
    private void substituirNodo(Nodo<T> antigo, Nodo<T> novoNodo) {
        if (antigo.getPai() == null) {
            raiz = novoNodo;
        } else if (antigo == antigo.getPai().getEsquerdo()) {
            antigo.getPai().setEsquerdo(novoNodo);
        } else {
            antigo.getPai().setDireito(novoNodo);
        }
        if (novoNodo != null) {
            novoNodo.setPai(antigo.getPai());
        }
    }

    private Nodo<T> menorNodo(Nodo<T> nodo) {
        while (nodo.getEsquerdo() != null) {
            nodo = nodo.getEsquerdo();
        }
        return nodo;
    }
    private void balancearExclusao(Nodo<T> nodo) {
        while (nodo != raiz && (nodo == null || nodo.getCor() == Cor.PRETO)) {
            if (nodo == nodo.getPai().getEsquerdo()) {
                Nodo<T> irmao = nodo.getPai().getDireito();
                if (irmao.getCor() == Cor.VERMELHO) {
                    irmao.setCor(Cor.PRETO);
                    nodo.getPai().setCor(Cor.VERMELHO);
                    rotacaoEsquerda(nodo.getPai());
                    irmao = nodo.getPai().getDireito();
                }
                if ((irmao.getEsquerdo() == null || irmao.getEsquerdo().getCor() == Cor.PRETO) &&
                    (irmao.getDireito() == null || irmao.getDireito().getCor() == Cor.PRETO)) {
                    irmao.setCor(Cor.VERMELHO);
                    nodo = nodo.getPai();
                } else {
                    if (irmao.getDireito() == null || irmao.getDireito().getCor() == Cor.PRETO) {
                        if (irmao.getEsquerdo() != null) irmao.getEsquerdo().setCor(Cor.PRETO);
                        irmao.setCor(Cor.VERMELHO);
                        rotacaoDireita(irmao);
                        irmao = nodo.getPai().getDireito();
                    }
                    irmao.setCor(nodo.getPai().getCor());
                    nodo.getPai().setCor(Cor.PRETO);
                    if (irmao.getDireito() != null) irmao.getDireito().setCor(Cor.PRETO);
                    rotacaoEsquerda(nodo.getPai());
                    nodo = raiz;
                }
            } else {
                Nodo<T> irmao = nodo.getPai().getEsquerdo();
                if (irmao.getCor() == Cor.VERMELHO) {
                    irmao.setCor(Cor.PRETO);
                    nodo.getPai().setCor(Cor.VERMELHO);
                    rotacaoDireita(nodo.getPai());
                    irmao = nodo.getPai().getEsquerdo();
                }
                if ((irmao.getDireito() == null || irmao.getDireito().getCor() == Cor.PRETO) &&
                    (irmao.getEsquerdo() == null || irmao.getEsquerdo().getCor() == Cor.PRETO)) {
                    irmao.setCor(Cor.VERMELHO);
                    nodo = nodo.getPai();
                } else {
                    if (irmao.getEsquerdo() == null || irmao.getEsquerdo().getCor() == Cor.PRETO) {
                        if (irmao.getDireito() != null) irmao.getDireito().setCor(Cor.PRETO);
                        irmao.setCor(Cor.VERMELHO);
                        rotacaoEsquerda(irmao);
                        irmao = nodo.getPai().getEsquerdo();
                    }
                    irmao.setCor(nodo.getPai().getCor());
                    nodo.getPai().setCor(Cor.PRETO);
                    if (irmao.getEsquerdo() != null) irmao.getEsquerdo().setCor(Cor.PRETO);
                    rotacaoDireita(nodo.getPai());
                    nodo = raiz;
                }
            }
        }
        if (nodo != null) nodo.setCor(Cor.PRETO);
    }
    public Nodo<T> getRaiz(){
    	return this.raiz;
    }
}
