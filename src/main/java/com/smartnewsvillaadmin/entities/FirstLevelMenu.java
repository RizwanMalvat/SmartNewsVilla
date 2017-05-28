/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartnewsvillaadmin.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
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
@Table(name = "first_level_menu")
@SequenceGenerator(name = "first_level_menu_seq", sequenceName = "first_level_menu_seq", allocationSize = 1)
@NamedQueries({
    @NamedQuery(name = "FirstLevelMenu.findAll", query = "SELECT f FROM FirstLevelMenu f")})
public class FirstLevelMenu implements Serializable {

    @OneToMany(mappedBy = "menuid")
    private List<Blogs> blogsList;

    @Size(max = 255)
    @Column(name = "menutype")
    private String menutype;
    @OneToMany(mappedBy = "parentmenuid")
    private List<FirstLevelMenu> parentMenuList;
    @JoinColumn(name = "parentmenuid", referencedColumnName = "firstmenuid")
    @ManyToOne
    private FirstLevelMenu parentmenuid;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "first_level_menu_seq")
    @Column(name = "firstmenuid")
    private Long firstmenuid;
    @Size(max = 2147483647)
    @Column(name = "menupath")
    private String menupath;
    @Size(max = 2147483647)
    @Column(name = "menuname")
    private String menuname;
    @Size(max = 255)
    @Column(name = "createdby")
    private String createdby;
    @Column(name = "modifieddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifieddate;
    @Column(name = "createddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createddate;
    @Size(max = 255)
    @Column(name = "modifiedby")
    private String modifiedby;
    @Size(max = 10)
    @Column(name = "status")
    private String status;

    public FirstLevelMenu() {
    }

    public FirstLevelMenu(Long firstmenuid) {
        this.firstmenuid = firstmenuid;
    }

    public Long getFirstmenuid() {
        return firstmenuid;
    }

    public void setFirstmenuid(Long firstmenuid) {
        this.firstmenuid = firstmenuid;
    }

    public String getMenupath() {
        return menupath;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMenupath(String menupath) {
        this.menupath = menupath;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public Date getModifieddate() {
        return modifieddate;
    }

    public void setModifieddate(Date modifieddate) {
        this.modifieddate = modifieddate;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (firstmenuid != null ? firstmenuid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FirstLevelMenu)) {
            return false;
        }
        FirstLevelMenu other = (FirstLevelMenu) object;
        if ((this.firstmenuid == null && other.firstmenuid != null) || (this.firstmenuid != null && !this.firstmenuid.equals(other.firstmenuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smartnewsvillaadmin.entities.FirstLevelMenu[ firstmenuid=" + firstmenuid + " ]";
    }

    public String getMenutype() {
        return menutype;
    }

    public void setMenutype(String menutype) {
        this.menutype = menutype;
    }

    public List<FirstLevelMenu> getParentMenuList() {
        return parentMenuList;
    }

    public void setParentMenuList(List<FirstLevelMenu> parentMenuList) {
        this.parentMenuList = parentMenuList;
    }


    public FirstLevelMenu getParentmenuid() {
        return parentmenuid;
    }

    public void setParentmenuid(FirstLevelMenu parentmenuid) {
        this.parentmenuid = parentmenuid;
    }

    public List<Blogs> getBlogsList() {
        return blogsList;
    }

    public void setBlogsList(List<Blogs> blogsList) {
        this.blogsList = blogsList;
    }
    
}
