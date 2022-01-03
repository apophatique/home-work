package com.sbrf.reboot.repository.impl;

import com.sbrf.reboot.repository.AccountRepository;
import com.sbrf.reboot.repository.impl.util.JsonStringHandler;
import lombok.RequiredArgsConstructor;

import java.io.*;
import java.util.Set;

/**
 * Реализация {@link AccountRepository} для работы с текстовым файлом.
 */
@RequiredArgsConstructor
public class FileAccountRepository implements AccountRepository {
    final String filePath;

    /**
     * Возврат всех аккаунтов клиента по заданному айди.
     *
     * @param clientId - айди клиента.
     * @return сет всех счетов клиента.
     */
    @Override
    public Set<Long> getAllAccountsByClientId(final long clientId) throws IOException {
        return JsonStringHandler.getClientAccountsSet(
                filePath,
                clientId
        );
    }

    /**
     * Обновление счета у клиента
     *
     * @param clientId  - айди клиента
     * @param numberOld - счет, который нужно заменить
     * @param numberNew - новый счет
     * @return true если такой счёт был, false - если нет
     * @throws IOException на случай ошибки создания {@link BufferedReader}
     */
    @Override
    public boolean updateAccountNumber(long clientId, long numberOld, long numberNew) throws IOException {
        return JsonStringHandler.updateClientAccount(
                filePath,
                clientId,
                numberOld,
                numberNew
        );
    }
}
