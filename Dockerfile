FROM maven:3.6.3-jdk-8

RUN git clone https://github.com/MaiNorapong/atm-web.git
WORKDIR /atm-web
RUN mvn compile

ADD https://api.github.com/repos/MaiNorapong/atm-web/git/refs/heads/master version.json
RUN git pull
RUN mvn compile

RUN chmod +x wait-for-it.sh
EXPOSE 8090
CMD ["mvn", "exec:java", "-Dexec.mainClass=th.ac.ku.atm.AtmApplication"]
