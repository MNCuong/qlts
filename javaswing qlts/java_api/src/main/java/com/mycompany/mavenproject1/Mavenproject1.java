/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mavenproject1;

import API.ChiTietHoaDon_API;
import API.QL_HoaDon_API;
import API.QL_Menu_API;
import API.QL_NguyenLieu_API;
import API.QL_NhanVien_API;
import API.ThongKe_API;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author ADMIN
 */
public class Mavenproject1 {
    public static PreparedStatement ps;
    public static ResultSet rs;
    public static Statement st;
    
    public static void main(String[] args){
        ThongKe_API tk = new ThongKe_API();
        QL_NhanVien_API qlnv = new QL_NhanVien_API();
        QL_NguyenLieu_API qlnl = new QL_NguyenLieu_API();
        QL_HoaDon_API qlhd = new QL_HoaDon_API();
        QL_Menu_API qlmn = new QL_Menu_API();
        ChiTietHoaDon_API cthd = new ChiTietHoaDon_API();
    }
}

