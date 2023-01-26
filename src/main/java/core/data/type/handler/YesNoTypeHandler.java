package core.data.type.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import core.data.type.YesNoType;

public class YesNoTypeHandler implements TypeHandler<YesNoType> {

	@Override
	public void setParameter(PreparedStatement ps, int i, YesNoType parameter,
			JdbcType jdbcType) throws SQLException {
		ps.setString(i, parameter.getValue());
	}

	@Override
	public YesNoType getResult(ResultSet rs, String columnName)
			throws SQLException {
		return getEnum(rs.getString(columnName));
	}

	@Override
	public YesNoType getResult(ResultSet rs, int columnIndex)
			throws SQLException {
		return getEnum(rs.getString(columnIndex));
	}

	@Override
	public YesNoType getResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		return getEnum(cs.getString(columnIndex));
	}
	
	private YesNoType getEnum(String value){
		for(YesNoType yn : YesNoType.values()){
			if(value.equals(yn.getValue())){
				return yn;
			}
		}
		return null;
	}

}
