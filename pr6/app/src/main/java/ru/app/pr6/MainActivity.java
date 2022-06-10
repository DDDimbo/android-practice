package ru.app.pr6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    private EditText keyEditor;
    private EditText valueEditor;
    private EditText nameEditor_DB;
    private EditText phoneEditor_DB;
    private EditText desEditor_DB;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        File externalFile = getExternalFilesDir("file.txt");
        File internalFile = getDir("file.txt", Context.MODE_APPEND);


        keyEditor = findViewById(R.id.keyText);
        valueEditor = findViewById(R.id.valueText);

        nameEditor_DB = findViewById(R.id.DB_name);
        phoneEditor_DB = findViewById(R.id.DB_phone);
        desEditor_DB = findViewById(R.id.DB_des);

        MyDBHelper helper = new MyDBHelper(this);
        database = helper.getDatabase();

        // 1. Prefs
        Button addRecord_btn = findViewById(R.id.addRecord);
        addRecord_btn.setOnClickListener(v -> {
            addRecord(keyEditor.getText().toString(), valueEditor.getText().toString());
            toEmptyAllTextFields();
        });

        Button outputRecords_btn = findViewById(R.id.showButton);
        outputRecords_btn.setOnClickListener(v -> printAll());

        // 2. Files
        Button createExternalFile_btn = findViewById(R.id.createFileExternal);
        createExternalFile_btn.setOnClickListener(v -> {
            try {
                externalFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        Button createInternalFile_btn = findViewById(R.id.createFileInternal);
        createInternalFile_btn.setOnClickListener(v -> {
            try {
                internalFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        Button deleteAllFiles_btn = findViewById(R.id.deleteAllFiles);
        deleteAllFiles_btn.setOnClickListener(v -> {
            internalFile.delete();
            externalFile.delete();
        });
        // Свободное место
        TextView freeSpace = findViewById(R.id.freeSpaceTitle);
        String format = freeSpace.getText().toString();
        freeSpace.setText( String.format(format, externalFile.getFreeSpace()) );

        // 3. SQLite
        Button add_DBrecord_btn = findViewById(R.id.DB_add_and_show);
        LinearLayout DB_root = findViewById(R.id.DB_output);

        add_DBrecord_btn.setOnClickListener(v -> {
            String name = nameEditor_DB.getText().toString();
            String phone = phoneEditor_DB.getText().toString();
            String des = desEditor_DB.getText().toString();
            if(name.isEmpty() | phone.isEmpty() | des.isEmpty()){
                Toast.makeText(this, "Поля не должны быть пустыми!", Toast.LENGTH_LONG).show();
                return;
            }
            createRecord(DB_root, name, phone, des);
            toEmptyAllTextFields();
        });

    }

    //Метод добавления записи в SharedPrefs
    public void addRecord(String key, String value){
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString(key, value);
        edit.apply();
    }

    //Вывод всех SharedPrefs
    public void printAll(){
        TextView output = findViewById(R.id.output);
        output.setText("");
        Map<String, String> all = (Map<String, String>) preferences.getAll();
        if (all.isEmpty())
            return;
        StringBuilder result = new StringBuilder();
        for(Map.Entry<String, String> entry : all.entrySet())
            result.append(entry.getKey()).append(" -> ").append(entry.getValue()).append('\n');
        output.setText(result);
    }

    //Опустошить все EditText ы
    public void toEmptyAllTextFields(){
        keyEditor.setText("");
        valueEditor.setText("");
        nameEditor_DB.setText("");
        phoneEditor_DB.setText("");
        desEditor_DB.setText("");
    }

    //Создание карточки записи с кнопкой удаление, запись карточки идет в parent
    public void createRecord(LinearLayout parent, String name, String phone, String description){
        LinearLayout record = new LinearLayout(this);
        record.setOrientation(LinearLayout.HORIZONTAL);

        TextView name1 = new TextView(this);
        name1.setPadding(20, 0, 20, 0);
        name1.setText(name);

        TextView phone1 = new TextView(this);
        phone1.setPadding(20, 0, 20, 0);
        phone1.setText(phone);

        TextView des1 = new TextView(this);
        des1.setPadding(20, 0, 20, 0);
        des1.setText(description);

        ContentValues values = new ContentValues();
        values.put(MyDBContract.MyDBEntry.NAME, name);
        values.put(MyDBContract.MyDBEntry.PHONE, phone);
        values.put(MyDBContract.MyDBEntry.DESCRIPTION, description);

        long row = database.insert(MyDBContract.MyDBEntry.TABLE_NAME, null, values);

        Button deleteRecord_btn = new Button(this);
        deleteRecord_btn.setText(" - ");
        deleteRecord_btn.setOnClickListener(v -> {
            parent.removeView(record);
            database.delete(MyDBContract.MyDBEntry.TABLE_NAME, "_id = ?", new String[]{row+""});
        });



        record.addView(name1);
        record.addView(phone1);
        record.addView(des1);
        record.addView(deleteRecord_btn);
        parent.addView(record);
    }

    public CardView createClinic(String name, String address, int num){
        CardView cardView;
        LayoutInflater from = LayoutInflater.from(this);

        cardView = (CardView) from.inflate(R.layout.activity_main, null, false);

        cardView.findViewById();

        return cardView;
    }

}