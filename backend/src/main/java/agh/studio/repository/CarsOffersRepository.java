package agh.studio.repository;

import agh.studio.entity.CarOffer;
import com.fasterxml.jackson.annotation.OptBoolean;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

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

    Optional<CarOffer> findCarOfferById(Long id);
}
