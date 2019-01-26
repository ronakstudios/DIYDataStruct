
public class MergeSort {

	//int[] a = {234,52,23,64,2,4,6,3,6,2,58,9,3,5,7,8,54,3,6,8};
	//System.out.println(print(mergesort(a)));
	
	public static int[] mergesort(int[] in) {
	
		if(in.length==1) //Works as edge case and as the stop condition for recursion!
			return in;
		
		int left = (int) (in.length/2.0);
		int right = in.length-left;
		int[] l = new int[left];
		int[] r = new int[right];
		for(int i = 0; i<in.length;i++)
			if(i<left)
				l[i]=in[i];
			else
				r[i-left]=in[i];
		
		
		
		int[] ll = mergesort(l);
		int[] rr = mergesort(r);
		
		
		
		return merge(ll,rr);
		
		
	}
	private static int[] merge(int[] l, int[] r) {
		int[] merge = new int[l.length+r.length];

		//see udiprod video about this
				
		int right = 0;
		int left = 0;
		for(int i = 0; i<l.length+r.length;i++) {
			
			
			
			if(right!=r.length&&(left==l.length||r[right]<l[left])) {
				merge[i] = r[right];
				right++;
			}else {
				merge[i] = l[left];
				left++;
			}
			
		}
			
		
		return merge;
		
	}
	
	public static String print(int[]a) {
		String z = "";
		for(int i = 0; i<a.length;i++) {
			z+=a[i]+((i==a.length-1)?"":",");
		}
		return z;
	}

}
