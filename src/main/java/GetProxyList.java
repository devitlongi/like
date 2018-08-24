import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GetProxyList {

    public List<ProxyString> proxiList (){
        System.out.print("Load Proxy List\t\t ");
        HexToDec hexToDec = new HexToDec();
//        String ipValidRegex = "([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])";
        URL url;
        InputStream is = null;
        BufferedReader br;
        String line;
        List<ProxyString> ipList = new ArrayList<>();


        try {
            url = new URL("http://www.gatherproxy.com/");
            is = url.openStream();  // throws an IOException
            br = new BufferedReader(new InputStreamReader(is));

            while ((line = br.readLine()) != null) {
//                System.out.println(line);
              if(line.contains("PROXY_IP")) {
                  String ip = line.substring(line.indexOf("PROXY_IP") + 11, line.indexOf("PROXY_LAST_UPDATE")-3);
                  String hostHex = line.substring(line.indexOf("PROXY_PORT") + 13, line.indexOf("PROXY_REFS")-3);
                  String hostDec =  hexToDec.getDec(hostHex);
                  ProxyString proxyString = new ProxyString(ip,hostDec);
//                  System.out.println(proxyString.ip + ":" + proxyString.host);
                  ipList.add(proxyString);
                }
            }
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException ioe) {
                System.out.println("Stranka je prazdna");
            }
        }
        System.out.println(" Complete");
        return ipList;
    }
}
