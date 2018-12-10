package me.ninjasul.jpa.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    //@Query(nativeQuery = true, value="SELECT * FROM ACCOUNT WHERE username='{0}'")
    Optional<Account> findByUsername(String username);
}