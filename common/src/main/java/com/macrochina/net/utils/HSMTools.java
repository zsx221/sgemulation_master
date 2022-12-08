package com.macrochina.net.utils;


import com.cavium.cfm2.CFM2Exception;
import com.cavium.cfm2.LoginManager;

import java.io.IOException;
import java.security.Key;
import java.security.Security;

public class HSMTools {
    public static void login(String user,String password,String partition,String method) throws Exception {
        boolean res=false;
        try {
            Security.addProvider(new com.cavium.provider.CaviumProvider());
        } catch (IOException ex) {
            return ;
        }
        if (null == method) {
            return ;
        }
        if (method.equals("explicit") || method.equals("system-properties")) {
            if (null == user || null == password || null == partition) {
                return ;
            }
        }
        if (method.equals("explicit")) {
            loginWithExplicitCredentials(user, password, partition);
        } else if (method.equals("system-properties")) {
            loginUsingJavaProperties(user, password, partition);
        } else if (method.equals("environment")) {
            loginWithEnvVariables();
        }
    }
    /**
     * The explicit login method allows users to pass credentials to the Cluster manually. If you obtain credentials
     * from a provider during runtime, this method allows you to login.
     * @param user Name of CU user in HSM
     * @param pass Password for CU user.
     * @param partition HSM ID
     */
    public static void loginWithExplicitCredentials(String user, String pass, String partition) {
        LoginManager lm = LoginManager.getInstance();
        try {
            lm.login(partition, user, pass);
            System.out.printf("\nLogin successful!\n\n");
        } catch (CFM2Exception e) {
            if (CFM2Exception.isAuthenticationFailure(e)) {
                System.out.printf("\nDetected invalid credentials\n\n");
            }

            e.printStackTrace();
        }
    }

    public static boolean isLoggedIn(){
        LoginManager lm = LoginManager.getInstance();
        System.out.printf("isLoggedIn()"+lm.isLoggedIn());
        return lm.isLoggedIn();
    }
    /**
     * One implicit login method is to set credentials through system properties. This can be done using
     * System.setProperty(), or credentials can be read from a properties file. When implicit credentials are used,
     * you do not have to use the LoginManager. The login will be done automatically for you.
     * @param user Name of CU user in HSM
     * @param pass Password for CU user.
     * @param partition HSM ID
     */
    public static void loginUsingJavaProperties(String user, String pass, String partition) throws Exception {
        System.setProperty("HSM_PARTITION", partition);
        System.setProperty("HSM_USER", user);
        System.setProperty("HSM_PASSWORD", pass);

        Key aesKey = null;

        try {
            aesKey = SymmetricKeys.generateAESKey(256, "Implicit Java Properties Login Key");
        } catch (Exception e) {
            if (CFM2Exception.isAuthenticationFailure(e)) {
                System.out.printf("\nDetected invalid credentials\n\n");
                e.printStackTrace();
                return;
            }

            throw e;
        }

        assert(aesKey != null);
        System.out.printf("\nLogin successful!\n\n");
    }

    /**
     * One implicit login method is to use environment variables. To use this method, you must set the following
     * environment variables before running the test:
     * HSM_USER
     * HSM_PASSWORD
     * HSM_PARTITION
     *
     * The LoginManager is not required to use implicit credentials. When you try to perform operations, the login
     * will be done automatically.
     */
    public static void loginWithEnvVariables() throws Exception {
        Key aesKey = null;

        try {
            aesKey = SymmetricKeys.generateAESKey(256, "Implicit Java Properties Login Key");
        } catch (Exception e) {
            if (CFM2Exception.isAuthenticationFailure(e)) {
                System.out.printf("\nDetected invalid credentials\n\n");
                e.printStackTrace();
                return;
            }

            throw e;
        }

        System.out.printf("\nLogin successful!\n\n");
    }

}
