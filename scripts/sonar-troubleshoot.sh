#!/bin/bash

# SonarCloud Troubleshooting Script
echo "🔍 SonarCloud Troubleshooting Script"
echo "====================================="

# Check Java version
echo "1. Checking Java version..."
java -version

# Check Maven version
echo -e "\n2. Checking Maven version..."
mvn -version

# Clean previous builds and caches
echo -e "\n3. Cleaning previous builds and caches..."
mvn clean
rm -rf ~/.sonar/cache/*

# Check if required secrets/properties are set
echo -e "\n4. Checking SonarCloud configuration..."
if [ -f "sonar-project.properties" ]; then
    echo "✅ sonar-project.properties found"
    grep -E "(sonar\.projectKey|sonar\.organization)" sonar-project.properties
else
    echo "❌ sonar-project.properties not found"
fi

# Check pom.xml for SonarCloud configuration
echo -e "\n5. Checking pom.xml for SonarCloud plugin..."
grep -A 5 "sonar-maven-plugin" pom.xml || echo "❌ SonarCloud plugin not found in pom.xml"

# Verify network connectivity to SonarCloud
echo -e "\n6. Testing connectivity to SonarCloud..."
curl -I https://sonarcloud.io || echo "❌ Cannot reach SonarCloud"

echo -e "\n7. Recommendations:"
echo "   - Ensure SONAR_TOKEN is set correctly"
echo "   - Verify your organization and project key match your SonarCloud setup"
echo "   - Try running: mvn clean compile test jacoco:report"
echo "   - Then run: mvn sonar:sonar"

echo -e "\n✅ Troubleshooting complete!"