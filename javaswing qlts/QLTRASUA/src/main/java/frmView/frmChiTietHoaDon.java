/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frmView;

import Controller.ExcelFileExporter;
import Controller.HoaDonData;
import Controller.MenuData;
import Controller.NguyenLieuData;
import Controller.NhanVienData;
import Model.ChiTietHoaDon;
import Model.QLHoaDon;
import Model.QLMenu;
import Model.QLNguyenLieu;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
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
import org.apache.hc.core5.http.ParseException;

/**
 *
 * @author ad
 */
public class frmChiTietHoaDon extends JFrame implements ActionListener{
    //<editor-fold defaultstate="collapsed" desc="Var">
    boolean tbdv_isSelected = false, tbhd_isSelected = false;
    int tbdv_item_id, tbhd_item_id,n;
    private ArrayList<QLNguyenLieu> arr_nl = new ArrayList();
    private ArrayList<ChiTietHoaDon> arr_hd = new ArrayList();
    private ArrayList<QLMenu> arr_mn = new ArrayList();
    MenuData mn = new MenuData("");
    NguyenLieuData nl = new NguyenLieuData("");
    QLHoaDon chd;
    //---------------------------------------------------------------------------
    
    JPanel p_left = new JPanel();//_________________
    JLabel tile1 = new JLabel("MENU");
    JButton btn_search = new JButton("TÌM KIẾM");
    JTextField txt_search = new JTextField();
    JComboBox<String> cb_filter = new JComboBox<>();
    JTable tbdv = new JTable();
    DefaultTableModel model_dv = (DefaultTableModel) tbdv.getModel();
    JLabel l_pic = new JLabel();
    
    JLabel l_soLuong = new JLabel(" Số lượng: ");
    SpinnerNumberModel snm_soLuong = new SpinnerNumberModel(0, 0, 500, 1);
    JSpinner spr_soLuong = new JSpinner(snm_soLuong);
    JButton btn_add = new JButton("THÊM MÓN");
    JButton btn_del = new JButton("XÓA MÓN");
    
    JPanel p_right = new JPanel();//_________________
    JLabel tile2 = new JLabel("HÓA ĐƠN");
    JLabel l_maHD = new JLabel();
    JLabel l_tenNV = new JLabel();
    JLabel l_ngayLap = new JLabel();
    JLabel l_ban = new JLabel();
    JTable tbhd = new JTable();
    DefaultTableModel model_hd = (DefaultTableModel) tbhd.getModel();
    JLabel l_thanhTien = new JLabel();
    JButton btn_excel = new JButton("Excel");
    
    JMenuBar mb = new JMenuBar();
    JMenu m_hethong = new JMenu("Hệ thống");
    JMenuItem mi_exit = new JMenuItem("Thoát");
    JLabel l_preAcc = new JLabel("Tài khoản: ");
    JLabel l_acc = new JLabel();
    //</editor-fold>
    public frmChiTietHoaDon(QLHoaDon hd) throws ParseException, IOException {
        chd=hd;
        btn_excel.setEnabled(false);
        if(chd.getTinhTrang().equals("Đã thanh toán")){
            tbdv.setEnabled(false);
            tbhd.setEnabled(false);
            btn_excel.setEnabled(true);
        }
        this.setSize(1200,800);
        this.setLocation(50, 70);
        this.setTitle("Chi tiết hóa đơn");
        this.setLayout(new BorderLayout());
        Border pad = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border padB = BorderFactory.createLineBorder(Color.BLACK);
        //<editor-fold defaultstate="collapsed" desc="Menu">
        l_acc.setText(NhanVienData.user);
        m_hethong.add(mi_exit);
        mb.add(m_hethong);
        mb.add(l_preAcc);
        mb.add(l_acc);
        this.add(mb,BorderLayout.NORTH);
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Panel_left">
        p_left.setLayout(new BorderLayout());
        //setup tiêu đề
        JPanel pL1 = new JPanel();
        pL1.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc= new GridBagConstraints();
        gbc.weightx = 1;
        gbc.weighty = 1.0;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        
        Font fo_tile = new Font("Serif", Font.BOLD,26);
        tile1.setFont(fo_tile);
        tile1.setHorizontalAlignment(JLabel.CENTER);
        tile1.setBorder(pad);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        pL1.add(tile1,gbc);
        //setup thanh tìm kiếm
        btn_search.setBackground(Color.WHITE);
        btn_search.setPreferredSize(new Dimension(75,25));
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        pL1.add(btn_search,gbc);
        
        Font fo_t = new Font("Serif", Font.PLAIN,14);
        txt_search.setFont(fo_t);
        txt_search.setPreferredSize(new Dimension(200,25));
        gbc.gridx=1;
        pL1.add(txt_search,gbc);
        
        p_left.add(pL1,BorderLayout.NORTH);//thêm panel phụ 1
        
        //---------------------------------------------------------------------------------
        JPanel pL2 = new JPanel();
        pL2.setLayout(new BorderLayout());
        //setup combo box
        cb_filter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Menu", "Nguyên liệu"}));
        cb_filter.setEditable(false);
        pL2.add(cb_filter,BorderLayout.NORTH);
        //setup bảng
        model_dv.addColumn("Món");
        model_dv.addColumn("Số lượng");
        model_dv.addColumn("Giá");
        pL2.add(new JScrollPane(tbdv),BorderLayout.CENTER);
        
        //setup ảnh
        l_pic.setPreferredSize(new Dimension(250,250));
        l_pic.setBorder(padB);
        l_pic.setHorizontalAlignment(JLabel.CENTER);
        pL2.add(l_pic,BorderLayout.SOUTH);
        
        p_left.add(pL2,BorderLayout.CENTER);//thêm panel phụ 2
        //---------------------------------------------------------------------------------
        JPanel pL3 = new JPanel();
        pL3.setBorder(pad);
        pL3.setLayout(new GridLayout(1,3,10,10));
        
        Font fo_l = new Font("Serif", Font.BOLD,14);
        l_soLuong.setFont(fo_l);
        pL3.add(l_soLuong,gbc);
        
        spr_soLuong.setFont(fo_t);
        pL3.add(spr_soLuong,gbc);
        
        btn_add.setBackground(Color.WHITE);
        btn_add.setEnabled(false);
        pL3.add(btn_add);
        btn_add.addActionListener(this);
        
        p_left.add(pL3,BorderLayout.SOUTH);//thêm panel phụ 3

        this.add(p_left,BorderLayout.WEST);
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Panel_right">
        p_right.setLayout(new BorderLayout());
        p_right.setBorder(pad);
        //setup tiêu đề
        tile2.setFont(fo_tile);
        tile2.setHorizontalAlignment(JLabel.CENTER);
        tile2.setBorder(pad);
        p_right.add(tile2,BorderLayout.NORTH);
        //----------------------------setup label
        JPanel pR11 = new JPanel();
        pR11.setBorder(pad);
        pR11.setLayout(new GridLayout(2, 2, 10, 10));
        
        //config element
        l_maHD.setFont(fo_l);
        l_tenNV.setFont(fo_l);
        l_ngayLap.setFont(fo_l);
        l_ban.setFont(fo_l);
       
        l_maHD.setText("Mã hóa đơn: " + hd.getMaHD());
        l_tenNV.setText("Nhân viên: " + hd.getTenNhanVien());
        
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String strDate = dateFormat.format(hd.getNgayLap());
        l_ngayLap.setText("Ngày: " + strDate);
        l_ban.setText("Bàn: "+hd.getMaBan());
        //add
        pR11.add(l_maHD);
        pR11.add(l_tenNV);
        pR11.add(l_ngayLap);
        pR11.add(l_ban);
        //------------------------table
        model_hd.addColumn("Dịch vụ");
        model_hd.addColumn("Số lượng");
        model_hd.addColumn("Đơn giá");
        model_hd.addColumn("Thành tiền");
        
        JPanel pR1 = new JPanel();
        pR1.setLayout(new BorderLayout());
        pR1.add(pR11,BorderLayout.NORTH);
        pR1.add(new JScrollPane(tbhd),BorderLayout.CENTER);
        
        p_right.add(pR1,BorderLayout.CENTER);
        //setup xóa và thành tiền
        JPanel pR2 = new JPanel();
        pR2.setLayout(new BorderLayout());
        
        btn_del.setBackground(Color.WHITE);
        btn_del.setEnabled(false);
        pR2.add(btn_del,BorderLayout.WEST);
        btn_del.addActionListener(this);
        
        l_thanhTien.setFont(fo_l);
        l_thanhTien.setHorizontalAlignment(JLabel.RIGHT);
        pR2.add(l_thanhTien,BorderLayout.CENTER);
        pR2.setBorder(pad);
        
        btn_excel.setBackground(Color.WHITE);
        pR2.add(btn_excel,BorderLayout.EAST);
        
        p_right.add(pR2,BorderLayout.SOUTH);
        
        this.add(p_right,BorderLayout.CENTER);
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Event">
        btn_excel.addActionListener((e) -> {
            String[] headers = new String[] {"Tên sản phẩm","Số lượng","Đơn giá","Thành tiền"};
            String fileName = "Hóa đơn.xlsx";
            ExcelFileExporter excelFileExporter = new ExcelFileExporter();
            excelFileExporter.exportChiTietHoaDonExcelFile(chd, arr_hd, headers, fileName);
        });
        btn_search.addActionListener((e) -> {
            spr_soLuong.setValue(0);
            if(cb_filter.getSelectedIndex()==0){
                try {
                    tbdv.clearSelection();
                    loadTableMN(txt_search.getText());
                } catch (ParseException | IOException ex) {}
            } else {
                try {
                    tbdv.clearSelection();
                    loadTableNL(txt_search.getText());
                } catch (IOException | ParseException ex) {}
            }
        });
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    HoaDonData hdfrm = new HoaDonData();
                } catch (IOException | ParseException ex) {
                    Logger.getLogger(frmChiTietHoaDon.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        mi_exit.addActionListener((e) -> {
            try {
                HoaDonData hdfrm = new HoaDonData();
                dispose();
            } catch (IOException | ParseException ex) {
                Logger.getLogger(frmChiTietHoaDon.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        final ListSelectionModel sel_dv = tbdv.getSelectionModel();
        sel_dv.addListSelectionListener((ListSelectionEvent e) -> {
            if(!sel_dv.isSelectionEmpty()){
                spr_soLuong.setValue(0);
                tbdv_item_id = sel_dv.getMinSelectionIndex();
                btn_add.setEnabled(true);
                tbdv_isSelected=true;
                setPic();
            } else {
                btn_add.setEnabled(false);
                tbdv_isSelected=false;
            }
        });
        cb_filter.addActionListener((e) -> {
            spr_soLuong.setValue(0);
            if(cb_filter.getSelectedIndex()==0){
                try {
                    tbdv.clearSelection();
                    tile1.setText("MENU");
                    loadTableMN("");
                    pL2.add(l_pic,BorderLayout.SOUTH);
                } catch (ParseException | IOException ex) {}
            } else {
                try {
                    tbdv.clearSelection();
                    tile1.setText("DANH SÁCH NGUYÊN LIỆU");
                    loadTableNL("");
                    pL2.remove(l_pic);
                } catch (IOException | ParseException ex) {}
            }
        });
        final ListSelectionModel sel_hd = tbhd.getSelectionModel();
        sel_hd.addListSelectionListener((ListSelectionEvent e) -> {
            if(!sel_hd.isSelectionEmpty()){
                tbhd_item_id = sel_hd.getMinSelectionIndex();
                btn_del.setEnabled(true);
                tbhd_isSelected=true;
            } else {
                btn_del.setEnabled(false);
                tbhd_isSelected=false;
            }
        });
        //</editor-fold>
    }
    //<editor-fold defaultstate="collapsed" desc="Event2">
    public void delListener (ActionListener log){
        btn_del.addActionListener(log);
    }
    public void addListener (ActionListener log){
        btn_add.addActionListener(log);
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Method">

    public QLHoaDon getChd() {
        return chd;
    }
    
    public String getMaHD(){
        return chd.getMaHD();
    }
    public String getMaDV(){
        if(cb_filter.getSelectedIndex()==0){
            return arr_mn.get(tbdv_item_id).getMaMon();
        } else {
            return arr_nl.get(tbdv_item_id).getMaNL();
        }
    }
    public String getSoLuong(){
        if(Integer.parseInt(spr_soLuong.getValue().toString())==0){
            JOptionPane.showMessageDialog(null, "Chọn số lượng muốn thêm", "Thông báo", 1);
            return "";
        } else {
            return spr_soLuong.getValue().toString();
        }
    }
    public String getThanhTien(){
        int r,n=Integer.parseInt(spr_soLuong.getValue().toString());
        if(cb_filter.getSelectedIndex()==0){
            r = arr_mn.get(tbdv_item_id).getGia() * n;
        } else {
            r = arr_nl.get(tbdv_item_id).getDonGia() * n;
        }
        return Integer.toString(r);
    }
    public int getTbhd_item_id() {
        return tbhd_item_id;
    }
    
    public void loadTable(ArrayList<ChiTietHoaDon> arr) throws ParseException, IOException{
        int rc = model_hd.getRowCount();
        for(int i=0;i<rc;i++){
            model_hd.removeRow(0);
        }
        Object r[] = new Object[4];
        int tong=0;
        for(int i=0;i<arr.size();i++){
            r[0] = arr.get(i).getTenDV();
            r[1] = arr.get(i).getSoLuong();
            r[2] = arr.get(i).getDonGia();
            r[3] = arr.get(i).getThanhTien();
            tong = tong + arr.get(i).getThanhTien();
            model_hd.addRow(r);
        }
        this.arr_hd = arr;
        chd.setThanhTien(tong);
        if(cb_filter.getSelectedIndex()==0){
            loadTableMN("");
        } else {
            loadTableNL("");
        }
        txt_search.setText("");
        l_thanhTien.setText("Tổng: "+chd.getThanhTien()+"   Tình Trạng: "+chd.getTinhTrang());
    }
    public void setPic(){
        if(cb_filter.getSelectedIndex()==0){
            try {
                File file = new File("");
                String currentDirectory = file.getAbsolutePath() + "/src/main/java";
                BufferedImage myPicture = ImageIO.read(new File(currentDirectory + arr_mn.get(tbdv_item_id).getAnh()));
                l_pic.setIcon(new ImageIcon(myPicture.getScaledInstance(250, 250, Image.SCALE_SMOOTH)));
                l_pic.repaint();
            } catch (IOException ex) {
                Logger.getLogger(frmChiTietHoaDon.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void loadTableNL(String s) throws ParseException, IOException{
        this.arr_nl = nl.getNL(s);
        int rc = model_dv.getRowCount();
        for(int i=0;i<rc;i++){
            model_dv.removeRow(0);
        }
        Object r[] = new Object[3];
        for(int i=0;i<arr_nl.size();i++){
            r[0] = arr_nl.get(i).getTenNL();
            r[1] = arr_nl.get(i).getSoLuong();
            r[2] = arr_nl.get(i).getDonGia();
            model_dv.addRow(r);
        }
    }
    public void loadTableMN(String s) throws ParseException, IOException{
        this.arr_mn = mn.getDV(s);
        int rc = model_dv.getRowCount();
        for(int i=0;i<rc;i++){
            model_dv.removeRow(0);
        }
        Object r[] = new Object[3];
        for(int i=0;i<arr_mn.size();i++){
            r[0] = arr_mn.get(i).getTenMon();
            r[1] = arr_mn.get(i).getSoLuong();
            r[2] = arr_mn.get(i).getGia();
            model_dv.addRow(r);
        }
    }
    //</editor-fold>
    @Override
    public void actionPerformed(ActionEvent e) {} 
}
