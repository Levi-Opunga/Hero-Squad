package models;

import java.util.ArrayList;
import java.util.List;

public class Hero {
    private static ArrayList<Hero> instances = new ArrayList<>();
    private final int id;

    public boolean isAllocatedSquad() {
        return allocatedSquad;
    }

    public void setAllocatedSquad(boolean allocatedSquad) {
        this.allocatedSquad = allocatedSquad;
    }

    private String name;
    private Integer age;
    private String power;
    private String weakness;

    boolean allocatedSquad =false;
    private String squad;

    public String getSquad() {
        return squad;
    }

    public void setSquad(String squad) {
        this.squad = squad;
        allocatedSquad = true;
    }

    public Hero(String name, Integer age, String power, String weakness) {
        this.name = name;
        this.age = age;
        this.power = power;
        this.weakness = weakness;
        instances.add(this);
        id = instances.size()-1;
    }
    public static List<Hero> getAll(){
        return instances;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getWeakness() {
        return weakness;
    }

    public void setWeakness(String weakness) {
        this.weakness = weakness;
    }


    public int getId(){
        return id;
    }
    public  static  void clearAllPosts(){
        instances.clear();
    }

    public static  Hero getHeroById(int idToUse){
        return instances.get(idToUse);
    }

}