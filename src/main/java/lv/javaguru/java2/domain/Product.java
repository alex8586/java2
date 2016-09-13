package lv.javaguru.java2.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "products")
public class Product implements BaseEntity {

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private List<Review> reviews = new ArrayList<>();
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "category_id")
    private long categoryId;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private long price;
    @Column(name = "imgurl")
    private String imgUrl;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private List<Stock> stockList = new ArrayList<>();

    public List<Stock> getStockList() {
        return stockList;
    }

    public void setStockList(List<Stock> stockList) {
        this.stockList = stockList;
    }

    public List<Stock> getFresh() {
        Date today = new Date();
        List<Stock> fresh = stockList.stream()
                .filter(stock -> stock.getExpireDate()
                        .compareTo(today) >= 0)
                .sorted((stock1, stock2) -> stock1.getExpireDate().compareTo(stock2.getExpireDate()))
                .collect(Collectors.toList());
        return fresh;
    }

    public List<Review> getReviews() {
        return reviews;
    }
    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public long getCategoryID() {
        return categoryId;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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
        return new HashCodeBuilder(11, 37)
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
                + "categoryId: " + categoryId
                + "}";
    }

}
