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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "transaction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TransactionDTO.findAll", query = "SELECT t FROM TransactionDTO t"),
    @NamedQuery(name = "TransactionDTO.findByTransactionId", query = "SELECT t FROM TransactionDTO t WHERE t.transactionId = :transactionId"),
    @NamedQuery(name = "TransactionDTO.findByFromAccount", query = "SELECT t FROM TransactionDTO t WHERE t.fromAccount = :fromAccount"),
    @NamedQuery(name = "TransactionDTO.findByToAccount", query = "SELECT t FROM TransactionDTO t WHERE t.toAccount = :toAccount"),
    @NamedQuery(name = "TransactionDTO.findByDate", query = "SELECT t FROM TransactionDTO t WHERE t.date = :date"),
    @NamedQuery(name = "TransactionDTO.findByAmount", query = "SELECT t FROM TransactionDTO t WHERE t.amount = :amount"),
    @NamedQuery(name = "TransactionDTO.findByStatus", query = "SELECT t FROM TransactionDTO t WHERE t.status = :status"),
    @NamedQuery(name = "TransactionDTO.findByTransactionType", query = "SELECT t FROM TransactionDTO t WHERE t.transactionType = :transactionType"),
    @NamedQuery(name = "TransactionDTO.findByIsCritical", query = "SELECT t FROM TransactionDTO t WHERE t.isCritical = :isCritical"),
    @NamedQuery(name = "TransactionDTO.findByIsAuthorized", query = "SELECT t FROM TransactionDTO t WHERE t.isAuthorized = :isAuthorized"),
    @NamedQuery(name = "TransactionDTO.findByProcessedDate", query = "SELECT t FROM TransactionDTO t WHERE t.processedDate = :processedDate")})
public class TransactionDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "transaction_id")
    private Long transactionId;
    @Column(name = "from_account")
    private long fromAccount;
    @Column(name = "to_account")
    private long toAccount;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount")
    private Double amount;
    @Size(max = 45)
    @Column(name = "status")
    private String status;
    @Size(max = 45)
    @Column(name = "transaction_type")
    private String transactionType;
    @Column(name = "is_critical")
    private Boolean isCritical;
    @Column(name = "is_authorized")
    private Boolean isAuthorized;
    @Column(name = "processed_date")
    @Temporal(TemporalType.DATE)
    private Date processedDate;
    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    @ManyToOne(optional = false)
    private UserDTO memberId;

    public TransactionDTO() {
    }

    public TransactionDTO(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public long getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(long fromAccount) {
        this.fromAccount = fromAccount;
    }

    public long getToAccount() {
        return toAccount;
    }

    public void setToAccount(long toAccount) {
        this.toAccount = toAccount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Boolean getIsCritical() {
        return isCritical;
    }

    public void setIsCritical(Boolean isCritical) {
        this.isCritical = isCritical;
    }

    public Boolean getIsAuthorized() {
        return isAuthorized;
    }

    public void setIsAuthorized(Boolean isAuthorized) {
        this.isAuthorized = isAuthorized;
    }

    public Date getProcessedDate() {
        return processedDate;
    }

    public void setProcessedDate(Date processedDate) {
        this.processedDate = processedDate;
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
        hash += (transactionId != null ? transactionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransactionDTO)) {
            return false;
        }
        TransactionDTO other = (TransactionDTO) object;
        if ((this.transactionId == null && other.transactionId != null) || (this.transactionId != null && !this.transactionId.equals(other.transactionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.asu.safemoney.dto.TransactionDTO[ transactionId=" + transactionId + " ]";
    }
    
}
