/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartnewsvillaadmin.entities;

import java.io.Serializable;
import java.math.BigInteger;
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

/**
 *
 * @author Rizwan Malvat
 */
@Entity
@Table(name = "blogs")
@NamedQueries({
    @NamedQuery(name = "Blogs.findAll", query = "SELECT b FROM Blogs b")})
public class Blogs implements Serializable {

    @Column(name = "views")
    private BigInteger views;
    @Size(max = 255)
    @Column(name = "createdby")
    private String createdby;
    @Column(name = "createddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createddate;
    @Size(max = 255)
    @Column(name = "modifiedby")
    private String modifiedby;
    @Column(name = "modifieddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifieddate;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "blogid")
    private Long blogid;
    @Size(max = 2000)
    @Column(name = "blogimage")
    private String blogimage;
    @Size(max = 2000)
    @Column(name = "blogtitle")
    private String blogtitle;
    @Size(max = 2000)
    @Column(name = "blogdescription")
    private String blogdescription;
    @Size(max = 2147483647)
    @Column(name = "blogdetails")
    private String blogdetails;
    @Size(max = 20)
    @Column(name = "status")
    private String status;
    @JoinColumn(name = "menuid", referencedColumnName = "firstmenuid")
    @ManyToOne
    private FirstLevelMenu menuid;

    public Blogs() {
    }

    public Blogs(Long blogid) {
        this.blogid = blogid;
    }

    public Long getBlogid() {
        return blogid;
    }

    public void setBlogid(Long blogid) {
        this.blogid = blogid;
    }

    public String getBlogimage() {
        return blogimage;
    }

    public void setBlogimage(String blogimage) {
        this.blogimage = blogimage;
    }

    public String getBlogtitle() {
        return blogtitle;
    }

    public void setBlogtitle(String blogtitle) {
        this.blogtitle = blogtitle;
    }

    public String getBlogdescription() {
        return blogdescription;
    }

    public void setBlogdescription(String blogdescription) {
        this.blogdescription = blogdescription;
    }

    public String getBlogdetails() {
        return blogdetails;
    }

    public void setBlogdetails(String blogdetails) {
        this.blogdetails = blogdetails;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public FirstLevelMenu getMenuid() {
        return menuid;
    }

    public void setMenuid(FirstLevelMenu menuid) {
        this.menuid = menuid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (blogid != null ? blogid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Blogs)) {
            return false;
        }
        Blogs other = (Blogs) object;
        if ((this.blogid == null && other.blogid != null) || (this.blogid != null && !this.blogid.equals(other.blogid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smartnewsvillaadmin.entities.Blogs[ blogid=" + blogid + " ]";
    }

    public BigInteger getViews() {
        return views;
    }

    public void setViews(BigInteger views) {
        this.views = views;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public Date getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    public String getModifiedby() {
        return modifiedby;
    }

    public void setModifiedby(String modifiedby) {
        this.modifiedby = modifiedby;
    }

    public Date getModifieddate() {
        return modifieddate;
    }

    public void setModifieddate(Date modifieddate) {
        this.modifieddate = modifieddate;
    }
    
}
