/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.QLMenu;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import frmView.frmQuanLyDichVu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;

/**
 *
 * @author ad
 */
public class MenuData {
    //<editor-fold defaultstate="collapsed" desc="Var">
    private frmQuanLyDichVu frm;
    private ArrayList<QLMenu> arr = new ArrayList();
    private QLMenu mn;
    //</editor-fold>
    public MenuData() throws IOException, ParseException{
        frm = new frmQuanLyDichVu();
        createArr();
        frm.loadTable(arr);
        frm.addListener(new AddListener());
        frm.delListener(new DelListener());
        frm.editListener(new EditListener());
        frm.searchListener(new SearchListener());
        frm.clearListener(new ClearListener());
        frm.setVisible(true);
    }
    public MenuData(String l){
    }
    //<editor-fold defaultstate="collapsed" desc="Method">
    public void thongBao(CloseableHttpResponse response) throws IOException, ParseException{
        if(response.toString().contains("200")){
            HttpEntity entity = response.getEntity();
            String r = EntityUtils.toString(entity, Charset.defaultCharset());
            JOptionPane.showMessageDialog(null, r, "Thông báo", 1);
            createArr();
            frm.loadTable(arr);
        } else {
            JOptionPane.showMessageDialog(null, response.toString(), "Thông báo", 1);
        }
        
    }
    public ArrayList<QLMenu> getTop5() throws ParseException, IOException{
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet httpG = new HttpGet("http://localhost:4567/menu/top5");
            CloseableHttpResponse response = client.execute(httpG);
            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity, Charset.defaultCharset());
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<QLMenu>>(){}.getType();
            arr = gson.fromJson(responseString, type);
        } catch (JsonSyntaxException | IOException | ParseException ex) {
            JOptionPane.showMessageDialog(null, ex, "Thông báo", 1);
        }
        return arr;
    }
    public ArrayList<QLMenu> getDV(String s) throws ParseException, IOException{
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpG = new HttpPost("http://localhost:4567/menu/search");
            ArrayList<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("search", s));
            httpG.setEntity(new UrlEncodedFormEntity(params, Charset.defaultCharset()));
            CloseableHttpResponse response = client.execute(httpG);
            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity, Charset.defaultCharset());
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<QLMenu>>(){}.getType();
            arr = gson.fromJson(responseString, type);
        } catch (JsonSyntaxException | IOException | ParseException ex) {
            JOptionPane.showMessageDialog(null, ex, "Thông báo", 1);
        }
        return arr;
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Event">
    class AddListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                mn = frm.getInfo();
                frm.saveAnh();
                if(mn != null){
                    CloseableHttpClient client = HttpClients.createDefault();
                    HttpPost httpP = new HttpPost("http://localhost:4567/menu/them");
                    ArrayList<NameValuePair> params = new ArrayList<>();
                    params.add(new BasicNameValuePair("TenMon", mn.getTenMon()));
                    params.add(new BasicNameValuePair("SoLuong", Integer.toString(mn.getSoLuong())));
                    params.add(new BasicNameValuePair("Gia", Integer.toString(mn.getGia())));
                    params.add(new BasicNameValuePair("Anh", mn.getAnh()));
                    httpP.setEntity(new UrlEncodedFormEntity(params, Charset.defaultCharset()));
                    CloseableHttpResponse response = client.execute(httpP);
                    thongBao(response);
                }
            } catch (IOException | ParseException ex) {
                JOptionPane.showMessageDialog(null, ex, "Thông báo", 1);
            }
        }
    }
    class DelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if(JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa bản ghi này?")==0){
                    int id = frm.getItem_id();
                    CloseableHttpClient client = HttpClients.createDefault();
                    HttpPost httpP = new HttpPost("http://localhost:4567/menu/xoa");
                    ArrayList<NameValuePair> params = new ArrayList<>();
                    params.add(new BasicNameValuePair("MaMon", arr.get(id).getMaMon()));
                    httpP.setEntity(new UrlEncodedFormEntity(params, Charset.defaultCharset()));
                    CloseableHttpResponse response = client.execute(httpP);
                    thongBao(response);
                }
            } catch (IOException | ParseException ex) {
                JOptionPane.showMessageDialog(null, ex, "Thông báo", 1);
            }
        }
    }
    class EditListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if(frm.editMode()){
                    mn = frm.getInfo();
                    frm.saveAnh();
                    int id = frm.getItem_id();
                    if(mn != null){
                        CloseableHttpClient client = HttpClients.createDefault();
                        HttpPost httpP = new HttpPost("http://localhost:4567/menu/sua");
                        ArrayList<NameValuePair> params = new ArrayList<>();
                        params.add(new BasicNameValuePair("MaMon", arr.get(id).getMaMon()));
                        params.add(new BasicNameValuePair("TenMon", mn.getTenMon()));
                        params.add(new BasicNameValuePair("SoLuong", Integer.toString(mn.getSoLuong())));
                        params.add(new BasicNameValuePair("Gia", Integer.toString(mn.getGia())));
                        params.add(new BasicNameValuePair("Anh", mn.getAnh()));
                        httpP.setEntity(new UrlEncodedFormEntity(params, Charset.defaultCharset()));
                        CloseableHttpResponse response = client.execute(httpP);
                        thongBao(response);
                    }
                }
            } catch (IOException | ParseException ex) {
                JOptionPane.showMessageDialog(null, ex, "Thông báo", 1);
            }
        }
    }
    class SearchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                CloseableHttpClient client = HttpClients.createDefault();
                HttpPost httpG = new HttpPost("http://localhost:4567/menu/search");
                ArrayList<NameValuePair> params = new ArrayList<>();
                params.add(new BasicNameValuePair("search", frm.getSearch()));
                httpG.setEntity(new UrlEncodedFormEntity(params, Charset.defaultCharset()));
                CloseableHttpResponse response = client.execute(httpG);
                HttpEntity entity = response.getEntity();
                String responseString = EntityUtils.toString(entity, Charset.defaultCharset());
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<QLMenu>>(){}.getType();
                arr = gson.fromJson(responseString, type);
                frm.loadTable(arr);
            } catch (JsonSyntaxException | IOException | ParseException ex) {
                JOptionPane.showMessageDialog(null, ex, "Thông báo", 1);
            }
        }
    }
    class ClearListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                frm.clearMode();
                createArr();
                frm.loadTable(arr);
            } catch (IOException | ParseException ex) {
            }
        }
    }
    //</editor-fold>
    private void createArr() throws IOException, ParseException{
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpG = new HttpGet("http://localhost:4567/menu");
        CloseableHttpResponse response = client.execute(httpG);
        HttpEntity entity = response.getEntity();
        String responseString = EntityUtils.toString(entity, Charset.defaultCharset());
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<QLMenu>>(){}.getType();
        arr = gson.fromJson(responseString, type);
    }
}
