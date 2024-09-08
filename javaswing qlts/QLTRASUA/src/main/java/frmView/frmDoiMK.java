/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frmView;

import Controller.NhanVienData;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.Border;

/**
 *
 * @author ADMIN
 */
public class frmDoiMK extends JFrame implements ActionListener {
    //<editor-fold defaultstate="collapsed" desc="Var">
    JLabel tile = new JLabel("ĐỔI MẬT KHẨU");
    JLabel l_pic = new JLabel();
    JPanel p1 = new JPanel();
    JLabel l_omk = new JLabel("Mật khẩu cũ:");
    JLabel l_mk = new JLabel("Mật khẩu mới:");
    JLabel l_mkc = new JLabel("Xác nhận Mật khẩu mới:");
    JPasswordField txt_omk = new JPasswordField();
    JPasswordField txt_mk = new JPasswordField();
    JPasswordField txt_mkc = new JPasswordField();
    JButton btn_confirm = new JButton("XÁC NHẬN");
    JButton btn_cancel = new JButton("HỦY");
    JLabel l_tk = new JLabel("Tài khoản:");
    JLabel l_acc = new JLabel();
    //</editor-fold>
    public frmDoiMK() throws IOException {
        l_acc.setText(NhanVienData.user);
        this.setTitle("ĐỔI MẬT KHẨU");
        this.setSize(750,400);
        this.setLocation(50, 70);
        this.setLayout(new BorderLayout());
        
        Font fo_tile = new Font("Serif", Font.BOLD,30);
        tile.setFont(fo_tile);
        tile.setHorizontalAlignment(JLabel.CENTER);
        this.add(tile,BorderLayout.NORTH);
        
        File file = new File("");
        String currentDirectory = file.getAbsolutePath() + "/src/main/java";
        BufferedImage myPicture = ImageIO.read(new File(currentDirectory + "/Image/pass.png"));
        l_pic = new JLabel(new ImageIcon(myPicture));
        this.add(l_pic,BorderLayout.WEST);        
        //<editor-fold defaultstate="collapsed" desc="Panel1">
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        
        p1.setLayout(new GridBagLayout());
        //-------------------LABEL
        Font fo_l = new Font("Serif", Font.BOLD,14);
        
        l_tk.setFont(fo_l);
        gbc.gridx = 0;
        gbc.gridy = 0;
        p1.add(l_tk,gbc);
        
        JLabel l_bl0 = new JLabel(" ");
        gbc.gridy = 1;
        p1.add(l_bl0,gbc);
        
        l_omk.setFont(fo_l);
        gbc.gridy = 2;
        p1.add(l_omk,gbc);
        
        JLabel l_bl1 = new JLabel(" ");
        gbc.gridy = 3;
        p1.add(l_bl1,gbc);
        
        l_mk.setFont(fo_l);
        gbc.gridy = 4;
        p1.add(l_mk,gbc);
        
        JLabel l_bl2 = new JLabel(" ");
        gbc.gridy = 5;
        p1.add(l_bl2,gbc);
        
        l_mkc.setFont(fo_l);
        gbc.gridy = 6;
        p1.add(l_mkc,gbc);
        //---------------------TEXT
        Font fo_t = new Font("Serif", Font.PLAIN,14);
        
        l_acc.setFont(fo_l);
        gbc.gridwidth = 2;
        gbc.gridx = 1;
        gbc.gridy = 0;
        p1.add(l_acc,gbc);
        
        
        txt_omk.setFont(fo_t);
        gbc.gridy = 2;
        p1.add(txt_omk,gbc);
        
        txt_mk.setFont(fo_t);
        gbc.gridy = 4;
        p1.add(txt_mk,gbc);
        
        txt_mkc.setFont(fo_t);
        gbc.gridy = 6;
        p1.add(txt_mkc,gbc);
        //--------------------BUTTON
        JLabel l_bl3 = new JLabel("                               ");
        gbc.gridwidth = 1;
        gbc.gridy = 7;
        gbc.gridx = 1;
        p1.add(l_bl3,gbc);
        
        btn_confirm.setBackground(Color.WHITE);
        gbc.gridx = 2;
        gbc.gridy = 8;
        p1.add(btn_confirm,gbc);
        btn_cancel.setBackground(Color.WHITE);
        gbc.gridx = 0;
        p1.add(btn_cancel,gbc);
        btn_confirm.addActionListener(this);
        //</editor-fold>
        Border pad = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        p1.setBorder(pad);
        this.add(p1,BorderLayout.CENTER);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    frmHome home = new frmHome();
                    home.setVisible(true);
                } catch (IOException ex) {}
            }
        });
        btn_cancel.addActionListener((e) -> {
            try {
                frmHome home = new frmHome();
                home.setVisible(true);
                dispose();
            } catch (IOException ex) {}
        });
    }
    public String getMatKhauCu(){
        if(txt_omk.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy dủ thông tin!", "Thông báo", 1);
            return null;
        } else {
            return txt_omk.getText();
        }
    }
    public String getMatKhauMoi(){
        if(txt_mk.getText().trim().isEmpty() || txt_mkc.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy dủ thông tin!", "Thông báo", 1);
            return null;
        } else {
            if(txt_mk.getText().equals(txt_mkc.getText())){
                return txt_mk.getText();
            } else {
                JOptionPane.showMessageDialog(null, "Xác nhận Mật khẩu mới không chính xác, vui lòng nhập lại.", "Thông báo", 1);
                return null;
            }
        }
    }
    public void confirmListener (ActionListener log){
        btn_confirm.addActionListener(log);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
