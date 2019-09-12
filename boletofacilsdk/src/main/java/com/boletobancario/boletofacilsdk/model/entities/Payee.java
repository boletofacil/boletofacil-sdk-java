package com.boletobancario.boletofacilsdk.model.entities;

import com.boletobancario.boletofacilsdk.model.entities.enums.Category;
import com.boletobancario.boletofacilsdk.model.entities.enums.CompanyType;

public class Payee extends Person {

    private String notificationUrl;
    private String email;
    private String password;
    private String phone;
    private String linesOfBusiness;
    private String tradingName;
    private Person repr;
    private Person accountHolder;
    private BankAccount bankAccount;
    private Category category;
    private CompanyType companyType;
    private Address address;
    private Integer businessAreaId;
    private Boolean emailOptOut;
    private Boolean autoApprove;
    private Boolean autoTransfer;

    private String token;
    private String status;

    public String getNotificationUrl() {
        return notificationUrl;
    }

    public void setNotificationUrl(String notificationUrl) {
        this.notificationUrl = notificationUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLinesOfBusiness() {
        return linesOfBusiness;
    }

    public void setLinesOfBusiness(String linesOfBusiness) {
        this.linesOfBusiness = linesOfBusiness;
    }

    public String getTradingName() {
        return tradingName;
    }

    public void setTradingName(String tradingName) {
        this.tradingName = tradingName;
    }

    public Person getRepr() {
        return repr;
    }

    public void setRepr(Person repr) {
        this.repr = repr;
    }

    public Person getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(Person accountHolder) {
        this.accountHolder = accountHolder;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public CompanyType getCompanyType() {
        return companyType;
    }

    public void setCompanyType(CompanyType companyType) {
        this.companyType = companyType;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getBusinessAreaId() {
        return businessAreaId;
    }

    public void setBusinessAreaId(Integer businessAreaId) {
        this.businessAreaId = businessAreaId;
    }

    public Boolean getEmailOptOut() {
        return emailOptOut;
    }

    public void setEmailOptOut(Boolean emailOptOut) {
        this.emailOptOut = emailOptOut;
    }

    public Boolean getAutoApprove() {
        return autoApprove;
    }

    public void setAutoApprove(Boolean autoApprove) {
        this.autoApprove = autoApprove;
    }

    public Boolean getAutoTransfer() {
        return autoTransfer;
    }

    public void setAutoTransfer(Boolean autoTransfer) {
        this.autoTransfer = autoTransfer;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
