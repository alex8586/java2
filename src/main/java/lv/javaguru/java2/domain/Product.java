package lv.javaguru.java2.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Product extends BaseEntity {

    private long id;
    private String name;
    private String description;
    private long price;
    private long categoryId;
    private String imgUrl;

    public Product() {
    }

    public Product(String name) {
        this.name = name;
    }

    @Override
    public long getId() {
        return id;
    }

    public long getCategoryID() {
        return categoryID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || this.getClass() != object.getClass()) return false;

        Product other = (Product) object;

        return new EqualsBuilder()
                .append(this.getId(), other.getId())
                .append(this.getName(), other.getName())
                .append(this.getDescription(), other.getDescription())
                .append(this.getPrice(), other.getPrice())
                .append(this.getCategoryId(), other.getCategoryId())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .append(name)
                .append(description)
                .append(price)
                .append(categoryId)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Product {"
                + "id: " + id + ", "
                + "name: " + name + ", "
                + "description: " + description + ", "
                + "price: " + price + ", "
                + "categoryId: " + categoryId;
    }
}
