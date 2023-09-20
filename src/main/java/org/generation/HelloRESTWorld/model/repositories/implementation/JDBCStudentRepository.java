package org.generation.HelloRESTWorld.model.repositories.implementation;

import jakarta.persistence.EntityManager;
import org.generation.HelloRESTWorld.model.Student;
import org.generation.HelloRESTWorld.model.repositories.abstraction.StudentRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PutMapping;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Profile("jdbc")
public class JDBCStudentRepository implements StudentRepository {

    //Dichiarazione delle costanti
    public final static String JDBC_URL = "jdbc:mysql://localhost:3306/db_example";
    public final static String JDBC_USER = "root";
    public final static String JDBC_PASSWORD = "";
    public final static String ALL_STUDENTS = "SELECT id, firstname,lastname,birthdate FROM students";
    public final static String FIND_STUDENT_BY_ID = "SELECT firstname, lastname, birthdate FROM students WHERE id = ?";
    public final static String INSERT_STUDENT = "INSERT INTO students(firstname, lastname, birthdate) VALUES (?, ?, ?)";
    public final static String DELATE_STUDENT = "DELETE FROM students WHERE id = ?";
    public final static String UPDATA = "UPDATE students SET firstname=?, lastname=?, birthdate=? WHERE id=?";
     /*
    @Override
    public Iterable<Student> getALLStudents() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Collection<Student> students = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);//creare la connessione
            statement = connection.createStatement();//creare un oggetto statman che serve per lanciare la quary
            resultSet = statement.executeQuery(ALL_STUDENTS); //lanciare la quary
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                LocalDate birthdate = resultSet.getDate("birthdate").toLocalDate();
                Student student = new Student(id, firstname, lastname, birthdate);
                students.add(student);
            }
            return students;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    */

    // Implementazione del metodo per ottenere tutti gli studenti
    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(ALL_STUDENTS);
        ) {
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                LocalDate birthdate = resultSet.getDate("birthdate").toLocalDate();
                Student student = new Student(id, firstname, lastname, birthdate);
                students.add(student);
            }
            return students;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Implementazione del metodo per trovare uno studente per ID
    @Override
    public Optional<Student> findById(long id) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_STUDENT_BY_ID);
        ) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String firstname = resultSet.getString("firstname");
                    String lastname = resultSet.getString("lastname");
                    LocalDate birthdate = resultSet.getDate("birthdate").toLocalDate();
                    Student student = new Student(id, firstname, lastname, birthdate);
                    return Optional.of(student);
                } else {
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Implementazione del metodo per salvare uno studente
    @Override
    public Student save(Student s) {
        if (s.getId() == 0) {
            // L'entità non ha un ID, quindi è una nuova entità
            return createStudent(s);
        } else {
            // L'entità ha un ID, quindi esiste già nel database e deve essere aggiornata
            return updateStudent(s);
        }
    }

    private Student createStudent(Student s) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, s.getFirstName());
            preparedStatement.setString(2, s.getLastName());
            preparedStatement.setDate(3, Date.valueOf(s.getBirthdate()));

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creazione dello studente fallita");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    long id = generatedKeys.getLong(1);
                    s.setId(id);
                    return s;
                } else {
                    throw new SQLException("Creazione dello studente fallita, nessun ID generato");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Student updateStudent(Student s) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATA)) {
            preparedStatement.setString(1, s.getFirstName());
            preparedStatement.setString(2, s.getLastName());
            preparedStatement.setDate(3, Date.valueOf(s.getBirthdate()));
            preparedStatement.setLong(4, s.getId());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Aggiornamento dello studente fallito");
            }

            return s;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(DELATE_STUDENT);
        ) {
            preparedStatement.setLong(1, id);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Eliminazione dello studente fallita: lo studente non esiste");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
