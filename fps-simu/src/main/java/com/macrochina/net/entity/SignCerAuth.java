package com.macrochina.net.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SignCerAuth {
    private String keyStoreBytesBase64;

    private String keyStoreSignPassword;

    private String privateKey;

    private String privateKeyPassword;
}
