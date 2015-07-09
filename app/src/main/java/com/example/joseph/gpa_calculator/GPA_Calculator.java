package com.example.joseph.gpa_calculator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
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
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.joeforbroke.gpa_calculator.R;

import java.text.DecimalFormat;


public class GPA_Calculator extends Activity {

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

    private static final int SETTINGS_INFO = 1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editedlayout);

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



        //Retrieves data on startup

        if(savedInstanceState != null) {

            String[] classNames = savedInstanceState.getStringArray("CLASSNAMES");
            String[] credits = savedInstanceState.getStringArray("CREDITS");
            String prevGPA = savedInstanceState.getString("PREVGPA");
            String prevCredits = savedInstanceState.getString("PREVCREDITS");

            Credit1.setText(credits[0]);
            Credit2.setText(credits[1]);
            Credit3.setText(credits[2]);
            Credit4.setText(credits[3]);
            Credit5.setText(credits[4]);
            Credit6.setText(credits[6]);

            Class1.setText(classNames[0]);
            Class2.setText(classNames[1]);
            Class3.setText(classNames[2]);
            Class4.setText(classNames[3]);
            Class5.setText(classNames[4]);
            Class6.setText(classNames[6]);

            Previous_GPA.setText(prevGPA);
            Previous_Credits.setText(prevCredits);



        }

        String class1saved = getPreferences(Context.MODE_PRIVATE).getString("CLASS1", "EMPTY");
        if (!class1saved.equals("EMPTY")){
            Class1.setText(class1saved);
        }

        String class2saved = getPreferences(Context.MODE_PRIVATE).getString("CLASS2", "EMPTY");
        if (!class2saved.equals("EMPTY")){
            Class2.setText(class2saved);
        }

        String class3saved = getPreferences(Context.MODE_PRIVATE).getString("CLASS3", "EMPTY");
        if (!class3saved.equals("EMPTY")){
            Class3.setText(class3saved);
        }

        String class4saved = getPreferences(Context.MODE_PRIVATE).getString("CLASS4", "EMPTY");
        if (!class4saved.equals("EMPTY")){
            Class4.setText(class4saved);
        }

        String class5saved = getPreferences(Context.MODE_PRIVATE).getString("CLASS5", "EMPTY");
        if (!class5saved.equals("EMPTY")){
            Class5.setText(class5saved);
        }

        String class6saved = getPreferences(Context.MODE_PRIVATE).getString("CLASS6", "EMPTY");
        if (!class6saved.equals("EMPTY")){
            Class6.setText(class6saved);
        }

        String credit1saved = getPreferences(Context.MODE_PRIVATE).getString("CREDIT1", "EMPTY");
        if (!credit1saved.equals("EMPTY")){
            Credit1.setText(credit1saved);
        }

        String credit2saved = getPreferences(Context.MODE_PRIVATE).getString("CREDIT2", "EMPTY");
        if (!credit2saved.equals("EMPTY")){
            Credit2.setText(credit2saved);
        }

        String credit3saved = getPreferences(Context.MODE_PRIVATE).getString("CREDIT3", "EMPTY");
        if (!credit3saved.equals("EMPTY")){
            Credit3.setText(credit3saved);
        }

        String credit4saved = getPreferences(Context.MODE_PRIVATE).getString("CREDIT4", "EMPTY");
        if (!credit4saved.equals("EMPTY")){
            Credit4.setText(credit4saved);
        }

        String credit5saved = getPreferences(Context.MODE_PRIVATE).getString("CREDIT5", "EMPTY");
        if (!credit5saved.equals("EMPTY")){
            Credit5.setText(credit5saved);
        }

        String credit6saved = getPreferences(Context.MODE_PRIVATE).getString("CREDIT6", "EMPTY");
        if (!credit6saved.equals("EMPTY")){
            Credit6.setText(credit6saved);
        }

        String prevGPAsaved = getPreferences(Context.MODE_PRIVATE).getString("PREVGPA", "EMPTY");
        if (!prevGPAsaved.equals("EMPTY")){
            Previous_GPA.setText(prevGPAsaved);
        }

        String prevCreditssaved = getPreferences(Context.MODE_PRIVATE).getString("PREVCREDITS", "EMPTY");
        if (!prevCreditssaved.equals("EMPTY")){
            Previous_Credits.setText(prevCreditssaved);
        }


        ImageButton settingsBtn = (ImageButton) findViewById(R.id.Settingsbtn);

        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPreferences = new Intent(getApplicationContext(), SettingsActivity.class);

                startActivityForResult(intentPreferences, SETTINGS_INFO);
            }
        });



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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == SETTINGS_INFO){
            updateApp();
        }
    }

    private void updateApp(){

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        if(!sharedPreferences.getBoolean("plus_minus_system", true)) {
            ArrayAdapter<CharSequence> gradeOptionSpinnerAdapter2 = ArrayAdapter.createFromResource(this,
                    R.array.grade_options2, android.R.layout.simple_spinner_dropdown_item);
            gradeOptionSpinnerAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            Grade1.setAdapter(gradeOptionSpinnerAdapter2);
            Grade2.setAdapter(gradeOptionSpinnerAdapter2);
            Grade3.setAdapter(gradeOptionSpinnerAdapter2);
            Grade4.setAdapter(gradeOptionSpinnerAdapter2);
            Grade5.setAdapter(gradeOptionSpinnerAdapter2);
            Grade6.setAdapter(gradeOptionSpinnerAdapter2);
        } else {
            addItemsToGradeSpinner(Grade1);
            addItemsToGradeSpinner(Grade2);
            addItemsToGradeSpinner(Grade3);
            addItemsToGradeSpinner(Grade4);
            addItemsToGradeSpinner(Grade5);
            addItemsToGradeSpinner(Grade6);
        }




    }



    //Saves data on system close
    @Override
    protected void onSaveInstanceState(Bundle outState) {

        String[] saveClasses = new String[6];
        saveClasses[0] = Class1.getText().toString();
        saveClasses[1] = Class2.getText().toString();
        saveClasses[2] = Class3.getText().toString();
        saveClasses[3] = Class4.getText().toString();
        saveClasses[4] = Class5.getText().toString();
        saveClasses[5] = Class6.getText().toString();

        outState.putStringArray("CLASSNAMES", saveClasses);

        String[] saveCredits = new String[6];
        saveCredits[0] = Credit1.getText().toString();
        saveCredits[1] = Credit2.getText().toString();
        saveCredits[2] = Credit3.getText().toString();
        saveCredits[3] = Credit4.getText().toString();
        saveCredits[4] = Credit5.getText().toString();
        saveCredits[5] = Credit6.getText().toString();

        outState.putStringArray("CREDITS", saveCredits);

        String savePrevGPA = Previous_GPA.getText().toString();
        String savePrevCredits = Previous_Credits.getText().toString();

        outState.putString("PREVGPA", savePrevGPA);
        outState.putString("PREVCredits", savePrevCredits);


        super.onSaveInstanceState(outState);
    }

    //Saves data on user close
    private void saveSettings(){

        SharedPreferences.Editor sPEditor = getPreferences(Context.MODE_PRIVATE).edit();

        String[] saveClasses = new String[6];
        saveClasses[0] = Class1.getText().toString();
        saveClasses[1] = Class2.getText().toString();
        saveClasses[2] = Class3.getText().toString();
        saveClasses[3] = Class4.getText().toString();
        saveClasses[4] = Class5.getText().toString();
        saveClasses[5] = Class6.getText().toString();

        sPEditor.putString("CLASS1", saveClasses[0]);
        sPEditor.putString("CLASS2", saveClasses[1]);
        sPEditor.putString("CLASS3", saveClasses[2]);
        sPEditor.putString("CLASS4", saveClasses[3]);
        sPEditor.putString("CLASS5", saveClasses[4]);
        sPEditor.putString("CLASS6", saveClasses[5]);

        String[] saveCredits = new String[6];
        saveCredits[0] = Credit1.getText().toString();
        saveCredits[1] = Credit2.getText().toString();
        saveCredits[2] = Credit3.getText().toString();
        saveCredits[3] = Credit4.getText().toString();
        saveCredits[4] = Credit5.getText().toString();
        saveCredits[5] = Credit6.getText().toString();

        sPEditor.putString("CREDIT1",saveCredits[0]);
        sPEditor.putString("CREDIT2",saveCredits[1]);
        sPEditor.putString("CREDIT3",saveCredits[2]);
        sPEditor.putString("CREDIT4",saveCredits[3]);
        sPEditor.putString("CREDIT5",saveCredits[4]);
        sPEditor.putString("CREDIT6",saveCredits[5]);

        sPEditor.putString("PREVGPA", Previous_GPA.getText().toString());
        sPEditor.putString("PREVCREDITS", Previous_Credits.getText().toString());

        sPEditor.apply();
    }

    @Override
    protected void onStop() {

        saveSettings();
        super.onStop();
    }

    //adds a Listener to the EditText
    public void addListenerToET(final EditText et, final int pos) {

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
                if (et.getText().toString().equals("")) {
                    return;
                } else {
                    totalCredits[pos] = Double.parseDouble(et.getText().toString());
                }
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
                if (et.getText().toString().equals("")) {
                    return;
                } else {
                    Previous_Credits_Double = Double.parseDouble(et.getText().toString());
                }
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
                if (et.getText().toString().equals("")) {
                    return;
                } else {
                    Previous_GPA_Double = Double.parseDouble(et.getText().toString());
                }
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


    public void clearFields(View view) {
        Class1.setText("");
        Class2.setText("");
        Class3.setText("");
        Class4.setText("");
        Class5.setText("");
        Class6.setText("");

        Credit1.setText("");
        Credit2.setText("");
        Credit3.setText("");
        Credit4.setText("");
        Credit5.setText("");
        Credit6.setText("");

        Previous_Credits.setText("");
        Previous_GPA.setText("");
    }
}
