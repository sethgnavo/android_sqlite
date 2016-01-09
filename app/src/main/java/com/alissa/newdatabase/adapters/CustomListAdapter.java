package com.alissa.newdatabase.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.alissa.newdatabase.R;
import com.alissa.newdatabase.model.Participant;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Seth-Pharès Gnavo on September 27, 2015.
 */
public class CustomListAdapter extends BaseAdapter {

    Context context;
    List<Participant> p_list = new LinkedList<Participant>();

    public CustomListAdapter(Context context, List<Participant> p_list) {
        this.context = context;
        this.p_list = p_list;
    }

    @Override
    public int getCount() {
        return p_list.size();
    }

    @Override
    public Object getItem(int position) {
        return p_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return p_list.get(position).getId();
    }

    static class Holder {
        TextView nom;
        TextView prenom;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        Participant p = p_list.get(position);
        Holder h = new Holder();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(R.layout.list_row, null);
        h.nom = (TextView) v.findViewById(R.id.textview_nom);
        h.prenom = (TextView) v.findViewById(R.id.textview_prenom);

        h.nom.setText(p.getNom());
        h.prenom.setText(p.getPrenom());

        return v;
    }
}
