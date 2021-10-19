package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class AdvActivity extends AppCompatActivity {

    private static final String OPERATION_NO_SELECT = "Nie wybrano operacji";
    private static final String DIV_BY_ZERO = "Nie mozna dzielic przez zero";
    private static final String FIRST_NUMBER = "firstNumber";
    private static final String SECOND_NUMBER = "secondNumber";
    private static final String CLICK_OPERATION = "clickOperation";
    private static final String OPERATION = "operation";
    private static final String INPUT = "input";

    private TextView textView;
    private double firstNumber;
    private double secondNumber;
    private CalcOperation operation = CalcOperation.NONE;
    private boolean clickOperation;

    private StringBuilder input;

    protected void initialize(){
        textView = findViewById(R.id.textView);
        input = new StringBuilder();
        textView.setText(input);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adv);
        initialize();
        if (savedInstanceState != null) {
            firstNumber = savedInstanceState.getDouble(FIRST_NUMBER);
            secondNumber = savedInstanceState.getDouble(SECOND_NUMBER);
            clickOperation = savedInstanceState.getBoolean(CLICK_OPERATION);
            String operationString = savedInstanceState.getString(OPERATION);
            operation = CalcOperation.valueOf(operationString);
            input.append(savedInstanceState.getString(INPUT));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putDouble(FIRST_NUMBER, firstNumber);
        outState.putDouble(SECOND_NUMBER, secondNumber);
        outState.putBoolean(CLICK_OPERATION, clickOperation);
        outState.putString(OPERATION, operation.toString());
        outState.putString(INPUT, input.toString());
        super.onSaveInstanceState(outState);
    }

    public void multiplication(View view) {
        addNumbers(CalcOperation.MUL);
    }

    public void addition(View view) {
        addNumbers(CalcOperation.ADD);
    }

    public void subtraction(View view) {
        addNumbers(CalcOperation.SUB);
    }

    public void division(View view) {
        addNumbers(CalcOperation.DIV);
    }

    void addNumbers(CalcOperation operation){
        if(!clickOperation){
            if(addFirstNubmer()) {
                this.operation = operation;
                clickOperation = true;
            }
        }
        else{
            if(addSecondNubmer()) {
                this.firstNumber = returnResult();
            }
            this.operation = operation;
        }
    }

    public void equal(View view) {
        if(operation.equals(CalcOperation.NONE)){
            addToast(OPERATION_NO_SELECT);
        }else{
            if(addSecondNubmer()) {
                showResult();
                refreshInput();
            }
        }
        operation = operation.NONE;
    }

    public void backspace(View view) {
        if(input.length() > 0){
            input = input.deleteCharAt(input.length() - 1);
        }
        refreshInput();
    }

    public void refreshInput(){
        textView.setText(input);
    }

    public void clear(View view) {
        operation = CalcOperation.NONE;
        clickOperation = false;
        firstNumber = 0;
        secondNumber = 0;
        input.setLength(0);
        refreshInput();
    }

    public double returnResult(){
        double value = 0;
        if(operation.equals(CalcOperation.ADD)){
            value = firstNumber + secondNumber;
        }
        if(operation.equals(CalcOperation.SUB)){
            value = firstNumber - secondNumber;
        }
        if(operation.equals(CalcOperation.MUL)){
            value = firstNumber * secondNumber;
        }
        if(operation.equals(CalcOperation.DIV)){
            if(secondNumber != 0) {
                value = firstNumber / secondNumber;
            }
            else{
                addToast(DIV_BY_ZERO);
            }
        }
        if(operation.equals(CalcOperation.SQRT)){
            value = Math.sqrt(firstNumber);
        }
        if(operation.equals(CalcOperation.COS)){
            value = Math.cos(firstNumber);
        }
        if(operation.equals(CalcOperation.SIN)){
            value = Math.sin(firstNumber);
        }
        if(operation.equals(CalcOperation.TAN)){
            value = Math.tan(firstNumber);
        }
        if(operation.equals(CalcOperation.LOGN)){
            value = Math.log(firstNumber);
        }
        if(operation.equals(CalcOperation.LOG)){
            value = Math.log10(firstNumber);
        }
        if(operation.equals(CalcOperation.POW2)){
            value = Math.pow(firstNumber,2);
        }
        if(operation.equals(CalcOperation.POWY)){
            value = Math.pow(firstNumber, secondNumber);
        }
        return value;
    }

    public void showResult(){
        double value = returnResult();
        input.setLength(0);
        input.append(String.valueOf(value));
        firstNumber = value;
        clickOperation = false;
    }

    public void addToast(String information){
        Context context = getApplicationContext();
        CharSequence text = information;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void checkZero(){
        if(input.toString().startsWith("0") && input.length() == 1){
            input.deleteCharAt(0);
        }
    }

    public boolean addFirstNubmer(){
        try {
            firstNumber = Double.parseDouble(input.toString());
            input.setLength(0);
            refreshInput();
            return true;
        }catch(NumberFormatException ignored){
            return false;
        }
    }

    public boolean addSecondNubmer(){
        try {
            secondNumber = Double.parseDouble(input.toString());
            input.setLength(0);
            refreshInput();
            return true;
        }
        catch(NumberFormatException ignored){
            return false;
        }
    }

    public void changeSign(View view) {
        try{
            double value = Double.parseDouble(input.toString());
            if(value < 0){
                input.deleteCharAt(0);
            }
            else if(value > 0){
                input.insert(0,"-");
            }
        }
        catch(NumberFormatException ignored){
        }
        refreshInput();
    }

    public void addComa(View view) {
        if(!input.toString().contains(".")) {
            if (input.toString().equals("")) {
                input.insert(0, "0.");
            } else {
                input.insert(input.length(), ".");
            }
        }
        refreshInput();
    }

    public void addButton0(View view) {
        checkZero();
        input.append("0");
        refreshInput();
    }

    public void addButton1(View view) {
        checkZero();
        input.append("1");
        refreshInput();
    }

    public void addButton2(View view) {
        checkZero();
        input.append("2");
        refreshInput();
    }

    public void addButton3(View view) {
        checkZero();
        input.append("3");
        refreshInput();
    }

    public void addButton4(View view) {
        checkZero();
        input.append("4");
        refreshInput();
    }

    public void addButton5(View view) {
        checkZero();
        input.append("5");
        refreshInput();
    }

    public void addButton6(View view) {
        checkZero();
        input.append("6");
        refreshInput();
    }

    public void addButton7(View view) {
        checkZero();
        input.append("7");
        refreshInput();
    }

    public void addButton8(View view) {
        checkZero();
        input.append("8");
        refreshInput();
    }

    public void addButton9(View view) {
        checkZero();
        input.append("9");
        refreshInput();
    }

    //advanced

    public void logarithm(View view) {
        if(!clickOperation){
            if(addFirstNubmer()) {
                operation = CalcOperation.LOG;
                clickOperation = true;
                showResult();
                refreshInput();
            }
        }
    }

    public void sqrt(View view) {
        if(!clickOperation){
            if(addFirstNubmer()) {
                operation = CalcOperation.SQRT;
                clickOperation = true;
                showResult();
                refreshInput();
            }
        }
    }

    public void naturalLogarithm(View view) {
        if(!clickOperation){
            if(addFirstNubmer()) {
                operation = CalcOperation.LOGN;
                clickOperation = true;
                showResult();
                refreshInput();
            }
        }
    }

    public void tanges(View view) {
        if(!clickOperation){
            if(addFirstNubmer()) {
                operation = CalcOperation.TAN;
                clickOperation = true;
                showResult();
                refreshInput();
            }
        }
    }

    public void cosinus(View view) {
        if(!clickOperation){
            if(addFirstNubmer()) {
                operation = CalcOperation.COS;
                clickOperation = true;
                showResult();
                refreshInput();
            }
        }
    }

    public void sinus(View view) {
        if(!clickOperation){
            if(addFirstNubmer()) {
                operation = CalcOperation.SIN;
                clickOperation = true;
                showResult();
                refreshInput();
            }
        }
    }

    public void powerToTwo(View view) {
        if(!clickOperation){
            if(addFirstNubmer()) {
                operation = CalcOperation.POW2;
                clickOperation = true;
                showResult();
                refreshInput();
            }
        }
    }

    public void powerToY(View view) {
        if(!clickOperation){
            if(addFirstNubmer()) {
                operation = CalcOperation.POWY;
                clickOperation = true;
            }
        }
        else{
            if(addSecondNubmer()) {
                this.firstNumber = returnResult();
            }
            operation = CalcOperation.POWY;
        }
    }
}
