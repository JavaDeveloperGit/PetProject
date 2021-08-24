package ua.com.vovacoffee.model;

import javax.persistence.*;

@Entity
@Table(name = "photos")
public class Photo extends Model {

    private static final long serialVersionUID = 1L;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "photo_link_short")
    private String photoLinkShort;

    @Column(name = "photo_link_long")
    private String photoLinkLong;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "photo")
    private Product product;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "photo")
    private Category category;

    public Photo() {
        super();
        title = "";
        photoLinkShort = "";
        photoLinkLong = "";
    }

    public Photo(String title, String photoLinkShort, String photoLinkLong) {
        super();
        this.title = title;
        this.photoLinkShort = photoLinkShort;
        this.photoLinkLong = photoLinkLong;
    }

    public Photo(String title, String photoLinkShort) {
        super();
        this.title = title;
        this.photoLinkShort = photoLinkShort;
        photoLinkLong = "";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nTitle: ").append(title)
                .append("\nphoto short link: ").append(photoLinkShort)
                .append("\nphoto long link: ").append(photoLinkLong);
        return sb.toString();
    }

    public void initialize(String title, String photoLinkShort, String photoLinkLong) {
        setTitle(title);
        setPhotoLinkShort(photoLinkShort);
        setPhotoLinkLong(photoLinkLong);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? "" : title;
    }

    public String getPhotoLinkShort() {
        return photoLinkShort;
    }

    public void setPhotoLinkShort(String photoLinkShort) {
        this.photoLinkShort = photoLinkShort == null ? "" : photoLinkShort;
    }

    public String getPhotoLinkLong() {
        return photoLinkLong;
    }

    public void setPhotoLinkLong(String photoLinkLong) {
        this.photoLinkLong = photoLinkLong == null ? "" : photoLinkLong;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
