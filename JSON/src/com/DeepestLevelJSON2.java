package com;

import java.util.Stack;

public class DeepestLevelJSON2 {
	
	public static void main(String[] args) {
		
		//String input= "[{}]";
		//String input= "{[]";
		//String input= "[] {}";
		//String input= "{\"name\": [\"1\",\"2\"}]";
		//String input= "{\"name\":\"John\",\"age\":30,\"cars\":[{\"name\":\"Ford\",\"models\":[\"Fiesta\",\"Focus\",\"Mustang\"]},{\"name\":\"BMW\",\"models\":[\"320\",\"X3\",\"X5\"]},{\"name\":\"Fiat\",\"models\":[\"500\",\"Panda\"]}]}";
		//String input= "[{\"name\":\"John\",\"age\":30,\"cars\":[{\"name\":\"Ford\",\"models\":[\"Fiesta\",\"Focus\",\"Mustang\"]},{\"name\":\"BMW\",\"models\":[\"320\",\"X3\",\"X5\"]},{\"name\":\"Fiat\",\"models\":[\"500\",\"Panda\"]}]},{\"name\":\"John\",\"age\":30,\"cars\":[{\"name\":\"Ford\",\"models\":[\"Fiesta\",\"Focus\",\"Mustang\"]},{\"name\":\"BMW\",\"models\":[\"320\",\"X3\",\"X5\"]},{\"name\":\"Fiat\",\"models\":[\"500\",\"Panda\"]}]}]";
		//String input= "{\"b\": {\"name\": {\"fName\": \"Hii\", \"lName\": \"Hello\"} }, \"a\": {\"name\": \"John\", \"age\": \"10\"} }";
		//String input= "[[[[]]]]";
		String input= "{\"a\": {\"name\": \"John\", \"age\": \"10\"} }";
		input= input.replace("\"", "");
		input= input.replace(" ", "");
		
		//{a: {name: John, age: 10}, b: {name: {fName: Hii, lName: Hello} } }
		//{a:{name:John,age:10},b:{name:{fName:Hii,lName:Hello}}}
		
		System.out.println(findMaxDepth(input));
	
	}
	
	static int findMaxDepth(String input) {
		
		char firstChar= input.charAt(0);
		
		if(firstChar == '{') {
			int lastIndex= input.lastIndexOf("}");
			String newString= input.substring(lastIndex+1);
			if(newString.length() > 0) {
				if(newString.indexOf('{') != -1 || newString.indexOf('[') != -1){
					throw new RuntimeException("Invalid JSON");
				}
			}
		}else if(firstChar == '[') {
			int lastIndex= input.lastIndexOf("]");
			String newString= input.substring(lastIndex+1);
			if(newString.length() > 0) {
				if(input.indexOf('{') != -1 || input.indexOf('[') != -1){
					throw new RuntimeException("Invalid JSON");
				}
			}
		}
		
		int max= 0;
		int currentMax= 0;
		int length= input.length();
		
		Stack<Character> stack= new Stack<>();
		
		for(int i=0; i< length; i++) {
			
			char currentChar= input.charAt(i);
			
			if(currentChar == '{') {
				currentMax++;
				if(currentMax > max) {
					max= currentMax;
				}
				stack.push('}');
			}else if(currentChar == '[') {
				currentMax++;
				if(currentMax > max) {
					max= currentMax;
				}
				stack.push(']');
			}else if(currentChar == '}'|| currentChar == ']') {
				if(stack.isEmpty() || currentChar != stack.peek()) {
					throw new RuntimeException("Invalid JSON");
				}
				stack.pop();
				if(currentMax > 0) {
					currentMax--;
				}else {
					throw new RuntimeException("Invalid JSON");
				}
			}
		}
		
		if(currentMax != 0) {
			throw new RuntimeException("Invalid JSON");
		}
		return max;
	}
}
