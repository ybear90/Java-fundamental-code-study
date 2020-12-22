
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
		
		v.clear();
		System.out.println(v);
		System.out.println("size:"+v.size());
		System.out.println("capacity:"+v.capacity());
		System.out.println("isEmpty:"+v.isEmpty());
	}

}
