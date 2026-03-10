public class ArvoreBinariaDeBusca<X extends Comparable<X>> {

    private class No {

        private No esq;
        private X info;
        private No dir;

        public No(No e, X i, No d) {
            this.esq = e;
            this.info = i;
            this.dir = d;
        }

        public No(X i) {
            this.esq = null;
            this.info = i;
            this.dir = null;
        }

        public No getEsq() {
            return this.esq;
        }

        public X getInfo() {
            return this.info;
        }

        public No getDir() {
            return this.dir;
        }

        public void setEsq(No e) {
            this.esq = e;
        }

        public void setInfo(X i) {
            this.info = i;
        }

        public void setDir(No d) {
            this.dir = d;
        }
    } //fim da classe No

    private No raiz;

    public void guardeUmItem(X i) throws Exception {
        if (i == null) {
            throw new Exception("Informacao ausente");
        }

        if (this.raiz == null) {
            this.raiz = new No(i);
            return;
        }

        No atual = this.raiz;
        for (;;) // forever
        {
            int comparacao = i.compareTo(atual.getInfo());

            if (comparacao == 0) {
                throw new Exception("Elemento repetido");
            }

            if (comparacao < 0) {
                if (atual.getEsq() == null) {
                    atual.setEsq(new No(i));
                    return;
                } else {
                    atual = atual.getEsq();
                }
            } else // comparacao>0
            {
                if (atual.getDir() == null) {
                    atual.setDir(new No(i));
                    return;
                } else {
                    atual = atual.getDir();
                }
            }
        }
    }

    public boolean temOItem(X i) throws Exception {
        if (i == null) {
            throw new Exception("Informacao ausente");
        }
        No atual = this.raiz;
        while (atual != null) {
            int comp = i.compareTo(atual.getInfo());
            if (comp == 0) {
                return true; //igual ao valor do nó atual "true"

            }
            if (comp < 0) {
                atual = atual.getEsq(); //menor que o valor do nó atual vai para o filho a esquerda
            } else {
                atual = atual.getDir(); //maior que o valor do nó atual vai para o filho a direita
            }
        }
        return false;
    }

    public boolean temOItemRec(X i) throws Exception {
        if (i == null) {
            throw new Exception("Informacao ausente"); // Se for nulo, da pal

        }
        return tem(this.raiz, i); // Chama o metodo private começando pela raiz
    }

    private boolean tem(No atual, X i) throws Exception {
        if (atual == null) {
            return false; // Se o atual for nulo, não foi encontrado(não esta na arvore)

        }
        int comp = i.compareTo(atual.getInfo()); // Compara o valor procurado com o item do no atual
        if (comp == 0) {
            return true; //achou
        } else if (comp < 0) {
            return tem(atual.getEsq(), i);  //menor que o valor do nó atual vai para o filho a esquerda    
        } else {
            return tem(atual.getDir(), i);//maior que o valor do nó atual vai para o filho a direita

        }
    }

    public int altura() throws Exception {
        if (this.raiz == null) {
            throw new Exception("Informacao ausente"); // Se for nulo, da pal

        }
        return altura(this.raiz);
    }

    private int altura(No no) {
        //ver qual o caminho mais longo e devolver a qtd -1
        if (no == null) {
            return -1;
        }
        int qtdAtrEsq = altura(no.getEsq());
        int qtdAtrDir = altura(no.getEsq());

        if (qtdAtrEsq > qtdAtrDir) {
            return qtdAtrEsq + 1;
        } else {
            return qtdAtrDir + 1;
        }
    }

    public void removaUmItem(X i) throws Exception {
        if (i == null) {
            throw new Exception("Informacao ausente");
        }

        if (this.raiz == null) {
            throw new Exception("Arvore vazia");
        }

        No atual = this.raiz;
        No pai = null;
        boolean filhoEsquerdo = true;

        for (;;) {
            int comparacao = info.compareTo(atual.getInfo());
            if (comparacao == 0) {
                break;
            }

            pai = atual;
            if (comparacao < 0) {
                atual = atual.getEsq();
                filhoEsquerdo = true;
            } else {
                atual = atual.getDir();
                filhoEsquerdo = false;
            }

            if (atual == null) {
                throw new Exception("Remocao de algo inexistente");
            }
        }

        // se a info for encontrada numa folha, deslique a folha da árvore,
        // fazendo o ponteiro que aponta para ela dentro do seu nó pai,
        // tornar-se null
        if (atual.getEsq() == null && atual.getDir() == null) {
            if (atual == this.raiz) {
                this.raiz = null;
            } else if (filhoEsquerdo) {
                pai.setEsq(null);
            } else {
                pai.setDir(null);
            }
        } // se info for encontrada num nó N, que não é folha, sendo que N
        // só tem filho à esquerda, e sendo N filho esquerdo de um certo
        // pai P, faça o ponteiro esquerdo de P, passar a apontar para
        // esse filho que ha na esquerda de N
        else if (atual.getDir() == null && filhoEsquerdo) {
            if (atual == this.raiz) {
                this.raiz = atual.getEsq();
            } else {
                pai.setEsq(atual.getEsq());
            }
        } // se info for encontrada num nó N, que não é folha, sendo que N
        // só tem filho à esquerda, e sendo N filho direito de um certo
        // pai P, faça o ponteiro direito de P, passar a apontar para
        // esse filho que ha na esquerda de N
        else if (atual.getDir() == null && !filhoEsquerdo) {
            if (atual == this.raiz) {
                this.raiz = atual.getEsq();
            } else {
                pai.setDir(atual.getEsq());
            }
        } // se info for encontrada num nó N, que não é folha, sendo que N
        // só tem filho à direita, e sendo N filho esquerdo de um certo
        // pai P, faça o ponteiro esquerdo de P, passar a apontar para
        // esse filho que ha na direita de N
        else if (atual.getEsq() == null && filhoEsquerdo) {
            if (atual == this.raiz) {
                this.raiz = atual.getDir();
            } else {
                pai.setEsq(atual.getDir());
            }
        } // se info for encontrada num nó N, que não é folha, sendo que N
        // só tem filho à direita, e sendo N filho direita de um certo
        // pai P, faça o ponteiro direito de P, passar a apontar para
        // esse filho que ha na direita de N
        else if (atual.getEsq() == null && !filhoEsquerdo) {
            if (atual == this.raiz) {
                this.raiz = atual.getDir();
            } else {
                pai.setDir(atual.getDir());
            }
        } // se info for encontrada num nó N, que não é folha e tem 2 filhos,
        // encontre a informação info que existe à extrema esquerda da
        // subarvore direita de N ou à extrema direita da subarvore esquerda
        // de N; remova o nó que contém info e substitua dentro do nó N,
        // a informação que ali se encontra por info
        else {
            No sucessor = null;
            pai = atual;
            if (getQtdNodos(atual.getEsq()) > getQtdNodos(atual.getDir())) {
                sucessor = atual.getEsq();
                filhoEsquerdo = true;
                while (sucessor.getDir() != null) {
                    pai = sucessor;
                    sucessor = sucessor.getDir();
                    filhoEsquerdo = false;
                }
            } else {
                sucessor = atual.getDir();
                filhoEsquerdo = false;
                while (sucessor.getEsq() != null) {
                    pai = sucessor;
                    sucessor = sucessor.getEsq();
                    filhoEsquerdo = true;
                }
            }
            if (filhoEsquerdo) {
                pai.setEsq(sucessor.getDir());
            } else {
                pai.setDir(sucessor.getEsq());
            }

            atual.setInfo(sucessor.getInfo());
        }
    }

    public boolean isBalanceada() throws Exception {
        return isBalanceada(this.raiz);
    }

    private boolean isBalanceada(No no) throws Exception {
        if (no == null) {
            return false;
        }

        int alturaEsq = altura(no.getEsq());
        int alturaDir = altura(no.getDir());

        int diferenca;
        if (alturaDir > alturaEsq) {
            diferenca = alturaDir - alturaEsq;
        } else {
            diferenca = alturaEsq - alturaDir;
        }

        if (diferenca > 1) {
            return false;
        }

        return isBalanceada(no.getDir()) && isBalanceada(no.getEsq());
    }

    private int getQtdNodos(No r) {
        if (r == null) {
            return 0;
        }

        return 1 + getQtdNodos(r.getEsq()) + 1 + getQtdNodos(r.getDir());
    }

    private void balanceieSe(No r) throws Exception {
        if (this.raiz == null) {
            return;
        }

        int qtdEsq = getQtdNodos(r.getEsq());
        int qtdDir = getQtdNodos(r.getDir());

        while (Math.abs(qtdDir - qtdEsq) > 1) {
            if (qtdEsq > qtdDir) {
                No atual = r.getEsq();
                No pai = r;
                while (atual.getDir() != null) {
                    pai = atual;
                    atual = atual.getDir();
                }
                X infoRaiz = r.getInfo();
                r.setInfo(atual.getInfo());
                pai.setDir(atual.getEsq());
                qtdEsq--;
                guardeUmItem(infoRaiz);
                qtdDir++;
            } else {
                No atual = r.getDir();
                No pai = r;
                while (atual.getEsq() != null) {
                    pai = atual;
                    atual = atual.getEsq();
                }
                X infoRaiz = r.getInfo();
                r.setInfo(atual.getInfo());
                pai.setDir(atual.getEsq());
                qtdDir--;
                guardeUmItem(infoRaiz);
                qtdEsq++;
            }
        }
    }

    public void balanceieSe() throws Exception {
        balanceieSe(this.raiz);

    }

    public boolean isEspelho(No r1, No r2) {
        if (r1 == null && r2 == null) {
            return true;
        }
        if (r1 == null || r2 == null) {
            return false;
        }

        if (!r1.getInfo().equals(r2.getInfo())) {
            return false;
        }
        return isEspelho(r1.getEsq(), r2.getDir()) && isEspelho(r1.getDir(), r2.getEsq());

    }
}
