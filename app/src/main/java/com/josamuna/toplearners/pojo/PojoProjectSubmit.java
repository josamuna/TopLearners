package com.josamuna.toplearners.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PojoProjectSubmit {
    @SerializedName("entry.1877115667")
    @Expose
    private String firstName;
    @SerializedName("entry.2006916086")
    @Expose
    private String lastName;
    @SerializedName("entry.1824927963")
    @Expose
    private String emailAddress;
    @SerializedName("entry.284483984")
    @Expose
    private String linkGitHub;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getLinkGitHub() {
        return linkGitHub;
    }

    public void setLinkGitHub(String linkGitHub) {
        this.linkGitHub = linkGitHub;
    }
}
