package agh.studio.model;

import java.util.List;

public class CarStatsDto {

    private List<StringCountProjection> voivodship;
    private List<StringCountProjection> fuel;
    private List<StringCountProjection> body;
    private List<StringCountProjection> make;
    private List<Integer> power;
    private List<Integer> capacity;
    private List<Double> price;


    public CarStatsDto(){};

    public void setVoivodship(List<StringCountProjection> voivodship) {
        this.voivodship = voivodship;
    }

    public void setFuel(List<StringCountProjection> fuel) {
        this.fuel = fuel;
    }

    public void setBody(List<StringCountProjection> body) {
        this.body = body;
    }

    public void setMake(List<StringCountProjection> make) {
        this.make = make;
    }

    public void setPower(List<Integer> power) {
        this.power = power;
    }

    public void setCapacity(List<Integer> capacity) {
        this.capacity = capacity;
    }

    public void setPrice(List<Double> price) {
        this.price = price;
    }

    public List<StringCountProjection> getVoivodship() {
        return voivodship;
    }

    public List<StringCountProjection> getFuel() {
        return fuel;
    }

    public List<StringCountProjection> getBody() {
        return body;
    }

    public List<StringCountProjection> getMake() {
        return make;
    }

    public List<Integer> getPower() {
        return power;
    }

    public List<Integer> getCapacity() {
        return capacity;
    }

    public List<Double> getPrice() {
        return price;
    }
}
