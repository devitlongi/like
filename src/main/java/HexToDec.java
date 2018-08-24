public class HexToDec {
    public String getDec(String h){
        String digits = "0123456789ABCDEF";

        String hex = h.toUpperCase();
        int val = 0;
        for (int i = 0; i < hex.length(); i++)
        {
            if(digits.contains(hex.charAt(i)+"")) {
                char c = hex.charAt(i);
                int d = digits.indexOf(c);
                val = 16 * val + d;
            }
            else return "badIP";
        }
        return String.valueOf(val);
    }

}


