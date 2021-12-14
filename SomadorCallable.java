package ExemploThread;

import java.util.concurrent.Callable;

public class SomadorCallable implements Callable<Integer>{
	
	
	/** o Callable recebe como parâmetros na estrutura abaixo o array de inteiros, o inicio e o fim. 
	 * criamos uma variável para guardar a soma, e a aplicação vai executar a soma do inicio até o fim.
	 * Fizemos um contrutor que recebe os dados.  **/

		
		private int [] numeros;
		private int inicio;
		private int fim;
		
		public SomadorCallable(int[] numeros, int inicio, int fim) {
			super();
			this.numeros = numeros;
			this.inicio = inicio;
			this.fim = fim;
		}
		
		
		/** na linha abaixo nós criamos um try e um finally para sempre executar e então ele vai 
		 * mostrar o resultado final.**/

		public Integer call() throws Exception {
			try {
				int soma=0;
				for(int i =inicio; i<fim; i++) {
					soma+=numeros[i];
				}
				return soma;
			} finally {
				
				/** na linha abaixo nós podemos observar quando formos compilar o código, que além de ele apresentar
				 * o que está sendo executado, mostramos também qual Thread está sendo utilizada, utilizando a linha 
				 *Thread.currentThread().getId(). Cada ID é único para cada Thread criada, porém ela pode ser usada várias vezes.**/
				
				System.out.println("Thread" + Thread.currentThread().getId() + " - Executado callable de " + inicio + "até " + fim);
			}
		}

	}
