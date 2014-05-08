If SSL is enabled on DB2, execute the following steps to add certificate to a keystore.

1. On DB2, copy the following file to where the up.time monitoring station is.  The path might be different on your sever:

	D:\Program Files\IBM\SQLLIB\security\keystore\mydbserver.arm

2. Import the above certificate to your server where up.time monitoring station is installed:

	keytool -importcert -file mydbserver.arm -keystore mystore

   Note: You will be asked for a keystore password.  Take note of the keystore password because it is needed when you add the service monitor.

After the above steps are finished, you are now ready to use the plugin in up.time.

