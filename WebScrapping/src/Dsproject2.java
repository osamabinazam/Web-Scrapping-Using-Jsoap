/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import java.io.BufferedWriter;
import java.util.HashMap;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Lenovo
 */
class Node {

    String data;
    Node next;

    Node(String data) {
        this.data = data;
    }
}

class news {

    String headline;
    String fullnews;
    news next;

    news(String headline, String fullnews) {
        this.headline = headline;
        this.fullnews = fullnews;
    }
}

class Data {

    news head1, curn1;
    Node head, curn;
//add at the end 

    void addwebpage(String name) {
        curn = head;
        Node obj = new Node(name);
        if (head == null) {
            head = obj;
        } else {
            while (curn.next != null) {
                curn = curn.next;
            }
            curn.next = obj;
            //obj.prev=curn;
            curn = obj;
        }
    }

    void addnews(String headline, String fullnews) {
        curn1 = head1;
        news obj = new news(headline, fullnews);
        if (head1 == null) {
            head1 = obj;
        } else {
            while (curn1.next != null) {
                curn1 = curn1.next;
            }
            curn1.next = obj;
            //obj.prev=curn;
            curn1 = obj;
        }
    }

    void print() {
        curn = head;
        while (curn.next != head) {
            System.out.println(curn.data + " ");
            if (curn.next == null) {
                return;
            }
            curn = curn.next;

        }
        //System.out.println(curn.Data);
    }

}

class Functions {
    static String dataset;

    public static void step1webpage(String k, Data obj) throws IOException {
        try {
            System.out.println("100 stories from 6 sections");
            String url = k;
            Document doc = Jsoup.connect(url).get();
            Elements op = doc.select("div.bbc-1cjmyg9.e1fc18nj0");
            Elements links = op.select("a[href]");
            int j = 0;
            for (Element link : links) {

                if (j > 0 && j < 7) {
                    String l = link.text();
                    obj.addwebpage(l);
                    String h = "https://www.bbc.com" + link.attr("href");
                    step2storiesheadlines(h, obj);

                }
                j++;
            }

            //obj.print();
        } catch (IOException ex) {
        }
    }

     public static  void step2storiesheadlines(String k, Data obj) {
        try {
            // System.out.println(k+"/page/"+p);
            String url = k;
            for (int i = 1; i <= 10; i++) {
                if (i < 2) {
                    Document doc = Jsoup.connect(url).get();
                    Elements op = doc.select("a.qa-heading-link.lx-stream-post__header-link");
                    Elements links = op.select("a[href]");

                    for (Element link : links) {

                        // System.out.println(link.text());
                        //System.out.println(" ");
                        String h = "https://www.bbc.com" + link.attr("href");
                        String ke1 = link.text();
                        story(h, ke1, obj);

                    }
                } else {
                    Document doc = Jsoup.connect(url + "/page/" + i).get();
                    Elements op = doc.select("a.qa-heading-link.lx-stream-post__header-link");
                    Elements links = op.select("a[href]");

                    for (Element link : links) {

                        String ke = link.text();
                        //System.out.println(" ");
                        String h = "https://www.bbc.com" + link.attr("href");
                        story(h, ke, obj);

                    }

                }

            }
        } catch (IOException ex) {
        }
    }
    static int i = 0;

    public static void story(String k, String l, Data obj) throws IOException {
        try {

            String url = k;
            Document doc = Jsoup.connect(url).get();
            Elements op = doc.select("main");
            String ke1 = op.text();
            i++;
                    System.out.println(l + i);
            obj.addnews(l, ke1);
            dataset = dataset+ke1;
            Scrapping.dataForFile =obj;

        } catch (IOException ex) {
        }
    }
     public static String removeWord(String string)
    {
        String arr[] = {"Getty Images","اب" ,"ابھی" ,"اپنا" ,"اپنے" ,"اپنی" ,"اٹھا" ,"اس" ,"اسے","اسی" ,"اگر" ,"ان" ,"انہوں" , "انہی" ,"انہیں" ,"انھیں" ,"او" ,"اور" ,"اے" ,"ایسا" ,"ایسے" ,"ایسی" ,"ایک" ,"آ" ,"آپ" ,"آتا" , "آتے" , "آتی" ,"آگے" ,"آنا" ,"آنے" , "آنی" ,"آئے" ,"آئی" ,"آئیں" ,"آیا" , "با" ,"بڑا" ,"بڑے" ,"بڑی" ,"بعد" ,"بعض" ,"بلکہ" ,"بہت" ,"بھی" ,"بے" ,"پر" ,"پہلے" ,"پھر" ,"تا" , "تاکہ" ,"تب" , "تجھ" ,"تجھے" ,"تک" ,"تم" ,"تمام" ,"تمہارا" ,"تمہارے" ,"تمھارے" ,"تمہاری" ,"تمہیں" ,"تمھیں" ,"تھا" , "تھے" ,"تھی" ,"تھیں" ,"تو" ,"تیری" ,"تیرے" ,"جا" ,"جاتا" ,"جاتی" ,"جاتے" ,"جاتی" ,"جانے" ,"جانی" , "جاؤ" ,"جائے" ,"جب" ,"جس" ,"جن" ,"جنہوں" ,"جنہیں" ,"جو" ,"جیسا" ,"جیسے" ,"جیسی" , "جیسوں" ,"چاہیئے" ,"چلا" , "چاہے" ,"چونکہ" ,"حالاں" ,"حالانکہ" ,"دو" ,"دونوں" ,"دوں" ,"دے" , "دی" ,"دیا" ,"دیں" ,"دیے" ,"دیتا" ,"دیتے" ,"دیتی" ,"دینا" ,"دینے" , "دینی" , "دیئے" ,"ڈالا" ,"ڈالنا" ,"ڈالنے" ,"ڈالنی" ,"ڈالے" ,"ڈالی" ,"ذرا" ,"رکھا" ,"رکھتا" ,"رکھتے" ,"رکھتی" ,"رکھنا" ,"رکھنے" ,"رکھنی" ,"رکھے" ,"رکھی" ,"رہ" ,"رہا" , "رہتا" , "رہتے" ,"رہتی" ,"رہنا" ,"رہنے" ,"رہنی" ,"رہو" ,"رہے" ,"رہی" ,"رہیں" ,"زیادہ" , "سا" ,"سامنے" ,"سب" ,"سکتا" ,"سو", "سے" ,"سی" ,"شاید" ,"صرف" ,"طرح" ,"طرف" ,"عین" , "کا" ,"کبھی" ,"کچھ" ,"کہہ" ,"کر" ,"کرتا" ,"کرتے" ,"کرتی" ,"کرنا" ,"کرنے" ,"کرو" ,"کروں" ,"کرے" ,"کریں" ,"کس" ,"کسے" ,"کسی" ,"کہ" ,"کہا" ,"کہے" ,"کو" ,"کون" , "کوئی" , "کے" ,"کی" ,"کیا" ,"کیسے" , "کیوں" ,"کیونکہ" ,"کیے" ,"کئے" ,"گا" ,"گویا" ,"گے" ,"گی" ,"گیا" ,"گئے" ,"گئی" ,"لا" ,"لاتا" ,"لاتے" ,"لاتی" ,"لانا" , "لانے" ,"لایا"  ,"لائے" ,"لائی" ,"لگا" ,"لگے" ,"لگی" ,"لگیں" ,"لو" ,"لے" ,"لی" ,"لیا" ,"لیتا" ,"لیتے" ,"لیتی" ,"لیں" ,"لیے" , "لئے" ,"مجھ" ,  "مجھے" ,"مگر" , "میرا" ,"میری" ,"میں" ,"نا" ,"نہ" ,"نہایت" ,"نہیں" ,"نے" ,"ہاں" ,"ہر" ,"ہم" ,"ہمارا" ,"ہمارے" ,"ہماری" ,"ہو" ,"ہوا" ,"ہوتا" ,"ہوتے" ,"ہوتی" ,"ہوتیں" , "ہوں" ,"ہونا" , "ہونگے" ,"ہونے" ,  "ہونی" , "ہوئے" ,"ہوئی" ,"ہوئیں" ,"ہے" ,"ہی" ,"ہیں" ,  "و" ,"والا" ,  "والوں" ,"والے" ,"والی" ,"وہ" ,"وہاں" ,"وہی" , "وہیں" , "یا", "یعنی" ,"یہ" ,"یہاں" ,"یہی" ,"یہیں","تھا۔","کیا","ہے،","انھیں","رہا","تھے۔","یا","سال","ہے۔","زیادہ","بعد","جس","ساتھ","لیے","انھوں","ہیں۔","تھا","گیا","سے","نے","وہ", "null",};
        for (String arr1 : arr) {
            if (string.contains(arr1)) {
                String tempWord = arr1 + " ";
                string = string.replaceAll(tempWord, "");
//                tempWord = " " + arr1;
//                string = string.replaceAll(tempWord, "");
            }
        }

        return string;
    }
    static int lol=-1;
    public static String smallest(Data obj) {
        news w =obj.head1;
        String k=null;
        while(w.next!=null) {
            if(lol==-1) {
                lol=w.fullnews.length();
                k=w.headline;
            }
            else {
                if(lol>w.fullnews.length() && w.fullnews.length() > 0) {
                    lol=w.fullnews.length();
                    k=w.headline;
                }
            }
            w=w.next;
        }
        k=" is the smallest story with the length of "+lol +" is :\n" +k;
        return k;
    }
    //Largest Story..
    static int largest_story_counter=-1;
    public  static  String largest(Data obj) {
        news news_variabel=obj.head1;
        String largest_story=null;
        while(news_variabel.next!=null) {
            if(largest_story_counter==-1) {
                largest_story_counter=news_variabel.fullnews.length();
                 largest_story=news_variabel.headline;
            }
            else {
                if(largest_story_counter<news_variabel.fullnews.length()) {
                    largest_story_counter=news_variabel.fullnews.length();
                    largest_story=news_variabel.headline;
                }
            }
            news_variabel= news_variabel.next;
        }
        return "The largest  story with the length of "+largest_story_counter + " is : \n" + largest_story ;
    }
}

public class Dsproject2 {
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
}
