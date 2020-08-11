package javaSE.project.logic;


import javaSE.project.model.Unit;

import java.sql.SQLException;
import java.util.List;

public interface UnitViewLogicI {

    boolean add(Unit unit) throws SQLException;

    boolean update(Unit unit) throws SQLException;

    boolean delete(Unit unit) throws SQLException;

    Unit find(int id) throws SQLException, ClassNotFoundException;

    List<Unit> findAll() throws SQLException, ClassNotFoundException;
}
