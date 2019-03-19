package com.example.sheetstoroomdatabase;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class DataRepository {

    private MyDao myDao;
    private LiveData<List<Sheet1>> listOfData;
    private List<Sheet1> list;

    DataRepository(Application application){
        Database database =Database.getINSTANCE(application);
        myDao = database.getDao();
        listOfData = myDao.getAllData();
        list = myDao.getAll();
    }

    public List<Sheet1> getList() {
        return list;
    }

    public LiveData<List<Sheet1>> getListOfData() {
        return listOfData;
    }

    public void insertData(Sheet1 sheet1){
         new InsertTask(myDao).execute(sheet1);
    }
    private static class InsertTask extends AsyncTask<Sheet1,Void,Void>{

        MyDao myDao;
        public InsertTask(MyDao myDao) {
            this.myDao = myDao;
        }

        @Override
        protected Void doInBackground(Sheet1... sheet1s) {
            myDao.insertData(sheet1s[0]);
            return null;
        }
    }
}
