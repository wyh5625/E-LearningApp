package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.BelongsTo;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the Enrollment type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Enrollments")
@Index(name = "byStudent", fields = {"studentID","courseID"})
@Index(name = "byCourse", fields = {"courseID","studentID"})
public final class Enrollment implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField STUDENT = field("studentID");
  public static final QueryField COURSE = field("courseID");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="Student", isRequired = true) @BelongsTo(targetName = "studentID", type = Student.class) Student student;
  private final @ModelField(targetType="Course", isRequired = true) @BelongsTo(targetName = "courseID", type = Course.class) Course course;
  public String getId() {
      return id;
  }
  
  public Student getStudent() {
      return student;
  }
  
  public Course getCourse() {
      return course;
  }
  
  private Enrollment(String id, Student student, Course course) {
    this.id = id;
    this.student = student;
    this.course = course;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Enrollment enrollment = (Enrollment) obj;
      return ObjectsCompat.equals(getId(), enrollment.getId()) &&
              ObjectsCompat.equals(getStudent(), enrollment.getStudent()) &&
              ObjectsCompat.equals(getCourse(), enrollment.getCourse());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getStudent())
      .append(getCourse())
      .toString()
      .hashCode();
  }
  
  public static StudentStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   * @throws IllegalArgumentException Checks that ID is in the proper format
   **/
  public static Enrollment justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new Enrollment(
      id,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      student,
      course);
  }
  public interface StudentStep {
    CourseStep student(Student student);
  }
  

  public interface CourseStep {
    BuildStep course(Course course);
  }
  

  public interface BuildStep {
    Enrollment build();
    BuildStep id(String id) throws IllegalArgumentException;
  }
  

  public static class Builder implements StudentStep, CourseStep, BuildStep {
    private String id;
    private Student student;
    private Course course;
    @Override
     public Enrollment build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Enrollment(
          id,
          student,
          course);
    }
    
    @Override
     public CourseStep student(Student student) {
        Objects.requireNonNull(student);
        this.student = student;
        return this;
    }
    
    @Override
     public BuildStep course(Course course) {
        Objects.requireNonNull(course);
        this.course = course;
        return this;
    }
    
    /** 
     * WARNING: Do not set ID when creating a new object. Leave this blank and one will be auto generated for you.
     * This should only be set when referring to an already existing object.
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     * @throws IllegalArgumentException Checks that ID is in the proper format
     **/
    public BuildStep id(String id) throws IllegalArgumentException {
        this.id = id;
        
        try {
            UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
        } catch (Exception exception) {
          throw new IllegalArgumentException("Model IDs must be unique in the format of UUID.",
                    exception);
        }
        
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, Student student, Course course) {
      super.id(id);
      super.student(student)
        .course(course);
    }
    
    @Override
     public CopyOfBuilder student(Student student) {
      return (CopyOfBuilder) super.student(student);
    }
    
    @Override
     public CopyOfBuilder course(Course course) {
      return (CopyOfBuilder) super.course(course);
    }
  }
  
}
