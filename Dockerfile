FROM jenkins/jenkins:lts
USER root

    # AWS CLI
RUN apt-get update
RUN apt-get install -y python python-pip
RUN pip install --upgrade pip
RUN pip install --upgrade awscli

USER jenkins

ENV JAVA_OPTS="-Djenkins.install.runSetupWizard=false"

COPY jobs  /usr/share/jenkins/ref/jobs
COPY config-scripts/security.groovy /usr/share/jenkins/ref/init.groovy.d/security.groovy
COPY config-scripts/jobs.groovy /usr/share/jenkins/ref/init.groovy.d/jobs.groovy
COPY config-scripts/email.groovy /usr/share/jenkins/ref/init.groovy.d/email.groovy
COPY plugins.txt /usr/share/jenkins/ref/plugins.txt

ARG jenkins_username
ARG jenkins_password
ARG email_address
ARG email_password
RUN sed -i s/USERNAME/$jenkins_username/g /usr/share/jenkins/ref/init.groovy.d/security.groovy && \
    sed -i s/PASSWORD/$jenkins_password/g /usr/share/jenkins/ref/init.groovy.d/security.groovy && \
    sed -i s/EMAIL/$email_address/g /usr/share/jenkins/ref/init.groovy.d/email.groovy && \
    sed -i s/PWD/$email_password/g /usr/share/jenkins/ref/init.groovy.d/email.groovy


RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt
