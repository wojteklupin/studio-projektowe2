package agh.studio.controller;

import agh.studio.entity.CarOffer;
import agh.studio.repository.CarsOffersRepository;
import agh.studio.service.WebScrappingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CarOffersController {

    private CarsOffersRepository carsOffersRepository;
    private WebScrappingService webScrappingService;

    public CarOffersController(CarsOffersRepository carsOffersRepository, WebScrappingService webScrappingService) {
        this.carsOffersRepository = carsOffersRepository;
        this.webScrappingService = webScrappingService;
        webScrappingService.scrapCarOffers();
    }

    @GetMapping("/cars")
    List<CarOffer> getCarOffers(@RequestParam(required = false) String make, @RequestParam(required = false) Double minPrice,
                                @RequestParam(required = false) Double maxPrice, @RequestParam(required = false) Integer minYear,
                                @RequestParam(required = false) Integer maxYear, @RequestParam(required = false) Long minMileage,
                                @RequestParam(required = false) Long maxMileage) {
        return carsOffersRepository.findCarOffers(make, minPrice, maxPrice, minYear, maxYear, minMileage, maxMileage);
    }
}
