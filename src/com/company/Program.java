package com.company;

import java.util.concurrent.Semaphore;

public class Program {

    public static void main(String[] args) {

        Semaphore sem = new Semaphore(2);
        for(int i=1;i<3;i++)
            new Tunnel(sem,i).start();
    }
}
// класс тоннель
class Tunnel extends Thread
{
    Semaphore sem;

    int num = 0;
    // условный номер философа
    int id;
    // в качестве параметров конструктора передаем идентификатор тоннеля и семафор
    Tunnel(Semaphore sem, int id)
    {
        this.sem=sem;
        this.id=id;
    }

    public void run()
    {
        try
        {
            while(num<5)
            {
                //Запрашиваем у семафора разрешение на выполнение
                sem.acquire();
                System.out.println ("Тоннель " + id+" занят поездом");
                // поезд едет
                sleep(500);
                num++;
                System.out.println ("Тоннель " + id+" свободен");
                sem.release();
                sleep(500);
            }
        }
        catch(InterruptedException e)
        {
            System.out.println ("Ошибка");
        }
    }
}


