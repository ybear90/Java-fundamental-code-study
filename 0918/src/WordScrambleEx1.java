import java.util.Arrays;

/*
 * getAnswer(String[] strArr)는 배열strArr의 요소중의 하나를 임의로 골라서 반환한다.(Math.random()사용)
 * getScrambledWord(String str)는 주어진 문자열 str의 각 문자의 순서를 뒤섞은 다음, 새로운 문자열로 반환한다.(Math.random()사용)
 */

class WordScrambleEx1 {
      public static void main(String[] args) {
            String[] strArr = { "CHANGE", "LOVE", "HOPE", "VIEW"};

            String answer = getAnswer(strArr);
            String question = getScrambledWord(answer);

            System.out.println("Question:"+question);
            System.out.println("Answer:"+answer);
      } // main

      // 배열strArr의 요소중의 하나를 임의로 골라서 반환한다.(Math.random()사용)
      public static String getAnswer(String[] strArr) { 
    	  return strArr[(int)(Math.random() * strArr.length)];
      }
     
      // 주어진 문자열 str의 각 문자의 순서를 뒤섞은 다음, 새로운 문자열로 반환한다.(Math.random()사용)
      public static String getScrambledWord(String str) { 
    	  
    	  String result = "";
    	  
    	  // 1. 가져온 문자열을 임의로 섞는다
    	  // 섞기위해 문자열을 charArr로 바꾼다
    	  char[] scramStr = str.toCharArray();
    	  int randIdx; // 난수 발생에 사용할 index 변수
    	  char temp; // 문자열에 있는 글자 하나를 담는 변수
    	  
    	  // 난수를 발생시켜 shuffle 한다.
    	  for (int i = 0; i < scramStr.length; i++) {
    		  randIdx = (int)(Math.random() * scramStr.length);
    		  temp = scramStr[i];
    		  scramStr[i] = scramStr[randIdx];
    		  scramStr[randIdx] = temp;
    	  }
    	  
    	  // 섞어진 문자열을 하나씩 가져와 새로운 String 참조에 더한다.
    	  for (int i = 0; i < scramStr.length; i++) {
    		  result += scramStr[i];
    	  }
    	  
    	  // 더한 결과를 반환한다.
    	  return result;
      } // scramble(String str)
}