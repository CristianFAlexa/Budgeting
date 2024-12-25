package com.playground.budgeting.api;

import com.playground.budgeting.entity.Owner;
import com.playground.budgeting.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class OwnerApi {

    private final OwnerRepository ownerRepository;

    @QueryMapping
    Iterable<Owner> owners() {
        return ownerRepository.findAll();
    }
}
