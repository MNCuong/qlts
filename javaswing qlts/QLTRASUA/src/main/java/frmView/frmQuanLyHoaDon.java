/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frmView;

import Controller.ChiTietHoaDonData;
import Controller.ExcelFileExporter;
import Controller.NhanVienData;
import Model.QLHoaDon;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import org.apache.hc.core5.http.ParseException;

/**
 *
 * @author ADMIN
 */
public class frmQuanLyHoaDon extends JFrame implements ActionListener{
    //<editor-fold defaultstate="collapsed" desc="Var">
    boolean isSelected = false;
    int item_id,mode=0;
    private ArrayList<QLHoaDon> arr = new ArrayList();
    //---------------------------------------------------------------------------
    JLabel tile = new JLabel("QUẢN LÝ HÓA ĐƠN");
    
    JPanel p1 = new JPanel();
    JTextField txt_search = new JTextField();
    JButton btn_search = new JButton("TÌM KIẾM");
    
    JPanel p2 = new JPanel();
    JButton btn_detail = new JButton("CHI TIẾT");
    JComboBox<String> cb_ban = new JComboBox<>();
    JButton btn_newOrder = new JButton("HÓA ĐƠN MỚI");
    JButton btn_edit = new JButton("SỬA");
    JButton btn_del = new JButton("XÓA");
    JButton btn_excel = new JButton("EXCEL");
    JCheckBox check_thanhToan = new JCheckBox("Đã thanh toán");
    
    JPanel p3 = new JPanel();
    JTable td = new JTable();
    DefaultTableModel model = (DefaultTableModel) td.getModel();
    
    JMenuBar mb = new JMenuBar();
    JMenu m_hethong = new JMenu("Hệ thống");
    JMenuItem mi_exit = new JMenuItem("Thoát");
    JLabel l_preAcc = new JLabel("Tài khoản: ");
    JLabel l_acc = new JLabel();
    JComboBox<String> cb_month = new JComboBox<>();
    //</editor-fold>
    public frmQuanLyHoaDon() {
        this.setSize(1200,800);
        this.setLocation(50, 70);
        this.setTitle("Quản lý hóa đơn");
        this.setLayout(new BorderLayout());
        Border pad = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        //<editor-fold defaultstate="collapsed" desc="Menu">
        l_acc.setText(NhanVienData.user);
        m_hethong.add(mi_exit);
        mb.add(m_hethong);
        mb.add(l_preAcc);
        mb.add(l_acc);
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Panel Top">
        p2.setLayout(new BorderLayout());
        
        p2.add(mb,BorderLayout.NORTH);
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
        //<editor-fold defaultstate="collapsed" desc="Panel Left">
        p1.setBorder(pad);
        p1.setLayout(new GridLayout(5, 1, 10, 10));
        
        btn_detail.setBackground(Color.WHITE);
        btn_detail.setEnabled(false);
        btn_newOrder.setBackground(Color.WHITE);
        check_thanhToan.setEnabled(false);
        btn_edit.setBackground(Color.WHITE);
        btn_edit.setEnabled(false);
        btn_del.setBackground(Color.WHITE);
        btn_del.setEnabled(false);
        cb_ban.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Đem về", "Bàn 001", "Bàn 002", "Bàn 003", "Bàn 004", "Bàn 005", "Bàn 006", "Bàn 007", "Bàn 008", "Bàn 009", "Bàn 010"}));
        cb_ban.setEditable(false);
        btn_excel.setBackground(Color.WHITE);
        
        JPanel p11 = new JPanel();
        p11.setLayout(new GridLayout(3, 1, 0, 0));
        p11.add(cb_ban);
        p11.add(check_thanhToan);
        p11.add(btn_edit);
        
        p1.add(btn_detail);
        p1.add(btn_newOrder);
        p1.add(p11);
        p1.add(btn_del);
        p1.add(btn_excel);
        btn_newOrder.addActionListener(this);
        btn_edit.addActionListener(this);
        btn_del.addActionListener(this);
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Panel Mid">
        p3.setLayout(new BorderLayout());
        
        cb_month.setModel(new javax.swing.DefaultComboBoxModel(get12Thang()));
        p3.add(cb_month,BorderLayout.NORTH);
        cb_month.addActionListener(this);
        
        model.addColumn("Mã hóa đơn");
        model.addColumn("Tên nhân viên");
        model.addColumn("Ngày lập");
        model.addColumn("Bàn");
        model.addColumn("Thành tiền");
        model.addColumn("Tình trạng");
        p3.add(new JScrollPane(td),BorderLayout.CENTER);
        //</editor-fold>
        this.add(p3,BorderLayout.CENTER);
        this.add(p1,BorderLayout.WEST);
        this.add(p2,BorderLayout.NORTH);
        //<editor-fold defaultstate="collapsed" desc="Event">
        btn_excel.addActionListener((e) -> {
            String[] headers = new String[] {"Mã hóa đơn","Tên nhân viên","Ngày lập","Bàn","Thành tiền","Tình trạng"};
            String fileName = "Quản lí hóa đơn.xlsx";
            ExcelFileExporter excelFileExporter = new ExcelFileExporter();
            excelFileExporter.exportHoaDonExcelFile(arr, headers, fileName);
        });
        btn_detail.addActionListener((e) -> {
            try {
                ChiTietHoaDonData cthd = new ChiTietHoaDonData(arr.get(item_id));
                dispose();
            } catch (IOException | ParseException ex) {
                Logger.getLogger(frmQuanLyHoaDon.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        final ListSelectionModel sel = td.getSelectionModel();
        sel.addListSelectionListener((ListSelectionEvent e) -> {
                if(!sel.isSelectionEmpty()){
                    item_id=sel.getMinSelectionIndex();
                    cb_ban.setSelectedItem(arr.get(item_id).getMaBan());
                    btn_detail.setEnabled(true);
                    if(arr.get(item_id).getTinhTrang().equals("Chưa thanh toán")){
                        btn_del.setEnabled(true);
                        btn_edit.setEnabled(true);
                        check_thanhToan.setSelected(false);
                    } else {
                        if(NhanVienData.phanQuyen==1){   
                            btn_del.setEnabled(true);
                            btn_edit.setEnabled(false);
                        }else{
                            btn_del.setEnabled(false);
                            btn_edit.setEnabled(false);
                        }
                        check_thanhToan.setSelected(true);
                    }
                    isSelected=true;
                } else {
                    btn_detail.setEnabled(false);
                    btn_edit.setEnabled(false);
                    btn_del.setEnabled(false);
                    isSelected=false;
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
    public void searchListener (ActionListener log){
        btn_search.addActionListener(log);
    }
    public void newOrderListener (ActionListener log){
        btn_newOrder.addActionListener(log);
    }
    public void editListener (ActionListener log){
        btn_edit.addActionListener(log);
    }
    public void delListener (ActionListener log){
        btn_del.addActionListener(log);
    }
    public void monthListener (ActionListener log){
        cb_month.addActionListener(log);
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Method">
    public String getMonth(){
        return cb_month.getSelectedItem().toString();
    }
    public Object[] get12Thang(){
        LocalDate date = LocalDate.now();
        ArrayList<String> dates = new ArrayList<>();
        dates.add("Toàn bộ");
        for(int i=0;i<6;i++){
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
            String strDate = dateFormat.format(java.sql.Date.valueOf(date.minusMonths(i)));
            dates.add(strDate);
        }
        return dates.toArray();
    }
    public QLHoaDon getInfo(){
        arr.get(item_id).setMaBan(getBan());
        if(check_thanhToan.isSelected()){
            arr.get(item_id).setTinhTrang("Đã thanh toán");
        } else {
            arr.get(item_id).setTinhTrang("Chưa thanh toán");
        }
        return arr.get(item_id);
    }
    public boolean editMode(){
        if(mode == 1){
            mode = 0;
            check_thanhToan.setEnabled(false);
            btn_edit.setBackground(Color.WHITE);
            btn_del.setEnabled(true);
            td.setEnabled(true);
            return true;
        } else {
            mode = 1;
            if(arr.get(item_id).getTinhTrang().equals("Chưa thanh toán")){
                check_thanhToan.setEnabled(true);
            }
            btn_edit.setBackground(Color.YELLOW);
            btn_del.setEnabled(false);
            td.setEnabled(false);
            return false;
        }
    }
    public int getItem_id() {
        return item_id;
    }
    public String getBan(){
        return cb_ban.getSelectedItem().toString();
    }
    public String getSearch(){
        String s = txt_search.getText().trim().replace(" ", "_");
        return s;
    }
    public void loadTable(ArrayList<QLHoaDon> arr){
        int rc = model.getRowCount();
        for(int i=0;i<rc;i++){
            model.removeRow(0);
        }
        Object r[] = new Object[6];
        for(int i=0;i<arr.size();i++){
            r[0] = arr.get(i).getMaHD();
            r[1] = arr.get(i).getTenNhanVien();
            r[2] = arr.get(i).getNgayLap();
            r[3] = arr.get(i).getMaBan();
            r[4] = arr.get(i).getThanhTien();
            r[5] = arr.get(i).getTinhTrang();
            model.addRow(r);
        }
        this.arr = arr;
    }
    //</editor-fold>
    @Override
    public void actionPerformed(ActionEvent e) {}
}
