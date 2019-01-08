package org.lyg.common;
//import org.springframework.core.Ordered;
//
//import org.springframework.core.annotation.Order;
import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
//@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface RequestLimit {
	int count() default Integer.MAX_VALUE;
	long time() default 60000;
}