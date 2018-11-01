package application.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * The persistent class for the users database table.
 */
@Entity
@Table(name = "users")
public class User implements Serializable, UserDetails {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "users_id_seq")
    @SequenceGenerator(
            name = "users_id_seq",
            sequenceName = "users_id_seq",
            allocationSize = 1
    )
    @Column(unique = true, nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name="album_guid_id")
    private Album albumGuid;

    private double bonus;

    @Column(name = "call_time")
    private Timestamp callTime;

    @Column(length = 250)
    private String fio;

    @Column(nullable = false, length = 250)
    private String login;

    @Column(nullable = false, length = 60)
    private String pass;

    private Boolean subscribe;
    private Boolean isActive;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    //bi-directional many-to-one association to Contact
    @OneToMany(mappedBy = "user",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Contact> contacts;

    //bi-directional many-to-one association to Order
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Order> orders;

    //bi-directional many-to-many association to Tour
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "wishlist",
            joinColumns = @JoinColumn(name = "client_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "tour_id", nullable = false)
    )
    private Set<Tour> tours;

    public User() {
        this.contacts = new HashSet<>();
        this.roles = new HashSet<>();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Album getAlbumGuid() {
        return albumGuid;
    }

    public void setAlbumGuid(Album albumGuid) {
        this.albumGuid = albumGuid;
    }

    public double getBonus() {
        return this.bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public Timestamp getCallTime() {
        return this.callTime;
    }

    public void setCallTime(Timestamp callTime) {
        this.callTime = callTime;
    }

    public String getFio() {
        return this.fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return this.pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Boolean getSubscribe() {
        return this.subscribe;
    }

    public void setSubscribe(Boolean subscribe) {
        this.subscribe = subscribe;
    }

    public Set<Contact> getContacts() {
        return this.contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    public Contact addContact(Contact contact) {
        getContacts().add(contact);
        contact.setUser(this);

        return contact;
    }

    public Contact removeContact(Contact contact) {
        getContacts().remove(contact);
        contact.setUser(null);

        return contact;
    }

    public Set<Order> getOrders() {
        return this.orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Order addOrder(Order order) {
        getOrders().add(order);
        order.setUser(this);

        return order;
    }

    public Order removeOrder(Order order) {
        getOrders().remove(order);
        order.setUser(null);

        return order;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Set<Tour> getTours() {
        return this.tours;
    }

    public void setTours(Set<Tour> tours) {
        this.tours = tours;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getPassword() {
        return pass;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id != null ? id.equals(user.id) : user.id == null;

//        if (Double.compare(user.bonus, bonus) != 0) return false;
//        if (albumGuid != null ? !albumGuid.equals(user.albumGuid) : user.albumGuid != null) return false;
//        if (callTime != null ? !callTime.equals(user.callTime) : user.callTime != null) return false;
//        if (fio != null ? !fio.equals(user.fio) : user.fio != null) return false;
//        if (login != null ? !login.equals(user.login) : user.login != null) return false;
//        if (pass != null ? !pass.equals(user.pass) : user.pass != null) return false;
//        if (subscribe != null ? !subscribe.equals(user.subscribe) : user.subscribe != null) return false;
//        if (isActive != null ? !isActive.equals(user.isActive) : user.isActive != null) return false;
//        if (roles != null ? !roles.equals(user.roles) : user.roles != null) return false;
//        if (contacts != null ? !contacts.equals(user.contacts) : user.contacts != null) return false;
//        if (orders != null ? !orders.equals(user.orders) : user.orders != null) return false;
//        return tours != null ? tours.equals(user.tours) : user.tours == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}