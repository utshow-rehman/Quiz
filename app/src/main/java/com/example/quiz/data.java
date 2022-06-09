package com.example.quiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class data extends SQLiteOpenHelper {
    public static final String QUIZ_TABLE = "QUIZ_TABLE";
    public static final String COLUMN_QUIZ_QUESTION = "QUESTION";
    public static final String CHOICES = "CHOICES";
    public static final String ANSWER = "ANSWER";
    public static final String COLUMN_QUIZ_ID="ID";
   public String[] questions=new String[100];
   public String[] correctAnswer=new String[100];
   public String[] choice=new String[100];
   public String[][] choices=new String[100][100];


    public data(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public String getQuestion(int a){
        String question = questions[a];
        return question;
    }

    public String getchoice1(int a){
        String choice = choices[a][0];
        return choice;
    }

    public String getchoice2(int a){
        String choice = choices[a][1];
        return choice;
    }

    public String getchoice3(int a){
        String choice = choices[a][2];
        return choice;
    }

    public String getchoice4(int a){
        String choice = choices[a][3];
        return choice;
    }

    public String getCorrectAnswer(int a){
        String answer = correctAnswer[a];
        return answer;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String createTableStatement = "CREATE TABLE " + QUIZ_TABLE + " (" + COLUMN_QUIZ_QUESTION+ "  VARCHAR(100),"+ COLUMN_QUIZ_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+ CHOICES + "  VARCHAR(1000) , "+ ANSWER + " VARCHAR(30))";
        sqLiteDatabase.execSQL(createTableStatement);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + QUIZ_TABLE);
        onCreate(db);
    }

     void delete(){
         SQLiteDatabase db = this.getWritableDatabase();
         db.delete(QUIZ_TABLE,null,null);
         db.close();
     }

    void add() {
        delete();
        SQLiteDatabase db = this.getWritableDatabase();

        // Creating an instance of 'ContentValues' to insert data in the Database
        ContentValues cv = new ContentValues();
        //Questions---------------------------------------------------------
        cv.put(COLUMN_QUIZ_QUESTION,"Which is a Programming Language?");
        cv.put(CHOICES,"HTML,CSS,Vala,PHP");
        cv.put(ANSWER,"PHP");
        db.insert(QUIZ_TABLE, null, cv);

        cv.put(COLUMN_QUIZ_QUESTION,"In COMAL language program, after name of procedure parameters must be in?");
        cv.put(CHOICES,"Punction Marks,Back-Slash,Brackets,Semi Colon");
        cv.put(ANSWER,"Brackets");
        db.insert(QUIZ_TABLE, null, cv);

        cv.put(COLUMN_QUIZ_QUESTION,"Programming language COBOL works best for use in?");
        cv.put(CHOICES,"Siemens Applications,Student Applications, Social Applications,Commercial Applications");
        cv.put(ANSWER,"Commercial Applications");
        db.insert(QUIZ_TABLE, null, cv);

        cv.put(COLUMN_QUIZ_QUESTION,"Programming language COBOL works best for use in?");
        cv.put(CHOICES,"Siemens Applications,Student Applications, Social Applications,Commercial Applications");
        cv.put(ANSWER,"Commercial Applications");
        db.insert(QUIZ_TABLE, null, cv);

        cv.put(COLUMN_QUIZ_QUESTION,"In OO, the concept of IS-A is based on--");
        cv.put(CHOICES,"Class inheritance,Interface implementation.,Both,None");
        cv.put(ANSWER,"Both");
        db.insert(QUIZ_TABLE, null, cv);

        cv.put(COLUMN_QUIZ_QUESTION,"Who was the first female game Developer?");
        cv.put(CHOICES,"Carol Shaw,Hannah Duncan,Naomi,Brooks");
        cv.put(ANSWER,"Carol Shaw");
        db.insert(QUIZ_TABLE, null, cv);

        cv.put(COLUMN_QUIZ_QUESTION,"ReactJS was created by?");
        cv.put(CHOICES,"Jordan Walke,Jordan Mike,Jordan Lee,Tim Lee");
        cv.put(ANSWER,"Jordan Walke");
        db.insert(QUIZ_TABLE, null, cv);
        db.close();

        String queryToGetAllUsers = "SELECT * FROM " + QUIZ_TABLE;
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.rawQuery(queryToGetAllUsers, null);
          int i=0;
        if(cursor.moveToFirst()){
            // This means the query has returned some results
            do {
                // Get the column values for each row with accurate datatypes

                questions[i]= cursor.getString(0);
                choice[i] = cursor.getString(2);
                correctAnswer[i] = cursor.getString(3);
                String[] words=choice[i].split(",");
                for(int j=0;j<4;j++)
                    choices[i][j]=words[j];

                i++;


            }while(cursor.moveToNext());
        }



    }



}
