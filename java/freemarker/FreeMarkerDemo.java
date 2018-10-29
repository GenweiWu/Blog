package com.njust.test.freemarker;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreeMarkerDemo
{
    @Test
    public void test01()
        throws IOException, TemplateException
    {
        File dir = new File("D:\\2222\\freemarker");

        Configuration configuration = new Configuration();
        configuration.setDirectoryForTemplateLoading(dir);

        Template template = configuration.getTemplate("111.ftl");
        StringWriter stringWriter = new StringWriter();
        Map<String, Object> dataMap = getDataMap();
        template.process(dataMap, stringWriter);

        System.out.println(stringWriter.toString());
    }

    private Map<String, Object> getDataMap()
    {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("name", "dave");

        List<String> weeks = new ArrayList<>();
        weeks.add("monday");
        weeks.add("tuesday");
        weeks.add("wednesday");
        dataMap.put("weeks", weeks);

        Map<String, String> scoreMap = new HashMap<>();
        scoreMap.put("key1", "value111");
        scoreMap.put("key2", "value222");
        scoreMap.put("key3", "value333");
        dataMap.put("scoreMap", scoreMap);

        return dataMap;
    }
}
