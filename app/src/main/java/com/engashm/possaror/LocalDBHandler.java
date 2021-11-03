package com.engashm.possaror;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class LocalDBHandler extends SQLiteOpenHelper {

    //Fields
    private Context context = null;
    private SQLiteDatabase myDB = null;
    private final String TAG = "MaKaNy";
    private Cursor myCursor = null;
    private String tableName = null;
    private String[] columns;
    public final static String _ID = "_id";
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "possarorDB";


    public LocalDBHandler(@Nullable Context context, String tableName, String[]columns)
    {
        super(context, DB_NAME,null, DB_VERSION );
        this.context = context;
        this.tableName = tableName;
        this.columns = columns;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    // check if Database is ready to read and write
    private boolean isDBReady(){
        myDB = getWritableDatabase();
        if (myDB == null)
        {
            Log.d(TAG, "isDBReady: Database is null ");
            return false;
        }
        if (myDB.isOpen())
            return true;
        return false;
    }

    /**
     * create a new table in the specified database, using the required data
     * @param tableName table name
     * @param columns is the columns to be added to the tables, the order of
     *                the columns is required so that it match the datatypes
     *                and constraints
     * @param dataTypes is datatypes of the columns, must match the order of the
     *                  columns.
     * @param constraints is the constraints of the columns, must correspond to
     *                    the columns order, if there is a columns or more don`t
     *                    have constraint, the constraint is replaced by "",
     *                    empty string.
     * @return true if the table has created, false otherwise.
     */
    public boolean createTable( String tableName, String [] columns,
                                String [] dataTypes,String [] constraints  ) {
        // Database table creation
        String CREATE_NEW_TABLE = "CREATE TABLE IF NOT EXISTS " + tableName + " ( ";
        String NEW_COL = "";
        int cols = columns.length;
        int types = dataTypes.length;
        int colsConstraints = constraints.length;

        if (cols != types) return false;
        for (int i = 0; i < cols; i++) {
            NEW_COL = columns[i] + " " + dataTypes[i] + " ";
            if (colsConstraints > 0) {
                NEW_COL += constraints[i];
                colsConstraints--;
            }
            CREATE_NEW_TABLE += NEW_COL +((i <= (cols-2))? " , ":"");
            NEW_COL = "";
        }
        CREATE_NEW_TABLE  += " ) ";
        try{
            myDB.execSQL(CREATE_NEW_TABLE);
        }
        catch (SQLException ex){
            return false;
        }
        return isTableExist(tableName) ;
    }

    /**
     * test if the table is exist
     * @param tableName the name of the table
     * @return true if exist, false otherwise
     */
    public boolean isTableExist(String tableName){
        boolean isExist = false;
        try{
            myDB.rawQuery("SELECT * FROM "+tableName, null);
            isExist = true;
        }
        catch (Exception e)
        {
            Toast.makeText(context.getApplicationContext(), "table with this name is not exist",
                    Toast.LENGTH_SHORT).show();
            return  false ;
        }
        return isExist;}

    /**
     * insert new data to the database
     * @param values the values of data to be inserted in the database
     * @return true if data inserted, false otherwise
     */
    public boolean insertData(ContentValues values){

        myDB = getWritableDatabase();
        long result =  myDB.insert(tableName, null, values);
        return result != -1;
    }

    /**
     * update data for specified row with the id
     * @param values updating values to be inserted
     * @param id of the row on which data updated
     * @return true if data is updated, false otherwise
     */
    public boolean updateColumn(ContentValues values, long id){
        String whereClause = _ID + " = ? ";
        String [] whereArgs = new String[]{id+""};
        myDB = getWritableDatabase();
        long updatedRows = myDB.update(tableName, values, whereClause, whereArgs);
        return updatedRows > 0;
    }

    /**
     * get the data (cursor of data) from the DB with the specified id(only on row)
     * @param id of the row
     * @returnthe cursor of data with this row id
     */
    public Cursor  getCursor( long id ){
        myDB = getReadableDatabase();

        if (!isDBReady()){
            Log.d(TAG, "database table did not created");
            return null;
        }
        myCursor =  myDB.rawQuery("SELECT * FROM "+ tableName + " WHERE " +_ID
                +  " = ?",new String[]{id+""});
        return myCursor;
    }

    /*
     * delete a row of data with the specified id
     * @param id of the row
     * @return true if row deleted, false otherwise
     */
    public boolean deleteLocation(long id){
        String whereClause = _ID + " = ? ";
        String [] whereArgs = new String[]{id+""};
        myDB = this.getWritableDatabase();
        int result =   myDB.delete(tableName, whereClause, whereArgs);
        return result > 0;
    }

    public void setDBConsts(Object DBClass){

    }

}
