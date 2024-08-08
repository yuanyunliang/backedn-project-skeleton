package com.orange.eduback.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 
 * @TableName course
 */
@TableName(value ="course")
public class Course implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private String courseName;

    /**
     * 
     */
    private String courseDescription;

    /**
     * 
     */
    private String courseType;

    /**
     * 
     */
    private String courseDuration;

    /**
     * 
     */
    private BigDecimal courseFee;

    /**
     * 
     */
    private String courseUrl;

    /**
     * 
     */
    private String courseStatus;

    /**
     * 
     */
    private LocalDateTime createdAt;

    /**
     * 
     */
    private LocalDateTime updatedAt;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * 
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * 
     */
    public String getCourseDescription() {
        return courseDescription;
    }

    /**
     * 
     */
    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    /**
     * 
     */
    public String getCourseType() {
        return courseType;
    }

    /**
     * 
     */
    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    /**
     * 
     */
    public String getCourseDuration() {
        return courseDuration;
    }

    /**
     * 
     */
    public void setCourseDuration(String courseDuration) {
        this.courseDuration = courseDuration;
    }

    /**
     * 
     */
    public BigDecimal getCourseFee() {
        return courseFee;
    }

    /**
     * 
     */
    public void setCourseFee(BigDecimal courseFee) {
        this.courseFee = courseFee;
    }

    /**
     * 
     */
    public String getCourseUrl() {
        return courseUrl;
    }

    /**
     * 
     */
    public void setCourseUrl(String courseUrl) {
        this.courseUrl = courseUrl;
    }

    /**
     * 
     */
    public String getCourseStatus() {
        return courseStatus;
    }

    /**
     * 
     */
    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }

    /**
     * 
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * 
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * 
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 
     */
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Course other = (Course) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCourseName() == null ? other.getCourseName() == null : this.getCourseName().equals(other.getCourseName()))
            && (this.getCourseDescription() == null ? other.getCourseDescription() == null : this.getCourseDescription().equals(other.getCourseDescription()))
            && (this.getCourseType() == null ? other.getCourseType() == null : this.getCourseType().equals(other.getCourseType()))
            && (this.getCourseDuration() == null ? other.getCourseDuration() == null : this.getCourseDuration().equals(other.getCourseDuration()))
            && (this.getCourseFee() == null ? other.getCourseFee() == null : this.getCourseFee().equals(other.getCourseFee()))
            && (this.getCourseUrl() == null ? other.getCourseUrl() == null : this.getCourseUrl().equals(other.getCourseUrl()))
            && (this.getCourseStatus() == null ? other.getCourseStatus() == null : this.getCourseStatus().equals(other.getCourseStatus()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCourseName() == null) ? 0 : getCourseName().hashCode());
        result = prime * result + ((getCourseDescription() == null) ? 0 : getCourseDescription().hashCode());
        result = prime * result + ((getCourseType() == null) ? 0 : getCourseType().hashCode());
        result = prime * result + ((getCourseDuration() == null) ? 0 : getCourseDuration().hashCode());
        result = prime * result + ((getCourseFee() == null) ? 0 : getCourseFee().hashCode());
        result = prime * result + ((getCourseUrl() == null) ? 0 : getCourseUrl().hashCode());
        result = prime * result + ((getCourseStatus() == null) ? 0 : getCourseStatus().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", courseName=").append(courseName);
        sb.append(", courseDescription=").append(courseDescription);
        sb.append(", courseType=").append(courseType);
        sb.append(", courseDuration=").append(courseDuration);
        sb.append(", courseFee=").append(courseFee);
        sb.append(", courseUrl=").append(courseUrl);
        sb.append(", courseStatus=").append(courseStatus);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}