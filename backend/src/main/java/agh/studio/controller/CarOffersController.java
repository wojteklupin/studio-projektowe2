package agh.studio.controller;

import agh.studio.entity.CarOffer;
import agh.studio.entity.MotorcycleOffer;
import agh.studio.model.*;
import agh.studio.repository.CarsOffersRepository;
import agh.studio.repository.MotorcyclesOffersRepository;
import agh.studio.service.WebScrappingService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CarOffersController {

    private CarsOffersRepository carsOffersRepository;
    private MotorcyclesOffersRepository motorcyclesOffersRepository;
    private WebScrappingService webScrappingService;

    public CarOffersController(CarsOffersRepository carsOffersRepository, WebScrappingService webScrappingService,
                               MotorcyclesOffersRepository motorcyclesOffersRepository) {
        this.carsOffersRepository = carsOffersRepository;
        this.webScrappingService = webScrappingService;
        this.motorcyclesOffersRepository = motorcyclesOffersRepository;
        webScrappingService.scrapCarOffers();
        webScrappingService.scrapMotorcycleOffers();
    }

    @CrossOrigin
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

    @CrossOrigin
    @GetMapping("/motorcycles")
    List<MotorcycleOfferDto> getMotorcyclesOffers(@RequestParam(required = false) String make, @RequestParam(required = false) Double minPrice,
                                   @RequestParam(required = false) Double maxPrice, @RequestParam(required = false) Integer minYear,
                                   @RequestParam(required = false) Integer maxYear, @RequestParam(required = false) Long minMileage,
                                   @RequestParam(required = false) Long maxMileage, @RequestParam(required = false) String sort,
                                   @RequestParam(required = false) String mode, @RequestParam Integer page) {
        if (page < 1)
            page = 1;
        Pageable pageable = PageRequest.of(page - 1, 10);
        List<MotorcycleOffer> result;
        if (sort != null && sort.equals("price")) {
            if (mode != null && mode.equals("desc"))
                result = motorcyclesOffersRepository.findMotorcycleOffersSortedByPriceDesc(make, minPrice, maxPrice, minYear, maxYear, minMileage, maxMileage, pageable);
            else
                result = motorcyclesOffersRepository.findMotorcycleOffersSortedByPriceAsc(make, minPrice, maxPrice, minYear, maxYear, minMileage, maxMileage, pageable);
        }
        else
            result = motorcyclesOffersRepository.findMotorcycleOffers(make, minPrice, maxPrice, minYear, maxYear, minMileage, maxMileage, pageable);

        return result.stream()
                .map(MotorcycleOfferDto::createFromEntity)
                .collect(Collectors.toList());
    }

    @CrossOrigin
    @GetMapping("/cars/stats")
    public CarStatsDto getCarStats(@RequestParam(required = false) String make, @RequestParam(required = false) Double minPrice,
                                   @RequestParam(required = false) Double maxPrice, @RequestParam(required = false) Integer minYear,
                                   @RequestParam(required = false) Integer maxYear, @RequestParam(required = false) Long minMileage,
                                   @RequestParam(required = false) Long maxMileage) {
        CarStatsDto stats = new CarStatsDto();
        stats.setVoivodship(carsOffersRepository.getOffersCountPerVoivodship(make, minPrice, maxPrice, minYear, maxYear, minMileage, maxMileage)
                .stream()
                .sorted(Comparator.comparingInt(StringCountProjection::getCount).reversed())
                .collect(Collectors.toList()));
        stats.setFuel(carsOffersRepository.getOffersCountPerFuelType(make, minPrice, maxPrice, minYear, maxYear, minMileage, maxMileage)
                .stream()
                .sorted(Comparator.comparingInt(StringCountProjection::getCount).reversed())
                .collect(Collectors.toList()));
        stats.setBody(carsOffersRepository.getOffersCountPerBody(make, minPrice, maxPrice, minYear, maxYear, minMileage, maxMileage)
                .stream()
                .sorted(Comparator.comparingInt(StringCountProjection::getCount).reversed())
                .collect(Collectors.toList()));
        stats.setMake(carsOffersRepository.getOffersCountPerMake(make, minPrice, maxPrice, minYear, maxYear, minMileage, maxMileage)
                .stream()
                .sorted(Comparator.comparingInt(StringCountProjection::getCount).reversed())
                .collect(Collectors.toList()));
        stats.setCapacity(carsOffersRepository.getOffersCOuntPerEngineCapacity(make, minPrice, maxPrice, minYear, maxYear, minMileage, maxMileage).stream()
                .map(count -> CountStatsDto.fromIntegerCountProjection(count, 100))
                .sorted(Comparator.comparingInt(CountStatsDto::getCount).reversed())
                .collect(Collectors.toList()));
        stats.setPower(carsOffersRepository.getOffersCOuntPerEnginePower(make, minPrice, maxPrice, minYear, maxYear, minMileage, maxMileage).stream()
                .map(count -> CountStatsDto.fromIntegerCountProjection(count, 10))
                .sorted(Comparator.comparingInt(CountStatsDto::getCount).reversed())
                .collect(Collectors.toList()));
        stats.setPrice(carsOffersRepository.getOffersCOuntPerPrice(make, minPrice, maxPrice, minYear, maxYear, minMileage, maxMileage).stream()
                .map(count -> CountStatsDto.fromIntegerCountProjection(count, 10000))
                .sorted(Comparator.comparingInt(CountStatsDto::getCount).reversed())
                .collect(Collectors.toList()));
        return stats;
    }

    @CrossOrigin
    @GetMapping("/motorcycles/stats")
    public MotorcycleStatsDto getMotorcycleStats(@RequestParam(required = false) String make, @RequestParam(required = false) Double minPrice,
                                                 @RequestParam(required = false) Double maxPrice, @RequestParam(required = false) Integer minYear,
                                                 @RequestParam(required = false) Integer maxYear, @RequestParam(required = false) Long minMileage,
                                                 @RequestParam(required = false) Long maxMileage) {
        MotorcycleStatsDto stats = new MotorcycleStatsDto();
        stats.setVoivodship(motorcyclesOffersRepository.getOffersCountPerVoivodship(make, minPrice, maxPrice, minYear, maxYear, minMileage, maxMileage)
                .stream()
                .sorted(Comparator.comparingInt(StringCountProjection::getCount).reversed())
                .collect(Collectors.toList()));
        stats.setMake(motorcyclesOffersRepository.getOffersCountPerMake(make, minPrice, maxPrice, minYear, maxYear, minMileage, maxMileage)
                .stream()
                .sorted(Comparator.comparingInt(StringCountProjection::getCount).reversed())
                .collect(Collectors.toList()));
        stats.setCapacity(motorcyclesOffersRepository.getOffersCOuntPerEngineCapacity(make, minPrice, maxPrice, minYear, maxYear, minMileage, maxMileage).stream()
                .map(count -> CountStatsDto.fromIntegerCountProjection(count, 100))
                .sorted(Comparator.comparingInt(CountStatsDto::getCount).reversed())
                .collect(Collectors.toList()));
        stats.setPrice(motorcyclesOffersRepository.getOffersCOuntPerPrice(make, minPrice, maxPrice, minYear, maxYear, minMileage, maxMileage).stream()
                .map(count -> CountStatsDto.fromIntegerCountProjection(count, 10000))
                .sorted(Comparator.comparingInt(CountStatsDto::getCount).reversed())
                .collect(Collectors.toList()));
        return stats;
    }
}
