package exercicis;

public class Contador implements Runnable {
	private String nomFil;
	private int iniciComptador;
	private int limitComptador;

	public Contador(String nomFil, int iniciComptador, int limitComptador) {
		this.nomFil = nomFil;
		this.iniciComptador = iniciComptador;
		this.limitComptador = limitComptador;
	}

	@Override
	public void run() {
		for (int i = iniciComptador; i <= limitComptador; i++) {
			System.out.println(nomFil + ": " + i);
		}
	}

	public static void main(String[] args) {
		Thread[] threads = new Thread[5];
		threads[0] = new Thread(new Contador("Hilo 1", 1, 20));
		threads[1] = new Thread(new Contador("Hilo 2", 100, 120));
		threads[2] = new Thread(new Contador("Hilo 3", 1000, 1020));
		threads[3] = new Thread(new Contador("Hilo 4", 10000, 10020));
		threads[4] = new Thread(new Contador("Hilo 5", 100000, 100020));

		for (Thread thread : threads) {
			thread.start();
		}
	}
}
