FROM gradle:jdk8-alpine AS BUILD
COPY --chown=gradle:gradle . /config
WORKDIR /config
RUN gradle clean build