package com.example.springreactivedemo;

import com.example.springreactivedemo.model.Foo;
import com.example.springreactivedemo.resource.FooResource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.scheduler.VirtualTimeScheduler;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;


@ExtendWith(SpringExtension.class)
@WebFluxTest(FooResource.class)
public class FooResouceTest {

    @Autowired
    WebTestClient webTestClient;


    @Test
    public void whenRun_then_Return_StreamOf5FoosWithSequentialIds() {

        String[] possibleFooNames = new String[]{"Shailesh", "Josh", "Loredana", "Eugen", "Roger"};
        FluxExchangeResult<Foo> result = webTestClient.get()
                .uri("/foo")
                .exchange()
                .expectStatus()
                .isOk()
                .returnResult(Foo.class);

        Flux<Foo> fooFlux = result.getResponseBody().take(5);
        StepVerifier.create(fooFlux)
                .expectSubscription()
                .expectNextMatches(foo -> foo.getId().equals(0L))
                .expectNextMatches(foo -> foo.getId().equals(1L))
                .expectNextMatches(foo -> foo.getId().equals(2L))
                .expectNextMatches(foo -> foo.getId().equals(3L))
                .expectNextMatches(foo -> foo.getId().equals(4L))
                .verifyComplete();

    }

    @Test
    public void whenRun_then_Return_StreamOf5Foos_With_FirstElement_With_Id_0_and_Last_With_Id_4() {
        FluxExchangeResult<Foo> result = webTestClient.get()
                .uri("/foo")
                .exchange()
                .expectStatus()
                .isOk()
                .returnResult(Foo.class);

        Flux<Foo> fooFlux = result.getResponseBody().take(5);
        StepVerifier.create(fooFlux)
            .expectSubscription()
            .expectNextMatches(foo -> foo.getId().equals(0L))
            .expectNextCount(3)
            .expectNextMatches(foo -> foo.getId().equals(4L))
            .verifyComplete();
    }
    @Test
    public void whenRun_then_Return_StreamOf5FoosWithNamesFromPossibleValue() {

        final String[] possibleFooNames = new String[]{"Shailesh", "Josh", "Loredana", "Eugen", "Roger"};
        final List<String> nameList = Arrays.asList(possibleFooNames);
        final Predicate<Foo> nameChecker = foo -> nameList.contains(foo.getName());
        FluxExchangeResult<Foo> result = webTestClient.get()
                .uri("/foo")
                .exchange()
                .expectStatus()
                .isOk()
                .returnResult(Foo.class);

        Flux<Foo> fooFlux = result.getResponseBody().take(5);
        StepVerifier.create(fooFlux)
                .expectNextMatches(nameChecker)
                .expectNextMatches(nameChecker)
                .expectNextMatches(nameChecker)
                .expectNextMatches(nameChecker)
                .expectNextMatches(nameChecker)
                .verifyComplete();
    }


 }
