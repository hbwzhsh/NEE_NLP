package edu.bupt.util.json;

import edu.bupt.model.Word;
import org.fnlp.nlp.corpus.WordList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shixu on 2016/11/30.
 */
public class ParseJson {
    public static void main(String[] args) throws JSONException {
        String json = "[\n" +
                "  [\n" +
                "    [\n" +
                "      {\n" +
                "        \"id\": 0,\n" +
                "        \"cont\": \"中国\",\n" +
                "        \"pos\": \"ns\",\n" +
                "        \"ne\": \"S-Ns\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 1,\n" +
                "        \"cont\": \"国家\",\n" +
                "        \"pos\": \"n\",\n" +
                "        \"ne\": \"O\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 2,\n" +
                "        \"cont\": \"主席\",\n" +
                "        \"pos\": \"n\",\n" +
                "        \"ne\": \"O\"\n" +
                "      }" +
                "    ]\n" +
                "  ]\n" +
                "]";

        JSONArray jsonArray = getJsonArrayByStr(json);
        System.out.println(parseJsonArrayToWords(jsonArray));
    }


    public static JSONArray getJsonArrayByStr(String jsonStr) {
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(jsonStr).getJSONArray(0).getJSONArray(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    public static List<Word> parseJsonArrayToWords(JSONArray jsonArray) {
        List<Word> wordList = null;
        Word word;
        try {
            wordList = new ArrayList<>(jsonArray.length());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                word = new Word();
                word.setId(jsonObject.getInt("id"));
                if (jsonObject.has("cont")) {
                    word.setCont(jsonObject.getString("cont"));
                }
                if (jsonObject.has("ne")) {
                    word.setNe(jsonObject.getString("ne"));
                }
                if (jsonObject.has("pos")) {
                    word.setPos(jsonObject.getString("pos"));
                }
                wordList.add(word);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return wordList;
    }



}