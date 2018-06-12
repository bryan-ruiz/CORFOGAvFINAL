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
import com.example.bryan.corfoga.Adarter.AnimalAdapter;
import com.example.bryan.corfoga.Class.Farm;
import com.example.bryan.corfoga.Class.Global;
import com.example.bryan.corfoga.Class.Inspection;
import com.example.bryan.corfoga.Class.InspectionVisitComment;
import com.example.bryan.corfoga.R;

import java.util.ArrayList;
import java.util.List;

public class AnimalActivity extends AppCompatActivity {
    private ListView listView;
    private AnimalAdapter animalAdapter;
    private ArrayList<Animal> listItems;
    private Animal animal;
    private EditText buscar;
    private AlertDialog.Builder alert;
    private ArrayList<InspectionVisitComment> visitCommentlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal);
        listView = (ListView) findViewById(R.id.listAnimals);
        buscar = (EditText) findViewById(R.id.buscar);
        Farm farm = Global.getInstance().getFarm();
        visitCommentlist = farm.getAnimalVisitCommentState(getApplicationContext());
        fillAnimalList();
        checkMatchColor();
        mostrarLista();
        buscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().equals("")){
                    fillAnimalList();
                    mostrarLista();
                }
                else{
                    //Hacer la busqueda
                    buscarAnimal(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
    private void buscarAnimal(String busqueda){

        for(Animal ani : listItems ){
            if(!(ani.getId()+"").contains(busqueda)){
                listItems.remove(ani);
            }
        }
        animalAdapter.notifyDataSetChanged();

    }
    private void mostrarLista(){
        listView = (ListView) findViewById(R.id.listFarms);
        alert = new AlertDialog.Builder(this);
        animalAdapter = new AnimalAdapter(listItems,this);
        listView.setAdapter(animalAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Animal selectedAnimal = (Animal) listView.getItemAtPosition(i);
                Global.getInstance().setAnimal(selectedAnimal);
                boolean exist = Global.getInstance().getAnimalVisitCommentState(getApplicationContext(), animal.getAsocebuFarmID(), animal.getId());
                if (exist) {
                    showAlert("Animal guardado anteriormente. Imposible editar.",false);
                }
                else {
                    Intent intent = new Intent(getBaseContext(), InspectionActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
    private void checkMatchColor() {
        for (Animal animal: listItems) {
            for (InspectionVisitComment inspectionVisitComment: visitCommentlist) {
                if (animal.getId() == inspectionVisitComment.getAnimalID()) {
                    animal.setState(inspectionVisitComment.getObservations());
                }
            }
        }
    }

    /*
    * metodo encargado de mostrar mensajes de alerta
    * */
    private void showAlert(String message, boolean state) {
        if (state) {
            alert.setTitle("¡Listo!");
            alert.setIcon(R.drawable.success);
        }
        else {
            alert.setTitle("¡Error!");
            alert.setIcon(R.drawable.failed);
        }
        alert.setMessage(message);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        alert.show();
    }

    private void fillAnimalList() {
        listItems = cloneList(Global.getInstance().getAnimalsList());
    }
    public static ArrayList<Animal> cloneList(List<Animal> listaAnim) {
        ArrayList<Animal> clonedList = new ArrayList<Animal>(listaAnim.size());
        for (Animal ani : listaAnim) {
            clonedList.add(new Animal(ani));
        }
        return clonedList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_buscador_animal, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        fillAnimalList();
        listView = (ListView) findViewById(R.id.listFarms);
        animalAdapter = new AnimalAdapter(listItems,this);
        listView.setAdapter(animalAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Animal selectedAnimal = (Animal) listView.getItemAtPosition(i);
                Global.getInstance().setAnimal(selectedAnimal);
                Intent intent = new Intent(getBaseContext(), InspectionActivity.class);
                startActivity(intent);
            }
        });
    }
}
