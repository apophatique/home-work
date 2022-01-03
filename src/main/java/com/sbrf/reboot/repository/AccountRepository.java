package com.sbrf.reboot.repository;

import java.io.IOException;
import java.util.Set;

public interface AccountRepository {
    Set<Long> getAllAccountsByClientId(final long clientId) throws IOException;
    boolean updateAccountNumber(final long clientId, final long numberOld, final long numberNew) throws IOException;
}
