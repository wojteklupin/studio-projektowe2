package agh.studio.repository;

import agh.studio.entity.CarOffer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarsOffersRepository extends CrudRepository<CarOffer, Long> {

    @Query(value = "SELECT co FROM CarOffer co WHERE " +
            "(:make IS NULL OR co.make = :make) AND " +
            "(:minPrice IS NULL OR co.price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR co.price <= :maxPrice) AND " +
            "(:minYear IS NULL OR co.year >= :minYear) AND " +
            "(:maxYear IS NULL OR co.year <= :maxYear) AND " +
            "(:minMileage IS NULL OR co.mileage >= :minMileage) AND " +
            "(:maxMileage IS NULL OR co.mileage <= :maxMileage)"
    )
    List<CarOffer> findCarOffers(@Param("make") String make, @Param("minPrice") Double minPrice,
                                 @Param("maxPrice") Double maxPrice, @Param("minYear") Integer minYear,
                                 @Param("maxYear") Integer maxYear, @Param("minMileage") Long minMileage,
                                 @Param("maxMileage") Long maxMileage);
}
