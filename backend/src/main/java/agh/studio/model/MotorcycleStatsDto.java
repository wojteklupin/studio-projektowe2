package agh.studio.model;

import java.util.List;

public class MotorcycleStatsDto {

    private List<StringCountProjection> voivodship;
    private List<StringCountProjection> make;
    private List<CountStatsDto> power;
    private List<CountStatsDto> capacity;
    private List<CountStatsDto> price;

    public MotorcycleStatsDto(){};

    public List<StringCountProjection> getVoivodship() {
        return voivodship;
    }

    public List<StringCountProjection> getMake() {
        return make;
    }

    public List<CountStatsDto> getPower() {
        return power;
    }

    public List<CountStatsDto> getCapacity() {
        return capacity;
    }

    public List<CountStatsDto> getPrice() {
        return price;
    }

    public void setVoivodship(List<StringCountProjection> voivodship) {
        this.voivodship = voivodship;
    }

    public void setMake(List<StringCountProjection> make) {
        this.make = make;
    }

    public void setPower(List<CountStatsDto> power) {
        this.power = power;
    }

    public void setCapacity(List<CountStatsDto> capacity) {
        this.capacity = capacity;
    }

    public void setPrice(List<CountStatsDto> price) {
        this.price = price;
    }
}
