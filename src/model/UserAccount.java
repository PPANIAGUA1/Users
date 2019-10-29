/*
 * Entity UserAccount. This object contains all information on user accounts
 */
package model;

import java.io.Serializable;
import java.util.Objects;
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
@Table(name="Users")
public class UserAccount implements Serializable{
    
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)   
    private Long id;
    
    @Column(name="firstName", length=30)
    private  String firstName;
    
    @Column(name="lastname", length=30) 
    private String lastname;
    
    @Column(name="iban", length=50, nullable = false)
    private String iban; 

    // Join with authorities. To manage security about who can work with every user account.
    @JoinColumn(name = "createdby", referencedColumnName = "idAuth")
    @ManyToOne()
    private Authorities authorities;
      
    public UserAccount() {
    }

    public UserAccount(String firstName, String lastname, String iban) {
        this.firstName = firstName;
        this.lastname = lastname;
        this.iban = iban;
    }
   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public Authorities getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Authorities authorities) {
        this.authorities = authorities;
    }

    
    @Override
    public String toString() {
        return "User{" + "id=" + id + ", firstName=" + firstName + ", lastname=" + lastname + ", iban=" + iban + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.firstName);
        hash = 67 * hash + Objects.hashCode(this.lastname);
        hash = 67 * hash + Objects.hashCode(this.iban);
        hash = 67 * hash + Objects.hashCode(this.authorities);
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
        final UserAccount other = (UserAccount) obj;
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastname, other.lastname)) {
            return false;
        }
        if (!Objects.equals(this.iban, other.iban)) {
            return false;
        }
        return Objects.equals(this.authorities, other.authorities);
    }
    
    
}
