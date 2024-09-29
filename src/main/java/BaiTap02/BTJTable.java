/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BaiTap02;

import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class BTJTable extends JFrame {
    
    private JLabel lbTK, lbTien;
    private JTextField txtTK, txtTien;
    private JButton btnThem, btnXoa;
    private DefaultTableModel tblModel;
    private JTable tblTK;
    
    public BTJTable(String title) {
        super(title);
        createGUI();
        processEvent();
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    private void createGUI() {
        String[] columnNames = {"Tên tài khoản", "Số tiền"};
        Object[][] data = {
            {"Ngô Trung Hiếu Kiên", "10000000"},
            {"Trịnh Trần Phương Tuấn", "5000000"}
        };
        
        tblModel = new DefaultTableModel(data, columnNames);
        tblTK = new JTable(tblModel);
        JScrollPane scr = new JScrollPane(tblTK);
        
        JPanel p = new JPanel();
        p.add(lbTK = new JLabel("Tài khoản"));
        p.add(txtTK = new JTextField(10));
        p.add(lbTien = new JLabel("Số tiền"));
        p.add(txtTien = new JTextField(10));
        p.add(btnThem = new JButton("Thêm"));
//        btnThem.setIcon(new ImageIcon(this.getClass().getResource("../../../newicon.jpg")));
        p.add(btnXoa = new JButton("Xóa"));
        
        add(scr, BorderLayout.CENTER);
        add(p, BorderLayout.NORTH);
    }
    
    private void processEvent() {
        
        btnThem.addActionListener((e) -> {
            String error = "";
            try {
                String tk = txtTK.getText();
                double tien = Double.parseDouble(txtTien.getText());
                if (tk.length() == 0) {
                    error = "Vui lòng nhập tài khoản";
                }
                if (error.length() > 0) {
                    JOptionPane.showMessageDialog(this, error, "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                tblModel.addRow(new Object[]{tk, tien});
            } catch (Exception ex) {
                error += "\nSố tiền nhập sai định dạng";
                JOptionPane.showMessageDialog(this, error, "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        btnXoa.addActionListener((e) -> {
            int selectedIndex = tblTK.getSelectedRow();
            if (selectedIndex >= 0) {
                if (JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa?") == JOptionPane.YES_OPTION) {
                    tblModel.removeRow(selectedIndex);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Chọn dòng cần xóa!");
            }
        });
    }
    
}
