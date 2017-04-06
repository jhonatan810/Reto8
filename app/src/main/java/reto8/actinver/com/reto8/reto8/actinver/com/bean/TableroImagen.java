package reto8.actinver.com.reto8.reto8.actinver.com.bean;

/**
 * Created by Jhonatan on 30/03/2017.
 */

public class TableroImagen {

    private final String nombre;
    private final int logoRes;

    public TableroImagen(String nombre, int logoRes) {
        this.nombre = nombre;
        this.logoRes = logoRes;
    }

    public String getNombre() {
        return nombre;
    }

    public int getLogoRes() {
        return logoRes;
    }
}
