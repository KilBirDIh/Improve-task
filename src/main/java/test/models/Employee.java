package test.models;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Table(name = "employee")
public class Employee implements Serializable
{
    private int id;
    private String name;
    private String surname;
    private String secondName;
    private LocalDate dateOfBirth;
    private Collection<Meeting> responsibleForMeetings;
    private Collection<Meeting> memberOfMeetings;
    private Department department;


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name = "surname")
    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    @Column(name = "second_name")
    public String getSecondName()
    {
        return secondName;
    }

    public void setSecondName(String secondName)
    {
        this.secondName = secondName;
    }

    @Column(name = "date_of_birth")
    public LocalDate getDateOfBirth()
    {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }

    @OneToMany(mappedBy = "responsibleEmployee")
    @LazyCollection(LazyCollectionOption.FALSE)
    public Collection<Meeting> getResponsibleForMeetings()
    {
        return responsibleForMeetings;
    }

    public void setResponsibleForMeetings(Collection<Meeting> responsibleForMeetings)
    {
        this.responsibleForMeetings = responsibleForMeetings;
    }

    @ManyToMany
    @JoinTable(name = "employee_meeting",
               joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "meeting_id", referencedColumnName = "id")
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    public Collection<Meeting> getMemberOfMeetings()
    {
        return memberOfMeetings;
    }

    public void setMemberOfMeetings(Collection<Meeting> memberOfMeetings)
    {
        this.memberOfMeetings = memberOfMeetings;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department", nullable = false)
    @LazyCollection(LazyCollectionOption.FALSE)
    public Department getDepartment()
    {
        return department;
    }

    public void setDepartment(Department department)
    {
        this.department = department;
    }

    @Override
    public String toString()
    {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", secondName='" + secondName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", responsibleForMeetings=" + responsibleForMeetings +
                ", memberOfMeetings=" + memberOfMeetings +
               // ", department=" + department +
                '}';
    }
}
