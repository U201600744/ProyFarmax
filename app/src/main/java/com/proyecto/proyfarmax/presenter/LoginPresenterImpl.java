package com.proyecto.proyfarmax.presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.proyecto.proyfarmax.interfaces.LoginPresenter;
import com.proyecto.proyfarmax.interfaces.LoginView;
import com.proyecto.proyfarmax.view.MainActivity;
import com.proyecto.proyfarmax.view.RecuperarPassword;
import com.proyecto.proyfarmax.view.RegistrarUsuario;

public class LoginPresenterImpl implements LoginPresenter {
    private Context context;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private String TAG = "LoginPresenter";

    public LoginPresenterImpl(Context context, FirebaseAuth firebaseAuth, DatabaseReference databaseReference) {
        this.context = context;
        this.firebaseAuth = firebaseAuth;
        this.databaseReference = databaseReference;
    }

    @Override
    public void onDestroy() {

    }

    public void signInUser(String email, String password){
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setMessage("Ingresando...");
        dialog.setCancelable(false);
        dialog.show();
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:exitoso");
                            databaseReference.child("Usuarios").setValue(task.getResult().getUser().getUid());
                            Intent intent = new Intent(context, MainActivity.class);
                            context.startActivity(intent);
                           // FirebaseUser user = mAuth.getCurrentUser();
                            dialog.dismiss();

                        } else {
                            dialog.dismiss();
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(context, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    public void enviarRecuperarPassword(){
        Intent intent = new Intent(context, RecuperarPassword.class);
        context.startActivity(intent);
    }
    public void enviarNuevoUsuario(){
        Intent intent = new Intent(context, RegistrarUsuario.class);
        context.startActivity(intent);
    }
}
