package com.radenmas.system.monitoring.hydroponic.adapter;

public class DataPoints {
    int tempRuang;
    int humRuang;
    float phAir;
    int tdsAir;
    int tempAir;
    long time;

    public DataPoints() {
    }

    public DataPoints(int tempRuang, int humRuang, float phAir, int tdsAir, int tempAir, long time) {
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

    public void setTempRuang(int tempRuang) {
        this.tempRuang = tempRuang;
    }

    public int getHumRuang() {
        return humRuang;
    }

    public void setHumRuang(int humRuang) {
        this.humRuang = humRuang;
    }

    public float getPhAir() {
        return phAir;
    }

    public void setPhAir(float phAir) {
        this.phAir = phAir;
    }

    public int getTdsAir() {
        return tdsAir;
    }

    public void setTdsAir(int tdsAir) {
        this.tdsAir = tdsAir;
    }

    public int getTempAir() {
        return tempAir;
    }

    public void setTempAir(int tempAir) {
        this.tempAir = tempAir;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
