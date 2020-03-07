package com.example.springreactivedemo.resource;

import com.example.springreactivedemo.model.Foo;
import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@RestController
public class FooResource {

    @GetMapping(value = "/foo", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Foo> getFoo() {

        final String[] names = new String []{"Shailesh", "Josh", "Loredana", "Eugen", "Roger"};
        final Random randomGenerator = new Random();
        IntStream streamOfRandomIndexes = IntStream.iterate(0, nextInt -> randomGenerator.nextInt(names.length));
        Stream<String> fooNameStream = streamOfRandomIndexes.mapToObj(nextIndex -> names[nextIndex]);
        Flux<String> fooNames = Flux.fromStream(fooNameStream);

        Flux<Long> fooIds = Flux.interval(Duration.ofSeconds(1));

        return Flux.zip(fooNames,fooIds)
                .map(fooTuple -> new Foo(fooTuple.getT1(),fooTuple.getT2()))
                .delayElements(Duration.ofSeconds(1));
    }

}
