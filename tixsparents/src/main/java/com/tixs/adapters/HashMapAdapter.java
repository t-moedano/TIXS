package com.tixs.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.HashMap;

/**
 * Created by aline on 07/10/17.
 */

public class HashMapAdapter extends BaseAdapter {
    private final HashMap<String, String> map;

    public HashMapAdapter(HashMap<String, String> map) {
        this.map = map;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
