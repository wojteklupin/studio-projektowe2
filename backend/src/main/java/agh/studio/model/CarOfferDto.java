package agh.studio.model;

import agh.studio.entity.CarOffer;

import java.util.List;

public class CarOfferDto {

    private Long id;
    private String make;
    private String model;
    private String title;
    private String description;
    private Double price;
    private Integer year;
    private Long mileage;
    private List<String> images;
    private Double rate;

    public CarOfferDto(Long id, String make, String model, String title, String description, Double price, Integer year,
                       Long mileage, List<String> images, Double rate) {
        this.id = id;
        this.make = make;
        this.model = model;
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

    public Double getRate() {
        return rate;
    }

    public static CarOfferDto createFromEntity(CarOffer carOffer) {
        return new CarOfferDto(carOffer.getId(), carOffer.getMake(), carOffer.getModel(), carOffer.getTitle(),
                carOffer.getDescription(), carOffer.getPrice(), carOffer.getYear(), carOffer.getMileage(),
                carOffer.getImages(), 0.0);
    }
}
