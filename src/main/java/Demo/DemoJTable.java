/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Demo;

import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class DemoJTable extends JFrame {

    private JTable tblSanPham;
    private JButton btnThem, btnXoa;
    DefaultTableModel model;
    private JTextField txtMa, txtTen, txtGia;

    public DemoJTable(String title) {
        super(title);
        createGUI();
        processEvent();
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        DemoJTable jt = new DemoJTable("Demo JTable");
        jt.setVisible(true);
    }

    private void createGUI() {
        Object[][] data = {
            {"001", "Gạo", "450000"},
            {"002", "Đường", "470000"},};

        String[] columnNames = {"Mã sản phẩm", "Tên sản phẩm", "Giá bán"};

        model = new DefaultTableModel(data, columnNames);

        tblSanPham = new JTable(model);
        JScrollPane scrollTable = new JScrollPane(tblSanPham);

//        model.addColumn();
        JPanel p = new JPanel();

        p.add(new JLabel("Mã"));
        p.add(txtMa = new JTextField(5));
        p.add(new JLabel("Tên"));
        p.add(txtTen = new JTextField(10));
        p.add(new JLabel("Giá"));
        p.add(txtGia = new JTextField(10));
        p.add(btnThem = new JButton("Thêm"));
        p.add(btnXoa = new JButton("Xóa"));
        
        add(scrollTable, BorderLayout.CENTER);
        add(p, BorderLayout.NORTH);
    }

    private void processEvent() {
        btnThem.addActionListener((e) -> {
            String error = "";
            try {
                String maSP = txtMa.getText(); 
                String tenSP = txtTen.getText();
                if (maSP.length() == 0) {
                    error = "Chưa nhập mã sản phẩm!";
                }
                if (tenSP.length() == 0) {
                    error += "\nChưa nhập tên sản phẩm!";
                }
                
                double gia = Double.parseDouble(txtGia.getText());
                
                if(error.length() > 0){
                    JOptionPane.showMessageDialog(this, error, "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                model.addRow(new Object[]{maSP, tenSP, gia});
            } catch (Exception ex) {
                error += "\nNhập sai kiểu giá bán!";
                JOptionPane.showMessageDialog(this, error, "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnXoa.addActionListener((e) -> {
            int selectedIndex = tblSanPham.getSelectedRow();
            if (selectedIndex >= 0) {
                if (JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa?") == JOptionPane.YES_OPTION) {
                    model.removeRow(selectedIndex);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Chọn dòng cần xóa!");
            }
        });
    }

}
