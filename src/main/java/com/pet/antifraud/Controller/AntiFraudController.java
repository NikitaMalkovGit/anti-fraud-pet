package com.pet.antifraud.Controller;

import com.pet.antifraud.DTO.ResultDTO;
import com.pet.antifraud.DTO.StatusDTO;
import com.pet.antifraud.DTO.TransactionDTO;
import com.pet.antifraud.Model.StolenCard;
import com.pet.antifraud.Model.SuspiciousIP;
import com.pet.antifraud.Service.StolenCardService;
import com.pet.antifraud.Service.SuspiciousIpService;
import com.pet.antifraud.Service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller class for handling anti-fraud operations.
 */
@RestController
@RequestMapping("/api/antifraud")
public class AntiFraudController {

    private final TransactionService transactionService;
    private final StolenCardService stolenCardService;
    private final SuspiciousIpService suspiciousIpService;

    public AntiFraudController(TransactionService transactionService, StolenCardService stolenCardService,
                               SuspiciousIpService suspiciousIpService) {
        this.transactionService = transactionService;
        this.stolenCardService = stolenCardService;
        this.suspiciousIpService = suspiciousIpService;
    }

    /**
     * Validates a transaction using the provided TransactionDTO and returns the result.
     *
     * @param transaction The TransactionDTO containing transaction details.
     * @return ResponseEntity containing the validation result.
     */
    @PostMapping("/transaction")
    public ResponseEntity<ResultDTO> validateTransaction(@RequestBody @Valid TransactionDTO transaction) {
        return transactionService.checkTransactionValidity(transaction);
    }

    /**
     * Adds a suspicious IP to the database based on the provided SuspiciousIP and returns the added IP.
     *
     * @param suspiciousIP The SuspiciousIP containing the IP details.
     * @return ResponseEntity containing the added SuspiciousIP.
     */
    @PostMapping("/suspicious-ip")
    public ResponseEntity<SuspiciousIP> addSuspiciousIp(@RequestBody @Valid SuspiciousIP suspiciousIP) {
        return ResponseEntity.ok(suspiciousIpService.addSuspiciousIP(suspiciousIP));
    }

    /**
     * Retrieves a list of all suspicious IPs.
     *
     * @return ResponseEntity containing a list of SuspiciousIPs.
     */
    @GetMapping("/suspicious-ip")
    public ResponseEntity<List<SuspiciousIP>> getSuspiciousIp() {
        return ResponseEntity.ok(suspiciousIpService.showAllSuspiciousIps());
    }

    /**
     * Deletes a suspicious IP by the provided IP address and returns the deletion status.
     *
     * @param ip The IP address to be deleted.
     * @return ResponseEntity containing the status of the deletion.
     */
    @DeleteMapping("/suspicious-ip/{ip}")
    public ResponseEntity<StatusDTO> deleteSuspiciousIp(@PathVariable String ip) {
        return ResponseEntity.ok(suspiciousIpService.deleteSuspiciousIp(ip));
    }

    /**
     * Adds a stolen card to the database based on the provided StolenCard and returns the added card.
     *
     * @param stolenCard The StolenCard containing card details.
     * @return ResponseEntity containing the added StolenCard.
     */
    @PostMapping("/stolencard")
    public ResponseEntity<StolenCard> addStolenCard(@RequestBody @Valid StolenCard stolenCard) {
        return ResponseEntity.ok(stolenCardService.addNewCard(stolenCard));
    }

    /**
     * Retrieves a list of all stolen cards.
     *
     * @return ResponseEntity containing a list of StolenCards.
     */
    @GetMapping("/stolencard")
    public ResponseEntity<List<StolenCard>> getStolenCard() {
        return ResponseEntity.ok(stolenCardService.showAllCards());
    }

    /**
     * Deletes a stolen card by the provided card number and returns the deletion status.
     *
     * @param number The card number to be deleted.
     * @return ResponseEntity containing the status of the deletion.
     */
    @DeleteMapping("/stolencard/{number}")
    public ResponseEntity<StatusDTO> deleteStolenCard(@PathVariable String number) {
        return ResponseEntity.ok(stolenCardService.deleteCard(number));
    }
}
