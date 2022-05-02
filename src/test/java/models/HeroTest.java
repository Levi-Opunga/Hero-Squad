package models;


import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeroTest {



    @Test
    public void NewHeroObjectGetsCorrectly() throws Exception {
        Hero hero = setUpHero();
        assertEquals(true, hero instanceof Hero);
    }

    @Test
    public void getName() {
        Hero hero = setUpHero();
        assertEquals("levi", hero.getName());
    }

    @Test
    void setName() {
        Hero hero = setUpHero();
        hero.setName("joe");
        assertEquals("joe", hero.getName());
    }

    @Test
    void getAge() {
        Hero hero = setUpHero();
        assertEquals(20, hero.getAge());
    }

    @Test
    void setAge() {
        Hero.clearAllHeroes();
        Hero hero = setUpHero();
        hero.setAge(40);
        assertEquals(40, hero.getAge());
    }

    @Test
    void getPower() {
        Hero hero = setUpHero();
        assertEquals("super speed", hero.getPower());
    }

    @Test
    void setPower() {
        Hero hero = setUpHero();
        hero.setPower("power");
        assertEquals("power", hero.getPower());
    }

    @Test
    void getWeakness() {
        Hero hero = setUpHero();
        assertEquals("slippery floor", hero.getWeakness());
    }

    @Test
    void setWeakness() {
        Hero hero = setUpHero();
        hero.setWeakness("weak");
        assertEquals("weak", hero.getWeakness());
    }

    @Test
    void createIdTest() {
        Hero hero = setUpHero();
        Hero two = new Hero("Steve", 45, "turbo", "sleeping","/images/batman.png");
        Hero three = new Hero("Steve", 45, "turbo", "sleeping","/images/batman.png");
        Hero four = new Hero("Steve", 45, "turbo", "sleeping","/images/batman.png");
        assertEquals(3, four.getId());

    }

    private Hero setUpHero() {
        Hero.clearAllHeroes();
        return new Hero("levi", 20, "super speed", "slippery floor","/images/batman.png");
    }
}