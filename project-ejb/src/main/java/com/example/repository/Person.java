package com.example.repository;

public class Person {
    
    private String id;
    private String name;
    private int age;
    private String email;

    private Person(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.age = builder.age;
        this.email = builder.email;
    }

    public static class Builder {
        private String id;
        private String name;
        private int age;
        private String email;

        public Builder(String id) {
            this.id = id;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }

    // Getters for the fields (id, name, age, email)

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Person{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", age=" + age + ", email='" + email + '\''
                + '}';
    }

}
