package com.android.game2048;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;
import java.util.List;


public class ScoreDao {
    Context context;
    ScoreDBHelper dbHelper;

    public ScoreDao(Context context) {
        this.context = context;
        dbHelper = new ScoreDBHelper(context, "note_type.db", null, 1);
    }

    public void insertNote(ScoreBean bean) {

        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("score", bean.getScore());
        cv.put("createTime", bean.getCreateTime());
        sqLiteDatabase.insert("note_type", null, cv);
    }

    public int DeleteNote(int id) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        int ret = 0;
        ret = sqLiteDatabase.delete("note_type", "type_id=?", new String[]{id + ""});
        return ret;
    }


    public List<ScoreBean> queryTypesAll() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        List<ScoreBean> noteList = new ArrayList<>();
        ScoreBean note;
        Cursor cursor = null;
        String sql = "select * from note_type order by score desc";

        cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            note = new ScoreBean();
            note.setScore(cursor.getInt(cursor.getColumnIndex("score")));
            note.setCreateTime(cursor.getString(cursor.getColumnIndex("createTime")));
            noteList.add(note);
        }

        if (cursor != null) {
            cursor.close();
        }
        if (db != null) {
            db.close();
        }

        return noteList;
    }


}
