package com.company.feladat02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Kliens {

    public static void main(String[] args) {
        try {
            Socket kapcsolat = new Socket("localhost", 8080);
            DataInputStream szervertol = new DataInputStream(kapcsolat.getInputStream());
            DataOutputStream szerverre = new DataOutputStream(kapcsolat.getOutputStream());
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("Kérem a(z) a oldalt:  ");
                int a = sc.nextInt();
                szerverre.writeInt(a);
                szerverre.flush();
                System.out.println("Kérem a(z) b oldalt:  ");
                int b = sc.nextInt();
                szerverre.writeInt(b);
                szerverre.flush();

                int menu;
                do {
                    System.out.println("Válasszon az alábbi lehetőségek közül: ");
                    System.out.println("1) Kerület \n 2) Terület \n 3) Négyzet-e \n 4) Átló mérete \n 5) Kilépés");
                    menu = sc.nextInt();
                    szerverre.writeInt(menu);
                    szerverre.flush();
                    System.out.println(szervertol.readUTF());
                } while (menu != 5);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
