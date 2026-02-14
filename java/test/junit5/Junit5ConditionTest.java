package com.njust.junit5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

public class Junit5ConditionTest {

    @Test
    @EnabledOnOs({OS.WINDOWS})
    public void onOnWindows() {
    }

    @Test
    @EnabledOnJre(JRE.JAVA_21)
    public void onlyOnJava21() {
    }

    @Test
    @EnabledIfSystemProperty(named = "user.country", matches = "CN")
    public void onInChina() {
    }

    @Test
    @EnabledIfEnvironmentVariable(named = "ENV", matches = "dev")
    public void onInDevEnvironment() {
    }


}
