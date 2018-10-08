package com.arsenal.santiha.cloneposeidon.model;

public class User {
    private String ho_ten;
    private String ngay_sinh;
    private String so_dien_thoai;

    public User() {

    }

    public User(String ho_ten, String ngay_sinh, String so_dien_thoai) {
        this.ho_ten = ho_ten;
        this.ngay_sinh = ngay_sinh;
        this.so_dien_thoai = so_dien_thoai;
    }

    public String getHo_ten() {
        return ho_ten;
    }

    public void setHo_ten(String ho_ten) {
        this.ho_ten = ho_ten;
    }

    public String getNgay_sinh() {
        return ngay_sinh;
    }

    public void setNgay_sinh(String ngay_sinh) {
        this.ngay_sinh = ngay_sinh;
    }

    public String getSo_dien_thoai() {
        return so_dien_thoai;
    }

    public void setSo_dien_thoai(String so_dien_thoai) {
        this.so_dien_thoai = so_dien_thoai;
    }
}
