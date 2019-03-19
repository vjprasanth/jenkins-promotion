#!/usr/bin/env groovy
def basePath = 'Reconciliation Service'

folder(basePath) {
    description 'This job will create a seed-job for Reconciliation Service'
}

multibranchPipelineJob("$basePath/master") {
    displayName('01 - Build')

    branchSources {
        git {
            remote('https://github.com/vjprasanth/jenkins-promotion.git')
            includes('master')
        }
    }
    orphanedItemStrategy {
        discardOldItems {
            daysToKeep(2)
        }
    }
    factory {
        workflowBranchProjectFactory {
            scriptPath('cicd/pipelines/masterBuild.groovy')
        }
    }
    triggers {
        periodic(2)
    }
}

