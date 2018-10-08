package com.arsenal.santiha.cloneposeidon.model;

public class DoAn {
    private String ten_mon;
    private String hinh_anh;
    private long gia;

    public DoAn() {
    }

    public DoAn(String ten_mon, String hinh_anh, long gia) {
        this.ten_mon = ten_mon;
        this.hinh_anh = hinh_anh;
        this.gia = gia;
    }

    public String getTen_mon() {
        return ten_mon;
    }

    public void setTen_mon(String ten_mon) {
        this.ten_mon = ten_mon;
    }

    public String getHinh_anh() {
        return hinh_anh;
    }

    public void setHinh_anh(String hinh_anh) {
        this.hinh_anh = hinh_anh;
    }

    public long getGia() {
        return gia;
    }

    public void setGia(long gia) {
        this.gia = gia;
    }
}
