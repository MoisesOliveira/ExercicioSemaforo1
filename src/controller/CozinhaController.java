package controller;

import java.util.concurrent.Semaphore;

public class CozinhaController extends Thread{

	private static int id = 0;
	private Semaphore semaforo;
	
	public CozinhaController(Semaphore semaforo) {
		id = id + 1;
		this.semaforo = semaforo;
	}
	
	@Override
	public void run() {
		int resto = resto();
		
		if(resto == 0) {
			inicio("Sopa de cebola");
			prepara(500, "Sopa de cebola");
			try {
				semaforo.acquire();
				entrega("Sopa de cebola");

			} catch (Exception e) {
			}
			semaforo.release();
		}
		if(resto != 0) {
			inicio("Lasanha a Bolonesa");
			prepara(700, "Lasanha a Bolonesa");
			try {
				semaforo.acquire();
				entrega("Lasanha a Bolonesa");

			} catch (Exception e) {
			}
			semaforo.release();
		}

	}

	public void inicio(String nome) {
		System.out.println("Iniciando prato: " + nome);
	}
	
	public void prepara(int tempo, String nome) {
		
		int temp = 0;
		
		while(tempo >= temp) {
			System.out.println("Preparando: " + nome + " -- " + "Percentual: " + (temp / 100) + "/" + (tempo/100));
//			System.out.println("Percentual: " + (temp / 100) + "/" + (tempo/100));
			temp = temp + 100;
			dorme(100);
			
		}
	}
	
	public void dorme(int tempo) {
		try {
			sleep(tempo);
		} 
		
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void entrega(String nome) {
		
		System.out.println("Entregando prato: " + nome);	
		dorme(500);
	}
	
	public int resto() {
		return id % 2;
	}
	
}
