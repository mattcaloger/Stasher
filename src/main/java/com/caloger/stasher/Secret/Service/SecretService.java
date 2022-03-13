package com.caloger.stasher.Secret.Service;

import com.caloger.stasher.Secret.Model.SecretModel;

public interface SecretService {
    public SecretModel createSecret(String message);
    public String readSecuredByCode(String code) throws Exception;
    void deleteSecret(Long id);

    boolean checkIfSecuredExists(String code);
}
