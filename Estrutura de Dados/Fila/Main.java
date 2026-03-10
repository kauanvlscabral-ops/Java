public class Main {
    public static void main(String[] args) {
        try {
            Fila<Integer> fila = new Fila<>(1000001);
            for (int i = 0; i < 1000000; i++) {
                fila.inclua(i);
            }
            System.out.println("Foram adicionados 1.000.000 de itens!!");

            for (int i = 0; i < 1000000; i++) {
                fila.removaUmItem();
            }

            System.out.println("1.000.000 removidos!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
