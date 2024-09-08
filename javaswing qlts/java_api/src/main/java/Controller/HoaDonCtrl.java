/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import static Controller.ChiTietHoaDonCtrl.ps;
import static Controller.NhanVienCtrl.ps;
import Model.HoaDon;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class HoaDonCtrl {

    public static PreparedStatement ps;
    public ResultSet rs;
    public static Statement st;
    private static connectDatabase DBCtrl = new connectDatabase();
    private static Connection conn = DBCtrl.TaoKetNoi();
    private ArrayList<HoaDon> arr = new ArrayList();

    public HoaDonCtrl() {
    }
    public ArrayList<HoaDon> createArr() throws SQLException{
        arr = new ArrayList<>();
        ps = connectDatabase.TaoKetNoi().prepareStatement("SELECT MaHD,TenNhanVien,Ngay,MaBan,ThanhTien,TinhTrang FROM hoadon,qlnhan_vien WHERE qlnhan_vien.MaNhanVien=hoadon.MaNhanVien");
        rs = ps.executeQuery();
        while(rs.next()){
           HoaDon tmp = new HoaDon();
           
           tmp.setMaHD(rs.getString("MaHD"));
           tmp.setTenNhanVien(rs.getString("TenNhanVien"));
           tmp.setNgayLap(rs.getDate("Ngay"));
           tmp.setMaBan(rs.getString("MaBan"));
           tmp.setThanhTien(rs.getInt("ThanhTien"));
           tmp.setTinhTrang(rs.getString("TinhTrang"));
           
           arr.add(tmp);
        }
        ps.close();
        return arr;
    }
    public ArrayList<HoaDon> searchArr(String s) throws SQLException{
        
        arr = new ArrayList<>();
        String sql = "SELECT MaHD,TenNhanVien,Ngay,MaBan,ThanhTien,TinhTrang FROM hoadon,qlnhan_vien WHERE qlnhan_vien.MaNhanVien=hoadon.MaNhanVien "
                + "and (MaHD like '%"+s+"%'"
                + " or TenNhanVien like '%"+s+"%'"
                + " or Ngay like '%"+s+"%'"
                + " or MaBan like '%"+s+"%'"
                + " or ThanhTien like '%"+s+"%'"
                + " or TinhTrang like '%"+s+"%')";
        ps = connectDatabase.TaoKetNoi().prepareStatement(sql);
        rs = ps.executeQuery();
        while(rs.next()){
           HoaDon tmp = new HoaDon();
           
           tmp.setMaHD(rs.getString("MaHD"));
           tmp.setTenNhanVien(rs.getString("TenNhanVien"));
           tmp.setNgayLap(rs.getDate("Ngay"));
           tmp.setMaBan(rs.getString("MaBan"));
           tmp.setThanhTien(rs.getInt("ThanhTien"));
           tmp.setTinhTrang(rs.getString("TinhTrang"));
           
           arr.add(tmp);
        }
        ps.close();
        return arr;
    }
    public String UpdateHoaDon(HoaDon nv) throws SQLException {
        if(nv.getTinhTrang().equals("Đã thanh toán")){
            ps = connectDatabase.TaoKetNoi().prepareStatement("SELECT Ngay FROM hoadon WHERE MaHD=?");
            ps.setString(1, nv.getMaHD());
            rs = ps.executeQuery();
            rs.next();
            nv.setNgayLap(rs.getDate("Ngay"));
            
            ThongKeCtrl tkCtrl = new ThongKeCtrl();
            tkCtrl.themDoanhThu(nv.getNgayLap(), nv.getThanhTien());
        }
        
        String sql = "UPDATE hoadon SET MaBan = ?,"
                + "ThanhTien = ?,TinhTrang = ? where MaHD = ?";
        try {
            ps = conn.prepareStatement(sql);;
            ps.setString(1, nv.getMaBan());
            ps.setInt(2, nv.getThanhTien());
            ps.setString(3, nv.getTinhTrang());
            ps.setString(4, nv.getMaHD());
            ps.executeUpdate();
            ps.close();
            return "Sửa thành công";
        } catch (SQLException e) {
            ps.close();
            return e.getMessage();
        }

    }
    public HoaDon InsertHoaDon(HoaDon nl) throws SQLException {
        String sql = "INSERT INTO hoadon VALUES(?,?,?,?,?,?)";
        ps = conn.prepareStatement(sql);
        ps.setString(1, nl.getMaHD());
        ps.setString(2, nl.getTenNhanVien());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = dateFormat.format(nl.getNgayLap());
        ps.setString(3, strDate);
        ps.setString(4, nl.getMaBan());
        ps.setInt(5, nl.getThanhTien());
        ps.setString(6, nl.getTinhTrang());
        ps.execute();
        
        ps = connectDatabase.TaoKetNoi().prepareStatement("SELECT MaHD,TenNhanVien,Ngay,MaBan,ThanhTien,TinhTrang "
                + "FROM hoadon,qlnhan_vien "
                + "WHERE qlnhan_vien.MaNhanVien=hoadon.MaNhanVien "
                + "AND MaHD='"+nl.getMaHD()+"'");
        rs = ps.executeQuery();
        HoaDon tmp = new HoaDon();
        while(rs.next()){
            tmp.setMaHD(rs.getString("MaHD"));
            tmp.setTenNhanVien(rs.getString("TenNhanVien"));
            tmp.setNgayLap(rs.getDate("Ngay"));
            tmp.setMaBan(rs.getString("MaBan"));
            tmp.setThanhTien(rs.getInt("ThanhTien"));
            tmp.setTinhTrang(rs.getString("TinhTrang"));
        }
        ps.close();
        return tmp;
    }
    

    public String DeleteHoaDon(String MaHD) throws SQLException {
        try {
            ps = conn.prepareStatement("DELETE FROM hoadon WHERE MaHD = ?");
            ps.setString(1, MaHD);
            ps.executeUpdate();
            ps.close();
            return "Xóa thành công";
        } catch (SQLException e) {
            ps.close();
            return e.getMessage();
        }
    }

    public String taomaHoaDon() throws SQLException, ClassNotFoundException {
        Connection Nconn;
        Statement stmt;
        Nconn = connectDatabase.TaoKetNoi();
        String sql = "SELECT MaHD FROM hoadon order by MaHD Desc";
        stmt = Nconn.createStatement();
        String manv;
        rs = stmt.executeQuery(sql);
        rs.next();
        manv = rs.getString("MaHD").trim();
        stmt.close();
        Nconn.close();

        if ((Integer.parseInt(manv.substring(3)) + 1) < 10) {
            manv = "HD00" + (Integer.parseInt(manv.substring(3)) + 1);
        } else if (((Integer.parseInt(manv.substring(3)) + 1) >= 10) && ((Integer.parseInt(manv.substring(3)) + 1) < 100)) {
            manv = "HD0" + (Integer.parseInt(manv.substring(3)) + 1);
        } else {
            manv = "HD" + (Integer.parseInt(manv.substring(3)) + 1);
        }
        return manv;
    }

}
