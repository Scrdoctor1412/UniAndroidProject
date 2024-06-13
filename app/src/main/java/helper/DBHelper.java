package helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "truyenvui.db";
    private static final int DB_VERSION = 4;

    private static final String TABLE_USERS = "users";
    private static final String TABLE_TRUYEN = "truyens";
    private static final String TABLE_PUBLISHER = "nxbs";
    private static final String TABLE_AUTHOR = "tacgias";       //table tac gia

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreateUsers = "create table if not exists users(mauser varchar(10) primary key, username text, password text, level int)";
        String sqlCreateTruyen = "create table if not exists truyens(matruyen varchar(10) primary key, tentruyen text, anhbia int, noidung text, theloai text, matacgia varchar(10), manxb varchar(10), " +
                "foreign key (matacgia) references tacgias(matacgia)" +
                "foreign key (manxb) references nxbs(manxb)" +
                ")";
        String sqlCreateAuthor = "create table if not exists tacgias(matacgia varchar(10) primary key, tentacgia text, gioithieu text, imgdaidien int)";
        String sqlCreatePublisher = "create table if not exists publisher(manxb varchar(10) primary key, tennxb text, gioithieu text, imgdaidien int)";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
