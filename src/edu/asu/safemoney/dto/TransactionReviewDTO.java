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
@Table(name = "transaction_review")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TransactionReviewDTO.findAll", query = "SELECT t FROM TransactionReviewDTO t"),
    @NamedQuery(name = "TransactionReviewDTO.findByTransactionReviewId", query = "SELECT t FROM TransactionReviewDTO t WHERE t.transactionReviewId = :transactionReviewId"),
    @NamedQuery(name = "TransactionReviewDTO.findByTransactionId", query = "SELECT t FROM TransactionReviewDTO t WHERE t.transactionId = :transactionId"),
    @NamedQuery(name = "TransactionReviewDTO.findByFromAccount", query = "SELECT t FROM TransactionReviewDTO t WHERE t.fromAccount = :fromAccount"),
    @NamedQuery(name = "TransactionReviewDTO.findByToAccount", query = "SELECT t FROM TransactionReviewDTO t WHERE t.toAccount = :toAccount"),
    @NamedQuery(name = "TransactionReviewDTO.findByAmount", query = "SELECT t FROM TransactionReviewDTO t WHERE t.amount = :amount"),
    @NamedQuery(name = "TransactionReviewDTO.findByTransactionType", query = "SELECT t FROM TransactionReviewDTO t WHERE t.transactionType = :transactionType"),
    @NamedQuery(name = "TransactionReviewDTO.findByStatus", query = "SELECT t FROM TransactionReviewDTO t WHERE t.status = :status"),
    @NamedQuery(name = "TransactionReviewDTO.findByAuthorizingAuthorityId", query = "SELECT t FROM TransactionReviewDTO t WHERE t.authorizingAuthorityId = :authorizingAuthorityId"),
    @NamedQuery(name = "TransactionReviewDTO.findByAuthorizingMemberId", query = "SELECT t FROM TransactionReviewDTO t WHERE t.authorizingMemberId = :authorizingMemberId"),
    @NamedQuery(name = "TransactionReviewDTO.findByAuthorizingAuthorityType", query = "SELECT t FROM TransactionReviewDTO t WHERE t.authorizingAuthorityType = :authorizingAuthorityType"),
    @NamedQuery(name = "TransactionReviewDTO.findByRequestDate", query = "SELECT t FROM TransactionReviewDTO t WHERE t.requestDate = :requestDate"),
    @NamedQuery(name = "TransactionReviewDTO.findByProcessedDate", query = "SELECT t FROM TransactionReviewDTO t WHERE t.processedDate = :processedDate"),
    @NamedQuery(name = "TransactionReviewDTO.findByReviewType", query = "SELECT t FROM TransactionReviewDTO t WHERE t.reviewType = :reviewType")})
public class TransactionReviewDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "transaction_review_id")
    private Long transactionReviewId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "transaction_id")
    private long transactionId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "from_account")
    private long fromAccount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "to_account")
    private long toAccount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount")
    private double amount;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "transaction_type")
    private String transactionType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "authorizing_authority_id")
    private int authorizingAuthorityId;
    @Column(name = "authorizing_member_id")
    private Integer authorizingMemberId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "authorizing_authority_type")
    private String authorizingAuthorityType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "request_date")
    @Temporal(TemporalType.DATE)
    private Date requestDate;
    @Column(name = "processed_date")
    @Temporal(TemporalType.DATE)
    private Date processedDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "review_type")
    private String reviewType;
    @JoinColumn(name = "cust_member_id", referencedColumnName = "member_id")
    @ManyToOne(optional = false)
    private UserDTO custMemberId;

    public TransactionReviewDTO() {
    }

    public TransactionReviewDTO(Long transactionReviewId) {
        this.transactionReviewId = transactionReviewId;
    }

    public TransactionReviewDTO(Long transactionReviewId, long transactionId, long fromAccount, long toAccount, double amount, String transactionType, String status, int authorizingAuthorityId, String authorizingAuthorityType, Date requestDate, String reviewType) {
        this.transactionReviewId = transactionReviewId;
        this.transactionId = transactionId;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.transactionType = transactionType;
        this.status = status;
        this.authorizingAuthorityId = authorizingAuthorityId;
        this.authorizingAuthorityType = authorizingAuthorityType;
        this.requestDate = requestDate;
        this.reviewType = reviewType;
    }

    public Long getTransactionReviewId() {
        return transactionReviewId;
    }

    public void setTransactionReviewId(Long transactionReviewId) {
        this.transactionReviewId = transactionReviewId;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAuthorizingAuthorityId() {
        return authorizingAuthorityId;
    }

    public void setAuthorizingAuthorityId(int authorizingAuthorityId) {
        this.authorizingAuthorityId = authorizingAuthorityId;
    }

    public Integer getAuthorizingMemberId() {
        return authorizingMemberId;
    }

    public void setAuthorizingMemberId(Integer authorizingMemberId) {
        this.authorizingMemberId = authorizingMemberId;
    }

    public String getAuthorizingAuthorityType() {
        return authorizingAuthorityType;
    }

    public void setAuthorizingAuthorityType(String authorizingAuthorityType) {
        this.authorizingAuthorityType = authorizingAuthorityType;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Date getProcessedDate() {
        return processedDate;
    }

    public void setProcessedDate(Date processedDate) {
        this.processedDate = processedDate;
    }

    public String getReviewType() {
        return reviewType;
    }

    public void setReviewType(String reviewType) {
        this.reviewType = reviewType;
    }

    public UserDTO getCustMemberId() {
        return custMemberId;
    }

    public void setCustMemberId(UserDTO custMemberId) {
        this.custMemberId = custMemberId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transactionReviewId != null ? transactionReviewId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransactionReviewDTO)) {
            return false;
        }
        TransactionReviewDTO other = (TransactionReviewDTO) object;
        if ((this.transactionReviewId == null && other.transactionReviewId != null) || (this.transactionReviewId != null && !this.transactionReviewId.equals(other.transactionReviewId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.asu.safemoney.dto.TransactionReviewDTO[ transactionReviewId=" + transactionReviewId + " ]";
    }
    
}
