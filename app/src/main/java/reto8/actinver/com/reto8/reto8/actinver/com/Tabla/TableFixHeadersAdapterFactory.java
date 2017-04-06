package reto8.actinver.com.reto8.reto8.actinver.com.Tabla;

import android.content.Context;

import reto8.actinver.com.reto8.reto8.actinver.com.Tabla.adaptadores.BaseTableAdapter;
import reto8.actinver.com.reto8.reto8.actinver.com.Tabla.original_sortable.OriginalSortableTableFixHeader;
import reto8.actinver.com.reto8.reto8.actinver.com.Tabla.original_sortable.OriginalSortableTableFixHeaderAdapter;
import reto8.actinver.com.reto8.reto8.actinver.com.bean.Tablero;


/**
 * Created by miguel on 12/02/2016.
 */
public class TableFixHeadersAdapterFactory {
    public static final int ORIGINAL = 0, BASIC = 1, ORIGINAL_SORTABLE = 2;
    private Context context;

    public TableFixHeadersAdapterFactory(Context context) {
        this.context = context;
    }

    public OriginalSortableTableFixHeaderAdapter getAdapter(int type) {
        switch (type) {
            case ORIGINAL_SORTABLE:
                return new OriginalSortableTableFixHeader(context).getInstance();
            default:
                return new OriginalSortableTableFixHeader(context).getInstance();
        }
    }
}
