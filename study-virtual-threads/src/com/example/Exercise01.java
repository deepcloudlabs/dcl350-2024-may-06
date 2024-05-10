package com.example;

@SuppressWarnings("unused")
public class Exercise01 {

	public static void main(String[] args) {
		var thread1 = Thread.ofPlatform(); // kernel -> parallel/multi-core programming
		var thread2 = Thread.ofVirtual(); // jvm -> io

	}

}
