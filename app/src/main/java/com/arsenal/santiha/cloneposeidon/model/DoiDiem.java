package com.arsenal.santiha.cloneposeidon.model;

public class DoiDiem {
    private String hinh_anh;
    private String ten_mon;
    private long so_diem;

    public DoiDiem() {
    }

    public DoiDiem(String hinh_anh, String ten_mon, long so_diem) {
        this.hinh_anh = hinh_anh;
        this.ten_mon = ten_mon;
        this.so_diem = so_diem;
    }

    public String getHinh_anh() {
        return hinh_anh;
    }

    public void setHinh_anh(String hinh_anh) {
        this.hinh_anh = hinh_anh;
    }

    public String getTen_mon() {
        return ten_mon;
    }

    public void setTen_mon(String ten_mon) {
        this.ten_mon = ten_mon;
    }

    public long getSo_diem() {
        return so_diem;
    }

    public void setSo_diem(long so_diem) {
        this.so_diem = so_diem;
    }
}
