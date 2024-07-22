pipeline{
    agent any
    stages{
        stage('Run cucumber test'){
            steps{
                sh 'mvn test -D cucumber.filter.tags="@AddPlace"'
            }
        }
    }