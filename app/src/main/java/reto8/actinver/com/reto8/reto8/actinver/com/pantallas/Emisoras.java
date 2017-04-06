package reto8.actinver.com.reto8.reto8.actinver.com.pantallas;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AlphabetIndexer;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import reto8.actinver.com.reto8.R;
import reto8.actinver.com.reto8.reto8.actinver.com.Tabla.TableFixHeaderAdapter;
import reto8.actinver.com.reto8.reto8.actinver.com.Tabla.TableFixHeadersAdapterFactory;
import reto8.actinver.com.reto8.reto8.actinver.com.Tabla.adaptadores.TableFixHeaders;
import reto8.actinver.com.reto8.reto8.actinver.com.Tabla.original_sortable.NexusWithImage;
import reto8.actinver.com.reto8.reto8.actinver.com.Tabla.original_sortable.OriginalBodyCellViewGroup;
import reto8.actinver.com.reto8.reto8.actinver.com.Tabla.original_sortable.OriginalFirstBodyCellViewGroup;
import reto8.actinver.com.reto8.reto8.actinver.com.Tabla.original_sortable.OriginalSortableTableFixHeader;
import reto8.actinver.com.reto8.reto8.actinver.com.Tabla.original_sortable.OriginalSortableTableFixHeaderAdapter;
import reto8.actinver.com.reto8.reto8.actinver.com.bean.Tablero;


public class Emisoras extends AppCompatActivity{

//the request should be done by



    private TableFixHeaders tableFixHeaders;
    private TableFixHeadersAdapterFactory tableFixHeadersAdapterFactory;
    private OriginalSortableTableFixHeaderAdapter adapter;
    private static TextView lblEmisora;
    private static TextView lblUltimoPrecio;
    private static TextView lblPorcentajeVar;
    private static TextView lblCompra;
    private static TextView lblPrecioCompra;
    private static TextView lblVenta;
    private static TextView lblPrecioVenta;
    private Button btnCompra;
    private Button btnVenta;

    public static Tablero tablero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_emisoras);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        this.lblEmisora = (TextView) findViewById(R.id.lblEmisora);
        this.lblUltimoPrecio = (TextView) findViewById(R.id.lblUltimoPrecio);
        this.lblPorcentajeVar = (TextView) findViewById(R.id.lblPorcentajeVar);
        this.lblCompra = (TextView) findViewById(R.id.lblCompra);
        this.lblPrecioCompra = (TextView) findViewById(R.id.lblPrecioCompra);
        this.lblVenta = (TextView) findViewById(R.id.lblVenta);
        this.lblPrecioVenta = (TextView) findViewById(R.id.lblPrecioVenta);
        this.btnCompra = (Button) findViewById(R.id.btnCompra);
        this.btnVenta = (Button) findViewById(R.id.btnVenta);


        tableFixHeaders = (TableFixHeaders) findViewById(R.id.tablefixheaders);
        tableFixHeadersAdapterFactory = new TableFixHeadersAdapterFactory(this);

        Emisoras.tablero = new Tablero();

         adapter = tableFixHeadersAdapterFactory.getAdapter(TableFixHeadersAdapterFactory.ORIGINAL_SORTABLE);

        adapter.setClickListenerFirstBody(new TableFixHeaderAdapter.ClickListener<NexusWithImage, OriginalFirstBodyCellViewGroup>() {
            @Override
            public void onClickItem(NexusWithImage nexusWithImage, OriginalFirstBodyCellViewGroup originalFirstBodyCellViewGroup, int row, int column) {
                Toast.makeText(getApplicationContext(), "f", Toast.LENGTH_SHORT);
                Log.i("Mensaje", "jdr");
            }
        });

        tableFixHeaders.setAdapter(adapter);

        final Handler handler = new Handler();
        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                handler .post(new Runnable() {
                    public void run() {
                        try {
                            adapter = tableFixHeadersAdapterFactory.getAdapter(TableFixHeadersAdapterFactory.ORIGINAL_SORTABLE);
                            tableFixHeaders.setAdapter(adapter);
                            actualizaFormulario(tablero);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            });
        }
        };
        timer.schedule(task, 0, 1000);  // interval of one minute


        listeners();
    }


    public void listeners(){
        this.btnCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validarFormulario()){
                    Intent iCompra = new Intent(getApplicationContext(), Compra.class);
                    iCompra.putExtra("precio", String.valueOf(lblUltimoPrecio.getText()));
                    iCompra.putExtra("emisora", String.valueOf(lblEmisora.getText()).split(" ")[0]);
                    iCompra.putExtra("serie", String.valueOf(lblEmisora.getText()).split(" ")[1]);
                    iCompra.putExtra("ultimoPrecio", String.valueOf(lblUltimoPrecio.getText()));
                    startActivity(iCompra);
                }else{
                    Toast.makeText(getApplicationContext(), "Selecciona una emisora", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public boolean validarFormulario(){
        try {
            if(lblUltimoPrecio.getText().toString().isEmpty()){
                return false;
            }
            if(lblEmisora.getText().toString().isEmpty()){
                return false;
            }
            if(lblEmisora.getText().toString().isEmpty()){
                return false;
            }
            if(lblUltimoPrecio.getText().toString().isEmpty()){
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }


// and the result will be collected in

    public static void actualizaFormulario(Tablero tablero){
        try{
            lblEmisora.setText(Emisoras.tablero.getEmisora());
            lblUltimoPrecio.setText(String.valueOf(Emisoras.tablero.getUltimoPrecio()));
            lblPorcentajeVar.setText(String.valueOf(Emisoras.tablero.getPorVar()));
            lblCompra.setText(String.valueOf(Emisoras.tablero.getCompra()));
            lblPrecioCompra.setText(String.valueOf(Emisoras.tablero.getPrecioCompra()));
            lblVenta.setText(String.valueOf(Emisoras.tablero.getVenta()));
            lblPrecioVenta.setText(String.valueOf(Emisoras.tablero.getPrecioVenta()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
