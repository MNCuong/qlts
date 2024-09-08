/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package API;

import Controller.MenuCtrl;
import Model.Menu;
import com.google.gson.Gson;
import java.sql.Date;
import static spark.Spark.get;
import static spark.Spark.post;

/**
 *
 * @author ad
 */
public class QL_Menu_API {
    public QL_Menu_API() {
        get("/menu", (rqst,rspns) -> {
                MenuCtrl nl = new MenuCtrl();
                rspns.type("application/json");
                return new Gson().toJson(nl.createArr());
        });
        get("/menu/top5", (rqst,rspns) -> {
                MenuCtrl nl = new MenuCtrl();
                rspns.type("application/json");
                return new Gson().toJson(nl.getTop5());
        });
        post("/menu/search", (rqst,rspns) -> {
            MenuCtrl nl = new MenuCtrl();
            String s = rqst.queryParams("search");
            rspns.type("application/json");
            return new Gson().toJson(nl.searchArr(s));
        });
        post("/menu/them", (rqst,rspns) -> {
            MenuCtrl nvCtrl = new MenuCtrl();
            Menu nl = new Menu();
            nl.setMaMon(nvCtrl.taomaMon());
            nl.setTenMon(rqst.queryParams("TenMon"));
            nl.setSoLuong(Integer.parseInt(rqst.queryParams("SoLuong")));
            nl.setGia(Integer.parseInt(rqst.queryParams("Gia")));
            nl.setAnh(rqst.queryParams("Anh"));
            
            rspns.type("application/json");
            return nvCtrl.InsertMon(nl);
        });
        post("/menu/xoa", (rqst,rspns) -> {
            MenuCtrl nvCtrl = new MenuCtrl();
            String manl = rqst.queryParams("MaMon");
            return nvCtrl.DeleteMon(manl);
        });
        post("/menu/sua", (rqst,rspns) -> {
            MenuCtrl nvCtrl = new MenuCtrl();
            Menu nl = new Menu();
            nl.setMaMon(rqst.queryParams("MaMon"));
            nl.setTenMon(rqst.queryParams("TenMon"));
            nl.setSoLuong(Integer.parseInt(rqst.queryParams("SoLuong")));
            nl.setGia(Integer.parseInt(rqst.queryParams("Gia")));
            nl.setAnh(rqst.queryParams("Anh"));
            
            rspns.type("application/json");
            return nvCtrl.UpdateMon(nl);
        });
    }
}
