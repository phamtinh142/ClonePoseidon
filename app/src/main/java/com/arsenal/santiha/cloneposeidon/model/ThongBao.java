package com.arsenal.santiha.cloneposeidon.model;

public class ThongBao {
    private String gio, key, ngay, noi_dung,uid;

    public ThongBao() {
    }

    public ThongBao(String gio, String key, String ngay, String noi_dung, String uid) {
        this.gio = gio;
        this.key = key;
        this.ngay = ngay;
        this.noi_dung = noi_dung;
        this.uid = uid;
    }

    public String getGio() {
        return gio;
    }

    public void setGio(String gio) {
        this.gio = gio;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getNoi_dung() {
        return noi_dung;
    }

    public void setNoi_dung(String noi_dung) {
        this.noi_dung = noi_dung;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
