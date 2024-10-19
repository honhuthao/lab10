package com.example.room_database;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.room_database.adapter.AppDatabase;
import com.example.room_database.constants.Constants;
import com.example.room_database.dao.PersonDao;
import com.example.room_database.model.Person;

public class EditPersonActivity extends AppCompatActivity {
    private EditText etFirstName;
    private EditText etLastName;

    private Button btnSave;
    private int mPersonId;

    private Intent intent;
    private AppDatabase mDb;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        setContentView(R.layout.activity_person_edit);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initView();
        mDb = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "app-database").build();

        intent = getIntent();
        if(intent != null && intent.hasExtra(Constants.UPDATE_Person_Id)){
            btnSave.setText("update");
            mPersonId = intent.getIntExtra(Constants.UPDATE_Person_Id, -1);

            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    Person person = mDb.personDao().loadPersonById(mPersonId);
                    populateUI(person);
                }
            });
        }
    }
    private void populateUI(Person person){
        if(person == null){
            return;
        }

        etFirstName.setText(person.getFirstName());
        etLastName.setText(person.getLastName());
    }

    private void initView(){
        etFirstName = findViewById(R.id.first_name);
        etLastName = findViewById(R.id.last_name);

        btnSave = findViewById(R.id.button_save);
        btnSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onSaveButtonClicked();
            }
        });
    }
    public void onSaveButtonClicked(){
        final Person person = new Person(
                etFirstName.getText().toString(),
                etLastName.getText().toString());

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                if(!intent.hasExtra(Constants.UPDATE_Person_Id)){
                    mDb.personDao().insert(person);
                }else {
                    person.setUid(mPersonId);
                    mDb.personDao().update(person);
                }
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
