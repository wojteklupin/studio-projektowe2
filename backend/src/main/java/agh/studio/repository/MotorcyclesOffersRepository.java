package agh.studio.repository;

import agh.studio.entity.CarOffer;
import agh.studio.entity.MotorcycleOffer;
import agh.studio.model.IntegerCountProjection;
import agh.studio.model.StringCountProjection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MotorcyclesOffersRepository extends PagingAndSortingRepository<MotorcycleOffer, Long> {

    @Query(value = "SELECT mo FROM MotorcycleOffer mo WHERE " +
            "(:make IS NULL OR mo.make = :make) AND " +
            "(:minPrice IS NULL OR mo.price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR mo.price <= :maxPrice) AND " +
            "(:minYear IS NULL OR mo.year >= :minYear) AND " +
            "(:maxYear IS NULL OR mo.year <= :maxYear) AND " +
            "(:minMileage IS NULL OR mo.mileage >= :minMileage) AND " +
            "(:maxMileage IS NULL OR mo.mileage <= :maxMileage)"
    )
    List<MotorcycleOffer> findMotorcycleOffers(@Param("make") String make, @Param("minPrice") Double minPrice,
                                 @Param("maxPrice") Double maxPrice, @Param("minYear") Integer minYear,
                                 @Param("maxYear") Integer maxYear, @Param("minMileage") Long minMileage,
                                 @Param("maxMileage") Long maxMileage, Pageable pageable);


    @Query(value = "SELECT mo FROM MotorcycleOffer mo WHERE " +
            "(:make IS NULL OR mo.make = :make) AND " +
            "(:minPrice IS NULL OR mo.price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR mo.price <= :maxPrice) AND " +
            "(:minYear IS NULL OR mo.year >= :minYear) AND " +
            "(:maxYear IS NULL OR mo.year <= :maxYear) AND " +
            "(:minMileage IS NULL OR mo.mileage >= :minMileage) AND " +
            "(:maxMileage IS NULL OR mo.mileage <= :maxMileage) " +
            "ORDER BY mo.price ASC"
    )
    List<MotorcycleOffer> findMotorcycleOffersSortedByPriceAsc(@Param("make") String make, @Param("minPrice") Double minPrice,
                                                 @Param("maxPrice") Double maxPrice, @Param("minYear") Integer minYear,
                                                 @Param("maxYear") Integer maxYear, @Param("minMileage") Long minMileage,
                                                 @Param("maxMileage") Long maxMileage, Pageable pageable);

    @Query(value = "SELECT mo FROM MotorcycleOffer mo WHERE " +
            "(:make IS NULL OR mo.make = :make) AND " +
            "(:minPrice IS NULL OR mo.price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR mo.price <= :maxPrice) AND " +
            "(:minYear IS NULL OR mo.year >= :minYear) AND " +
            "(:maxYear IS NULL OR mo.year <= :maxYear) AND " +
            "(:minMileage IS NULL OR mo.mileage >= :minMileage) AND " +
            "(:maxMileage IS NULL OR mo.mileage <= :maxMileage) " +
            "ORDER BY mo.price DESC"
    )
    List<MotorcycleOffer> findMotorcycleOffersSortedByPriceDesc(@Param("make") String make, @Param("minPrice") Double minPrice,
                                                  @Param("maxPrice") Double maxPrice, @Param("minYear") Integer minYear,
                                                  @Param("maxYear") Integer maxYear, @Param("minMileage") Long minMileage,
                                                  @Param("maxMileage") Long maxMileage, Pageable pageable);

    @Query(value = "SELECT mo.voivodship as label, COUNT(*) as count FROM MotorcycleOffer mo WHERE " +
            "(:make IS NULL OR mo.make = :make) AND " +
            "(:minPrice IS NULL OR mo.price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR mo.price <= :maxPrice) AND " +
            "(:minYear IS NULL OR mo.year >= :minYear) AND " +
            "(:maxYear IS NULL OR mo.year <= :maxYear) AND " +
            "(:minMileage IS NULL OR mo.mileage >= :minMileage) AND " +
            "(:maxMileage IS NULL OR mo.mileage <= :maxMileage) GROUP BY mo.voivodship")
    List<StringCountProjection> getOffersCountPerVoivodship(@Param("make") String make, @Param("minPrice") Double minPrice,
                                                            @Param("maxPrice") Double maxPrice, @Param("minYear") Integer minYear,
                                                            @Param("maxYear") Integer maxYear, @Param("minMileage") Long minMileage,
                                                            @Param("maxMileage") Long maxMileage);

    @Query(value = "SELECT mo.make as label, COUNT(*) as count FROM MotorcycleOffer mo WHERE " +
            "(:make IS NULL OR mo.make = :make) AND " +
            "(:minPrice IS NULL OR mo.price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR mo.price <= :maxPrice) AND " +
            "(:minYear IS NULL OR mo.year >= :minYear) AND " +
            "(:maxYear IS NULL OR mo.year <= :maxYear) AND " +
            "(:minMileage IS NULL OR mo.mileage >= :minMileage) AND " +
            "(:maxMileage IS NULL OR mo.mileage <= :maxMileage) GROUP BY mo.make")
    List<StringCountProjection> getOffersCountPerMake(@Param("make") String make, @Param("minPrice") Double minPrice,
                                                      @Param("maxPrice") Double maxPrice, @Param("minYear") Integer minYear,
                                                      @Param("maxYear") Integer maxYear, @Param("minMileage") Long minMileage,
                                                      @Param("maxMileage") Long maxMileage);

    @Query(value = "SELECT mo.enginePowerCategory as category, COUNT(*) as count FROM MotorcycleOffer mo WHERE " +
            "(:make IS NULL OR mo.make = :make) AND " +
            "(:minPrice IS NULL OR mo.price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR mo.price <= :maxPrice) AND " +
            "(:minYear IS NULL OR mo.year >= :minYear) AND " +
            "(:maxYear IS NULL OR mo.year <= :maxYear) AND " +
            "(:minMileage IS NULL OR mo.mileage >= :minMileage) AND " +
            "(:maxMileage IS NULL OR mo.mileage <= :maxMileage) GROUP BY mo.enginePowerCategory")
    List<IntegerCountProjection> getOffersCOuntPerEnginePower(@Param("make") String make, @Param("minPrice") Double minPrice,
                                                              @Param("maxPrice") Double maxPrice, @Param("minYear") Integer minYear,
                                                              @Param("maxYear") Integer maxYear, @Param("minMileage") Long minMileage,
                                                              @Param("maxMileage") Long maxMileage);

    @Query(value = "SELECT mo.engineCapacityCategory as category, COUNT(*) as count FROM MotorcycleOffer mo WHERE " +
            "(:make IS NULL OR mo.make = :make) AND " +
            "(:minPrice IS NULL OR mo.price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR mo.price <= :maxPrice) AND " +
            "(:minYear IS NULL OR mo.year >= :minYear) AND " +
            "(:maxYear IS NULL OR mo.year <= :maxYear) AND " +
            "(:minMileage IS NULL OR mo.mileage >= :minMileage) AND " +
            "(:maxMileage IS NULL OR mo.mileage <= :maxMileage) GROUP BY mo.engineCapacityCategory")
    List<IntegerCountProjection> getOffersCOuntPerEngineCapacity(@Param("make") String make, @Param("minPrice") Double minPrice,
                                                                 @Param("maxPrice") Double maxPrice, @Param("minYear") Integer minYear,
                                                                 @Param("maxYear") Integer maxYear, @Param("minMileage") Long minMileage,
                                                                 @Param("maxMileage") Long maxMileage);

    @Query(value = "SELECT mo.priceCategory as category, COUNT(*) as count FROM MotorcycleOffer mo WHERE " +
            "(:make IS NULL OR mo.make = :make) AND " +
            "(:minPrice IS NULL OR mo.price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR mo.price <= :maxPrice) AND " +
            "(:minYear IS NULL OR mo.year >= :minYear) AND " +
            "(:maxYear IS NULL OR mo.year <= :maxYear) AND " +
            "(:minMileage IS NULL OR mo.mileage >= :minMileage) AND " +
            "(:maxMileage IS NULL OR mo.mileage <= :maxMileage) GROUP BY mo.priceCategory")
    List<IntegerCountProjection> getOffersCOuntPerPrice(@Param("make") String make, @Param("minPrice") Double minPrice,
                                                        @Param("maxPrice") Double maxPrice, @Param("minYear") Integer minYear,
                                                        @Param("maxYear") Integer maxYear, @Param("minMileage") Long minMileage,
                                                        @Param("maxMileage") Long maxMileage);

    @Query(value = "SELECT mo.engineCapcacity FROM MotorcycleOffer mo WHERE " +
            "(:make IS NULL OR mo.make = :make) AND " +
            "(:minPrice IS NULL OR mo.price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR mo.price <= :maxPrice) AND " +
            "(:minYear IS NULL OR mo.year >= :minYear) AND " +
            "(:maxYear IS NULL OR mo.year <= :maxYear) AND " +
            "(:minMileage IS NULL OR mo.mileage >= :minMileage) AND " +
            "(:maxMileage IS NULL OR mo.mileage <= :maxMileage)"
    )
    List<Integer> getEngineCapacity(@Param("make") String make, @Param("minPrice") Double minPrice,
                                               @Param("maxPrice") Double maxPrice, @Param("minYear") Integer minYear,
                                               @Param("maxYear") Integer maxYear, @Param("minMileage") Long minMileage,
                                               @Param("maxMileage") Long maxMileage);

    @Query(value = "SELECT mo.price FROM MotorcycleOffer mo WHERE " +
            "(:make IS NULL OR mo.make = :make) AND " +
            "(:minPrice IS NULL OR mo.price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR mo.price <= :maxPrice) AND " +
            "(:minYear IS NULL OR mo.year >= :minYear) AND " +
            "(:maxYear IS NULL OR mo.year <= :maxYear) AND " +
            "(:minMileage IS NULL OR mo.mileage >= :minMileage) AND " +
            "(:maxMileage IS NULL OR mo.mileage <= :maxMileage)"
    )
    List<Double> getPrice(@Param("make") String make, @Param("minPrice") Double minPrice,
                                    @Param("maxPrice") Double maxPrice, @Param("minYear") Integer minYear,
                                    @Param("maxYear") Integer maxYear, @Param("minMileage") Long minMileage,
                                    @Param("maxMileage") Long maxMileage);
}
