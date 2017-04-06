package reto8.actinver.com.reto8.reto8.actinver.com.adaptador;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.LongPressAwareTableDataAdapter;
import reto8.actinver.com.reto8.R;
import reto8.actinver.com.reto8.reto8.actinver.com.bean.Tablero;


public class TableroDataAdapter extends LongPressAwareTableDataAdapter<Tablero> {

    private static final int TEXT_SIZE = 14;
    private static final NumberFormat PRICE_FORMATTER = NumberFormat.getNumberInstance();


    public TableroDataAdapter(final Context context, final List<Tablero> data, final TableView<Tablero> tableView) {
        super(context, data, tableView);
    }

    @Override
    public View getDefaultCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        final Tablero renglon = getRowData(rowIndex);
        View renderedView = null;

        switch (columnIndex) {

            case 0://ID
                renderedView = renderString(String.valueOf(renglon.getId()));
                break;
            case 1://EMISORA
                renderedView = renderString(renglon.getEmisora());
                break;
            case 2://ULTIMO PRECIO
                renderedView = renderBigDecimal(renglon.getUltimoPrecio());
                break;
            case 3://%VAR
                renderedView = renderBigDecimal(renglon.getPorVar());
                break;
            case 4://COMPRA
                renderedView = renderString(String.valueOf(renglon.getCompra()));
                break;
            case 5:// PRECIO COMPRA
                renderedView = renderBigDecimal(renglon.getPrecioCompra());
                break;
            case 6://Venta
                renderedView = renderString(String.valueOf(renglon.getVenta()));
                break;
            case 7:// PRECIO VENTA
                renderedView = renderBigDecimal(renglon.getPrecioVenta());
                break;
            case 8:// VISTA
                renderedView = renderImagenVista(renglon, parentView);
                break;
            case 9:// GRAFICA
                renderedView = renderImagenGrafica(renglon,parentView);
                break;
        }

        return renderedView;
    }

    @Override
    public View getLongPressCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        final Tablero tablero = getRowData(rowIndex);
        View renderedView = null;

        switch (columnIndex) {
            case 1:
                //renderedView = renderEditableCatName(car);
                break;
            default:
             //   renderedView = getDefaultCellView(rowIndex, columnIndex, parentView);
        }

        return renderedView;
    }


  /*  private View renderEditableCatName(final Car car) {
        final EditText editText = new EditText(getContext());
        editText.setText(car.getName());
        editText.setPadding(20, 10, 20, 10);
        editText.setTextSize(TEXT_SIZE);
        editText.setSingleLine();
        editText.addTextChangedListener(new CarNameUpdater(car));
        return editText;
    }
    */

    private View renderBigDecimal(final BigDecimal valor) {
        final String priceString = PRICE_FORMATTER.format(valor);

        final TextView textView = new TextView(getContext());
        textView.setText(priceString);
        textView.setPadding(20, 10, 20, 10);
        textView.setTextSize(TEXT_SIZE);



        if (valor.doubleValue() < 50000) {
            textView.setTextColor(0xFF2E7D32);
        } else if (valor.doubleValue() > 100000) {
            textView.setTextColor(0xFFC62828);
        }

        return textView;
    }

   /* private View renderPower(final Car car, final ViewGroup parentView) {
        final View view = getLayoutInflater().inflate(R.layout.table_cell_power, parentView, false);
        final TextView kwView = (TextView) view.findViewById(R.id.kw_view);
        final TextView psView = (TextView) view.findViewById(R.id.ps_view);

        kwView.setText(format(Locale.ENGLISH, "%d %s", car.getKw(), getContext().getString(R.string.kw)));
        psView.setText(format(Locale.ENGLISH, "%d %s", car.getPs(), getContext().getString(R.string.ps)));

        return view;
    }*/


    private View renderImagenVista(final Tablero tablero, final ViewGroup parentView) {
        final View view = getLayoutInflater().inflate(R.layout.table_cell_image, parentView, false);
        final ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setImageResource(tablero.getVista().getLogoRes());
        return view;
    }

    private View renderImagenGrafica(final Tablero tablero, final ViewGroup parentView) {
        final View view = getLayoutInflater().inflate(R.layout.table_cell_image, parentView, false);
        final ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setImageResource(tablero.getGrafica().getLogoRes());
        return view;
    }

    private View renderString(final String value) {
        final TextView textView = new TextView(getContext());
        textView.setText(value);
        textView.setPadding(20, 10, 20, 10);
        textView.setTextSize(TEXT_SIZE);
        return textView;
    }

    /*
    private static class CarNameUpdater implements TextWatcher {

        private Car carToUpdate;

        public CarNameUpdater(Car carToUpdate) {
            this.carToUpdate = carToUpdate;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // no used
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // not used
        }

        @Override
        public void afterTextChanged(Editable s) {
            carToUpdate.setName(s.toString());
        }
    }
*/
}
