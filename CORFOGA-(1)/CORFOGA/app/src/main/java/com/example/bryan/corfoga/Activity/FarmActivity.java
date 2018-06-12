package com.example.bryan.corfoga.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bryan.corfoga.Class.Animal;
import com.example.bryan.corfoga.Class.Farm;
import com.example.bryan.corfoga.Adarter.FarmAdapter;
import com.example.bryan.corfoga.Class.Global;
import com.example.bryan.corfoga.R;

import java.util.ArrayList;
import java.util.List;

public class FarmActivity extends AppCompatActivity {
    private ListView listView;
    private FarmAdapter farmAdapter;
    private ArrayList<Farm> listItems;
    private Farm selectedFarm;
    private Intent intent;
    private AlertDialog.Builder alert;
    private EditText buscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farms);
        llenar();
        loadEnvironment();
        buscar = (EditText) findViewById(R.id.buscarF);
        listView = (ListView) findViewById(R.id.listFarms);
        mostrarLista();
        buscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().equals("")){
                    llenar();
                    mostrarLista();
                }
                else{
                    //Hacer la busqueda
                    buscarFarm(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
    private void mostrarLista(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedFarm = (Farm) listView.getItemAtPosition(i);
                Global.getInstance().setAnimalsList(selectedFarm.getAnimalListDB(getApplication()));
                Global.getInstance().setFarm(selectedFarm);
                if (Global.getInstance().getAnimalsList().isEmpty()) {
                    //Toast.makeText(getApplicationContext(),,Toast.LENGTH_LONG).show();
                    showAlert();
                }
                else {
                    startActivity(intent);
                }
            }
        });
    }
    private void buscarFarm(String busqueda){

        for(Farm fa : listItems ){
            if(!(fa.getName()+"").contains(busqueda)){
                listItems.remove(fa);
            }
        }
        farmAdapter.notifyDataSetChanged();

    }
    public static ArrayList<Farm> cloneList(List<Farm> listaAnim) {
        ArrayList<Farm> clonedList = new ArrayList<Farm>(listaAnim.size());
        for (Farm f : listaAnim) {
            clonedList.add(new Farm(f));
        }
        return clonedList;
    }
    private void loadEnvironment() {
        listView = (ListView) findViewById(R.id.listFarms);
        farmAdapter = new FarmAdapter(listItems,this);
        listView.setAdapter(farmAdapter);
        intent = new Intent(getBaseContext(), AlertInspectionNumber.class);
        alert = new AlertDialog.Builder(this);
    }
    /*
    * metodo encargado de mostrar mensajes de alerta
    * */
    private void showAlert() {
        alert.setTitle("¡Error!");
        alert.setIcon(R.drawable.failed);
        alert.setMessage("No hay animales disponibles.");
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        alert.show();
    }

    private void llenar() {
        int x = 0;
        listItems = Global.getInstance().getFarmsList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_buscador_fincas, menu);
        return true;
    }
}
