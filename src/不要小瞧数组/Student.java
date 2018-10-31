package 不要小瞧数组;

public class Student {
    private String name;
    private String sex;

    public Student(String name,String sex){
        this.name = name;
        this.sex = sex;
    }

    @Override
    public String toString(){
        return String.format("Student(name=%s,sex=%s)",name,sex);
    }
}
