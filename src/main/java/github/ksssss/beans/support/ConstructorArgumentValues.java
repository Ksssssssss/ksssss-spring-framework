package github.ksssss.beans.support;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ksssss
 * @since 2021/3/16 21:26
 */
public class ConstructorArgumentValues {
    private Map<Integer, ValueHolder> indexedArgumentValues = new HashMap<>();

    public void addIndexedArgumentValues(int index, Object value) {
        this.indexedArgumentValues.put(index, new ValueHolder(value));
    }

    public void addIndexedArgumentValues(int index, String type, Object value) {
        this.indexedArgumentValues.put(index, new ValueHolder(value, type));
    }

    public ValueHolder getIndexedArgumentValues(int index, Class requiredType) {
        ValueHolder valueHolder = this.indexedArgumentValues.get(index);
        if (valueHolder != null && (valueHolder.type == null || valueHolder.type.equals(requiredType))) {
            return valueHolder;
        }
        return null;
    }

    public static class ValueHolder {
        private Object value;
        private String type;

        public ValueHolder(Object value) {
            this.value = value;
        }

        public ValueHolder(Object value, String type) {
            this.value = value;
            this.type = type;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public String getType() {
            return type;
        }
    }

}
