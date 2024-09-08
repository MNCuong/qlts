/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package API;

import Controller.HoaDonCtrl;
import Model.HoaDon;
import com.google.gson.Gson;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static spark.Spark.get;
import static spark.Spark.post;

/**
 *
 * @author ADMIN
 */
public class QL_HoaDon_API {
    public QL_HoaDon_API() {
        get("/hoa_don", (rqst,rspns) -> {
                HoaDonCtrl hd = new HoaDonCtrl();
                rspns.type("application/json");
                rspns.body("123");
                return new Gson().toJson(hd.createArr());
        });
        post("/hoa_don/search", (rqst,rspns) -> {
            HoaDonCtrl hd = new HoaDonCtrl();
            String s = rqst.queryParams("search");
            rspns.type("application/json");
            return new Gson().toJson(hd.searchArr(s));
        });
        post("/hoa_don/them", (rqst,rspns) -> {
            HoaDonCtrl hdCtrl = new HoaDonCtrl();
            HoaDon hd = new HoaDon();
            hd.setMaHD(hdCtrl.taomaHoaDon());
            hd.setTenNhanVien(rqst.queryParams("MaNV"));
            
            LocalDate localDate = LocalDate.now();
            hd.setNgayLap(java.sql.Date.valueOf(localDate));
            
            hd.setMaBan(rqst.queryParams("MaBan"));
            hd.setThanhTien(0);
            hd.setTinhTrang("Chưa thanh toán");
            rspns.type("application/json");
            
            return new Gson().toJson(hdCtrl.InsertHoaDon(hd));
        });
        post("/hoa_don/xoa", (rqst,rspns) -> {
            HoaDonCtrl hdCtrl = new HoaDonCtrl();
            String maHD = rqst.queryParams("MaHD");
            rspns.type("application/json");
            
            return hdCtrl.DeleteHoaDon(maHD);
        });
        post("/hoa_don/sua", (rqst,rspns) -> {
            HoaDonCtrl hdCtrl = new HoaDonCtrl();
            HoaDon hd = new HoaDon();
            hd.setMaHD(rqst.queryParams("MaHD"));
            hd.setMaBan(rqst.queryParams("MaBan"));
            hd.setThanhTien(Integer.parseInt(rqst.queryParams("ThanhTien")));
            hd.setTinhTrang(rqst.queryParams("TinhTrang"));
            rspns.type("application/json");
            
            return hdCtrl.UpdateHoaDon(hd);
        });
    }
}
