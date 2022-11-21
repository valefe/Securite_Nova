package valentina.fajardo.scuritnova;

import androidx.annotation.NonNull;
import androidx.annotation.StyleableRes;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.satyajit.thespotsdialog.SpotsDialog;

public class LoginActivity extends AppCompatActivity {
    Button iniciar;
    TextInputEditText gmail, password;
    private FirebaseAuth mAuth;
    AlertDialog alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.txt_iniciosesion);
        mAuth = FirebaseAuth.getInstance();

        iniciar = (Button) findViewById(R.id.iniciar);

        gmail = findViewById(R.id.gmail);
        password = findViewById(R.id.password);
        
        alerta = new SpotsDialog.Builder().setContext(LoginActivity.this).setMessage("por favor espere").build();
        
        login();
        limpiar();
    }

    private void limpiar() {
        gmail.setText("");
        password.setText("");
    }

    private void login() {
        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userE = gmail.getText().toString().trim();
                String passE = password.getText().toString().trim();
                if (TextUtils.isEmpty(userE)){
                    Toast.makeText(getApplicationContext(), "Ingrese un correo", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(passE)){
                    Toast.makeText(getApplicationContext(), "Ingrese una contraseña", Toast.LENGTH_SHORT).show();
                }else{
                    alerta.show();
                    mAuth.signInWithEmailAndPassword(userE, passE).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                            }else{
                                limpiar();
                                Intent i = new Intent(LoginActivity.this, IntentoActivity.class);
                                startActivity(i);
                            }
                            alerta.dismiss();
                        }
                    });
                }

            }
        });
    }

    public void Registrate (View view){
        Intent registrar= new Intent(LoginActivity.this, SigninActivity.class);
        startActivity(registrar);
    }
    public void Recuperar (View view){
        Intent recuperar= new Intent(LoginActivity.this, RecuperarActivity.class);
        startActivity(recuperar);
    }
}