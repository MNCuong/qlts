/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package API;

import Controller.ChiTietHoaDonCtrl;
import Model.ChiTietHoaDon;
import com.google.gson.Gson;
import static spark.Spark.get;
import static spark.Spark.post;

/**
 *
 * @author ad
 */
public class ChiTietHoaDon_API {

    public ChiTietHoaDon_API() {
        get("/chi_tiet_hoa_don", (rqst,rspns) -> {
            ChiTietHoaDonCtrl cthd = new ChiTietHoaDonCtrl();
            String MaHD = rqst.queryParams("MaHD");
            rspns.type("application/json");
            return new Gson().toJson(cthd.createArr(MaHD));
        });
        post("/chi_tiet_hoa_don/xoa", (rqst,rspns) -> {
            ChiTietHoaDonCtrl cthd = new ChiTietHoaDonCtrl();
            String ID = rqst.queryParams("ID");
            rspns.type("application/json");
            
            return cthd.DeleteMon(ID);
        });
        post("/chi_tiet_hoa_don/them", (rqst,rspns) -> {
            ChiTietHoaDonCtrl cthdCtrl = new ChiTietHoaDonCtrl();
            ChiTietHoaDon cthd = new ChiTietHoaDon();
            String MaHD = rqst.queryParams("MaHD");
            String MaDV = rqst.queryParams("MaDV");
            int SoLuong = Integer.parseInt(rqst.queryParams("SoLuong"));
            int DonGia = Integer.parseInt(rqst.queryParams("DonGia"));
            rspns.type("application/json");
            
            return cthdCtrl.InsertMon(MaHD, MaDV, SoLuong, DonGia);
        });
        post("/chi_tiet_hoa_don/sua", (rqst,rspns) -> {
            ChiTietHoaDonCtrl cthdCtrl = new ChiTietHoaDonCtrl();
            ChiTietHoaDon cthd = new ChiTietHoaDon();
            String MaHD = rqst.queryParams("MaHD");
            String MaDV = rqst.queryParams("MaDV");
            int SoLuong = Integer.parseInt(rqst.queryParams("SoLuong"));
            int DonGia = Integer.parseInt(rqst.queryParams("DonGia"));
            rspns.type("application/json");
            
            return cthdCtrl.UpdateMon(MaHD, MaDV, SoLuong, DonGia);
        });
    }
}
