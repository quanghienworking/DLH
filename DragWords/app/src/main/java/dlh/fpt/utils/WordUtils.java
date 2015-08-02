package dlh.fpt.utils;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dlh.fpt.DTO.NodeMean;
import dlh.fpt.DTO.ObjectParseDictionary;

/**
 * Created by HienTQ on 8/1/2015.
 */
public class WordUtils {


    /**
     * Author: HienTQ
     * Date: 8-2-2015JP
     * Action: Check word in dictionary which has mean. If It has mean, then return list of its mean
     * input: String word;
     * output: 1. NULL It NOT has mean.
     *         2. NOT NULL It has Mean:
     *            List<String> List mean, 1 mean: 1 String
     * */

    public static  List<String> checkMeanWord(String word){
        List<String> result = null;
        ObjectParseDictionary dictionary = ConnectAPI(word);
        if( dictionary != null){
            result = new ArrayList<String>();
            List<NodeMean> listMean = dictionary.getDefinitions();
            for(NodeMean meanPair : listMean){
                String[] means = meanPair.getText().split(":");
                if(means.length >1){
                    String mean = means[0] + ".";
                    result.add(mean);
                }else {
                    String mean = means[0];
                    result.add(mean);
                }

            }
            System.out.println("checkMeanWord | RESULT:" + result);
        }

        return result;
    }


/**
 *      Author: HienTQ
 *      Date: 8-2-2015JP
 *      input: List<String>
 *      output: List<Char>
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
            for(char c : letters){
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


    /**
     * Author: HienTQ
     * Date: 8-2-2015JP
     * Action:  Connect To API get Object in Dictionary
     * return: ObjectParseDictionary Dictionary
     * */

    public static ObjectParseDictionary ConnectAPI(String word) {
        ObjectMapper mapping = new ObjectMapper();
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        ObjectParseDictionary dictionary =  null;

        String url = "https://montanaflynn-dictionary.p.mashape.com/define?word=" + word.trim();
        String header1 = "Content-type";
        String value1  = "application/json";
        String header2 = "X-Mashape-Key";
        String value2  = "2PBEnLg8JgmshPG1K9FEZ0mphHfDp1cOnegjsnIj2p4QlLInXg";

        try {

            httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);

            httpGet.addHeader(header1, value1);
            httpGet.addHeader(header2, value2);

            httpResponse = httpClient.execute(httpGet);
            if(httpResponse.getStatusLine().getStatusCode() != 200){
                throw new RuntimeException("Failed : HTTP error code : " + httpResponse.getStatusLine().getStatusCode());
            }

            InputStream inputStream = httpResponse.getEntity().getContent();

            dictionary = mapping.readValue(inputStream, ObjectParseDictionary.class);
            //TODO
            System.out.println(dictionary);


        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(httpClient != null){
                    httpClient.close();
                }
                if(httpResponse != null){
                    httpResponse.close();
                }

            }catch (Exception e){
                e.printStackTrace();
            }

        }

        return dictionary;
    }
}
