package javaSE.project.logic;




import javaSE.project.db.DbConnection;
import javaSE.project.db.DbConnectionI;
import javaSE.project.model.Teacher;
import javaSE.project.model.Unit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UnitViewLogic implements UnitViewLogicI {
    private DbConnectionI dbConnectionI;

    public UnitViewLogic() throws SQLException, ClassNotFoundException {
//        System.out.println("[" + this.getClass().getSimpleName() + "] getting connection...");
        this.dbConnectionI = new DbConnection();
    }

    @Override
    public boolean add(Unit Unit) throws SQLException {
        PreparedStatement preparedStatement = this
                .dbConnectionI
                .getConnection()
                .prepareStatement("INSERT INTO Unit(id, name, time teacherstaffno) VALUES(?, ?, ?, ?)");
        preparedStatement.setInt(1, Unit.getId());
        preparedStatement.setString(2, Unit.getName());
        preparedStatement.setDouble(3, Unit.getTime());
        preparedStatement.setString(4, Unit.getTeacher().getStaffNo());
        return dbConnectionI.execute(preparedStatement);
    }

    @Override
    public boolean update(Unit Unit) throws SQLException {
        PreparedStatement preparedStatement = this
                .dbConnectionI
                .getConnection()
                .prepareStatement("UPDATE Unit SET id = ?, time = ?,name=?, teacherstaffno = ? WHERE id = ?");
        preparedStatement.setInt(1, Unit.getId());
        preparedStatement.setDouble(2, Unit.getTime());
        preparedStatement.setString(3, Unit.getName());
        preparedStatement.setString(4, Unit.getTeacher().getStaffNo());
        preparedStatement.setLong(5, Unit.getId());
        return dbConnectionI.execute(preparedStatement);
    }


    @Override
    public boolean delete(Unit Unit) throws SQLException {
        PreparedStatement preparedStatement = this
                .dbConnectionI
                .getConnection()
                .prepareStatement("DELETE FROM Unit WHERE id = ?");
        preparedStatement.setLong(1, Unit.getId());
        return dbConnectionI.execute(preparedStatement);
    }

    @Override
    public List<Unit> findAll() throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = this
                .dbConnectionI
                .getConnection()
                .prepareStatement("SELECT * FROM Unit");
        ResultSet resultSet = dbConnectionI.executeQuery(preparedStatement);
        List<Unit> Units = new ArrayList<>();
        TeacherLogicI teacherLogicI = new TeacherLogic();
        while (resultSet.next()) {
            Unit Unit = new Unit();
            Unit.setId(resultSet.getInt("id"));
            Unit.setName(resultSet.getString("name"));
            Unit.setTime(resultSet.getDouble("time"));
            Teacher teacher = teacherLogicI.find(resultSet.getString("teacherstaffno"));
            Unit.setTeacher(teacher);
            Units.add(Unit);
        }

        //empty resource
       // ((TeacherLogic) teacherLogicI).close();
        return Units;
    }

    @Override
    public Unit find(int id) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = this
                .dbConnectionI
                .getConnection()
                .prepareStatement("SELECT * FROM students WHERE id = ?");
        preparedStatement.setLong(1, id);
        ResultSet resultSet = dbConnectionI.executeQuery(preparedStatement);
        if (resultSet.next()) {
            Unit Unit = new Unit();
            Unit.setId(resultSet.getInt("id"));
            Unit.setName(resultSet.getString("name"));
            Unit.setTime(resultSet.getDouble("time"));
            TeacherLogicI teacherLogicI = new TeacherLogic();
            Teacher teacher = teacherLogicI.find(resultSet.getString("teacherstaffno"));
            Unit.setTeacher(teacher);

            //empty resource
            //((TeacherLogic) teacherLogicI).close();
            return Unit;
        } else
            return null;
    }

    @Override
    protected void finalize() throws Throwable {
        dbConnectionI.close();
    }
}
