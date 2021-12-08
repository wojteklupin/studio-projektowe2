package agh.studio.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cars_offers")
public class CarOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String make;
    String model;
    String title;
    @Column(columnDefinition = "TEXT")
    String description;
    Double price;
    Integer year;
    Long mileage;
    @ElementCollection
    List<String> images;

    public CarOffer(){};

    public CarOffer(String make, String title, String description, Double price, Integer year, Long mileage, List<String> images) {
        this.make = make;
        this.title = title;
        this.description = description;
        this.price = price;
        this.year = year;
        this.mileage = mileage;
        this.images = images;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setMileage(Long mileage) {
        this.mileage = mileage;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public Long getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getYear() {
        return year;
    }

    public Long getMileage() {
        return mileage;
    }

    public List<String> getImages() {
        return images;
    }
}
