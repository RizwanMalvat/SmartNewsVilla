/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartnewsvillaadmin.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 1006
 */
@Entity
@Table(name = "admin_auth")
@SequenceGenerator(name = "admin_auth_seq", sequenceName = "admin_auth_seq", allocationSize = 1)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdminAuth.findAll", query = "SELECT a FROM AdminAuth a")})
public class AdminAuth implements Serializable {

    @Column(name = "rating")
    private Long rating;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admin_auth_seq")
    @Column(name = "authid")
    private Long authid;
    @Size(max = 255)
    @Column(name = "firstname")
    private String firstname;
    @Size(max = 255)
    @Column(name = "secondname")
    private String secondname;
    @Size(max = 255)
    @Column(name = "username")
    private String username;
    @Size(max = 255)
    @Column(name = "type")
    private String type;
    @Size(max = 255)
    @Column(name = "password")
    private String password;
    @Size(max = 10)
    @Column(name = "status")
    private String status;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "email")
    private String email;
    @Size(max = 255)
    @Column(name = "createdby")
    private String createdby;
    @Size(max = 1000)
    @Column(name = "profilepicture")
    private String profilepicture;
    @Size(max = 255)
    @Column(name = "modifiedby")
    private String modifiedby;
    @Column(name = "creatdedate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creatdedate;
    @Column(name = "modifieddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifieddate;
    @Column(name = "enabled")
    private Boolean enabled;
    @Column(name = "termsofservice")
    private Boolean termsofservice;

    public AdminAuth() {
    }

    public AdminAuth(Long authid) {
        this.authid = authid;
    }

    public Long getAuthid() {
        return authid;
    }

    public void setAuthid(Long authid) {
        this.authid = authid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getTermsofservice() {
        return termsofservice;
    }

    public void setTermsofservice(Boolean termsofservice) {
        this.termsofservice = termsofservice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public String getProfilepicture() {
        return profilepicture;
    }

    public void setProfilepicture(String profilepicture) {
        this.profilepicture = profilepicture;
    }

    public String getModifiedby() {
        return modifiedby;
    }

    public void setModifiedby(String modifiedby) {
        this.modifiedby = modifiedby;
    }

    public Date getCreatdedate() {
        return creatdedate;
    }

    public void setCreatdedate(Date creatdedate) {
        this.creatdedate = creatdedate;
    }

    public Date getModifieddate() {
        return modifieddate;
    }

    public void setModifieddate(Date modifieddate) {
        this.modifieddate = modifieddate;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (authid != null ? authid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdminAuth)) {
            return false;
        }
        AdminAuth other = (AdminAuth) object;
        if ((this.authid == null && other.authid != null) || (this.authid != null && !this.authid.equals(other.authid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smartnewsvillaadmin.entities.AdminAuth[ authid=" + authid + " ]";
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }
}
