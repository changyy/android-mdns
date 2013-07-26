package com.example.mdns_study;


import java.net.InetAddress;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceListener;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;

public class JMDNSAddServiceListenerActivity extends Activity {
	private static final String TAG = "JMDNS";
	private static JmDNS mJmDNS = null;
		
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.JMDNS_Activity);
        
        new Thread( new Runnable() {
	
			@Override
			public void run() {
			       try {
			           WifiManager wifi = (WifiManager) JMDNSAddServiceListenerActivity.this.getSystemService(Context.WIFI_SERVICE);
			           WifiInfo wifiinfo = wifi.getConnectionInfo();
			           int intaddr = wifiinfo.getIpAddress();
			           byte[] byteaddr = new byte[] { (byte) (intaddr & 0xff), (byte) (intaddr >> 8 & 0xff), (byte) (intaddr >> 16 & 0xff), (byte) (intaddr >> 24 & 0xff) };
	
			           mJmDNS = JmDNS.create(InetAddress.getByAddress(byteaddr));
			           mJmDNS.addServiceListener(
			        		   //"_airplay._tcp.local." , 
			        		   "_changyytcp._tcp.local.",
			        		   new ServiceListener() {
			        			   
									@Override
									public void serviceAdded(ServiceEvent arg0) {
										System.out.println("TXT:"+new String(arg0.getInfo().getTextBytes()) );
										// TODO Auto-generated method stub
										Log.w(TAG, String.format("serviceAdded(event=\n%s\n)", arg0.toString()));
									}
				
									@Override
									public void serviceRemoved(ServiceEvent arg0) {
										// TODO Auto-generated method stub
										Log.w(TAG, String.format("serviceRemoved(event=\n%s\n)", arg0.toString()));
									}
				
									@Override
									public void serviceResolved(ServiceEvent arg0) {
										// TODO Auto-generated method stub
										//System.out.println("serviceResolved TXT:"+new String(arg0.getInfo().getTextBytes()) );
										System.out.println("serviceResolve:"+arg0.getName());
										byte [] txt = arg0.getInfo().getTextBytes();
										if (txt.length > 0) {
											for( int i=0 ; i < txt.length ; ++i ) {	
												int len = ((int) txt[i] & 0xff);	// first byte is length of the "key=value"
												int begin_at = i+1;
												i += len; 
												System.out.println("TXT KeyValuePair:["+new String( txt, begin_at, len )+"]");
											}
										}
									}
			        		   }
			        	);
			       } catch (Exception e) {
			    	   // TODO Auto-generated catch block
			    	   e.printStackTrace();
			       }
			} 
		}).start();
    }
}
