import dao.daoStudent;
import models.Student;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Instanciamos el DAO de estudiantes
        daoStudent studentDao = new daoStudentImpl();  // Usa una implementación concreta del daoStudent
        Scanner scanner = new Scanner(System.in);

        // Menú simple para seleccionar operaciones
        while (true) {
            System.out.println("\n--- MENU DE ESTUDIANTES ---");
            System.out.println("1. Crear estudiante");
            System.out.println("2. Leer todos los estudiantes");
            System.out.println("3. Actualizar estudiante");
            System.out.println("4. Eliminar estudiante");
            System.out.println("5. Buscar estudiante");
            System.out.println("6. Salir");
            System.out.print("Elige una opción: ");

            int option = scanner.nextInt();
            scanner.nextLine();  // Consumir la línea nueva después del entero

            switch (option) {
                case 1:
                    // Crear estudiante
                    System.out.println("Introduce el CIF del estudiante: ");
                    String cif = scanner.nextLine();
                    System.out.println("Introduce el nombre: ");
                    String name = scanner.nextLine();
                    System.out.println("Introduce el apellido: ");
                    String surname = scanner.nextLine();
                    System.out.println("Introduce el teléfono: ");
                    String phone = scanner.nextLine();
                    System.out.println("Introduce el correo electrónico: ");
                    String email = scanner.nextLine();

                    Student newStudent = new Student(cif, name, surname, phone, email);
                    studentDao.create(newStudent);
                    System.out.println("Estudiante creado con éxito.");
                    break;

                case 2:
                    // Leer todos los estudiantes
                    System.out.println("\nLista de estudiantes: ");
                    for (Object obj : (Object[]) studentDao.read()) {
                        Student student = (Student) obj;
                        System.out.println(student);
                    }
                    break;

                case 3:
                    // Actualizar estudiante
                    System.out.println("Introduce el CIF del estudiante a actualizar: ");
                    String cifToUpdate = scanner.nextLine();

                    // Buscamos el estudiante por CIF
                    Student studentToUpdate = studentDao.search(cifToUpdate);
                    if (studentToUpdate != null) {
                        System.out.println("Introduce el nuevo nombre: ");
                        String newName = scanner.nextLine();
                        System.out.println("Introduce el nuevo apellido: ");
                        String newSurname = scanner.nextLine();
                        System.out.println("Introduce el nuevo teléfono: ");
                        String newPhone = scanner.nextLine();
                        System.out.println("Introduce el nuevo correo electrónico: ");
                        String newEmail = scanner.nextLine();

                        // Actualizamos los valores
                        studentToUpdate.setName(newName);
                        studentToUpdate.setSurname(newSurname);
                        studentToUpdate.setPhone(newPhone);
                        studentToUpdate.setEmail(newEmail);

                        studentDao.update(studentToUpdate);
                        System.out.println("Estudiante actualizado con éxito.");
                    } else {
                        System.out.println("Estudiante no encontrado.");
                    }
                    break;

                case 4:
                    // Eliminar estudiante
                    System.out.println("Introduce el CIF del estudiante a eliminar: ");
                    String cifToDelete = scanner.nextLine();
                    studentDao.delete(cifToDelete);
                    System.out.println("Estudiante eliminado con éxito.");
                    break;

                case 5:
                    // Buscar estudiante
                    System.out.println("Introduce el CIF o nombre del estudiante: ");
                    String searchValue = scanner.nextLine();
                    Student foundStudent = studentDao.search(searchValue);
                    if (foundStudent != null) {
                        System.out.println("Estudiante encontrado: ");
                        System.out.println(foundStudent);
                    } else {
                        System.out.println("Estudiante no encontrado.");
                    }
                    break;

                case 6:
                    // Salir
                    System.out.println("Saliendo del programa...");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Opción no válida. Por favor, elige una opción correcta.");
            }
        }
    }

    private record daoStudentImpl() extends daoStudent {
    }
}
