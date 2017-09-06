package Homework8;


import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Library1 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("������� peopleCOunt");
        int peopleCount=scanner.nextInt();
        if(peopleCount<=0){System.out.println("������� ������� peopleCount!");}
        System.out.println("������� maxAmount");
        int maxAmount=scanner.nextInt();
        if(maxAmount<=0){System.out.println("������� ������� maxAmount!");}
        
        Random rand = new Random();

        Semaphore SEMAPHORE = new Semaphore(maxAmount);
        Semaphore Door = new Semaphore(1);
        
        for (int i =1;i<=peopleCount;i++) {
            final  int x =i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("["+x+"] ������ �� ����� � ����������");
                    try {
                        System.out.println("["+x+"] ������� � ����� � �����");
                        Door.acquire();
                        try {
                        	for(int i = 1;i<=2;i++){
                            Thread.sleep(250);
                            System.out.println("["+x+"] �������� ����� ����� ������");
                        	}
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            }
                        System.out.println("["+x+"] ������ ����� ����� ������");
                        if(SEMAPHORE.availablePermits()<=0){System.out.println("["+x+"] ����� � �������");}
                        SEMAPHORE.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("["+x+"] ����� � ����������");

                    try {
                    	int random = rand.nextInt(5000)+1000;
                  
                    	
                    	
                    	for(int i  = 1;i<=3;i++){
                        Thread.sleep(random/3);
                        System.out.println("["+x+"] ������ �����..." );
                    	}
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("["+x+"]������� � ����� ������� ");
                    
                    try {
                        Thread.sleep(500);
                        System.out.println("["+x+"] �������� ����� ����� ������");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        }
                    Door.release();
                    System.out.println("["+x+"] ������ ����� ����� ������");
                    
                   
                    SEMAPHORE.release();
                    System.out.println("["+x+"] �����");
                }
            }).start();
        }
    }


}