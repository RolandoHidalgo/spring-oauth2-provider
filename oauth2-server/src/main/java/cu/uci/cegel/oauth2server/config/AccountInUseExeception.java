package cu.uci.cegel.oauth2server.config;

import org.springframework.security.authentication.AccountStatusException;

public class AccountInUseExeception extends AccountStatusException {
    public AccountInUseExeception(String msg) {
        super(msg);
    }

    public AccountInUseExeception(String msg, Throwable t) {
        super(msg, t);
    }
}
