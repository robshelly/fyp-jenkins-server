# Jenkins Server for Backup Test Centre

A Jenkins instance serves as the management server for the Backup Restoration centre. This repo contains the files neccessary to build a Jenkins image for such a systems

The image build performs the following functions:

* Performs the Jenkins installatino inlcuding creating the necessary admin user;
* Install necessary anad recommended plugins;
* Create the basic Jenkins jobs required for the Backup Restoration Centre;
* Configures the SMTP server on Jenkins to allow email notifications of job status

## Commands

    docker build \
        --build-arg jenkins_username='admin' \
        --build-arg jenkins_password='fyp-project' \
        --build-arg email_address='EMAIL' \
        --build-arg email_password='PASSWORD' \
        -t rshelly/jenkins-backup-test-centre .

# Run image
   
    docker run -d --name jenkins -p 8080:8080 rshelly/jenkins-backup-test-centre
