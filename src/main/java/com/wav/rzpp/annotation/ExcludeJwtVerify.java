package com.wav.rzpp.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @author: hbw
 **/
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface ExcludeJwtVerify {
}
