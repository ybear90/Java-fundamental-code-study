import java.util.Iterator;

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
		//if (size == capacity) {
		//	if (capacity == 0) {
		//		setCapacity(size+1);
		//	} else {
		//		setCapacity(size*2);
		//	}
		//}
		
		// Q4. ans
		ensureCapacity(size+1);
		
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
	
	// Q3.
	public int indexOf(Object obj, int index) {
		/*
		 * 다음의 코드를 완성하세요.
		 * 
		 * 1. 넘겨받은 객체(obj)가 null이면,
		 * 	1.1 반복문을 이용해서 객체배열(data)에서 null인 것을 찾아서 그 위치를 반환한다.
		 *  (검색순서는 index부터 시작해서 증가하는 방향)
		 * 2. 넘겨밭은 객체(obj)가 null이 아닌 경우에는
		 * 	2.1 equals를 이용해서 같은 객체가 있는지 찾아서 그 위치를 반환한다.
		 * 	(검색순서는 index부터 시작해서 증가하는 방향)
		 * 3. 못찾으면 -1을 반환한다.
		 */
		// 1. 넘겨받은 객체(obj)가 null이면,
		// 1.1 반복문을 이용해서 객체배열(data)에서 null인 것을 찾아서 그 위치를 반환한다.
		if (obj == null) {
			for (int i = index; i < size; i++) {
				if (data[i] == null) return i;
			}
		} else {
			for (int i = index; i < size; i++) {
				if (data[i].equals(obj)) return i;
			}
		}
		
		return -1;
	}
	
	// Q3.
	public int lastIndexOf(Object obj, int index) {
		/*
		 *  다음의 코드를 완성하시오.
		 *  1. index의 값이 size보다 같거나 크면, IndexOutOfBoundsException을 발생시킨다.
		 *  2. 넘겨받은 객체(obj)가 null 이면,
		 *    2.1 반복문을 이용해서 객체배열(data)에서 null인 것을 찾아서 그 위치를 반환한다.
		 *    (검색순서는 index부터 시작해서, index값을 감소시켜서 객체배열의 0번째까지)
		 *  3. 넘겨받은 객체(obj)가 null이 아닌 경우에는
		 *    3.1 equals를 이용해서 같은 객체가 있는지 찾아서 그 위치를 반환한다.
		 *    (검색순서는 index부터 시작해서, index값을 감소시켜서 객체배열의 0번째까지)
		 *  4. 못찾으면 -1을 반환한다.
		 */
		 // 1. index의 값이 size보다 같거나 크면, IndexOutOfBoundsException을 발생시킨다.
		 if (index >= size) {
			 throw new IndexOutOfBoundsException("유효하지 않은 index입니다: "+index);
		 } else if (obj == null) { //2. 넘겨받은 객체(obj)가 null 이면,
			 // 2.1 반복문을 이용해서 객체배열(data)에서 null인 것을 찾아서 그 위치를 반환한다.
			 // (검색순서는 index부터 시작해서, index값을 감소시켜서 객체배열의 0번째까지)
			 for (int i = index; i >=0 ; i--) {
				 if (data[i] == null) return i;
			 }
		 } else { // 3. 넘겨받은 객체(obj)가 null이 아닌 경우에는
			 // 3.1 equals를 이용해서 같은 객체가 있는지 찾아서 그 위치를 반환한다.
			 // (검색순서는 index부터 시작해서, index값을 감소시켜서 객체배열의 0번째까지)
			 for (int i = index; i >=0; i--) {
				 if (data[i].equals(obj)) return i;
			 }
		 }
		
		return -1;
	}
	
	// Q3.
	public boolean contains(Object obj) {
		/*
		 * 코드를완성하세요. indexOf(Object obj, int index)를 사용
		 */
		// return indexOf(obj) != -1;
		return indexOf(obj) >= 0;
	}
	
	// Q3.
	public int indexOf(Object obj) {
		return indexOf(obj, 0);
	}
	
	// Q3.
	public int lastIndexOf(Object obj) {
		//return lastIndexOf(obj, data.length - 1);
		return lastIndexOf(obj, size-1);
	}
	
	// Q4.
	public void add(int index, Object obj) {
		/*
		 * 다음의 코드를 완성하세요.
		 * 
		 * 1. index의 값이 size보다 크면, ArrayIndexOutOfBoundsException
		 * 2. ensureCapacity()를 호출해서 새로운 객체가 저장될 공간을 확보한다.
		 * 3. 객체배열에서 index위치의 객체와 이후의 객체들을 한칸씩 옆으로 이동한다. 
		 *   (System.arraycopy()사용)
		 * 4. 객체배열의 index위치에 새로운 객체(obj)를 저장한다.
		 * 5. size의 값을 1 증가시킨다.
		 * 
		 */
		// 1. index의 값이 size보다 크면, ArrayIndexOutOfBoundsException
		if (index > size) throw new ArrayIndexOutOfBoundsException("유효하지 않은 index입니다: "+index);
		
		// 2. ensureCapacity()를 호출해서 새로운 객체가 저장될 공간을 확보한다.
		ensureCapacity(size+1);
		
		// 3. 객체배열에서 index위치의 객체와 이후의 객체들을 한칸씩 옆으로 이동한다. (System.arraycopy()사용)
		System.arraycopy(data, index, data, index+1, size-index);
		
		// 4. 객체배열의 index위치에 새로운 객체(obj)를 저장한다.
		data[index] = obj;
		
		// 5. size의 값을 1 증가시킨다.
		size++;
	}
	
	// Q4.
	public Object remove(int index) {
		Object oldObj = null;
		
		/*
		 *  다음의 코드를 완성하시오
		 *  
		 *  1. index가 배열의 범위를 벗어나는지 체크하고, 벗어나면 IndexOutOfBoundsException를 발생시킨다.
		 *  2. 삭제하고자하는 데이터를 oldObj에 저장한다.
		 *  3. 삭제하고자 하는 객체가 마지막 객체가 아니라면, 배열복사를 통해 빈자리를 채워준다.
		 *  4. 마지막 데이터를 null로 한다. 배열은 0부터 시작하므로 마지막 요소는 index가 size-1이다.
		 *  5. size의 값을 1 감소시킨다.
		 *  6. oldObj를 반환한다.
		 */
		// 1. index가 배열의 범위를 벗어나는지 체크하고, 벗어나면 IndexOutOfBoundsException를 발생시킨다.
		if (index >= size) throw new IndexOutOfBoundsException("유효하지 않은 index입니다: "+index);
		// 2. 삭제하고자하는 데이터를 oldObj에 저장한다.
		oldObj = data[index];
		// 3. 삭제하고자 하는 객체가 마지막 객체가 아니라면, 배열복사를 통해 빈자리를 채워준다.
		System.arraycopy(data, index+1, data, index, size-index-1);
		
		// 4. 마지막 데이터를 null로 한다. 배열은 0부터 시작하므로 마지막 요소는 index가 size-1이다.
		data[--size] = null;
		
		return oldObj;
	}
	
	// Q4.
	public boolean remove(Object obj) {
		/*
		 *  다음의 코드를 완성하세오.
		 *  
		 *  1. 반복문을 이용해서 객체배열의 모든 요소와 obj가 일치하는지 확인한다.
		 *  	1.1 일치하면 remove(int index)를 호출해서 삭제하고 true를 반환한다.
		 *  	1.2 일치하는 것을 찾지 못하면, false를 반환한다.
		 */
		// 1. 반복문을 이용해서 객체배열의 모든 요소와 obj가 일치하는지 확인한다.
		for (int i = 0; i < size; i++) {
			// 1.1 일치하면 remove(int index)를 호출해서 삭제하고 true를 반환한다.
			if (data[i].equals(obj)) {
				remove(i);
				return true;
			}
		}
		// 1.2 일치하는 것을 찾지 못하면, false를 반환한다.
		return false;
	}
	
	// Q4.
	public void clear() {
		// 코드를 완성하세요.
		// 객체배열을 비운다
		int oldSize = size;
		for (int i = 0; i < oldSize; i++) remove(0);
	}
	
	// Q4.
	public Object[] toArray() {
		// 객체배열을 복사해서 반환한다.
		Object[] copyArr = new Object[size];
		
		System.arraycopy(data, 0, copyArr, 0, size);		
		
		return copyArr;
	}
	
	// Q4.
	public String toString() {
		// 객체배열에 저장된 모든 객체를 출력한다.(모든 객체의 toString()을 호출한다.)
		StringBuffer results = new StringBuffer();
		results.append("[");
		
		for (int i = 0; i < size; i++) {
			//if (i != 0) {
			//	results.append(",").append(data[i]);
			//} else {
			//	results.append(data[i]);
			//}
			// Q4. ans.
			if (i == 0) results.append(",");
			results.append(get(i).toString());
		}
		
		results.append("]");
		
		return results.toString();
	}
	
	// Q5.
	public Iterator iterator() {
		// 다음의 코드를 완성하시오.
		// 1. 내부클래스 Itr의 객체를 생성해서 반환한다.
		return new Itr();
	}
	
	// Q5.
	private class Itr implements Iterator {
		int cursor = 0; // 읽어올 요소의 위치(index)
		int lastRet = -1;
		public Object next() {
			/*
			 *  다음의 코드를 완성하시오.
			 *  1. cursor가 가리키고 있는 위치(index)의 객체를 꺼내온다.(get() 사용)
			 *  2. cursor의 값을 lastRet에 저장하고 cursor의 값을 1 증가시킨다.
			 *  (에를 들어, cursor의 값이 1 이었으면 lastRet의 값은 1이 되고, cursor의 값은 2가 된다.)
			 *  3. 1에서 꺼내온 객체를 반환한다
			 *  
			 */
			return null;
		}
		
		public boolean hasNext() {
			/*
			 *  코드를 완성하세요.
			 *  hint : cursor의 값이 객체배열의 마지막요소의 위치(index)에 다다랐는지 확인한다.
			 */
			
			return false;
		}
		
		public void remove() {
			/*
			 *  다음의 코드를 완성하시오.
			 *  
			 *  1. lastRet의 값이 -1이면(직전에 읽어온 객체가 없거나 삭제 되었으면) IllegalStateException을 발생시킨다.
			 *  2. 직전에 읽어온 객체를 객체배열에서 제거한다.(MyVector의 remove() 사용)
			 *  3. lastRet의 값이 cursor의 값보다 작으면 cursor의 값을 1 감소 시킨다.
			 *  (현재 cursor의 위치보다 이전의 값이 삭제되면 cursor의 위치도 변경되어야 하므로)
			 *  4. lastRet에 -1을 저장한다.(직전에 읽어온 객체가 삭제 되었으므로)
			 */
		}
		
	} // private class Itr
	
} // class MyVector