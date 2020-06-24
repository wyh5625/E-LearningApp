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

/** This is an auto generated class representing the Video type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Videos")
public final class Video implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField NAME = field("name");
  public static final QueryField URL = field("url");
  public static final QueryField COURSE = field("videoCourseId");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String name;
  private final @ModelField(targetType="String", isRequired = true) String url;
  private final @ModelField(targetType="Course", isRequired = true) @BelongsTo(targetName = "videoCourseId", type = Course.class) Course course;
  public String getId() {
      return id;
  }
  
  public String getName() {
      return name;
  }
  
  public String getUrl() {
      return url;
  }
  
  public Course getCourse() {
      return course;
  }
  
  private Video(String id, String name, String url, Course course) {
    this.id = id;
    this.name = name;
    this.url = url;
    this.course = course;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Video video = (Video) obj;
      return ObjectsCompat.equals(getId(), video.getId()) &&
              ObjectsCompat.equals(getName(), video.getName()) &&
              ObjectsCompat.equals(getUrl(), video.getUrl()) &&
              ObjectsCompat.equals(getCourse(), video.getCourse());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getName())
      .append(getUrl())
      .append(getCourse())
      .toString()
      .hashCode();
  }
  
  public static NameStep builder() {
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
  public static Video justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new Video(
      id,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      name,
      url,
      course);
  }
  public interface NameStep {
    UrlStep name(String name);
  }
  

  public interface UrlStep {
    CourseStep url(String url);
  }
  

  public interface CourseStep {
    BuildStep course(Course course);
  }
  

  public interface BuildStep {
    Video build();
    BuildStep id(String id) throws IllegalArgumentException;
  }
  

  public static class Builder implements NameStep, UrlStep, CourseStep, BuildStep {
    private String id;
    private String name;
    private String url;
    private Course course;
    @Override
     public Video build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Video(
          id,
          name,
          url,
          course);
    }
    
    @Override
     public UrlStep name(String name) {
        Objects.requireNonNull(name);
        this.name = name;
        return this;
    }
    
    @Override
     public CourseStep url(String url) {
        Objects.requireNonNull(url);
        this.url = url;
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
    private CopyOfBuilder(String id, String name, String url, Course course) {
      super.id(id);
      super.name(name)
        .url(url)
        .course(course);
    }
    
    @Override
     public CopyOfBuilder name(String name) {
      return (CopyOfBuilder) super.name(name);
    }
    
    @Override
     public CopyOfBuilder url(String url) {
      return (CopyOfBuilder) super.url(url);
    }
    
    @Override
     public CopyOfBuilder course(Course course) {
      return (CopyOfBuilder) super.course(course);
    }
  }
  
}
