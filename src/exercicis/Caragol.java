package exercicis;

import java.util.Random;

public class Caragol implements Runnable {
	private String nom;
	private double velocitat;

	public Caragol(String nom, double velocitat) {
		this.nom = nom;
		this.velocitat = velocitat;
	}

	@Override
	public void run() {
		double distancia = 0;
		Random random = new Random();
		while (distancia < 1) {
			distancia += velocitat * random.nextDouble();
			if (distancia > 1) {
				distancia = 1; // Asegurarse de no pasar de la meta.
			}
			mostrarCarrera(distancia);
			try {
				Thread.sleep(1000); // Simulación de tiempo para mostrar el progreso
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(nom + " ha llegado a la meta!");
	}

	private void mostrarCarrera(double distancia) {
		int progreso = (int) (distancia * 20); // 20 caracteres para representar 1 metro
		StringBuilder barra = new StringBuilder();
		for (int i = 0; i < progreso; i++) {
			barra.append("=");
		}
		System.out.println(nom + ": " + barra);
	}

	public static void main(String[] args) {
		Thread[] caracoles = new Thread[5];
		caracoles[0] = new Thread(new Caragol("Caracol 1", 0.1));
		caracoles[1] = new Thread(new Caragol("Caracol 2", 0.15));
		caracoles[2] = new Thread(new Caragol("Caracol 3", 0.2));
		caracoles[3] = new Thread(new Caragol("Caracol 4", 0.12));
		caracoles[4] = new Thread(new Caragol("Caracol 5", 0.18));

		int[] prioridades = { 1, 2, 3, 4, 10 };

		int index = 0;
		for (Thread caracol : caracoles) {
			caracol.setPriority(prioridades[index]);
			caracol.start();
			index += 1;
		}
	}
}
