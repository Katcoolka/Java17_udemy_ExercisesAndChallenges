package dev.lpa;
@FunctionalInterface //if valid, it confirms that the interface has only one abstract method
public interface Operation <T>{

    T operate(T value1, T value2);
}
