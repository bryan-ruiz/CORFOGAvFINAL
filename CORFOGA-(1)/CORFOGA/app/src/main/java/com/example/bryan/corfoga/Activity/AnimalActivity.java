package com.example.bryan.corfoga.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
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

public class AnimalActivity extends AppCompatActivity {
    private ListView listView;
    private AnimalAdapter animalAdapter;
    private ArrayList<Animal> listItems;
    private Animal animal;
    private AlertDialog.Builder alert;
    private ArrayList<InspectionVisitComment> visitCommentlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal);
        Farm farm = Global.getInstance().getFarm();
        visitCommentlist = farm.getAnimalVisitCommentState(getApplicationContext());
        fillAnimalList();
        checkMatchColor();
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
        listItems = Global.getInstance().getAnimalsList();
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
