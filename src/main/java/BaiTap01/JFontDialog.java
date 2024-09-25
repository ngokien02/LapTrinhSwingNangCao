/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BaiTap01;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 *
 * @author Admin
 */
public class JFontDialog extends JDialog {

    private JList lstFontName, lstStyle, lstSize;
    private JLabel lbReview;
    private JButton btnOk, btnCancel;
    private Font font;
    private int[] arrayStyle = {Font.PLAIN, Font.ITALIC, Font.BOLD, Font.ITALIC + Font.BOLD};
    private JNotepad parents;

    public JFontDialog(Frame owner, boolean modal) {
        super(owner, modal);
        parents = (JNotepad) owner;
        createUI();
        setReviewFont();
        processEvent();
        setSize(600, 450);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(owner);
    }

    private void createUI() {
        JPanel p = new JPanel();
        p.setLayout(null);

        String[] fontName = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        lstFontName = new JList(fontName);
        JScrollPane scrollFontName = new JScrollPane(lstFontName);
        lstFontName.setSelectedIndex(0);

        p.add(scrollFontName);
        scrollFontName.setBounds(5, 50, 200, 200);

        String[] style = {"Regular", "Italic", "Bold", "Italic Bold"};
        lstStyle = new JList(style);
        JScrollPane scrollStyle = new JScrollPane(lstStyle);
        lstStyle.setSelectedIndex(0);

        p.add(scrollStyle);
        scrollStyle.setBounds(210, 50, 200, 200);

        String[] size = {"8", "9", "10", "11", "12", "14", "16", "20", "22", "24", "26", "32", "48", "72"};
        lstSize = new JList(size);
        JScrollPane scrollSize = new JScrollPane(lstSize);
        lstSize.setSelectedIndex(4);

        p.add(scrollSize);
        scrollSize.setBounds(420, 50, 100, 200);

        p.add(lbReview = new JLabel("AaBbYyZz"));
        lbReview.setBounds(300, 240, 300, 80);

        p.add(btnOk = new JButton("OK"));
        p.add(btnCancel = new JButton("Cancel"));
        btnOk.setBounds(250, 350, 100, 50);
        btnCancel.setBounds(380, 350, 100, 50);

        add(p);
    }

    private void setReviewFont() {
        String name = (String) lstFontName.getSelectedValue();
        int style = arrayStyle[lstStyle.getSelectedIndex()];
        int size = Integer.parseInt(lstSize.getSelectedValue().toString());
        font = new Font(name, style, size);
        lbReview.setFont(font);
    }

    private void processEvent() {
        lstFontName.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                setReviewFont();
            }
        });

        lstStyle.addListSelectionListener((e) -> {
            setReviewFont();
        });

        lstSize.addListSelectionListener((e) -> {
            setReviewFont();
        });

        btnOk.addActionListener((e) -> {
            parents.getTxtEditor().setFont(font);
            this.dispose();
        });

        btnCancel.addActionListener((e) -> {
            this.dispose();
        });

    }

}
