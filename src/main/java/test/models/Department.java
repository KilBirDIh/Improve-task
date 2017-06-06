package test.models;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "department")
public class Department implements Serializable
{
    private int id;
    private String name;
    private Collection<Meeting> meetings;
    private Collection<Employee> employees;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    @Column(name = "name")
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @OneToMany(mappedBy = "responsibleDepartment", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    public Collection<Meeting> getMeetings()
    {
        return meetings;
    }

    public void setMeetings(Collection<Meeting> meetings)
    {
        this.meetings = meetings;
    }

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    public Collection<Employee> getEmployees()
    {
        return employees;
    }

    public void setEmployees(Collection<Employee> employees)
    {
        this.employees = employees;
    }

    @Override
    public String toString()
    {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", meetings=" + meetings +
                ", employees=" + employees +
                '}';
    }
}
