package phdhtl.k63cntt1.nguyen.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import phdhtl.k63cntt1.nguyen.R;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "truyenvui.db";
    private static final int DB_VERSION = 8;

    private static final String TABLE_USERS = "users";
    private static final String TABLE_STORY = "stories";
    private static final String TABLE_PUBLISHER = "publishers";
    private static final String TABLE_AUTHOR = "authors";       //table tac gia
    private static final String TABLE_CHAPTER = "chapters";
    private static final String TABLE_CHAPTER_IMAGE = "chapterimage";
    private static final String TABLE_TYPE = "types";
    private static final String TABLE_TYPE_STORY = "typestory";
    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreateUsers = "create table if not exists users(mauser varchar(10) primary key, username text, password text, level integer)";
        String sqlCreateTruyen = "create table if not exists stories(matruyen varchar(10) primary key, tentruyen text, anhbia text, noidung text, tennxb text, luotlike integer, luotxem integer, matacgia varchar(10), manxb varchar(10), " +
                "foreign key (matacgia) references tacgias(matacgia)," +
                "foreign key (manxb) references nxbs(manxb)" +
                ")";
        String sqlCreateAuthor = "create table if not exists authors(matacgia varchar(10) primary key, tentacgia text, gioithieu text, imgdaidien text)";
        String sqlCreatePublisher = "create table if not exists publisher(manxb varchar(10) primary key, tennxb text, gioithieu text, imgdaidien text)";
        String sqlCreateType = "create table if not exists types(matl varchar(10) primarykey, tentl text, noidungtl text)";
        String sqlCreateTypeStory = "create table if not exists typestory(matl varchar(10), matruyen varchar(10)," +
                "foreign key (matl) references types(matl)," +
                "foreign key (matruyen) references stories(matruyen)," +
                ")";
        String sqlCreateChapter = "create table if not exists chapters(machuong varchar(10) primary key, chuongso integer, chuongten text, matruyen varchar(10)," +
                "foreign key (matruyen) references stories(matruyen)" +
                ")";
        String sqlCreateChapterImage = "create table if not exists chapterimage(mahinhanhchuong varchar(10), hinhanh text, machuong varchar(10)" +
                "foreign key (machuong) references chapter(machuong)" +
                ")";

        db.execSQL(sqlCreatePublisher);
        db.execSQL(sqlCreateAuthor);

//        ImageView testImg;
//        testImg.setImageResource(R.drawable.author);
//        Bitmap bm = ((BitmapDrawable)testImg.getDrawable()).getBitmap();
//        String bmText = ConvertHelper.BitMapToString(bm);
//        String sqlInsertNXB = "insert into publisher values('nxb01', 'nxbtesting', 'day la nxb testing', '"+bmText+"')";
//        db.execSQL(sqlInsertNXB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //xoa bang cu
        db.execSQL("drop table if exists" + TABLE_AUTHOR);
        db.execSQL("drop table if exists" + TABLE_CHAPTER);
        db.execSQL("drop table if exists" + TABLE_STORY);
        db.execSQL("drop table if exists" + TABLE_TYPE);
        db.execSQL("drop table if exists" + TABLE_TYPE_STORY);
        db.execSQL("drop table if exists" + TABLE_CHAPTER_IMAGE);
        db.execSQL("drop table if exists" + TABLE_USERS);
        db.execSQL("drop table if exists" + TABLE_PUBLISHER);

        onCreate(db);
    }
}
