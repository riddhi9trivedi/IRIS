@echo off
REM ================================
REM Allure Report Deployment Script
REM ================================

REM 1. Generate Allure report
echo Generating Allure report...
allure generate target\allure-results -o target\allure-report --clean

REM 2. Save current branch
for /f "tokens=*" %%i in ('git rev-parse --abbrev-ref HEAD') do set CURRENT_BRANCH=%%i
echo Current branch: %CURRENT_BRANCH%

REM 3. Switch to gh-pages branch
git checkout gh-pages

REM 4. Remove old files
echo Removing old files from gh-pages...
git rm -r *

REM 5. Copy new Allure report to root
echo Copying new Allure report...
xcopy /E /I /Y target\allure-report\* .

REM 6. Commit changes
echo Committing changes...
git add .
git commit -m "Deploy updated Allure report"

REM 7. Push to GitHub
echo Pushing to gh-pages...
git push origin gh-pages

REM 8. Switch back to original branch
git checkout %CURRENT_BRANCH%
echo Deployment complete!
pause