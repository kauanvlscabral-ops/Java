import java.lang.reflect.*;

public class ArvoreBinariaDeBusca <X extends Comparable<X>>
{
    private class No
    {
        private No esq;
        private X  info;
        private No dir;

        public No (No e, X i, No d)
        {
            this.esq  = e;
            this.info = i;
            this.dir  = d;
        }

        public No (X i)
        {
            this.esq  = null;
            this.info = i;
            this.dir  = null;
        }

        public No getEsq ()
        {
            return this.esq;
        }

        public X getInfo ()
        {
            return this.info;
        }

        public No getDir ()
        {
            return this.dir;
        }

        public void setEsq (No e)
        {
            this.esq = e;
        }
        
        public void setInfo (X i)
        {
            this.info = i;
        }

        public void setDir (No d)
        {
            this.dir = d;
        }
    } //fim da classe No

    private No raiz;
    
    public void guardeUmItem (X i) throws Exception
    {
        if (i==null) throw new Exception ("Informacao ausente");
        
        if (this.raiz==null)
        {
            this.raiz = new No (i);
            return;
        }
        
        No atual=this.raiz;
        for(;;) // forever
        {
            int comparacao=i.compareTo(atual.getInfo());
            
            if (comparacao==0) throw new Exception ("Elemento repetido");
            
            if (comparacao<0)
            {
                if (atual.getEsq()==null)
                {
                    atual.setEsq (new No (i));
                    return;
                }
                else
                    atual=atual.getEsq();
            }
            else // comparacao>0
            {
                if (atual.getDir()==null)
                {
                    atual.setDir (new No (i));
                    return;
                }
                else
                    atual=atual.getDir();
            }
        }
    }
    
    public boolean temOItem (X i) throws Exception
    {
		if (i==null) throw new Exception ("Informacao ausente");
		
		if (this.raiz==null) return false;
		
		No atual=this.raiz;
		while (atual!=null)
		{
			int comparacao=i.compareTo(atual.getInfo());
			if (comparacao==0) return true;
			if (comparacao<0)
			    atual=atual.getEsq();
			else // comparacao>0
			    atual=atual.getDir();
		}
		return false;
	}
	
	private int getAltura (No r)
	{
		if (r==null) return 0;
		
		int alturaDaSubarvoreEsquerda = this.getAltura(r.getEsq()),
		    alturaDaSubarvoreDireita  = this.getAltura(r.getDir());
		    
		return alturaDaSubarvoreEsquerda>alturaDaSubarvoreDireita
		       ?alturaDaSubarvoreEsquerda+1
		       :alturaDaSubarvoreDireita +1;
	}
	
	public int getAltura ()
	{
		return this.getAltura(this.raiz);
	}
	
	public void removaUmItem (X i) throws Exception
    {
		if (i==null) throw new Exception ("Informacao ausente");

        if (this.raiz == null) throw new Exception("Arvore vazia");

        No atual = this.raiz;
        No pai = null;
        boolean filhoEsquerdo = true;

        for (;;)
        {
            int comparacao = info.compareTo(atual.getInfo());
            if (comparacao==0) break;
     
            pai = atual;
            if (comparacao<0)
            {
                atual=atual.getEsq();
                filhoEsquerdo=true;
            }
            else
            {
                atual=atual.getDir();
                filhoEsquerdo=false;
            }
            
            if (atual==null) throw new Exception ("Remocao de algo inexistente");
        }

        // se a info for encontrada numa folha, deslique a folha da árvore,
        // fazendo o ponteiro que aponta para ela dentro do seu nó pai,
        // tornar-se null
        if (atual.getEsq()==null && atual.getDir()==null)
        {
            if (atual==this.raiz)
                this.raiz=null;
            else if (filhoEsquerdo)
                pai.setEsq(null);
            else
                pai.setDir(null);
        }
        // se info for encontrada num nó N, que não é folha, sendo que N
        // só tem filho à esquerda, e sendo N filho esquerdo de um certo
        // pai P, faça o ponteiro esquerdo de P, passar a apontar para
        // esse filho que ha na esquerda de N
        else if (atual.getDir()==null && filhoEsquerdo)
        {
            if (atual==this.raiz)
                this.raiz=atual.getEsq();
            else
                pai.setEsq(atual.getEsq());
        }
        // se info for encontrada num nó N, que não é folha, sendo que N
        // só tem filho à esquerda, e sendo N filho direito de um certo
        // pai P, faça o ponteiro direito de P, passar a apontar para
        // esse filho que ha na esquerda de N
        else if (atual.getDir()==null && !filhoEsquerdo)
        {
            if (atual==this.raiz)
                this.raiz=atual.getEsq();
            else
                pai.setDir(atual.getEsq());
        }
        // se info for encontrada num nó N, que não é folha, sendo que N
        // só tem filho à direita, e sendo N filho esquerdo de um certo
        // pai P, faça o ponteiro esquerdo de P, passar a apontar para
        // esse filho que ha na direita de N
        else if (atual.getEsq()==null && filhoEsquerdo)
        {
            if (atual==this.raiz)
                this.raiz=atual.getDir();
            else
                pai.setEsq(atual.getDir());
        }
        // se info for encontrada num nó N, que não é folha, sendo que N
        // só tem filho à direita, e sendo N filho direita de um certo
        // pai P, faça o ponteiro direito de P, passar a apontar para
        // esse filho que ha na direita de N
        else if (atual.getEsq()==null && !filhoEsquerdo)
        {
            if (atual==this.raiz)
                this.raiz=atual.getDir();
            else
                pai.setDir(atual.getDir());
        }
        // se info for encontrada num nó N, que não é folha e tem 2 filhos,
        // encontre a informação info que existe à extrema esquerda da
        // subarvore direita de N ou à extrema direita da subarvore esquerda
        // de N; remova o nó que contém info e substitua dentro do nó N,
        // a informação que ali se encontra por info
        else
        {
            No sucessor = null;
            pai=atual;
            if (getQtdDeNodos(atual.getEsq())>getQtdDeNodos(atual.getDir())) {
                sucessor = atual.getEsq();
                filhoEsquerdo = true;
                while (sucessor.getDir() != null)
                {
                    pai = sucessor;
                    sucessor = sucessor.getDir();
                    filhoEsquerdo = false;
                }
            }
            else
            {
                sucessor = atual.getDir();
                filhoEsquerdo = false;
                while (sucessor.getEsq() != null)
                {
                    pai = sucessor;
                    sucessor = sucessor.getEsq();
                    filhoEsquerdo = true;
                }
            }
            if (filhoEsquerdo){
                pai.setEsq(sucessor.getDir());
            }
            else{
                pai.setDir(sucessor.getEsq());
            }

            atual.setInfo(sucessor.getInfo());
        }
	}
	
	private int getQtdDeNodos (No r)
	{
		if (r==null) return 0;
		
		return getQtdNos(r.getEsq())+1+getQtdNos(r.getDir());
	}
	
    public int getQtdDeNodos ()
    {
		return getQtdNos (this.raiz);
	}
	
	private boolean isBalanceada (No r)
	{
		int qtdEsq = getQtdDeNodos(r.getEsq()),
		    qtdDir = getQtdDeNodos(r.getDir());
		    
		if (qtdEsq>qtdDir+1 || qtdDir>qtdEsq+1) return false;
		
		/*
		O Vinicius propoe trocar todos os comandos abaixo por:
		return isBalanceada(r.getEsq()) && isBalanceada(r.getDir());
		e tudo bem!
		*/
		
		if (!isBalanceada(r.getEsq())) return false;
		if (!isBalanceada(r.getDir())) return false;
		
		return true;
		
	}
	
	public boolean isBalanceada ()
	{
		return isBalaceada(this.raiz);
	}
	
	public void balanceieSe (No r)
	{
        if (r==null) return;

		int qtdEsq = getQtdDeNodos(r.getEsq()),
		    qtdDir = getQtdDeNodos(r.getDir());
		    
		while (Math.abs(qtdEsq-qtdDir)>1)
			if (qtdEsq>qtdDir)
            {
				No pai   = r;
				No atual = r.getEsq();
				while (atual.getDir() != null)
				{
					pai   = atual;
					atual = atual.getDir();
				}
				X infoRaiz = r.getInfo();
				r.setInfo(atual.getInfo());
				pai.setDir(atual.getEsq());
				qtdEsq--;
				guardeUmItem(infoRaiz);
				qtdDir++;
			}
			else // qtdDir>qtdEsq
            {
				No pai   = r;
				No atual = r.getDir();
				while (atual.getEsq() != null)
				{
					pai   = atual;
					atual = atual.getEsq();
				}
				X infoRaiz = r.getInfo();
				r.setInfo(atual.getInfo());
				pai.setEsq(atual.getDir());
				qtdDir--;
				guardeUmItem(infoRaiz);
				qtdEsq++;
			}
			
		balanceieSe(r.getEsq());
		balanceieSe(r.getDir());		
	}

	public void balanceieSe ()
	{
		balanceieSe(this.raiz);
	}
	
	public boolean isEspelho (No r1, No r2)
	{
		if (r1==null && r2==null) return true;
		if (r1==null || r2==null) return false;
		
		if (!r1.getInfo().equals(r2.getInfo())) return false;
		
		/*
		O Vinicius propoe trocar todos os comandos abaixo por:
		return isEspelho(r1.getEsq(),r2.getDir()) &&
		       isEspelho(r1.getDir(),r2.getEsq());
		e tudo bem!
		*/
		
		if (!isEspelho(r1.getEsq(),r2.getDir()) return false;
		if (!isEspelho(r1.getDir(),r2.getEsq()) return false;
		
		return true;
	}
	
	public boolean isEspelho (ArvoreBinariaDeBusca<X> arv)
	{
		return isEspelho(this.raiz,arv.raiz);
	}
}
