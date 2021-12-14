package ExemploThread;

import java.util.concurrent.Callable;

public class SomadorCallable implements Callable<Integer>{
	
	
	/** o Callable recebe como par�metros na estrutura abaixo o array de inteiros, o inicio e o fim. 
	 * criamos uma vari�vel para guardar a soma, e a aplica��o vai executar a soma do inicio at� o fim.
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
		
		
		/** na linha abaixo n�s criamos um try e um finally para sempre executar e ent�o ele vai 
		 * mostrar o resultado final.**/

		public Integer call() throws Exception {
			try {
				int soma=0;
				for(int i =inicio; i<fim; i++) {
					soma+=numeros[i];
				}
				return soma;
			} finally {
				
				/** na linha abaixo n�s podemos observar quando formos compilar o c�digo, que al�m de ele apresentar
				 * o que est� sendo executado, mostramos tamb�m qual Thread est� sendo utilizada, utilizando a linha 
				 *Thread.currentThread().getId(). Cada ID � �nico para cada Thread criada, por�m ela pode ser usada v�rias vezes.**/
				
				System.out.println("Thread" + Thread.currentThread().getId() + " - Executado callable de " + inicio + "at� " + fim);
			}
		}

	}
