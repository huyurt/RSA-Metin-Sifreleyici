package com.application;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

public class Main {
    private final String kopyalamaUyarisi1 = "Herhangi bir metni şifrelemediniz.";
    private final String kopyalamaUyarisi2 = "Herhangi bir metni çözümlemediniz.";
    private final String islemUyarisi1 = "N, E veya şifrelenecek metin değerleri boş olamaz.";
    private final String islemUyarisi2 = "N, D veya çözülecek metin değerleri boş olamaz.";
    private final String formatUyarisi1 = "N ve E değerleri sayı olmalı. (Base64 değil)";
    private final String formatUyarisi2 = "N ve D değerleri sayı olmalı. (Base64 değil)";
    private final String base64Uyarisi = "Girdi Base64 formatında olmalı.";

    private AsalSayi asalSayi;
    private Sifrele sifrele;
    private Cozumle cozumle;
    private int bit;
    public static BigInteger p;
    public static BigInteger q;
    public static BigInteger Fi;
    public static BigInteger N;
    public static BigInteger E;
    public static BigInteger D;

    private static JFrame jFrame;
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JList list1;
    private JButton olusturButton;
    private JLabel labelP;
    private JLabel labelQ;
    private JLabel labelN;
    private JLabel labelFi;
    private JLabel labelE;
    private JLabel labelD;
    private JTextArea textArea1Sifrele;
    private JTextArea textArea2Sifrele;
    private JButton temizleButton;
    private JButton kopyalaButton;
    private JTextField textField1Sifrele;
    private JTextField textField2Sifrele;
    private JButton şifreleButton;
    private JTextArea textArea1Cozumle;
    private JTextArea textArea2Cozumle;
    private JButton çözümleButton;
    private JButton kopyalaButton1Cozumle;
    private JButton temizleButton1Cozumle;
    private JTextField textField1Cozumle;
    private JTextField textField2Cozumle;

    public Main() {
        list1.setSelectedIndex(0);

        olusturButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anahtarOlustur();
            }
        });

        şifreleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!textField1Sifrele.getText().equals("") && !textField2Sifrele.getText().equals("") && !textArea1Sifrele.getText().equals("")) {
                    try {
                        sifrele = new Sifrele(new BigInteger(textField1Sifrele.getText()), new BigInteger(textField2Sifrele.getText()), textArea1Sifrele.getText());
                        textArea2Sifrele.setText(sifrele.getSifreliMetin());
                    } catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(null, formatUyarisi1, "Uyarı", JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(null, base64Uyarisi, "Uyarı", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, islemUyarisi1, "Uyarı", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        çözümleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!textField1Cozumle.getText().equals("") && !textField2Cozumle.getText().equals("") && !textArea1Cozumle.getText().equals("")) {
                    try {
                        cozumle = new Cozumle(new BigInteger(textField1Cozumle.getText()), new BigInteger(textField2Cozumle.getText()), textArea1Cozumle.getText());
                        textArea2Cozumle.setText(cozumle.getOrjinalMetin());
                    } catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(null, formatUyarisi2, "Uyarı", JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(null, base64Uyarisi, "Uyarı", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, islemUyarisi2, "Uyarı", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        temizleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea1Sifrele.setText("");
                textArea2Sifrele.setText("");
            }
        });

        kopyalaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!textArea2Sifrele.getText().equals("")) {
                    StringSelection stringSelection = new StringSelection(textArea2Sifrele.getText());
                    Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clpbrd.setContents(stringSelection, null);
                } else {
                    JOptionPane.showMessageDialog(null, kopyalamaUyarisi1, "Uyarı", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        temizleButton1Cozumle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea1Cozumle.setText("");
                textArea2Cozumle.setText("");
            }
        });

        kopyalaButton1Cozumle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!textArea2Cozumle.getText().equals("")) {
                    StringSelection stringSelection = new StringSelection(textArea2Cozumle.getText());
                    Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clpbrd.setContents(stringSelection, null);
                } else {
                    JOptionPane.showMessageDialog(null, kopyalamaUyarisi2, "Uyarı", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        jFrame = new JFrame("RSA Metin Şifreleyici");
        JPanel jPanel = new Main().panel1;
        JScrollPane jScrollPane = new JScrollPane(jPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setContentPane(jScrollPane);
        jFrame.pack();
        jFrame.setVisible(true);
    }

    private BigInteger setAsalSayi(int bit, JLabel jLabel) {
        AsalSayi asalSayi = new AsalSayi(bit);
        jLabel.setText(asalSayi.getAsalSayi().toString());
        return asalSayi.getAsalSayi();
    }

    private BigInteger hesaplaFi(BigInteger p, BigInteger q) {
        BigInteger p_1 = p.subtract(BigInteger.ONE);
        BigInteger q_1 = q.subtract(BigInteger.ONE);
        return p_1.multiply(q_1);
    }

    private int bit(JList jList) {
        switch (jList.getSelectedIndex()) {
            case 0:
                return 1024;
            case 1:
                return 512;
            case 2:
                return 256;
            case 3:
                return 128;
            case 4:
                return 64;
            case 5:
                return 32;
            case 6:
                return 16;
            case 7:
                return 8;
            default:
                return 1024;
        }
    }

    private void anahtarOlustur() {
        try {
            bit = bit(list1);
            asalSayi = new AsalSayi(bit);
            p = setAsalSayi(bit, labelP);
            do {
                q = setAsalSayi(bit, labelQ);
            } while (p.equals(q));
            N = p.multiply(q);
            labelN.setText(N.toString());
            Fi = hesaplaFi(p, q);
            labelFi.setText(Fi.toString());
            E = asalSayi.hesaplaE(Fi);
            labelE.setText(E.toString());
            D = asalSayi.hesaplaD(Fi, E);
            labelD.setText(D.toString());

            textField1Sifrele.setText(N.toString());
            textField2Sifrele.setText(E.toString());

            textField1Cozumle.setText(N.toString());
            textField2Cozumle.setText(D.toString());
        } catch (ArithmeticException e) {
            anahtarOlustur();
        }
    }
}