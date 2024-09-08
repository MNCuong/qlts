/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Model.QLHoaDon;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import frmView.frmQuanLyHoaDon;
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
 * @author Administrator
 */
public class HoaDonData {
    //<editor-fold defaultstate="collapsed" desc="Var">
    private frmQuanLyHoaDon frm;
    private ArrayList<QLHoaDon> arr = new ArrayList();
    QLHoaDon hd = new QLHoaDon();
    //</editor-fold>
    public HoaDonData() throws ParseException, IOException {
        frm = new frmQuanLyHoaDon();
        createArr();
        frm.loadTable(arr);
        frm.searchListener(new SearchListener());
        frm.monthListener(new MonthListener());
        frm.newOrderListener(new NewOrderListener());
        frm.delListener(new DelListener());
        frm.editListener(new EditListener());
        frm.setVisible(true);
    }
    public HoaDonData(String l) {
        
    }
    //<editor-fold defaultstate="collapsed" desc="Event">
    class EditListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if(frm.editMode()){
                    int id = frm.getItem_id();
                    hd = frm.getInfo();
                    if(hd != null){
                        if(JOptionPane.showConfirmDialog(null, "Lưu ý! Hóa đơn sau khi thanh toán sẽ không thể chỉnh sửa. Bạn có muốn thanh toán hóa đơn này?")==0){
                            CloseableHttpClient client = HttpClients.createDefault();
                            HttpPost httpP = new HttpPost("http://localhost:4567/hoa_don/sua");
                            ArrayList<NameValuePair> params = new ArrayList<>();
                            params.add(new BasicNameValuePair("MaHD", hd.getMaHD()));
                            params.add(new BasicNameValuePair("MaBan", hd.getMaBan()));
                            params.add(new BasicNameValuePair("ThanhTien", Integer.toString(hd.getThanhTien())));
                            params.add(new BasicNameValuePair("TinhTrang", hd.getTinhTrang()));
                            httpP.setEntity(new UrlEncodedFormEntity(params, Charset.defaultCharset()));
                            CloseableHttpResponse response = client.execute(httpP);
                            thongBao(response);
                        }
                    }
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
                    HttpPost httpP = new HttpPost("http://localhost:4567/hoa_don/xoa");
                    ArrayList<NameValuePair> params = new ArrayList<>();
                    params.add(new BasicNameValuePair("MaHD", arr.get(id).getMaHD()));
                    httpP.setEntity(new UrlEncodedFormEntity(params, Charset.defaultCharset()));
                    CloseableHttpResponse response = client.execute(httpP);
                    thongBao(response);
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
                HttpPost httpG = new HttpPost("http://localhost:4567/hoa_don/search");
                ArrayList<NameValuePair> params = new ArrayList<>();
                params.add(new BasicNameValuePair("search", frm.getSearch()));
                httpG.setEntity(new UrlEncodedFormEntity(params, Charset.defaultCharset()));
                CloseableHttpResponse response = client.execute(httpG);
                HttpEntity entity = response.getEntity();
                String responseString = EntityUtils.toString(entity, Charset.defaultCharset());
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<QLHoaDon>>(){}.getType();
                arr = gson.fromJson(responseString, type);
                frm.loadTable(arr);
            } catch (JsonSyntaxException | IOException | ParseException ex) {
                JOptionPane.showMessageDialog(null, ex, "Thông báo", 1);
            }
        }
    }
    class MonthListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                CloseableHttpClient client = HttpClients.createDefault();
                HttpPost httpG = new HttpPost("http://localhost:4567/hoa_don/search");
                ArrayList<NameValuePair> params = new ArrayList<>();
                if(frm.getMonth().equals("Toàn bộ")){
                    params.add(new BasicNameValuePair("search", ""));
                } else {
                    params.add(new BasicNameValuePair("search", frm.getMonth()));
                }
                httpG.setEntity(new UrlEncodedFormEntity(params, Charset.defaultCharset()));
                CloseableHttpResponse response = client.execute(httpG);
                HttpEntity entity = response.getEntity();
                String responseString = EntityUtils.toString(entity, Charset.defaultCharset());
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<QLHoaDon>>(){}.getType();
                arr = gson.fromJson(responseString, type);
                frm.loadTable(arr);
            } catch (JsonSyntaxException | IOException | ParseException ex) {
                JOptionPane.showMessageDialog(null, ex, "Thông báo", 1);
            }
        }
    }
    class NewOrderListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                CloseableHttpClient client = HttpClients.createDefault();
                HttpPost httpG = new HttpPost("http://localhost:4567/hoa_don/them");
                ArrayList<NameValuePair> params = new ArrayList<>();
                params.add(new BasicNameValuePair("MaNV", NhanVienData.user));
                params.add(new BasicNameValuePair("MaBan", frm.getBan()));
                httpG.setEntity(new UrlEncodedFormEntity(params, Charset.defaultCharset()));
                CloseableHttpResponse response = client.execute(httpG);
                HttpEntity entity = response.getEntity();
                String responseString = EntityUtils.toString(entity, Charset.defaultCharset());
                QLHoaDon hd = new Gson().fromJson(responseString, QLHoaDon.class);
                ChiTietHoaDonData cthd = new ChiTietHoaDonData(hd);
                frm.dispose();
                
            } catch (JsonSyntaxException | IOException | ParseException ex) {
                JOptionPane.showMessageDialog(null, ex, "Thông báo", 1);
            }
        }
    }
    
    //</editor-fold>
    public void UpdateHoaDon(QLHoaDon h) throws IOException{
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpP = new HttpPost("http://localhost:4567/hoa_don/sua");
        ArrayList<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("MaHD", h.getMaHD()));
        params.add(new BasicNameValuePair("MaBan", h.getMaBan()));
        params.add(new BasicNameValuePair("ThanhTien", Integer.toString(h.getThanhTien())));
        params.add(new BasicNameValuePair("TinhTrang", h.getTinhTrang()));
        httpP.setEntity(new UrlEncodedFormEntity(params, Charset.defaultCharset()));
        CloseableHttpResponse response = client.execute(httpP);
    }
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
    private void createArr() throws IOException, ParseException{
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpG = new HttpGet("http://localhost:4567/hoa_don");
        CloseableHttpResponse response = client.execute(httpG);
        HttpEntity entity = response.getEntity();
        String responseString = EntityUtils.toString(entity, Charset.defaultCharset());
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<QLHoaDon>>(){}.getType();
        arr = gson.fromJson(responseString, type);
    }
}
