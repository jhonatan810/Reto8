package reto8.actinver.com.reto8.reto8.actinver.com.Tabla.original_sortable;

import android.content.Context;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.util.Log;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import reto8.actinver.com.reto8.R;
import reto8.actinver.com.reto8.reto8.actinver.com.Tabla.TableFixHeaderAdapter;
import reto8.actinver.com.reto8.reto8.actinver.com.Tabla.adaptadores.BaseTableAdapter;
import reto8.actinver.com.reto8.reto8.actinver.com.bean.Tablero;
import reto8.actinver.com.reto8.reto8.actinver.com.oauth.LoadUrl;
import reto8.actinver.com.reto8.reto8.actinver.com.pantallas.Emisoras;
import reto8.actinver.com.reto8.reto8.actinver.com.pantallas.OBActionResult;
import reto8.actinver.com.reto8.reto8.actinver.com.pantallas.OBHttpClient;
import reto8.actinver.com.reto8.reto8.actinver.com.utilerias.Constantes;

/**
 * Created by miguel on 05/06/2016.
 */
public class OriginalSortableTableFixHeader implements OBActionResult {
    private Context context;
    private ItemSortableCheckBox firstHeader;
    private List<ItemSortable> header;
    private List<NexusWithImage> body;



    public OriginalSortableTableFixHeader(Context context) {
        this.context = context;
    }

    public OriginalSortableTableFixHeaderAdapter getInstance() {


        OriginalSortableTableFixHeaderAdapter adapter = new OriginalSortableTableFixHeaderAdapter(context);
        body = getBody();
        header = getHeader();
        firstHeader = new ItemSortableCheckBox(header.get(header.size() - 1).text);
        adapter.setFirstHeader(firstHeader);
        adapter.setHeader(header.subList(0, header.size() - 1));
        adapter.setFirstBody(body);
        adapter.setBody(body);
        adapter.setSection(body);
        setListeners(adapter);
        return adapter;
    }

    private void updateOderIndicators(ItemSortable item, final int column, TableFixHeaderAdapter adapter) {
        final boolean orderAsc = item.order == 1;
        firstHeader.order = 0;
        for (ItemSortable itemAux : header) itemAux.order = 0;
        item.order = !orderAsc ? 1 : -1;
    }

    private void applyOrder(final boolean orderAsc, final boolean orderSectionsAsc, final int column, TableFixHeaderAdapter adapter) {
        // Order devices by column data, not really a good comparation, simply compare strings.
        Collections.sort(body, new Comparator<NexusWithImage>() {
            @Override
            public int compare(NexusWithImage nexus1, NexusWithImage nexus2) {
                if (nexus1.isSection() || nexus2.isSection()) return 1;
                else if (orderAsc)
                    return nexus1.data[column + 1].compareToIgnoreCase(nexus2.data[column + 1]);
                else
                    return nexus2.data[column + 1].compareToIgnoreCase(nexus1.data[column + 1]);
            }
        });

        // Group devices by section
        Collections.sort(body, new Comparator<NexusWithImage>() {
            @Override
            public int compare(NexusWithImage nexus1, NexusWithImage nexus2) {
                int compare = orderSectionsAsc ? nexus1.type.compareToIgnoreCase(nexus2.type) : nexus2.type.compareToIgnoreCase(nexus1.type);
                boolean areEquals = compare == 0;

                if (areEquals)
                    return nexus1.isSection() ? -1 : nexus2.isSection() ? 1 : 0;

                return compare;
            }
        });

        adapter.setBody(body);
    }

    private void setListeners(final OriginalSortableTableFixHeaderAdapter adapter) {
        TableFixHeaderAdapter.ClickListener<ItemSortableCheckBox, OriginalFirstHeaderCellViewGroup> clickListenerFirstHeader = new TableFixHeaderAdapter.ClickListener<ItemSortableCheckBox, OriginalFirstHeaderCellViewGroup>() {
            @Override
            public void onClickItem(ItemSortableCheckBox item, OriginalFirstHeaderCellViewGroup viewGroup, int row, int column) {
                updateOderIndicators(item, column, adapter);
                boolean orderAsc = item.order == 1;
                boolean orderSectionsAsc = item.orderSectionsAsc;
                applyOrder(orderAsc, orderSectionsAsc, column, adapter);

                String order = orderAsc ? "ASC" : "DESC";
              //  Snackbar.make(viewGroup, "Click on " + item.text + " (" + row + "," + column + ") " + order, Snackbar.LENGTH_SHORT).show();
            }
        };

        TableFixHeaderAdapter.ClickListener<ItemSortable, OriginalHeaderCellViewGroup> clickListenerHeader = new TableFixHeaderAdapter.ClickListener<ItemSortable, OriginalHeaderCellViewGroup>() {
            @Override
            public void onClickItem(ItemSortable item, OriginalHeaderCellViewGroup viewGroup, int row, int column) {
                updateOderIndicators(item, column, adapter);
                boolean orderAsc = item.order == 1;
                boolean orderSectionsAsc =firstHeader.orderSectionsAsc;
                applyOrder(orderAsc, orderSectionsAsc, column, adapter);

                String order = orderAsc ? "ASC" : "DESC";
                //Snackbar.make(viewGroup, "Click on " + item.text + " (" + row + "," + column + ") " + order, Snackbar.LENGTH_SHORT).show();
            }
        };

        TableFixHeaderAdapter.ClickListener<NexusWithImage, OriginalBodyCellViewGroup> clickListenerBody = new TableFixHeaderAdapter.ClickListener<NexusWithImage, OriginalBodyCellViewGroup>() {
            @Override
            public void onClickItem(NexusWithImage item, OriginalBodyCellViewGroup viewGroup, int row, int column) {
               // Snackbar.make(viewGroup, "Click on jdr " + item.data[column + 1] + " (" + row + "," + column + ")", Snackbar.LENGTH_SHORT).show()

                        Emisoras.tablero.setEmisora(item.data[0]);
                        Emisoras.tablero.setUltimoPrecio(new BigDecimal(item.data[1]));
                        Emisoras.tablero.setPorVar(new BigDecimal(item.data[2]));
                        Emisoras.tablero.setCompra(new BigDecimal(item.data[3]).intValue());
                        Emisoras.tablero.setPrecioCompra(new BigDecimal(item.data[4]));
                        Emisoras.tablero.setVenta(new BigDecimal(item.data[5]).intValue());
                        Emisoras.tablero.setPrecioVenta(new BigDecimal(item.data[6]));
                        Emisoras.actualizaFormulario(Emisoras.tablero);
            }
        };

        TableFixHeaderAdapter.ClickListener<NexusWithImage, OriginalFirstBodyCellViewGroup> clickListenerFirstBody = new TableFixHeaderAdapter.ClickListener<NexusWithImage, OriginalFirstBodyCellViewGroup>() {
            @Override
            public void onClickItem(NexusWithImage item, OriginalFirstBodyCellViewGroup viewGroup, int row, int column) {
              //  Snackbar.make(viewGroup, "Click on jdr2" + item.data[column + 1] + " (" + row + "," + column + ")", Snackbar.LENGTH_SHORT).show();

                Emisoras.tablero.setEmisora(item.data[0]);
                Emisoras.tablero.setUltimoPrecio(new BigDecimal(item.data[1]));
                Emisoras.tablero.setPorVar(new BigDecimal(item.data[2]));
                Emisoras.tablero.setCompra(new BigDecimal(item.data[3]).intValue());
                Emisoras.tablero.setPrecioCompra(new BigDecimal(item.data[4]));
                Emisoras.tablero.setVenta(new BigDecimal(item.data[5]).intValue());
                Emisoras.tablero.setPrecioVenta(new BigDecimal(item.data[6]));
                Emisoras.actualizaFormulario(Emisoras.tablero);

            }
        };

       /* TableFixHeaderAdapter.ClickListener<NexusWithImage, OriginalSectionCellViewGroup> clickListenerSection = new TableFixHeaderAdapter.ClickListener<NexusWithImage, OriginalSectionCellViewGroup>() {
            @Override
            public void onClickItem(NexusWithImage item, OriginalSectionCellViewGroup viewGroup, int row, int column) {
                Snackbar.make(viewGroup, "Click on " + item.type + " (" + row + "," + column + ")", Snackbar.LENGTH_SHORT).show();
            }
        };*/

        adapter.setClickListenerFirstHeader(clickListenerFirstHeader);
        adapter.setClickListenerHeader(clickListenerHeader);
        adapter.setClickListenerFirstBody(clickListenerFirstBody);
        adapter.setClickListenerBody(clickListenerBody);
        //adapter.setClickListenerSection(clickListenerSection);

    }

    private List<ItemSortable> getHeader() {
        final ItemSortable headers[] = {
                new ItemSortable("Ultimo Precio"),
                new ItemSortable("%VAR"),
                new ItemSortable("Compra"),
                new ItemSortable("Precio"),
                new ItemSortable("Venta"),
                new ItemSortable("Precio"),
                new ItemSortable("Emisora")
        };

        return Arrays.asList(headers);
    }

    OBHttpClient obclient = new OBHttpClient();
    private List<NexusWithImage> getBody(){



                            String s = "";
        try {
            s =   obclient.OBrequestHttp(OBHttpClient.METHOD_GET, Constantes.URL_ACTINVER, null);
        } catch (Exception e) {
            e.printStackTrace();
        }


        String[] renglones = s.split("\n");

                                for(int i = 0; i < renglones.length; i++) {

                                    String[] unRenglon = renglones[i].split("\\|");
                                    Log.i("EMISORA", unRenglon[1]);

                                    items.add(new NexusWithImage("", unRenglon[1] + " " + unRenglon[2], unRenglon[3], unRenglon[4], unRenglon[6], unRenglon[7], unRenglon[8], unRenglon[9]));
                                }
                            Log.i("SSSSSSSSSSSS",s);


        return items;
    }

    List<NexusWithImage> items = new ArrayList<>();


    private List<NexusWithImage> getRespuestaBody(String respueta) {
        List<Integer> resImages = Arrays.asList(R.drawable.ic_home_24dp, R.drawable.ic_android_24dp, R.drawable.ic_settings_24dp, R.drawable.ic_sd_storage_24dp, R.drawable.ic_aspect_ratio_24dp, R.drawable.ic_memory_24dp);

        String type = "";
        items.add(new NexusWithImage(type, resImages));

       // Log.i("RESPUESTA", peticion.getRespuesta());
       /*
        items.add(new NexusWithImage(type, "Nexus S", "Samsung", "Gingerbread", "10", "16 GB", "4\"", "512 MB"));
        items.add(new NexusWithImage(type, "Galaxy Nexus (16 GB)", "Samsung", "Ice cream Sandwich", "15", "16 GB", "4.65\"", "1 GB"));
        items.add(new NexusWithImage(type, "Galaxy Nexus (32 GB)", "Samsung", "Ice cream Sandwich", "15", "32 GB", "4.65\"", "1 GB"));
        items.add(new NexusWithImage(type, "Nexus 4 (8 GB)", "LG", "Jelly Bean", "17", "8 GB", "4.7\"", "2 GB"));
        items.add(new NexusWithImage(type, "Nexus 4 (16 GB)", "LG", "Jelly Bean", "17", "16 GB", "4.7\"", "2 GB"));
/*



        /*
        type = "Tablets";
        items.add(new NexusWithImage(type, resImages));
        items.add(new NexusWithImage(type, "Nexus 7 (16 GB)", "Asus", "Jelly Bean", "16", "16 GB", "7\"", "1 GB"));
        items.add(new NexusWithImage(type, "Nexus 7 (32 GB)", "Asus", "Jelly Bean", "16", "32 GB", "7\"", "1 GB"));
        items.add(new NexusWithImage(type, "Nexus 10 (16 GB)", "Samsung", "Jelly Bean", "17", "16 GB", "10\"", "2 GB"));
        items.add(new NexusWithImage(type, "Nexus 10 (32 GB)", "Samsung", "Jelly Bean", "17", "32 GB", "10\"", "2 GB"));

        type = "Others";
        items.add(new NexusWithImage(type, resImages));
        items.add(new NexusWithImage(type, "Nexus Q", "--", "Honeycomb", "13", "--", "--", "--"));
*/
        return items;
    }



    @Override
    public void completedWithResult(String urlfrom, String response) {
        Log.i("OB","url = "+urlfrom+" data = " + response);

        getRespuestaBody(response);

    }

}
