pipeline{
    agent any
    environment {
        PATH = "C:\\WINDOWS\\SYSTEM32"
    }
    stages{
        stage('Compile Stage'){
            steps{
                withMaven(maven:'maven_3_9_8'){
                    sh "mvn clean install"
                }
            }
        }
        stage('Test Stage'){
            steps{
                withMaven(maven:'maven_3_9_8'){
                    sh "mvn test"
                }
            }
        }
        stage('Cucumber Reports'){
            steps{
                cucumber buildStatus: "UNSTABLE"
                fileIncludePattern: "**/cucumber-report.json",
                jsonReportDirectory: 'target'
            }
        }
    }
}