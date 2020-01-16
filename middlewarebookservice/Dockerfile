FROM gradle:jdk8-alpine AS BUILD
COPY --chown=gradle:gradle . /middlewarebookservice
COPY ./scripts/ /middlewarebookservice
WORKDIR /middlewarebookservice
RUN gradle clean build