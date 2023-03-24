package DAO;

import Model.Center;

import java.util.ArrayList;
import java.util.List;

public class CenterDatabase {
    //Adding main center obj
    ArrayList<Center> centerList = new ArrayList<>();

    public List<Center> getCenterList() {
        return centerList;
    }

    public void setCenterList(ArrayList<Center> centerList) {
        this.centerList = centerList;
    }
}
