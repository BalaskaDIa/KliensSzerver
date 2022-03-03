package com.company.feladat03;

import java.util.ArrayList;

public class Indian {

    private String nev;
    private String torzs;
    private String nem;
    private int eletkor;
    private ArrayList<String> tulajdonok;

    public Indian(String sor) {
        String [] adatok = sor.split(";");
        this.nev = adatok[0];
        this.torzs = adatok[1];
        this.nem = adatok[2];
        this.eletkor = Integer.parseInt(adatok[3]);
        this.tulajdonok = tulajdonok;
    }

    public String getNev() {
        return nev;
    }

    public String getTorzs() {
        return torzs;
    }

    public String getNem() {
        return nem;
    }

    public int getEletkor() {
        return eletkor;
    }

    public ArrayList<String> getTulajdonok() {
        return tulajdonok;
    }

    @Override
    public String toString() {
        String s = " ";
        for (String item: this.tulajdonok) {
            s += item + " ";
        }
        return nev + " '" + torzs +
                ", " + nem +
                ", " + eletkor + 5;
    }
}
