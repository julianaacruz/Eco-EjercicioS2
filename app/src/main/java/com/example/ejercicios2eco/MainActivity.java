package com.example.ejercicios2eco;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView pregunta;
    private EditText respuesta;
    private Button ok;
    private Button retry;
    private Pregunta preguntaActual;
    private TextView contador;
    private TextView puntaje;
    private int puntos=0;
    int time = 30;

    private boolean isPressing =false;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pregunta = findViewById(R.id.pregunta);
        respuesta = findViewById(R.id.respuesta);
        ok = findViewById(R.id.ok);
        contador = findViewById(R.id.contador);
        retry = findViewById(R.id.retry);
        puntaje = findViewById(R.id.puntaje);

        puntaje.setText("Puntaje: "+puntos);

        preguntaActual= new Pregunta();
        pregunta.setText(preguntaActual.getPregunta());
        contador.setText(""+time);



        setContador();

        mostrarNuevaPregunta();



        retry.setOnClickListener(
                V->{
                    retry.setVisibility(View.GONE);

                    puntos=0;
                    puntaje.setText("Puntaje: "+puntos);
                    time=30;
                    contador.setText(""+time);
                    setContador();
                    mostrarNuevaPregunta();

                }

        );

        ok.setOnClickListener(
                V->{
                    responder();
                }

        );

        pregunta.setOnTouchListener(
                (view,event)->{

                    switch (event.getAction()){

                        case MotionEvent.ACTION_DOWN:
                            isPressing=true;
                            new Thread(
                                    ()->{

                                        //contar los 1.5 segundos

                                        // sensar si el boton se libero
                                        for(int i=0; i<20;i++) {
                                            try {
                                                Thread.sleep(75);
                                                if(isPressing==false){
                                                    //matar hilo
                                                    return;
                                                }else{

                                                }



                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                        runOnUiThread(
                                                ()->{
                                                    mostrarNuevaPregunta();
                                                }
                                        );

                                    }
                            ).start();
                            break;

                        case MotionEvent.ACTION_UP:
                            isPressing=false;

                            break;
                    }

                    return true;

                }
        );

                }

    public void setContador(){
        new Thread(
                ()->{
                    while(time>0){
                        time--;

                        runOnUiThread(
                                ()->{
                                    contador.setText(""+time);
                                }
                        );

                        try {
                            Thread.sleep(1000);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                    runOnUiThread(
                            ()->{
                                retry.setVisibility(View.VISIBLE);

                            }
                    );


                }

        ).start();
    }

    public void responder(){
        String res = respuesta.getText().toString();
        int resInt = Integer.parseInt(res);
        int correcta = preguntaActual.getRespuesta();

        if(resInt==correcta){
            Toast.makeText(this, "Correcto", Toast.LENGTH_SHORT).show();
            puntos += 5;
        }else{
            Toast.makeText(this, "Incorrecto", Toast.LENGTH_SHORT).show();
            puntos -= 4;
        }
        mostrarNuevaPregunta();
        respuesta.setText("");
        puntaje.setText("Puntaje: "+puntos);


    }

    public void mostrarNuevaPregunta(){
        preguntaActual = new Pregunta();
        pregunta.setText(preguntaActual.getPregunta());
    }
}
