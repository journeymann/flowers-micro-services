package com.flowers.microservice.tax.invoke;

@FunctionalInterface
public interface TriConsumer<T1,T2,T3> {
    void apply(T1 arg1, T2 arg2, T3 arg3);
}