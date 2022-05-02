package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Squad {
    private int id;
    private static List<Squad> instances = new ArrayList<>();

    private String name;
    private String motto;
    private Integer maxsize;


boolean squadFull = false;

    public boolean isSquadFull() {
        return squadFull;
    }

    public void setSquadFull(boolean squadFull) {
        this.squadFull = squadFull;
    }

    private  String cause;



    public Squad(String name,String motto, String cause,Integer maxsize) {
        this.name = name;
        this.maxsize = maxsize;
        this.cause = cause;
        this.motto = motto;
        instances.add(this);
        this.id = instances.size();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMaxsize() {
        return maxsize;
    }

    public void setMaxsize(Integer maxsize) {
        this.maxsize = maxsize;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
    private int createId(){
       return instances.size()-1;
    }
    public int getId(){
        return id;
    }
    public  void setId(int id){
        this.id = id;
    }
    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }
   public static  Squad getSquadById(int idToUse){
return instances.get(idToUse-1);
   }
    public static List<Squad> getAllSquads(){
        return instances;
    }


}
