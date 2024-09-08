/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.MenuCtrl.ps;
import Model.NguyenLieu;
import Model.ThongKe;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class NguyenLieuCtrl {

    public static PreparedStatement ps;
    public static ResultSet rs;
    public static Statement st;
    private ArrayList<NguyenLieu> arr = new ArrayList();

    public NguyenLieuCtrl() {}
    public void updateSoLuongNL(String MaDV,int SoLuong) throws SQLException{
        ps = connectDatabase.TaoKetNoi().prepareStatement("SELECT * FROM nguyenlieu WHERE MaDV=?");
        ps.setString(1, MaDV);
        rs = ps.executeQuery();
        rs.next();
        int s = rs.getInt("SoLuong");
        int r = s-SoLuong;
        
        ps = connectDatabase.TaoKetNoi().prepareStatement("UPDATE nguyenlieu SET SoLuong=? where MaDV = ?");
        ps.setInt(1, r);
        ps.setString(2, MaDV);
        ps.executeUpdate();
        ps.close();
    }
    public ArrayList<NguyenLieu> createArr() throws SQLException{
        arr = new ArrayList<>();
        ps = connectDatabase.TaoKetNoi().prepareStatement("SELECT * FROM NguyenLieu");
        rs = ps.executeQuery();
        while(rs.next()){
           NguyenLieu tmp = new NguyenLieu();
           
           tmp.setMaNL(rs.getString("MaDV"));
           tmp.setTenNL(rs.getString("TenDV"));
           tmp.setNgayNhap(rs.getDate("NgayNhap"));
           tmp.setSoLuong(rs.getInt("SoLuong"));
           tmp.setDvTinh(rs.getString("DvTinh"));
           tmp.setDonGia(rs.getInt("Gia"));
           
           arr.add(tmp);
        }
        ps.close();
        return arr;
    }
    public ArrayList<NguyenLieu> searchArr(String s) throws SQLException{
        
        arr = new ArrayList<>();
        String sql = "SELECT * FROM nguyenlieu where MaDV like '%"+s+"%'"
                + " or TenDV like '%"+s+"%'"
                + " or NgayNhap like '%"+s+"%'"
                + " or Gia like '%"+s+"%'";
        ps = connectDatabase.TaoKetNoi().prepareStatement(sql);
        rs = ps.executeQuery();
        while(rs.next()){
           NguyenLieu tmp = new NguyenLieu();
           tmp.setMaNL(rs.getString("MaDV"));
           tmp.setTenNL(rs.getString("TenDV"));
           tmp.setNgayNhap(rs.getDate("NgayNhap"));
           tmp.setSoLuong(rs.getInt("SoLuong"));
           tmp.setDvTinh(rs.getString("DvTinh"));
           tmp.setDonGia(rs.getInt("Gia"));
           arr.add(tmp);
        }
        ps.close();
        return arr;
    }
    public String InsertNguyenLieu(NguyenLieu nl) {
        String sql = "INSERT INTO nguyenlieu VALUES(?,?,?,?,?,?)";
        try {
            ps = connectDatabase.TaoKetNoi().prepareStatement(sql);
            ps.setString(1, nl.getMaNL());
            ps.setString(2, nl.getTenNL());
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String strDate = dateFormat.format(nl.getNgayNhap());
            ps.setString(3, strDate);
            ps.setInt(4, nl.getSoLuong());
            ps.setString(5, nl.getDvTinh());
            ps.setInt(6, nl.getDonGia());
            ps.execute();
            ps.close();
            return "Ðã thêm nguyên liệu thành công!";
        } catch (HeadlessException | SQLException e) {
            return e.getMessage();
        }

    }
    public String UpdateNguyenLieu(NguyenLieu nl) {
        try {
            ps = connectDatabase.TaoKetNoi().prepareStatement("UPDATE nguyenlieu SET TenDV = ?,"
                    + "NgayNhap = ?,SoLuong=?,DvTinh=?,Gia=? where MaDV = ?");
            ps.setString(1, nl.getTenNL());
            ps.setDate(2, nl.getNgayNhap());
            ps.setInt(3, nl.getSoLuong());
            ps.setString(4, nl.getDvTinh());
            ps.setInt(5, nl.getDonGia());
            ps.setString(6, nl.getMaNL());
            ps.executeUpdate();
            ps.close();
            return "Ðã sửa thành công!";
        } catch (SQLException e) {
            return e.getMessage();
        }

    }

    public String DeleteNguyenLieu(String MaNL) {
        try {
            ps = connectDatabase.TaoKetNoi().prepareStatement("DELETE FROM nguyenlieu WHERE MaDV = ?");
            ps.setString(1, MaNL);
            ps.executeUpdate();
            ps.close();
            return "Ðã xóa thành công!";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public String taomaNguyenLieu() throws SQLException, ClassNotFoundException {
        Connection conn;
        Statement stmt;
        conn = connectDatabase.TaoKetNoi();
        String sql = "SELECT MaDV FROM nguyenlieu order by MaDV Desc";
        stmt = conn.createStatement();
        String manv;
        rs = stmt.executeQuery(sql);
        rs.next();
        manv = rs.getString("MaDV").trim();
        stmt.close();
        conn.close();

        if ((Integer.parseInt(manv.substring(3)) + 1) < 10) {
            manv = "NL00" + (Integer.parseInt(manv.substring(3)) + 1);
        } else if (((Integer.parseInt(manv.substring(3)) + 1) >= 10) && ((Integer.parseInt(manv.substring(3)) + 1) < 100)) {
            manv = "NL0" + (Integer.parseInt(manv.substring(3)) + 1);
        } else {
            manv = "NL" + (Integer.parseInt(manv.substring(3)) + 1);
        }
        return manv;
    }
}
