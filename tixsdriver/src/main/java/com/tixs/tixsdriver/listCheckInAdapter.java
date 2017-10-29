package com.tixs.tixsdriver;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.view.LayoutInflater;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.TaskCompletionSource;
import com.tixs.database.Crianca;
import com.tixs.database.Responsavel;
import com.tixs.database.Van;
import com.tixs.maps.EnderecoBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pedro on 28/10/2017.
 */

public class listCheckInAdapter extends BaseAdapter {

    private Context mContext;
    private List<Crianca> mArrSchoolData;

    public listCheckInAdapter(Context context, @NonNull List<Crianca> objects) {
        super();
        mContext = context;
        mArrSchoolData = objects;
    }

    public int getCount() {
        // return the number of records
        return mArrSchoolData.size();
    }

    // getView method is called for each item of ListView
    public View getView(final int position, View view, ViewGroup parent) {
        // inflate the layout for each item of listView
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.custom_list_view_layout, parent, false);


        // get the reference of textView and button
        TextView txtSchoolTitle = (TextView) view.findViewById(R.id.txtSchoolTitle);
        Button btnAction = (Button) view.findViewById(R.id.btnAction);

        // Set the title and button name
        txtSchoolTitle.setText(mArrSchoolData.get(position).nome);
        btnAction.setText("Action " + position);

        // Click listener of button
        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mArrSchoolData.get(position).aguardando == true && mArrSchoolData.get(position).emTransito == false && mArrSchoolData.get(position).entregue == false){
                    mArrSchoolData.get(position).aguardando = false;
                    mArrSchoolData.get(position).emTransito = true;
                    mArrSchoolData.get(position).entregue = false;
                }
                if(mArrSchoolData.get(position).aguardando == false && mArrSchoolData.get(position).emTransito == true && mArrSchoolData.get(position).entregue == false){
                    mArrSchoolData.get(position).aguardando = false;
                    mArrSchoolData.get(position).emTransito = false;
                    mArrSchoolData.get(position).entregue = true;
                }
            }
        });

        return view;
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }}