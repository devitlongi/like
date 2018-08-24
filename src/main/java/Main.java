import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Set set = new Set();
        ProxyString proxyString;
        CheckIP checkIP = new CheckIP();

        GetProxyList getProxyList = new GetProxyList();

        List<ProxyString> ipList = getProxyList.proxiList();
        if (!ipList.isEmpty()) {
//            for(int i=0;i<ipList.size();i++)
            for (ProxyString ipHost : ipList) {
                try {
                    if (checkIP.check(ipHost.ip,ipHost.host) == 1) {
                        try {
                            System.out.println("set firefox setings:" + set.proxy(ipHost.ip, ipHost.host));
                            }
                            catch(IOException eFirefox){
                                eFirefox.printStackTrace();
                                System.out.println("\t************ IO Error firefox");
                        }
                        catch(Exception exp){
                            exp.printStackTrace();
                            System.out.println("*********exception");

                        }
                    } else {
                        System.out.println("\t************   Invalid");
                    }
                } catch (IOException e) {
                    System.out.println("\t************   invalid");
                }
            }
            System.out.println("koniec");
        }
    }
}

