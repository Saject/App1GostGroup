package ru.gostgroup.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.gostgroup.models.Departs;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartsMapper implements RowMapper<Departs> {
    @Override
    public Departs mapRow(ResultSet rs, int rowNum) throws SQLException {
        Departs emp = new Departs();
        emp.setId(rs.getInt("id"));
        emp.setName(rs.getString("name"));
        return emp;
    }
}
