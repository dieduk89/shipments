package com.test.shipments.repository;

import com.test.shipments.model.LocationEntity;
import com.test.shipments.model.PackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PackageRepository extends JpaRepository<PackageEntity, Long> {

    @Query
    public Optional<PackageEntity> findByCode(String code);

    @Query(
            value =
                              "select '{\"source\":\"' || ls.code || '\",\"destination\":\"'  || ld.code  || '\",\"code\":\"' || p.code || '\",\"state\":\"' || s.name || '\",\"createDate\":\"' || FORMATDATETIME(p.create_date, 'yyyy-MM-dd HH:mm:ss') || '\"}'"
                            + "from packages p\n"
                            + "join locations ls on p.source_id = ls.id\n"
                            + "join locations ld on p.destination_id = ld.id\n"
                            + "join states s on p.current_state_id = s.id\n"
                            + "where (ls.code = :source or :source = 'none')\n"
                            + "and (ld.code = :destination or :destination = 'none')\n"
                            + "and (s.name = :state or :state = 'none')\n"
                            + "and (FORMATDATETIME(p.create_date, 'yyyy-MM-dd') = :date or :date = 'none')\n"
                            + "order by p.create_date desc",
            nativeQuery = true)
    List<String> listShipments(
            @Param("source") String source,
            @Param("destination") String destination,
            @Param("state") String state,
            @Param("date") String date
    );
}
