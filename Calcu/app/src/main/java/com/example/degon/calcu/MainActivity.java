package com.example.degon.calcu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.regex.Pattern;
import static java.lang.Double.NaN;

public class MainActivity extends AppCompatActivity {

    private TextView txtText;
    private Button btn_0;
    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    private Button btn_4;
    private Button btn_5;
    private Button btn_6;
    private Button btn_7;
    private Button btn_8;
    private Button btn_9;
    private Button btn_Suma;
    private Button btn_Resta;
    private Button btn_Division;
    private Button btn_Multiplicacion;
    private Button btn_Punto;
    private Button btn_Igual;
    private Button btn_Clear;
    private Button btn_Delete;
    private int punto;
    private String operador;
    private String operandos[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtText = (TextView) findViewById(R.id.textView);
        btn_0 = (Button) findViewById(R.id.btnCero);
        btn_1 = (Button) findViewById(R.id.btn1);
        btn_2 = (Button) findViewById(R.id.btn2);
        btn_3 = (Button) findViewById(R.id.btn3);
        btn_4 = (Button) findViewById(R.id.btn4);
        btn_5 = (Button) findViewById(R.id.btn5);
        btn_6 = (Button) findViewById(R.id.btn6);
        btn_7 = (Button) findViewById(R.id.btn7);
        btn_8 = (Button) findViewById(R.id.btn8);
        btn_9 = (Button) findViewById(R.id.btn9);
        btn_Punto = (Button) findViewById(R.id.btnPunto);
        btn_Division = (Button) findViewById(R.id.btnDivision);
        btn_Suma = (Button) findViewById(R.id.btnSuma);
        btn_Multiplicacion = (Button) findViewById(R.id.btnMultiplicacion);
        btn_Resta = (Button) findViewById(R.id.btnResta);
        btn_Igual = (Button) findViewById(R.id.btnIgual);
        btn_Delete = (Button) findViewById(R.id.btnBorrar);
        btn_Clear = (Button) findViewById(R.id.btnClear);
        operador = "";
        punto = 0;
    }

    public void Clic(View view){
        Button btn = (Button) view;
        switch (btn.getId()){
            case R.id.btnCero:
                Escribir(btn.getText().toString());
                break;
            case R.id.btn1:
                Escribir(btn.getText().toString());
                break;
            case R.id.btn2:
                Escribir(btn.getText().toString());
                break;
            case R.id.btn3:
                Escribir(btn.getText().toString());
                break;
            case R.id.btn4:
                Escribir(btn.getText().toString());
                break;
            case R.id.btn5:
                Escribir(btn.getText().toString());
                break;
            case R.id.btn6:
                Escribir(btn.getText().toString());
                break;
            case R.id.btn7:
                Escribir(btn.getText().toString());
                break;
            case R.id.btn8:
                Escribir(btn.getText().toString());
                break;
            case R.id.btn9:
                Escribir(btn.getText().toString());
                break;
            case R.id.btnBorrar:
                Borar();
                break;
            case R.id.btnResta:
                operaciones(btn.getText().toString());
                break;
            case R.id.btnSuma:
                operaciones(btn.getText().toString());
                break;
            case R.id.btnDivision:
                operaciones(btn.getText().toString());
                break;
            case R.id.btnMultiplicacion:
                operaciones(btn.getText().toString());
                break;
            case R.id.btnPunto:
                validarPunto(btn.getText().toString());
                break;
            case R.id.btnIgual:
                igual();
                break;
            case R.id.btnClear:
                clear();
                break;
        }
    }

    public void Escribir(String num){

        txtText.setText(txtText.getText() + num);

    }

    public void operaciones(String signo){
        if (operador == ""){
            Escribir(signo);
            operador = signo;
        }else {
            if (txtText.getText().toString().endsWith(operador)) {
                Escribir(signo);
            } else {
                igual();
                Escribir(signo);
                operador = signo;
            }
        }
    }

    public void validarPunto(String text){
        if (punto == 0){
            if (operador !="") {
                if (txtText.getText().toString().contains(operador)) {
                    punto++;
                }
            }
            Escribir(text);
            punto ++;
        }

        if (punto == 1){
            if (operador !="") {
                if (txtText.getText().toString().contains(operador)) {
                    Escribir(text);
                    punto++;
                }
            }
        }
    }

    public void Borar(){
        if (!txtText.getText().toString().equals("")){
            if (txtText.getText().toString().endsWith(".")){
                punto--;
            }
            if (operador != ""){
                if (txtText.getText().toString().endsWith(operador) && !txtText.getText().toString().contains(".")){
                    punto--;
                }
            }
            txtText.setText(txtText.getText().subSequence(0,txtText.getText().length()-1)+"");
        }
    }

    public void separar(){
        operandos = txtText.getText().toString().split(Pattern.quote(operador));

        if (operandos.length > 2){
            operandos[0] = "-" + operandos[1];
            operandos[1] = operandos[2];
        }
    }

    public void igual(){
        separar();
        if (operandos.length > 1) {
            if (operandos[0].isEmpty() || operandos[0] == "" || operandos[0].equals(null)){
                operandos[0] = "0.0";
            }
            if (operandos[1].isEmpty() || operandos[1] == "" || operandos[1].equals(null)){
                operandos[1] = "0.0";
            }
            txtText.setText(String.valueOf(resolver(operandos[0], operandos[1], operador)));
        }
    }

    private double resolver(String a, String b, String operacion){
        operador = "";
        punto = 1;
        switch (operacion){
            case "+":
                try {
                    return (Double.valueOf(a) + Double.valueOf(b));
                }catch (Exception e){
                    return NaN;
                }
            case "-":
                try {
                    return (Double.valueOf(a) - Double.valueOf(b));
                }catch (Exception e){
                    return NaN;
                }
            case "*":
                try {
                    return (Double.valueOf(a) * Double.valueOf(b));
                }catch (Exception e){
                    return NaN;
                }
            case "/":
                try{
                return(Double.valueOf(a) / Double.valueOf(b));
            } catch(Exception e){
                Log.d("Calc", e.getMessage());
                return NaN;
            }
            default:
                try {
                    return Double.parseDouble(txtText.getText().toString());
                }catch (Exception e){
                    return NaN;
                }
        }
    }

    public void clear(){
        operador = "";
        txtText.setText("");
        punto = 0;
    }

}

