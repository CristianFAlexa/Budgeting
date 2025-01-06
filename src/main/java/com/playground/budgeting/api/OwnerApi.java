package com.playground.budgeting.api;

import com.playground.budgeting.dto.OwnerInput;
import com.playground.budgeting.entity.Owner;
import com.playground.budgeting.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class OwnerApi {

    private final OwnerService ownerService;

    @QueryMapping
    public Iterable<Owner> owners() {
        return ownerService.getAll();
    }

    @QueryMapping
    public Owner ownerById(@Argument Long id) {
        return ownerService.getById(id);
    }

    @MutationMapping
    public Owner addOwner(@Argument OwnerInput owner) {
        return ownerService.addOwner(owner);
    }
}
