package com.njust.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.util.StdConverter;
import lombok.Getter;
import lombok.Setter;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class JsonDeserializeContentConverterTest {

    @Setter
    @Getter
    private static class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    @Getter
    private static class PointWrapper {
        @JsonDeserialize(converter = PointConverter.class)
        Point point;
    }

    static class PointListWrapperArray {
        @JsonDeserialize(contentConverter = PointConverter.class)
        private Point[] values;
    }

    static class PointListWrapperList {
        @JsonDeserialize(contentConverter = PointConverter.class)
        private List<Point> values;
    }

    static class PointListWrapperMap {
        @JsonDeserialize(contentConverter = PointConverter.class)
        private Map<String, Point> values;
    }

    private static class PointConverter extends StdConverter<int[], Point> {

        @Override
        public Point convert(int[] value) {
            return new Point(value[0], value[1]);
        }
    }

    @Test
    public void testConverter() throws JsonProcessingException {
        String jsonStr = "{\"point\":[11,22]}";
        PointWrapper pointWrapper = new ObjectMapper().readValue(jsonStr, PointWrapper.class);

        Point point = pointWrapper.getPoint();
        Assert.assertNotNull(point);
        Assert.assertEquals(11, point.x);
        Assert.assertEquals(22, point.y);
    }

    @Test
    public void testConverterForArray() throws JsonProcessingException {
        String jsonStr = "{\"values\":[[11,22],[33,44]]}";
        PointListWrapperArray pointListWrapperArray = new ObjectMapper().readValue(jsonStr, PointListWrapperArray.class);

        Point[] values = pointListWrapperArray.values;
        Assert.assertNotNull(values);
        Assert.assertEquals(2, values.length);
        Assert.assertEquals(11, values[0].getX());
        Assert.assertEquals(22, values[0].getY());
        Assert.assertEquals(33, values[1].getX());
        Assert.assertEquals(44, values[1].getY());
    }

    @Test
    public void testConverterForList() throws JsonProcessingException {
        String jsonStr = "{\"values\":[[11,22],[33,44]]}";
        PointListWrapperList pointListWrapperList = new ObjectMapper().readValue(jsonStr, PointListWrapperList.class);

        List<Point> values = pointListWrapperList.values;
        Assert.assertNotNull(values);
        Assert.assertEquals(2, values.size());
        Assert.assertEquals(11, values.get(0).getX());
        Assert.assertEquals(22, values.get(0).getY());
        Assert.assertEquals(33, values.get(1).getX());
        Assert.assertEquals(44, values.get(1).getY());
    }

    @Test
    public void testConverterForMap() throws JsonProcessingException {
        String jsonStr = "{\"values\":{\"doNotCare\":[666,777],\"doNotCare2\":[888,999]}}";
        PointListWrapperMap pointListWrapperMap = new ObjectMapper().readValue(jsonStr, PointListWrapperMap.class);

        Map<String, Point> values = pointListWrapperMap.values;
        Assert.assertNotNull(values);
        Assert.assertEquals(2, values.size());

        Point point1 = values.get("doNotCare");
        Assert.assertNotNull(point1);
        Assert.assertEquals(666, point1.x);
        Assert.assertEquals(777, point1.y);

        Point point2 = values.get("doNotCare2");
        Assert.assertNotNull(point2);
        Assert.assertEquals(888, point2.x);
        Assert.assertEquals(999, point2.y);
    }
}
