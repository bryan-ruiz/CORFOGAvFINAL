package com.example.bryan.corfoga.Adarter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bryan.corfoga.Class.Animal;
import com.example.bryan.corfoga.R;

import java.util.ArrayList;

public class AnimalAdapter extends BaseAdapter {
    private ArrayList<Animal> listItems;
    private Context context;

    public AnimalAdapter(ArrayList<Animal> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.listItems.size();
    }

    @Override
    public Animal getItem(int i) {
        return this.listItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Animal animal = getItem(i);
        view = LayoutInflater.from(this.context).inflate(R.layout.listview_item_row_animal, null);
        TextView txtID = (TextView) view.findViewById(R.id.txtID);
        TextView txtFarm = (TextView) view.findViewById(R.id.txtFarm);
        TextView idAnimal = (TextView) view.findViewById(R.id.itemsIdAnimal);
        TextView idFarm = (TextView) view.findViewById(R.id.itemsIdAsocebu);

        idAnimal.setText(String.valueOf(animal.getId()));
        idFarm.setText(String.valueOf(animal.getAsocebuFarmID()));

        setAnimalBackgroundColorRefferedToInspectionState(view, animal, txtID, txtFarm, idAnimal, idFarm);

        return view;
    }

    private void setAnimalBackgroundColorRefferedToInspectionState(View view, Animal animal, TextView txtID, TextView txtFarm, TextView idAnimal, TextView idFarm) {
        //Toast.makeText(this,"¡Aplicación sincronizada correctamente!",Toast.LENGTH_LONG).show();
        Log.d("error",animal.getState());
        switch (animal.getState()) {
            case "Normal":
                view.setBackgroundColor(Color.RED);
                txtID.setTextColor(Color.WHITE);
                txtFarm.setTextColor(Color.WHITE);
                idAnimal.setTextColor(Color.WHITE);
                idFarm.setTextColor(Color.WHITE);
                break;
            case "Muerto":
                view.setBackgroundColor(Color.BLACK);
                txtID.setTextColor(Color.WHITE);
                txtFarm.setTextColor(Color.WHITE);
                idAnimal.setTextColor(Color.WHITE);
                idFarm.setTextColor(Color.WHITE);
                break;
            case "Comercializado":
                view.setBackgroundColor(Color.BLACK);
                txtID.setTextColor(Color.WHITE);
                txtFarm.setTextColor(Color.WHITE);
                idAnimal.setTextColor(Color.WHITE);
                idFarm.setTextColor(Color.WHITE);
                break;
            case "Externo":
                view.setBackgroundColor(Color.YELLOW);
                txtID.setTextColor(Color.BLACK);
                txtFarm.setTextColor(Color.BLACK);
                idAnimal.setTextColor(Color.BLACK);
                idFarm.setTextColor(Color.BLACK);
                break;
            default:
                view.setBackgroundColor(Color.GREEN);
                txtID.setTextColor(Color.BLACK);
                txtFarm.setTextColor(Color.BLACK);
                idAnimal.setTextColor(Color.BLACK);
                idFarm.setTextColor(Color.BLACK);
                break;
        }
    }
}
