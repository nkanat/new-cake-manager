pipeline {
    agent any
    tools {
        maven 'Maven'
        dockerTool 'Docker'
    }
    environment {
        DATE = new Date().format('yy.M')
        TAG = "${DATE}.${BUILD_NUMBER}"
        dockerHome = tool 'Docker'
        PATH = "${dockerHome}/bin:${env.PATH}"
    }
    stages {
        stage ('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Docker Build') {
            steps {
                script {
                    docker.build("nkanat/cake-manager:${TAG}")
                }
            }
        }
	    stage('Pushing Docker Image to Dockerhub') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'docker_credential') {
                        docker.image("nkanat/cake-manager:${TAG}").push()
                        docker.image("nkanat/cake-manager:${TAG}").push("latest")
                    }
                }
            }
        }
        stage('Deploy'){
            steps {
                sh "docker stop cake-manager | true"
                sh "docker rm cake-manager | true"
                sh "docker run --name cake-manager -d -p 8088:8088 nkanat/cake-manager:${TAG}"
            }
        }
    }
}