package com.linepro.modellbahn.persistence.util;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * BusinessKey.
 * Marks the getter as a business key for natural id queries.
 * @author   $Author$
 * @version  $Id$
 */
@Target({METHOD}) 
@Retention(RUNTIME)
@Documented
public @interface BusinessKey {

}
