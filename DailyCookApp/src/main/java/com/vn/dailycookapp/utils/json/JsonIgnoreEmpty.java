/**
 * 
 */
package com.vn.dailycookapp.utils.json;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author duyetpt
 * Igore empty and null value
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.FIELD})
public @interface JsonIgnoreEmpty {
}
