FROM gradle:jdk8-alpine AS BUILD
COPY --chown=gradle:gradle . /config
COPY ./scripts/ /config
WORKDIR /config
RUN gradle clean build