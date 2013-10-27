package org.mapjdbc.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * This annotation is to be put on the methods whose value is to be sent to the database for operations.
 * The type is used so that at the time of converting the object we can cast it to the field.
 * The jdbc type specifies that '' should be used with a column or not.
 * 
 * 
 * */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface parameter {
	String type() default "java.lang.String";
	String jdbcType() default "VARCHAR";
}
