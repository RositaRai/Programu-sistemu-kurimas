package it.vu.mybatis.dao;

import java.util.List;
import it.vu.mybatis.model.Student;
import org.mybatis.cdi.Mapper;

@Mapper
public interface StudentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.STUDENT
     *
     * @mbg.generated Thu May 04 19:32:59 EEST 2023
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.STUDENT
     *
     * @mbg.generated Thu May 04 19:32:59 EEST 2023
     */
    int insert(Student record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.STUDENT
     *
     * @mbg.generated Thu May 04 19:32:59 EEST 2023
     */
    Student selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.STUDENT
     *
     * @mbg.generated Thu May 04 19:32:59 EEST 2023
     */
    List<Student> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.STUDENT
     *
     * @mbg.generated Thu May 04 19:32:59 EEST 2023
     */
    int updateByPrimaryKey(Student record);

    /** Added manually **/
    Student selectByStudentId(Integer studentId);
}