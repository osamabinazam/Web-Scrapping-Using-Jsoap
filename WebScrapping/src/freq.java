import java.util.Map;
import java.util.TreeMap;

public class freq {
    public static String count_freq(String str) {
        Map<String,Integer> mp=new TreeMap<>();
        String arr[]=str.split(" ");
        String returned_string ="";
        for (String s : arr) {
            if (mp.containsKey(s)) {
                mp.put(s, mp.get(s) + 1);
            } else {
                mp.put(s, 1);
            }
        }
        int x=1;
        for(Map.Entry<String,Integer> entry: mp.entrySet())
        {

            while(x!=10)
            {
                if(entry.getValue()<308){
                    System.out.println(entry.getKey()+ " - "+entry.getValue());
                    returned_string += entry.getKey()+" - "+entry.getValue()+"\n";
                    x++;
                }
                break;
            }
        }
        return returned_string;
    }
//public static void  count_freq(String str) {
//    Map<String,Integer> mp=new TreeMap<>();
//    String arr[]=str.split(" ");
//    for(int i=0;i<arr.length;i++) {
//        if(mp.containsKey(arr[i])) {
//            mp.put(arr[i], mp.get(arr[i])+1);
//        }
//        else {
//            mp.put(arr[i],1);
//        }
//    }
//    int x=1;
//    System.out.println("TOP 10 FREQUENT WORDS");
//    for(Map.Entry<String,Integer> entry: mp.entrySet()) {
//        while(x!=10) {
//            if(entry.getValue()>100){
//                System.out.println(entry.getKey()+ " - "+entry.getValue());
//                x++;
//            }
//            break;
//        }
//    }
//}
//    public static String count_freq(String para){
//        String[] words = para.split("\\s+");
//        int finalCount = 0;
//        int tempCount = 0;
//        String mostlyUsedWord = null;
//        for (String word: words) {
//            tempCount = 0;
//            for (String w: words) {
//                if (word.equalsIgnoreCase(w)) {
//                    tempCount++;}
//
//            }
//            if (tempCount >= finalCount) {
//                finalCount = tempCount;
//                mostlyUsedWord = word;
//            }
//        }
//        return mostlyUsedWord;
//    }
}