android-mdns
============

Testing at Mac 10.8.3:

~~~
$ dns-sd -R device1 _changyytcp._tcp local 9900 k1=v1 k2=v2 k3=v3
Registering Service device1._changyytcp._tcp.local port 9900 TXT k1=v1 k2=v2 k3=v3
DATE: ---Wed 15 May 2013---
22:38:45.164  ...STARTING...
22:38:45.346  Got a reply for service device1._changyytcp._tcp.local.: Name now registered and active

$ dns-sd -R device2 _changyytcp._tcp local 9900                                 
Registering Service device2._changyytcp._tcp.local port 9900
DATE: ---Wed 15 May 2013---
22:49:23.819  ...STARTING...
22:49:24.486  Got a reply for service device2._changyytcp._tcp.local.: Name now registered and active
~~~

Runing on Nexus S:

~~~
05-15 22:51:00.071: I/System.out(3744): TXT:device1._changyytcp._tcp.local.
05-15 22:51:00.078: I/System.out(3744): TXT:device2._changyytcp._tcp.local.
05-15 22:51:00.102: I/System.out(3744): serviceResolve:device1
05-15 22:51:00.102: I/System.out(3744): TXT KeyPair:[k1=v1]
05-15 22:51:00.102: I/System.out(3744): TXT KeyPair:[k2=v2]
05-15 22:51:00.106: I/System.out(3744): TXT KeyPair:[k3=v3]
05-15 22:51:00.114: I/System.out(3744): serviceResolve:device2
~~~
