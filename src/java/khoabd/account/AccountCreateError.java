/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoabd.account;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class AccountCreateError implements Serializable {

    private String userNammeLengthError;
    private String passwordLengthError;
    private String fullnameLenghtError;
    private String cofirmNotMatch;
    private String usernameIsExisted;

    public String getUserNammeLengthError() {
        return userNammeLengthError;
    }

    public void setUserNammeLengthError(String userNammeLengthError) {
        this.userNammeLengthError = userNammeLengthError;
    }

    public String getPasswordLengthError() {
        return passwordLengthError;
    }

    public void setPasswordLengthError(String passwordLengthError) {
        this.passwordLengthError = passwordLengthError;
    }

    public String getFullnameLenghtError() {
        return fullnameLenghtError;
    }

    public void setFullnameLenghtError(String fullnameLenghtError) {
        this.fullnameLenghtError = fullnameLenghtError;
    }

    public String getCofirmNotMatch() {
        return cofirmNotMatch;
    }

    public void setCofirmNotMatch(String cofirmNotMatch) {
        this.cofirmNotMatch = cofirmNotMatch;
    }

    public String getUsernameIsExisted() {
        return usernameIsExisted;
    }

    public void setUsernameIsExisted(String usernameIsExisted) {
        this.usernameIsExisted = usernameIsExisted;
    }
    
}
