import java.util.HashMap;

public class CountUniqueWords {

    /*
     * Given a string, return the number of unique (i.e., non-repeated) words in the string, not including punctuation, in O(n).
     * Hyphenated words such as "case-sensitive" are considered as one word, as are contractions such as "didn't".
     * NOTE: this function is NOT case-sensitive, so "Ball" and "ball" are not considered unique if both are in the string.
     */
    public static int countUniqueWords(String s) {
        HashMap<String, Integer> h = new HashMap<String, Integer>();

        String[] words = s.toLowerCase().replaceAll("[^a-zA-Z1-9\\-\\' ]","").split(" ");

        //assume every word is unique
        int count = words.length;

        //get/put are O(1) in most cases, so this is still O(n)
        for (String word : words) {
            if (h.get(word) == null) {
                h.put(word, 1);
            }
            else {
                h.put(word, (h.get(word) + 1));
            }
        }

        int uniqueCount = 0;
        for (String word : words) {
            if (h.get(word) == 1) {
                uniqueCount++;
            }
        }
        return uniqueCount;
    }


//    public static void main(String[] args) {
//        String s = "Shazee loves you Shazee loves 9 9 Shazee loves you Hanzla Hanzla SS";
//        System.out.println(CountUniqueWords.countUniqueWords(s));
//    }
}