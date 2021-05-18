package com.example.m1_ssii.mejrihamza_gestiondestock;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.m1_ssii.mejrihamza_gestiondestock.DataBase.UserDataBaseHelper;
import com.example.m1_ssii.mejrihamza_gestiondestock.Model.User;

import java.util.Set;

public class Settings extends AppCompatActivity{

    private String nom__,prenom__,email__,password__;
    private int id__,tel__;
    private Boolean checked = false;
    private Intent redirection;
    private AlertDialog.Builder alert;
    private EditText name,prenom,tel,email,password;
    private Button btnRetour,btnEnregistrer;
    private CheckBox rememberPassword;
    private ImageButton deleteAccount;
    private SharedPreferences pref;
    public static final String MY_PREFERENCES = "user_details";
    private UserDataBaseHelper db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Paramètres du compte");
        setContentView(R.layout.activity_settings);

        alert = new AlertDialog.Builder(Settings.this);
        pref  = getSharedPreferences(MY_PREFERENCES,MODE_PRIVATE);

        name             = (EditText) findViewById(R.id.name_);
        prenom           = (EditText) findViewById(R.id.prenom_);
        tel              = (EditText) findViewById(R.id.tel_);
        email            = (EditText) findViewById(R.id.email_);
        password         = (EditText) findViewById(R.id.password_);
        rememberPassword = (CheckBox) findViewById(R.id.rememberpassword);
        btnEnregistrer   = (Button)   findViewById(R.id.btnEnregistrer);
        btnRetour        = (Button)   findViewById(R.id.btnRetour);
        deleteAccount    = (ImageButton)   findViewById(R.id.imgButtonDelete);


        id__        = pref.getInt("id",0);
        nom__       = pref.getString("nom","nom");
        prenom__    = pref.getString("prenom","prenom");
        tel__       = pref.getInt("tel",0);
        email__     = pref.getString("email","email");
        password__  = pref.getString("password","password");

        name.setText(nom__);
        prenom.setText(prenom__);
        tel.setText(String.valueOf(tel__));
        email.setText(email__);
        password.setText(password__);

        //Status of remember password CheckBox
        if (pref.getBoolean("checked",false))
            rememberPassword.setChecked(true);

        //User for data base update access
        final User u = new User(id__,nom__,prenom__,tel__,email__,password__);

        //On click name input
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText editText = new EditText(Settings.this);
                editText.setText(name.getText());
                alert.setView(editText);
                alert.setTitle("Modification du nom");
                alert.setPositiveButton("Modifier", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            if(editText.getText().toString().trim().equals("") ||
                                    editText.getText().toString().trim().equals(nom__))
                                Toast.makeText(Settings.this,"Modification non prise en charge",Toast.LENGTH_SHORT).show();
                            else {
                                name.setText(editText.getText());
                                u.setNom(editText.getText().toString());
                                alert.setView(null);
                            }
                    }
                });
                alert.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alert.show();
            }
        });

        //On click prenom input
        prenom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText editText = new EditText(Settings.this);
                editText.setText(prenom.getText());
                alert.setView(editText);
                alert.setTitle("Modification du prénom");
                alert.setPositiveButton("Modifier", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(editText.getText().toString().trim().equals("") ||
                                editText.getText().toString().trim().equals(prenom__))
                            Toast.makeText(Settings.this,"Modification non prise en charge",Toast.LENGTH_SHORT).show();
                        else {
                            prenom.setText(editText.getText());
                            u.setPrenom(editText.getText().toString());
                            alert.setView(null);
                        }
                    }
                });
                alert.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alert.show();
            }
        });

        //On click tel input
        tel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText editText = new EditText(Settings.this);
                editText.setText(String.valueOf(tel.getText()));
                alert.setView(editText);
                alert.setTitle("Modification du num tél");
                alert.setPositiveButton("Modifier", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(editText.getText().toString().trim().equals("") ||
                                Integer.parseInt(editText.getText().toString().trim())==(tel__))
                            Toast.makeText(Settings.this,"Modification non prise en charge",Toast.LENGTH_SHORT).show();
                        else {
                            tel.setText(editText.getText());
                            u.setTel(Integer.parseInt(editText.getText().toString()));
                            alert.setView(null);
                        }
                    }
                });
                alert.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alert.show();
            }
        });

        //On click email input
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText editText = new EditText(Settings.this);
                editText.setText(email.getText());
                alert.setView(editText);
                alert.setTitle("Modification email");
                alert.setPositiveButton("Modifier", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(editText.getText().toString().trim().equals("") ||
                                editText.getText().toString().trim().equals(email__))
                            Toast.makeText(Settings.this,"Modification non prise en charge",Toast.LENGTH_SHORT).show();
                        else {
                            email.setText(editText.getText());
                            u.setEmail(editText.getText().toString());
                            alert.setView(null);
                        }
                    }
                });
                alert.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alert.show();
            }
        });

        //On click password input
        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater dialogLayoutInflater = LayoutInflater.from(Settings.this);
                final View finalDialogView = dialogLayoutInflater.inflate(R.layout.password_edit_dialog,null);
                alert.setTitle("Modification du mot de passe");
                alert.setView(finalDialogView);
                        final EditText oldPassword_        = (EditText) finalDialogView.findViewById(R.id.oldpassword);
                        final EditText newpassword_        = (EditText) finalDialogView.findViewById(R.id.newpassword);
                        final EditText confirmnewpassword_ = (EditText) finalDialogView.findViewById(R.id.newpasswordconfirm);
                        final CheckBox showPassword        = (CheckBox) finalDialogView.findViewById(R.id.show);
                 //Show password
                showPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if ( buttonView.isChecked()){
                            oldPassword_.setInputType(InputType.TYPE_CLASS_TEXT);
                            newpassword_.setInputType(InputType.TYPE_CLASS_TEXT);
                            confirmnewpassword_.setInputType(InputType.TYPE_CLASS_TEXT);
                        }else{
                            oldPassword_.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD);
                            newpassword_.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                            confirmnewpassword_.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD);
                        }
                    }
                });
                alert.setPositiveButton("Modifier", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, int which) {
                        //check conform inputs
                        if(u.getPassword().equals(oldPassword_.getText().toString().trim()) &&
                            !newpassword_.getText().toString().trim().equals("") &&
                            newpassword_.getText().toString().trim().equals(
                                    confirmnewpassword_.getText().toString().trim())
                            ){
                            password.setText(newpassword_.getText());
                            u.setPassword(newpassword_.getText().toString());
                            alert.setView(null);
                        }else
                            Toast.makeText(Settings.this,"Modification non prise en charge",Toast.LENGTH_SHORT).show();

                    }
                });
                alert.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alert.show();
            }
        });

        //On check remember password box
        rememberPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if ( buttonView.isChecked())
                    checked = true;
                else
                    checked = false;
            }
        });

        //On click back button
        btnRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirection = new Intent(Settings.this,Home.class);
                startActivity(redirection);
                finish();
            }
        });

        //On click save button
        btnEnregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.setTitle("Attention")
                        .setMessage("Voulez vous vraiment enregistrer les modifications")
                        .setPositiveButton("Enregistrer", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //User update in data base
                                db = new UserDataBaseHelper(Settings.this);
                                db.open();
                                if(db.update(u)>0){
                                    //Preferences update
                                    SharedPreferences.Editor prefEditor = pref.edit();
                                    prefEditor.putInt("id", u.getId());
                                    prefEditor.putString("nom", u.getNom());
                                    prefEditor.putString("prenom", u.getPrenom());
                                    prefEditor.putInt("tel",u.getTel());
                                    prefEditor.putString("email", u.getEmail());
                                    prefEditor.putString("password", u.getPassword());
                                    prefEditor.commit();

                                    if (checked){
                                        //To remember password : remember password box is checked
                                        prefEditor.putBoolean("checked",true);
                                        prefEditor.commit();
                                    }else{
                                        //To remember password : remember password box is checked
                                        prefEditor.putBoolean("checked",false);
                                        prefEditor.commit();
                                    }
                                    Toast.makeText(Settings.this,"Modification eregistrée",Toast.LENGTH_SHORT).show();

                                }else {
                                    Toast.makeText(Settings.this,"Problème de modification",Toast.LENGTH_SHORT).show();
                                }

                            }
                        }).setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                }).show();
            }
        });

        //On click delete user account icone
        deleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater dialogLayouInflater = LayoutInflater.from(Settings.this);
                final View finalDialogView         = dialogLayouInflater.inflate(R.layout.delete_account_dialog,null);
                alert.setView(finalDialogView);
                        final Button btnSupprimer = (Button) finalDialogView.findViewById(R.id.btnDelete);
                        final Button btnAnnuler   = (Button) finalDialogView.findViewById(R.id.btnCancel);
                alert.setTitle("Attention !! dernière chance !");
                final AlertDialog show = alert.show();
                btnAnnuler.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        show.dismiss();
                    }
                });
                btnSupprimer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Delete user
                        db = new UserDataBaseHelper(Settings.this);
                        db.open();
                        if(db.delete(u)>0){
                            SharedPreferences.Editor prefEditor = pref.edit();
                            prefEditor.clear();
                            prefEditor.commit();
                            Toast.makeText(Settings.this,"Votre compte est supprimé",Toast.LENGTH_SHORT).show();
                            redirection = new Intent(Settings.this,MainActivity.class);
                            startActivity(redirection);
                            finish();
                        }else
                            Toast.makeText(Settings.this,"Problème de suppression du compte",Toast.LENGTH_SHORT).show();
                        show.dismiss();
                    }
                });

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar_settings,menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                redirection = new Intent(this,Home.class);
                startActivity(redirection);
                finishAffinity();
                return true;
            case R.id.productmanagement:
                redirection = new Intent(this,ProductManagement.class);
                startActivity(redirection);
                finishAffinity();
                return true;
            case R.id.apropos:
                redirection = new Intent(this,About.class);
                startActivity(redirection);
                finishAffinity();
                return true;
            case R.id.logout:
                pref  = getSharedPreferences(MY_PREFERENCES,MODE_PRIVATE);
                SharedPreferences.Editor prefEditor = pref.edit();
                prefEditor.clear();
                prefEditor.commit();
                redirection = new Intent(Settings.this,MainActivity.class);
                startActivity(redirection);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
