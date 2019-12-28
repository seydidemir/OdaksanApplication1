package com.example.odaksanmuhendislikv1;

import androidx.annotation.NonNull;

public class TeklifID {

public  String teklifID;

public <T extends TeklifID>T withId(@NonNull final  String id){

    this.teklifID = id;
    return  (T) this;

}

}
