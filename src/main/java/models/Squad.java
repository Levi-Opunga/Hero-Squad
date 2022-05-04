package models;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

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
    public static Multimap<String, Hero> squadAllocations = ArrayListMultimap.create();

    private   List<Hero> allocatedHeroes = new ArrayList<>(); //list of all allocated heroes


    private String cause;
    private int heroesPresentCount;

    public  int getHeroesPresentCount() {
        return heroesPresentCount;
    }

    public int setHeroesPresentCount(int heroesPresentCount) {
        return this.heroesPresentCount = heroesPresentCount;
    }

    public void setHeroesPresentCount() {
        this.heroesPresentCount = this.heroesPresentCount+1;
    }

public static int deleteCount =0;



    private int percentageFull;

    public int getPercentageFull() {
        return percentageFull;
    }

    public void setPercentageFull(int percentageFull) {
        this.percentageFull = percentageFull;
    }


    public  List<Hero> getAllocatedHeroes() {
        return allocatedHeroes;
    }

    public void setAllocatedHeroes(Hero hero) {
         this.allocatedHeroes.add(hero);
    }

    public Squad(String name, String motto, String cause, Integer maxsize) {
        this.name = name;
        this.maxsize = maxsize;
        this.cause = cause;
        this.motto = motto;
        instances.add(this);
        this.id = instances.size()-1;
        this.percentageFull =0;
        this.heroesPresentCount =0;

    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //methods
    public boolean isSquadFull() {
        return squadFull;
    }
    public void setSquadFull(boolean squadFull) {
        this.squadFull = squadFull;
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

    private int createId() {
        return instances.size() - 1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public static Squad getSquadById(int idToUse) {
        return instances.get(idToUse);
    }

    public static List<Squad> getAllSquads() {
        return instances;
    }

    public static void deleteAllSquads() {
        instances.clear();
    }

    public static void deleteParicularSquad(int id) {
        instances.remove(id);
        deleteCount++;
    }

}
