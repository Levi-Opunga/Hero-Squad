package models;

import java.util.ArrayList;
import java.util.List;

public class Squad {
    private final int id;
    private static List<Squad> instances = new ArrayList<>();
    private static List<Hero> heroesInSquad = new ArrayList<>();
    private String name;
    private String motto;
    private Integer maxsize;
    private  String cause;



    public Squad(String name,String motto, String cause,Integer maxsize) {
        this.name = name;
        this.maxsize = maxsize;
        this.cause = cause;
        instances.add(this);
        this.id = createId();
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
    private int getId(){
        return id;
    }

    private List<Squad> getAllSquads(){
        return instances;
    }
    private List<Hero> getAllHeroesInSquad(){
        return heroesInSquad;
    }
}
