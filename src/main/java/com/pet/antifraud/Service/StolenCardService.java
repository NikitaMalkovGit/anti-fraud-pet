package com.pet.antifraud.Service;

import com.pet.antifraud.DTO.StatusDTO;
import com.pet.antifraud.Exception.BadRequestException;
import com.pet.antifraud.Exception.EntityAlreadyExistsException;
import com.pet.antifraud.Exception.NotFoundException;
import com.pet.antifraud.Model.StolenCard;
import com.pet.antifraud.Repository.StolenCardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing stolen cards.
 */
@Service
public class StolenCardService {

    private final StolenCardRepository stolenCardRepository;

    public StolenCardService(StolenCardRepository stolenCardRepository) {
        this.stolenCardRepository = stolenCardRepository;
    }

    /**
     * Add a new stolen card to the repository.
     *
     * @param card The stolen card to be added.
     * @return The added stolen card.
     * @throws BadRequestException        If the card number is invalid.
     * @throws EntityAlreadyExistsException If a card with the same number already exists.
     */
    public StolenCard addNewCard(StolenCard card) throws BadRequestException, EntityAlreadyExistsException {
        if (isCardInvalid(card.getNumber())) {
            throw new BadRequestException();
        }
        if (stolenCardRepository.findStolenCardByNumber(card.getNumber()).isPresent()) {
            throw new EntityAlreadyExistsException();
        }
        stolenCardRepository.save(card);
        return stolenCardRepository.findStolenCardByNumber(card.getNumber()).get();
    }

    /**
     * Retrieve a list of all stolen cards.
     *
     * @return A list of all stolen cards.
     */
    public List<StolenCard> showAllCards() {
        return stolenCardRepository.findAll();
    }

    /**
     * Delete a stolen card by its number.
     *
     * @param number The number of the stolen card to be deleted.
     * @return A status DTO indicating the result of the deletion.
     * @throws BadRequestException If the card number is invalid.
     * @throws NotFoundException   If the card with the provided number is not found.
     */
    public StatusDTO deleteCard(String number) {
        if (isCardInvalid(number)) {
            throw new BadRequestException();
        }
        Optional<StolenCard> card = stolenCardRepository.findStolenCardByNumber(number);
        if (card.isEmpty()) {
            throw new NotFoundException();
        }
        stolenCardRepository.deleteById(card.get().getId());
        return new StatusDTO(String.format("Card %s successfully removed!", card.get().getNumber()));
    }
    /**
     * Checks if a card number is invalid using Luhn's algorithm.
     *
     * @param number The card number to be checked.
     * @return True if the card number is invalid, false otherwise.
     */
    public boolean isCardInvalid(String number) {
        int[] array = convertToIntArray(number);
        int checksum = array[array.length - 1];
        array[array.length - 1] = 0;
        multiplyOddNumbersByTwo(array);
        subtractNineFromNumbersOverNine(array);
        int sum = addAllNumbers(array);
        return (sum + checksum) % 10 != 0;
    }

    /**
     * Calculates the sum of all numbers in the array.
     *
     * @param array The array of integers to be summed.
     * @return The sum of all numbers in the array.
     */
    private int addAllNumbers(int[] array) {
        int sum = 0;
        for (int i : array) {
            sum += i;
        }
        return sum;
    }

    /**
     * Subtracts 9 from numbers greater than 9 in the array.
     *
     * @param array The array of integers to be modified.
     */
    private void subtractNineFromNumbersOverNine(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] > 9) {
                array[i] -= 9;
            }
        }
    }

    /**
     * Multiplies odd-indexed numbers by 2 in the array.
     *
     * @param array The array of integers to be modified.
     */
    private void multiplyOddNumbersByTwo(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if ((i + 1) % 2 != 0) {
                array[i] *= 2;
            }
        }
    }

    /**
     * Converts a string representing a number to an integer array.
     *
     * @param number The string to be converted.
     * @return An integer array representing the converted number.
     */
    private int[] convertToIntArray(String number) {
        int[] convertedIntArray = new int[number.length()];
        for (int i = 0; i < number.length(); i++) {
            convertedIntArray[i] = number.charAt(i) - 48;
        }
        return convertedIntArray;
    }
}
