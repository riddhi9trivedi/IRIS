@echo off
REM ================================
REM Safe Allure Report Deployment Script (No Global Maven Needed)
REM ================================



REM 3. Save current branch
for /f "tokens=*" %%i in ('git rev-parse --abbrev-ref HEAD') do set CURRENT_BRANCH=%%i
echo Current branch: %CURRENT_BRANCH%

REM 4. Switch to gh-pages branch
git checkout gh-pages

REM 5. Remove old files
echo Removing old files from gh-pages...
git rm -r *

REM 6. Copy new Allure report to root
echo Copying new Allure report...
xcopy /E /I /Y target\allure-report\* .

REM 7. Commit changes
echo Committing changes...
git add .
git commit -m "Deploy updated Allure report"

REM 8. Push to GitHub
echo Pushing to gh-pages...
git push origin gh-pages

REM 9. Switch back to original branch
git checkout %CURRENT_BRANCH%

REM 10. Open GitHub Pages URL in default browser
echo Opening Allure report in browser...
start https://riddhi9trivedi.github.io/IRIS/

echo Deployment complete!
pause