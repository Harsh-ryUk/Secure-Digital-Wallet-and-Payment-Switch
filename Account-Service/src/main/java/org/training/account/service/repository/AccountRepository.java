package org.training.account.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;
import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import org.training.account.service.model.AccountType;
import org.training.account.service.model.entity.Account;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    /**
     * Find an account by user ID and account type.
     *
     * @param userId The ID of the user.
     * @param accountType The type of the account.
     * @return An optional containing the account if found, or empty if not found.
     */
    Optional<Account> findAccountByUserIdAndAccountType(Long userId, AccountType accountType);

    /**
     * Find an account by its account number.
     *
     * @param accountNumber The account number to search for.
     * @return An optional containing the found account, or an empty optional if no account was found.
     */
    Optional<Account> findAccountByAccountNumber(String accountNumber);

    /**
     * Finds an account by user ID.
     *
     * @param userId the ID of the user
     * @return an optional account object
     */
    Optional<Account> findAccountByUserId(Long userId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "3000")})
    Optional<Account> findByAccountNumber(String accountNumber);
}
