package com.social.view;


import java.util.List;

public abstract class DataManager {

    public abstract List<User> getData();

    public static DataReader1 getDataManager(){
        return new DataReader1();
    }

}


