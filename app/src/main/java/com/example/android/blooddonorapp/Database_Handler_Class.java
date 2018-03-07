package com.example.android.blooddonorapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;

/**
 * Created by user on 17-12-2017.
 */

public class Database_Handler_Class {
    private static final String DATABASE_NAME = "Blood_Donation.db";
    private static final String TABLE_NAME = "Donor";
    private static final int DATABASE_VERSION = 4;
    private static final String COLUMN_ID = "_ID";
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_GENDER = "Gender";
    private static final String COLUMN_AGE = "Age";
    private static final String COLUMN_STATE = "State";
    private static final String COLUMN_CITY = "City";
    private static final String COLUMN_BLOODGROUP = "Bloodgroup";
    private static final String COLUMN_CONTACT = "ContactNo";
    private static final String COLUMN_EMAIL = "EmailAddress";
    private static final String COLUMN_PINCODE = "PinCode";
    private static final String COLUMN_USERNAME = "Username";
    private static final String COLUMN_PASSWORD = "Password";
    private static final String COLUMN_RECENTDONATION = "RecentDonationDate";
    private static final String COLUMN_DONATIONCOUNT = "DonationCount";

    private static final String TABLE_CREATION_QUERY_Donor = "create table if not exists Donor" +
            "(_ID INTEGER PRIMARY KEY AUTOINCREMENT,Name text,Gender text,Age INTEGER," +
            "State text,City text,Bloodgroup text,ContactNo text,EmailAddress text,PinCode text" +
            ",Username text,Password text,RecentDonationDate Text,DonationCount Text);";

    private static final String TABLE_NAME1 = "Campaign";
    private static final String COL_CID = "_CID";
    private static final String COL_CAMPNAME = "CampName";
    private static final String COL_VENUE = "Venue";
    private static final String COL_EMAILID = "EmailId";
    private static final String COL_CONTACT = "Contact";
    private static final String COL_STRENGTH = "Strength";
    private static final String COL_START = "start";
    private static final String COL_END = "end";
    private static final String COL_DESC = "description";
    private static final String COL_STATE = "state1";

    private static final String TABLE_CREATION_QUERY_ADMIN = "create table if not exists Campaign" +
            "(_CID INTEGER PRIMARY KEY AUTOINCREMENT,CampName text,Venue text,EmailId text," +
            "Contact text,Strength text,start text,end text,description text,state1 text);";
    public String[][] d_data;
    int k = 0;

    public String[][] c_data;
    int m = 0;


    public String[][] b;
    int j = 0;
    String cid, cname, venue, cContact, cEmail, start, end, cState, desc, strength;
    public String[][] a;
    int apgroup, amgroup, bpgroup, bmgroup, abpgroup, abmgroup, opgropu, omgroup;
    int count, columncount;
    String id, name, age, City, Contact, Email, recentDonation, Gender, State, BloodGroup, Pincode, Username, Password, bloodgrup,doncnt;
    int i = 0;
    private DBHandler dbhandler;
    private SQLiteDatabase sqLiteDatabase;

    public Database_Handler_Class(Context context) {
        dbhandler = new DBHandler(context);
        sqLiteDatabase = dbhandler.getWritableDatabase();
    }

    public void getAllRecords(String State, String Bloodgroup) {
        Cursor cursor = sqLiteDatabase.query("Donor", null, "Bloodgroup" + "=?" + " and State=?", new String[]{Bloodgroup, State}, null, null, null);
        a = new String[cursor.getCount()][cursor.getColumnCount()];
        count = cursor.getCount();
        cursor.moveToFirst();
        i = 0;
        if (cursor.moveToFirst()) {
            do {
                name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                age = cursor.getString(cursor.getColumnIndex(COLUMN_AGE));
                City = cursor.getString(cursor.getColumnIndex(COLUMN_CITY));
                Contact = cursor.getString(cursor.getColumnIndex(COLUMN_CONTACT));
                Email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
                recentDonation = cursor.getString(cursor.getColumnIndex(COLUMN_RECENTDONATION));
                State=cursor.getString(cursor.getColumnIndex(COLUMN_STATE));
                count = cursor.getCount();
                columncount = cursor.getColumnCount();
                a[i][0] = name;
                a[i][1] = age;
                a[i][2] = City;
                a[i][3] = Contact;
                a[i][4] = Email;
                a[i][5] = recentDonation;
                a[i][6] = State;
                i = i + 1;
            } while (cursor.moveToNext());
        }
        cursor.close();

    }

    public void getAllCampaignRecord(String state) {
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME1, null, COL_STATE + "=?", new String[]{state}, null, null, null);
        c_data = new String[cursor.getCount()][cursor.getColumnCount()];
        count = cursor.getCount();
        cursor.moveToFirst();
        m = 0;
        if (cursor.moveToFirst()) {
            do {
                cname = cursor.getString(cursor.getColumnIndex(COL_CAMPNAME));
                venue = cursor.getString(cursor.getColumnIndex(COL_VENUE));
                cState = cursor.getString(cursor.getColumnIndex(COL_STATE));
                start = cursor.getString(cursor.getColumnIndex(COL_START));
                end = cursor.getString(cursor.getColumnIndex(COL_END));
                cEmail = cursor.getString(cursor.getColumnIndex(COL_EMAILID));
                strength = cursor.getString(cursor.getColumnIndex(COL_STRENGTH));
                desc = cursor.getString(cursor.getColumnIndex(COL_DESC));
                cContact = cursor.getString(cursor.getColumnIndex(COL_CONTACT));
                cid = cursor.getString(cursor.getColumnIndex(COL_CID));

                count = cursor.getCount();
                columncount = cursor.getColumnCount();

                c_data[m][0] = cname;
                c_data[m][1] = venue;
                c_data[m][2] = cState;
                c_data[m][3] = start;
                c_data[m][4] = end;
                c_data[m][5] = cEmail;
                c_data[m][6] = strength;
                c_data[m][7] = desc;
                c_data[m][8] = cContact;
                c_data[m][9] = cid;
                m = m + 1;
            } while (cursor.moveToNext());
        }
        cursor.close();
    }


    public long getAllDonorRecords() {
        Cursor cursor = sqLiteDatabase.query("Donor", null, null, null, null, null, null);
        d_data = new String[cursor.getCount()][cursor.getColumnCount()];
        count = cursor.getCount();
        cursor.moveToFirst();
        k = 0;
        if (cursor.moveToFirst()) {
            do {
                name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                age = cursor.getString(cursor.getColumnIndex(COLUMN_AGE));
                City = cursor.getString(cursor.getColumnIndex(COLUMN_CITY));
                Contact = cursor.getString(cursor.getColumnIndex(COLUMN_CONTACT));
                Email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
                recentDonation = cursor.getString(cursor.getColumnIndex(COLUMN_RECENTDONATION));
                bloodgrup = cursor.getString(cursor.getColumnIndex(COLUMN_BLOODGROUP));
                State = cursor.getString(cursor.getColumnIndex(COLUMN_STATE));
                doncnt=cursor.getString(cursor.getColumnIndex(COLUMN_DONATIONCOUNT));
                count = cursor.getCount();
                columncount = cursor.getColumnCount();
                d_data[k][0] = name;
                d_data[k][1] = age;
                d_data[k][2] = City;
                d_data[k][3] = Contact;
                d_data[k][4] = Email;
                d_data[k][5] = recentDonation;
                d_data[k][6] = bloodgrup;
                d_data[k][7] = State;
                d_data[k][8] = doncnt;
                k = k + 1;
            } while (cursor.moveToNext());
        }
        cursor.close();
        return 0;
    }

    public void getAllCampaignRecords() {
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME1, null, null, null, null, null, null);
        b = new String[cursor.getCount()][cursor.getColumnCount()];
        count = cursor.getCount();
        cursor.moveToFirst();
        j = 0;
        if (cursor.moveToFirst()) {
            do {
                cname = cursor.getString(cursor.getColumnIndex(COL_CAMPNAME));
                venue = cursor.getString(cursor.getColumnIndex(COL_VENUE));
                cState = cursor.getString(cursor.getColumnIndex(COL_STATE));
                start = cursor.getString(cursor.getColumnIndex(COL_START));
                end = cursor.getString(cursor.getColumnIndex(COL_END));
                cEmail = cursor.getString(cursor.getColumnIndex(COL_EMAILID));
                strength = cursor.getString(cursor.getColumnIndex(COL_STRENGTH));
                desc = cursor.getString(cursor.getColumnIndex(COL_DESC));
                cContact = cursor.getString(cursor.getColumnIndex(COL_CONTACT));
                cid = cursor.getString(cursor.getColumnIndex(COL_CID));

                count = cursor.getCount();
                columncount = cursor.getColumnCount();

                b[j][0] = cname;
                b[j][1] = venue;
                b[j][2] = cState;
                b[j][3] = start;
                b[j][4] = end;
                b[j][5] = cEmail;
                b[j][6] = strength;
                b[j][7] = desc;
                b[j][8] = cContact;
                b[j][9] = cid;
                j = j + 1;
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    public int getRecordOfAP() {
        Cursor cursor = sqLiteDatabase.query("Donor", null, "Bloodgroup" + "=?", new String[]{"A+"}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                apgroup = cursor.getCount();
            } while (cursor.moveToNext());
        }
        cursor.close();
        return apgroup;
    }

    public int getRecordOfAN() {
        Cursor cursor = sqLiteDatabase.query("Donor", null, "Bloodgroup" + "=?", new String[]{"A-"}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                amgroup = cursor.getCount();
            } while (cursor.moveToNext());
        }
        cursor.close();
        return amgroup;
    }

    public int getRecordOfABP() {
        Cursor cursor = sqLiteDatabase.query("Donor", null, "Bloodgroup" + "=?", new String[]{"AB+"}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                abpgroup = cursor.getCount();
            } while (cursor.moveToNext());
        }
        cursor.close();
        return abpgroup;
    }

    public int getRecordOfABN() {
        Cursor cursor = sqLiteDatabase.query("Donor", null, "Bloodgroup" + "=?", new String[]{"AB-"}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                abmgroup = cursor.getCount();
            } while (cursor.moveToNext());
        }
        cursor.close();
        return abmgroup;
    }

    public int getRecordOfOP() {
        Cursor cursor = sqLiteDatabase.query("Donor", null, "Bloodgroup" + "=?", new String[]{"O+"}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                opgropu = cursor.getCount();
            } while (cursor.moveToNext());
        }
        cursor.close();
        return opgropu;
    }

    public int getRecordOfON() {
        Cursor cursor = sqLiteDatabase.query("Donor", null, "Bloodgroup" + "=?", new String[]{"O-"}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                omgroup = cursor.getCount();
            } while (cursor.moveToNext());
        }
        cursor.close();
        return omgroup;
    }

    public int getRecordOfBP() {
        Cursor cursor = sqLiteDatabase.query("Donor", null, "Bloodgroup" + "=?", new String[]{"B+"}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                bpgroup = cursor.getCount();
            } while (cursor.moveToNext());
        }
        cursor.close();
        return bpgroup;
    }

    public int getRecordOfBN() {
        Cursor cursor = sqLiteDatabase.query("Donor", null, "Bloodgroup" + "=?", new String[]{"B-"}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                bmgroup = cursor.getCount();
            } while (cursor.moveToNext());
        }
        cursor.close();
        return bmgroup;
    }


    public void insertRecord_Donor(Contact contact) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, contact.getFirst());
        values.put(COLUMN_AGE, contact.getAge());
        values.put(COLUMN_GENDER, contact.getGender());
        values.put(COLUMN_STATE, contact.getState());
        values.put(COLUMN_CITY, contact.getCity());
        values.put(COLUMN_BLOODGROUP, contact.getBloodGroup());
        values.put(COLUMN_CONTACT, contact.getContact());
        values.put(COLUMN_EMAIL, contact.getEmail());
        values.put(COLUMN_PINCODE, contact.getPinCode());
        values.put(COLUMN_RECENTDONATION, contact.getRecentDate());
        values.put(COLUMN_USERNAME, contact.getUsername());
        values.put(COLUMN_PASSWORD, contact.getPassword());
        values.put(COLUMN_DONATIONCOUNT,"0");
        sqLiteDatabase.insert(TABLE_NAME, null, values);
        sqLiteDatabase.close();

    }

    public long insertRecord_Camp(Contacts contact) {
        ContentValues values = new ContentValues();
        values.put(COL_CAMPNAME, contact.getCname());
        values.put(COL_STATE, contact.getCstate());
        values.put(COL_VENUE, contact.getVenue());
        values.put(COL_EMAILID, contact.getEmail_id());
        values.put(COL_CONTACT, contact.getC_contact());
        values.put(COL_STRENGTH, contact.getPeople());
        values.put(COL_START, contact.getStart());
        values.put(COL_END, contact.getEnd());
        values.put(COL_DESC, contact.getDescription());
        sqLiteDatabase.insert(TABLE_NAME1, null, values);
        sqLiteDatabase.close();
        return 0;
    }

    void getLoginRecords(String Username1, String password) {
        Cursor cursor = sqLiteDatabase.query("Donor", null, "Username" + "=?" + " and Password=?", new String[]{Username1, password}, null, null, null);
        count = cursor.getCount();
        cursor.moveToFirst();
        if (cursor.moveToFirst()) {
            do {
                int nameColumnIndex = cursor.getColumnIndex(COLUMN_NAME);
                int ageColumnIndex = cursor.getColumnIndex(COLUMN_AGE);
                int CityColumnIndex = cursor.getColumnIndex(COLUMN_CITY);
                int contactnoIndex = cursor.getColumnIndex(COLUMN_CONTACT);
                int EmailIndex = cursor.getColumnIndex(COLUMN_EMAIL);
                int recentdonationIndex = cursor.getColumnIndex(COLUMN_RECENTDONATION);
                int GenderIndex = cursor.getColumnIndex(COLUMN_GENDER);
                int StateIndex = cursor.getColumnIndex(COLUMN_STATE);
                int BloodGroupIndex = cursor.getColumnIndex(COLUMN_BLOODGROUP);
                int PincodeIndex = cursor.getColumnIndex(COLUMN_PINCODE);
                int UsernameIndex = cursor.getColumnIndex(COLUMN_USERNAME);
                int PasswordIndex = cursor.getColumnIndex(COLUMN_PASSWORD);
                int idIndex = cursor.getColumnIndex(COLUMN_ID);
                int dncnt=cursor.getColumnIndex(COLUMN_DONATIONCOUNT);

                name = cursor.getString(nameColumnIndex);
                age = cursor.getString(ageColumnIndex);
                City = cursor.getString(CityColumnIndex);
                Contact = cursor.getString(contactnoIndex);
                Email = cursor.getString(EmailIndex);
                recentDonation = cursor.getString(recentdonationIndex);
                Gender = cursor.getString(GenderIndex);
                State = cursor.getString(StateIndex);
                BloodGroup = cursor.getString(BloodGroupIndex);
                Pincode = cursor.getString(PincodeIndex);
                Username = cursor.getString(UsernameIndex);
                Password = cursor.getString(PasswordIndex);
                id = cursor.getString(idIndex);
                doncnt=cursor.getString(dncnt);


            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    public void updateRecords(Contact contact) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, contact.getId());
        values.put(COLUMN_NAME, contact.getFirst());
        values.put(COLUMN_AGE, contact.getAge());
        values.put(COLUMN_STATE, contact.getState());
        values.put(COLUMN_CITY, contact.getCity());
        values.put(COLUMN_CONTACT, contact.getContact());
        values.put(COLUMN_EMAIL, contact.getEmail());
        values.put(COLUMN_PINCODE, contact.getPinCode());
        values.put(COLUMN_RECENTDONATION, contact.getRecentDate());
        values.put(COLUMN_USERNAME, contact.getUsername());
        values.put(COLUMN_PASSWORD, contact.getPassword());
        values.put(COLUMN_DONATIONCOUNT,contact.getDonorDonationCount());

        String[] args = new String[]{contact.getId()};
        sqLiteDatabase.update("Donor", values, "_ID=?", args);
    }



    public void deleterecords(String id) {
        String[] args = new String[]{id};
        sqLiteDatabase.delete("Donor", "_ID=?", args);
    }

    public void deleteCamp(String id) {
        String[] args = new String[]{id};
        sqLiteDatabase.delete(TABLE_NAME1, "_CID=?", args);
    }


    public Database_Handler_Class open() throws SQLException {
        sqLiteDatabase = dbhandler.getWritableDatabase();
        return this;
    }

    private class DBHandler extends SQLiteOpenHelper {
        public DBHandler(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(TABLE_CREATION_QUERY_Donor);
            sqLiteDatabase.execSQL(TABLE_CREATION_QUERY_ADMIN);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
            onCreate(sqLiteDatabase);
        }
    }
}
