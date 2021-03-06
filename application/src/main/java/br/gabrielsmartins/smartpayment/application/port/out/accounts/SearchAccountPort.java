package br.gabrielsmartins.smartpayment.application.port.out.accounts;

import br.gabrielsmartins.smartpayment.application.domain.accounts.Account;
import br.gabrielsmartins.smartpayment.application.domain.enums.AccountTypeEnum;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SearchAccountPort {

    Optional<Account> findById(UUID id);

    List<Account> findByCustomerId(String customerId);

    List<Account> findByType(AccountTypeEnum accountTypeEnum);

}
