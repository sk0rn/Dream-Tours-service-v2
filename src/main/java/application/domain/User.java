package application.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * The persistent class for the users database table.
 *
 */
@Entity
@Table(name="users")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,
            generator = "users_id_seq")
    @SequenceGenerator(
            name="users_id_seq",
            sequenceName="users_id_seq",
            allocationSize=1
    )
    @Column(unique=true, nullable=false)
    private Long id;

    @Column(name="album_guid", length=60)
    private String albumGuid;

    private double bonus;

    @Column(name="call_time")
    private Timestamp callTime;

    @Column(length=250)
    private String fio;

    @Column(nullable=false, length=250)
    private String login;

    @Column(nullable=false)
    private Integer options;

    @Column(nullable=false, length=60)
    private String pass;

    private Boolean subscribe;

    //bi-directional many-to-one association to Contact
    @OneToMany(mappedBy = "user",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Contact> contacts;

    //bi-directional many-to-one association to Order
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Order> orders;

    //bi-directional many-to-many association to Tour
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="wishlist"
            , joinColumns={
            @JoinColumn(name="client_id", nullable=false)
    }
            , inverseJoinColumns={
            @JoinColumn(name="tour_id", nullable=false)
    }
    )
    private Set<Tour> tours;

    public User() {
        this.contacts = new HashSet<>();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlbumGuid() {
        return this.albumGuid;
    }

    public void setAlbumGuid(String albumGuid) {
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

    public Integer getOptions() {
        return this.options;
    }

    public void setOptions(Integer options) {
        this.options = options;
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

    public Set<Tour> getTours() {
        return this.tours;
    }

    public void setTours(Set<Tour> tours) {
        this.tours = tours;
    }
}