/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Controller.connectDatabase;
import Model.ThongKe;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author ad
 */
public class ThongKeCtrl {
    public static PreparedStatement ps;
    public ResultSet rs;
    public static Statement st;
    private static connectDatabase DBCtrl = new connectDatabase();
    private static Connection conn = DBCtrl.TaoKetNoi();
    private ArrayList<ThongKe> arr = new ArrayList();
    public ThongKeCtrl() {}
    
    public ArrayList<ThongKe> createArr() throws SQLException{
        arr = new ArrayList<>();
        ps = connectDatabase.TaoKetNoi().prepareStatement("select * from thongke order by MaTK desc limit 6");
        rs = ps.executeQuery();
        while(rs.next()){
           ThongKe tmp = new ThongKe();
           
           tmp.setMaTK(rs.getString("MaTK"));
           tmp.setDoanhThu(rs.getInt("DoanhThu"));
           
           arr.add(tmp);
        }
        ps.close();
        return arr;
    }
    public void themDoanhThu(Date d,int ThanhTien) throws SQLException{
        String MaTK = dateToMa(d);
        ps = connectDatabase.TaoKetNoi().prepareStatement("SELECT * FROM thongke WHERE MaTK=?");
        ps.setString(1, MaTK);
        rs = ps.executeQuery();
        if(rs.next()){
            int s = rs.getInt("DoanhThu");
            int r = s+ThanhTien;

            ps = connectDatabase.TaoKetNoi().prepareStatement("UPDATE thongke SET DoanhThu=? where MaTK = ?");
            ps.setInt(1, r);
            ps.setString(2, MaTK);
            ps.executeUpdate();
            ps.close();
        } else {
            String sql = "INSERT INTO thongke VALUES(?,?)";
            ps = connectDatabase.TaoKetNoi().prepareStatement(sql);
            ps.setString(1, MaTK);
            ps.setInt(2, ThanhTien);
            ps.execute();
            ps.close();
        }
    }
    public String dateToMa(Date d){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        return dateFormat.format(d).replace("-", "M");
    }
}
