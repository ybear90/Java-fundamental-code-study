
public class MyVector {
	protected Object[] data = null; // 객체를 담기 위한 객체배열을 선언한다.
	protected int capacity = 0; // 용량(객체배열의 크기)
	protected int size = 0; // 객체배열에 저장된 객체의 개수
	
	// Q1.
	public MyVector(int capacity) {
		/*
		 * 다음의 코드를 완성하시
		 */
		// 1. 지역변수 capacity의 값이 0보다 작으면, IllegalArgumentException을 발생시킨다.
		// Q1. Ans: IllegalArgumentException에 문자열과 값을 추가
		if (capacity < 0) throw new IllegalArgumentException("유효하지 않은 값입니다."+capacity);
		// 2. 지역번수 capacity의 값을 인스턴스변수 capacity에 저장한다.
		this.capacity = capacity;
		// 3. 지역변수 capacity의 같은 크기의 Object 배열을 생성해서 객체배열 data에 저장한다.
		data = new Object[capacity];
	}
	
	// Q1.
	public MyVector() {
		// 1. 매개변수가 없는 생성자를 통해 MyVector를 생성한다면.
		// 용량(capacity)가 10이 되도록 한다. 매개변수가 있는 생성자 MyVector(int capacity)를 활용할 것
		this(10);
	}
	
	// Q1.
	public boolean isEmpty() {
		return size == 0;
	}
	
	// Q1.
	public int capacity() {
		return capacity;
	}
	
	// Q1.
	public int size() {
		return size;
	}
	
	// Q2.
	public void ensureCapacity(int minCapacity) {
		/*
		 * 다음의 코드를 완성하시오
		 *
		 */
		int newCapacity = capacity;
		// 1. minCapacity가 capacity보다 크면 newCapacity의 값을 두배로 한다.
		// (사실 반드시 2배이어야 할 필요는 없다. 적절한 비율로 크기를 늘려주기 위한 것임)
		if (minCapacity > capacity) newCapacity *= 2;
		
		// 2. 그래도 minCapacity가 newCapacity보다 크면, minCapacity의 값을 newCapacity에 저장한다.
		if (minCapacity > newCapacity) newCapacity = minCapacity;
		
		// 3. setCapacity()를 호출해서 객체배열의 크기가 newCapacity가 되도록 한다.
		setCapacity(newCapacity);
	}
	
	// Q2.
	public void setCapacity(int newCapacity) {
		if (this.capacity == newCapacity) return;
		/*
		 * 다음의 코드를 완성하시오.
		 */
		
		 // 1. newCapacity 크기의 객체배열을 새로 만든다.
		 Object[] newData = new Object[newCapacity];
		 // 2. 기존의 객체배열(data)의 내용을 새로운 객체배열에 복사한다. (System.arraycopy() 사용)
		 System.arraycopy(data, 0, newData, 0, data.length);
		 
		 // 3. data가 새로 생성된 객체배열을 참조하도록 한다.
		 data = newData;
		 // 4. capacity의 값을 newCapacity로 변경한다.
		 capacity = newCapacity;
	}
	
	// Q2
	public boolean add(Object obj) {
		// 새로운 객체를 저장하기 전에 저장할 공간을 확보한 후에 객체를 추가.
		// 객체의 갯수와 capacity 가 같다면 -> setCapacity를 사용해서 늘린다
		if (size == capacity) {
			if (capacity == 0) {
				setCapacity(size+1);
			} else {
				setCapacity(size*2);
			}
		}
		
		data[size++] = obj;
		
		return size < capacity; // 여유가 있다면 true, 꽉 찼다면 false
	}
	
	// Q2
	public Object get(int index) {
		// index에 대한 유효성 검사 + isEmpty()에 대한 체크
		if (index < 0 || isEmpty()) return null;
		
		return data[index];
	}
	
	// Q2
	public Object set(int index, Object obj) {
		/*
		 * 다음의 코드를 완성하시오.
		 */
		
		 // 1. index가 size보다 크면 ArrayIndexOutOfBoundsException을 발생시킨다.
		 if (index > size) throw new ArrayIndexOutOfBoundsException("유효하지 않은 index입니다: "+index);
		 // 2. 객체배열 data의 index번째 값을 임시로 저장한다.
		 Object temp = data[index];
		 // 3. 새로운 객체(obj)를 객체배 data의 index번째 값에 저장한다.
		 data[index] = obj;
		 // 4. 임시로 저장했던 기존 객체를 반환한다.
		 return temp;
	}
}
