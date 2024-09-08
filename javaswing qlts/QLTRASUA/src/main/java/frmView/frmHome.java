/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frmView;

import Controller.HoaDonData;
import Controller.MenuData;
import Controller.NguyenLieuData;
import Controller.NhanVienData;
import Controller.ThongKeData;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.Border;
import org.apache.hc.core5.http.ParseException;

/**
 *
 * @author ADMIN
 */
public class frmHome extends JFrame{
    //<editor-fold defaultstate="collapsed" desc="Var">
    JLabel tile = new JLabel("QUÁN TRÀ SỮA");
    JLabel l_pic = new JLabel();
    JPanel p1 = new JPanel();
    JButton btn_qlnv;
    JButton btn_qlnl;
    JButton btn_mn;
    JButton btn_qlhd;
    JButton btn_tk;
    
    JMenuBar mb = new JMenuBar();
    JMenu m_hethong = new JMenu("Hệ thống");
    JMenu m_acc = new JMenu("Tài khoản");
    JMenuItem mi_exit = new JMenuItem("Thoát");
    JMenuItem mi_logout = new JMenuItem("Đăng xuất");
    JMenuItem mi_changePass = new JMenuItem("Đổi mật khẩu");
    
    JLabel l_preAcc = new JLabel("Tài khoản: ");
    JLabel l_acc = new JLabel();
    //</editor-fold>

    public frmHome() throws IOException {
        this.setTitle("Quản lý quán trà sữa");
        this.setSize(1185,610);
        this.setLocation(50, 70);
        this.setLayout(new BorderLayout());
        
        Font fo_l = new Font("Serif", Font.BOLD,30);
        tile.setFont(fo_l);
        tile.setHorizontalAlignment(JLabel.CENTER);
        JPanel p_tile = new JPanel();
        p_tile.setLayout(new BorderLayout());
        p_tile.add(tile,BorderLayout.CENTER);
        //<editor-fold defaultstate="collapsed" desc="Menu">
        l_acc.setText(NhanVienData.user);
        m_hethong.add(mi_exit);
        m_acc.add(mi_logout);
        m_acc.add(mi_changePass);
        m_hethong.add(m_acc);
        mb.add(m_hethong);
        mb.add(l_preAcc);
        mb.add(l_acc);
        p_tile.add(mb,BorderLayout.NORTH);
        //</editor-fold>
        this.add(p_tile,BorderLayout.NORTH);
        
        File file = new File("");
        String currentDirectory = file.getAbsolutePath() + "/src/main/java";
        BufferedImage myPicture = ImageIO.read(new File(currentDirectory + "/Image/anhgioithieu.jpg"));
        l_pic = new JLabel(new ImageIcon(myPicture));
        this.add(l_pic,BorderLayout.CENTER);
        
        //<editor-fold defaultstate="collapsed" desc="Panel1">
        p1.setLayout(new GridLayout(5,1,10,10));
        
        ImageIcon nvi = new ImageIcon(currentDirectory + "/Image/iconNhanVien2.png");
        btn_qlnv = new JButton("QUẢN LÝ NHÂN VIÊN",nvi);
        Font fo_b = new Font("Serif", Font.BOLD,20);
        btn_qlnv.setBackground(Color.WHITE);
        btn_qlnv.setFont(fo_b);
        if (NhanVienData.phanQuyen == 1) {
            btn_qlnv.setEnabled(true);
        }
        else{
            btn_qlnv.setEnabled(false);
        }
        ImageIcon nli = new ImageIcon(currentDirectory + "/Image/iconNguyenLieu.png");
        btn_qlnl = new JButton("QUẢN LÝ NGUYÊN LIỆU",nli);
        btn_qlnl.setBackground(Color.WHITE);
        btn_qlnl.setFont(fo_b);
        
        ImageIcon mni = new ImageIcon(currentDirectory + "/Image/iconMenu.png");
        btn_mn = new JButton("MENU",mni);
        btn_mn.setBackground(Color.WHITE);
        btn_mn.setFont(fo_b);
        
        ImageIcon hdi = new ImageIcon(currentDirectory + "/Image/iconHoaDon.png");
        btn_qlhd = new JButton("QUẢN LÝ HÓA ĐƠN",hdi);
        btn_qlhd.setBackground(Color.WHITE);
        btn_qlhd.setFont(fo_b);
        
        ImageIcon tki = new ImageIcon(currentDirectory + "/Image/iconThongKe.png");
        btn_tk = new JButton("THỐNG KÊ",tki);
        btn_tk.setBackground(Color.WHITE);
        btn_tk.setFont(fo_b);
        
        p1.add(btn_qlnv);
        p1.add(btn_qlnl);
        p1.add(btn_mn);
        p1.add(btn_qlhd);
        p1.add(btn_tk);
        
        //</editor-fold>
        Border pad = BorderFactory.createLineBorder(Color.BLACK, 2);
        p1.setBorder(pad);
        p1.setPreferredSize(new Dimension(443,448));
        this.add(p1,BorderLayout.EAST);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //<editor-fold defaultstate="collapsed" desc="Event">
        btn_mn.addActionListener((e) -> {
            try {
                MenuData mn = new MenuData();
                dispose();
            } catch (IOException | ParseException ex) {
                Logger.getLogger(frmHome.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btn_tk.addActionListener((e) -> {
            try {
                ThongKeData tk = new ThongKeData();
                dispose();
            } catch (IOException | ParseException ex) {
                Logger.getLogger(frmHome.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btn_qlnv.addActionListener(((e) -> {
            try {
                NhanVienData frmQLNV = new NhanVienData(NhanVienData.user+"qlnv");
                dispose();
            } catch (SQLException | IOException | ParseException | URISyntaxException ex) {}
        }));
        btn_qlnl.addActionListener(((e) -> {
            try {
                NguyenLieuData frmQLNL = new NguyenLieuData();
                dispose();
            } catch (IOException | ParseException  ex) {}
        }));
        btn_qlhd.addActionListener((e) -> {
            try {
                HoaDonData frmQLHD = new HoaDonData();
                dispose();
            } catch (ParseException | IOException ex) {}
        });
        mi_exit.addActionListener((e) -> {
            dispose();
        });
        mi_logout.addActionListener((e) -> {
            try {
                NhanVienData frm = new NhanVienData("login");
                dispose();
            } catch (SQLException | ParseException | URISyntaxException | IOException ex) {}
        });
        mi_changePass.addActionListener((e) -> {
            try {
                NhanVienData frm = new NhanVienData("");
                dispose();
            } catch (SQLException | ParseException | URISyntaxException | IOException ex) {}
        });
        //</editor-fold>
    }
    public void lock(){
        btn_qlnv.setEnabled(false);
        btn_tk.setEnabled(false);
    }
}
