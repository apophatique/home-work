package com.sbrf.reboot.atm.cassettes;

import com.sbrf.reboot.atm.Banknote;
import com.sbrf.reboot.atm.Cassette;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class CassetteTest {

    class OneHundred extends Banknote {
    }

    class OneThousand extends Banknote {
    }

    @Test
    void getCountBanknotes() {
        OneHundred oneHundred = new OneHundred();

        Cassette<OneHundred> cassette = new Cassette<>(new ArrayList<OneHundred>() {{
            add(oneHundred);
//            add(new OneThousand()); //it will not compile
//            add(new Banknote()); //it will not compile
        }});

        Assertions.assertEquals(1, cassette.getCountBanknotes());
    }
}

/**
 * Задание на 5+.
 */
class AdvancedTest {
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