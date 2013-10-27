package org.mapjdbc.test;

import org.mapjdbc.annotations.Table;
import org.mapjdbc.annotations.parameter;

@Table(name = "TableName")
public class MyClassTest {

	private Integer idInsert;
	private String strigName;
	
	
	
	/**
	 * @param idInsert
	 * @param strigName
	 */
	public MyClassTest(Integer idInsert, String strigName) {
		super();
		this.idInsert = idInsert;
		this.strigName = strigName;
	}
	@parameter(type="java.lang.Integer",jdbcType="INTEGER")
	public Integer getIdInsert() {
		return idInsert;
	}
	public void setIdInsert(Integer idInsert) {
		this.idInsert = idInsert;
	}
	@parameter(type="java.lang.String",jdbcType="VARCHAR")
	public String getStrigName() {
		return strigName;
	}
	public void setStrigName(String strigName) {
		this.strigName = strigName;
	}
	
	 

}
