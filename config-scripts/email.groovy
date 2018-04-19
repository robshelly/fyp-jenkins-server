#!groovy
/**
 * Configures Jenkins email server
 */
// https://github.com/Accenture/adop-jenkins/pull/17/files?diff=split
// https://github.com/jenkinsci/mailer-plugin/blob/master/src/main/java/hudson/tasks/Mailer.java
import hudson.model.*;
import jenkins.model.*;
import hudson.tools.*;
import hudson.util.Secret;


def host = 'smtp.gmail.com'
def defaultSuffix = '@gmail.com'
def username = 'EMAIL'
def password = 'PWD'

def instance = Jenkins.getInstance()
def mailServer = instance.getDescriptor("hudson.tasks.Mailer")
def jenkinsLocationConfiguration = JenkinsLocationConfiguration.get()


// This sets reply to address
jenkinsLocationConfiguration.setAdminAddress(username)
jenkinsLocationConfiguration.save()


mailServer.setSmtpHost(host)
mailServer.setDefaultSuffix(defaultSuffix)
mailServer.setSmtpAuth(username, password)
mailServer.setUseSsl(true)
mailServer.setSmtpPort('465')
mailServer.setCharset("UTF-8")

// Save the state
instance.save()