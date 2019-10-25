package com.test;

import com.model.list.queue.LinkedQueue;

public class QueueTest {
    public static void main(String[] args) {
        LinkedQueue<Integer> linkedQueue = new LinkedQueue<>();
        linkedQueue.add(2);
        linkedQueue.add(1);
        linkedQueue.add(3);
        System.out.println(linkedQueue.toString());
        System.out.println(linkedQueue.peek());
        System.out.println(linkedQueue);
    }
}
