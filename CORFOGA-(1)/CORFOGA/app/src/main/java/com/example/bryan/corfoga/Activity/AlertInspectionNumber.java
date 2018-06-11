package com.example.bryan.corfoga.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bryan.corfoga.Class.Global;
import com.example.bryan.corfoga.R;

import java.util.Calendar;

public class AlertInspectionNumber extends AppCompatActivity{
    private Button inspectionOne, inspectionTwo, inspectionThree,inspectionFour;
    private Calendar cal;
    private int month, visitNumberForFarm;
    private AlertDialog.Builder alert;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alert_inspection_number_view);
        loadEnvironment();
        setEnableVisitNumber();
        setOnClicks();
    }

    private void buttonChangePropperties(Button button) {
        button.setBackgroundColor(Color.GREEN);
        button.setTextColor(Color.BLACK);
    }

    private void setEnableVisitNumber() {
        cal = Calendar.getInstance();
        month = cal.get(Calendar.MONTH) + 1;
        if (month >= 1 && month <=3) {
            buttonChangePropperties(inspectionOne);
            visitNumberForFarm = 1;
        }
        else if (month >= 4 && month <=6) {
            buttonChangePropperties(inspectionTwo);
            visitNumberForFarm = 2;
        }
        else if (month >= 7 && month <=9) {
            buttonChangePropperties(inspectionThree);
            visitNumberForFarm = 3;
        }
        else {
            buttonChangePropperties(inspectionFour);
            visitNumberForFarm = 4;
        }
    }

    /*
    * metodo encargado de mostrar mensajes de alerta
    * */
    private void showAlert() {
        alert.setTitle("¡Error!");
        alert.setIcon(R.drawable.failed);
        alert.setMessage("Visita no disponible, seleccione la habilitada que se encuentra en color VERDE.");
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        alert.show();
    }

    private void setOnClicks() {
        inspectionOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (visitNumberForFarm == 1) {
                    Global.getInstance().setVisitNumber(1);
                    startActivity(intent);
                }
                else {
                    showAlert();
                }
            }
        });
        inspectionTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (visitNumberForFarm == 2) {
                    Global.getInstance().setVisitNumber(2);
                    startActivity(intent);
                }
                else {
                    showAlert();
                }
            }
        });
        inspectionThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (visitNumberForFarm == 3) {
                    Global.getInstance().setVisitNumber(3);
                    startActivity(intent);
                }
                else {
                    showAlert();
                }
            }
        });
        inspectionFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (visitNumberForFarm == 4) {
                    Global.getInstance().setVisitNumber(4);
                    startActivity(intent);
                }
                else {
                    showAlert();
                }
            }
        });
    }

    private void loadEnvironment() {
        alert = new AlertDialog.Builder(this);
        intent = new Intent(getBaseContext(), AnimalActivity.class);
        inspectionOne = (Button) findViewById(R.id.inspectionOne);
        inspectionTwo = (Button) findViewById(R.id.inspectionTwo);
        inspectionThree = (Button) findViewById(R.id.inspectionThree);
        inspectionFour = (Button) findViewById(R.id.inspectionFour);
        visitNumberForFarm = 0;
    }

}
