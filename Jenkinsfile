#!groovy

properties([disableConcurrentBuilds()])

pipeline {
    agent {
        label 'master'
    }
    triggers { pollSCM('* * * * *') }
    options {
        buildDiscarder(logRotator(numToKeepStr: '10', artifactNumToKeepStr: '10'))
        timestamps()
    }
    stages {

        stage("Tests") {
            steps {
                tests("adyen-payment-integration-service")
            }
        }
        stage("check branch and push to Docker hub repository") {
            when {
                expression {
                    print(env.BRANCH_NAME)
                    return env.BRANCH_NAME == 'master';
                }
            }
            steps {
                pushImageToRepository("adyen-payment-integration-service")
            }
        }
    }
}
