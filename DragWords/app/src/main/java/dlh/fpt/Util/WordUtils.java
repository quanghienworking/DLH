package dlh.fpt.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by HienTQ on 8/1/2015.
 */
public class WordUtils {


/**
 *     input: List<String>
 *     output: List<Char>
 *
 * */
    public static List<Character> dispatchWord(List<String> listWord){

        List<Character> listChar = new ArrayList<Character>();
        int n = 30;
        int[] listInt = new int[n];
        Arrays.fill(listInt, 0);
        for(String word : listWord){
            word = word.toUpperCase();
            char[] letters = word.toCharArray();
            for(char c: letters){
                listInt[c - 'A'] = 1;
            }
        }

        for(int i = 0; i < n; i++){
            if(listInt[i] == 1){
                listChar.add((char) (i +  'A'));
            }
        }



        return listChar;
    }

}
