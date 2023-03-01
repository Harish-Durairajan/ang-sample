pipeline {
    agent any
    stages{
        stage("pulling the source"){
            steps{
                sh git init
		sh git remote add origin "https://github.com/Harish-Durairajan/AngularSample.git"
		sh git pull origin master
		 
            }
        }
        stage("clearing the workspace"){
            steps{
                sh clean wsl
            }
        }
	stage("pulling latest source"){
            steps{
                sh git pull origin master
            }
        }        
        stage("building the angular application"){
            steps{
		sh ng build                
            }
        }
        stage("pushing s3 bucket"){
            steps{
              sh "aws s3 cp dist/hello-angular s3://jenkinsangular-harish --recursive"
            }
        }      

    }
}