public class ListaLigada {

    private Celula primeira = null;
    private Celula ultima = null;
    private int totalDeElementos = 0;

    public void adicionaNoComeco(Object elemento) {
        if (totalDeElementos == 0) {
            Celula nova = new Celula(elemento);
            primeira = nova;
            ultima = nova;
        } else {
            Celula nova = new Celula(elemento, primeira); // elemento, proximo
            nova.setAnterior(null);
            primeira.setAnterior(nova);
            primeira = nova;
        }
        totalDeElementos++;
    }

    public void adiciona(Object elemento) {
        if (totalDeElementos == 0) {
            adicionaNoComeco(elemento);
            return;
        }

        Celula nova = new Celula(elemento);
        nova.setAnterior(ultima);
        ultima.setProximo(nova);
        ultima = nova;
        totalDeElementos++;
    }

    public void adiciona(int posicao, Object elemento) {
        if (posicao < 0 || posicao > totalDeElementos) {
            throw new IllegalArgumentException("posicao invalida");
        }

        if (posicao == 0) {
            adicionaNoComeco(elemento);
            return;
        }

        if (posicao == totalDeElementos) {
            adiciona(elemento);
            return;
        }

        Celula anterior = pegaCelula(posicao - 1);
        Celula proxima = anterior.getProximo();

        Celula nova = new Celula(elemento, proxima);
        nova.setAnterior(anterior);

        anterior.setProximo(nova);
        proxima.setAnterior(nova);

        totalDeElementos++;
    }

    public Object pega(int posicao) {
        return pegaCelula(posicao).getElemento();
    }

    public void removeDoComeco() {
        if (totalDeElementos == 0) {
            throw new IllegalArgumentException("Lista vazia");
        }

        if (totalDeElementos == 1) {
            primeira = null;
            ultima = null;
            totalDeElementos = 0;
            return;
        }

        Celula novaPrimeira = primeira.getProximo();
        novaPrimeira.setAnterior(null);
        primeira = novaPrimeira;
        totalDeElementos--;
    }

    public void removeDoFim() {
        if (totalDeElementos == 0) {
            throw new IllegalArgumentException("Lista vazia");
        }

        if (totalDeElementos == 1) {
            removeDoComeco();
            return;
        }

        Celula penultima = ultima.getAnterior();
        penultima.setProximo(null);
        ultima = penultima;
        totalDeElementos--; // CORRETO: decrementa
    }

    public void remove(int posicao) {
        if (!posicaoOcupada(posicao)) {
            throw new IllegalArgumentException("posicao inexistente");
        }

        if (posicao == 0) {
            removeDoComeco();
            return;
        }

        if (posicao == totalDeElementos - 1) {
            removeDoFim();
            return;
        }

        Celula atual = pegaCelula(posicao);
        Celula anterior = atual.getAnterior();
        Celula proxima = atual.getProximo();

        anterior.setProximo(proxima);
        proxima.setAnterior(anterior);

        totalDeElementos--;
    }

    public int tamanho() {
        return totalDeElementos;
    }

    public boolean contem(Object o) {
        Celula atual = primeira;
        while (atual != null) {
            if (o == null) {
                if (atual.getElemento() == null) return true;
            } else {
                if (o.equals(atual.getElemento())) return true;
            }
            atual = atual.getProximo();
        }
        return false;
    }

    private boolean posicaoOcupada(int posicao) {
        return posicao >= 0 && posicao < totalDeElementos;
    }

    private Celula pegaCelula(int posicao) {
        if (!posicaoOcupada(posicao)) {
            throw new IllegalArgumentException("posicao inexistente");
        }

        // Otimização: se estiver mais perto do fim, percorre de trás pra frente
        if (posicao <= totalDeElementos / 2) {
            Celula atual = primeira;
            for (int i = 0; i < posicao; i++) {
                atual = atual.getProximo();
            }
            return atual;
        } else {
            Celula atual = ultima;
            for (int i = totalDeElementos - 1; i > posicao; i--) {
                atual = atual.getAnterior();
            }
            return atual;
        }
    }

    @Override
    public String toString() {
        if (totalDeElementos == 0) return "[]";

        StringBuilder sb = new StringBuilder("[");
        Celula atual = primeira;

        while (atual != null) {
            sb.append(atual.getElemento());
            if (atual.getProximo() != null) sb.append(", ");
            atual = atual.getProximo();
        }

        sb.append("]");
        return sb.toString();
    }
}