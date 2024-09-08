/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package API;

import Controller.ThongKeCtrl;
import com.google.gson.Gson;
import static spark.Spark.get;

/**
 *
 * @author ad
 */
public class ThongKe_API {
    public ThongKe_API() {
        get("/thong_ke", (rqst,rspns) -> {
            ThongKeCtrl tk = new ThongKeCtrl();
            rspns.type("application/json");
            return new Gson().toJson(tk.createArr());
        });
    }
}
