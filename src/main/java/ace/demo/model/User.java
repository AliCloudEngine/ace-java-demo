package ace.demo.model;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 336719350483543743L;
    private String name;
    private boolean sex;
    private byte age;
    private int weight;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public byte getAge() {
        return age;
    }


    public void setAge(byte age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", weight=" + weight +
                '}';
    }
}
