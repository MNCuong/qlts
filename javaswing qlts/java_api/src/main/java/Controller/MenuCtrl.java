/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Menu;
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
public class MenuCtrl {
    public static PreparedStatement ps;
    public static ResultSet rs;
    public static Statement st;
    private ArrayList<Menu> arr = new ArrayList();

    public MenuCtrl() {}
    public String UpdateMon(Menu nl) {
        try {
            ps = connectDatabase.TaoKetNoi().prepareStatement("UPDATE dichvu SET TenDV = ?,"
                    + "SoLuong=?,Gia=?,Anh=? where MaDV = ?");
            ps.setString(1, nl.getTenMon());
            ps.setInt(2, nl.getSoLuong());
            ps.setInt(3, nl.getGia());
            ps.setString(4, nl.getAnh());
            ps.setString(5, nl.getMaMon());
            ps.executeUpdate();
            ps.close();
            return "Ðã sửa thành công!";
        } catch (SQLException e) {
            return e.getMessage();
        }

    }
    public String DeleteMon(String MaMon) {
        try {
            ps = connectDatabase.TaoKetNoi().prepareStatement("DELETE FROM dichvu WHERE MaDV = ?");
            ps.setString(1, MaMon);
            ps.executeUpdate();
            ps.close();
            return "Ðã xóa thành công!";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }
    public String InsertMon(Menu nl) {
        String sql = "INSERT INTO dichvu VALUES(?,?,?,?,?)";
        try {
            ps = connectDatabase.TaoKetNoi().prepareStatement(sql);
            ps.setString(1, nl.getMaMon());
            ps.setString(2, nl.getTenMon());
            ps.setInt(3, nl.getSoLuong());
            ps.setInt(4, nl.getGia());
            ps.setString(5, nl.getAnh());
            ps.execute();
            ps.close();
            return "Ðã thêm nguyên liệu thành công!";
        } catch (HeadlessException | SQLException e) {
            return e.getMessage();
        }

    }
    public ArrayList<Menu> getTop5() throws SQLException{
        String sql = "select dichvu.TenDV,sum(chitiethoadon.SoLuong) as so "
                + "from dichvu,chitiethoadon "
                + "where dichvu.MaDV=chitiethoadon.MaDV "
                + "group by chitiethoadon.MaDV "
                + "order by so desc limit 5";
        ps = connectDatabase.TaoKetNoi().prepareStatement(sql);
        rs = ps.executeQuery();
        while(rs.next()){
           Menu tmp = new Menu();
           
           tmp.setTenMon(rs.getString("TenDV"));
           tmp.setSoLuong(rs.getInt("so"));
           
           arr.add(tmp);
        }
        ps.close();
        return arr;
    }
    public void updateSoLuongM(String MaDV,int SoLuong) throws SQLException{
        ps = connectDatabase.TaoKetNoi().prepareStatement("SELECT * FROM dichvu WHERE MaDV=?");
        ps.setString(1, MaDV);
        rs = ps.executeQuery();
        rs.next();
        int s = rs.getInt("SoLuong");
        int r = s-SoLuong;
        
        ps = connectDatabase.TaoKetNoi().prepareStatement("UPDATE dichvu SET SoLuong=? where MaDV = ?");
        ps.setInt(1, r);
        ps.setString(2, MaDV);
        ps.executeUpdate();
        ps.close();
    }
    public ArrayList<Menu> createArr() throws SQLException{
        arr = new ArrayList<>();
        ps = connectDatabase.TaoKetNoi().prepareStatement("SELECT * FROM dichvu");
        rs = ps.executeQuery();
        while(rs.next()){
           Menu tmp = new Menu();
           
           tmp.setMaMon(rs.getString("MaDV"));
           tmp.setTenMon(rs.getString("TenDV"));
           tmp.setSoLuong(rs.getInt("SoLuong"));
           tmp.setGia(rs.getInt("Gia"));
           tmp.setAnh(rs.getString("Anh"));
           
           arr.add(tmp);
        }
        ps.close();
        return arr;
    }
    public ArrayList<Menu> searchArr(String s) throws SQLException{
        
        arr = new ArrayList<>();
        String sql = "SELECT * FROM dichvu where MaDV like '%"+s+"%'"
                + " or TenDV like '%"+s+"%'"
                + " or Gia like '%"+s+"%'";
        ps = connectDatabase.TaoKetNoi().prepareStatement(sql);
        rs = ps.executeQuery();
        while(rs.next()){
           Menu tmp = new Menu();
           
           tmp.setMaMon(rs.getString("MaDV"));
           tmp.setTenMon(rs.getString("TenDV"));
           tmp.setSoLuong(rs.getInt("SoLuong"));
           tmp.setGia(rs.getInt("Gia"));
           tmp.setAnh(rs.getString("Anh"));
           
           arr.add(tmp);
        }
        ps.close();
        return arr;
    }
    public String taomaMon() throws SQLException, ClassNotFoundException {
        Connection conn;
        Statement stmt;
        conn = connectDatabase.TaoKetNoi();
        String sql = "SELECT MaDV FROM dichvu order by MaDV Desc";
        stmt = conn.createStatement();
        String manv;
        rs = stmt.executeQuery(sql);
        rs.next();
        manv = rs.getString("MaDV").trim();
        stmt.close();
        conn.close();
        if ((Integer.parseInt(manv.substring(2)) + 1) < 10) {
            manv = "M00" + (Integer.parseInt(manv.substring(2)) + 1);
        } else if (((Integer.parseInt(manv.substring(2)) + 1) >= 10) && ((Integer.parseInt(manv.substring(2)) + 1) < 100)) {
            manv = "M0" + (Integer.parseInt(manv.substring(2)) + 1);
        } else {
            manv = "M" + (Integer.parseInt(manv.substring(2)) + 1);
        }
        return manv;
    }
}
