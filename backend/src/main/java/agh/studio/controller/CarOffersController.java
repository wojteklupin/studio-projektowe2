package agh.studio.controller;

import agh.studio.entity.CarOffer;
import agh.studio.model.CarOfferDto;
import agh.studio.repository.CarsOffersRepository;
import agh.studio.service.WebScrappingService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    List<CarOfferDto> getCarOffers(@RequestParam(required = false) String make, @RequestParam(required = false) Double minPrice,
                                   @RequestParam(required = false) Double maxPrice, @RequestParam(required = false) Integer minYear,
                                   @RequestParam(required = false) Integer maxYear, @RequestParam(required = false) Long minMileage,
                                   @RequestParam(required = false) Long maxMileage, @RequestParam(required = false) String sort,
                                   @RequestParam(required = false) String mode, @RequestParam Integer page) {
        if (page < 1)
            page = 1;
        Pageable pageable = PageRequest.of(page - 1, 10);
        List<CarOffer> result;
        if (sort != null && sort.equals("price")) {
            if (mode != null && mode.equals("desc"))
                result = carsOffersRepository.findCarOffersSortedByPriceDesc(make, minPrice, maxPrice, minYear, maxYear, minMileage, maxMileage, pageable);
            else
                result = carsOffersRepository.findCarOffersSortedByPriceAsc(make, minPrice, maxPrice, minYear, maxYear, minMileage, maxMileage, pageable);
        }
        else
            result = carsOffersRepository.findCarOffers(make, minPrice, maxPrice, minYear, maxYear, minMileage, maxMileage, pageable);

        return result.stream()
                .map(CarOfferDto::createFromEntity)
                .collect(Collectors.toList());
    }
}