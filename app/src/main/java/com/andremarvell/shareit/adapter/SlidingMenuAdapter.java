package com.andremarvell.shareit.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.andremarvell.shareit.R;

/**
 * Created by ikounga_marvel on 12/09/2014.
 */
public class SlidingMenuAdapter extends BaseAdapter {
    private Context context;
    private final Integer[] iconIds = {
            R.drawable.menu_search,
            R.drawable.menu_plus,
            R.drawable.menu_profil,
            R.drawable.menu_car,
            R.drawable.menu_booking,
            R.drawable.menu_settings,
            R.drawable.menu_logout,
    };
    private final String[] iconTitles = {
        "Rechercher un covoiturage", "Poster une annonce","Mon profil","Mes annonces","Mes demandes","Paramètres","Se déconnecter"
    };

    public SlidingMenuAdapter(Context context) {
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            convertView = inflater.inflate(R.layout.menu_item, parent,false);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.icon = (ImageView) convertView.findViewById(R.id.icon);
            convertView.setTag(holder);

        } else
            holder = (ViewHolder) convertView.getTag();

        holder.title.setText(iconTitles[position]);
        holder.icon.setImageResource(iconIds[position]);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });

        return convertView;
    }

    private class ViewHolder {
        TextView title;
        ImageView icon;
    }

    @Override
    public int getCount() {
        return iconIds.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }



}
