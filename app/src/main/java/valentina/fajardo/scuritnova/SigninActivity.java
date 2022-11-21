package valentina.fajardo.scuritnova;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class SigninActivity extends AppCompatActivity {

    private TextInputEditText usernameRegistro;
    private TextInputEditText emailRegistro;
    private TextInputEditText passwordRegirtro;
    private Button btnRegistrar;

    private String name =  "";
    private String email =  "";
    private String password =  "";

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.txt_signin);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        usernameRegistro = (TextInputEditText) findViewById(R.id.usernameR);
        emailRegistro = (TextInputEditText) findViewById(R.id.gmailR);
        passwordRegirtro = (TextInputEditText) findViewById(R.id.passwordR);
        btnRegistrar = (Button) findViewById(R.id.registrarmeR);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = usernameRegistro.getText().toString();
                email = emailRegistro.getText().toString();
                password = passwordRegirtro.getText().toString();

                if(!name.isEmpty() && !email.isEmpty() && !password.isEmpty()){
                    if (password.length()>= 8){

                        registerUser();

                    }
                    else {
                        Toast.makeText(SigninActivity.this, "La contraseña debe tener al menos 8 caracteres", Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    Toast.makeText(SigninActivity.this, "Debe Completar todos los campos", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void registerUser() {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    Map<String, Object> map = new HashMap<>();
                    map.put( "name", name);
                    map.put( "email", email);
                    map.put( "password", password);


                    String id = mAuth.getCurrentUser().getUid();
                    mDatabase.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if (task2.isSuccessful()){
                                startActivity(new Intent(SigninActivity.this, LoginActivity.class));
                                finish();
                            }
                            else{
                                Toast.makeText(SigninActivity.this, "No se pudieron crear los datos correctamente", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }else {
                    Toast.makeText(SigninActivity.this, "No fue posible registrar este usuario", Toast.LENGTH_SHORT).show();
                }
                
            }
        });

    }

    public void Iniciar (View view){
        Intent iniciar= new Intent(SigninActivity.this, LoginActivity.class);
        startActivity(iniciar);
    }
}