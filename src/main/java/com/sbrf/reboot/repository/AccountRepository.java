package com.sbrf.reboot.repository;

import java.util.Set;

/**
 * Интерфейс
 */
public interface AccountRepository {
    Set<Long> getAllAccountsByClientId(final long clientId);
    boolean clientExists(final long clientId);
}
