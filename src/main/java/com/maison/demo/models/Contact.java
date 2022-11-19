package com.maison.demo.models;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.*;

@Data
public class Contact {
    @NotBlank(message = "Name is required.")
    @Size(min = 3, message = "Name must be at least 3 characters long.")
    private String name;

    @NotBlank(message = "Mobile number is required.")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits.")
    private String mobileNum;

    @NotBlank(message = "Email is required.")
    @Email(message = "Email is invalid.")
    private String email;

    @NotBlank(message = "Subject is required.")
    @Size(min = 5, message = "Subject must be at least 5 characters long.")
    private String subject;

    @NotBlank(message = "Message is required.")
    @Size(min = 10, message = "Message must be at least 10 characters long.")
    private String message;

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getMobileNum() {
//        return mobileNum;
//    }
//
//    public void setMobileNum(String mobileNum) {
//        this.mobileNum = mobileNum;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getSubject() {
//        return subject;
//    }
//
//    public void setSubject(String subject) {
//        this.subject = subject;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    @Override
//    public String toString() {
//        return "Contact{" +
//                "name='" + name + '\'' +
//                ", mobileNum='" + mobileNum + '\'' +
//                ", email='" + email + '\'' +
//                ", subject='" + subject + '\'' +
//                ", message='" + message + '\'' +
//                '}';
//    }
}
