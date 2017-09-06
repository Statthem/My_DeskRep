package Homework8;


import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Library1 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите peopleCOunt");
        int peopleCount=scanner.nextInt();
        if(peopleCount<=0){System.out.println("Неверно введено peopleCount!");}
        System.out.println("Введите maxAmount");
        int maxAmount=scanner.nextInt();
        if(maxAmount<=0){System.out.println("Неверно введено maxAmount!");}
        
        Random rand = new Random();

        Semaphore SEMAPHORE = new Semaphore(maxAmount);
        Semaphore Door = new Semaphore(1);
        
        for (int i =1;i<=peopleCount;i++) {
            final  int x =i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("["+x+"] Пришел ко входу в библиотеку");
                    try {
                        System.out.println("["+x+"] Подошел к двери с улицы");
                        Door.acquire();
                        try {
                        	for(int i = 1;i<=2;i++){
                            Thread.sleep(250);
                            System.out.println("["+x+"] Проходит через дверь внутрь");
                        	}
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            }
                        System.out.println("["+x+"] Прошел через дверь внутрь");
                        if(SEMAPHORE.availablePermits()<=0){System.out.println("["+x+"] Стоит в очереди");}
                        SEMAPHORE.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("["+x+"] Вошел в библеотеку");

                    try {
                    	int random = rand.nextInt(5000)+1000;
                  
                    	
                    	
                    	for(int i  = 1;i<=3;i++){
                        Thread.sleep(random/3);
                        System.out.println("["+x+"] Читает книгу..." );
                    	}
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("["+x+"]Подошел к двери изнутри ");
                    
                    try {
                        Thread.sleep(500);
                        System.out.println("["+x+"] Проходит через дверь наружу");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        }
                    Door.release();
                    System.out.println("["+x+"] Прошел через дверь наружу");
                    
                   
                    SEMAPHORE.release();
                    System.out.println("["+x+"] Вышел");
                }
            }).start();
        }
    }


}