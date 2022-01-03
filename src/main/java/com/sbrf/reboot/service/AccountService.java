package com.sbrf.reboot.service;

import com.sbrf.reboot.repository.AccountRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class AccountService {
    @Getter
    final AccountRepository accountRepository;

    public boolean isClientHasContract(final long clientId, final long contractNumber) throws IOException {
        return accountRepository
                .getAllAccountsByClientId(clientId)
                .contains(contractNumber);
    }
}
