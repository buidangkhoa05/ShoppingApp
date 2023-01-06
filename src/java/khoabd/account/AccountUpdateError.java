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
public class AccountUpdateError implements Serializable {

    private String passwordLengthError;
    private String fullnameLenghtError;

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
    
   
}
