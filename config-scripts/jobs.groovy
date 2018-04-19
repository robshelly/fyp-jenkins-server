#!groovy

/**
 * Configures jobs on Jenkins
 */
import jenkins.model.*

def instance = Jenkins.getInstance()
def jobs = [
  'auto-pipeline-backup-decrypt-file',
  'auto-pipeline-backup-decrypt-latest',
  'auto-pipeline-backup-restoration',
  'auto-pipeline-backup-restore-json',
  'auto-pipeline-deploy-restoration-server',
  'auto-pipeline-destroy-restoration-server'
]

for (String job : jobs) {
  String configXml = new File('/usr/share/jenkins/ref/jobs/'+job+'.xml').text

  def xmlStream = new ByteArrayInputStream( configXml.getBytes() )

  Jenkins.instance.createProjectFromXML(job, xmlStream)
  instance.save()
}