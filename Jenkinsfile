pipeline{
    agent any
    environment {
        PATH = "C:\\WINDOWS\\SYSTEM32"
    }
    stages{
        stage('Run cucumber test'){
            steps{
                bat "mvn clean test"
            }
        }
    }
}