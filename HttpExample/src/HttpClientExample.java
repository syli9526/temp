import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HttpClientExample {

    public static void main(String args[]){

        try {
            getRequest("","","");
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<NameValuePair> body = new ArrayList<NameValuePair>();

        body.add(new BasicNameValuePair("keyword","Foo"));
        body.add(new BasicNameValuePair("cursor",""));
        body.add(new BasicNameValuePair("size","20"));




    }

    public static String getRequest(String url, String token, String contentType) throws ClientProtocolException, IOException {

        //http client 생성
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //get 메서드와 URL 설정
        //HttpGet httpGet = new HttpGet(GET_URL);
        HttpGet httpGet = new HttpGet(url);

        //agent 정보 설정
        httpGet.addHeader("Content-Type",contentType);

        /*
        //agent 정보 설정
        httpGet.addHeader("User-Agent", USER_AGENT);
        httpGet.addHeader("Content-type", "application/json");
         */

        // token 설정
        httpGet.setHeader("Authorizetion", "Bearer " + token);

        //get 요청
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

        System.out.println("GET Response Status");
        System.out.println(httpResponse.getStatusLine().getStatusCode());
        String responseBody = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");

        System.out.println(responseBody);

        httpClient.close();
        return responseBody;
    }


    public static String postRequset(String url, String token,String contentType, List<NameValuePair> body)throws ClientProtocolException, IOException{

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://example.com/");

        //agent 정보 설정
        httpPost.addHeader("Content-Type",contentType);

        // token 설정
        httpPost.setHeader("Authorizetion", "Bearer " + token);

        // Body 추가
        httpPost.setEntity(new UrlEncodedFormEntity(body,"UTF-8"));

        /*
        HttpUriRequest request = RequestBuilder.get()
                .setUri(url)
                .setHeader("Content-Type",contentType)
                .addHeader("Authorizetion",token)
                .build();
        httpClient.execute(request);
         */

        HttpResponse httpResponse = httpClient.execute(httpPost);
        System.out.println("POST Response Status");
        System.out.println(httpResponse.getStatusLine().getStatusCode());
        String responseBody = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");

        System.out.println(responseBody);

        return responseBody;




    }



}
