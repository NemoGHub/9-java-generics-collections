package com.example.task01;

import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Pair<T, U> {
    private final T firstValue;
    private final U secondValue;

    private Pair(T firstValue, U secondValue){
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public static <T, U> Pair<T, U> of(T firstValue, U secondValue) {
        return new Pair<>(firstValue, secondValue);
    }

    public T getFirst(){return firstValue;}
    public U getSecond(){return secondValue;}

    public void ifPresent(BiConsumer<? super T, ? super U> action) {
        if (firstValue != null && secondValue != null) {
            action.accept(firstValue, secondValue);
        }
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair)) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(firstValue, pair.firstValue) && Objects.equals(secondValue, pair.secondValue);
    }

    public int hashCode() {
        return Objects.hash(firstValue, secondValue);
    }
}
