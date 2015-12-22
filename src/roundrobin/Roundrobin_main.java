package roundrobin;

public class Roundrobin_main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int n=10;
	    int k=5;

	    Combination c = new Combination(n,k);

	    System.out.println("C("+c.getN()+","+c.getK()+")="
	                       +c.getSize());

	    System.out.println("全ての組合せを表示します。");

	    System.out.println("toString を利用");
	    for(int i=0; i<c.getSize(); ++i){
	      System.out.println(i+1+ ":" + c.toString(i));
	    }

	    System.out.println("直接値を取得");
	    int temp[] = new int[k];
	    for(int i=0; i<c.getSize(); ++i){
	      String result = "";
	      c.getElements(i,temp);
	      result += i+":";
	      for(int j=0; j<temp.length; ++j){
	        result += temp[j];
	        if(j!=temp.length-1){
	          result += ",";
	        }
	      }
	      System.out.println(result);
	    }
		
	}

}
