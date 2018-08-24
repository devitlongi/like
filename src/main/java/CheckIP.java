import java.net.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import  java.io.InputStream;

import java.net.URL;

public class CheckIP {


        public  int check(String ip,String host)  throws IOException {
            URL url;
            InputStream is = null;
            BufferedReader br;
            String line;

            System.out.print("Testing Proxy\t" + ip+":"+host);

            // adding proxy details

            System.setProperty("http.proxyHost",ip);
            System.setProperty("http.proxyPort", host);
            // testing for changes is done

            try {
                url = new URL("http://ipecho.net/plain");
                is = url.openStream();  // throws an IOException
                br = new BufferedReader(new InputStreamReader(is));

                while ((line = br.readLine()) != null) {
//                    System.out.println(line);
                    if(line.contains(ip)) {
                        System.out.println("\tProxy OK,");
                        return 1;
                    }
                }
            } catch (MalformedURLException mue) {
                mue.printStackTrace();
            }  finally {
                try {
                    if (is != null) {
                        is.close();
                    }

                } catch (IOException ioe) {
                    System.out.println("nothink to see on \"http://ipecho.net/plain\"n");
                }
            }

            return 0;
        }
        }
//            URL ourURL = null;
//            HttpURLConnection huc = null;
//            try {
//
//                System.setProperty("http.proxyHost", ip);
//                System.setProperty("http.proxyPort", host);
//
//                ourURL = new URL("http://www.blanksite.com/");
//                huc = (HttpURLConnection) ourURL.openConnection();
//                huc.setRequestMethod("GET");
//                try {
//                    huc.connect();
//                } catch (Exception es) {
//                    es.printStackTrace();
//                    System.out.println("Exception " + es.getMessage());
//                    System.out.println("RESPONSE CODE" + huc.getResponseCode());
//                    Thread.sleep(2000);
//                    huc.connect();
//                }
//                BufferedReader reader = new BufferedReader(new InputStreamReader(
//                        huc.getInputStream()));
//
//                String line = null;
//
//                while ((line = reader.readLine()) != null) {
//                    System.out.println(line);
//                }
//            } catch (IOException ioe) {
//                // ioe.printStackTrace();
//            } catch (Exception e) {
//                System.err.println("General Exception " + e);
//                e.printStackTrace();
//            }
//        }
//    }


