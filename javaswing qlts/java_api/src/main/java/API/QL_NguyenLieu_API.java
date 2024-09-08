/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package API;

import Controller.NguyenLieuCtrl;
import Model.NguyenLieu;
import com.google.gson.Gson;
import java.sql.Date;
import static spark.Spark.get;
import static spark.Spark.post;

/**
 *
 * @author ADMIN
 */
public class QL_NguyenLieu_API {

    public QL_NguyenLieu_API() {
        get("/nguyen_lieu", (rqst,rspns) -> {
                NguyenLieuCtrl nl = new NguyenLieuCtrl();
                rspns.type("application/json");
                return new Gson().toJson(nl.createArr());
        });
        post("/nguyen_lieu/search", (rqst,rspns) -> {
            NguyenLieuCtrl nl = new NguyenLieuCtrl();
            String s = rqst.queryParams("search");
            rspns.type("application/json");
            return new Gson().toJson(nl.searchArr(s));
        });
        post("/nguyen_lieu/them", (rqst,rspns) -> {
            NguyenLieuCtrl nvCtrl = new NguyenLieuCtrl();
            NguyenLieu nl = new NguyenLieu();
            nl.setMaNL(nvCtrl.taomaNguyenLieu());
            nl.setTenNL(rqst.queryParams("TenNL"));
            
            String d = rqst.queryParams("NgayNhap");
            System.out.println(d);
            Date date = Date.valueOf(d);
            nl.setNgayNhap(date);
            
            nl.setSoLuong(Integer.parseInt(rqst.queryParams("SoLuong")));
            nl.setDvTinh(rqst.queryParams("DonVi"));
            nl.setDonGia(Integer.parseInt(rqst.queryParams("DonGia")));
            
            rspns.type("application/json");
            return nvCtrl.InsertNguyenLieu(nl);
        });
        post("/nguyen_lieu/xoa", (rqst,rspns) -> {
            NguyenLieuCtrl nvCtrl = new NguyenLieuCtrl();
            String manl = rqst.queryParams("MaNL");
            return nvCtrl.DeleteNguyenLieu(manl);
        });
        post("/nguyen_lieu/sua", (rqst,rspns) -> {
            NguyenLieuCtrl nvCtrl = new NguyenLieuCtrl();
            NguyenLieu nl = new NguyenLieu();
            nl.setMaNL(rqst.queryParams("MaNL"));
            nl.setTenNL(rqst.queryParams("TenNL"));
            
            String d = rqst.queryParams("NgayNhap");
            Date date = Date.valueOf(d);
            nl.setNgayNhap(date);
            
            nl.setSoLuong(Integer.parseInt(rqst.queryParams("SoLuong")));
            nl.setDvTinh(rqst.queryParams("DonVi"));
            nl.setDonGia(Integer.parseInt(rqst.queryParams("DonGia")));
            
            rspns.type("application/json");
            return nvCtrl.UpdateNguyenLieu(nl);
        });
    }
}
