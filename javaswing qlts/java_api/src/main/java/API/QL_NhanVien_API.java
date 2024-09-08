/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package API;

import Controller.NhanVienCtrl;
import Model.NhanVien;
import com.google.gson.Gson;
import java.sql.Date;
import static spark.Spark.get;
import static spark.Spark.post;

/**
 *
 * @author ADMIN
 */
public class QL_NhanVien_API {
    
    public QL_NhanVien_API(){
        get("/nhan_vien", (rqst,rspns) -> {
            NhanVienCtrl nv = new NhanVienCtrl();
            rspns.type("application/json");
            return new Gson().toJson(nv.createArr());
        });
        post("/nhan_vien/login", (rqst,rspns) -> {
            NhanVienCtrl nv = new NhanVienCtrl();
            String tk = rqst.queryParams("MaNhanVien");
            String mk = rqst.queryParams("Password");
            rspns.type("application/json");
            return nv.dangNhap(tk, mk);
        });
        post("/nhan_vien/search", (rqst,rspns) -> {
            NhanVienCtrl nv = new NhanVienCtrl();
            String s = rqst.queryParams("search");
            rspns.type("application/json");
            return new Gson().toJson(nv.searchArr(s));
        });
        post("/nhan_vien/them", (rqst,rspns) -> {
            NhanVienCtrl nvCtrl = new NhanVienCtrl();
            NhanVien nv = new NhanVien();
//            nv.setMaNhanVien(nvCtrl.taomaNhanVien());
            
            nv.setMaNhanVien(nvCtrl.taomaNhanVien());
            nv.setTenNhanVien(rqst.queryParams("TenNhanVien"));
            nv.setPassword(rqst.queryParams("Password"));
            nv.setPhone(rqst.queryParams("Phone"));
            nv.setEmail(rqst.queryParams("Email"));
            nv.setCMND(rqst.queryParams("CMND"));
            
            String d = rqst.queryParams("NgayLamViec");
            Date date = Date.valueOf(d);
            nv.setNgayLamViec(date);
            
            nv.setCaLamViec(rqst.queryParams("CaLamViec"));
            nv.setLuongCoBan(rqst.queryParams("LuongCoBan"));
            nv.setHeSoLuong(rqst.queryParams("HeSoLuong"));
            nv.setTienLuong(rqst.queryParams("TienLuong"));
            nv.setPhanQuyen(Integer.parseInt(rqst.queryParams("PhanQuyen")));
            
            rspns.type("application/json");
            return nvCtrl.InsertNhanVien(nv);
        });
        post("/nhan_vien/xoa", (rqst,rspns) -> {
            NhanVienCtrl nvCtrl = new NhanVienCtrl();
            String manv = rqst.queryParams("MaNhanVien");
            return nvCtrl.DeleteKhachHang(manv);
        });
        post("/nhan_vien/sua", (rqst,rspns) -> {
            NhanVienCtrl nvCtrl = new NhanVienCtrl();
            NhanVien nv = new NhanVien();
            nv.setMaNhanVien(rqst.queryParams("MaNhanVien"));
            nv.setTenNhanVien(rqst.queryParams("TenNhanVien"));
            nv.setPassword(rqst.queryParams("Password"));
            nv.setPhone(rqst.queryParams("Phone"));
            nv.setEmail(rqst.queryParams("Email"));
            nv.setCMND(rqst.queryParams("CMND"));
            
            String d = rqst.queryParams("NgayLamViec");
            Date date = Date.valueOf(d);
            nv.setNgayLamViec(date);
            
            nv.setCaLamViec(rqst.queryParams("CaLamViec"));
            nv.setLuongCoBan(rqst.queryParams("LuongCoBan"));
            nv.setHeSoLuong(rqst.queryParams("HeSoLuong"));
            nv.setTienLuong(rqst.queryParams("TienLuong"));
            nv.setPhanQuyen(Integer.parseInt(rqst.queryParams("PhanQuyen")));
            
            rspns.type("application/json");
            return nvCtrl.UpdateNhanVien(nv);
        });
        post("/nhan_vien/doi_mat_khau", (rqst,rspns) -> {
            NhanVienCtrl nvCtrl = new NhanVienCtrl();
            String tk = rqst.queryParams("MaNhanVien");
            String mk = rqst.queryParams("Password");
            String newPass = rqst.queryParams("NewPassword");
            
            rspns.type("application/json");
            return nvCtrl.DoiMatKhau(tk,mk,newPass);
        });
    }
}
