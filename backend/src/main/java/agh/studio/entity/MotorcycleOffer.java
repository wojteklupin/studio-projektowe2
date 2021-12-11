package agh.studio.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "motorcycle_offers")
public class MotorcycleOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String make;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    private Double price;
    private Integer year;
    private Long mileage;
    @ElementCollection
    private List<String> images;
    private Double numberOfRates;
    private Double sumOfRates;
    private String voivodship;

    public MotorcycleOffer(){};

    public MotorcycleOffer(String make, String title, String description, Double price, Integer year, Long mileage,
                    List<String> images, Double numberOfRates, Double sumOfRates, String voivodship) {
        this.make = make;
        this.title = title;
        this.description = description;
        this.price = price;
        this.year = year;
        this.mileage = mileage;
        this.images = images;
        this.numberOfRates = numberOfRates;
        this.sumOfRates = sumOfRates;
        this.voivodship = voivodship;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMake(String make) {
        this.make = make;
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

    public void setNumberOfRates(Double numberOfRates) {
        this.numberOfRates = numberOfRates;
    }

    public void setSumOfRates(Double sumOfRates) {
        this.sumOfRates = sumOfRates;
    }

    public void setVoivodship(String voivodship) {
        this.voivodship = voivodship;
    }

    public Long getId() {
        return id;
    }

    public String getMake() {
        return make;
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

    public Double getNumberOfRates() {
        return numberOfRates;
    }

    public Double getSumOfRates() {
        return sumOfRates;
    }

    public String getVoivodship() {
        return voivodship;
    }
}
