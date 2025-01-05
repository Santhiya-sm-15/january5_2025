class Solution {
    public String shiftingLetters(String s, int[][] shifts) {
        int n=s.length(),i;
        int[] prefix=new int[n];
        for(int[] x:shifts)
        {
            int l=x[0],r=x[1];
            if(x[2]==1)
            {
                prefix[l]+=1;
                if(r+1<n)
                    prefix[r+1]-=1;
            }
            else
            {
                prefix[l]-=1;
                if(r+1<n)
                    prefix[r+1]+=1;
            }
        }
        for(i=1;i<n;i++)
            prefix[i]+=prefix[i-1];
        StringBuilder sb=new StringBuilder();
        for(i=0;i<n;i++)
        {
            int z=s.charAt(i)-'a';
            if(prefix[i]>0)
                z=(z+prefix[i])%26;
            else if(prefix[i]<0)
                z=(z+(prefix[i]%26)+26)%26;
            sb.append((char)(z+'a'));
        }
        return sb.toString();
    }
}