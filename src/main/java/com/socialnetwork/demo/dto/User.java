package com.socialnetwork.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class User implements Cloneable {

    private @Id
    @GeneratedValue
    Long id = 0L;
    Long timestamp;
    private String userName;
    private String address;

    public User(Long timestamp, String userName, String address) {
        this.timestamp = timestamp;
        this.userName = userName;
        this.address = address;
    }

    public User(String userName, String address) {
        this.timestamp = System.currentTimeMillis();
        this.userName = userName;
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
               "id=" + id +
               ", userName='" + userName + '\'' +
               ", address='" + address + '\'' +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
               Objects.equals(timestamp, user.timestamp) &&
               Objects.equals(userName, user.userName) &&
               Objects.equals(address, user.address);
    }

    public User clone(){
        try {
            return (User) super.clone();
        } catch (CloneNotSupportedException e) {
            return new User();
        }
    }
}
