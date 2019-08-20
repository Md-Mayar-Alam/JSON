package com;

public class DeepestLevelJSON {
	
	public static void main(String[] args) {
		
		//String input= "{\"name\":\"John\",\"age\":30,\"cars\":[{\"name\":\"Ford\",\"models\":[\"Fiesta\",\"Focus\",\"Mustang\"]},{\"name\":\"BMW\",\"models\":[\"320\",\"X3\",\"X5\"]},{\"name\":\"Fiat\",\"models\":[\"500\",\"Panda\"]}]}";
		String input= "[{\"name\":\"John\",\"age\":30,\"cars\":[{\"name\":\"Ford\",\"models\":[\"Fiesta\",\"Focus\",\"Mustang\"]},{\"name\":\"BMW\",\"models\":[\"320\",\"X3\",\"X5\"]},{\"name\":\"Fiat\",\"models\":[\"500\",\"Panda\"]}]},{\"name\":\"John\",\"age\":30,\"cars\":[{\"name\":\"Ford\",\"models\":[\"Fiesta\",\"Focus\",\"Mustang\"]},{\"name\":\"BMW\",\"models\":[\"320\",\"X3\",\"X5\"]},{\"name\":\"Fiat\",\"models\":[\"500\",\"Panda\"]}]}]";
		//String input= "{\"b\": {\"name\": {\"fName\": \"Hii\", \"lName\": \"Hello\"} }, \"a\": {\"name\": \"John\", \"age\": \"10\"} }";
		//String input= "[[[[]]]]";
		input= input.replace("\"", "");
		input= input.replace(" ", "");
		
		//{a: {name: John, age: 10}, b: {name: {fName: Hii, lName: Hello} } }
		//{a:{name:John,age:10},b:{name:{fName:Hii,lName:Hello}}}
		
		System.out.println(findMaxDepth(input));
	
	}
	
	static int findMaxDepth(String input) {
		int max= 0;
		int currentMax= 0;
		int length= input.length();
		
		for(int i=0; i< length; i++) {
			if(input.charAt(i) == '{' || input.charAt(i) == '[') {
				currentMax++;
				
				if(currentMax > max) {
					max= currentMax;
				}
			}else if(input.charAt(i) == '}' || input.charAt(i) == ']') {
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
