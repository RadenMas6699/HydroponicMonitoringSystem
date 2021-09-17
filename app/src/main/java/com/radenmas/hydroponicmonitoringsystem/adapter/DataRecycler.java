package com.radenmas.hydroponicmonitoringsystem.adapter;

public class DataRecycler {

    int tempRuang;
    int humRuang;
    float phAir;
    int tdsAir;
    int tempAir;
    long time;

    public DataRecycler() {
    }

    public DataRecycler(int tempRuang, int humRuang, float phAir, int tdsAir, int tempAir, long time) {
        this.tempRuang = tempRuang;
        this.humRuang = humRuang;
        this.phAir = phAir;
        this.tdsAir = tdsAir;
        this.tempAir = tempAir;
        this.time = time;
    }

    public int getTempRuang() {
        return tempRuang;
    }

    public int getHumRuang() {
        return humRuang;
    }

    public float getPhAir() {
        return phAir;
    }

    public int getTdsAir() {
        return tdsAir;
    }

    public int getTempAir() {
        return tempAir;
    }

    public long getTime() {
        return time;
    }
}