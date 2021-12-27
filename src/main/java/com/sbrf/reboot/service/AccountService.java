package com.sbrf.reboot.service;

import com.sbrf.reboot.repository.AccountRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AccountService {
    @Getter
    final AccountRepository accountRepository;

    public boolean isClientHasContract(final long clientId, final long contractNumber) {
        return accountRepository
                .getAllAccountsByClientId(clientId)
                .contains(contractNumber);
    }
}
