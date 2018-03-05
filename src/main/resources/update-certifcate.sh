#!/bin/bash
set -x #echo on

cd saml/

#IDP_HOST=account.google.com
#IDP_PORT=443
#CERTIFICATE_FILE=GoogleIDPCertificate.pem
CERTIFICATE_FILE=HPIDPCertificate.crt
KEYSTORE_PASSWORD=Shaklee1
ALIAS=healthprintidp
KEYSTORE_FILE=HPKEY2.jks

#openssl s_client -host $IDP_HOST -port $IDP_PORT -prexit -showcerts </dev/null | sed -ne '/-BEGIN CERTIFICATE-/,/-END CERTIFICATE-/p' > $CERTIFICATE_FILE
#keytool -delete -alias ssocircle -keystore $KEYSTORE_FILE -storepass $KEYSTORE_PASSWORD

keytool -genkey -alias $ALIAS -keyalg RSA -keystore $KEYSTORE_FILE -storepass $KEYSTORE_PASSWORD  -dname "ST=California, C=US, OU=HybrisDev, CN=Shaklee, L=Pleaseonton, O=Shaklee"

#keytool -import -alias $ALIAS -file $CERTIFICATE_FILE -keystore $KEYSTORE_FILE -storepass $KEYSTORE_PASSWORD -noprompt

keytool -list -V -storepass $KEYSTORE_PASSWORD -keystore $KEYSTORE_FILE
