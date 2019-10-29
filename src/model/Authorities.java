/*
 * Entity Authorities. This object contains all information on the system administrator.
 * Table authorities
*/
package model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author paniagua
 */
@Entity
@Table(name="authorities")
public class Authorities implements Serializable {

    @Id 
    @Column(name="idAuth")
    @GeneratedValue(strategy = GenerationType.IDENTITY)   
    private Long idAuth;
    
    @Column(name="username", length=30,  nullable = false)
    private  String username;
    
    @Column(name="pass", length=8, nullable = false) 
    private String pass;
 
    // Join with entity Roles. 
    @JoinColumn(name = "idRol", referencedColumnName = "idRol")
    @ManyToOne( cascade = CascadeType.ALL)
    private Roles roles;

    public Authorities() {
    }

    public Long getIdAuth() {
        return idAuth;
    }

    public void setIdAuth(Long idAuth) {
        this.idAuth = idAuth;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Authorities{" + "idAuth=" + idAuth + ", username=" + username + ", pass=" + pass + ", roles=" + roles + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.idAuth);
        hash = 59 * hash + Objects.hashCode(this.username);
        hash = 59 * hash + Objects.hashCode(this.pass);
        hash = 59 * hash + Objects.hashCode(this.roles);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Authorities other = (Authorities) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.pass, other.pass)) {
            return false;
        }
        if (!Objects.equals(this.idAuth, other.idAuth)) {
            return false;
        }
        if (!Objects.equals(this.roles, other.roles)) {
            return false;
        }
        return true;
    }
 
    

    
}
