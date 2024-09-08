/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.ChiTietHoaDon;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ad
 */
public class ChiTietHoaDonCtrl {
    public static PreparedStatement ps;
    public static ResultSet rs;
    public static Statement st;
    private static connectDatabase DBCtrl = new connectDatabase();
    private ArrayList<ChiTietHoaDon> arr = new ArrayList();
    private static Connection conn = DBCtrl.TaoKetNoi();
    
    public ChiTietHoaDonCtrl() {}
    public String UpdateMon(String MaHD,String MaDV,int SoLuong,int DonGia) throws SQLException {
            String sql = "UPDATE chitiethoadon SET SoLuong=?,DonGia=? WHERE MaHD=? AND MaDV=?";
            try {
                ps = connectDatabase.TaoKetNoi().prepareStatement(sql);
                ps.setInt(1, SoLuong);
                ps.setInt(2, DonGia);
                ps.setString(3, MaHD);
                ps.setString(4, MaDV);
                ps.execute();
                ps.close();
                return "Ðã sửa món thành công!";
            } catch (HeadlessException | SQLException e) {
                return e.getMessage();
            }
    }
    public String InsertMon(String MaHD,String MaDV,int SoLuong,int DonGia) throws SQLException {
        if(MaDV.contains("NL")){
            NguyenLieuCtrl nl = new NguyenLieuCtrl();
            nl.updateSoLuongNL(MaDV, SoLuong);
        } else {
            MenuCtrl mn = new MenuCtrl();
            mn.updateSoLuongM(MaDV, SoLuong);
        }
        ps = connectDatabase.TaoKetNoi().prepareStatement("select SoLuong,DonGia from chitiethoadon where MaDV=? and MaHD=?");
        ps.setString(1, MaDV);
        ps.setString(2, MaHD);
        rs = ps.executeQuery();
        if (rs.next()) {
            int n = rs.getInt("Soluong");
            int g = rs.getInt("DonGia");
            ps.close();
            return UpdateMon(MaHD, MaDV, SoLuong+n, DonGia+g);
        }
        
        String sql = "INSERT INTO chitiethoadon (`MaHD`, `MaDV`, `SoLuong`, `DonGia`) VALUES (?,?,?,?)";
        try {
            ps = connectDatabase.TaoKetNoi().prepareStatement(sql);
            ps.setString(1, MaHD);
            ps.setString(2, MaDV);
            ps.setInt(3, SoLuong);
            ps.setInt(4, DonGia);
            ps.execute();
            ps.close();
            return "Ðã thêm món thành công!";
        } catch (HeadlessException | SQLException e) {
            return e.getMessage();
        }
    }
    public String DeleteMon(String ID) throws SQLException {
        ps = connectDatabase.TaoKetNoi().prepareStatement("select MaDV,SoLuong from chitiethoadon where WHERE STT = ?");
        ps.setString(1, ID);
        rs = ps.executeQuery();
        rs.next();
        String MaDV = rs.getString("MaDV");
        int SoLuong = rs.getInt("Soluong");
        if(MaDV.contains("NL")){
            NguyenLieuCtrl nl = new NguyenLieuCtrl();
            nl.updateSoLuongNL(MaDV, SoLuong*(-1));
        } else {
            MenuCtrl mn = new MenuCtrl();
            mn.updateSoLuongM(MaDV, SoLuong*(-1));
        }
        
        try {
            ps = conn.prepareStatement("DELETE FROM chitiethoadon WHERE STT = ?");
            ps.setString(1, ID);
            ps.executeUpdate();
            ps.close();
            ps.close();
            return "Xóa thành công";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }
    public ArrayList<ChiTietHoaDon> createArr(String MaHD) throws SQLException{
        arr = new ArrayList<>();
        ps = connectDatabase.TaoKetNoi().prepareStatement("select STT,chitiethoadon.MaDV,TenDV,chitiethoadon.SoLuong,Gia,DonGia "
                + "from chitiethoadon,nguyenlieu "
                + "where chitiethoadon.MaDV=nguyenlieu.MaDV and MaHD = ?");
        ps.setString(1, MaHD);
        rs = ps.executeQuery();
        while(rs.next()){
           ChiTietHoaDon tmp = new ChiTietHoaDon();
           
           tmp.setID(rs.getInt("STT"));
           tmp.setMaDV(rs.getString("MaDV"));
           tmp.setTenDV(rs.getString("TenDV"));
           tmp.setSoLuong(rs.getDouble("SoLuong"));
           tmp.setDonGia(rs.getInt("Gia"));
           tmp.setThanhTien(rs.getInt("DonGia"));
           
           arr.add(tmp);
        }
        ps = connectDatabase.TaoKetNoi().prepareStatement("select STT,chitiethoadon.MaDV,TenDV,chitiethoadon.SoLuong,Gia,DonGia "
                + "from chitiethoadon,dichvu "
                + "where chitiethoadon.MaDV=dichvu.MaDV and MaHD = ?");
        ps.setString(1, MaHD);
        rs = ps.executeQuery();
        
        while(rs.next()){
           ChiTietHoaDon tmp = new ChiTietHoaDon();
           
           tmp.setID(rs.getInt("STT"));
           tmp.setMaDV(rs.getString("MaDV"));
           tmp.setTenDV(rs.getString("TenDV"));
           tmp.setSoLuong(rs.getDouble("SoLuong"));
           tmp.setDonGia(rs.getInt("Gia"));
           tmp.setThanhTien(rs.getInt("DonGia"));
           
           arr.add(tmp);
        }
        ps.close();
        return arr;
    }
}
