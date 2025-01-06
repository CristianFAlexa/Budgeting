package com.playground.budgeting.service;

import com.playground.budgeting.entity.Owner;
import com.playground.budgeting.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;

    public List<Owner> getAll() {
        return ownerRepository.findAll();
    }

    public Owner getById(Long id) {
        return ownerRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Owner with id: " + id + " not found"));
    }
}
