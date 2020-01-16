FROM gradle:jdk8-alpine AS BUILD
COPY --chown=gradle:gradle . /gateway
COPY ./scripts/ /gateway
WORKDIR /gateway
RUN gradle clean build