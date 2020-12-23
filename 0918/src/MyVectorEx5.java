/*
 * [문제5] MyVector클래스에 iterator()를 완성하세요. 그리고 이 메서드를 구현하는데 필요한, Iterator인터페이스를 구현한 내부 클래스를 완성하세요.
 * Iterator iterator() - MyVector클래스의 iterator를 반환한다.(Iterator인터페이스를 구현한 클래스의 인스턴스를 반환)
 * 
 * << Iterator인터페이스에 정의된 메서드 >>
 * boolean hasNext() - 다음에 읽을 요소가 있는지 확인한다. 있으면 true, 없으면 false
 * Object next() -  다음 요소를 읽어서 반환한다.
 * void remove() -  읽어온 요소를 제거한다.(반드시 next()를 호출한 다음에 호출해야한다.
 * [참고] iterator를 통해 MyVector의 요소들에 접근하는 동안, MyVector의 요소가 추가또는 삭제 되면 iterator는 예외를
 * 발생시켜야한다. 여기서 그 것(fast-fail)에 대한 구현은 생략했다.
 * Vector클래스에는 구현되어 있으니, 필요하다면 Vector클래스의 소스를 참고하도록 하자.
 * [출처] [Java1000제] 객체배열 MyVector 구현하기 5 - iterator() (남궁성의 코드초보스터디(자바 java, c언어, javascript, python) | 작성자 남궁성
 */
import java.util.Iterator;

public class MyVectorEx5 {

	public static void main(String[] args) {
		MyVector v = new MyVector(2);
		
		v.add("AAA");
		v.add("BBB");
		v.add("CCC");
		v.add("DDD");
		
		System.out.println("Before: "+v);
		
		Iterator it = v.iterator();
		
		while (it.hasNext()) {
			Object obj = it.next();
			System.out.println(obj);
			
			if (obj.equals("BBB"))
				it.remove();
		}
		
		System.out.println(v);
	} // main

}
