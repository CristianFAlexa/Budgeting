package com.playground.budgeting.service;

import com.playground.budgeting.dto.SavingsGoalInput;
import com.playground.budgeting.entity.SavingsGoal;
import com.playground.budgeting.repository.OwnerRepository;
import com.playground.budgeting.repository.SavingsGoalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SavingsGoalService {

    private final SavingsGoalRepository savingsGoalRepository;
    private final OwnerRepository ownerRepository;

    @Transactional
    public SavingsGoal addSavingsGoal(SavingsGoalInput savingsGoal) {
        final var owner = ownerRepository.findById(savingsGoal.ownerId())
            .orElseThrow(() -> new IllegalArgumentException("Owner with id: " + savingsGoal.ownerId() + " not found"));

        final var newSavingsGoal = SavingsGoal.builder()
            .owner(owner)
            .name(savingsGoal.name())
            .targetAmount(savingsGoal.targetAmount())
            .savedAmount(savingsGoal.savedAmount())
            .deadline(Instant.parse(savingsGoal.deadline()))
            .build();

        return savingsGoalRepository.save(newSavingsGoal);
    }
}
