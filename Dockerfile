FROM maven:3.8.5-openjdk-11-slim
# copying entire framework
COPY ./. /home/auto-pilot
# check java installation
RUN java -version
# run maven version check
RUN mvn -v
# package the project to jar by skipping tests
RUN mvn -f /home/auto-pilot clean package -DskipTests=true