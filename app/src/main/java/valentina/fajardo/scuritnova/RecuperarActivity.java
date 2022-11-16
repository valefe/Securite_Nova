package valentina.fajardo.scuritnova;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.airbnb.lottie.L;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class RecuperarActivity extends AppCompatActivity {
    private TextInputEditText gmail;
    private Button recuperar;
    private ProgressDialog progress;
    private String correo;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.txt_recuperar);

        gmail =findViewById(R.id.gmailregistrado);
        recuperar = findViewById(R.id.recuperarbtn);

        auth = FirebaseAuth.getInstance();

        progress = new ProgressDialog(this);
        getRecuperar();
    }

    private void getRecuperar() {
        recuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correo = gmail.getText().toString().trim();
                if (!correo.isEmpty()){
                    progress.setMessage("Espere un momento...");
                    progress.setCanceledOnTouchOutside(false);
                    progress.show();
                    getEnviarCorreo();
                }else {
                    Toast.makeText(getApplicationContext(), "No fue posible enviar el correo", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void getEnviarCorreo(){
        auth.setLanguageCode("es");
        auth.sendPasswordResetEmail(correo).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Por favor revise su correo para restaurar la contraseña", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(RecuperarActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "No fue posible enviar el correo", Toast.LENGTH_SHORT).show();
                }

            }

        });

    }
}