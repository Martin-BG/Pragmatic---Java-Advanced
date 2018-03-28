package services;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Service
public class LanguageTranslatorService {

    private static final String ENCODING = "UTF-8";
    private static final String REQUEST_AGENT = "User-Agent";
    private static final String REQUEST_VALUE = "Mozilla/5.0";
    private static final String TRANSLATE_GOOGLEAPIS =
            "https://translate.googleapis.com/translate_a/single?client=gtx&sl=%s&tl=%s&dt=t&q=%s";

    public String translate(String from, String to, String text) throws IOException {
        String url = String.format(TRANSLATE_GOOGLEAPIS, from, to, URLEncoder.encode(text, ENCODING));

        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setRequestProperty(REQUEST_AGENT, REQUEST_VALUE);

        String response = IOUtils.toString(con.getInputStream(), ENCODING);
        IOUtils.closeQuietly(con.getInputStream());

        return parseResult(response);
    }

    private String parseResult(String inputJson) {
        JSONArray jsonArray = new JSONArray(inputJson);
        JSONArray jsonArray2 = (JSONArray) jsonArray.get(0);
        JSONArray jsonArray3 = (JSONArray) jsonArray2.get(0);
        return jsonArray3.get(0).toString();
    }
}
