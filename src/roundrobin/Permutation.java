package roundrobin;

import java.util.ArrayList;

public class Permutation {
	
	//プライベートな変数・オブジェクトの宣言
	  ArrayList<ArrayList> _E = new ArrayList<ArrayList>();
	  int _n;
	  int _k;

	/*
	 * 目的: コンストラクタ。順列に必要な変数を用意し，
	 *      順列を作成する。
	 * 引数: nPk のn,k
	 */
	  Permutation(int n,int k){
	    _n = n; _k = k;
	    //引数チェック
	    if (_k>_n || _k<0 || _n<0) {
	      _n=0;_k=0; //不当な引数であるため，0 とする。
	    }
	    int val[] = new int[_k];

	    //(1)
	    for(int j=0; j<_k; ++j) val[j] = 0;
	    //(2)
	    for (int j=0; j<Math.pow(_n,_k); ++j){
	      incrementVal(val,0);
	      //(3)
	      if (isNoDup(val) == true) {
	        //(4)
	        ArrayList<Integer> Nums = new ArrayList<Integer>();
	        for(int jj=0; jj<_k; ++jj) {
	          Nums.add(val[jj]);
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
	 * 目的: 指定番目(0..(nPk-1)) の順列を返す。
	 * 引数: 添字, 順列を格納する配列
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
	 * 目的: 順列の数を返す
	 * 戻り値: 順列の数
	 */
	  public long getSize(){
	    long size = 0;
	    size = factorial(_n) / factorial(_n - _k);
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


	  //n 進数k 桁の数値をインクリメント
	  private void incrementVal(int val[],int cnt){
	    if(cnt >= _k){
	      //k 桁を超える桁上がり。全桁をゼロに。
	      for(int i=0; i <_k; ++i) val[i]=0;
	    } else {
	      //インクリメント操作
	      ++val[cnt];
	      if(val[cnt]>=_n){
	        //桁上がり
	        for(int i=0; i <= cnt; ++i) val[i]=0;
	        incrementVal(val,cnt+1);
	      }
	    }
	  }// end of incrementVal


	  //引数の配列にセットされた数値の重複チェック
	  //重複があればfalse を返す。
	  private boolean isNoDup(int val[]){
	    int num[] = new int[_n];
	    for(int i=0; i<_n; ++i) num[i]=0;
	    for(int j=0; j<_k; ++j){
	      ++num[val[j]];
	      if (num[val[j]] > 1) return false;
	    }
	    return true;
	  }// end of isNoDup

}
