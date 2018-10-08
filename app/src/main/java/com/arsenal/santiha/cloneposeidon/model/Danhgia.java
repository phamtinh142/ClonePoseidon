package com.arsenal.santiha.cloneposeidon.model;

public class Danhgia {
    String tieu_de;
    String noi_dung;
    long so_diem;

    public Danhgia() {

    }

    public Danhgia(String tieu_de, String noi_dung, long so_diem) {
        this.tieu_de = tieu_de;
        this.noi_dung = noi_dung;
        this.so_diem = so_diem;
    }

    public String getTieu_de() {
        return tieu_de;
    }

    public void setTieu_de(String tieu_de) {
        this.tieu_de = tieu_de;
    }

    public String getNoi_dung() {
        return noi_dung;
    }

    public void setNoi_dung(String noi_dung) {
        this.noi_dung = noi_dung;
    }

    public long getSo_diem() {
        return so_diem;
    }

    public void setSo_diem(long so_diem) {
        this.so_diem = so_diem;
    }
}
