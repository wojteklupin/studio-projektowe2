package agh.studio.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cars_offers")
public class CarOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String make;
    private String model;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    private Double price;
    private Integer year;
    private Long mileage;
    @ElementCollection
    private List<String> images;
    private String voivodship;
    @Column(name = "engine_capacity")
    private Integer engineCapcacity;
    @Column(name = "engine_power")
    private Integer enginePower;
    @Column(name = "fuel_type")
    private String fuelType;
    private String body;
    @Column(name = "price_category")
    private Integer priceCategory;
    @Column(name = "engine_capacity_category")
    private Integer engineCapacityCategory;
    @Column(name = "engine_power_category")
    private Integer enginePowerCategory;

    public CarOffer(){};

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

    public void setVoivodship(String voivodship) {
        this.voivodship = voivodship;
    }

    public void setEngineCapcacity(Integer engineCapcacity) {
        this.engineCapcacity = engineCapcacity;
    }

    public void setEnginePower(Integer enginePower) {
        this.enginePower = enginePower;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setPriceCategory(Integer priceCategory) {
        this.priceCategory = priceCategory;
    }

    public void setEngineCapacityCategory(Integer engineCapacityCategory) {
        this.engineCapacityCategory = engineCapacityCategory;
    }

    public void setEnginePowerCategory(Integer enginePowerCategory) {
        this.enginePowerCategory = enginePowerCategory;
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

    public String getVoivodship() {
        return voivodship;
    }

    public Integer getEngineCapcacity() {
        return engineCapcacity;
    }

    public Integer getEnginePower() {
        return enginePower;
    }

    public String getFuelType() {
        return fuelType;
    }

    public String getBody() {
        return body;
    }

    public Integer getPriceCategory() {
        return priceCategory;
    }

    public Integer getEngineCapacityCategory() {
        return engineCapacityCategory;
    }

    public Integer getEnginePowerCategory() {
        return enginePowerCategory;
    }
}
