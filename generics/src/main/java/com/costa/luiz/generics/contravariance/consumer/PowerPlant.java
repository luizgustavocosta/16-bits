package com.costa.luiz.generics.contravariance.consumer;

import com.costa.luiz.generics.contravariance.hierarchy.SourceOfEnergy;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.function.Consumer;

@Slf4j
@AllArgsConstructor
public class PowerPlant implements CustomConsumer<SourceOfEnergy> {

//    @Override
//    public void apply(List<? super SourceOfEnergy> t) {
//        log.info(t.toString());
//    }

//    @Override
//    public void apply(Consumer<? super SourceOfEnergy> consumer, SourceOfEnergy source) {
//        log.info("Time to generate energy ╰( ͡° ͜ʖ ͡° )");
//        consumer.accept(source);
//    }
}
