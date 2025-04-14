From k8s.jrx.com/library/alpine-openjdk8:latest


ENV APP_NAME anyapi-service-server

ENV APP_MAIN com.anytech.anytxn.anyapi.server.AnyapiApplication

ADD target/$APP_NAME.tar.gz /app

ENTRYPOINT ["sh","/app/bin/docker_entrypoint.sh"]