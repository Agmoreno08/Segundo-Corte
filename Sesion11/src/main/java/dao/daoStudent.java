package dao;

import interfaces.operacion;
import models.Student;

import java.util.ArrayList;

public abstract class daoStudent implements operacion {
    private ArrayList<Student> list = new ArrayList<>();

    @Override
    public void create(Object object) {
        try {
            Student student = (Student) object;
            list.add(student);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public Object read() {
        return list;
    }

    @Override
    public void update(Object object) {
        Student student = (Student) object;

        for (Student s : list) {
            if (student.getCif().equals(s.getCif())) {
                // Actualizamos los valores del estudiante existente con los nuevos
                s.setName(student.getName());
                s.setSurname(student.getSurname());
                s.setEmail(student.getEmail());
                s.setPhone(student.getPhone());
                return;
            }
        }
    }

    @Override
    public void delete(String cif) {
        for (Student s : list) {
            if (s.getCif().equals(cif)) {  // Comparamos el cif del estudiante
                list.remove(s);
                return;
            }
        }
    }

    @Override
    public Student search(String value) {
        for (Student s : list) {
            if (s.getCif().equals(value) || s.getName().equalsIgnoreCase(value)) {
                return s;  // Devolvemos el estudiante si coincide
            }
        }
        return null;  // Si no lo encuentra, retorna null
    }
}

