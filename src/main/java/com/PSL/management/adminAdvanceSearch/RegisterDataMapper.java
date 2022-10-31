package com.PSL.management.adminAdvanceSearch;


import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.PSL.management.dataModel.Register;


public class RegisterDataMapper implements RowMapper<Register>{

	@Override
	public Register mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Register register = new Register();
		register.setId(rs.getLong("id"));
		register.setFirstname(rs.getString("firstname"));
		register.setLastname(rs.getString("lastname"));
		register.setUsername(rs.getString("username"));
		register.setPassword(rs.getString("password"));
		register.setCreatedBy(rs.getString("created_by"));
		register.setModifyBy(rs.getString("modify_by"));
		register.setCreatedDate(rs.getDate("created_date"));
		register.setModifyDate(rs.getDate("modify_date"));
		return register;
	}

}
