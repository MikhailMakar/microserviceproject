FROM gradle:jdk8-alpine AS BUILD
COPY --chown=gradle:gradle . /discovery
COPY ./scripts/ /discovery
WORKDIR /discovery
RUN gradle clean build