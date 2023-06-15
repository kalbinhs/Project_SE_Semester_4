package com.example.project_se_semester_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button signInBtn;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.usernameLoginPage);
        password = findViewById(R.id.passwordLoginPage);
        signInBtn = findViewById(R.id.signInButtonLoginPage);
        DB = new DBHelper(this);

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("")||pass.equals("")){
                    Toast.makeText(LoginActivity.this, "Please Enter All Fields", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkUserPass = DB.checkInsert(user, pass);
                    if (checkUserPass == true){
                        Toast.makeText(LoginActivity.this, "Sign In Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        return;
    }
}
