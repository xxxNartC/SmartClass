package model;

import java.util.Date;

public class AccountReport {
    private int reportId;
    private int accountId;
    private int reportedAccountId;
    private String reportType;
    private String reportDescription;
    private Date reportDate;
    private int status;
    private String reporterUsername;
    private String reportedUsername;
 private String reportedAccountName; // New field to store the name of the reported account
    private String reportByName;

    public AccountReport() {
    }

    
    public AccountReport(int reportId, int accountId, int reportedAccountId, String reportType, String reportDescription, Date reportDate, int status) {
        this.reportId = reportId;
        this.accountId = accountId;
        this.reportType = reportType;
        this.reportDescription = reportDescription;
        this.reportDate = reportDate;
        this.status = status;
        this.reportedAccountId = reportedAccountId;
    }

    public AccountReport(int reportId, int accountId, int reportedAccountId, String reportType, String reportDescription, Date reportDate, int status, String reporterUsername, String reportedUsername) {
        this.reportId = reportId;
        this.accountId = accountId;
        this.reportedAccountId = reportedAccountId;
        this.reportType = reportType;
        this.reportDescription = reportDescription;
        this.reportDate = reportDate;
        this.status = status;
        this.reporterUsername = reporterUsername;
        this.reportedUsername = reportedUsername;
    }
    
    
    public AccountReport( int accountId, int reportedAccountId, String reportType, String reportDescription, Date reportDate, int status) {
        this.accountId = accountId;
        this.reportedAccountId = reportedAccountId;
        this.reportType = reportType;
        this.reportDescription = reportDescription;
        this.reportDate = reportDate;
        this.status = status;
    }

    
    public int getReportedAccountId() {
        return reportedAccountId;
    }

    public String getReporterUsername() {
        return reporterUsername;
    }

    public String getReportedUsername() {
        return reportedUsername;
    }

    
    public int getReportId() {
        return reportId;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getReportType() {
        return reportType;
    }

    public String getReportDescription() {
        return reportDescription;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public int getStatus() {
        return status;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public void setReportDescription(String reportDescription) {
        this.reportDescription = reportDescription;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setReportedAccountId(int reportedAccountId) {
        this.reportedAccountId = reportedAccountId;
    }

    public void setReporterUsername(String reporterUsername) {
        this.reporterUsername = reporterUsername;
    }

    public void setReportedUsername(String reportedUsername) {
        this.reportedUsername = reportedUsername;
    }

    public String getReportedAccountName() {
        return reportedAccountName;
    }

    public void setReportedAccountName(String reportedAccountName) {
        this.reportedAccountName = reportedAccountName;
    }

    public String getReportByName() {
        return reportByName;
    }

    public void setReportByName(String reportByName) {
        this.reportByName = reportByName;
    }

    @Override
    public String toString() {
        return "AccountReport{" + "reportId=" + reportId + ", accountId=" + accountId + ", reportedAccountId=" + reportedAccountId + ", reportType=" + reportType + ", reportDescription=" + reportDescription + ", reportDate=" + reportDate + ", status=" + status + ", reporterUsername=" + reporterUsername + ", reportedUsername=" + reportedUsername + '}';
    }

    

    
    
}
   