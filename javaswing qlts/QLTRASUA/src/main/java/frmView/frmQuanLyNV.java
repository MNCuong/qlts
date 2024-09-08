/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frmView;

import Controller.ExcelFileExporter;
import Controller.NhanVienData;
import Model.QLNhanVien;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.Base64;
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
import javax.swing.JPasswordField;
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
public class frmQuanLyNV extends JFrame implements ActionListener {
    //<editor-fold defaultstate="collapsed" desc="Var">
    boolean isSelected = false;
    int item_id,mode=0;
    private ArrayList<QLNhanVien> arr = new ArrayList();
    //---------------------------------------------------------------------------
    SpinnerNumberModel snm_sal1 = new SpinnerNumberModel(0, 0, 2000000000, 100000);
    SpinnerNumberModel snm_sal2 = new SpinnerNumberModel(0, 0, 2000000000, 100000);
    SpinnerNumberModel snm_time = new SpinnerNumberModel(0, 0, 3, 1);
    SpinnerNumberModel snm_HSLuong = new SpinnerNumberModel(0, 0, 2, 0.1);
    //panel 1
    JLabel l_maNV = new JLabel("                                   Mã nhân viên:");
    JTextField txt_maNV = new JTextField();
    JLabel l_tenNV = new JLabel("                                   Tên nhân viên:");
    JTextField txt_tenNV = new JTextField();
    JLabel l_pass= new JLabel("                                   Password:");
    JPasswordField txt_pass = new JPasswordField();
    JLabel l_sdt = new JLabel("                                   Điện thoại:");
    JTextField txt_sdt = new JTextField();
    JLabel l_cccd = new JLabel("                                   CCCD:");
    JTextField txt_cccd = new JTextField();
    JLabel l_luong = new JLabel("                                   Tiền lương thanh toán:");
    JSpinner spr_luong = new JSpinner(snm_sal1);
    JLabel l_workDate = new JLabel("                                   Ngày làm việc:");
    JDateChooser dc_workDate = new JDateChooser();
    JLabel l_time = new JLabel("                                   Ca làm việc:");
    JSpinner spr_time = new JSpinner(snm_time);
    JLabel l_luongCB = new JLabel("                                   Lương cơ bản:");
    JSpinner spr_luongCB = new JSpinner(snm_sal2);
    JLabel l_email = new JLabel("                                   Email:");
    JTextField txt_email = new JTextField();
    JLabel l_HSLuong = new JLabel("                                   Hệ số lương:");
    JSpinner spr_HSLuong = new JSpinner(snm_HSLuong);
    JLabel l_phanQuyen = new JLabel("                                   Phân quyền:");
    JComboBox cb_phanQuyen = new JComboBox();
    JPanel p1 = new JPanel();
    JPanel p11 = new JPanel();
    JPanel p12 = new JPanel();
    JTable td = new JTable();
    DefaultTableModel model = (DefaultTableModel) td.getModel();
    JLabel tile = new JLabel("QUẢN LÝ NHÂN VIÊN");
    //panel 2
    JPanel p2 = new JPanel();
    JButton btn_add = new JButton("THÊM");
    JButton btn_edit = new JButton("SỬA");
    JButton btn_del = new JButton("XÓA");
    JButton btn_clear = new JButton("LÀM MỚI");
    JButton btn_search = new JButton("TÌM KIẾM");
    JButton btn_cal = new JButton("TÍNH TIỀN");
    JButton btn_excel = new JButton("EXCEL");
    JTextField txt_search = new JTextField();
    
    JMenuBar mb = new JMenuBar();
    JMenu m_hethong = new JMenu("Hệ thống");
    JMenuItem mi_exit = new JMenuItem("Thoát");
    JLabel l_preAcc = new JLabel("Tài khoản: ");
    JLabel l_acc = new JLabel();
    //</editor-fold>
    public frmQuanLyNV() {
        this.setTitle("Quản lý nhân viên");
        this.setSize(1600,600);
        this.setLocation(50, 70);
        this.setLayout(new BorderLayout());
        
        Font fo_tile = new Font("Serif", Font.BOLD,32);
        tile.setFont(fo_tile);
        tile.setHorizontalAlignment(JLabel.CENTER);
        JPanel p_tile = new JPanel();
        p_tile.setLayout(new BorderLayout());
        p_tile.add(tile,BorderLayout.CENTER);
        //<editor-fold defaultstate="collapsed" desc="Menu">
        l_acc.setText(NhanVienData.user);
        m_hethong.add(mi_exit);
        mb.add(m_hethong);
        mb.add(l_preAcc);
        mb.add(l_acc);
        p_tile.add(mb,BorderLayout.NORTH);
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Panel01">
        Border pad = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        p1.setBorder(pad);
        p1.setLayout(new BorderLayout());
        
        p11.setBorder(pad);
        p11.setLayout(new GridLayout(6, 4, 10, 10));
        
        //config element
        Font fo_l = new Font("Serif", Font.BOLD,14);
        Font fo_t = new Font("Serif", Font.PLAIN,14);
        l_maNV.setFont(fo_l);
        l_tenNV.setFont(fo_l);
        l_pass.setFont(fo_l);
        l_sdt.setFont(fo_l);
        l_cccd.setFont(fo_l);
        l_luong.setFont(fo_l);
        l_workDate.setFont(fo_l);
        l_time.setFont(fo_l);
        l_luongCB.setFont(fo_l);
        l_email.setFont(fo_l);
        l_HSLuong.setFont(fo_l);
        l_phanQuyen.setFont(fo_l);
        
        txt_maNV.setFont(fo_t);
        txt_maNV.setPreferredSize(new Dimension(300,25));
        txt_maNV.setEditable(false);
        txt_tenNV.setFont(fo_t);
        txt_tenNV.setPreferredSize(new Dimension(300,25));
        txt_pass.setFont(fo_t);
        txt_pass.setPreferredSize(new Dimension(300,25));
        txt_sdt.setFont(fo_t);
        txt_sdt.setPreferredSize(new Dimension(300,25));
        txt_cccd.setFont(fo_t);
        txt_cccd.setPreferredSize(new Dimension(300,25));
        spr_luong.setEnabled(false);
        spr_luong.setFont(fo_t);
        spr_luong.setPreferredSize(new Dimension(300,25));
        dc_workDate.setFont(fo_t);
        dc_workDate.setPreferredSize(new Dimension(300,25));
        dc_workDate.setDateFormatString("dd/MM/yyyy");
        dc_workDate.setFont(fo_t);
        spr_time.setFont(fo_t);
        spr_time.setPreferredSize(new Dimension(300,25));
        spr_luongCB.setFont(fo_t);
        spr_luongCB.setPreferredSize(new Dimension(300,25));
        txt_email.setFont(fo_t);
        txt_email.setPreferredSize(new Dimension(300,25));
        spr_HSLuong.setFont(fo_t);
        spr_HSLuong.setPreferredSize(new Dimension(300,25));
        cb_phanQuyen.setFont(fo_t);
        cb_phanQuyen.setPreferredSize(new Dimension(300,25));
        cb_phanQuyen.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"", "Nhân viên", "Quản lý"}));
        cb_phanQuyen.setEditable(false);
        
        model.addColumn("Mã nhân viên");
        model.addColumn("Tên nhân viên");
        model.addColumn("Password");
        model.addColumn("SDT");
        model.addColumn("Email");
        model.addColumn("CCCD");
        model.addColumn("Ngày làm việc");
        model.addColumn("Ca làm việc");
        model.addColumn("Lương cơ bản");
        model.addColumn("Hệ số lương");
        model.addColumn("Tiền lương");
        //add
        p11.add(l_maNV);
        p11.add(txt_maNV);
        p11.add(l_workDate);
        p11.add(dc_workDate);
        p11.add(l_tenNV);
        p11.add(txt_tenNV);
        p11.add(l_time);
        p11.add(spr_time);
        p11.add(l_pass);
        p11.add(txt_pass);
        p11.add(l_luongCB);
        p11.add(spr_luongCB);
        p11.add(l_sdt);
        p11.add(txt_sdt);
        p11.add(l_email);
        p11.add(txt_email);
        p11.add(l_cccd);
        p11.add(txt_cccd);
        p11.add(l_HSLuong);
        p11.add(spr_HSLuong);
        p11.add(l_luong);
        p11.add(spr_luong);
        p11.add(l_phanQuyen);
        p11.add(cb_phanQuyen);
        //table
        p12.setLayout(new BorderLayout());
        p12.add(new JScrollPane(td),BorderLayout.CENTER);
        
        p1.add(p11,BorderLayout.NORTH);
        p1.add(p12,BorderLayout.CENTER);
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Panel02">
        //config element
        txt_search.setFont(fo_t);
        txt_search.setPreferredSize(new Dimension(200,25));
        p2.setBorder(pad);
        p2.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        
        btn_del.setBackground(Color.WHITE);
        btn_edit.setBackground(Color.WHITE);
        btn_add.setBackground(Color.WHITE);
        btn_clear.setBackground(Color.WHITE);
        btn_search.setBackground(Color.WHITE);
        btn_cal.setBackground(Color.WHITE);
        btn_excel.setBackground(Color.WHITE);
        btn_edit.setEnabled(false);
        btn_del.setEnabled(false);
        
        //add
        gbc.weightx = 1;
        gbc.weighty = 1.0;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        
        gbc.gridwidth=2;
        gbc.gridx=0;
        gbc.gridy=0;
        p2.add(txt_search,gbc);
        
        gbc.gridy=1;
        p2.add(btn_search,gbc);
        
        gbc.gridy=2;
        p2.add(btn_cal,gbc);
        
        gbc.gridwidth=1;
        gbc.gridy=3;
        p2.add(btn_add,gbc);
        
        gbc.gridx=1;
        p2.add(btn_edit,gbc);
        
        gbc.gridx=0;
        gbc.gridy=4;
        p2.add(btn_del,gbc);
        
        gbc.gridx=1;
        p2.add(btn_clear,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        p2.add(btn_excel,gbc);
        
        btn_add.addActionListener(this);
        btn_edit.addActionListener(this);
        btn_del.addActionListener(this);
        btn_clear.addActionListener(this);
        btn_search.addActionListener(this);
        btn_cal.addActionListener(this);
        
        JPanel bl = new JPanel();
        bl.setLayout(new GridLayout(10, 1, 10, 10));
        bl.setPreferredSize(new Dimension(100,400));
        gbc.gridwidth=2;
        gbc.gridy=5;
        gbc.gridx=0;
        p2.add(bl,gbc);
        //</editor-fold>
        this.add(p_tile,BorderLayout.NORTH);
        this.add(p1,BorderLayout.CENTER);
        this.add(p2,BorderLayout.EAST);
        //<editor-fold defaultstate="collapsed" desc="Event">
        //--------------------------Select--------------------------------------
        btn_excel.addActionListener((e) -> {
            String[] headers = new String[] {"Mã nhân viên","Tên nhân viên","SĐT","Email","CCCD","Ngày làm việc","Ca làm","Lương cơ bản","Hệ số lương","Tổng lương"};
            String fileName = "Quản lí nhân viên.xlsx";
            ExcelFileExporter excelFileExporter = new ExcelFileExporter();
            excelFileExporter.exportNhanVienExcelFile(arr, headers, fileName);
        });
        final ListSelectionModel sel = td.getSelectionModel();
        sel.addListSelectionListener((ListSelectionEvent e) -> {
            if(!sel.isSelectionEmpty()){
                if(!sel.isSelectionEmpty()){
                    btn_add.setEnabled(false);
                    btn_edit.setEnabled(true);
                    btn_del.setEnabled(true);
                    btn_cal.setEnabled(true);
                    item_id=sel.getMinSelectionIndex();
                    enableInput(false);
                    isSelected=true;
                    setText();
                } else {
                    isSelected=false;
                }
            }
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
    public void calListener (ActionListener log){
        btn_cal.addActionListener(log);
    }
    public void searchListener (ActionListener log){
        btn_search.addActionListener(log);
    }
    public void clearListener (ActionListener log){
        btn_clear.addActionListener(log);
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Method">
    public String decode(String pass){
        return new String(Base64.getDecoder().decode(pass));
    }
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
    public void enableInput(boolean a){
        txt_tenNV.setEditable(a);
        txt_pass.setEditable(a);
        txt_sdt.setEditable(a);
        txt_email.setEditable(a);
        txt_cccd.setEditable(a);
        dc_workDate.setEnabled(a);
        spr_time.setEnabled(a);
        spr_luongCB.setEnabled(a);
        spr_HSLuong.setEnabled(a);
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
        btn_cal.setEnabled(false);
        td.clearSelection();
    }
    public void clearText(){
        txt_maNV.setText("");
        txt_tenNV.setText("");
        txt_pass.setText("");
        txt_sdt.setText("");
        txt_email.setText("");
        txt_cccd.setText("");
        dc_workDate.setDate(new java.util.Date("01/01/2000"));
        spr_time.setValue(0);
        spr_luongCB.setValue(0);
        spr_HSLuong.setValue(0);
        spr_luong.setValue(0);
        cb_phanQuyen.setSelectedIndex(0);
    }
    public void setText(){
        txt_maNV.setText(arr.get(item_id).getMaNhanVien());
        txt_tenNV.setText(arr.get(item_id).getTenNhanVien());
        txt_pass.setText(decode(arr.get(item_id).getPassword()));
        txt_sdt.setText(arr.get(item_id).getPhone());
        txt_email.setText(arr.get(item_id).getEmail());
        txt_cccd.setText(arr.get(item_id).getCMND());
        java.util.Date  utilDate = new java.util.Date(arr.get(item_id).getNgayLamViec().getTime());
        dc_workDate.setDate(utilDate);
        spr_time.setValue(Integer.valueOf(arr.get(item_id).getCaLamViec()));
        spr_luongCB.setValue(Integer.valueOf(arr.get(item_id).getLuongCoBan()));
        spr_HSLuong.setValue(Double.valueOf(arr.get(item_id).getHeSoLuong()));
        if(arr.get(item_id).getTienLuong() != null){
        spr_luong.setValue(Integer.valueOf(arr.get(item_id).getTienLuong()));}
        cb_phanQuyen.setSelectedIndex(arr.get(item_id).getPhanQuyen()+1);
    }
    public void loadTable(ArrayList<QLNhanVien> arr){
        int rc = model.getRowCount();
        for(int i=0;i<rc;i++){
            model.removeRow(0);
        }
        Object r[] = new Object[11];
        for(int i=0;i<arr.size();i++){
            r[0] = arr.get(i).getMaNhanVien();
            r[1] = arr.get(i).getTenNhanVien();
            r[2] = arr.get(i).getPassword();
            r[3] = arr.get(i).getPhone();
            r[4] = arr.get(i).getEmail();
            r[5] = arr.get(i).getCMND();
            r[6] = arr.get(i).getNgayLamViec();
            r[7] = arr.get(i).getCaLamViec();
            r[8] = arr.get(i).getLuongCoBan();
            r[9] = arr.get(i).getHeSoLuong();
            r[10] = arr.get(i).getTienLuong();
            model.addRow(r);
        }
        this.arr = arr;
        clearMode();
    }
    public boolean checkBlank(){
        if(txt_tenNV.getText().trim().isEmpty()||txt_pass.getText().trim().isEmpty()||txt_sdt.getText().trim().isEmpty()||txt_cccd.getText().trim().isEmpty()||Integer.parseInt(spr_time.getValue().toString())==0||Integer.parseInt(spr_luongCB.getValue().toString())==0||Double.parseDouble(spr_HSLuong.getValue().toString())==0||cb_phanQuyen.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!", "Thông báo", 1);
            return true;
        } else {
            if(txt_sdt.getText().matches("[0-9]+") && txt_cccd.getText().matches("[0-9]+")){
                return false;
            } else {
                JOptionPane.showMessageDialog(null, "Số điện thoại hoặc CCCD không hợp lệ!", "Thông báo", 1);
                return true;
            }
        }
    }

    public int getItem_id() {
        return item_id;
    }
    
    public QLNhanVien getInfo(){
        if(checkBlank()){
            return null;
        } else {
            String a = txt_maNV.getText().trim();
            String b = txt_tenNV.getText().trim();
            String c = txt_pass.getText().trim();
            String d = txt_sdt.getText().trim();
            String e = txt_email.getText().trim();
            String f = txt_cccd.getText().trim();
            Date g1 = dc_workDate.getDate();
            java.sql.Date g = new java.sql.Date(g1.getTime());
            String h = spr_time.getValue().toString();
            String i = spr_luongCB.getValue().toString();
            String k = spr_HSLuong.getValue().toString();
            String l = spr_luong.getValue().toString();
            int m = cb_phanQuyen.getSelectedIndex()-1;
            QLNhanVien nv = new QLNhanVien(a,b,c,d,e,f,g,h,i,k,l,m);
            return nv;
        }
    }
    //</editor-fold>
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}
