package github.ksssss.beans.support;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author ksssss
 * @since 2021/3/16 10:18
 */
public class MutablePropertyValues implements PropertyValues {

    private List<PropertyValue> propertyValuesList;

    public MutablePropertyValues() {
        propertyValuesList = new ArrayList<>(10);
    }

    public MutablePropertyValues(PropertyValues other) {
        if (other != null) {
            PropertyValue[] pvs = other.getPropertyValues();
            this.propertyValuesList = new ArrayList<>(pvs.length);
            for (PropertyValue pv : pvs) {
                addPropertyValue(new PropertyValue(pv.getName(), pv.getValue()));
            }
        }
    }

    public void addPropertyValue(PropertyValue pv) {
        for (int i = 0; i < this.propertyValuesList.size(); i++) {
            PropertyValue currentPv = this.propertyValuesList.get(i);
            if (currentPv.getName().equals(pv.getName())) {
                this.propertyValuesList.set(i, pv);
                return;
            }
        }
        this.propertyValuesList.add(pv);
    }

    @Override
    public PropertyValue[] getPropertyValues() {
        return propertyValuesList.toArray(new PropertyValue[0]);
    }

    @Override
    public PropertyValue getPropertyValue(String PropertyName) {

        PropertyValue pv = this.propertyValuesList.stream().filter(propertyValue -> propertyValue.getName()
                .equals(PropertyName))
                .findFirst().orElse(null);
        return pv;
    }

    @Override
    public boolean contains(String PropertyName) {
        return this.propertyValuesList.stream()
                .anyMatch(propertyValue -> propertyValue.getName().equals(PropertyName));
    }

    @Override
    public PropertyValues changesSince(PropertyValues old) {
        MutablePropertyValues changes = new MutablePropertyValues();
        if (old == this){
            return changes;
        }
        for (PropertyValue pv : this.propertyValuesList) {
            PropertyValue pvOld = old.getPropertyValue(pv.getName());
            if (pvOld == null || !pvOld.equals(pv)){
                addPropertyValue(pvOld);
            }
        }
        return changes;
    }
}
