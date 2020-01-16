FROM gradle:jdk8-alpine AS BUILD
COPY --chown=gradle:gradle . /mainbookservice
COPY ./scripts/ /mainbookservice
WORKDIR /mainbookservice
RUN gradle clean build