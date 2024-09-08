/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.ChiTietHoaDon;
import Model.QLHoaDon;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import frmView.frmChiTietHoaDon;
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
public class ChiTietHoaDonData {
    frmChiTietHoaDon frm;
    ArrayList<ChiTietHoaDon> arr = new ArrayList();
    ChiTietHoaDon cthd;
    QLHoaDon hd;
    public ChiTietHoaDonData(QLHoaDon hd) throws IOException, ParseException {
        this.hd=hd;
        frm = new frmChiTietHoaDon(hd);
        createArr(hd.getMaHD());
        frm.loadTable(arr);
        updateTong();
        frm.delListener(new DelListener());
        frm.addListener(new AddListener());
        frm.setVisible(true);
    }
    //<editor-fold defaultstate="collapsed" desc="Event">
    class AddListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if(!frm.getSoLuong().equals("")){
                    CloseableHttpClient client = HttpClients.createDefault();
                    HttpPost httpP = new HttpPost("http://localhost:4567/chi_tiet_hoa_don/them");
                    ArrayList<NameValuePair> params = new ArrayList<>();
                    params.add(new BasicNameValuePair("MaHD", frm.getMaHD()));
                    params.add(new BasicNameValuePair("MaDV", frm.getMaDV()));
                    params.add(new BasicNameValuePair("SoLuong", frm.getSoLuong()));
                    params.add(new BasicNameValuePair("DonGia", frm.getThanhTien()));
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
                    int id = frm.getTbhd_item_id();
                    CloseableHttpClient client = HttpClients.createDefault();
                    HttpPost httpP = new HttpPost("http://localhost:4567/chi_tiet_hoa_don/xoa");
                    ArrayList<NameValuePair> params = new ArrayList<>();
                    params.add(new BasicNameValuePair("ID", Integer.toString(arr.get(id).getID())));
                    httpP.setEntity(new UrlEncodedFormEntity(params, Charset.defaultCharset()));
                    CloseableHttpResponse response = client.execute(httpP);
                    thongBao(response);
                }
            } catch (IOException | ParseException ex) {
                JOptionPane.showMessageDialog(null, ex, "Thông báo", 1);
            }
        }
    }
    //</editor-fold>
    public void updateTong() throws ParseException, IOException{
        HoaDonData ctrl = new HoaDonData("");
        ctrl.UpdateHoaDon(frm.getChd());
    }
    public void thongBao(CloseableHttpResponse response) throws IOException, ParseException{
        if(response.toString().contains("200")){
            HttpEntity entity = response.getEntity();
            String r = EntityUtils.toString(entity, Charset.defaultCharset());
            JOptionPane.showMessageDialog(null, r, "Thông báo", 1);
            createArr(hd.getMaHD());
            frm.loadTable(arr);
            updateTong();
        } else {
            JOptionPane.showMessageDialog(null, response.toString(), "Thông báo", 1);
        }
        
    }
    private void createArr(String MaHD) throws IOException, ParseException{
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpG = new HttpGet("http://localhost:4567/chi_tiet_hoa_don?MaHD="+MaHD);
        CloseableHttpResponse response = client.execute(httpG);
        HttpEntity entity = response.getEntity();
        String responseString = EntityUtils.toString(entity, Charset.defaultCharset());
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<ChiTietHoaDon>>(){}.getType();
        arr = gson.fromJson(responseString, type);
    }
}
