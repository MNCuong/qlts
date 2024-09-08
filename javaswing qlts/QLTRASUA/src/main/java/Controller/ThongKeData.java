/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.QLThongKe;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import frmView.frmThongKe;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

/**
 *
 * @author ad
 */
public class ThongKeData {
    frmThongKe frm;
    private ArrayList<QLThongKe> arr = new ArrayList();
    private QLThongKe nv;
    public ThongKeData() throws IOException, ParseException{
        frm = new frmThongKe();
        createArr();
        frm.loadTable1(arr);
        frm.loadChart();
        frm.setVisible(true);
    }
    public void createArr() throws IOException, ParseException{
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpG = new HttpGet("http://localhost:4567/thong_ke");
        CloseableHttpResponse response = client.execute(httpG);
        HttpEntity entity = response.getEntity();
        String responseString = EntityUtils.toString(entity, Charset.defaultCharset());
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<QLThongKe>>(){}.getType();
        arr = gson.fromJson(responseString, type);
    }
}
