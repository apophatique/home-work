package com.sbrf.reboot.atm;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class Cassette<T extends Banknote> {
    final List<T> banknotes;

    public static <T> T getValue() {
        return (T) null;
    }

    public int getCountBanknotes() {
        return banknotes.size();
    }
}
