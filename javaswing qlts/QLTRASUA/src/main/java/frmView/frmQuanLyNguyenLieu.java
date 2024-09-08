/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frmView;

import Controller.NhanVienData;
import Model.QLNguyenLieu;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ad
 */
public class frmQuanLyNguyenLieu extends JFrame implements ActionListener {
    //<editor-fold defaultstate="collapsed" desc="Var">
    boolean isSelected = false;
    int item_id,mode=0;
    private ArrayList<QLNguyenLieu> arr = new ArrayList();
    //---------------------------------------------------------------------------
    JPanel p2 = new JPanel();
    JLabel tile = new JLabel("QUẢN LÝ NGUYÊN LIỆU");
    
    JPanel p1 = new JPanel();
    JButton btn_add = new JButton("THÊM");
    JButton btn_edit = new JButton("SỬA");
    JButton btn_del = new JButton("XÓA");
    JButton btn_clear = new JButton("LÀM MỚI");
    JButton btn_search = new JButton("TÌM KIẾM");
    JTextField txt_search = new JTextField();
    
    JLabel l_maNL = new JLabel("Mã Nguyên Liệu:");
    JLabel l_tenNL = new JLabel("Tên Nguyên Liệu:");
    JLabel l_ngayNhap = new JLabel("Ngày Nhập:");
    JLabel l_soLuong = new JLabel("Số Lượng:");
    JLabel l_donVi = new JLabel("Đơn vị tính:");
    JLabel l_donGia = new JLabel("Đơn Giá:");
    JTextField txt_maNL = new JTextField();
    JTextField txt_tenNL = new JTextField();
    JDateChooser dc_ngayNhap = new JDateChooser();
    SpinnerNumberModel snm_soLuong = new SpinnerNumberModel(0, 0, 500, 1); //----------
    JSpinner spr_soLuong = new JSpinner(snm_soLuong);
    JComboBox cb_donVi = new JComboBox();
    SpinnerNumberModel snm_donGia = new SpinnerNumberModel(0, 0, 2000000000, 1000);//------------
    JSpinner spr_donGia = new JSpinner(snm_donGia);
    
    JTable td = new JTable();
    DefaultTableModel model = (DefaultTableModel) td.getModel();
    
    JMenuBar mb = new JMenuBar();
    JMenu m_hethong = new JMenu("Hệ thống");
    JMenuItem mi_exit = new JMenuItem("Thoát");
    JLabel l_preAcc = new JLabel("Tài khoản: ");
    JLabel l_acc = new JLabel();
    //</editor-fold>
    public frmQuanLyNguyenLieu() {
        this.setSize(1200,800);
        this.setLocation(50, 70);
        this.setTitle("Quản lý nguyên liệu");
        this.setLayout(new BorderLayout());
        Border pad = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        //<editor-fold defaultstate="collapsed" desc="Menu">
        p2.setLayout(new BorderLayout());
        
        l_acc.setText(NhanVienData.user);
        m_hethong.add(mi_exit);
        mb.add(m_hethong);
        mb.add(l_preAcc);
        mb.add(l_acc);
        p2.add(mb,BorderLayout.NORTH);
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Panel02">
        
        Font fo_tile = new Font("Serif", Font.BOLD,32);
        tile.setFont(fo_tile);
        tile.setHorizontalAlignment(JLabel.CENTER);
        tile.setBorder(pad);
        p2.add(tile,BorderLayout.CENTER);
        //---------------------------------------------------------------------------------
        JPanel p21 = new JPanel();
        p21.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc= new GridBagConstraints();
        gbc.weightx = 1;
        gbc.weighty = 1.0;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        
        btn_search.setBackground(Color.WHITE);
        btn_search.setPreferredSize(new Dimension(75,25));
        gbc.gridx = 0;
        gbc.gridy = 0;
        p21.add(btn_search,gbc);
        btn_search.addActionListener(this);
        
        Font fo_t = new Font("Serif", Font.PLAIN,14);
        txt_search.setFont(fo_t);
        txt_search.setPreferredSize(new Dimension(1000,25));
        gbc.gridx=1;
        p21.add(txt_search,gbc);
        //---------------------------------------------------------------------------------
        p2.add(p21,BorderLayout.SOUTH);
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Panel01">
        p1.setBorder(pad);
        p1.setLayout(new GridBagLayout());
        
        Font fo_l = new Font("Serif", Font.BOLD,14);
        //--------------------------add Lable-----------------------------------
        l_maNL.setFont(fo_l);
        gbc.gridx = 0;
        gbc.gridy = 0;
        p1.add(l_maNL,gbc);
        
        l_tenNL.setFont(fo_l);
        gbc.gridy = 1;
        p1.add(l_tenNL,gbc);
        
        l_ngayNhap.setFont(fo_l);
        gbc.gridy = 2;
        p1.add(l_ngayNhap,gbc);
        
        l_soLuong.setFont(fo_l);
        gbc.gridy = 3;
        p1.add(l_soLuong,gbc);
        
        l_donVi.setFont(fo_l);
        gbc.gridy = 4;
        p1.add(l_donVi,gbc);
        
        l_donGia.setFont(fo_l);
        gbc.gridy = 5;
        p1.add(l_donGia,gbc);
        //--------------------------add Text-----------------------------------
        txt_maNL.setEditable(false);
        txt_maNL.setFont(fo_t);
        txt_maNL.setPreferredSize(new Dimension(200,25));
        gbc.gridx=1;
        gbc.gridy=0;
        p1.add(txt_maNL,gbc);
        
        txt_tenNL.setFont(fo_t);
        txt_tenNL.setPreferredSize(new Dimension(200,25));
        gbc.gridy=1;
        p1.add(txt_tenNL,gbc);
        
        dc_ngayNhap.setFont(fo_t);
        dc_ngayNhap.setPreferredSize(new Dimension(200,25));
        gbc.gridy=2;
        p1.add(dc_ngayNhap,gbc);
        
        spr_soLuong.setFont(fo_t);
        spr_soLuong.setPreferredSize(new Dimension(200,25));
        gbc.gridy=3;
        p1.add(spr_soLuong,gbc);
        
        cb_donVi.setFont(fo_t);
        cb_donVi.setPreferredSize(new Dimension(200,25));
        cb_donVi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "Đồng", "Cái", "Hộp", "Bộ" }));
        cb_donVi.setEditable(false);
        gbc.gridy=4;
        p1.add(cb_donVi,gbc);
        
        spr_donGia.setFont(fo_t);
        spr_donGia.setPreferredSize(new Dimension(200,25));
        gbc.gridy=5;
        p1.add(spr_donGia,gbc);
        //--------------------------add Button-----------------------------------
        JPanel j_b = new JPanel();
        j_b.setPreferredSize(new Dimension(300,200));
        j_b.setLayout(new FlowLayout(100,10,10));
        
        btn_del.setBackground(Color.WHITE);
        btn_edit.setBackground(Color.WHITE);
        btn_add.setBackground(Color.WHITE);
        btn_clear.setBackground(Color.WHITE);
        btn_edit.setEnabled(false);
        btn_del.setEnabled(false);
        j_b.add(btn_add);
        j_b.add(btn_edit);
        j_b.add(btn_del);
        j_b.add(btn_clear);
        btn_add.addActionListener(this);
        btn_edit.addActionListener(this);
        btn_del.addActionListener(this);
        btn_clear.addActionListener(this);
        
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        p1.add(j_b,gbc);
        //</editor-fold>
        model.addColumn("Mã nguyên liệu");
        model.addColumn("Tên nguyên liệu");
        model.addColumn("Ngày nhập");
        model.addColumn("Số lượng");
        model.addColumn("Đơn vị tính");
        model.addColumn("Đơn giá");
        
        this.add(new JScrollPane(td),BorderLayout.CENTER);
        this.add(p2,BorderLayout.NORTH);
        this.add(p1,BorderLayout.WEST);
        //<editor-fold defaultstate="collapsed" desc="Event">
        //--------------------------Select--------------------------------------
        final ListSelectionModel sel = td.getSelectionModel();
        sel.addListSelectionListener((ListSelectionEvent e) -> {
            if(!sel.isSelectionEmpty()){
                if(!sel.isSelectionEmpty()){
                    btn_add.setEnabled(false);
                    btn_edit.setEnabled(true);
                    btn_del.setEnabled(true);
                    item_id=sel.getMinSelectionIndex();
                    enableInput(false);
                    isSelected=true;
                    setText();
                } else {
                    isSelected=false;
                }
            }
        });
        btn_clear.addActionListener((e) -> {
            clearMode();
        });
        mi_exit.addActionListener((e) -> {
            try {
                frmHome home = new frmHome();
                home.setVisible(true);
                dispose();
            } catch (IOException ex) {}
        });
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    frmHome home = new frmHome();
                    home.setVisible(true);
                } catch (IOException ex) {}
            }
        });
        //</editor-fold>
    }
    //<editor-fold defaultstate="collapsed" desc="Event2">
    public void addListener (ActionListener log){
        btn_add.addActionListener(log);
    }
    
    public void editListener (ActionListener log){
        btn_edit.addActionListener(log);
    }
    
    public void delListener (ActionListener log){
        btn_del.addActionListener(log);
    }
    public void searchListener (ActionListener log){
        btn_search.addActionListener(log);
    }
    public void clearListener (ActionListener log){
        btn_clear.addActionListener(log);
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Method">
    public String getSearch(){
        String s = txt_search.getText().trim().replace(" ", "_");
        return s;
    }
    public boolean editMode(){
        if(mode == 1){
            mode = 0;
            btn_edit.setBackground(Color.WHITE);
            btn_del.setEnabled(true);
            td.setEnabled(true);
            return true;
        } else {
            mode = 1;
            btn_edit.setBackground(Color.YELLOW);
            btn_del.setEnabled(false);
            td.setEnabled(false);
            enableInput(true);
            return false;
        }
    }
    public int getItem_id() {
        return item_id;
    }
    public boolean checkBlank(){
        int dg = Integer.parseInt(spr_donGia.getValue().toString());
        if(txt_tenNL.getText().trim().isEmpty()||cb_donVi.getSelectedItem().toString().trim().isEmpty()||dg==0){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!", "Thông báo", 1);
            return true;
        } else {
            return false;
        }
    }
    public QLNguyenLieu getInfo(){
        if(checkBlank()){
            return null;
        } else {
            String a = txt_maNL.getText();
            String b = txt_tenNL.getText();
            Date g1 = dc_ngayNhap.getDate();
            java.sql.Date c = new java.sql.Date(g1.getTime());
            int d = Integer.parseInt(spr_soLuong.getValue().toString());
            String e = cb_donVi.getSelectedItem().toString();
            int f = (int) spr_donGia.getValue();
            QLNguyenLieu nl = new QLNguyenLieu(a,b,c,d,e,f);
            return nl;
        }
    }
    public void setText(){
        txt_maNL.setText(arr.get(item_id).getMaNL());
        txt_tenNL.setText(arr.get(item_id).getTenNL());
        java.util.Date  utilDate = new java.util.Date(arr.get(item_id).getNgayNhap().getTime());
        dc_ngayNhap.setDate(utilDate);
        spr_soLuong.setValue(arr.get(item_id).getSoLuong());
        cb_donVi.setSelectedItem(arr.get(item_id).getDvTinh());
        spr_donGia.setValue(Integer.valueOf(arr.get(item_id).getDonGia()));
    }
    public void clearMode(){
        clearText();
        mode = 0;
        btn_edit.setBackground(Color.WHITE);
        enableInput(true);
        td.setEnabled(true);
        btn_add.setEnabled(true);
        btn_edit.setEnabled(false);
        btn_del.setEnabled(false);
        td.clearSelection();
    }
    public void clearText(){
        txt_maNL.setText("");
        txt_tenNL.setText("");
        dc_ngayNhap.setDate(new java.util.Date("01/01/2024"));
        spr_soLuong.setValue(0);
        cb_donVi.setSelectedIndex(0);
        spr_donGia.setValue(0);
    }
    public void enableInput(boolean a){
        txt_tenNL.setEditable(a);
        dc_ngayNhap.setEnabled(a);
        spr_soLuong.setEnabled(a);
        spr_donGia.setEnabled(a);
    }
    public void loadTable(ArrayList<QLNguyenLieu> arr){
        int rc = model.getRowCount();
        for(int i=0;i<rc;i++){
            model.removeRow(0);
        }
        Object r[] = new Object[6];
        for(int i=0;i<arr.size();i++){
            r[0] = arr.get(i).getMaNL();
            r[1] = arr.get(i).getTenNL();
            r[2] = arr.get(i).getNgayNhap();
            r[3] = arr.get(i).getSoLuong();
            r[4] = arr.get(i).getDvTinh();
            r[5] = arr.get(i).getDonGia();
            model.addRow(r);
        }
        this.arr = arr;
        clearMode();
    }
    //</editor-fold>
    @Override
    public void actionPerformed(ActionEvent e) {}
}
