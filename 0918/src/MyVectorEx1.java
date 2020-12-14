/*
 * 객체배열을 기반으로 하는 Vector클래스를 직접 구현해보는 문제 입니다.
	[문제1] 다음의 MyVector클래스의 메서드들을 완성하세요.
	MyVector(int capacity) - 매개변수 capacity의 값을 용량으로 갖는 객체배열을 생성한다.
	MyVector() - 매개변수 없는 생성자. 기본적으로 용량이 10인 객체배열을 생성한다.
	boolean isEmpty() - 객체배열이 비어있는지 확인하고 비어있으면 true, 그렇지 않으면 false를 반환한다.
	int size() - 객체배열의 저장되어 있는 객체의 개수를 반환한다.
	int capacity() - 객체배열의 용량(배열의 크기)을 반환한다.
 */
public class MyVectorEx1 {
	
	public static void main(String[] args) {
		MyVector v = new MyVector();
		
		System.out.println("size:"+v.size());
		System.out.println("capacity:"+v.capacity());
		System.out.println("isEmpty:"+v.isEmpty());
	}

}
