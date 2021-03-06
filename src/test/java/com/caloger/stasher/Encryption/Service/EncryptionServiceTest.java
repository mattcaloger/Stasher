package com.caloger.stasher.Encryption.Service;

import com.caloger.stasher.Encryption.Model.EncryptedPropertiesModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest
class EncryptionServiceTest {

    EncryptionService encryptionService;

    @Autowired
    public EncryptionServiceTest(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    @DisplayName("Given message and passsword Encrypt message")
    @Test
    void encryptBundle() throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {
        //given
        String message = "Lorem ipsum dolor sit amet consectetur adipisicing elit. Officia, mollitia vel voluptatem dolores velit praesentium quibusdam dignissimos quam tempore illum veritatis numquam error sint accusamus cum deleniti earum soluta iusto.";
        String password = "Test";

        // when
        EncryptedPropertiesModel encryptedProperties = encryptionService.encryptMessage(message, password);

        // then
        then(encryptedProperties.getEncryptedMessage()).isNotBlank();
        then(encryptedProperties.getEncryptedMessage()).isNotEmpty();
        then(encryptedProperties.getEncryptedMessage()).isNotNull();
    }

    @DisplayName("Given message and password, Decrypt with correct password")
    @Test
    void decryptMessage() throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {
        //given
        String message = "Lorem ipsum dolor sit amet consectetur adipisicing elit. Officia, mollitia vel voluptatem dolores velit praesentium quibusdam dignissimos quam tempore illum veritatis numquam error sint accusamus cum deleniti earum soluta iusto.";
        String password = "Test";

        // when
        EncryptedPropertiesModel encryptedProperties = encryptionService.encryptMessage(message, password);
        String unEncryptedString = encryptionService.decryptMessage(password, encryptedProperties);

        // then
        then(unEncryptedString).isEqualTo(message);
    }

    @DisplayName("Given a message, password, and incorrect password, when encrypting, then decryption should throw error")
    @Test
    void decryptMessageWithWrongPassword() throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {
        //given
        String message = "Lorem ipsum dolor sit amet consectetur adipisicing elit. Officia, mollitia vel voluptatem dolores velit praesentium quibusdam dignissimos quam tempore illum veritatis numquam error sint accusamus cum deleniti earum soluta iusto.";
        String password = "Test";
        String incorrectpassword = "wrong";

        // when
        EncryptedPropertiesModel encryptedProperties = encryptionService.encryptMessage(message, password);

        // then
        assertThatExceptionOfType(BadPaddingException.class)
                .isThrownBy(() -> encryptionService.decryptMessage(incorrectpassword, encryptedProperties));
    }
}