package javaSE.project.logic;

import javaSE.project.model.Unit;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UnitViewLogicTest {
    UnitViewLogicI unitViewLogicI;

    public UnitViewLogicTest() throws SQLException, ClassNotFoundException {
        unitViewLogicI=new UnitViewLogic();
    }

    @Test
    public void add() throws SQLException {
        Unit unit = new Unit();
        unit.setName("java");
        unit.setTime(3);
        unit.setId(101);
        if(!unitViewLogicI.add(unit))
            Assert.assertFalse(false); // confirmation that it has failed

    }

    @Test
    public void update() throws SQLException, ClassNotFoundException {
        Unit unit = unitViewLogicI.find(101);
        unit.setName("java");
        unit.setTime(3);
        unit.setId(101);
        if(!unitViewLogicI.update(unit))
            Assert.assertFalse(false); // confirmation that it has failed

    }

    @Test
    public void delete() throws SQLException {
        Unit search = new Unit();
        search.setId(101);
        if(!unitViewLogicI.delete(search));
        Assert.assertFalse(false);
    }

    @Test
    public void findAll() throws SQLException, ClassNotFoundException {
        List<Unit> find=new ArrayList<>() {};
        find=unitViewLogicI.findAll();
        if(find.isEmpty());
        Assert.assertFalse(false);
    }

    @Test
    public void find() throws SQLException, ClassNotFoundException {
        Unit Unit=new Unit();
        Unit.setId(101);
        //call find method
        Unit search=unitViewLogicI.find(Unit.getId());
        //check results
        Assert.assertEquals(search.getId(),Unit.getId());
    }
}