package ExemploThread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SomadorPrincipal {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		System.out.println("Iniciando a geração  do array....");
		
		/** a estrutura a seguir cria 10 Callables de 0 a 100 indo de 10 em 10. Aqui eu utilizei um numero pequeno, porém
		 * se fosse um numero de tamanho maior, como por exemplo 100 Threads 0 a 10.000 indo de 100 em 100,
		 * poderíamos observar que uma mesma Thread poderia ser utilizada para fazer uma soma diferente. 
		 * O ID da Thread é único, porém pode ser utilizada por várias vezes graças ao ExecuteService que faz essa reutilização.
		 * O numero que será gerado é aleatório, e para isso utilizamos o Random.**/
	   
		int [] numeros = new int[100];
		
		Random r = new Random(System.currentTimeMillis());
		for(int i =0; i<numeros.length; i++) {
			numeros [i] = Math.abs(r.nextInt()) % 10;
				
		}
		
		System.out.println("Geração do array finalizada....");
		
		/** utilizamos o arraylist para criar uma lista de objetos dos callables criados, 
		 * colocamos o future para armazenar a promessa de que os callables serão executados**/
		
		ExecutorService e = Executors.newCachedThreadPool();
		List<Future<Integer>> futures = new ArrayList<>();
		
		System.out.println("Execução de Callables Iniciada....");
		
		for(int i=0; i<10; i++) {
			Future<Integer> result = e.submit(new SomadorCallable(numeros, i*10, (i+1)*10 ));
			futures.add(result);
		}
		
		System.out.println("Callables executados....");
		
		/** a estrutura abaixo é responsável pela soma das Threads criadas, ela executa a soma e depois mostra 
		 * o resultado. É interessante ressaltar que a aplicação vai somar no final, depois que todas as Threads
		 * forem executadas, ela nunca vai executar uma Thread depois da soma.**/
		
		System.out.println("Iniciando a soma....");
		int soma=0;
		
		for(Future<Integer> result: futures ) {
			soma+=result.get();
		}
		System.out.println("Soma = " + soma);
		
		/** para evitar que se re-utilize as mesmas Threads criadas anteriormente, nós usamos o shutdown
		 * assim evitamos que ele crie varias Threads além das anteriores que ainda estarão ativas**/
		
		e.shutdown();

	}

}
