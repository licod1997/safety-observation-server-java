package vn.edu.fpt.entity;

import javax.persistence.*;
import java.util.Objects;

@Table
@Entity(name = "Demo")
public class Demo {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "id" )
    private Long id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String passsword;

    public Demo() {
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername( String username ) {
        this.username = username;
    }

    public String getPasssword() {
        return passsword;
    }

    public void setPasssword( String passsword ) {
        this.passsword = passsword;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        Demo demo = (Demo) o;
        return Objects.equals( id, demo.id ) &&
                Objects.equals( username, demo.username ) &&
                Objects.equals( passsword, demo.passsword );
    }

    @Override
    public int hashCode() {

        return Objects.hash( id, username, passsword );
    }
}
