import java.lang.reflect.Method;

public class Fila<X> implements Cloneable{
    private Object[] elem;
    private int primeiro;
    private int ultimo;

    public Fila (){
        this.elem = new Object[10];
        this.primeiro = 0;
        this.ultimo = 0;
        
    }

    public Fila(int cap) throws Exception{
        if(cap <= 0)
        throw new Exception("Capacidade invalida");

        this.elem = new Object[cap];
        this.primeiro = 0;
        this.ultimo = 0;
    }

    public void redimensioneSe(int novaCap){
        Object [] novo = new Object[novaCap];
        for(int i=0; i<this.ultimo; i++)
            novo[i] = this.elem[i];

        this.elem = novo;
    }


    public void inclua (X item) throws Exception{
        if(item == null)
        throw new Exception("Item ausente");

        if(ultimo == this.elem.length)
        redimensioneSe(2*this.elem.length);


        if (item instanceof Cloneable) {
            Clonador <X> clonador = new Clonador<X>();
            this.elem[ultimo] = clonador.clone(item);
            primeiro++;
        }
        this.elem[ultimo] = item;
        primeiro++;
    }


    public X recupereUmItem() throws Exception{
        if(this.elem[primeiro] == null)
        throw new Exception("Fila vazia");

        if(this.elem[primeiro] instanceof Cloneable){
            Clonador <Object> clonador = new Clonador<Object>();
            return (X) clonador.clone(this.elem[primeiro]);
        }

        return (X) this.elem[primeiro];
    }

    public void removaUmItem() throws Exception{
        if (ultimo == primeiro) 
        throw new Exception("Fila vazia");


        if(primeiro<this.elem.length/4)
        redimensioneSe(this.elem.length/2);

        this.elem[primeiro] = null;
        primeiro--;

        for(int i = 0; i<this.ultimo; i++){
            this.elem[i] = elem[i + 1];
        }
    }

    public boolean isCheio(){
        return ultimo == this.elem.length;
        // if(this.elem.length == ultimo- 1)
        // return true;

        // return false;
    }

    public boolean isVazio(){
        return primeiro== 0;
        // if(this.elem.length == 0 || ultimo==-1)
        // return true;

        // return false;
    }

    public String toString() {
        String ret = "{";

        for (int i = 0; i < this.ultimo; i++)
            ret += this.elem[i] + ",";

        if (this.ultimo > 0) {
            ret += this.elem[this.ultimo - 1];
        }
            

        ret += "}";

        return ret;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;    
        if (obj == null)return false;
        // if (!(obj instanceof Fila<T>))
        if (this.getClass() != obj.getClass()) return false;

        Fila<X> p = (Fila<X>) obj;
        if (this.ultimo != p.ultimo) return false;
        for (int i = 0; i <= this.ultimo; i++)

            if (!this.elem[i].equals(p.elem[i]))
                return false;

        return true;
    }



    public int hashCode() {
        int ret = super.hashCode(); //quando herda explicitamente

        ret = ret * 7 + Integer.valueOf(this.ultimo).hashCode();

        for (int i = 0; i <= this.ultimo; i++)
            ret = ret * 7 + this.elem[i].hashCode();

        return ret;
    }



    public Fila (Fila<X> modelo) throws Exception 
    { 
        if (modelo==null) 
        throw new Exception ("Modelo nao fornecido"); 
        
        this.elem = new Object [modelo.elem.length]; 
        
        for (int i=0; i<=modelo.ultimo; i++) {
            this.elem[i] = modelo.elem[i]; 
        }
        this.ultimo = modelo.ultimo; 
    }
   
       public Object clone() {
           Fila<X> ret = null;
   
           try {
               ret = new Fila<X>(this);
           } catch (Exception erro) 
           {} 
   
           return ret;
       }

}
