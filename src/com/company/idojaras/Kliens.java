package com.company.idojaras;

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
            DataOutputStream szervernek = new DataOutputStream(kapcsolat.getOutputStream());
            Scanner sc = new Scanner(System.in);
            int menu;
            do {
                System.out.println("Válasszon a menüpontok közül: ");
                System.out.println("1: megyék listázása");
                System.out.println("2: Napi maximum hőmérséklet");
                System.out.println("3: Napi minimum hőmérséklet");
                System.out.println("4: Holnapi maximum hőmérséklet");
                System.out.println("5: Holnapi minimum hőmérséklet");
                menu = sc.nextInt();
                szervernek.writeInt(menu);
                szervernek.flush();
                System.out.println(szervertol.readUTF());
            }
            while (menu != -1);


        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
