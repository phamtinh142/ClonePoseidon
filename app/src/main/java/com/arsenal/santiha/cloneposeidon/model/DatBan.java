package com.arsenal.santiha.cloneposeidon.model;

public class DatBan {
    private String ma_dat_ban;
    private String uid;
    private String ten;
    private long so_dien_thoai;
    private long so_nguoi;
    private String ngay_dat_hang;
    private String gio_den;
    private String ghi_chu;

    public DatBan() {

    }

    public DatBan(String ma_dat_ban,String uid, String ten, long so_dien_thoai, long so_nguoi, String ngay_dat_hang, String gio_den, String ghi_chu) {
        this.ma_dat_ban = ma_dat_ban;
        this.uid = uid;
        this.ten = ten;
        this.so_dien_thoai = so_dien_thoai;
        this.so_nguoi = so_nguoi;
        this.ngay_dat_hang = ngay_dat_hang;
        this.gio_den = gio_den;
        this.ghi_chu = ghi_chu;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public long getSo_dien_thoai() {
        return so_dien_thoai;
    }

    public void setSo_dien_thoai(long so_dien_thoai) {
        this.so_dien_thoai = so_dien_thoai;
    }

    public long getSo_nguoi() {
        return so_nguoi;
    }

    public void setSo_nguoi(long so_nguoi) {
        this.so_nguoi = so_nguoi;
    }

    public String getNgay_dat_hang() {
        return ngay_dat_hang;
    }

    public void setNgay_dat_hang(String ngay_dat_hang) {
        this.ngay_dat_hang = ngay_dat_hang;
    }

    public String getGio_den() {
        return gio_den;
    }

    public void setGio_den(String gio_den) {
        this.gio_den = gio_den;
    }

    public String getGhi_chu() {
        return ghi_chu;
    }

    public void setGhi_chu(String ghi_chu) {
        this.ghi_chu = ghi_chu;
    }

    public String getMa_dat_ban() {
        return ma_dat_ban;
    }

    public void setMa_dat_ban(String ma_dat_ban) {
        this.ma_dat_ban = ma_dat_ban;
    }
}
