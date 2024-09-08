/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frmView;

import Controller.MenuData;
import Controller.NhanVienData;
import Controller.ThongKeData;
import Model.QLMenu;
import Model.QLThongKe;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ComboBoxEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.hc.core5.http.ParseException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author ad
 */
public class frmThongKe extends JFrame{
    //<editor-fold defaultstate="collapsed" desc="Var">
    ArrayList<QLThongKe> arr;
    ArrayList<QLMenu> arr_mn;
    JMenuBar mb = new JMenuBar();
    JMenu m_hethong = new JMenu("Hệ thống");
    JMenuItem mi_exit = new JMenuItem("Thoát");
    JLabel l_preAcc = new JLabel("Tài khoản: ");
    JLabel l_acc = new JLabel();
    
    JTabbedPane tabbedPane = new JTabbedPane();
    
    JPanel p_1 = new JPanel();
    DefaultCategoryDataset dataset;
    JFreeChart chart;
    JTable td = new JTable();
    DefaultTableModel model = (DefaultTableModel) td.getModel();
    
    JPanel p_2 = new JPanel();
    JTable td2 = new JTable();
    DefaultTableModel model2 = (DefaultTableModel) td2.getModel();
    DefaultCategoryDataset dataset1;
    JFreeChart chart1;
    //</editor-fold>
    public frmThongKe() throws ParseException, IOException{
        this.setSize(1500,800);
        this.setLocation(50, 70);
        this.setTitle("Thống kê");
        this.setLayout(new BorderLayout());
        
        //<editor-fold defaultstate="collapsed" desc="Menu">
        l_acc.setText(NhanVienData.user);
        m_hethong.add(mi_exit);
        mb.add(m_hethong);
        mb.add(l_preAcc);
        mb.add(l_acc);
        this.add(mb,BorderLayout.NORTH);
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="P1">
        p_1.setLayout(new BorderLayout());
        model.addColumn("Tháng");
        model.addColumn("Doanh thu");
        p_1.add(new JScrollPane(td),BorderLayout.EAST);
        tabbedPane.addTab("Doanh thu 6 tháng gần nhất", p_1);
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="P2">
        p_2.setLayout(new BorderLayout());
        model2.addColumn("Tên món");
        model2.addColumn("Số lượng bán được");
        p_2.add(new JScrollPane(td2),BorderLayout.EAST);
        MenuData mn = new MenuData("");
        loadTable2(mn.getTop5());
        tabbedPane.addTab("Top 5 món được mua nhiều nhất", p_2);
        //</editor-fold>
        this.add(tabbedPane,BorderLayout.CENTER);
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
    }
    //<editor-fold defaultstate="collapsed" desc="Method">
    public void loadTable1(ArrayList<QLThongKe> arr){
        int rc = model.getRowCount();
        for(int i=0;i<rc;i++){
            model.removeRow(0);
        }
        Object r[] = new Object[2];
        for(int i=0;i<arr.size();i++){
            r[0] = arr.get(i).getMaTK().replace("M", "-");
            r[1] = arr.get(i).getDoanhThu();
            model.addRow(r);
        }
        this.arr = arr;
    }
    public void loadTable2(ArrayList<QLMenu> arr){
        int rc = model2.getRowCount();
        for(int i=0;i<rc;i++){
            model2.removeRow(0);
        }
        Object r[] = new Object[2];
        for(int i=0;i<arr.size();i++){
            r[0] = arr.get(i).getTenMon();
            r[1] = arr.get(i).getSoLuong();
            model2.addRow(r);
        }
        this.arr_mn = arr;
    }
    public void loadChart(){
        //<editor-fold defaultstate="collapsed" desc="Chart1">
        dataset = new DefaultCategoryDataset();
        for(int i=arr.size()-1;i>=0;i--){
            dataset.setValue(arr.get(i).getDoanhThu(), "", arr.get(i).getMaTK().replace("M", "-"));
        }
        chart = ChartFactory.createLineChart("Doanh thu", "Tháng", "", dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
        p_1.add(chartPanel,BorderLayout.CENTER);
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Chart2">
        dataset1 = new DefaultCategoryDataset();
        for(int i=0;i<arr_mn.size();i++){
            dataset1.setValue(arr_mn.get(i).getSoLuong(), arr_mn.get(i).getTenMon(),"");
        }
        chart1 = ChartFactory.createBarChart("Top 5 món bán chạy", "", "", dataset1);
        ChartPanel chartPanel1 = new ChartPanel(chart1);
        chartPanel1.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
        p_2.add(chartPanel1,BorderLayout.CENTER);
        //</editor-fold>
    }
    //</editor-fold>
}