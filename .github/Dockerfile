FROM ubuntu:latest

RUN apt-get update && apt-get install -y openjdk-8-jdk && apt-get clean
ADD https://ftp.nluug.nl/pub/graphics/blender/release/Blender2.81/blender-2.81a-linux-glibc217-x86_64.tar.bz2 blender.tar.gz
RUN tar -xf blender.tar.gz && rm blender.tar.gz
ENV PATH=/blender-2.81a-linux-glibc217-x86_64:$PATH
