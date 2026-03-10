import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static String removerEspacos(String st) {
        // Cria o StringTokenizer usando a string passada como parâmetro  
              BufferedReader teclado = new BufferedReader(
                new InputStreamReader(System.in));

        System.out.print("Digite a expressão: ");
        String expressao = teclado.readLine();
        VetorNo<String> v = new VetorNo<>();
        StringTokenizer st = new StringTokenizer(expressao," ",true);


         while (st.hasMoreTokens()) {
            resultado = v.removerEspacos(expressao);
            resultado.append(st.nextToken());
        }
        return resultado.toString();
        System.out.println("Sem espaços: " + semEspacos);
        
        
    }

}
