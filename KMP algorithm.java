import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String pattern = sc.nextLine();
        int n = s.length();
        int lps[] = lpsMaker(pattern);
        int i = 0;
        int j = 0;
        boolean flag = false;
        while ( i < n){
            if(s.charAt(i) == pattern.charAt(j)){
                i++;
                j++;
                if(j == pattern.length()){
                    flag = true;
                    break;
                }
            }
            else{
                if(j > 0){
                    j = lps[j-1];
                }
                else{
                    i++;
                }
            }
        }
        System.out.println(flag); 
    }
    public static int[] lpsMaker(String pattern){
        int m = pattern.length();
        int[] lps = new int[m];
        int maxlen = 0;
        int i = 1;
        while ( i < m){
            if(pattern.charAt(i) == pattern.charAt(maxlen)){
                maxlen++;
                lps[i] = maxlen;
                i++;
            }
            else{
                if ( maxlen > 0){
                    maxlen = lps[maxlen - 1];
                }
                else{
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }
}
