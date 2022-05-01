package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SquadTest {
    @Test
    public void squadGetsCreated(){
    Squad squad=  setUpSquad();
    assertEquals(true,squad instanceof Squad);

    }

    @Test
    void getName() {
        Squad squad=  setUpSquad();
        assertEquals("first",squad.getName());
    }

    @Test
    void setName() {  Squad squad=  setUpSquad();
        squad.setName("new");
        assertEquals("new",squad.getName());
    }

    @Test
    void getMaxsize() {  Squad squad=  setUpSquad();
        assertEquals(5,squad.getMaxsize());
    }

    @Test
    void setMaxsize() {  Squad squad=  setUpSquad();
        squad.setMaxsize(11);
        assertEquals(11,squad.getMaxsize());

    }

    @Test
    void getCause() {  Squad squad=  setUpSquad();
        assertEquals("corruption",squad.getCause());
    }

    @Test
    void setCause() {  Squad squad=  setUpSquad();
        squad.setCause("new");
        assertEquals("new",squad.getCause());
    }

    private Squad setUpSquad(){
        return new Squad("first",5,"corruption");
    }
}