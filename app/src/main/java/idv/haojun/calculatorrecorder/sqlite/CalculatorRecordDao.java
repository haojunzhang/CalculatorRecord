package idv.haojun.calculatorrecorder.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import idv.haojun.calculatorrecorder.helper.SQLiteHelper;
import idv.haojun.calculatorrecorder.model.CalculatorRecord;

public class CalculatorRecordDao {

    // table name
    public static final String TABLE_NAME = "calculator_record";
    // pk
    private static final String ID = "id";
    // other column
    private static final String VALUE1 = "value1";
    private static final String VALUE2 = "value2";
    private static final String OPERATE_TYPE = "operateType";
    private static final String RESULT = "result";
    

    public static String createTable() {
        StringBuilder sb = new StringBuilder();
        sb.append("Create Table " + TABLE_NAME + " ( ");
        sb.append(ID + " TEXT PRIMARY KEY , ");
        sb.append(VALUE1 + " TEXT NOT NULL , ");
        sb.append(VALUE2 + " TEXT NOT NULL , ");
        sb.append(OPERATE_TYPE + " INTEGER NOT NULL , ");
        sb.append(RESULT + " TEXT NOT NULL) ");
        
        return sb.toString();
    }

    private SQLiteDatabase db;

    public CalculatorRecordDao(Context context) {
        db = SQLiteHelper.getDatabase(context);
    }

    public boolean insert(CalculatorRecord item) {

        ContentValues cv = new ContentValues();

        cv.put(VALUE1, item.getValue1());
        cv.put(VALUE2, item.getValue2());
        cv.put(OPERATE_TYPE, item.getOperateType());
        cv.put(RESULT, item.getResult());

        return db.insert(TABLE_NAME, null, cv) > 0;
    }

    public boolean update(CalculatorRecord item) {

        ContentValues cv = new ContentValues();

        cv.put(VALUE1, item.getValue1());
        cv.put(VALUE2, item.getValue2());
        cv.put(OPERATE_TYPE, item.getOperateType());
        cv.put(RESULT, item.getResult());

        String where = ID + "=" + item.getId();

        return db.update(TABLE_NAME, cv, where, null) > 0;
    }


    public boolean delete(int id) {
        String where = ID + "=" + id;

        return db.delete(TABLE_NAME, where, null) > 0;
    }

    public List<CalculatorRecord> getAll() {
        List<CalculatorRecord> result = new ArrayList<>();
        Cursor cursor = db.query(
                TABLE_NAME, null, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            result.add(getRecord(cursor));
        }

        cursor.close();
        return result;
    }


    public CalculatorRecord get(int id) {

        CalculatorRecord item = null;

        String where = ID + "=" + id;

        Cursor result = db.query(
                TABLE_NAME, null, where, null, null, null, null, null);


        if (result.moveToFirst()) {

            item = getRecord(result);
        }


        result.close();

        return item;
    }

    public CalculatorRecord getRecord(Cursor cursor) {

        CalculatorRecord item = new CalculatorRecord();
        item.setId(cursor.getLong(0));
        item.setValue1(cursor.getString(1));
        item.setValue2(cursor.getString(2));
        item.setOperateType(cursor.getInt(3));
        item.setResult(cursor.getString(4));

        return item;
    }


    public int getCount() {
        int result = 0;
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME, null);
        if (cursor.moveToNext()) {
            result = cursor.getInt(0);
        }
        cursor.close();
        return result;
    }
}