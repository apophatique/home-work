package com.sbrf.reboot.atm.cassettes;

import java.util.HashMap;
import java.util.Map;

/**
 * Задание на 5+.
 */
public class AdvancedTest {
    abstract class Vehicle {
    }

    abstract class Car extends Vehicle {
    }

    public class ObjectStore<K, V> {
        void putAll(Map<? extends K, ? extends V> entries) {

        }
    }

    public void main(String[] args) {
        ObjectStore<String, Vehicle> store = new ObjectStore<>();

        Map<String, Car> cars = new HashMap<>();
        store.putAll(cars);
    }
}
