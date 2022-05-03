package models;

import java.util.ArrayList;
import java.util.List;

public class Hero {
    private static ArrayList<Hero> instances = new ArrayList<>();
    private final int id;

   private String bio;
    private String name;

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    private Integer age;
    private String power;
    private String weakness;

    boolean allocatedSquad = false;
    private String squad;

    public static int heroSubtractCounter =0;

    public String getSquad() {
        return squad;
    }

    public void setSquad(String squad) {
        this.squad = squad;
        allocatedSquad = true;
    }

    public Hero(String name, Integer age, String power, String weakness,String icon,String bio) {
        this.name = name;
        this.age = age;
        this.power = power;
        this.weakness = weakness;
        this.icon = icon;
        instances.add(this);
        id = instances.size() - 1;
        this.bio = bio;
    }

    public static List<Hero> getAll() {
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


    public int getId() {
        return id;
    }

    public static void clearAllHeroes() {
        instances.clear();
    }

    public static Hero getHeroById(int idToUse) {
        return instances.get(idToUse);
    }

    public static void deleteParicularHero(int id) {
        instances.remove(id);
        heroSubtractCounter++;
    }
    public boolean isAllocatedSquad() {
        return allocatedSquad;
    }


    public void setAllocatedSquad(boolean allocatedSquad) {
        this.allocatedSquad = allocatedSquad;
    }
    String icon;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public static String[] icons = {"superhero (12).png"
            , "superhero (10).png"
            , "superhero (9).png"
            , "superhero (8).png"
            , "superhero (7).png"
            , "superhero (11).png"
            , "superhero (6).png"
            , "superhero (5).png"
            , "superhero (4).png"
            , "superhero (3).png"
            , "superhero (2).png"
            , "superhero (1).png"
            , "bat (1).png"
            , "batman (1).png"
            , "spiderman.png"
            , "batman.png"
            , "superman.png"
            , "superhero.png",
            "bat.png"};


}