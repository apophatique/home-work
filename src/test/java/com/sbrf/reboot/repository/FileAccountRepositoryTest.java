package com.sbrf.reboot.repository;

import com.sbrf.reboot.repository.impl.FileAccountRepository;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileAccountRepositoryTest {

    AccountRepository accountRepository;

    @Test
    void onlyPersonalAccounts() throws IOException {
        String filePath = "src/main/resources/Accounts.txt";
        accountRepository = new FileAccountRepository(filePath);

        long clientId = 1L;
        Set<Long> actualAccounts = accountRepository.getAllAccountsByClientId(clientId);

        Set<Long> expected = new HashSet<Long>() {{
            add(111L);
            add(222L);
            add(333L);
        }};

        actualAccounts.forEach(e -> assertTrue(expected.contains(e)));
    }

    @Test
    void failGetAllAccountsByClientId() {
        long clientId = 1L;

        String filePath = "somePath";

        accountRepository = new FileAccountRepository(filePath);

        assertThrows(FileNotFoundException.class, () -> accountRepository.getAllAccountsByClientId(clientId));
    }

    /**
     * Тест для проверки задания на 5+.
     *
     * Перед выполнением убедится, что в Accounts.txt есть клиент с айди 3 и счетом 999
     */
    @Test
    void shouldReplaceAccountClientWithId3() throws IOException {
        String filePath = "src/main/resources/Accounts.txt";
        accountRepository = new FileAccountRepository(filePath);

        long clientId = 3;
        long oldAccount = 999;
        long newAccount = 123321;
        Set<Long> expected = new HashSet<Long>() {{
            add(newAccount);
        }};

        assertTrue(accountRepository.updateAccountNumber(
                clientId,
                oldAccount,
                newAccount
        ));


        Set<Long> actualAccounts = accountRepository.getAllAccountsByClientId(clientId);
        actualAccounts.forEach(e -> assertTrue(expected.contains(e)));
    }
}