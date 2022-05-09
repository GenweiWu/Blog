import com.zte.iot.domain.StudentEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

/**
 * 测试Optional的map、filter、flatmap方法
 */
public class OptionalTest {

    @Test
    public void test01_a() {
        StudentEntity studentEntity = new StudentEntity(1, "11", 111);

        final Optional<StudentEntity> optional = Optional.ofNullable(studentEntity);
        //map方法
        Integer actual = optional.map(StudentEntity::getAge).orElse(-1);
        Assertions.assertEquals(111, actual);

        //filter方法
        Assertions.assertTrue(optional.filter(s -> s.getAge() > 1).isPresent());
        Assertions.assertFalse(optional.filter(s -> s.getAge() > 1000).isPresent());
    }

    @Test
    public void test01_b() {
        StudentEntity studentEntity = null;

        final Optional<StudentEntity> optional = Optional.ofNullable(studentEntity);
        //map方法
        Integer actual = optional.map(StudentEntity::getAge).orElse(-1);
        Assertions.assertEquals(-1, actual);

        //filter方法
        Assertions.assertFalse(optional.filter(s -> s.getAge() > 1).isPresent());
        Assertions.assertFalse(optional.filter(s -> s.getAge() > 1000).isPresent());
    }

    @Test
    public void test_flatmap_01() {
        //测试flatmap:
        //Use map if the function returns the object
        //Use flatMap if the function returns an Optional.
        StudentEntity studentEntity = new StudentEntity(1, "11", 21);
        final Optional<StudentEntity> optional = Optional.ofNullable(studentEntity);

        boolean actual = optional.flatMap(s -> {
            //flatmap是返回Optional
            if (s.getAge() > 18) {
                return Optional.of(s);
            }
            return Optional.empty();
        }).isPresent();
        Assertions.assertTrue(actual);

        boolean actual222 = optional.map(s -> {
            //map直接返回对象
            if (s.getAge() > 18) {
                return s;
            }
            return null;

        }).isPresent();
        Assertions.assertTrue(actual222);


    }
}
