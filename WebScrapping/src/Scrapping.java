
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.*;

import static java.awt.Font.BOLD;

public class Scrapping implements ActionListener{

        static JFrame frame;
        JTextField textField;
        JButton start_Button,unique_words,frequent_words,largest,smallest,import_to_file;
        JLabel urlLabel,Heading_label,completeLable,topLabel ;
        JPanel top_panel,other_panel;
        Data newData = new Data();
        public static Data dataForFile;
        public static JFileChooser fileChooser = new JFileChooser();
        Scrapping (){

            frame =  new JFrame("Web Scrapping");
            top_panel = new JPanel();
            top_panel.setLayout(null);
            top_panel.setBackground(new Color(00,00,80));
            top_panel.setBounds(0,0,500, 100);
            topLabel = new JLabel("Scrapper");
            topLabel.setFont(new Font("sans-serif" , BOLD,25));
            topLabel.setBounds(180,30,200,40);
            topLabel.setForeground(Color.WHITE);
            top_panel.add(topLabel);




            urlLabel = new JLabel("Enter Url...");
            urlLabel.setBounds(20, 115, 300, 30);
            urlLabel.setForeground(Color.WHITE);
            urlLabel.setFont(new Font("sans-serif" , BOLD,16));
            textField = new JTextField(50);
            textField.setBounds(20,140 , 330, 30);
            textField.setFont(new Font("sans-serif" , Font.ITALIC,14));
            textField.setBackground(Color.WHITE);
            textField.setText("https://www.bbc.com/urdu");
            start_Button = new JButton("Start");
            start_Button.setBounds(360, 140, 90, 30);
            start_Button.setFont(new Font("sans-serif" , Font.ITALIC,14));
            start_Button.setBackground(Color.white);
            start_Button.addActionListener(this);
            unique_words = new JButton("Unique");
            unique_words.setBounds(20, 190, 90,30);
            unique_words.setFont(new Font("sans-serif" , Font.ITALIC,14));
            unique_words.setBackground(Color.WHITE);
            unique_words.addActionListener(this);
            frequent_words = new JButton("Freq: Words");
            frequent_words.setBounds(120, 190,120,30);
            frequent_words.setFont(new Font("sans-serif" , Font.ITALIC,14));
            frequent_words.setBackground(Color.WHITE);
            frequent_words.addActionListener(this);
            smallest =   new JButton("Smallest");
            smallest.setBounds(250,190,100,30);
            smallest.setFont(new Font("sans-serif" , Font.ITALIC,14));
            smallest.setBackground(Color.WHITE);
            smallest.addActionListener(this);
            largest = new JButton("Largest");
            largest.setBounds(360,190,90,30);
            largest.setFont(new Font("sans-serif" , Font.ITALIC,14));
            largest.setBackground(Color.WHITE);
            largest.addActionListener(this);
            import_to_file = new JButton("Import to File ");
            import_to_file.setBounds(360,250,100,30);
            import_to_file.setFont(new Font("sans-serif" , Font.ITALIC,14));
            import_to_file.setBackground(Color.WHITE);
            import_to_file.addActionListener(this);
            completeLable = new JLabel("Scrapping has been Completed!");
            completeLable.setBounds(150,350,200,30);
            completeLable.setForeground(Color.BLACK);
            completeLable.setFont(new Font("Courier", BOLD,20));
            completeLable.setVisible(false);

            frame.add(top_panel);
            frame.add(urlLabel);
            frame.add(textField);
            frame.add(start_Button);
            frame.add(unique_words);
            frame.add(frequent_words);
            frame.add(smallest);
            frame.add(largest);
            frame.add(import_to_file);
            frame.add(completeLable);
            frame.setLayout(null);
            frame.setSize(500, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().setBackground(Color.DARK_GRAY);
            frame.setResizable(false);
            frame.setVisible(true);
        }

        public static void main(String[] args) {
            new Scrapping();
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==start_Button){

                String url = textField.getText();
                System.out.println(url);
                try {
                    Functions.step1webpage(url, newData);
                } catch (IOException ex) {
//                    ex.printStackTrace();
                }
            }
            if (e.getSource()==unique_words){
//                CountUniqueWords.countUniqueWords()
                if (Functions.dataset!=null && CountUniqueWords.countUniqueWords(Functions.dataset)!=0)
                    JOptionPane.showMessageDialog(new JFrame("UniqueWords"), "Number of unique Words are : " + CountUniqueWords.countUniqueWords(Functions.dataset));
                else{
                    JOptionPane.showMessageDialog(new JFrame("Error"), "Error");
                }
            }
            if (e.getSource()==frequent_words){
                try {
                    String remove = Functions.removeWord(Functions.dataset);
                    String frequent  = freq.count_freq(remove);
                    if (frequent != null && Functions.dataset != null) {
                        JOptionPane.showMessageDialog(new JFrame("Frequent: Words"), "Top 10 Frequent Words are :\n" + frequent);
                    } else
                        JOptionPane.showMessageDialog(new JFrame("Error! "), "Error");
                }catch (Exception exception){
                    JOptionPane.showMessageDialog(new JFrame("Error! "), "Error");
                }
            }
            if (e.getSource()==smallest){
                try{
                if(newData != null){
                    JOptionPane.showMessageDialog(new JFrame("Smallest") , "Smallest Stroy is : \n" + Functions.smallest(newData));
                }
                else
                    JOptionPane.showMessageDialog(new JFrame("Error") , "Error");
                }
                catch (Exception exception){
                    JOptionPane.showMessageDialog(new JFrame("Error") , "Error");
                }
            }
            if (e.getSource()==largest){
                try{
                    JOptionPane.showMessageDialog(new JFrame("Largest Story") , "The Largest Story is : \n" + Functions.largest(newData));
                }
                catch (Exception exception){
                    JOptionPane.showMessageDialog(new JFrame("Error") ,  "Error");
                }
            }
            if (e.getSource()==import_to_file){

                try{
                    System.out.println("hello");
                    if(dataForFile == null){
                        JOptionPane.showMessageDialog(new JFrame("Error") ,  "Data is null for File");
                    }
                    else {
                        File newFile = new File("Untitled.csv");
                        fileChooser.setSelectedFile(newFile);
                        int returned_value = fileChooser.showSaveDialog(null);
                        if (returned_value==fileChooser.APPROVE_OPTION) {
                        fileStoring(dataForFile , fileChooser.getSelectedFile());
                        }
                    }
                }catch (Exception exception){
                    JOptionPane.showMessageDialog(new JFrame("Error") ,  "File Not Found");
                }

            }
        }
    public static void fileStoring(Data obj, File newFile) {
        try  {


            FileWriter fileWriter = new FileWriter(newFile.getAbsolutePath());
//
                frame.setCursor(new Cursor(Cursor.WAIT_CURSOR));
//

                fileWriter.append("label").append(",").append("headline").append(",").append("news").append("\n");
                Node currentNode = obj.head;
                news newsNode = obj.head1;
                int i = 0;
                while (newsNode!= null) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(currentNode.data).append(",").append(newsNode.headline).append(",").append(newsNode.fullnews).append("\n");
                    newsNode = newsNode.next;
                    fileWriter.write(stringBuilder.toString());
                }
                frame.setCursor(new Cursor (Cursor.DEFAULT_CURSOR));
        }
        catch (IOException E) {
            JOptionPane.showMessageDialog(null, "Record saved");
        }
    }
    }


