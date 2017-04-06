package reto8.actinver.com.reto8.reto8.actinver.com.utilerias;

import android.os.StrictMode;

import reto8.actinver.com.reto8.reto8.actinver.com.oauth.OAuth2Config;
import reto8.actinver.com.reto8.reto8.actinver.com.oauth.OAuthUtils;

/**
 * Created by Jhonatan on 30/03/2017.
 */

public class Utilerias {

    public static String getAccesToken(String usuario, String password, String urlToken){
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        String accesToken = "";
        try {
            OAuth2Config.OAuth2ConfigBuilder builder = new OAuth2Config.OAuth2ConfigBuilder(usuario,password,"","",urlToken);
            builder.scope("DEFAULT_SCOPE");
            builder.grantType("client_credentials");

            accesToken = OAuthUtils.getAccessToken(builder.build()).getAccessToken();

        }catch (Exception e){
            e.printStackTrace();
        }
        return accesToken;
    }

}
