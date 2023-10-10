package com.pet.antifraud.Service;


import com.pet.antifraud.DTO.StatusDTO;
import com.pet.antifraud.Exception.BadRequestException;
import com.pet.antifraud.Exception.EntityAlreadyExistsException;
import com.pet.antifraud.Exception.NotFoundException;
import com.pet.antifraud.Model.SuspiciousIP;
import com.pet.antifraud.Repository.SuspiciousIpRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SuspiciousIpService {

    private final SuspiciousIpRepository suspiciousIpRepository;

    public SuspiciousIpService(SuspiciousIpRepository suspiciousIpRepository) {
        this.suspiciousIpRepository = suspiciousIpRepository;
    }

    /**
     * Adds a suspicious IP address to the repository.
     *
     * @param ip The IP address to be added.
     * @return The added SuspiciousIP entity.
     * @throws BadRequestException        If the provided IP address is invalid.
     * @throws EntityAlreadyExistsException If a SuspiciousIP entity with the same IP address already exists.
     */
    public SuspiciousIP addSuspiciousIP(SuspiciousIP ip) {
        if (isInvalidIp(ip.getIp())) {
            throw new BadRequestException();
        }
        if (suspiciousIpRepository.findSuspiciousIPByIp(ip.getIp()).isPresent()) {
            throw new EntityAlreadyExistsException();
        }
        suspiciousIpRepository.save(ip);
        return suspiciousIpRepository.findSuspiciousIPByIp(ip.getIp()).get();
    }

    /**
     * Checks if an IP address is invalid.
     *
     * @param ip The IP address to be checked.
     * @return True if the IP address is invalid, false otherwise.
     */
    private boolean isInvalidIp(String ip) {
        String[] ipSplitByDot = ip.split("\\.");
        if (ipSplitByDot.length != 4) {
            return true;
        }
        try {
            for (String s : ipSplitByDot) {
                int i = Integer.parseInt(s);
                if (i < 0 || i > 255) {
                    return true;
                }
            }
        } catch (NumberFormatException nfe) {
            System.err.println(nfe.getMessage());
            return true;
        }
        return false;
    }

    /**
     * Retrieves a list of all suspicious IP addresses.
     *
     * @return A list of SuspiciousIP entities.
     */
    public List<SuspiciousIP> showAllSuspiciousIps() {
        return suspiciousIpRepository.findAll();
    }

    /**
     * Deletes a suspicious IP address from the repository.
     *
     * @param ip The IP address to be deleted.
     * @return A StatusDTO indicating the status of the operation.
     * @throws BadRequestException If the provided IP address is invalid.
     * @throws NotFoundException  If no SuspiciousIP entity with the provided IP address is found.
     */
    public StatusDTO deleteSuspiciousIp(String ip) {
        if (isInvalidIp(ip)) {
            throw new BadRequestException();
        }
        Optional<SuspiciousIP> suspiciousIPOptional = suspiciousIpRepository.findSuspiciousIPByIp(ip);
        if (suspiciousIPOptional.isEmpty()) {
            throw new NotFoundException();
        }
        suspiciousIpRepository.delete(suspiciousIPOptional.get());
        return new StatusDTO(String.format("IP %s successfully removed!", ip));
    }
}
