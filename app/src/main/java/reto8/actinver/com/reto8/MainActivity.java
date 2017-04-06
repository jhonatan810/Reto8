package reto8.actinver.com.reto8;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import reto8.actinver.com.reto8.reto8.actinver.com.oauth.LoadUrl;
import reto8.actinver.com.reto8.reto8.actinver.com.oauth.OAuthUtils;
import reto8.actinver.com.reto8.reto8.actinver.com.pantallas.Emisoras;
import reto8.actinver.com.reto8.reto8.actinver.com.utilerias.Constantes;
import reto8.actinver.com.reto8.reto8.actinver.com.utilerias.Utilerias;

public class MainActivity extends AppCompatActivity {


    private EditText txtUsuario;
    private EditText txtPassword;
    private Button btnIngresar;
    public Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.txtUsuario = (EditText) findViewById(R.id.txtUsuario);
        this.txtPassword = (EditText) findViewById(R.id.txtPaassword);
        this.btnIngresar = (Button) findViewById(R.id.btnIngresar);
        context = this;


        listeners();
    }




    public void listeners(){

        this.btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validarCampos()){
                    /*String token = Utilerias.getAccesToken(txtUsuario.getText().toString().trim(), txtPassword.getText().toString().trim(), Constantes.URL_TOKEN);

                    if(OAuthUtils.codigo == 400 || token == null || token.isEmpty()){

                        Toast  t = Toast.makeText(context, "Ocurrio un error intentalo de nuevo", Toast.LENGTH_LONG);
                        t.setGravity(Gravity.CENTER,0,0);
                        t.show();
                        return;
                    }
                    String url = String.format(Constantes.URL_JSON);

                    LoadUrl peticion = new LoadUrl(token);
                    peticion.execute(url);

                    //Log.i("RESPUESTA", peticion.getRespuesta());
*/
                    Intent ListSong = new Intent(getApplicationContext(), Emisoras.class);
                    startActivity(ListSong);


                }
            }
        });
    }



    public boolean validarCampos(){
        try{
            if(this.txtUsuario.getText().toString().trim().isEmpty()){
                this.txtUsuario.setError("Ingresar usuario");
                this.txtUsuario.requestFocus();
                return  false;
            }
            if(this.txtPassword.getText().toString().trim().isEmpty()){
                this.txtPassword.setError("Ingresar password");
                this.txtPassword.requestFocus();
                return  false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

}
