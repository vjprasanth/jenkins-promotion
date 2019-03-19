#!/usr/bin/env groovy

def tag = 'release'

pipeline {
  agent any

  environment {
    APP_VERSION = "${env.BUILD_NUMBER}"

    GITHUB_REPO = 'jenkins-promotion'
  }

  stages {
    stage('Test') {
      steps {
        sh 'yarn test'
      }
    }

    stage('Build') {
      steps {
        sh 'yarn build'
      }
    }

    stage('Create and Push tag') {
      steps {
        createGitTag(tag)
      }
    }
  }
}

def createGitTag(String tagName) {
  sh "git tag ${tagName}-${APP_VERSION}"
  sh "git push https://github.com/vjprasanth/${GITHUB_REPO}.git --tags"

}
