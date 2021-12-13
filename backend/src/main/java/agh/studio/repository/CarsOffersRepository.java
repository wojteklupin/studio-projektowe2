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

    @Query(value = "SELECT co.voivodship as label, COUNT(*) as count FROM CarOffer co WHERE " +
            "(:make IS NULL OR co.make = :make) AND " +
            "(:minPrice IS NULL OR co.price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR co.price <= :maxPrice) AND " +
            "(:minYear IS NULL OR co.year >= :minYear) AND " +
            "(:maxYear IS NULL OR co.year <= :maxYear) AND " +
            "(:minMileage IS NULL OR co.mileage >= :minMileage) AND " +
            "(:maxMileage IS NULL OR co.mileage <= :maxMileage) GROUP BY co.voivodship")
    List<StringCountProjection> getOffersCountPerVoivodship(@Param("make") String make, @Param("minPrice") Double minPrice,
                                                            @Param("maxPrice") Double maxPrice, @Param("minYear") Integer minYear,
                                                            @Param("maxYear") Integer maxYear, @Param("minMileage") Long minMileage,
                                                            @Param("maxMileage") Long maxMileage);

    @Query(value = "SELECT co.fuelType as label, COUNT(*) as count FROM CarOffer co WHERE " +
            "(:make IS NULL OR co.make = :make) AND " +
            "(:minPrice IS NULL OR co.price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR co.price <= :maxPrice) AND " +
            "(:minYear IS NULL OR co.year >= :minYear) AND " +
            "(:maxYear IS NULL OR co.year <= :maxYear) AND " +
            "(:minMileage IS NULL OR co.mileage >= :minMileage) AND " +
            "(:maxMileage IS NULL OR co.mileage <= :maxMileage) GROUP BY co.fuelType")
    List<StringCountProjection> getOffersCountPerFuelType(@Param("make") String make, @Param("minPrice") Double minPrice,
                                                          @Param("maxPrice") Double maxPrice, @Param("minYear") Integer minYear,
                                                          @Param("maxYear") Integer maxYear, @Param("minMileage") Long minMileage,
                                                          @Param("maxMileage") Long maxMileage);

    @Query(value = "SELECT co.body as label, COUNT(*) as count FROM CarOffer co WHERE " +
            "(:make IS NULL OR co.make = :make) AND " +
            "(:minPrice IS NULL OR co.price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR co.price <= :maxPrice) AND " +
            "(:minYear IS NULL OR co.year >= :minYear) AND " +
            "(:maxYear IS NULL OR co.year <= :maxYear) AND " +
            "(:minMileage IS NULL OR co.mileage >= :minMileage) AND " +
            "(:maxMileage IS NULL OR co.mileage <= :maxMileage) GROUP BY co.body")
    List<StringCountProjection> getOffersCountPerBody(@Param("make") String make, @Param("minPrice") Double minPrice,
                                                      @Param("maxPrice") Double maxPrice, @Param("minYear") Integer minYear,
                                                      @Param("maxYear") Integer maxYear, @Param("minMileage") Long minMileage,
                                                      @Param("maxMileage") Long maxMileage);

    @Query(value = "SELECT co.make as label, COUNT(*) as count FROM CarOffer co WHERE " +
            "(:make IS NULL OR co.make = :make) AND " +
            "(:minPrice IS NULL OR co.price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR co.price <= :maxPrice) AND " +
            "(:minYear IS NULL OR co.year >= :minYear) AND " +
            "(:maxYear IS NULL OR co.year <= :maxYear) AND " +
            "(:minMileage IS NULL OR co.mileage >= :minMileage) AND " +
            "(:maxMileage IS NULL OR co.mileage <= :maxMileage) GROUP BY co.make")
    List<StringCountProjection> getOffersCountPerMake(@Param("make") String make, @Param("minPrice") Double minPrice,
                                                      @Param("maxPrice") Double maxPrice, @Param("minYear") Integer minYear,
                                                      @Param("maxYear") Integer maxYear, @Param("minMileage") Long minMileage,
                                                      @Param("maxMileage") Long maxMileage);

    @Query(value = "SELECT co.enginePowerCategory as category, COUNT(*) as count FROM CarOffer co WHERE " +
            "(:make IS NULL OR co.make = :make) AND " +
            "(:minPrice IS NULL OR co.price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR co.price <= :maxPrice) AND " +
            "(:minYear IS NULL OR co.year >= :minYear) AND " +
            "(:maxYear IS NULL OR co.year <= :maxYear) AND " +
            "(:minMileage IS NULL OR co.mileage >= :minMileage) AND " +
            "(:maxMileage IS NULL OR co.mileage <= :maxMileage) GROUP BY co.enginePowerCategory")
    List<IntegerCountProjection> getOffersCOuntPerEnginePower(@Param("make") String make, @Param("minPrice") Double minPrice,
                                                              @Param("maxPrice") Double maxPrice, @Param("minYear") Integer minYear,
                                                              @Param("maxYear") Integer maxYear, @Param("minMileage") Long minMileage,
                                                              @Param("maxMileage") Long maxMileage);

    @Query(value = "SELECT co.engineCapacityCategory as category, COUNT(*) as count FROM CarOffer co WHERE " +
            "(:make IS NULL OR co.make = :make) AND " +
            "(:minPrice IS NULL OR co.price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR co.price <= :maxPrice) AND " +
            "(:minYear IS NULL OR co.year >= :minYear) AND " +
            "(:maxYear IS NULL OR co.year <= :maxYear) AND " +
            "(:minMileage IS NULL OR co.mileage >= :minMileage) AND " +
            "(:maxMileage IS NULL OR co.mileage <= :maxMileage) GROUP BY co.engineCapacityCategory")
    List<IntegerCountProjection> getOffersCOuntPerEngineCapacity(@Param("make") String make, @Param("minPrice") Double minPrice,
                                                                 @Param("maxPrice") Double maxPrice, @Param("minYear") Integer minYear,
                                                                 @Param("maxYear") Integer maxYear, @Param("minMileage") Long minMileage,
                                                                 @Param("maxMileage") Long maxMileage);

    @Query(value = "SELECT co.priceCategory as category, COUNT(*) as count FROM CarOffer co WHERE " +
            "(:make IS NULL OR co.make = :make) AND " +
            "(:minPrice IS NULL OR co.price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR co.price <= :maxPrice) AND " +
            "(:minYear IS NULL OR co.year >= :minYear) AND " +
            "(:maxYear IS NULL OR co.year <= :maxYear) AND " +
            "(:minMileage IS NULL OR co.mileage >= :minMileage) AND " +
            "(:maxMileage IS NULL OR co.mileage <= :maxMileage) GROUP BY co.priceCategory")
    List<IntegerCountProjection> getOffersCOuntPerPrice(@Param("make") String make, @Param("minPrice") Double minPrice,
                                                        @Param("maxPrice") Double maxPrice, @Param("minYear") Integer minYear,
                                                        @Param("maxYear") Integer maxYear, @Param("minMileage") Long minMileage,
                                                        @Param("maxMileage") Long maxMileage);

}
