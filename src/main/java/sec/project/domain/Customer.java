/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sec.project.domain;

import javax.persistence.Entity;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 *
 * @author User
 */
@Entity
public class Customer extends AbstractPersistable<Long> {
    
    private String username;
    private String password;
    private boolean signedUp;

    public Customer() {
        super();
    }

    public Customer(String username, String password) {
        this();
        this.username = username;
        this.password = password;
        //is false by default
        this.signedUp = false;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean isSignedUp() {
        return this.signedUp;
    }
    
    public void setSignedUp(boolean signedUp) {
        this.signedUp = signedUp;
    }
}
