/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.safemoney.dto;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ObuliKarthikeyan
 */
@Entity
@Table(name = "login")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LoginDTO.findAll", query = "SELECT l FROM LoginDTO l"),
    @NamedQuery(name = "LoginDTO.findByMemberId", query = "SELECT l FROM LoginDTO l WHERE l.memberId = :memberId"),
    @NamedQuery(name = "LoginDTO.findByUserName", query = "SELECT l FROM LoginDTO l WHERE l.userName = :userName"),
    @NamedQuery(name = "LoginDTO.findByPassword", query = "SELECT l FROM LoginDTO l WHERE l.password = :password"),
    @NamedQuery(name = "LoginDTO.findBySiteKey", query = "SELECT l FROM LoginDTO l WHERE l.siteKey = :siteKey"),
    @NamedQuery(name = "LoginDTO.findByIsAccountNonLocked", query = "SELECT l FROM LoginDTO l WHERE l.isAccountNonLocked = :isAccountNonLocked"),
    @NamedQuery(name = "LoginDTO.findByIsEnabled", query = "SELECT l FROM LoginDTO l WHERE l.isEnabled = :isEnabled"),
    @NamedQuery(name = "LoginDTO.findByFailedAttemptCount", query = "SELECT l FROM LoginDTO l WHERE l.failedAttemptCount = :failedAttemptCount"),
    @NamedQuery(name = "LoginDTO.findByLastLoginDate", query = "SELECT l FROM LoginDTO l WHERE l.lastLoginDate = :lastLoginDate"),
    @NamedQuery(name = "LoginDTO.findByOtp", query = "SELECT l FROM LoginDTO l WHERE l.otp = :otp"),
    @NamedQuery(name = "LoginDTO.findByOtpDate", query = "SELECT l FROM LoginDTO l WHERE l.otpDate = :otpDate")})
public class LoginDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "member_id")
    private Integer memberId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "user_name")
    private String userName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "site_key")
    private String siteKey;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isAccountNonLocked")
    private boolean isAccountNonLocked;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isEnabled")
    private boolean isEnabled;
    @Basic(optional = false)
    @NotNull
    @Column(name = "failedAttemptCount")
    private int failedAttemptCount;
    @Column(name = "lastLoginDate")
    @Temporal(TemporalType.DATE)
    private Date lastLoginDate;
    @Column(name = "otp")
    private long otp;
    @Column(name = "otp_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date otpDate;
    @JoinColumn(name = "member_id", referencedColumnName = "member_id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private UserDTO userDTO;

    public LoginDTO() {
    }

    public LoginDTO(Integer memberId) {
        this.memberId = memberId;
    }

    public LoginDTO(Integer memberId, String userName, String password, String siteKey, boolean isAccountNonLocked, boolean isEnabled, int failedAttemptCount) {
        this.memberId = memberId;
        this.userName = userName;
        this.password = password;
        this.siteKey = siteKey;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isEnabled = isEnabled;
        this.failedAttemptCount = failedAttemptCount;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSiteKey() {
        return siteKey;
    }

    public void setSiteKey(String siteKey) {
        this.siteKey = siteKey;
    }

    public boolean getIsAccountNonLocked() {
        return isAccountNonLocked;
    }

    public void setIsAccountNonLocked(boolean isAccountNonLocked) {
        this.isAccountNonLocked = isAccountNonLocked;
    }

    public boolean getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public int getFailedAttemptCount() {
        return failedAttemptCount;
    }

    public void setFailedAttemptCount(int failedAttemptCount) {
        this.failedAttemptCount = failedAttemptCount;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public long getOtp() {
        return otp;
    }

    public void setOtp(long otp) {
        this.otp = otp;
    }

    public Date getOtpDate() {
        return otpDate;
    }

    public void setOtpDate(Date otpDate) {
        this.otpDate = otpDate;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (memberId != null ? memberId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LoginDTO)) {
            return false;
        }
        LoginDTO other = (LoginDTO) object;
        if ((this.memberId == null && other.memberId != null) || (this.memberId != null && !this.memberId.equals(other.memberId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.asu.safemoney.dto.LoginDTO[ memberId=" + memberId + " ]";
    }
    
}