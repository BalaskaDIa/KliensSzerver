package com.company.feladat02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Ugyfelkiszolgalo implements Runnable{
    Socket kapcsolat;

    public Ugyfelkiszolgalo(Socket kapcsolat) {
        this.kapcsolat = kapcsolat;
    }

    @Override
    public void run() {
        try {
            DataInputStream ugyfeltol = new DataInputStream(kapcsolat.getInputStream());
            DataOutputStream ugyfelre = new DataOutputStream(kapcsolat.getOutputStream());

            while (true) {
                int a = ugyfeltol.readInt();
                int b = ugyfeltol.readInt();
                int menu;
                do {
                    menu = ugyfeltol.readInt();
                    switch (menu) {
                        case 1: ugyfelre.writeUTF(kerulet(a,b)); break;
                        case 5: ugyfelre.writeUTF("Ön a kilépést válaszotta"); break;
                    }
                } while (menu != 5);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String kerulet(int a, int b) {
        return "A téglalap kerülete: " + 2*a+b;
    }
}
