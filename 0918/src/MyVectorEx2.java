/*
 * [문제2] 다음의 MyVector클래스의 메서드들을 완성하세요.
	void ensureCapacity(int minCapacity) - 객체배열이 최소한 지정된 용량을 확보하도록 한다.
	void setCapacity(int newCapacity)   - 객체배열의 용량을 지정된 용량(newCapacity)으로 변경한다.
	boolean add(Object obj) - 객체배열에 객체(obj)를 저장한다.
	Object get(int index) - 객체배열에 저장된, index번째의 객체를 반환한다.
	Object set(int index, Object obj) - 객체배열의 index번째의 위치에 객체(obj)를 저장하고, 기존의 객체를 반환한다.
*/
public class MyVectorEx2 {
	
	public static void main(String[] args) {
		MyVector v = new MyVector(2);
		
		v.add("AAA");
		v.add("BBB");
		v.add("CCC");
		
		for (int i = 0; i < v.size(); i++) {
			System.out.println(v.get(i));
		}
		
		System.out.println("size:"+v.size());
		System.out.println("capacity:"+v.capacity());
		System.out.println("isEmpty:"+v.isEmpty());
		
		v.set(0, "aaa");
		
		for (int i = 0; i < v.size(); i++) {
			System.out.println(v.get(i));
		}
	}

}
