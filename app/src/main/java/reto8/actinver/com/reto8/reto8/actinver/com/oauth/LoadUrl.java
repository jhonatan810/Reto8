package reto8.actinver.com.reto8.reto8.actinver.com.oauth;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by Jhonatan on 30/03/2017.
 */


public class LoadUrl extends AsyncTask<String, Long, String> {

    private String accesToken;
    private String respuesta;

    public LoadUrl(String accesToken){
        this.accesToken = accesToken;
    }

    protected String doInBackground(String... urls) {
        try {
            HttpRequest httpRequest =  HttpRequest.get(urls[0]);
          //  httpRequest.authorization("Bearer "+accesToken);
            httpRequest.accept("application/json");
            return httpRequest.body();
        } catch (HttpRequest.HttpRequestException exception) {
            return null;
        }
    }

    protected void onPostExecute(String response) {
        Log.i("JDR", response);
        this.respuesta = response;
        //lblRespuesta.setText(response);
    }



    public String getAccesToken() {
        return accesToken;
    }

    public void setAccesToken(String accesToken) {
        this.accesToken = accesToken;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
}