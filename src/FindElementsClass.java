import java.util.Random;

public class FindElementsClass {
    private static int[] mass = new int[100];
    private volatile static int quantityElem = 0;
    private final static int FIND = 100;

    public static void main(String[] args) {
        Random rand = new Random();
        for (int i = 0; i < mass.length; i++) {
            mass[i] = rand.nextInt(101);
            System.out.print(mass[i] + " ");
            if ((i + 1) % 10 == 0) {
                System.out.println();
            }
        }
        if (mass.length < 10) System.out.println();

        FindElementsClass obj = new FindElementsClass();
        Thread thread1 = new Thread(obj::frontSearch);
        Thread thread2 = new Thread(obj::backSearch);
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("Number of element: " + quantityElem);
    }

    public void frontSearch() {
        int counter = 0;
        for (int i = 0; i <= mass.length / 2; i++) {
            if (mass[i] == FIND) {
                counter++;
            }
        }
        synchronized (this){
            quantityElem += counter;
        }
    }

    public void backSearch() {
        int counter = 0;
        for (int i = mass.length - 1; i > mass.length / 2; i--) {
            if (mass[i] == FIND) {
                counter++;
            }
        }
        synchronized (this){
            quantityElem += counter;
        }
    }
}
