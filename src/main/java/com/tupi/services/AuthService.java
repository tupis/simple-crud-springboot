package com.tupi.services;

import com.tupi.data.vo.v1.security.AccountCredentialsVO;
import com.tupi.data.vo.v1.security.TokenVO;

public interface AuthService {
    TokenVO signIn(AccountCredentialsVO data);

    TokenVO refreshToken(String refreshToken);

}
