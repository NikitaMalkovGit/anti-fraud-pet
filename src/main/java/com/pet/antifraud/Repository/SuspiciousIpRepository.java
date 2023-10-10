package com.pet.antifraud.Repository;

import com.pet.antifraud.Model.SuspiciousIP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface SuspiciousIpRepository extends JpaRepository<SuspiciousIP, Long> {

    /**
     * Find a suspicious IP by its IP address.
     *
     * @param ip The IP address to search for.
     * @return An Optional containing the found SuspiciousIP entity, if present.
     */
    Optional<SuspiciousIP> findSuspiciousIPByIp(String ip);
}
