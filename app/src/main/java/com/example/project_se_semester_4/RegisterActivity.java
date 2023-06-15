package com.example.project_se_semester_4;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    EditText firstName, lastName, userName, email, password, confirmPassword;
    Button signInBtn, registerBtn;
    DBHelper DB;

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +  "$");             //at least 4 characters

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firstName = findViewById(R.id.fnameRegisterPage);
        lastName = findViewById(R.id.lnameRegisterPage);
        userName = findViewById(R.id.usernameRegisterPage);
        email = findViewById(R.id.emailRegisterPage);
        password = findViewById(R.id.passwordRegisterPage);
        confirmPassword = findViewById(R.id.confirmPasswordRegisterPage);
        signInBtn = findViewById(R.id.signInButtonRegisterPage);
        registerBtn = findViewById(R.id.registerButtonRegisterPage);
        DB = new DBHelper(this);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = userName.getText().toString();
                String name1 = firstName.getText().toString();
                String name2 = lastName.getText().toString();
                String e_mail = email.getText().toString();
                String pass = password.getText().toString();
                String confirmPass = confirmPassword.getText().toString();

                if(user.equals("")||pass.equals("")||confirmPass.equals("")) {
                    Toast.makeText(RegisterActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                } else if (pass.equals(confirmPass)&& PASSWORD_PATTERN.matcher(pass).matches()) {
                    Boolean checkUser = DB.checkUsername(user);
                    if (checkUser == false){
                        Boolean insert = DB.insertData(user, name1, name2, e_mail, pass);
                        if (insert == true){
                            Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(intent);
                        } else{
                            Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "User Already Exists", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "Password doesn't match or doesn't meet the requirements", Toast.LENGTH_SHORT).show();
                }

            }
        });

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);

            }
        });
    }

}
