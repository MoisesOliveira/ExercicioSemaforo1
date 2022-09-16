package view;

import java.util.concurrent.Semaphore;

import controller.CozinhaController;

public class Main {

	public static void main(String[] args) {

		CozinhaController[] vetor = new CozinhaController[5];
		Semaphore semaforo = new Semaphore(1);
		
		for(int i = 0; i < 5; i++) {
			vetor[i] = new CozinhaController(semaforo);
			vetor[i].start();
		}
	}

}
