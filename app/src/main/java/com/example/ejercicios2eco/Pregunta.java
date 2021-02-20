package com.example.ejercicios2eco;

public class Pregunta {

    private int A, B;
    private String operador;
    private String [] operandos ={"+","-","*","/"};


    //Contructor
    public Pregunta(){
        this.A = (int)(Math.random()*11);
        this.B = (int)(Math.random()*11);
        int operadorRandom = (int)(Math.random()*4);
        this.operador = operandos[operadorRandom];

    }

    public String getPregunta(){

        if (operador.equals("/")){
            int S = A * B;
            return S +" "+operador+" "+A;

        }else{
            return A +" "+operador+" "+B;
        }
    }

    public int getRespuesta(){
        int respuesta = 0;
        switch (operador){
            case "+":
                respuesta = A + B;
                break;
            case "-":
                respuesta = A - B;
                break;
            case "*":
                respuesta = A * B;
                break;
            case "/":
                respuesta = B;
                break;

        }

        return respuesta;


    }


}
