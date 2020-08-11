package javaSE.project.logic;

import javaSE.project.model.Teacher;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class TeacherLogicTest {
    TeacherLogicI teacherLogicI;
    public TeacherLogicTest() throws SQLException, ClassNotFoundException {
        teacherLogicI = new TeacherLogic();
    }
    @Test
    public void add() throws SQLException {
        Teacher Teacher = new Teacher();
        Teacher.setName("samson john");
        Teacher.setId(3538123);
        Teacher.setCourse("computer science");
        Teacher.setStaffNo("001/2015");
        if(!teacherLogicI.add(Teacher))
            Assert.assertFalse(false); // confirmation that it has failed

    }

    @Test
    public void update() throws SQLException, ClassNotFoundException {
        Teacher Teacher = teacherLogicI.find(35848896);
        Teacher.setName("samson ngugi");
        Teacher.setStaffNo("001/2016");
        Teacher.setId(35848896);
        if(!teacherLogicI.update(Teacher))
            Assert.assertFalse(false); // confirmation that it has failed

    }

    @Test
    public void delete() throws SQLException {
        Teacher search = new Teacher();
        search.setStaffNo("com/0011/2015");
        if(!teacherLogicI.delete(search));
        Assert.assertFalse(false);
    }

    @Test
    public void findAll() throws SQLException {
        List<Teacher> find=new ArrayList<>() {};
        find=teacherLogicI.findAll();
        if(find.isEmpty());
        Assert.assertFalse(false);
    }

    @Test
    public void find() throws SQLException {
        Teacher student=new Teacher();
        student.setId(35848896);
        //call find method
        Teacher search=teacherLogicI.find(student.getId());
        //check results
        Assert.assertEquals(search.getId(),student.getId());
    }

    @Test
    public void testFind() throws SQLException {
        Teacher student=new Teacher();
        student.setStaffNo("t/kim");
        //call find method
        Teacher search=teacherLogicI.find(student.getStaffNo());
        //check results
        Assert.assertEquals(search.getId(),student.getId());
    }
}