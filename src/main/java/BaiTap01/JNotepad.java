/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BaiTap01;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

/**
 *
 * @author Admin
 */
public class JNotepad extends JFrame {

    private JMenuBar menuBar;
    private JMenu mFile, mEdit, mFormat, mView, mHelp;
    private JMenuItem itemNew, itemOpen, itemSave, itemSaveAs, itemPageSetup, itemPrint, itemExit,
            itemCopy, itemPaste;
    private JMenuItem itemFont;
    private JCheckBoxMenuItem itemWrap;
    private JTextArea txtEditor;
    private JToolBar toolBar;
    private JButton btnNew, btnOpen, btnSave;
    private JFontDialog fontdlg;
    private String path;
    JFileChooser fc;

    public JNotepad(String title) {
        super(title);
        createMenu();
        createGUI();
        createToolBar();
        ProcessEvent();
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void createMenu() {
        menuBar = new JMenuBar();

        menuBar.add(mFile = new JMenu("File"));
        menuBar.add(mEdit = new JMenu("Edit"));
        menuBar.add(mFormat = new JMenu("Format"));
        menuBar.add(mView = new JMenu("View"));
        menuBar.add(mHelp = new JMenu("Help"));

        mFile.add(itemNew = new JMenuItem("New"));
        mFile.add(itemOpen = new JMenuItem("Open..."));
        mFile.add(itemSave = new JMenuItem("Save"));
        mFile.add(itemSaveAs = new JMenuItem("Save as..."));
        mFile.add(new JSeparator());
        mFile.add(itemPrint = new JMenuItem("Print..."));
        mFile.add(new JSeparator());
        mFile.add(itemExit = new JMenuItem("Exit"));
        
        mEdit.add(itemCopy = new JMenuItem("Copy"));
        mEdit.add(itemPaste = new JMenuItem("Paste"));

        mFormat.add(itemWrap = new JCheckBoxMenuItem("Word warp", true));
        mFormat.add(itemFont = new JMenuItem("Font..."));

        itemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        itemOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        itemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        itemSaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));
        itemPrint.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
        itemExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK));
        itemCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
        itemPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK));

        setJMenuBar(menuBar);

    }

    private void createGUI() {
        txtEditor = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(txtEditor);
        add(scrollPane);
        txtEditor.setLineWrap(true);
        txtEditor.setFont(new Font("Arial", Font.PLAIN, 20));
    }

    private void createToolBar() {
        toolBar = new JToolBar();
        toolBar.add(btnNew = new JButton("New"));
        toolBar.add(btnOpen = new JButton("Open"));
        toolBar.add(btnSave = new JButton("Save"));

        add(toolBar, BorderLayout.NORTH);
    }

    private void ProcessEvent() {

        itemWrap.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (itemWrap.isSelected()) {
                    txtEditor.setLineWrap(true);
                } else {
                    txtEditor.setLineWrap(false);
                }
            }
        });

        itemOpen.addActionListener((e) -> {
            openFile();
        });

        itemSave.addActionListener((e) -> {
            save();
        });

        itemSaveAs.addActionListener((e) -> {
            saveAs();
        });

        itemNew.addActionListener((e) -> {
            itemNew();
        });

        itemFont.addActionListener((e) -> {
            fontdlg = new JFontDialog(this, true);
            fontdlg.setVisible(true);
        });

        itemExit.addActionListener((e) -> {
            if (JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thoát?") == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

    }

    private void openFile() {
        fc = new JFileChooser("D:/");
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                FileInputStream fis = new FileInputStream(fc.getSelectedFile());
                byte[] b = new byte[fis.available()];
                fis.read(b);
                txtEditor.setText(new String(b));
                path = String.valueOf(fc.getSelectedFile());
                fis.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi đọc file");
            }
        }
    }

    private void saveAs() {
        JFileChooser fc = new JFileChooser();
        if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                FileOutputStream fos = new FileOutputStream(fc.getSelectedFile());
                fos.write(txtEditor.getText().getBytes());
                fos.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi ghi file");
            }
        }
    }

    private void itemNew() {
        if (!txtEditor.getText().trim().isEmpty()) {
            int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn lưu văn bản hiện tại?",
                    "Xác nhận lưu", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                saveAs();
            } else if (result == JOptionPane.NO_OPTION) {
                txtEditor.setText("");
                txtEditor.setCaretPosition(0);
            }
        } else {
            txtEditor.setCaretPosition(0);
        }
    }

    private void save() {
        if (path == null) {
            saveAs();
        } else {
            try {
                FileOutputStream fos = new FileOutputStream(path);
                fos.write(txtEditor.getText().getBytes());
                fos.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi ghi file");
            }
        }
    }

    public JTextArea getTxtEditor() {
        return txtEditor;
    }

}
