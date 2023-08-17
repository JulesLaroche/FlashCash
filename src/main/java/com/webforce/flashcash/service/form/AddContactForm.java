package com.webforce.flashcash.service.form;

import lombok.Data;

@Data
public class AddContactForm {
    private String email;
    public String getEmail() {
        return email;
    }
}
