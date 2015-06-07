package com.example.joseph.gpa_calculator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;


public class GPA_Calculator extends ActionBarActivity {

    //Setting arrays to hold the credit and point awarded values
    double[] totalCredits = new double[6];
    double[] grades = new double[6];
    double Previous_Credits_Double = 0.0;
    double Previous_GPA_Double = 0.0;
    double SemesterCredits = 0.0;
    double SemesterPoints = 0.0;
    double CurGPA = 0.0;
    double OverallGPA_Double = 0.0;

    //Instantiating all objects being used in the app

    Button CalculateBtn;

    EditText Class1, Class2, Class3, Class4, Class5, Class6;
    EditText Credit1, Credit2, Credit3, Credit4, Credit5, Credit6;

    Spinner Grade1, Grade2, Grade3, Grade4, Grade5, Grade6;

    TextView SemesterGPA, OverallGPA;

    EditText Previous_GPA, Previous_Credits;
    private EditText et;
    private int pos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpa__calculator);

        //Setting each array to 99.99
        //zerOut();

        //Initializing all of the objects
        CalculateBtn = (Button) findViewById(R.id.CalculateButton);

        Class1 = (EditText) findViewById(R.id.ClassET1);
        Class2 = (EditText) findViewById(R.id.ClassET2);
        Class3 = (EditText) findViewById(R.id.ClassET3);
        Class4 = (EditText) findViewById(R.id.ClassET4);
        Class5 = (EditText) findViewById(R.id.ClassET5);
        Class6 = (EditText) findViewById(R.id.ClassET6);

        Credit1 = (EditText) findViewById(R.id.CreditET1);
        Credit2 = (EditText) findViewById(R.id.CreditET2);
        Credit3 = (EditText) findViewById(R.id.CreditET3);
        Credit4 = (EditText) findViewById(R.id.CreditET4);
        Credit5 = (EditText) findViewById(R.id.CreditET5);
        Credit6 = (EditText) findViewById(R.id.CreditET6);


        Grade1 = (Spinner) findViewById(R.id.GradeSpinner1);
        Grade2 = (Spinner) findViewById(R.id.GradeSpinner2);
        Grade3 = (Spinner) findViewById(R.id.GradeSpinner3);
        Grade4 = (Spinner) findViewById(R.id.GradeSpinner4);
        Grade5 = (Spinner) findViewById(R.id.GradeSpinner5);
        Grade6 = (Spinner) findViewById(R.id.GradeSpinner6);

        SemesterGPA = (TextView) findViewById(R.id.SemesterGPATV);
        OverallGPA = (TextView) findViewById(R.id.OverallGPATV);

        Previous_Credits = (EditText) findViewById(R.id.editText2);
        Previous_GPA = (EditText) findViewById(R.id.Current_GPA);

        //add listener to all ETs
        addListenerToET(Credit1, 0);
        addListenerToET(Credit2, 1);
        addListenerToET(Credit3, 2);
        addListenerToET(Credit4, 3);
        addListenerToET(Credit5, 4);
        addListenerToET(Credit6, 5);

        //adding items to all the spinners
        addItemsToGradeSpinner(Grade1);
        addItemsToGradeSpinner(Grade2);
        addItemsToGradeSpinner(Grade3);
        addItemsToGradeSpinner(Grade4);
        addItemsToGradeSpinner(Grade5);
        addItemsToGradeSpinner(Grade6);

        //adding listener to all spinners
        addListenerToGradeSpinner(Grade1, 0);
        addListenerToGradeSpinner(Grade2, 1);
        addListenerToGradeSpinner(Grade3, 2);
        addListenerToGradeSpinner(Grade4, 3);
        addListenerToGradeSpinner(Grade5, 4);
        addListenerToGradeSpinner(Grade6, 5);

        //add listener to the final credit and current GPA ETs
        addListenerToPreviousCredits(Previous_Credits);
        addListenerToPreviousGPA(Previous_GPA);

        //set what happens on click of the calculate button
        addOnClick(CalculateBtn);

    }

    //Sets array values to zero
    //public void zerOut() {
    //    for (int x =0; x < 6; x++){
    //         totalCredits[x] = 0.0;
    //    }
    //    for (int x =0; x < 6; x++) {
    //        grades[x] = 0.0;
    //   }
    //}



    //adds a Listener to the EditText
    public void addListenerToET(final EditText et, final int pos) {
        this.et = et;
        this.pos = pos;
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            //After text changed set the new value in the specified pos in the array
            @Override
            public void afterTextChanged(Editable s) {
                totalCredits[pos] = Double.parseDouble(et.getText().toString());
            }
        });

    }


    public void addItemsToGradeSpinner(Spinner gradeSel) {
        //Creating a spinner adapter that inserts the grade items and the layout as a dropdown
        ArrayAdapter<CharSequence> gradeOptionSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.grade_options, android.R.layout.simple_spinner_dropdown_item);

        //Sets dropdown
        gradeOptionSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //sets the created adapter to all the spinners passed in this method
        gradeSel.setAdapter(gradeOptionSpinnerAdapter);
    }

    //Listener on spinner
    public void addListenerToGradeSpinner(Spinner gradeSel, final int pos) {
        gradeSel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //Get the item selected in the spinner and assigns proper value in array
                String itemSelectedInSpinner = parent.getItemAtPosition(position).toString();
                switch (itemSelectedInSpinner) {
                    case "A+":
                        grades[pos] = 4.3;
                        break;
                    case "A":
                        grades[pos] = 4.0;
                        break;
                    case "A-":
                        grades[pos] = 3.7;
                        break;
                    case "B+":
                        grades[pos] = 3.3;
                        break;
                    case "B":
                        grades[pos] = 3.0;
                        break;
                    case "B-":
                        grades[pos] = 2.7;
                        break;
                    case "C+":
                        grades[pos] = 2.3;
                        break;
                    case "C":
                        grades[pos] = 2.0;
                        break;
                    case "C-":
                        grades[pos] = 1.7;
                        break;
                    case "D+":
                        grades[pos] = 1.3;
                        break;
                    case "D":
                        grades[pos] = 1.0;
                        break;
                    case "D-":
                        grades[pos] = 0.7;
                        break;
                    case "F":
                        grades[pos] = 0.0;

                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
    }

    public void addListenerToPreviousCredits(final EditText et) {
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Previous_Credits_Double = Double.parseDouble(et.getText().toString());

            }
        });
    }

    public void addListenerToPreviousGPA(final EditText et) {
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Previous_GPA_Double = Double.parseDouble(et.getText().toString());

            }
        });
    }

    public void addOnClick(Button btn) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int x = 0; x < 6; x++) {
                    if (totalCredits[x] != 0.0) {
                        SemesterCredits += (totalCredits[x]);
                        SemesterPoints += ((totalCredits[x] * grades[x]));
                    }
                    CurGPA = SemesterPoints / SemesterCredits;

                    DecimalFormat df = new DecimalFormat("#.00");
                    SemesterGPA.setText(String.valueOf(df.format(CurGPA)));

                    OverallGPA_Double = ((CurGPA * SemesterCredits) + (Previous_GPA_Double
                            * Previous_Credits_Double)) / (SemesterCredits + Previous_Credits_Double);


                    OverallGPA.setText(String.valueOf(df.format(OverallGPA_Double)));
                }
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gpa__calculator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
