import java.util.Stack;

public class TestarListaLIgada {
    public static void main(String[] args) {

        ListaLigada lista = new ListaLigada();





        System.out.println(lista);

        lista.adicionaNoComeco("Mauricio");
        System.out.println(lista);

        lista.adiciona("Cecília");
        System.out.println(lista);

        lista.adiciona("João");
        System.out.println(lista);


        System.out.println(lista.contem("Mauricio"));
        System.out.println(lista.contem("Danilo"));




        Stack<String> stack = new Stack<String>();

        stack.push("Mauricio");
        stack.push("Marcelo");

        System.out.println(stack);
        stack.pop();
        System.out.println(stack);

        String nome = stack.peek();
        System.out.println(nome);






    }
}