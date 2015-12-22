package roundrobin;

import java.util.ArrayList;

public class Combination {
	
	//プライベートな変数・オブジェクトの宣言
	  ArrayList<ArrayList> _E = new ArrayList<ArrayList>();
	  int _n;
	  int _k;

	/*
	 * 目的: コンストラクタ。組合せに必要な変数を用意し，
	 * 組合せを作成する。
	 * 引数: nCk のn,k
	 */
	  Combination(int n,int k){
	    _n = n; _k = k;
	    //引数チェック
	    if (_k>_n || _k<0 || _n<0) {
	      _n=0;_k=0; //不当な引数であるため，0 とする。
	    }
	    int temp[] = new int[_k];

	    //(1)
	    Permutation _p = new Permutation(_n,_k);
	    //(2)
	    for (int i=0; i<_p.getSize(); ++i){
	      _p.getElements(i,temp);
	      //(3)
	      if(isUniqueElement(temp) == true){
	        ArrayList<Integer> Nums = new ArrayList<Integer>();
	        for(int j=0; j<_k; ++j) {
	          Nums.add(temp[j]);
	        }
	        _E.add(Nums);
	      }
	    }

	  }// end of Constructor Permutation()


	/*
	 * 目的: n,k へのアクセッサ
	 * 戻り値: n,k の値
	 */
	  public int getN(){
	    return _n;
	  }
	  public int getK(){
	    return _k;
	  }// end of getN,getK


	/*
	 * 目的: 指定番目(0..(nCk-1)) の組合せを返す。
	 * 引数: 添字, 組合せを格納する配列
	 */
	  public void getElements(int count, int val[]){
	    ArrayList<Integer> temp = new ArrayList<Integer>();
	    if(count < getSize()){
	      temp = _E.get(count);
	      for(int i=0; i<temp.size(); ++i){
	        val[i]=temp.get(i);
	      }
	    }
	  }// end of getElements


	/*
	 * 目的: 引数で指定された順番の順列を返す
	 * 引数: 生成した順列の添字
	 * 戻り値: CSV 形式の文字列
	 */
	  public String toString(int count){
	    ArrayList<Integer> temp = new ArrayList<Integer>();
	    temp = _E.get(count);
	    String result = "";
	    for (int i=0; i<_k; ++i){
	      result += temp.get(i);
	      if (i != _k-1) result += ",";
	    }
	    return result;
	  }// end of toString


	/*
	 * 目的: 組合せの数を返す
	 * 戻り値: 組合せの数
	 */
	  public long getSize(){
	    long size = 0;
	    size = factorial(_n) / factorial(_n - _k) / factorial(_k);
	    return size;
	  }// end of getSize


	  //- - - - - - - - - - - - - - - - - - - - - - -
	  //PRIVATE METHODS
	  //- - - - - - - - - - - - - - - - - - - - - - -

	  //階乗計算
	  private long factorial(int n) {
	    if ( n == 0 ) {// n が0 になったら1 を返して脱出　
	      return 1;
	    } else {
	      return n*factorial(n-1);// 再帰呼び出し
	    }
	  }


	  //_E に同じ組合せがないかチェック
	  //重複があればfalse を返す。ユニークならtrue。
	  private boolean isUniqueElement(int val[]){
	    java.util.Arrays.sort(val);
	    int temp[] = new int[_k];
	    for(int i=0; i<_E.size(); ++i){
	      getElements(i,temp);
	      java.util.Arrays.sort(temp);
	      boolean match = true;
	      for(int j=0; j<temp.length; ++j){
	        if(val[j] != temp[j]){
	          match = false;
	          break;//ほんのちょっと時間節約
	        }
	      }
	      //_E のi 番目の要素がval と一致したら終了
	      if(match == true) return false;
	    }
	    //val は_E の全ての要素と一致しなかった
	    return true;
	  }// end of isUniqueElement

}
