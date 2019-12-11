package cn.wr.cn.wr.instance;

public class Person
{
    String name;
    Integer age;
    public Person() {
        this.name = "ee";
        this.age = 12;
    }
    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
