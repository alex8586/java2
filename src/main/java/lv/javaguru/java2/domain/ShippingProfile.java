package lv.javaguru.java2.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;

import javax.persistence.*;

@Entity
@Table(name = "shipping_profiles")
public class ShippingProfile implements BaseEntity {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "person")
    private String person;

    @Column(name = "document")
    private String document;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    public ShippingProfile() {
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "ShippingProfile{" +
                "id=" + id + '\'' +
                ",person='" + person + '\'' +
                ", document='" + document + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || this.getClass() != object.getClass()) return false;

        ShippingProfile other = (ShippingProfile) object;

        return new EqualsBuilder()
                //.append(this.getId(), other.getId())
                .append(this.getAddress(), other.getAddress())
                .append(this.getPerson(), other.getPerson())
                .append(this.getDocument(), other.getDocument())
                .append(this.getPhone(), other.getPhone())
                .append(this.getUserId(), other.getUserId())
                .isEquals();
    }


}
