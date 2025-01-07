package com.playground.budgeting.subscription;

import com.playground.budgeting.entity.Budget;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import static reactor.core.publisher.Sinks.EmitFailureHandler.FAIL_FAST;
import static reactor.util.concurrent.Queues.SMALL_BUFFER_SIZE;

@Component
public class BudgetPublisher {

    private final Sinks.Many<Budget> sink;

    public BudgetPublisher() {
        this.sink = Sinks.many().multicast().onBackpressureBuffer(SMALL_BUFFER_SIZE, false);
    }

    public void publish(Budget budget) {
        sink.emitNext(budget, FAIL_FAST);
    }

    public Flux<Budget> getBudgetPublisherFor(Long ownerId) {
        return sink.asFlux()
            .filter(budget -> budget.getOwner().getId().equals(ownerId));
    }
}
