package vn.edu.fpt.entity;

import javax.persistence.*;
import java.util.Set;

@Table
@Entity( name = "role" )
public class Role {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "id" )
    private Integer id;

    @Column( name = "role_name" )
    private String roleName;

    @ManyToMany( cascade = CascadeType.ALL )
    @JoinTable( name = "user_role", joinColumns = @JoinColumn( name = "role_id", referencedColumnName = "id" ), inverseJoinColumns = @JoinColumn( name = "user_id", referencedColumnName = "id" ) )
    private Set<User> users;

    public Role() {
    }

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName( String roleName ) {
        this.roleName = roleName;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers( Set<User> users ) {
        this.users = users;
    }
}
