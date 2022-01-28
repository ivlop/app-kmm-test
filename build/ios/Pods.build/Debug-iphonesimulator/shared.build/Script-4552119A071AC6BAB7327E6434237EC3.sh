#!/bin/sh
                if [ "YES" = "$COCOAPODS_SKIP_KOTLIN_BUILD" ]; then
                  echo "Skipping Gradle build task invocation due to COCOAPODS_SKIP_KOTLIN_BUILD environment variable set to "YES""
                  exit 0
                fi
                set -ev
                REPO_ROOT="$PODS_TARGET_SRCROOT"
                "$REPO_ROOT/../gradlew" -p "$REPO_ROOT" $KOTLIN_PROJECT_PATH:syncFramework                     -Pkotlin.native.cocoapods.platform=$PLATFORM_NAME                     -Pkotlin.native.cocoapods.archs="$ARCHS"                     -Pkotlin.native.cocoapods.configuration=$CONFIGURATION

