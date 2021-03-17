package github.ksssss.beans.support;

import com.sun.istack.internal.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author ksssss
 * @since 2021/3/15 18:07
 */
public interface PropertyValues {
    PropertyValue[] getPropertyValues();

    PropertyValue getPropertyValue(String PropertyName);

    boolean contains(String PropertyName);

    PropertyValues changesSince(PropertyValues old);
}
