package org.mapjdbc.build;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.mapjdbc.annotations.Table;
import org.mapjdbc.annotations.parameter;
import org.mapjdbc.constants.ApplicationConstants;
import org.mapjdbc.exceptions.ApplicationException;

/**
 * This is the main function class which is used to make the queries needed for
 * the database. The function is generic to make sure the structure of the class
 * is passed along with the object. The object here is the bag of values which
 * will be passed along with the class structure
 * 
 * */
public class QueryBuilder<T> {

	private final String COMMA_SIGN = ",";
	private final String START_END_STRING = "'";
	private StringBuilder query;
	private Integer questCount = 0;

	/**
	 * This class is used to generate the insert queries using the object. Execption are not handled yet.
	 * Please check and handle them
	 * 
	 * @author boyka
	 * 
	 * */
	@SuppressWarnings("unchecked")
	public String generateInsert(Object valueClass)
			throws ApplicationException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		Class clazz = valueClass.getClass();
		Method[] methods = clazz.getMethods();
		query = new StringBuilder("Insert into ");
		if (clazz.isAnnotationPresent(Table.class)) {
			Table table = (Table) clazz.getAnnotation(Table.class);
			query.append(table.name());
		}
		query.append(" values(");
		for (Method method : methods) {
			if (method.isAnnotationPresent(parameter.class)) {
				parameter parameter = method.getAnnotation(parameter.class);
				String type = parameter.type();
				if (method.getName().indexOf("get") >= 0) {
					questCount++;
				} else {
					throw new ApplicationException(104,
							"Unrecognised Type defined for a class");
				}
			}

		}
		int commas = questCount - 1;
		if (commas > 0) {
			for (; commas > 0; commas--) {
				query.append("? ,");
			}
		}
		query.append("?)");
		return query.toString();
	}
	
	
	private void generateUpdate() {
		// TODO Auto-generated method stub

	}
	
	private void generateDelete() {
		// TODO Auto-generated method stub

	}

}

