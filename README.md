# CI/CD Demo with SonarCloud & Snyk

> **Practical 4A**: Integrating SonarCloud & Snyk with GitHub Actions

## Quick Setup

### Required GitHub Secrets

Configure these 4 secrets in your repository:

1. **SNYK_TOKEN** (Required)

   - Go to [Snyk.io][snyk] and create an account
   - Navigate to Account Settings → API Token
   - Copy your API token
   - Add it as a repository secret named `SNYK_TOKEN`

2. **SONAR_TOKEN** (Required for SonarCloud)

   - Create an account at [SonarCloud.io][sonarcloud]
   - Create a new project for your repository
   - Generate a token in your SonarCloud account settings
   - Add it as a repository secret named `SONAR_TOKEN`

3. **SONAR_ORGANIZATION** (Required for SonarCloud)

   - Your SonarCloud organization key (found in your SonarCloud account)
   - Add it as a repository secret named `SONAR_ORGANIZATION`

4. **SONAR_PROJECT_KEY** (Required for SonarCloud)

   - **This is NOT the same as organization key!**
   - This is your specific project identifier, usually: `organization_repository-name`
   - To find it: Go to SonarCloud → Your Project → Project Settings → Copy "Project Key"
   - Example format: `organization_repository-name` or `Organization_repository-name`
   - Add it as a repository secret named `SONAR_PROJECT_KEY`

### How to Add Secrets

1. Go to your repository settings: `Settings → Secrets and variables → Actions`
2. Click **New repository secret** for each secret above
3. Push any commit to trigger the workflows

## Project Status

✅ **SonarCloud**: Code quality and security analysis  
✅ **Snyk**: Dependency vulnerability scanning  
✅ **GitHub Actions**: Automated CI/CD pipeline  

## Results & Screenshots

### 1. SonarCloud SAST Scan ✅
**Workflow**: `sonarcloud.yml` | **Duration**: 1m 2s | **SAST Scan**: 58s

![SonarCloud SAST Scan](image1.png)

*Successful Static Application Security Testing scan integrated with GitHub Actions*

### 2. Snyk Security Scan ✅  
**Workflow**: `basic-security.yml` | **Duration**: 27s | **Security Scan**: 24s

![Snyk Security Scan](image2.png)

*Dependency vulnerability scanning with Snyk detecting potential security issues*

### 3. SonarCloud Quality Dashboard ✅
**Project**: cicd-demo | **Lines of Code**: 125 (XML, Java) | **Quality Gate**: Passed

![SonarCloud Quality Dashboard](image3.png)

**Quality Metrics:**
- 🔒 **Security**: 0 issues (Grade A)
- 🛠️ **Reliability**: 0 issues (Grade A)  
- 📊 **Maintainability**: 3 issues (Grade A)
- 🔍 **Security Hotspots**: 100% reviewed
- 📈 **Code Coverage**: 93.3%
- 📋 **Duplications**: 0.0%

## Troubleshooting

### SonarCloud Issues

If you encounter `InvalidProtocolBufferException` or other SonarCloud errors:

1. **Run the troubleshooting script**:
   ```bash
   ./scripts/sonar-troubleshoot.sh
   ```

2. **Manual troubleshooting steps**:
   ```bash
   # Clean build and cache
   mvn clean
   rm -rf ~/.sonar/cache/*
   
   # Build project step by step
   mvn compile
   mvn test
   mvn jacoco:report
   mvn sonar:sonar
   ```

3. **Common fixes**:
   - Ensure your `SONAR_TOKEN` is valid and not expired
   - Verify your organization name matches your SonarCloud account
   - Make sure your project key is correct in both `pom.xml` and `sonar-project.properties`
   - Check that your SonarCloud project exists and you have permissions

### "Could not find a default branch" Error

If you get: `Could not find a default branch for project with key '***'`

**Solution Steps:**

1. **Check SonarCloud Project Exists**:
   - Go to [SonarCloud.io][sonarcloud]
   - Verify your project appears in your project list
   - If not, click "Analyze new project" and import from GitHub

2. **Get Correct Project Key**:
   - In SonarCloud → Your Project → Project Settings
   - Copy the exact "Project Key"
   - Update the `SONAR_PROJECT_KEY` secret in GitHub

3. **Common Project Key Formats**:
   - `Organization_repository-name` (with capital letter)
   - `organization-suffix_repository-name` (with organization prefix)
   - `repository-name` (simple name)

4. **Verify GitHub Integration**:
   - SonarCloud → Organization → Administration → GitHub Integration
   - Ensure your repository is connected

## ✅ Implementation Success

This project successfully demonstrates:

- **✅ Static Application Security Testing (SAST)** with SonarCloud
- **✅ Dependency Vulnerability Scanning** with Snyk  
- **✅ Automated Quality Gates** ensuring code quality standards
- **✅ Continuous Integration/Continuous Deployment** pipeline
- **✅ Security-First Development** approach with automated scanning

### Key Achievements:
- **Zero Security Vulnerabilities** detected
- **High Code Coverage** at 93.3%
- **Fast Pipeline Execution** (under 2 minutes)
- **Automated Security Reviews** on every commit
- **Quality Gate Compliance** with industry standards

## Configuration

**SonarCloud Settings:**
- Organization: Configured via `SONAR_ORGANIZATION` secret
- Project Key: Configured via `SONAR_PROJECT_KEY` secret
- Quality Gate: **✅ PASSED**

## Active Workflows

| Workflow | Trigger | Purpose | Status |
|----------|---------|---------|--------|
| **sonarcloud.yml** | Push to main/master, PRs | SonarCloud SAST analysis | ✅ Active |
| **basic-security.yml** | Push to main/master, PRs | Snyk dependency scanning | ✅ Active |  
| **maven.yml** | Push to master, PRs | Build, test, and integrated security | ✅ Active |

## 🔗 Links

- **GitHub Repository**: [View Repository][repo]
- **GitHub Actions**: [View Workflows][actions]
- **SonarCloud Project**: [View Dashboard][sonarcloud-project]

---

<!-- Reference Links -->
[repo]: https://github.com/KeldenPDorji/cicd-demo_sq
[actions]: https://github.com/KeldenPDorji/cicd-demo_sq/actions
[sonarcloud-project]: https://sonarcloud.io/project/overview?id=KeldenPDorji_cicd-demo_sq
[sonarcloud]: https://sonarcloud.io
[snyk]: https://snyk.io
