package com.njust.learn;

public class CaptureDemo {

    public void hello(int num) {
        sayMsg("hello world" + num);
        staticMsg("static:" + num);

        for (int j = 1; j <= num; j++) {
            sayRepeat("repeat:" + j);
        }

        sayNode(new Node(num, "node:" + num));
    }

    protected void sayMsg(String name) {
        System.out.println(name);
    }

    protected static void staticMsg(String msg) {
        System.out.println(msg);
    }

    protected void sayRepeat(String msg) {
        System.out.println(msg);
    }

    protected void sayNode(Node node) {
        System.out.println(node);
    }


    static class Node {
        private final int index;
        private final String name;

        public Node(int index, String name) {
            this.index = index;
            this.name = name;
        }

        public int getIndex() {
            return index;
        }

        public String getName() {
            return name;
        }
    }
}
