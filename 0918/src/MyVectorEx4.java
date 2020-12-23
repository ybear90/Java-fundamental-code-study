/*
 * [문제4] 다음의 MyVector클래스의 메서드들을 완성하세요.
 * void add(int index, Object obj) - 객체배열의 지정된 위치(index)에 객체(obj)를 추가한다.
 * Object remove(int index) - 객체배열에서 index번째(index는 0부터 시작)에 있는 객체를 삭제한다.
 * boolean remove(Object obj) - 객체배열에서 지정된 객체(obj)를 찾아서 삭제한다. 삭제에 성공하면 true, 실패하면 false
 * void clear() - 객체배열을 비운다.
 * Object[] toArray() -  객체배열을 복사해서 반환한다.
 * String toString() - 객체배열에 저장된 모든 객체를 출력한다.(모든 객체의 toString()을 호출한다.)
 * [출처] [Java1000제] 객체배열 MyVector 구현하기 4 - add(), remove(), toArray() 등 (남궁성의 코드초보스터디(자바 java, c언어, javascript, python) | 작성자 남궁성
 */
public class MyVectorEx4 {

	public static void main(String[] args) {
		MyVector v = new MyVector(2);
		
		v.add("AAA");
		v.add("BBB");
		v.add("CCC");
		v.add("DDD");
		v.add(2, "EEE");
		
		Object[] objArr = v.toArray();
		
		for (int i = 0; i < objArr.length; i++) {
			System.out.print(v.get(i)+",");
		}
		System.out.println();
		
		System.out.println("size:"+v.size());
		System.out.println("capacity:"+v.capacity());
		System.out.println("isEmpty:"+v.isEmpty());
		System.out.println();
		
		v.remove(1);
		v.remove("CCC");
		
		System.out.println(v); // System.out.println(v.toString());
		System.out.println("size:"+v.size());
		System.out.println("capacity:"+v.capacity());
		System.out.println("isEmpty:"+v.isEmpty());
		System.out.println();
		
		v.clear();
		System.out.println(v);
		System.out.println("size:"+v.size());
		System.out.println("capacity:"+v.capacity());
		System.out.println("isEmpty:"+v.isEmpty());
	}

}
