package github.ksssss.utils;

import org.reflections.Reflections;
import org.reflections.scanners.TypeAnnotationsScanner;

import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * @author ksssss
 * @since 2021/3/29 23:31
 */
public class ReflectionUtil {
    public static Set<Class<?>> scanPackagesClasses(String[] packagesName, Class<? extends Annotation> annotation) {
        Reflections reflections = new Reflections(packagesName, new TypeAnnotationsScanner());
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(annotation);
        return classes;
    }
}
