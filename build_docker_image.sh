#!/usr/bin/env bash

TAG="[build_docker_image.sh]"
usage="Usage: PUSH=[false|true] ENV=[PROD|DEV] $0"

if [ "$PUSH" == "" ]; then
  PUSH="false"
fi

# version
VERSION=$(./gradlew -q printVersion)
APP_NAME="template-springboot"

# git
GIT_HASH=$(git rev-parse --short HEAD)
GIT_BRANCH=$(git rev-parse --abbrev-ref HEAD)
GIT_CURRENT_USER=$(git config user.name)

# docker repo
IMAGE_NAME="${APP_NAME}:${VERSION}"
IMAGE_NAME_LATEST="${APP_NAME}:latest"
IMAGE_NAME_GIT_HASH="${APP_NAME}:${GIT_HASH}"
DOCKER_REPO="gcr.io/torrestemplate"

################################################

# build jar
echo ""
echo "${TAG} ./gradlew clean build -x test"
./gradlew clean build -x test
if [ $? -ne 0 ]; then { echo "${TAG} Failed to build ${APP_NAME}" ; exit 1; }; fi
if [ $? -ne 0 ]; then { echo "${TAG} Can't copy build result ${APP_NAME}" ; exit 1; }; fi
rm -rf build/libs/*.jar.original

# build docker image
docker rmi ${IMAGE_NAME}
echo ""
echo "${TAG} docker build -t ${IMAGE_NAME} ."
docker build -t ${IMAGE_NAME} .
if [ $? -ne 0 ]; then { echo "${TAG} Failed to build ${IMAGE_NAME}" ; exit 1; }; fi

# tag docker image
function create_docker_tag() {
  local image_name=$1
  echo "${TAG} docker tag ${IMAGE_NAME} ${DOCKER_REPO}/${image_name}"
  docker tag ${IMAGE_NAME} ${DOCKER_REPO}/${image_name}
  if [ $? -ne 0 ]; then { echo "${TAG} Failed to tag image ${DOCKER_REPO}/${image_name}" ; exit 1; }; fi
}

echo ""
create_docker_tag ${IMAGE_NAME}
create_docker_tag ${IMAGE_NAME_GIT_HASH}
create_docker_tag ${IMAGE_NAME_LATEST}

# push docker 2 images (version, git hash)
function push_docker_image() {
  if [ "${PUSH}" == "true" ]
  then
    echo ""
    local image_name=$1
    echo "${TAG} docker push"
    docker push ${DOCKER_REPO}/${image_name}
    if [ $? -ne 0 ]; then { echo "${TAG} Failed to push image ${DOCKER_REPO}/${image_name}" ; exit 1; }; fi
  fi
}

push_docker_image ${IMAGE_NAME}
push_docker_image ${IMAGE_NAME_GIT_HASH}
push_docker_image ${IMAGE_NAME_LATEST}


# docker run
echo -e "\n${TAG} to test the docker image:"
echo -e "\ndocker run -it -p 8080:8080 ${DOCKER_REPO}/${IMAGE_NAME}\n"

