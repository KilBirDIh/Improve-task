package test.domain;

public class Meeting
{
    private Long meetingId;
    private String datetime;
    private String subject;
    private Long department;
    private Long responsibleEmployee;

    public Long getMeetingId()
    {
        return meetingId;
    }

    public void setMeetingId(Long meetingId)
    {
        this.meetingId = meetingId;
    }

    public String getDatetime()
    {
        return datetime;
    }

    public void setDatetime(String datetime)
    {
        this.datetime = datetime;
    }

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public Long getDepartment()
    {
        return department;
    }

    public void setDepartment(Long department)
    {
        this.department = department;
    }

    public Long getResponsibleEmployee()
    {
        return responsibleEmployee;
    }

    public void setResponsibleEmployee(Long responsibleEmployee)
    {
        this.responsibleEmployee = responsibleEmployee;
    }
}
