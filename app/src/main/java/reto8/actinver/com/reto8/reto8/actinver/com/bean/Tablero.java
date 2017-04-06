package reto8.actinver.com.reto8.reto8.actinver.com.bean;

import java.math.BigDecimal;


/**
 * Created by Jhonatan on 30/03/2017.
 */

public class Tablero {

    private int id;
    private String emisora;
    private BigDecimal ultimoPrecio;
    private BigDecimal porVar;
    private int compra;
    private BigDecimal precioCompra;
    private int venta;
    private BigDecimal precioVenta;
    private TableroImagen vista;
    private TableroImagen grafica;


    public Tablero(){
        emisora = "";
        ultimoPrecio = BigDecimal.ZERO;
        porVar =  BigDecimal.ZERO;
        compra = 0;
        precioCompra  =  BigDecimal.ZERO;
        venta = 0;
        precioVenta =  BigDecimal.ZERO;

    }

    public Tablero(int id, String emisora, BigDecimal ultimoPrecio, BigDecimal porVar, int compra, BigDecimal precioCompra, int venta, BigDecimal precioVenta, TableroImagen vista, TableroImagen grafica) {
        this.grafica = grafica;
        this.id = id;
        this.emisora = emisora;
        this.ultimoPrecio = ultimoPrecio;
        this.porVar = porVar;
        this.compra = compra;
        this.precioCompra = precioCompra;
        this.venta = venta;
        this.precioVenta = precioVenta;
        this.vista = vista;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmisora() {
        return emisora;
    }

    public void setEmisora(String emisora) {
        this.emisora = emisora;
    }

    public BigDecimal getUltimoPrecio() {
        return ultimoPrecio;
    }

    public void setUltimoPrecio(BigDecimal ultimoPrecio) {
        this.ultimoPrecio = ultimoPrecio;
    }

    public BigDecimal getPorVar() {
        return porVar;
    }

    public void setPorVar(BigDecimal porVar) {
        this.porVar = porVar;
    }

    public int getCompra() {
        return compra;
    }

    public void setCompra(int compra) {
        this.compra = compra;
    }

    public BigDecimal getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(BigDecimal precioCompra) {
        this.precioCompra = precioCompra;
    }

    public int getVenta() {
        return venta;
    }

    public void setVenta(int venta) {
        this.venta = venta;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }

    public TableroImagen getVista() {
        return vista;
    }

    public void setVista(TableroImagen vista) {
        this.vista = vista;
    }

    public TableroImagen getGrafica() {
        return grafica;
    }

    @Override
    public String toString() {
        return "Tablero{" +
                "id=" + id +
                ", emisora='" + emisora + '\'' +
                ", ultimoPrecio=" + ultimoPrecio +
                ", porVar=" + porVar +
                ", compra=" + compra +
                ", precioCompra=" + precioCompra +
                ", venta=" + venta +
                ", precioVenta=" + precioVenta +
                ", vista=" + vista +
                ", grafica=" + grafica +
                '}';
    }
}
