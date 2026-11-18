class Solution {
    public int minChanges(String s) {
        int count=0;
        int j=1;
        for(int i=0;i<s.length();i=i+2){
            if(s.charAt(j) != s.charAt(i)){
                count+=1;
            }
            j=j+2;
        }
        return count;
    }
}