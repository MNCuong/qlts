/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;

/**
 *
 * @author Administrator
 */
public class HoaDon {
    private String MaHD;
    private String TenNhanVien;
    private Date NgayLap;
    private String MaBan;
    private int ThanhTien;
    private String TinhTrang;

    public String getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(String TinhTrang) {
        this.TinhTrang = TinhTrang;
    }

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }

    public String getTenNhanVien() {
        return TenNhanVien;
    }

    public void setTenNhanVien(String TenNhanVien) {
        this.TenNhanVien = TenNhanVien;
    }

    public Date getNgayLap() {
        return NgayLap;
    }

    public void setNgayLap(Date NgayLap) {
        this.NgayLap = NgayLap;
    }

    public String getMaBan() {
        return MaBan;
    }

    public void setMaBan(String MaBan) {
        this.MaBan = MaBan;
    }

    public int getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(int ThanhTien) {
        this.ThanhTien = ThanhTien;
    }
    
    public HoaDon(String MaHD, String TenNhanVien, Date NgayLap, String MaBan, int ThanhTien) {
        this.MaHD = MaHD;
        this.TenNhanVien = TenNhanVien;
        this.NgayLap = NgayLap;
        this.MaBan = MaBan;
        this.ThanhTien = ThanhTien;
    }
    
    public HoaDon() {
    }
    
}
