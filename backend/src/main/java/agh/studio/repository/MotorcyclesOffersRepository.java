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

    @Query(value = "SELECT voivodship as label, COUNT(*) as count FROM motorcycles_offers GROUP BY voivodship ORDER BY count DESC", nativeQuery = true)
    List<StringCountProjection> getOffersCountPerVoivodship();

    @Query(value = "SELECT make as label, COUNT(*) as count FROM motorcycles_offers GROUP BY make ORDER BY count DESC", nativeQuery = true)
    List<StringCountProjection> getOffersCountPerMake();

    @Query(value = "SELECT engine_power_category as category, COUNT(*) as count FROM motorcycles_offers GROUP BY engine_power_category ORDER BY count DESC", nativeQuery = true)
    List<IntegerCountProjection> getOffersCOuntPerEnginePower();

    @Query(value = "SELECT engine_capacity_category as category, COUNT(*) as count FROM motorcycles_offers GROUP BY engine_capacity_category ORDER BY count DESC", nativeQuery = true)
    List<IntegerCountProjection> getOffersCOuntPerEngineCapacity();

    @Query(value = "SELECT price_category as category, COUNT(*) as count FROM motorcycles_offers GROUP BY price_category ORDER BY count DESC", nativeQuery = true)
    List<IntegerCountProjection> getOffersCOuntPerPrice();
}
