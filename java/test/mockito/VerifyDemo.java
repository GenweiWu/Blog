package com.njust.learn;

import java.io.File;
import java.util.UUID;

public class VerifyDemo {

    public void work(int a, int b) {
        for (int i = a; i <= b; i++) {
            privateMethod(i);
            staticMethod(i);
            noPrivateNoStatic(i);
        }
    }

    public void noPrivateNoStatic(int value) {
        System.out.println("normalMethod:" + value);
    }

    private void privateMethod(int value) {
        System.out.println("privateMethod:" + value);
    }

    public static void staticMethod(int value) {
        System.out.println("staticMethod:" + value);
        staticMethod222(value);
        staticMethod222(value);
    }

    public static void staticMethod222(int value) {
        System.out.println("staticMethod222:" + value);
    }

    public String newNode(boolean needCreateFile) {
        if (needCreateFile) {
            VerifyNode verifyNode = new VerifyNode();
            return verifyNode.getNodeName();
        }
        return null;
    }

    public String newFile(boolean needCreateFile) {
        if (needCreateFile) {
            File file = new File(UUID.randomUUID().toString());
            String name = file.getName();
            System.out.println("getFileName with name:" + name);
            return name;
        }
        System.out.println("getFileName exit");
        return null;
    }

    public static class VerifyNode {
        private final String nodeName;

        public VerifyNode() {
            this.nodeName = UUID.randomUUID().toString();
        }

        public String getNodeName() {
            return nodeName;
        }
    }

}
