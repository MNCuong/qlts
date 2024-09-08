/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.QLNhanVien;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import frmView.frmDangNhap;
import frmView.frmDoiMK;
import frmView.frmHome;
import frmView.frmQuanLyNV;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
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
import java.lang.reflect.Type;
import org.apache.hc.client5.http.classic.methods.HttpGet;

/**
 *
 * @author Administrator
 */
public class NhanVienData {

    //<editor-fold defaultstate="collapsed" desc="Var">
    frmQuanLyNV frm;
    frmDangNhap frm_login;
    frmDoiMK frm_doiMK;
    private ArrayList<QLNhanVien> arr = new ArrayList();
    private QLNhanVien nv;
    public static String user;
    public static int phanQuyen;
    //</editor-fold>
    public NhanVienData(String l) throws SQLException, IOException, ParseException, URISyntaxException {
        if(l.equals("login")){
            this.frm_login = new frmDangNhap();
            frm_login.loginListener(new LoginListener());
            frm_login.setVisible(true);
        } else if (l.contains("qlnv")){
            frm = new frmQuanLyNV();
            createArr();
            frm.loadTable(arr);
            frm.addListener(new AddListener());
            frm.delListener(new DelListener());
            frm.editListener(new EditListener());
            frm.calListener(new CalListener());
            frm.searchListener(new SearchListener());
            frm.clearListener(new ClearListener());
            frm.setVisible(true);
        } else {
            this.frm_doiMK = new frmDoiMK();
            frm_doiMK.confirmListener(new ConfirmListener());
            frm_doiMK.setVisible(true);
        }
    }
    //<editor-fold defaultstate="collapsed" desc="Event of QLNV">
    class AddListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                nv = frm.getInfo();
                if(nv != null){
                    CloseableHttpClient client = HttpClients.createDefault();
                    HttpPost httpP = new HttpPost("http://www.hotellth.com:8080/nhan_vien/them");
                    ArrayList<NameValuePair> params = new ArrayList<>();
                    
                    params.add(new BasicNameValuePair("TenNhanVien", nv.getTenNhanVien()));
                    params.add(new BasicNameValuePair("Password", nv.getPassword()));
                    params.add(new BasicNameValuePair("Phone", nv.getPhone()));
                    params.add(new BasicNameValuePair("Email", nv.getEmail()));
                    params.add(new BasicNameValuePair("CMND", nv.getCMND()));
                    String strDate = nv.getNgayLamViec().toString();
                    params.add(new BasicNameValuePair("NgayLamViec", strDate));
                    params.add(new BasicNameValuePair("CaLamViec", nv.getCaLamViec()));
                    params.add(new BasicNameValuePair("LuongCoBan", nv.getLuongCoBan()));
                    params.add(new BasicNameValuePair("HeSoLuong", nv.getHeSoLuong()));
                    params.add(new BasicNameValuePair("TienLuong", nv.getTienLuong()));
                    params.add(new BasicNameValuePair("PhanQuyen", Integer.toString(nv.getPhanQuyen())));
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
                    HttpPost httpP = new HttpPost("http://www.hotellth.com:8080/nhan_vien/xoa");
                    ArrayList<NameValuePair> params = new ArrayList<>();
                    params.add(new BasicNameValuePair("MaNhanVien", arr.get(id).getMaNhanVien()));
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
                    nv = frm.getInfo();
                    int id = frm.getItem_id();
                    if(nv != null){
                        CloseableHttpClient client = HttpClients.createDefault();
                        HttpPost httpP = new HttpPost("http://www.hotellth.com:8080/nhan_vien/sua");
                        ArrayList<NameValuePair> params = new ArrayList<>();
                        params.add(new BasicNameValuePair("MaNhanVien", nv.getMaNhanVien()));
                        params.add(new BasicNameValuePair("TenNhanVien", nv.getTenNhanVien()));
                        params.add(new BasicNameValuePair("Password", nv.getPassword()));
                        params.add(new BasicNameValuePair("Phone", nv.getPhone()));
                        params.add(new BasicNameValuePair("Email", nv.getEmail()));
                        params.add(new BasicNameValuePair("CMND", nv.getCMND()));
                        String strDate = nv.getNgayLamViec().toString();
                        params.add(new BasicNameValuePair("NgayLamViec", strDate));
                        params.add(new BasicNameValuePair("CaLamViec", nv.getCaLamViec()));
                        params.add(new BasicNameValuePair("LuongCoBan", nv.getLuongCoBan()));
                        params.add(new BasicNameValuePair("HeSoLuong", nv.getHeSoLuong()));
                        if(!nv.getLuongCoBan().equals(arr.get(id).getLuongCoBan()) || !nv.getHeSoLuong().equals(arr.get(id).getHeSoLuong())){
                            params.add(new BasicNameValuePair("TienLuong", "0"));
                        } else {
                            params.add(new BasicNameValuePair("TienLuong", nv.getTienLuong()));
                        }
                        params.add(new BasicNameValuePair("PhanQuyen",Integer.toString(nv.getPhanQuyen())));
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
    class CalListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                    nv = frm.getInfo();
                    if(nv != null){
                        CloseableHttpClient client = HttpClients.createDefault();
                        HttpPost httpP = new HttpPost("http://www.hotellth.com:8080/nhan_vien/sua");
                        ArrayList<NameValuePair> params = new ArrayList<>();
                        params.add(new BasicNameValuePair("MaNhanVien", nv.getMaNhanVien()));
                        params.add(new BasicNameValuePair("TenNhanVien", nv.getTenNhanVien()));
                        params.add(new BasicNameValuePair("Password", nv.getPassword()));
                        params.add(new BasicNameValuePair("Phone", nv.getPhone()));
                        params.add(new BasicNameValuePair("Email", nv.getEmail()));
                        params.add(new BasicNameValuePair("CMND", nv.getCMND()));
                        String strDate = nv.getNgayLamViec().toString();
                        params.add(new BasicNameValuePair("NgayLamViec", strDate));
                        params.add(new BasicNameValuePair("CaLamViec", nv.getCaLamViec()));
                        params.add(new BasicNameValuePair("LuongCoBan", nv.getLuongCoBan()));
                        params.add(new BasicNameValuePair("HeSoLuong", nv.getHeSoLuong()));
                        int lcb = Integer.parseInt(nv.getLuongCoBan());
                        double hsl = Double.parseDouble(nv.getHeSoLuong());
                        params.add(new BasicNameValuePair("TienLuong", Integer.toString((int) (hsl*lcb))));
                        params.add(new BasicNameValuePair("PhanQuyen",Integer.toString(nv.getPhanQuyen())));
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
                HttpPost httpG = new HttpPost("http://www.hotellth.com:8080/nhan_vien/search");
                ArrayList<NameValuePair> params = new ArrayList<>();
                params.add(new BasicNameValuePair("search", frm.getSearch()));
                httpG.setEntity(new UrlEncodedFormEntity(params, Charset.defaultCharset()));
                CloseableHttpResponse response = client.execute(httpG);
                HttpEntity entity = response.getEntity();
                String responseString = EntityUtils.toString(entity, Charset.defaultCharset());
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<QLNhanVien>>(){}.getType();
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
    //<editor-fold defaultstate="collapsed" desc="Event of LOGIN">
    class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String tk = frm_login.getTK();
            user=tk;
            String mk = frm_login.getMK();
            if(tk!=null && mk!=null){
                try {
                    CloseableHttpClient client = HttpClients.createDefault();
                    HttpPost httpP = new HttpPost("http://www.hotellth.com:8080/nhan_vien/login");
                    ArrayList<NameValuePair> params = new ArrayList<>();
                    params.add(new BasicNameValuePair("MaNhanVien", tk));
                    params.add(new BasicNameValuePair("Password", mk));
                    httpP.setEntity(new UrlEncodedFormEntity(params, Charset.defaultCharset()));
                    CloseableHttpResponse response = client.execute(httpP);
                    HttpEntity entity = response.getEntity();
                    String r = EntityUtils.toString(entity, Charset.defaultCharset());
                    int kq = Integer.parseInt(r);
                    phanQuyen=kq;
                    if(kq!=-1){
                        JOptionPane.showMessageDialog(null, "Đăng nhập thành công", "Thông báo", 1);
                        frmHome home = new frmHome();
                        home.setVisible(true);
                        frm_login.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Tài khoản hoặc mật khẩu không chính xác", "Thông báo", 1);
                    }
                } catch (IOException | ParseException ex) {
                    JOptionPane.showMessageDialog(null, ex, "Thông báo", 1);
                }
            }
        }
    }class ConfirmListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String tk = NhanVienData.user;
            String mk = frm_doiMK.getMatKhauCu();
            String mkMoi = frm_doiMK.getMatKhauMoi();
            if(tk!=null && mk!=null && mkMoi!=null){
                try {
                    CloseableHttpClient client = HttpClients.createDefault();
                    HttpPost httpP = new HttpPost("http://www.hotellth.com:8080/nhan_vien/doi_mat_khau");
                    ArrayList<NameValuePair> params = new ArrayList<>();
                    params.add(new BasicNameValuePair("MaNhanVien", tk));
                    params.add(new BasicNameValuePair("Password", mk));
                    params.add(new BasicNameValuePair("NewPassword", mkMoi));
                    httpP.setEntity(new UrlEncodedFormEntity(params, Charset.defaultCharset()));
                    CloseableHttpResponse response = client.execute(httpP);
                    HttpEntity entity = response.getEntity();
                    String r = EntityUtils.toString(entity, Charset.defaultCharset());
                    if(Boolean.parseBoolean(r)){
                        JOptionPane.showMessageDialog(null, "Đổi mật khẩu thành công", "Thông báo", 1);
                        frm_doiMK.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Mật khẩu cũ không chính xác", "Thông báo", 1);
                    }
                } catch (IOException | ParseException ex) {
                    JOptionPane.showMessageDialog(null, ex, "Thông báo", 1);
                }
            }
        }
    }
    //</editor-fold>
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
    //</editor-fold>
    private void createArr() throws IOException, ParseException{
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpG = new HttpGet("http://www.hotellth.com:8080/nhan_vien");
        CloseableHttpResponse response = client.execute(httpG);
        HttpEntity entity = response.getEntity();
        String responseString = EntityUtils.toString(entity, Charset.defaultCharset());
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<QLNhanVien>>(){}.getType();
        arr = gson.fromJson(responseString, type);
    }
}
