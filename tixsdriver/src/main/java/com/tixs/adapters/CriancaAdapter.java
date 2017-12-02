package com.tixs.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import com.tixs.database.Crianca;

import java.util.List;

/**
 * Created by moeda on 28/11/2017.
 */

public class CriancaAdapter extends ArrayAdapter<Crianca> {

    public CriancaAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    public CriancaAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Crianca> objects) {
        super(context, resource, objects);
    }

//    @Override
//    public boolean isEnabled(int position) {
//        return this.getItem(position).confirma_ida;
//    }

}
