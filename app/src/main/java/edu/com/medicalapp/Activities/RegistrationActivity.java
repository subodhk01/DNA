package edu.com.medicalapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.com.medicalapp.R;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener{


    @BindView(R.id.edit_name)
    EditText editName;

    @BindView(R.id.edit_username)
    EditText editUsername;

    @BindView(R.id.edit_emailId)
    EditText editEmailId;

    @BindView(R.id.edit_Passwword)
    EditText editPassword;

    @BindView(R.id.btn_signUp)
    Button btnSignUp;

    @BindView(R.id.text_login)
    TextView textLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);
        btnSignUp.setOnClickListener(this);
        textLogin.setOnClickListener(this);


        if (getSupportActionBar() != null) {

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }



    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.btn_signUp:
               validation();
               break;

            case R.id.text_login:
                startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
                break;
        }

    }

    private void validation() {


        boolean check=true;
        String Name=editName.getText().toString().trim();
        String Username=editUsername.getText().toString().trim();
        String Email=editEmailId.getText().toString().trim();
        String Password=editPassword.getText().toString().trim();


        if(Name.isEmpty())
        {
            editName.setError(getString(R.string.empty_field));
            check=false;
        }

        if(Username.isEmpty())
        {
            editUsername.setError(getString(R.string.empty_field));
            check=false;
        }

        if(Email.isEmpty())
        {
            editEmailId.setError(getString(R.string.empty_field));
            check=false;
        }

        if(Password.isEmpty())
        {
            editPassword.setError(getString(R.string.empty_field));
            check=false;
        }
        if(Password.length()<6)
        {
            editPassword.setError(getString(R.string.too_short));
            check=false;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches())
        {
            editEmailId.setError(getString(R.string.invalid_email));
            check=false;
        }

        if(check==true)
        {
            Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show();
            //startActivity(new Intent());
        }
        else
        {
            Toast.makeText(this,getString(R.string.fill_detail), Toast.LENGTH_SHORT).show();
        }





    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();
        if(id==R.id.home)
        {
            finish();

        }
        return super.onOptionsItemSelected(item);
    }


}
