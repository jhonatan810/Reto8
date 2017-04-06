package reto8.actinver.com.reto8.reto8.actinver.com.Tabla.original_sortable;

import android.content.Context;


import java.util.Arrays;
import java.util.List;

import reto8.actinver.com.reto8.R;
import reto8.actinver.com.reto8.reto8.actinver.com.Tabla.TableFixHeaderAdapter;
import reto8.actinver.com.reto8.reto8.actinver.com.Tabla.adaptadores.TableAdapter;
import reto8.actinver.com.reto8.reto8.actinver.com.bean.Tablero;


/**
 * Created by miguel on 11/02/2016.
 */
public class OriginalSortableTableFixHeaderAdapter extends TableFixHeaderAdapter<
        ItemSortableCheckBox, OriginalFirstHeaderCellViewGroup,
        ItemSortable, OriginalHeaderCellViewGroup,
        NexusWithImage,
        OriginalFirstBodyCellViewGroup,
        OriginalBodyCellViewGroup,
        OriginalSectionCellViewGroup> implements TableAdapter {
    private Context context;
    private Tablero tablero;

    public OriginalSortableTableFixHeaderAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected OriginalFirstHeaderCellViewGroup inflateFirstHeader() {
        return new OriginalFirstHeaderCellViewGroup(context);
    }

    @Override
    protected OriginalHeaderCellViewGroup inflateHeader() {
        return new OriginalHeaderCellViewGroup(context);
    }

    @Override
    protected OriginalFirstBodyCellViewGroup inflateFirstBody() {
        return new OriginalFirstBodyCellViewGroup(context);
    }

    @Override
    protected OriginalBodyCellViewGroup inflateBody() {
        return new OriginalBodyCellViewGroup(context);
    }

    @Override
    protected OriginalSectionCellViewGroup inflateSection() {
        return new OriginalSectionCellViewGroup(context);
    }

    @Override
    protected List<Integer> getHeaderWidths() {
        Integer[] witdhs = {
                (int) context.getResources().getDimension(R.dimen._150dp),
                (int) context.getResources().getDimension(R.dimen._120dp),
                (int) context.getResources().getDimension(R.dimen._170dp),
                (int) context.getResources().getDimension(R.dimen._80dp),
                (int) context.getResources().getDimension(R.dimen._110dp),
                (int) context.getResources().getDimension(R.dimen._80dp),
                (int) context.getResources().getDimension(R.dimen._80dp)
        };

        return Arrays.asList(witdhs);
    }

    @Override
    protected int getHeaderHeight() {
        return (int) context.getResources().getDimension(R.dimen._50dp);
    }

    @Override
    protected int getSectionHeight() {
        return (int) context.getResources().getDimension(R.dimen._0dp);
    }

    @Override
    protected int getBodyHeight() {
        return (int) context.getResources().getDimension(R.dimen._45dp);
    }

    @Override
    protected boolean isSection(List<NexusWithImage> items, int row) {
        return items.get(row).data == null;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }
}
