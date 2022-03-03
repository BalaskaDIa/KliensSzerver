package com.company.feladat03;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Ugyfelkiszolgalo implements Runnable{

    Socket kapcsolat;
    private ArrayList<Indian> lista;

    public Ugyfelkiszolgalo(Socket kapcsolat) {
        this.kapcsolat = kapcsolat;
    }

    @Override
    public void run() {
        try {
            DataInputStream ugyfeltol = new DataInputStream(kapcsolat.getInputStream());
            DataOutputStream ugyfelnek = new DataOutputStream(kapcsolat.getOutputStream());
            Scanner sc = new Scanner(System.in);
            int menu;
            do {
                menu = ugyfeltol.readInt();
                switch (menu) {
                    case 1:
                        String s = "";
                        for (Indian item: lista) {
                            s += item+"\n";
                        }
                        ugyfelnek.writeUTF(s);
                        ugyfelnek.flush();
                        break;
                }



                menu = sc.nextInt();
            }
            while (menu != -1);

        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    private void beolvasas() {
        lista = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("indianok.txt"));
            String sor = br.readLine();
            while (sor != null) {
                lista.add(new Indian(sor));
                sor = br.readLine();
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
