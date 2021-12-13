package agh.studio.repository;

import agh.studio.entity.CarOffer;
import agh.studio.model.IntegerCountProjection;
import agh.studio.model.StringCountProjection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarsOffersRepository extends PagingAndSortingRepository<CarOffer, Long> {

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
                                 @Param("maxMileage") Long maxMileage, Pageable pageable);


    @Query(value = "SELECT co FROM CarOffer co WHERE " +
            "(:make IS NULL OR co.make = :make) AND " +
            "(:minPrice IS NULL OR co.price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR co.price <= :maxPrice) AND " +
            "(:minYear IS NULL OR co.year >= :minYear) AND " +
            "(:maxYear IS NULL OR co.year <= :maxYear) AND " +
            "(:minMileage IS NULL OR co.mileage >= :minMileage) AND " +
            "(:maxMileage IS NULL OR co.mileage <= :maxMileage) " +
            "ORDER BY co.price ASC"
    )
    List<CarOffer> findCarOffersSortedByPriceAsc(@Param("make") String make, @Param("minPrice") Double minPrice,
                                              @Param("maxPrice") Double maxPrice, @Param("minYear") Integer minYear,
                                              @Param("maxYear") Integer maxYear, @Param("minMileage") Long minMileage,
                                              @Param("maxMileage") Long maxMileage, Pageable pageable);

    @Query(value = "SELECT co FROM CarOffer co WHERE " +
            "(:make IS NULL OR co.make = :make) AND " +
            "(:minPrice IS NULL OR co.price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR co.price <= :maxPrice) AND " +
            "(:minYear IS NULL OR co.year >= :minYear) AND " +
            "(:maxYear IS NULL OR co.year <= :maxYear) AND " +
            "(:minMileage IS NULL OR co.mileage >= :minMileage) AND " +
            "(:maxMileage IS NULL OR co.mileage <= :maxMileage) " +
            "ORDER BY co.price DESC"
    )
    List<CarOffer> findCarOffersSortedByPriceDesc(@Param("make") String make, @Param("minPrice") Double minPrice,
                                              @Param("maxPrice") Double maxPrice, @Param("minYear") Integer minYear,
                                              @Param("maxYear") Integer maxYear, @Param("minMileage") Long minMileage,
                                              @Param("maxMileage") Long maxMileage, Pageable pageable);

    @Query(value = "SELECT voivodship as label, COUNT(*) as count FROM cars_offers GROUP BY voivodship ORDER BY count DESC", nativeQuery = true)
    List<StringCountProjection> getOffersCountPerVoivodship();

    @Query(value = "SELECT fuel_type as label, COUNT(*) as count FROM cars_offers GROUP BY fuel_type ORDER BY count DESC", nativeQuery = true)
    List<StringCountProjection> getOffersCountPerFuelType();

    @Query(value = "SELECT body as label, COUNT(*) as count FROM cars_offers GROUP BY body ORDER BY count DESC", nativeQuery = true)
    List<StringCountProjection> getOffersCountPerBody();

    @Query(value = "SELECT make as label, COUNT(*) as count FROM cars_offers GROUP BY make ORDER BY count DESC", nativeQuery = true)
    List<StringCountProjection> getOffersCountPerMake();

    @Query(value = "SELECT engine_power_category as category, COUNT(*) as count FROM cars_offers GROUP BY engine_power_category ORDER BY count DESC", nativeQuery = true)
    List<IntegerCountProjection> getOffersCOuntPerEnginePower();

    @Query(value = "SELECT engine_capacity_category as category, COUNT(*) as count FROM cars_offers GROUP BY engine_capacity_category ORDER BY count DESC", nativeQuery = true)
    List<IntegerCountProjection> getOffersCOuntPerEngineCapacity();

    @Query(value = "SELECT price_category as category, COUNT(*) as count FROM cars_offers GROUP BY price_category ORDER BY count DESC", nativeQuery = true)
    List<IntegerCountProjection> getOffersCOuntPerPrice();

}
