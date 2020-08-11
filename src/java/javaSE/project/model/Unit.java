package javaSE.project.model;

public class Unit {
    private String name;
    private double time;
    private int id;
    private Teacher teacher;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
    public String toStringRow() {
        return "[unit id: " + this.getId() + ", unit name: " + this.getName() + ", Hours: " + this.getTime() +  ", Teacher: " + this.getTeacher().getName() + "]";
    }

    @Override
    public String toString() {
        return "\nunit id:" + this.getId() +
                "\nunit name:'" + this.getTeacher() +
                "\nHours:'" + this.getTime() +
                "\nTeacher:'" + this.getTeacher().getName();
    }

}
