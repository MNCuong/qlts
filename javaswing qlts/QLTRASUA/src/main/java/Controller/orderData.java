///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Controller;
//
//import Model.HoaDonQL;
//import Model.orderDV;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.Statement;
//
///**
// *
// * @author Administrator
// */
//public class orderData {
//    public static PreparedStatement ps;
//    public static ResultSet rs;
//    public static Statement st;
//    public static void InsertOrderDV(orderDV nl) {
//        String sql = "INSERT INTO OrderDV VALUES(?,?,?,?,?,?)";
//        try {
//            ps = connectDatabase.TaoKetNoi().prepareStatement(sql);
//            ps.setString(1, nl.getMaHD());
//            ps.setString(2, nl.getMaMon());
//            ps.setString(3, nl.getMaNhanVien());
//            ps.setString(4, nl.getTenMon());
//            ps.setString(5, nl.getSoLuong());
//            ps.setString(6, nl.getDonGia());
//            ps.execute();
//            ps.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//            //JOptionPane.showMessageDialog(null, "Khách hàng không du?c thêm", "Thông báo", 1);
//        }
//
//    }
//    public static boolean DeleteHoaDon() {
//        try {
//            ps = connectDatabase.TaoKetNoi().prepareStatement("DELETE FROM OrderDV");
//            return ps.executeUpdate() > 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//    public static boolean UpdateHoaDon(HoaDonQL nv) {
//        try {
//            ps = connectDatabase.TaoKetNoi().prepareStatement("UPDATE HoaDon SET ThanhTien = ? where MaHD = ?");
//            ps.setString(1, nv.getThanhTien());
//            ps.setString(2, nv.getMaHD());
//            ps.executeUpdate();
//            ps.close();
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//
//    }
////    public static boolean UpdateOrder(orderDV nv) {
////        try {
////            ps = connectDatabase.TaoKetNoi().prepareStatement("UPDATE HoaDon SET TenMon = ?,"
////                    + "SoLuong = ? where ID = ?");
////            ps.setInt(1, nv.getID());
////            ps.setString(2, nv.getTenMon());
////            ps.setString(3, nv.getSoLuong());
////            ps.executeUpdate();
////            ps.close();
////            return true;
////        } catch (Exception e) {
////            e.printStackTrace();
////            return false;
////        }
////
////    }
//}
