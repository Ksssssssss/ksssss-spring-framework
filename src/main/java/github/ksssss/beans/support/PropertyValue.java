package github.ksssss.beans.support;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author ksssss
 * @since 2021/3/15 18:07
 */
@Setter
@Getter
@EqualsAndHashCode
public class PropertyValue {
    private String name;
    private Object value;

    public PropertyValue(@NotNull String name, Object value) {
        this.name = name;
        this.value = value;
    }
}
