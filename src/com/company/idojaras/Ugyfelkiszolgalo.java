package com.company.idojaras;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Ugyfelkiszolgalo implements Runnable {
    private HashMap<String, Idojaras> elorejelzesek;
    Socket kapcsolat;

    public Ugyfelkiszolgalo(Socket kapcsolat) {
        this.kapcsolat = kapcsolat;
        elorejelzesek=new HashMap<>();
        Beolvas();
    }

    @Override
    public void run() {
        try {
            DataInputStream ugyfeltol = new DataInputStream(kapcsolat.getInputStream());
            DataOutputStream ugyfelnek = new DataOutputStream(kapcsolat.getOutputStream());

            int menu;
            Beolvas();
            String varos;
            do {
                varos = ugyfeltol.readUTF();
                menu = ugyfeltol.readInt();

                switch (menu){
                    case 0: ugyfelnek.writeUTF(megye(varos));
                        break;
                    case 1: ugyfelnek.writeUTF(napiMax(varos));
                        break;
                    case 2: ugyfelnek.writeUTF(napiMin(varos));
                        break;
                    case 3: ugyfelnek.writeUTF(holnapMax(varos));
                        break;
                    case 4: ugyfelnek.writeUTF(holnapMin(varos));
                        break;
                    default: ugyfelnek.writeUTF("Kilépés");
                }

                ugyfelnek.flush();
            } while (menu != 0);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void Beolvas() {
        try{
            BufferedReader br = new BufferedReader(new FileReader("weather.txt"));
            br.readLine();
            String sor = br.readLine();
            while (sor != null) {
                Idojaras i = new Idojaras(sor);
                String megye = i.getMegye();
                elorejelzesek.put(megye,i);
                sor = br.readLine();
            }

            for(Map.Entry<String, Idojaras> entry: elorejelzesek.entrySet()) {
                System.out.println(entry.getValue());
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String megye(String varos){
        Idojaras i = elorejelzesek.get(varos);
        return ""+i.getMegye();
    }
    private String napiMax(String varos){
        Idojaras i = elorejelzesek.get(varos);
        return ""+i.getMai().getMax();
    }
    private String napiMin(String varos){
        Idojaras i = elorejelzesek.get(varos);
        return ""+i.getMai().getMin();
    }
    private String holnapMin(String varos){
        Idojaras i = elorejelzesek.get(varos);
        return ""+i.getHolnapi().getMin();
    }
    private String holnapMax(String varos){
        Idojaras i = elorejelzesek.get(varos);
        return ""+i.getHolnapi().getMax();
    }
}
