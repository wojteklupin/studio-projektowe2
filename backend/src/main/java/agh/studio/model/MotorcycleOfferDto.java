package agh.studio.model;

import agh.studio.entity.MotorcycleOffer;

import java.util.List;

public class MotorcycleOfferDto {

    private Long id;
    private String make;
    private String title;
    private String description;
    private Double price;
    private Integer year;
    private Long mileage;
    private List<String> images;
    private Double rate;

    public MotorcycleOfferDto(Long id, String make, String title, String description, Double price, Integer year,
                       Long mileage, List<String> images, Double rate) {
        this.id = id;
        this.make = make;
        this.title = title;
        this.description = description;
        this.price = price;
        this.year = year;
        this.mileage = mileage;
        this.images = images;
        this.rate = rate;
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

    public Double getRate() {
        return rate;
    }

    public static MotorcycleOfferDto createFromEntity(MotorcycleOffer motorcycleOffer) {
        return new MotorcycleOfferDto(motorcycleOffer.getId(), motorcycleOffer.getMake(), motorcycleOffer.getTitle(),
                motorcycleOffer.getDescription(), motorcycleOffer.getPrice(), motorcycleOffer.getYear(), motorcycleOffer.getMileage(),
                motorcycleOffer.getImages(), 0.0);
    }
}
