public class Main {
    public static void main(String[] args) throws Exception {
        Pilha<Integer> pilha = new Pilha<Integer>(10);
        
        System.out.println("Adicionando item 10");
        pilha.guardeUmItem(10);

        System.out.println("Adicionando item 20");
        pilha.guardeUmItem(20);

        System.out.println("Pilha após adicionar 10 e 20: " + pilha);

        System.out.println("Recuperando item: " + pilha.recupereUmItem());

        System.out.println("Removendo item...");
        pilha.removaUmItem();

        System.out.println("Pilha após remoção: " + pilha); 

        System.out.println("Pilha cheia " + pilha.isCheio());

        System.out.println("Pilha vazia " + pilha.isVazio());       
    }
}
