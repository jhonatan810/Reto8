package reto8.actinver.com.reto8.reto8.actinver.com.pantallas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import android.os.Handler;
import android.util.Log;

import reto8.actinver.com.reto8.reto8.actinver.com.oauth.HttpRequest;
import reto8.actinver.com.reto8.reto8.actinver.com.pantallas.OBActionResult;

public class OBHttpClient {
    /** The time it takes for our client to timeout */
    public static final int HTTP_TIMEOUT = 60 * 1000; // milliseconds

    /** Single instance of our HttpClient */
    private HttpClient mHttpClient;
    /** Asynchronous request handler */
    private Handler mHandler;
    public static int METHOD_GET = 1;
    public static int METHOD_POST = 2;
    /**
     * Get our single instance of our HttpClient object.
     *
     * @return an HttpClient object with connection parameters set
     */
    public HttpClient getHttpClient() {
        if (mHttpClient == null) {
            mHttpClient = new DefaultHttpClient();
            final HttpParams params = mHttpClient.getParams();
            HttpConnectionParams.setConnectionTimeout(params, HTTP_TIMEOUT);
            HttpConnectionParams.setSoTimeout(params, HTTP_TIMEOUT);
            ConnManagerParams.setTimeout(params, HTTP_TIMEOUT);
        }
        return mHttpClient;
    }

    /**
     * Performs an HTTP synchronous request to the specified url with the
     * specified parameters of get or post.
     * @param method GET or POST method
     * @param url The web address to post the request to
     * @param postParameters The parameters to send via the request for POST request – null for GET request
     * @return The result of the request as a string
     * @throws Exception
     */
    public String OBrequestHttp(int method, String url, ArrayList<NameValuePair> postParameters) throws Exception {
        BufferedReader in = null;
        try {
            HttpClient client = getHttpClient();
            if(method == METHOD_GET){
               /* HttpGet request = new HttpGet();
                request.setURI(new URI(url));
                HttpResponse response = client.execute(request);
                in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
*/
                HttpRequest httpRequest =  HttpRequest.get(url);
                //  httpRequest.authorization("Bearer "+accesToken);
                httpRequest.accept("application/json");
                return httpRequest.body();

            }
            else if(method == METHOD_POST){



                HttpPost postrequest = new HttpPost(url);
                UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(postParameters);
                postrequest.setEntity(formEntity);
                HttpResponse postresponse = client.execute(postrequest);
                in = new BufferedReader(new InputStreamReader(postresponse.getEntity().getContent()));
            }
            else{
                return "INVALID METHOD";
            }
            StringBuffer sb = new StringBuffer("");
            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                sb.append(line + NL);
            }
            in.close();

            String result = sb.toString();
            //Log.d("Login Response", result);
            return result;
        } catch(Exception e){
            e.printStackTrace();
            return "REQUEST ERROR";
        }
        finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Performs an HTTP asynchronous request to the specified url with the
     * specified parameters of get or post.
     * @param method GET or POST method
     * @param url The web address to post the request to
     * @param postParameters The parameters to send via the request for POST request – null for GET request
     * @type which url the request has suggested

     * @obaction result send back to the activity

     * @return The result of the request as a string
     * @throws Exception


     */


    public void OBrequestAsyncHttp(final int method, final String urls,final ArrayList<NameValuePair> formParams,final OBActionResult obaction) {
        if (mHandler == null){
            mHandler = new Handler();
        }
        //Log.i("OB","Thread started for url = " + urls);
        new Thread(new Runnable() {
            public void run() {
                final String restResponse;
                restResponse = requestposturl(method, urls, formParams);
                mHandler.post(new Runnable() {
                    public void run() {
                        obaction.completedWithResult("", restResponse);
                        //Log.i("OB","Result sent");
                    }
                });
            }

            private String requestposturl(int method, String urls, ArrayList<NameValuePair> formParams) {
                try{
                    //Log.i("OB","Url = " + urls);
                    String response = OBrequestHttp(method,urls, formParams);
                    return response;
                }
                catch(Exception e){Log.i("OB","Something error");return "";}
            }
        }).start();
    }
}

