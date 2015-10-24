/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.safemoney.dto;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ObuliKarthikeyan
 */
@Entity
@Table(name = "account")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AccountDTO.findAll", query = "SELECT a FROM AccountDTO a"),
    @NamedQuery(name = "AccountDTO.findByAccountNo", query = "SELECT a FROM AccountDTO a WHERE a.accountNo = :accountNo"),
    @NamedQuery(name = "AccountDTO.findByAmount", query = "SELECT a FROM AccountDTO a WHERE a.amount = :amount"),
    @NamedQuery(name = "AccountDTO.findByIsActive", query = "SELECT a FROM AccountDTO a WHERE a.isActive = :isActive")})
public class AccountDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "account_no")
    private Long accountNo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount")
    private Double amount;
    @Size(max = 5)
    @Column(name = "is_active")
    private String isActive;
    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    @ManyToOne(optional = false)
    private UserDTO memberId;

    public AccountDTO() {
    }

    public AccountDTO(Long accountNo) {
        this.accountNo = accountNo;
    }

    public Long getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(Long accountNo) {
        this.accountNo = accountNo;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public UserDTO getMemberId() {
        return memberId;
    }

    public void setMemberId(UserDTO memberId) {
        this.memberId = memberId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accountNo != null ? accountNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccountDTO)) {
            return false;
        }
        AccountDTO other = (AccountDTO) object;
        if ((this.accountNo == null && other.accountNo != null) || (this.accountNo != null && !this.accountNo.equals(other.accountNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.asu.safemoney.dto.AccountDTO[ accountNo=" + accountNo + " ]";
    }
    
}
