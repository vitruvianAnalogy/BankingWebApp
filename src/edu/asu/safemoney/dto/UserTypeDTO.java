/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.safemoney.dto;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ObuliKarthikeyan
 */
@Entity
@Table(name = "user_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserTypeDTO.findAll", query = "SELECT u FROM UserTypeDTO u"),
    @NamedQuery(name = "UserTypeDTO.findByUserTypeId", query = "SELECT u FROM UserTypeDTO u WHERE u.userTypeId = :userTypeId"),
    @NamedQuery(name = "UserTypeDTO.findByUserType", query = "SELECT u FROM UserTypeDTO u WHERE u.userType = :userType"),
    @NamedQuery(name = "UserTypeDTO.findByDescription", query = "SELECT u FROM UserTypeDTO u WHERE u.description = :description"),
    @NamedQuery(name = "UserTypeDTO.findByIsActive", query = "SELECT u FROM UserTypeDTO u WHERE u.isActive = :isActive")})
public class UserTypeDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_type_id")
    private Integer userTypeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "user_type")
    private String userType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "is_active")
    private String isActive;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userTypeId")
    private List<UserDTO> userDTOList;

    public UserTypeDTO() {
    }

    public UserTypeDTO(Integer userTypeId) {
        this.userTypeId = userTypeId;
    }

    public UserTypeDTO(Integer userTypeId, String userType, String description, String isActive) {
        this.userTypeId = userTypeId;
        this.userType = userType;
        this.description = description;
        this.isActive = isActive;
    }

    public Integer getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(Integer userTypeId) {
        this.userTypeId = userTypeId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    @XmlTransient
    public List<UserDTO> getUserDTOList() {
        return userDTOList;
    }

    public void setUserDTOList(List<UserDTO> userDTOList) {
        this.userDTOList = userDTOList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userTypeId != null ? userTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserTypeDTO)) {
            return false;
        }
        UserTypeDTO other = (UserTypeDTO) object;
        if ((this.userTypeId == null && other.userTypeId != null) || (this.userTypeId != null && !this.userTypeId.equals(other.userTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.asu.safemoney.dto.UserTypeDTO[ userTypeId=" + userTypeId + " ]";
    }
    
}
