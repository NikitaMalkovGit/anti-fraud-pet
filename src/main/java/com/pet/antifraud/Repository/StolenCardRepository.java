package com.pet.antifraud.Repository;

import com.pet.antifraud.Model.StolenCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface StolenCardRepository extends JpaRepository<StolenCard, Long> {

    /**
     * Find a stolen card by its number.
     *
     * @param number The card number to search for.
     * @return An Optional containing the found StolenCard entity, if present.
     */
    Optional<StolenCard> findStolenCardByNumber(String number);
}
