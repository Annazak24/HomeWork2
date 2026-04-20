pipeline {
    agent any

    tools {
        allure 'Allure 2.30'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Run Tests in Docker') {
            steps {
                sh '''
                    rm -rf allure-results
                    mkdir -p allure-results

                    docker build -t api-tests .

                    docker run --rm \
                      -v ${WORKSPACE}/allure-results:/app/allure-results \
                      api-tests
                '''
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'allure-results/**', allowEmptyArchive: true

            allure([
                includeProperties: false,
                jdk: '',
                properties: [],
                reportBuildPolicy: 'ALWAYS',
                results: [[path: 'allure-results']]
            ])
        }
    }
}