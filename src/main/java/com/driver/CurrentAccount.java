package com.driver;
import java.util.*;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name,balance,5000);
        this.tradeLicenseId=tradeLicenseId;
        if(balance<5000) throw new Exception("Insufficient Balance");
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        if(!isNumberValid(tradeLicenseId)) {
            String rearrangedId = arrange(tradeLicenseId);
            if (rearrangedId == "")
                throw new Exception("Valid License can not be generated");
            else this.tradeLicenseId = rearrangedId;
        }
    }
    public boolean isNumberValid(String s){
        for(int i=0;i<s.length()-1;i++){
            if(s.charAt(i)==s.charAt(i+1))
                return false;
        }
        return true;
    }
    public String arrange(String s){
        int n=s.length();
        HashMap<Character,Integer> map=new HashMap<>();
        int max=0;
        char ch_max=0;
        for(int i=0;i<n;i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
            if(map.get(s.charAt(i))>max) {
                ch_max = s.charAt(i);
                max=map.get(s.charAt(i));
            }
        }
        // validation for not possible
        if(n%2==0){
            if(max>(n/2)) return "";
        }
        else {
            if (max > (n / 2)) return "";
        }
        // it's possible
        char ans[]=new char[n];
        int index=0;
        for(;index<n;index+=2){  // every alternate index
            if(max>0){
                ans[index]=ch_max;
                max--;
            }
        }
        for(char ch:map.keySet()){
            if(ch==ch_max) continue;
            if(index>=n){
                index=1;
                while(map.get(ch)>0){
                    ans[index]=ch;
                    map.put(ch,map.get(ch)-1);
                    index+=2;
                }
            }
            else{
                while(map.get(ch)>0){
                    ans[index]=ch;
                    map.put(ch,map.get(ch)-1);
                    index+=2;
                }
            }
        }
        String ans1=Arrays.toString(ans);
        return ans1;
    }

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }
}
