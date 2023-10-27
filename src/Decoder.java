import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.plaf.basic.*;
import java.util.*;

public class Decoder implements ActionListener{

    JButton Bbase16, Bbase32, Bbase58, Bbase64, Bbase85, Bbase91;
    String data = "";
    JTextArea showText = new JTextArea();
    JTextArea input = new JTextArea();

    public static void main(String[]args){
    Decoder Decoder = new Decoder();
    }

    public Decoder(){
        Bbase16 = defaulButton("Base16", 15);
        Bbase32 = defaulButton("Base32", 110);
        Bbase58 = defaulButton("Base58", 205);
        Bbase64 = defaulButton("Base64",300);
        Bbase85 = defaulButton("Base85", 395);
        Bbase91 = defaulButton("Base91",490);

        Font fieldFont = null;
        try{
            File font = new File("src/NotoSans-Medium.ttf");
            fieldFont = Font.createFont(Font.TRUETYPE_FONT, font);
        }catch(FontFormatException | IOException e){
            e.printStackTrace();
        }

        JLabel titleInput = new JLabel("Input :");
        titleInput.setBounds(15, 10, 50, 20);
        titleInput.setForeground(new Color(30, 30, 30));
        input.setBounds(15, 30, 555, 45);
        input.setBackground(new Color(30, 30, 30));
        input.setForeground(new Color(190, 190, 190));
        input.setLineWrap(true);

        JScrollPane inputSusCode = new JScrollPane(input);
        inputSusCode.setFont(new Font("", Font.PLAIN, 13));
        inputSusCode.setBounds(15, 15, 555, 70);
        inputSusCode.setBorder(null);
        inputSusCode.setFont(fieldFont);
        inputSusCode.getViewport().getView().setBackground(new Color(30, 30, 30));
        inputSusCode.getViewport().getView().setForeground(new Color(190, 190, 190));
        inputSusCode.getVerticalScrollBar().setBackground(new Color(30, 30, 30));
        inputSusCode.getVerticalScrollBar().setUI(new BasicScrollBarUI(){
            @Override
            protected void configureScrollBarColors(){
                this.thumbColor = new Color(80, 80, 80);
            }
        });

        showText.setLineWrap(true);
        showText.setEditable(false);
        showText.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane showEncoded = new JScrollPane(showText);
        showEncoded.setFont(new Font("", Font.PLAIN, 13));
        showEncoded.setBounds(15, 15, 555, 210);
        showEncoded.setBorder(null);
        showEncoded.setFont(fieldFont);
        showEncoded.getViewport().getView().setBackground(new Color(30, 30, 30));
        showEncoded.getViewport().getView().setForeground(new Color(190, 190, 190));
        showEncoded.getVerticalScrollBar().setBackground(new Color(30, 30, 30));
        showEncoded.getVerticalScrollBar().setUI(new BasicScrollBarUI(){
            @Override
            protected void configureScrollBarColors(){
                this.thumbColor = new Color(80, 80, 80);
            }
        });
        
        JPanel menuPanel = new JPanel();
        menuPanel.setBounds(0, 120, 585, 280);
        menuPanel.setBackground(new Color(50, 50, 50));
        menuPanel.setLayout(null);
        menuPanel.add(showEncoded);
        
        JFrame frame = new JFrame("BokCoder v0.1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.getContentPane().setBackground(new Color(80, 80, 80));
        frame.add(titleInput);
        frame.add(inputSusCode);
        frame.add(Bbase16);
        frame.add(Bbase32);
        frame.add(Bbase58);
        frame.add(Bbase64);
        frame.add(Bbase85);
        frame.add(Bbase91);
        frame.add(menuPanel);
    }
    
    private void buttonReset(){
        Bbase16.setBackground(new Color(50, 50, 50));
        Bbase32.setBackground(new Color(50, 50, 50));
        Bbase58.setBackground(new Color(50, 50, 50));
        Bbase64.setBackground(new Color(50, 50, 50));
        Bbase85.setBackground(new Color(50, 50, 50));
        Bbase91.setBackground(new Color(50, 50, 50));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==Bbase16){
            buttonReset();
            data = input.getText();
            Bbase16.setBackground(new Color(30, 30, 30));
            showText.setText(Base16(data));
        }
        if(e.getSource()==Bbase32){
            buttonReset();
            data = input.getText();
            Bbase32.setBackground(new Color(30, 30, 30));
            showText.setText(Base32(data));
        }
        if(e.getSource()==Bbase58){
            buttonReset();
            data = input.getText();
            Bbase58.setBackground(new Color(30, 30, 30));
            showText.setText(Base58(data));
        }
        if(e.getSource()==Bbase64){
            buttonReset();
            data = input.getText();
            Bbase64.setBackground(new Color(30, 30, 30));
            showText.setText(Base64(data));
        }
        if(e.getSource()==Bbase85){
            buttonReset();
            data = input.getText();
            Bbase85.setBackground(new Color(30, 30, 30));
            showText.setText(Base85(data));
        }
        if(e.getSource()==Bbase91){
            buttonReset();
            data = input.getText();
            Bbase91.setBackground(new Color(30, 30, 30));
            showText.setText(Base91(data));
        }
    }

    private JButton defaulButton(String label, int location){
        JButton button = new JButton(label);
        button.setForeground(new Color(190, 190, 190));
        button.setBackground(new Color(50, 50, 50));
        button.setBounds(location, 100, 80, 35);
        button.setFocusable(false);
        button.setBorderPainted(false);
        button.addActionListener(this);
        return button;
    }

    //Base16
    private String Base16(String input){
        String decoded = "";
        String baseChar = "0123456789ABCDEF";
        int[] baseBinary = {(0),(1),(10),(11),(100),(101),(110),(111),(1000),(1001),(1010),(1011),(1100),(1101),(1110),(1111)};
        input = input.toUpperCase();

        if(input.matches("[0-9A-F]+")){
            StringBuilder binary = new StringBuilder();
            
            for(int i = 0; i < input.length(); i++){
                int indexChar = baseChar.indexOf(input.charAt(i));
                String format = String.format("%04d", baseBinary[indexChar]);
                binary.append(format);
            }
            
            decoded = ASCII(binary);
            return decoded;
        }else{
            decoded = "data dont match.";
            return decoded;
        }
    }

    //Base32
    private String Base32(String input){
        String decoded = "";
        String baseChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567";
        int[] baseBinary = {(0),(1),(10),(11),(100),(101),(110),(111),(1000),(1001),(1010),(1011),(1100),(1101),(1110),(1111),(10000),
        (10001),(10010),(10011),(10100),(10101),(10110),(10111),(11000),(11001),(11011),(11011),(11100),(11101),(11110),(11111)};
        
        if(input.matches("[2-7A-Za-z]+")){
            StringBuilder binary = new StringBuilder();
            input = input.toUpperCase();
            
            for(int i = 0; i < input.length(); i++){
                int indexChar = baseChar.indexOf(input.charAt(i));
                String format = String.format("%05d", baseBinary[indexChar]);
                binary.append(format);
            }
            
            decoded = ASCII(binary);
            return decoded;
        }else{
            decoded = "data dont match.";
            return decoded;
        }
    }

    //Base58
    private String Base58(String input){
        String decoded = "";
        String baseChar = "123456789ABCDEFGHIJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz";
        int[] baseBinary = {(0),(1),(10),(11),(100),(101),(110),(111),(1000),(1001),(1010),(1011),(1100),(1101),(1110),(1111),(10000),
        (10001),(10010),(10011),(10100),(10101),(10110),(10111),(11000),(11001),(11011),(11011),(11100),(11101),(11110),(11111),
        (100000),(100001),(100010),(100011),(100100),(100101),(100110),(100111),(101000),(101001),(101010),(101011),(101100),(101101),
        (101110),(101111),(110000),(101111),(110000),(110001),(110010),(110011),(110100),(110101),(110110),(110111),(111000),(111001)};
        
        if(input.matches("[123456789ABCDEFGHIJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz]+")){
            StringBuilder binary = new StringBuilder();
            
            for(int i = 0; i < input.length(); i++){
                int indexChar = baseChar.indexOf(input.charAt(i));
                String format = String.format("%06d", baseBinary[indexChar]);
                binary.append(format);
            }
            
            decoded = ASCII(binary);
            return decoded;
        }else{
            decoded = "data dont match.";
            return decoded;
        }
    }

    //Base64
    private String Base64(String input){
        String decoded = "";
        if(input.length()>1){
            byte[] decode = Base64.getDecoder().decode(input);
            decoded = new String(decode);
            return decoded;
        }else{
            decoded = "not enough byte";
            return decoded;
        }
    }

    //Base85
    private String Base85(String input){
        String decoded = "";
        String baseChar = "0123456789ABCDEFGHIJKLMNPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMN";
        int[] baseBinary = {(0),(1),(10),(11),(100),(101),(110),(111),(1000),(1001),(1010),(1011),(1100),(1101),(1110),(1111),(10000),
            (10001),(10010),(10011),(10100),(10101),(10110),(10111),(11000),(11001),(11011),(11011),(11100),(11101),(11110),(11111),
            (100000),(100001),(100010),(100011),(100100),(100101),(100110),(100111),(101000),(101001),(101010),(101011),(101100),(101101),
            (101110),(101111),(110000),(101111),(110000),(110001),(110010),(110011),(110100),(110101),(110110),(110111),(111000),(111001),
            (111010),(111011),(111100),(111101),(111110),(111111),(1000000),(1000001),(1000010),(1000011),(1000100),(1000101),(1000110),
            (1000111),(1001000),(1001001),(1001010),(1001011),(1001100),(1001101),(1001111),(1010000),(1010001),(1010010),(1010011),(1010100),
            (1010101)};
        
        if(input.matches("[0123456789ABCDEFGHIJKLMNPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMN]+")){
            StringBuilder binary = new StringBuilder();
            
            for(int i = 0; i < input.length(); i++){
                int indexChar = baseChar.indexOf(input.charAt(i));
                String format = String.format("%08d", baseBinary[indexChar]);
                binary.append(format);
            }
            
            decoded = ASCII(binary);
            return decoded;
        }else{
            decoded = "data dont match.";
            return decoded;
        }
    }

    //Base91
    private String Base91(String input){
        String decoded = "";
        String baseChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!#$%&()*+,./:;<=>?@[]^_`{|}~\"";
        int[] baseBinary = {(0),(1),(10),(11),(100),(101),(110),(111),(1000),(1001),(1010),(1011),(1100),(1101),(1110),(1111),(10000),
            (10001),(10010),(10011),(10100),(10101),(10110),(10111),(11000),(11001),(11011),(11011),(11100),(11101),(11110),(11111),
            (100000),(100001),(100010),(100011),(100100),(100101),(100110),(100111),(101000),(101001),(101010),(101011),(101100),(101101),
            (101110),(101111),(110000),(101111),(110000),(110001),(110010),(110011),(110100),(110101),(110110),(110111),(111000),(111001),
            (111010),(111011),(111100),(111101),(111110),(111111),(1000000),(1000001),(1000010),(1000011),(1000100),(1000101),(1000110),
            (1000111),(1001000),(1001001),(1001010),(1001011),(1001100),(1001101),(1001111),(1010000),(1010001),(1010010),(1010011),(1010100),
            (1010101),(1010110),(1010111),(1011000),(1011001),(1011010),(1011011)};
        
        if(input.matches("[0-9A-Za-z\\!\\#\\$\\%\\&\\(\\)\\*\\+\\,\\.\\/\\:\\;\\<\\=\\>\\?\\@\\[\\]\\^\\_\\`\\{\\|\\}\\~\"]+")){
            StringBuilder binary = new StringBuilder();
            
            for(int i = 0; i < input.length(); i++){
                int indexChar = baseChar.indexOf(input.charAt(i));
                String format = String.format("%07d", baseBinary[indexChar]);
                binary.append(format);
            }
            
            decoded = ASCII(binary);
            return decoded;
        }else{
            decoded = "data dont match.";
            return decoded;
        }
    }

    //ASCII
    private String ASCII(StringBuilder binary){
        String ASCII = "";
        StringBuilder rawACSII = new StringBuilder();
        StringBuilder gap = new StringBuilder();

        do{
            if(binary.length() < 8){
                for(int i = 0; i < 8 - binary.length(); i++){
                    gap.insert(0, "0");
                }
                binary.insert(0, gap);
            }
            
            String bit = binary.substring(0, 8);
            char charASCII = (char) Integer.parseInt(bit, 2);
            rawACSII.append(charASCII);
            
            binary.delete(0, 8);
            if(binary.length() < 8 && binary.length() > 0){
                for(int i = 0; i < 8 - binary.length(); i++){
                    gap.insert(0, "0");
                }
                binary.insert(0, gap);
            }
        }while(binary.length() != 0);

        
        ASCII = rawACSII.toString();
        return ASCII;
    }
}
