package lv.javaguru.java2.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Category extends BaseEntity {

    private String name;

    public Category() {
        this(0, "Mayo");
        //TODO: is it ok creating categories without name? set a default name for now.
    }

    public Category(String name) {
        this(0, name);
    }

    public Category(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static void main(String[] args) {
        Category c = new Category();
        System.out.print(c.getName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" + "id=" + id + ", name='" + name + '\'' + '}';
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }

        if ((object == null) || !(object instanceof Category)) {
            return false;
        }

        Category that = (Category) object;

        return new EqualsBuilder()
                .append(id, that.getId())
                .append(name, that.getName())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .append(name)
                .toHashCode();
    }
}
