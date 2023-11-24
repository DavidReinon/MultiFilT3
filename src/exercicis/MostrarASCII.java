package exercicis;

public class MostrarASCII implements Runnable {
    private int tipus;

    public MostrarASCII(int tipus) {
        this.tipus = tipus;
    }

    @Override
    public void run() {
        if (tipus == 1) {
            for (int i = 1; i <= 256; i += 2) {
                System.out.print("Caracter impar: "+ (char) i + " ");
            }
        } else if (tipus == 2) {
            for (int i = 2; i <= 256; i += 2) {
                System.out.print("Caracter par: "+ (char) i + " ");
            }
        }
    }

    public static void main(String[] args) {
        Thread oddThread = new Thread(new MostrarASCII(1));
        Thread evenThread = new Thread(new MostrarASCII(2));

        oddThread.start();
        evenThread.start();
    }
}
