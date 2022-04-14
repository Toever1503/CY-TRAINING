package annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Map;

@Retention(RetentionPolicy.RUNTIME)
public @interface ShikiAnnotation {
    Map author() ;
    String date();
    String description();
}
