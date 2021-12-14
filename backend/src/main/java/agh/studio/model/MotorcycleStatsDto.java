package agh.studio.model;

import java.util.List;

public class MotorcycleStatsDto {

    private List<StringCountProjection> voivodship;
    private List<StringCountProjection> make;
    private List<Integer> capacity;
    private List<Double> price;

    public MotorcycleStatsDto(){};

    public List<StringCountProjection> getVoivodship() {
        return voivodship;
    }

    public List<StringCountProjection> getMake() {
        return make;
    }

    public List<Integer> getCapacity() {
        return capacity;
    }

    public List<Double> getPrice() {
        return price;
    }

    public void setVoivodship(List<StringCountProjection> voivodship) {
        this.voivodship = voivodship;
    }

    public void setMake(List<StringCountProjection> make) {
        this.make = make;
    }

    public void setCapacity(List<Integer> capacity) {
        this.capacity = capacity;
    }

    public void setPrice(List<Double> price) {
        this.price = price;
    }
}
