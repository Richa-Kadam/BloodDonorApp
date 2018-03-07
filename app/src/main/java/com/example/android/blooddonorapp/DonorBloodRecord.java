package com.example.android.blooddonorapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by user on 28-12-2017.
 */

public class DonorBloodRecord  extends Fragment {
    TextView t;
    Database_Handler_Class handlerClass;
    int ap,an,abp,abn,op,one,bp,bn;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_blood_record, container, false);
        handlerClass=new Database_Handler_Class(getActivity());
        handlerClass.getAllDonorRecords();
        int count =handlerClass.count;
        t= (TextView) v.findViewById(R.id.tv);
        ap=handlerClass.getRecordOfAP();
        an=handlerClass.getRecordOfAN();
        abp=handlerClass.getRecordOfABP();
        abn=handlerClass.getRecordOfABN();
        op=handlerClass.getRecordOfOP();
        one=handlerClass.getRecordOfON();
        bp=handlerClass.getRecordOfBP();
        bn=handlerClass.getRecordOfBN();
        t.append("\n A+   is "+ap+"\n A-    is "+an+"\n B+   is "+bp+"\n B-    is "+bn+"\n AB+ is "+abp+"\n AB-  is "+abn+"\n O+   is "+op+"\n O-    is "+one+" \n Total Donors:: "+ count);
        return v;

    }

}
