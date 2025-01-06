package com.playground.budgeting.service;

import com.playground.budgeting.dto.OwnerInput;
import com.playground.budgeting.entity.Owner;
import com.playground.budgeting.entity.type.CurrencyType;
import com.playground.budgeting.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OwnerService {

    private final OwnerRepository ownerRepository;

    public List<Owner> getAll() {
        return ownerRepository.findAll();
    }

    public Owner getById(Long id) {
        return ownerRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Owner with id: " + id + " not found"));
    }

    @Transactional
    public Owner addOwner(OwnerInput owner) {
        final var newOwner = Owner.builder()
            .name(owner.name())
            .email(owner.email())
            .currency(CurrencyType.valueOf(owner.currency()))
            .build();

        return ownerRepository.save(newOwner);
    }
}
