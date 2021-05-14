package com.example.m1_ssii.mejrihamza_gestiondestock.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.m1_ssii.mejrihamza_gestiondestock.Model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDataBaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="UserManager";
    private static final String TABLE_NAME="User";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NOM = "nom";
    private static final String COLUMN_PRENOM = "prenom";
    private static final String COLUMN_TEL = "tel";
    private static final String COLUMN_EMAIL= "email";
    private static final String COLUMN_PASSWORD= "password";

    private SQLiteDatabase mdb;

    public UserDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+"( "+COLUMN_ID+" INTEGER PRIMARY KEY autoincrement,"
                                                  +COLUMN_NOM+" TEXT,"
                                                  +COLUMN_PRENOM+" TEXT,"
                                                  +COLUMN_TEL+" INTEGER,"
                                                  +COLUMN_EMAIL+" TEXT,"
                                                  +COLUMN_PASSWORD+" TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public SQLiteDatabase open(){
        mdb = this.getWritableDatabase();
        return mdb;
    }

    public void close(){
        mdb.close();
    }

    public long add(User u){
        ContentValues conteneur = new ContentValues();
        conteneur.put(COLUMN_NOM,u.getNom());
        conteneur.put(COLUMN_PRENOM,u.getPrenom());
        conteneur.put(COLUMN_TEL,u.getTel());
        conteneur.put(COLUMN_EMAIL,u.getEmail());
        conteneur.put(COLUMN_PASSWORD,u.getPassword());
        return mdb.insert(TABLE_NAME,null,conteneur);
    }

    public User getUser(String log, String password){
        User u = new User();
        mdb= this.getReadableDatabase();
        Cursor res = mdb.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+COLUMN_EMAIL+"=\'"+log+"\' AND "+COLUMN_PASSWORD+"=\'"+password+"\' LIMIT 1",null);
        if(res.moveToNext()){
            u.setId(Integer.parseInt(res.getString(0)));
            u.setNom(res.getString(1));
            u.setPrenom(res.getString(2));
            u.setTel(Integer.parseInt(res.getString(3)));
            u.setEmail(res.getString(4));
            u.setPassword(res.getString(5));
        }
        return u;

    }

    public List<User> getAll(){
        List<User> lst_U = new ArrayList<User>();
        mdb=this.getReadableDatabase();
        Cursor res = mdb.rawQuery("SELECT * FROM "+TABLE_NAME,null);

        while(res.moveToNext()){
            User u = new User();
            u.setId(Integer.parseInt(res.getString(0)));
            u.setNom(res.getString(1));
            u.setPrenom(res.getString(2));
            u.setTel(Integer.parseInt(res.getString(3)));
            u.setEmail(res.getString(4));
            u.setPassword(res.getString(5));
            lst_U.add(u);
        }
        res.close();
        return lst_U ;
    }

    public int update (User u ){
        mdb = this.getWritableDatabase();
        ContentValues conteneur = new ContentValues();
        conteneur.put(COLUMN_NOM,u.getNom());
        conteneur.put(COLUMN_PRENOM,u.getPrenom());
        conteneur.put(COLUMN_TEL,u.getTel());
        conteneur.put(COLUMN_EMAIL,u.getEmail());
        conteneur.put(COLUMN_PASSWORD,u.getPassword());

        return mdb.update(TABLE_NAME,conteneur,COLUMN_ID+"=?",new String[]{String.valueOf(u.getId())});
    }

    public void delete (User u){
        mdb = this.getWritableDatabase();
        mdb.delete(TABLE_NAME,COLUMN_ID+"=?",new String[]{String.valueOf(u.getId())});
        mdb.close();
    }
}
