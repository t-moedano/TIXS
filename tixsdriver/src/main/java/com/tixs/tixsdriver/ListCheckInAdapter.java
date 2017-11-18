package com.tixs.tixsdriver;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tixs.database.Condutor;
import com.tixs.database.Crianca;
import com.tixs.database.Responsavel;
import com.tixs.database.Van;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pedro on 28/10/2017.
 */

public class ListCheckInAdapter extends BaseAdapter {

    private Context mContext;
    private List<Crianca> mArrSchoolData;
    public Van van;
    public Condutor condutor;

    public ListCheckInAdapter(Context context, @NonNull List<Crianca> objects) {
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
        final Crianca crianca = mArrSchoolData.get(position);
        if(crianca.confirma_ida == true) {
            txtSchoolTitle.setText(mArrSchoolData.get(position).nome);
            escola.setText(mArrSchoolData.get(position).escola.nome);
            if(crianca.aguardando) {
                btnAction.setText("Aguardando");
            } else if (crianca.emTransito) {
                btnAction.setText("Em transito");
            } else if (crianca.entregue) {
                btnAction.setText("Entregue");
            } else {
                btnAction.setText("Erro");
            }
        }

        // Click listener of button
        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(crianca.aguardando) {
                    crianca.aguardando = false;
                    crianca.emTransito = true;
                    btnAction.setText("Em transito");
                } else if (crianca.emTransito) {
                    crianca.emTransito = false;
                    crianca.entregue = true;
                    btnAction.setText("Entregue");
                } else if (crianca.entregue) {
                    crianca.entregue = false;
                    crianca.aguardando = true;
                    btnAction.setText("Aguardando");
                }
                FirebaseDatabase.getInstance().getReference("condutores").child(condutor.id)
                        .setValue(condutor);
                FirebaseDatabase.getInstance().getReference("vans").child(van.id)
                        .setValue(van);
                FirebaseDatabase.getInstance().getReference("criancas").child(crianca.id)
                        .setValue(crianca);
                FirebaseDatabase.getInstance().getReference("responsaveis").child(crianca.responsavelID)
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                Responsavel responsavel = (Responsavel) dataSnapshot.getValue(Responsavel.class);
                                responsavel.addCrianca(crianca);
                                FirebaseDatabase.getInstance().getReference("responsaveis").child(crianca.responsavelID)
                                        .setValue(responsavel);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
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
    }

    synchronized private Van saveChildInVan(List<Van> vans, Van van, Crianca crianca) {
        boolean found = false;
        for (Van v : vans) {
            if (v.id.equals(van.id)) {
                van = v;
                found = true;
                break;
            }
        }
        if (!found) {
            vans.add(van);
        }
        van.addCrianca(crianca);
        return van;
    }

    synchronized private Condutor saveVanCondutor(ArrayList<Condutor> condutores, Condutor condutor, Van van) {
        boolean found = false;
        for (Condutor c : condutores) {
            if (c.id.equals(condutor.id)) {
                condutor = c;
                found = true;
                break;
            }
        }
        if (!found) {
            condutores.add(condutor);
        }
        condutor.addVan(van);
        return condutor;
    }

}