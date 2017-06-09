package test.models;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "meeting")
public class Meeting implements Serializable
{
    private Long id;
    private LocalDateTime meetingDateTime;
    private String subject;
    private Department responsibleDepartment;
    private Employee responsibleEmployee;
    private Collection<Employee> membersOfMeeting;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    @Column(name = "meeting_date")
    public LocalDateTime getMeetingDateTime()
    {
        return meetingDateTime;
    }

    public void setMeetingDateTime(LocalDateTime meetingDateTime)
    {
        this.meetingDateTime = meetingDateTime;
    }

    @Column(name = "subject")
    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "resp_department", nullable = false)
    @LazyCollection(LazyCollectionOption.FALSE)
    public Department getResponsibleDepartment()
    {
        return responsibleDepartment;
    }

    public void setResponsibleDepartment(Department responsibleDepartment)
    {
        this.responsibleDepartment = responsibleDepartment;
    }

    @ManyToOne( cascade = CascadeType.MERGE)
    @JoinColumn(name = "resp_employee", nullable = false)
    @LazyCollection(LazyCollectionOption.FALSE)
    public Employee getResponsibleEmployee()
    {
        return responsibleEmployee;
    }

    public void setResponsibleEmployee(Employee responsibleEmployee)
    {
        this.responsibleEmployee = responsibleEmployee;
    }

    @ManyToMany(mappedBy = "memberOfMeetings", cascade = CascadeType.MERGE)
    @LazyCollection(LazyCollectionOption.FALSE)
    public Collection<Employee> getMembersOfMeeting()
    {
        return membersOfMeeting;
    }

    public void setMembersOfMeeting(Collection<Employee> membersOfMeeting)
    {
        this.membersOfMeeting = membersOfMeeting;
    }
}
