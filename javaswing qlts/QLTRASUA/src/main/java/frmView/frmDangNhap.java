/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frmView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author ADMIN
 */
public class frmDangNhap extends JFrame implements ActionListener {
    //<editor-fold defaultstate="collapsed" desc="Var">
    JLabel tile = new JLabel("ĐĂNG NHẬP");
    JLabel l_pic = new JLabel();
    JPanel p1 = new JPanel();
    JLabel l_tk = new JLabel("Mã nhân viên:");
    JLabel l_mk = new JLabel("Mật khẩu:");
    JTextField txt_tk = new JTextField();
    JPasswordField txt_mk = new JPasswordField();
    JButton btn_login = new JButton("ĐĂNG NHẬP");
    //</editor-fold>
    public frmDangNhap() throws IOException {
        this.setTitle("ĐĂNG NHẬP");
        this.setSize(600,400);
        this.setLocation(50, 70);
        this.setLayout(new BorderLayout());
        
        Font fo_tile = new Font("Serif", Font.BOLD,30);
        tile.setFont(fo_tile);
        tile.setHorizontalAlignment(JLabel.CENTER);
        this.add(tile,BorderLayout.NORTH);
        
        File file = new File("");
        String currentDirectory = file.getAbsolutePath() + "/src/main/java";
        BufferedImage myPicture = ImageIO.read(new File(currentDirectory + "/Image/iconNhanVien.png"));
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
        
        JLabel l_bl1 = new JLabel(" ");
        gbc.gridy = 1;
        p1.add(l_bl1,gbc);
        
        l_mk.setFont(fo_l);
        gbc.gridy = 2;
        p1.add(l_mk,gbc);
        //---------------------TEXT
        Font fo_t = new Font("Serif", Font.PLAIN,14);
        
        txt_tk.setFont(fo_t);
        gbc.gridwidth = 2;
        gbc.gridx = 1;
        gbc.gridy = 0;
        p1.add(txt_tk,gbc);
        
        txt_mk.setFont(fo_t);
        gbc.gridy = 2;
        p1.add(txt_mk,gbc);
        //--------------------BUTTON
        JLabel l_bl2 = new JLabel("                               ");
        gbc.gridwidth = 1;
        gbc.gridy = 3;
        gbc.gridx = 2;
        p1.add(l_bl2,gbc);
        
        btn_login.setBackground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 4;
        p1.add(btn_login,gbc);
        btn_login.addActionListener(this);
        //</editor-fold>
        Border pad = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        p1.setBorder(pad);
        this.add(p1,BorderLayout.CENTER);
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public String getTK(){
        if(txt_tk.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập mã nhân viên!", "Thông báo", 1);
            return null;
        } else {
            return txt_tk.getText().trim();
        }
    }
    public String getMK(){
        if(txt_mk.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập mật khẩu!", "Thông báo", 1);
            return null;
        } else {
            return txt_mk.getText();
        }
    }
    public void loginListener (ActionListener log){
        btn_login.addActionListener(log);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
