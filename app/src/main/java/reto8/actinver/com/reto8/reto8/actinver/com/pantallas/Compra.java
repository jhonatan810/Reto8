package reto8.actinver.com.reto8.reto8.actinver.com.pantallas;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import java.math.BigDecimal;

import reto8.actinver.com.reto8.R;

import static android.view.View.TEXT_ALIGNMENT_CENTER;
import static android.view.View.TEXT_ALIGNMENT_GRAVITY;

public class Compra extends AppCompatActivity {

    private TextView lblPoderCompra;
    private TextView lblEmisora;
    private TextView lblSerie;
    private TextView lblPrecioMercado;
    private RadioButton rdoLimitada;
    private RadioButton rdoMercado;
    private EditText txtTitulos;
    private EditText txtPrecioOrden;
    private EditText txtCostoTotal;
    private Button btnAceptar;
    private Button btnCancelar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.lblPoderCompra = (TextView) findViewById(R.id.lblPoderCompra);
        this.lblEmisora = (TextView) findViewById(R.id.lblEmisora);
        this.lblSerie = (TextView) findViewById(R.id.lblSerie);
        this.lblPrecioMercado = (TextView) findViewById(R.id.lblPrecioMercado);
        this.rdoLimitada = (RadioButton) findViewById(R.id.rdoLimitada);
        this.rdoMercado = (RadioButton) findViewById(R.id.rdoMercado);
        this.txtTitulos = (EditText) findViewById(R.id.txtTitulos);
        this.txtPrecioOrden = (EditText) findViewById(R.id.txtPrecioOrden);
        this.txtCostoTotal = (EditText) findViewById(R.id.txtCostoTotal);
        this.btnAceptar = (Button) findViewById(R.id.btnAceptar);
        this.btnCancelar = (Button) findViewById(R.id.btnCancelar);

        this.txtPrecioOrden.setEnabled(false);
        this.txtCostoTotal.setEnabled(false);
        listeners();
        llenarFormulario();
    }



    public void llenarFormulario(){
        try{
            if(getIntent().getExtras()!=null){

                if(getIntent().getExtras().getString("precio")!=null){
                    lblPoderCompra.setText(getIntent().getExtras().getString("precio"));
                }
                if(getIntent().getExtras().getString("emisora")!=null){
                    lblEmisora.setText(getIntent().getExtras().getString("emisora"));
                }
                if(getIntent().getExtras().getString("serie")!=null){
                    lblSerie.setText(getIntent().getExtras().getString("serie"));
                }
                if(getIntent().getExtras().getString("ultimoPrecio")!=null){
                    lblPrecioMercado.setText(getIntent().getExtras().getString("ultimoPrecio"));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void listeners(){
        this.btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iEmisoras = new Intent(getApplicationContext(), Emisoras.class);
                startActivity(iEmisoras);
            }
        });

        this.rdoLimitada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtPrecioOrden.setEnabled(true);
                txtPrecioOrden.setText("");

                calcularCostoPrecioOrden(txtPrecioOrden.getText().toString());
                if(txtTitulos.getText().toString().isEmpty() || txtPrecioOrden.getText().toString().isEmpty()){
                    txtCostoTotal.setText("");
                }
            }
        });

        this.rdoMercado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtPrecioOrden.setEnabled(false);
                txtPrecioOrden.setText(" "+lblPrecioMercado.getText().toString());
                calcularCostoTitulos(txtTitulos.getText().toString());

                if(txtTitulos.getText().toString().isEmpty() || txtPrecioOrden.getText().toString().isEmpty()){
                    txtCostoTotal.setText("");
                }
            }
        });

        this.txtTitulos.addTextChangedListener(new AbsListView(getApplicationContext()) {
            @Override
            public ListAdapter getAdapter() {
                return null;
            }

            @Override
            public void setSelection(int position) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calcularCostoTitulos(s.toString());
                super.onTextChanged(s, start, before, count);
            }
        });

        this.txtPrecioOrden.addTextChangedListener(new AbsListView(getApplicationContext()) {
            @Override
            public ListAdapter getAdapter() {
                return null;
            }

            @Override
            public void setSelection(int position) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calcularCostoPrecioOrden(s.toString());
                super.onTextChanged(s, start, before, count);
            }
        });


    }


    public void calcularCostoTitulos(String s){
        try{
            if(!s.toString().isEmpty()){
                BigDecimal titulos = new BigDecimal(s.toString());

                if(!txtPrecioOrden.getText().toString().isEmpty()){
                    BigDecimal precio = new BigDecimal(txtPrecioOrden.getText().toString().replaceAll(" ","").replaceAll(",",""));

                    txtCostoTotal.setText(" "+titulos.multiply(precio));
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void calcularCostoPrecioOrden(String s){
        try{
            if(!s.toString().isEmpty()){
                if(!txtTitulos.getText().toString().isEmpty()){
                    BigDecimal titulos = new BigDecimal(txtTitulos.getText().toString());
                    BigDecimal precio = new BigDecimal(s.replaceAll(" ","").replaceAll(",",""));
                    txtCostoTotal.setText(" "+titulos.multiply(precio));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
