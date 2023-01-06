/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoabd.error;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class CheckoutError implements Serializable{
    private String guestNameLengthError;
    private String phoneNumberLengthError;
    private String addressLengthError;
    private String phonenumberFormatError;
    private String quantityError;
 
    
    public String getGuestNameLengthError() {
        return guestNameLengthError;
    }

    public void setGuestNameLengthError(String guestNameLengthError) {
        this.guestNameLengthError = guestNameLengthError;
    }

    public String getPhoneNumberLengthError() {
        return phoneNumberLengthError;
    }

    public void setPhoneNumberLengthError(String phoneNumberLengthError) {
        this.phoneNumberLengthError = phoneNumberLengthError;
    }

    public String getAddressLengthError() {
        return addressLengthError;
    }

    public void setAddressLengthError(String addressLengthError) {
        this.addressLengthError = addressLengthError;
    }

    public String getPhonenumberFormatError() {
        return phonenumberFormatError;
    }

    public void setPhonenumberFormatError(String phonenumberFormatError) {
        this.phonenumberFormatError = phonenumberFormatError;
    }

    public String getQuantityError() {
        return quantityError;
    }

    public void setQuantityError(String quantityError) {
        this.quantityError = quantityError;
    }
    
}
