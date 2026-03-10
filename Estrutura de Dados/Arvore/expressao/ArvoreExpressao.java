import java.util.StringTokenizer;

public class ArvoreExpressao {
    private class No {
        private No esq;
        private String info;
        private No dir;

        public No(String i) {
            this.info = i;
            this.esq = null;
            this.dir = null;
        }

        public No(No e, String i, No d) {
            this.esq = e;
            this.info = i;
            this.dir = d;
        }

        public boolean ehNumero() {
            StringTokenizer st = new StringTokenizer(info, " ");
            if (st.hasMoreTokens()) {
                String token = st.nextToken();
                try {
                    Integer.parseInt(token);
                    return true;
                } catch (NumberFormatException e) {
                    return false;
                }
            }
            return false;
        }

        public String getInfo() {
            return this.info;
        }

        public No getEsq() {
            return this.esq;
        }

        public No getDir() {
            return this.dir;
        }
    }

    private No raiz;

    public ArvoreExpressao() {
        this.raiz = null;
    }

    private int precedencia(String op) {
        if (op.equals("+") || op.equals("-")) return 1;
        if (op.equals("*") || op.equals("/")) return 2;
        if (op.equals("^")) return 3;
        return 0;
    }

    private boolean operador(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") ||
               s.equals("/") || s.equals("^");
    }

    private String removerEspacos(String s) {
        StringTokenizer st = new StringTokenizer(s, " ");
        String nova = "";
        while (st.hasMoreTokens()) {
            nova += st.nextToken();
        }
        return nova;
    }

    public void montarArvore(String expressao) throws Exception {
        expressao = removerEspacos(expressao);
        Pilha<No> valores = new Pilha<>(100);
        Pilha<String> operadores = new Pilha<>(100);

        StringTokenizer st = new StringTokenizer(expressao, "+-*/()^", true);

        while (st.hasMoreTokens()) {
            String token = st.nextToken();

            if (token.equals(" ")) continue;

            if (token.equals("(")) {
                operadores.guardeUmItem(token);
            } else if (token.equals(")")) {
                while (!operadores.isVazia() && !operadores.recupereUmItem().equals("(")) {
                    String op = operadores.recupereUmItem();
                    operadores.removaUmItem();

                    No dir = valores.recupereUmItem();
                    valores.removaUmItem();
                    No esq = valores.recupereUmItem();
                    valores.removaUmItem();

                    valores.guardeUmItem(new No(esq, op, dir));
                }
                operadores.removaUmItem(); // remove "("
            } else if (operador(token)) {
                while (!operadores.isVazia() &&
                       precedencia(operadores.recupereUmItem()) >= precedencia(token)) {
                    String op = operadores.recupereUmItem();
                    operadores.removaUmItem();

                    No dir = valores.recupereUmItem();
                    valores.removaUmItem();
                    No esq = valores.recupereUmItem();
                    valores.removaUmItem();

                    valores.guardeUmItem(new No(esq, op, dir));
                }
                operadores.guardeUmItem(token);
            } else {
                valores.guardeUmItem(new No(token));
            }
        }

        while (!operadores.isVazia()) {
            String op = operadores.recupereUmItem();
            operadores.removaUmItem();

            No dir = valores.recupereUmItem();
            valores.removaUmItem();
            No esq = valores.recupereUmItem();
            valores.removaUmItem();

            valores.guardeUmItem(new No(esq, op, dir));
        }

        raiz = valores.recupereUmItem();
    }

    private int avaliar(No n) {
        if (n.ehNumero())
            return Integer.parseInt(n.getInfo());

        int e = avaliar(n.getEsq());
        int d = avaliar(n.getDir());

        switch (n.getInfo()) {
            case "+": return e + d;
            case "-": return e - d;
            case "*": return e * d;
            case "/": return e / d;
            case "^":
                int pot = 1;
                for (int i = 0; i < d; i++)
                    pot *= e;
                return pot;
        }
        return 0;
    }

    public int processarExpressao(String exp) throws Exception {
        montarArvore(exp);
        return avaliar(raiz);
    }


    public void mostrarArvore() {
        mostrarArvore(raiz, 0);
    }

    private void mostrarArvore(No n, int nivel) {
        if (n == null) return;
        mostrarArvore(n.getDir(), nivel + 1);
        for (int i = 0; i < nivel; i++) System.out.print("   ");
        System.out.println(n.getInfo());
        mostrarArvore(n.getEsq(), nivel + 1);
    }
}
