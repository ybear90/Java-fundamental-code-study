/*
 * [문제3] 다음의 MyVector클래스의 메서드들을 완성하세요.
 * boolean contains(Object obj) - 지정된 객체(obj)가 객체배열에 존재하는지 확인한다. 있으면 true, 없으면 false
 * int indexOf(Object obj) - 지정된 객체의 위치(index)를 찾아서 반환한다. 객체배열에 없으면 -1을 반환
 * (객체배열의 첫번째 요소부터 찾기 시작한다.)
 * int lastIndexOf(Object obj) - 지정된 객체의 위치(index)를 찾아서 반환한다. 객체배열에 없으면 -1을 반환
 * (객체배열의 마지막 요소부터 역순으로 찾기 시작한다.)
 * int indexOf(Object obj, int index) - 지정된 객체를 지정한 위치(index)부터 찾기 시작한다. 객체배열에 없으면 -1을 반환
 * int lastIndexOf(Object obj, int index)  - 지정된 객체를 지정한 위치(index)부터 찾기 시작한다. 객체배열에 없으면 -1을 반환
 * (역순으로 찾기 시작한다.)
 * [출처] [Java1000제] 객체배열 MyVector 구현하기 3 - indexOf(), contains() 등 (남궁성의 코드초보스터디(자바 java, c언어, javascript, python) | 작성자 남궁성
*/
public class MyVectorEx3 {
	
	public static void main(String[] args) {
		MyVector v = new MyVector(2);
		
		v.add("AAA");
		v.add("BBB");
		v.add("CCC");
		v.add("BBB");
		
		for (int i = 0; i < v.size(); i++) {
			System.out.println(i+":"+v.get(i));
		}
		
		System.out.println("indexOf BBB:"+v.indexOf("BBB"));
		System.out.println("lastIndexOf BBB:"+v.lastIndexOf("BBB"));
		System.out.println("contains BBB:"+v.contains("BBB"));
		
	}// main

}
