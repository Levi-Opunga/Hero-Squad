package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SquadTest {
    @Test
    public void  TestsquadGetsCreated() {
        Squad squad = setUpSquad();
        assertEquals(true, squad instanceof Squad);

    }

    @Test
    void TestgetName() {
        Squad squad = setUpSquad();
        assertEquals("first", squad.getName());
    }

    @Test
    void  TestsetName() {
        Squad squad = setUpSquad();
        squad.setName("new");
        assertEquals("new", squad.getName());
    }

    @Test
    void  TestgetMaxsize() {
        Squad squad = setUpSquad();
        assertEquals(5, squad.getMaxsize());
    }

    @Test
    void  TestsetMaxsize() {
        Squad squad = setUpSquad();
        squad.setMaxsize(11);
        assertEquals(11, squad.getMaxsize());

    }

    @Test
    void  TestgetCause() {
        Squad squad = setUpSquad();
        assertEquals("corruption", squad.getCause());
    }

    @Test
    void  TestsetCause() {
        Squad squad = setUpSquad();
        squad.setCause("new");
        assertEquals("new", squad.getCause());
    }

    private Squad setUpSquad() {
        return new Squad("first", "do code",  "corruption",5);
    }
}