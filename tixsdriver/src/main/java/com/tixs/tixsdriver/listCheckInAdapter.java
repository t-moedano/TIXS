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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.tixs.database.Crianca;
import com.tixs.database.Responsavel;
import com.tixs.database.Van;
import com.tixs.maps.EnderecoBuilder;

import java.util.ArrayList;
import android.graphics.Color;
import java.util.Iterator;
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
        List<Crianca> toRemove = new ArrayList<Crianca>();
        for(Crianca a: objects){
            if(a.confirma_ida != true){
                toRemove.add(a);
            }
        }
        objects.removeAll(toRemove);
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
        final Button btnAction = (Button) view.findViewById(R.id.btnAction);
        TextView escola = (TextView) view.findViewById(R.id.escola);

        // Set the title and button name
        if(mArrSchoolData.get(position).confirma_ida == true) {
            txtSchoolTitle.setText(mArrSchoolData.get(position).nome);
            escola.setText(mArrSchoolData.get(position).escola.nome);
            btnAction.setText("Aguardando");
            btnAction.setTag(1);
        }

        // Click listener of button
        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int status =(Integer) btnAction.getTag();
                if(status == 1) {
                    btnAction.setText("Em trânsito");
                    btnAction.setTag(2); //pause
                } if(status == 2) {
                    btnAction.setText("Entregue");
                    btnAction.setTag(3); //pause
                } if(status == 3) {
                    btnAction.setText("Aguardando");
                    btnAction.setTag(1); //pause
                }
            }
                /*if(btnAction.getText() == "Aguardando")
                    btnAction.setText("Em trânsito");
                if(btnAction.getText() == "Em trânsito")
                    btnAction.setText("Entregue");
                if(btnAction.getText() == "Entregue")
                   // btnAction.setText("Aguardando");
               /* final DatabaseReference raiz;
                DatabaseReference nodeA, nodeB, nodeC, nodeD, nodeE, nodeF;
                raiz = FirebaseDatabase.getInstance().getReference();
                nodeA = raiz.child(mArrSchoolData.get(position).id.toString()).orderByChild("aguardando").equalTo(true);
                nodeB = raiz.child(mArrSchoolData.get(position).id.toString()).orderByChild("emTransito").equalTo(false);
                nodeC = raiz.child(mArrSchoolData.get(position).id.toString()).orderByChild("entregue").equalTo(false);
                final Query query1 = raiz.child(mArrSchoolData.get(position).id.toString()).orderByChild("aguardando").equalTo(true);
                query1.addListenerForSingleValueEvent(new ValueEventListener(){
                    @Override
                    public void onDataChange (DataSnapshot dataSnapshot){
                        nodeA.push().setValue(false);
                        nodeB.push().setValue(true);
                        nodeC.push().setValue(false);
                        btnAction.setText("Em trânsito");
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                nodeD = (DatabaseReference) raiz.child(mArrSchoolData.get(position).id.toString()).orderByChild("aguardando").equalTo(false);
                nodeE = (DatabaseReference) raiz.child(mArrSchoolData.get(position).id.toString()).orderByChild("emTransito").equalTo(true);
                nodeF = (DatabaseReference) raiz.child(mArrSchoolData.get(position).id.toString()).orderByChild("entregue").equalTo(false);
                final Query query2 = raiz.child(mArrSchoolData.get(position).id.toString()).orderByChild("emTransito").equalTo(true);
                query2.addListenerForSingleValueEvent(new ValueEventListener(){
                    @Override
                    public void onDataChange (DataSnapshot dataSnapshot){
                        nodeD.push().setValue(false);
                        nodeE.push().setValue(false);
                        nodeF.push().setValue(true);
                        btnAction.setText("Entregue");
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
           */
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