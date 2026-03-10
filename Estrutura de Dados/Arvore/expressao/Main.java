public class Main {
    public static void main(String[] args) {
        ArvoreExpressao arvore = new ArvoreExpressao();

        // Lista de expressões para teste
        String[] expressoes = {
            "3*(4+5^2)-8/2",      // ✅ Deve dar 83
            "2+3*4",              // ✅ 14
            "(2+3)*4",            // ✅ 20
            "10/(5-3)",           // ✅ 5
            "5^2^2",              // ✅ 625 (pois 5^(2^2) = 5^4)
            "8/2*(2+2)",          // ✅ 16
            "7+(6*5^2+3)",        // ✅ 7 + (6*25 + 3) = 160
            "9*(3+(8/(4-2)))",    // ✅ 9*(3+4) = 63
            "10/0",               // ❌ Erro de divisão por zero
            "4+*",                // ❌ Expressão inválida
            "(5+3",               // ❌ Parênteses faltando
            "5+3)",               // ❌ Parênteses extras
            "2^^3",               // ❌ Operador repetido
            "",                   // ❌ Vazio
            "2+2*2-2/2"           // ✅ 2+4-1 = 5
        };

        System.out.println("=== TESTE AUTOMÁTICO DE EXPRESSÕES ===");
        System.out.println("(com pilhas e árvore binária)\n");

        for (int i = 0; i < expressoes.length; i++) {
            String exp = expressoes[i];
            System.out.println("Teste " + (i+1) + ": " + exp);

            try {
                int resultado = arvore.processarExpressao(exp);
                System.out.println("✅ Resultado: " + resultado);
            } catch (ArithmeticException e) {
                System.out.println("❌ Erro: Divisão por zero.");
            } catch (Exception e) {
                System.out.println("❌ Erro: Expressão inválida ou sintaxe incorreta.");
            }

            System.out.println("------------------------------------");
        }
    }
}
